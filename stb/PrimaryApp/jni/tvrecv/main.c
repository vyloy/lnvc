/******************************************************
*******************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/errno.h>
#include "HostIncludeApi.h"
#include "SocketHeader.h"
#include "../../../tvdaemon/ipc/child_body.h"
#include "../../../tvdaemon/ipc/manage_pid.h"
#include "../../../tvdaemon/ipc/fun_typedef.h"

#ifdef ANDROID
char *p_log_tag_str = "tvrecv";
#endif

#if 1//OS_LINUX

//#define BUF_LEN (100 * 188)//3760

#define UCHAR unsigned char
#define DTUSB_BUFFER_LEN_PER_RECV (1024*3)
#define ULONG unsigned long
#define VOID void
#define BYTE unsigned char
#define UINT unsigned int
#define HANDLE UTIS32
#define USHORT unsigned short
#define BOOLEAN int
#define TRUE 1
#define FALSE 0


#define DBG 1

#define TS_HEADER_GET_PID(TsPacket)   (((TsPacket[1]&0x1f)<<8) + TsPacket[2])
#define TS_HEADER_GET_START_FLAG(TsPacket)  ((TsPacket[1]&0x40) ? 1 : 0)
#define TS_HEADER_GET_COUNTER(TsPacket)   (TsPacket[3]&0x0f)
#define TS_HEADER_GET_ERROR(TsPacket) ((TsPacket[1]&0x80) ? 1 : 0)



extern UTIS32 UTIPhyInit( void );
extern UTIS32 ReadAVData(UTIS32 dev,UTIU8 *pdata,unsigned long len);


#define PROG_PMT_PID 	1024
//1024
#define PROG_VIDEO_PID 	160
//160
#define PROG_AUDIO_PID 	80
//80
#define PROG_FREQ	443000000

#define PROGRAM_NUMBER 5
static UTIS32 iCurProgramIndex = 0;
static UTIU16 service_id[PROGRAM_NUMBER] = {2401, 0x0002, 0x0003, 0x0004, 0x0005};
static UTIU16 ts_id = 1024;
static UTIU16 pmt_pid[PROGRAM_NUMBER] = {PROG_PMT_PID, 0x102, 0x103, 0x104, 0x105};

static UTI_TUNER_PARAM tuner_parms;

static UTIS32 iModuleIndex = -1;

//UTIU8 buf[BUF_LEN];
//time_t g_time = 0 ;

int g_print_debug_data_message=0;

int g_main_b_runing = 1;

static unsigned int	g_frequency = 0;
static unsigned int	g_symbol_rate = 0;
static unsigned short	g_net_id = 0;
static unsigned short	g_org_net_id = 0;
static unsigned short	g_server_id = 0;
static unsigned short	g_ts_id = 0;
static unsigned short	g_pcr_pid = 0;
static unsigned short	g_mpe_pid = 0;
static unsigned short	g_pmt_pid = 0;
static unsigned short	g_video_pid = 0;
static unsigned short	g_audio_pid = 0;
static	int	g_fifo_fd = -1;

pthread_t g_play_thid;
static pthread_mutex_t	g_mutex;//操作本页数据的锁
static pthread_mutexattr_t	g_mutex_attr;

#define		BUF_SIZE		(10 * 1024*188 + 1)

int		g_r_off = 0;
int		g_w_off = 0;
char	g_buf[BUF_SIZE];

int		read_buf_data(char *data, int len)
{
	if (len <= 0 || len >= BUF_SIZE - 1)
	{
		return 0;
	}

	int r_off = g_r_off;
	int w_off = g_w_off;

	int data_len = 0;
	int move_len = 0;
	int data_off = 0;

	if (r_off == w_off)
	{
		data_len = 0;
		return 0;
	}
	else if (r_off < w_off)
	{
		data_len = w_off - r_off;
	}
	else
	{
		data_len = BUF_SIZE - r_off + w_off;
	}

	if (len > data_len)
	{
		return 0;
	}

	if (r_off + len > BUF_SIZE)
	{
		move_len = BUF_SIZE - r_off;
		memcpy(data + data_off, g_buf + r_off, move_len);
		data_off += move_len;
		r_off = 0;
	}

	memcpy(data + data_off, g_buf + r_off, len - data_off);
	r_off += len - data_off;

	g_r_off = r_off;

	return len;
}


int		write_buf_data(char *data, int len)
{
	if (len <= 0 || len >= BUF_SIZE - 1)
	{
		return 0;
	}

	int r_off = g_r_off;
	int w_off = g_w_off;

	int free_data_len = 0;

	int move_len = 0;
	int data_off = 0;

	if (r_off == w_off)
	{
		free_data_len = BUF_SIZE - 1;
	}
	else if (r_off < w_off)
	{
		free_data_len = BUF_SIZE - w_off + r_off - 1;
	}
	else
	{
		free_data_len = r_off - w_off - 1;
	}

	if (len > free_data_len)
	{
		//log_printf("%s len:%d free_data_size%d \n", __FUNCTION__,  len, free_data_len);
		return 0;
	}

	if (w_off + len > BUF_SIZE)
	{
		move_len = BUF_SIZE - w_off;
		memcpy(g_buf + w_off, data + data_off, move_len);
		data_off += move_len;
		w_off = 0;
	}

	memcpy(g_buf + w_off, data + data_off, len - data_off);
	w_off += len - data_off;

	g_w_off = w_off;

	return len;
}

void *ipc_thread_play(void *args)
{
	char buf[30 * 188];
	int len = 0;
	int write_len = 0;

	struct timeval last_tv;
	struct timeval tv;
	int data_size = 0;
	gettimeofday(&last_tv, NULL);
	gettimeofday(&tv, NULL);

	while(1 == g_main_b_runing)
	{
		len = read_buf_data(buf, sizeof(buf));
		if (len <= 0)
		{
			usleep(1000);
			continue;
		}

		//write_len = data_socket_send(buf, len);
		write_len = write(g_fifo_fd, buf, (size_t)len);
		if (write_len != len)
		{
			log_printf("%s line:%d write_len:%d len:%d errno:%d send faile\n", __FUNCTION__, __LINE__, write_len, len, errno);
			//pthread_mutex_lock(&g_mutex);
			g_main_b_runing = 0;
			//pthread_mutex_unlock(&g_mutex);
            //usleep(10000);
		}
/*
		data_size += len;
		gettimeofday(&tv, NULL);
		if (tv.tv_sec >= last_tv.tv_sec + 5)
		{
			last_tv = tv;
			log_printf("%s %dM  \t%dk per sec.\n", __FUNCTION__, data_size / (1024 * 1024 * 5), data_size / (1024 * 5));
			data_size = 0;
		}
 */
	}

	return NULL;
}

void on_packet_arrive(struct PipeIpcPacket *p_packet)
{
	if (NULL == p_packet)
	{
		return;
	}

	if (CHILD_EXIT_COMMAND == p_packet->m_command)
	{
		log_printf("%s packet cmd:%d\n", __FUNCTION__, p_packet->m_command);
		g_main_b_runing = 0;
		return;
	}
}

//modify by lzj 20080930
#define TS_HEADER_GET_ADAPTATION_FIELD_CONTROL(TsPacket)  ((TsPacket[3]&0x30)>>4)


int main(int argc, char *argv[])//澧炲姞瀛愯繘绋嬫帶鍒朵唬鐮侊紝鏉庣敳鏂�
{
	//kill_self_process_image();
    sleep(3);
	int read_fd = -1;
	int write_fd = -1;
	int use_pipe = -1;

	pthread_mutexattr_settype(&g_mutex_attr, PTHREAD_MUTEX_RECURSIVE_NP);
	pthread_mutex_init(&g_mutex, &g_mutex_attr);

	log_printf("argc:%d\n", argc);

	g_frequency = 291000000;//371000000;//PROG_FREQ;
	g_symbol_rate = 6875000;//6875000;
	g_org_net_id = 5000;
	g_net_id = 5000;
	g_ts_id = 1015;//1024;
	g_server_id =1503;//2401;
	g_pcr_pid = 0;//1873;//0
	g_mpe_pid = 0;
	g_pmt_pid = 1026;//PROG_PMT_PID;// 	1024
	g_video_pid = 162;//PROG_VIDEO_PID;//160
	g_audio_pid = 88;//PROG_AUDIO_PID;//80

	unsigned char v_key = 0;
	unsigned char a_key = 0;


	UTIS32 iRet;

	struct timeval last_tv;
	struct timeval tv;
	int data_size = 0;
	gettimeofday(&last_tv, NULL);
	gettimeofday(&tv, NULL);
	int len = 0;

	//char	buf[1000 * 188];
	char	buf[300 * 188];
	int index = 1;

	struct PipeIpcPacket packet;

	index = 1;
	while(index < argc - 1)
	{
		if (0 == strcasecmp(argv[index], "-freqency"))
		{
			g_frequency = atoi(argv[++index]);
		}
		else if(0 == strcasecmp(argv[index], "-symboRate"))
		{
			g_symbol_rate = atoi(argv[++index]);
		}
		else if(0 == strcasecmp(argv[index], "-org_net_id"))
		{
			g_org_net_id = atoi(argv[++index]);
		}
		else if(0 == strcasecmp(argv[index], "-net_id"))
		{
			g_net_id = atoi(argv[++index]);
		}
		else if(0 == strcasecmp(argv[index], "-modulation"))
		{
			printf("-modulation:%d\n", atoi(argv[++index]));

		}
		else if(0 == strcasecmp(argv[index], "-pmtPid"))
		{
			g_pmt_pid = atoi(argv[++index]);
		}
		else if(0 == strcasecmp(argv[index], "-videoPid"))
		{
			g_video_pid = atoi(argv[++index]);
		}
		else if(0 == strcasecmp(argv[index], "-audioPid"))
		{
			g_audio_pid = atoi(argv[++index]);
		}
		else if(0 == strcasecmp(argv[index], "-pcrPid"))
		{
			g_pcr_pid = atoi(argv[++index]);
		}
		else if(0 == strcasecmp(argv[index], "-serviceId"))
		{
			g_server_id = atoi(argv[++index]);
		}

		else if(0 == strcasecmp(argv[index], "-tsId"))
		{
			g_ts_id = atoi(argv[++index]);
		}

		else if(0 == strcasecmp(argv[index], "-read_fd"))
		{
			read_fd = atoi(argv[++index]);
		}
		else if(0 == strcasecmp(argv[index], "-write_fd"))
		{
			write_fd = atoi(argv[++index]);
		}

		++index;
	}

	log_printf("freqency:%d, modulation:%d, symboRate:%d, org_net_id:%d, net_id:%d, pmtPid:%d, videoPid:%d, audioPid:%d, pcrPid:%d, serviceId:%d, tsId:%d"
			,g_frequency, 0, g_symbol_rate, g_org_net_id, g_net_id, g_pmt_pid, g_video_pid, g_audio_pid, g_pcr_pid, g_server_id, g_ts_id);

	if (read_fd > 0 && write_fd > 0)
	{
		use_pipe = 0;
        log_printf("child init\n");
		child_init(read_fd, write_fd, use_pipe);
	}

    log_printf("start init UTI\n");
	UTIPhyInit();

	UTIProtocolInit();

	//create_send_socket();
    log_printf("open ts fifo\n");
	g_fifo_fd = open("/data/test/ts_fifo", O_WRONLY);
	if (g_fifo_fd < 0)
	{
		log_printf("%s %d\n", __FUNCTION__, __LINE__);
		goto out;
	}
    log_printf("open ts fifo OK\n");

//	if (create_send_socket() <= 0)
//	{
//		printf("%s line:%d\n", __FUNCTION__, __LINE__);
//		goto out;
//	}

    index = 0;
	while(iModuleIndex < 0 && 1 == g_main_b_runing)
	{
		child_run();
        if(index++ % 100 == 0)
		    log_printf("No card!\n");
		usleep(10000);
//
//		iRet = UTITunerQueryStatus(iModuleIndex);/*锟斤拷询锟斤拷频状态*/
//		printf("QueryTunerStatus  line:%d, iRet = %d\n", __LINE__, iRet);
//		if (iRet == 0)
//		{
//			break;
//		}
//		usleep(1000);
	}
	if (1 != g_main_b_runing || iModuleIndex < 0)
	{
		goto out;
	}

	tuner_parms.type = Tuner_DVB_C;
	tuner_parms.parms.dvb_c.Frequency = g_frequency;//PROG_FREQ;
	tuner_parms.parms.dvb_c.Modulation = 3;
	tuner_parms.parms.dvb_c.Symbol_rate = g_symbol_rate;//6875000;

	iRet = UTITunerSet(iModuleIndex, 0, tuner_parms);/*锟斤拷频*/
	log_printf("SetDVBCTuner  iRet = %d\n",iRet);
//	while(1 == g_main_b_runing)
//	{
//		child_run();
//		iRet = UTITunerQueryStatus(iModuleIndex);/*锟斤拷询锟斤拷频状态*/
//		printf("QueryTunerStatus  line:%d, iRet = %d\n", __LINE__, iRet);
//		if (iRet == 0)
//		{
//			break;
//		}
//		usleep(1000);
//	}
//	if (1 != g_main_b_runing)
//	{
//		goto out;
//	}

	iRet = UTICASetPmtPID(iModuleIndex, g_server_id, g_ts_id, g_net_id, g_org_net_id, g_pmt_pid);
	log_printf("UTICASetPmtPID  line:%d, iRet = %d\n", __LINE__, iRet);

	iRet = UTICASetFixCW(iModuleIndex, g_video_pid, g_audio_pid, &v_key, &a_key);
	log_printf("UTICASetFixCW  line:%d, iRet = %d\n", __LINE__, iRet);
//	while(1 == g_main_b_runing)
//	{
//		child_run();
//		iRet = UTITunerQueryStatus(iModuleIndex);/*锟斤拷询锟斤拷频状态*/
//		printf("QueryTunerStatus  line:%d, iRet = %d\n", __LINE__, iRet);
//		if (iRet == 0)
//		{
//			break;
//		}
//		usleep(1000);
//	}
//	if (1 != g_main_b_runing)
//	{
//		goto out;
//	}
    g_play_thid = 0;

	//pthread_create(&g_play_thid, NULL, ipc_thread_play, NULL);

	log_printf("start to read data\n");
    ClearAVBuffer(0);
	while(1 == g_main_b_runing)
	{   
        int write_len;

		child_run();
		len = ReadAVData(0, buf, sizeof(buf));
		if(len == 0)
		{
			//log_printf("len:%d readbuf:%d\n", len, sizeof(buf));
			usleep(1000);
			continue;
		}

		if(len < 0)
		{
			log_printf("my read uti failed%d\n", len);

			usleep(1000);
			continue;
		}
        write_len = write(g_fifo_fd, buf, (size_t)len);
		if (write_len != len) {
            log_printf("write fifo err\n");
            g_main_b_runing = 0;
        }

		//data_socket_send(buf, len);
		//if(!write_buf_data(buf, len))
		//{
			//log_printf("%s write failed\n", __FUNCTION__);
		//}

/*
		data_size += len;

		gettimeofday(&tv, NULL);
		if (tv.tv_sec >= last_tv.tv_sec + 5)
		{
			last_tv = tv;
			log_printf("%s %dM  \t%dk per sec.\n", __FUNCTION__, data_size / (1024 * 1024 * 5), data_size / (1024 * 5));
			data_size = 0;
		}
*/
	}

	    //if(g_play_thid != 0)
        //    pthread_join(g_play_thid, NULL);

out:
    log_printf("exit recv\n");
    ClearAVBuffer(0);

	packet.m_command = CHILD_EXIT_COMMAND;
	send_packet(&packet);

	if (g_fifo_fd >= 0)
	{
		close(g_fifo_fd);
	}
	if (read_fd >= 0)
	{
		close(read_fd);
	}
	if (write_fd >= 0)
	{
		close(write_fd);
	}
	
	pthread_mutex_destroy(&g_mutex);
	return 0;
}

void UTIRegisterDev( int devIndex)
{
	printf("UTIRegisterDev devIntex:%d\n",devIndex);
	pthread_mutex_lock(&g_mutex);
	iModuleIndex = devIndex;
	pthread_mutex_unlock(&g_mutex);
}

void UTIUnRegisterDev(int devIndex)
{
	printf("UTIUnRegisterDev devIntex:%d\n",devIndex);
	pthread_mutex_lock(&g_mutex);
	iModuleIndex = -1;
	pthread_mutex_unlock(&g_mutex);
}


UTIU8* u_compress(UTIU8 compfun,UTIU8 *input, UTIU32 inlen,UTIU8 *output,UTIU32 maxsize, UTIU32 *outlen)
{
	return 0;
}
UTIU8* u_decompress(UTIU8 *input, UTIU32 inlen,UTIU8 *output,UTIU32 maxsize, UTIU32 *outlen)
{
	return 0;
}


#endif
