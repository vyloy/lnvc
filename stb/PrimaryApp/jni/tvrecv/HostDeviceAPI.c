#include "HostIncludeApi.h"

#if OS_WINDOWS
void*			OpenDevice();
unsigned int	WriteCtrlData(void * hFile,unsigned char * data,unsigned long len);
void			CloseDevice(void* hFile);
unsigned int	ReadCtrlData(void * hFile,unsigned char * data,unsigned long len);
unsigned int	WriteImgCtrlData(void * hFile,unsigned char * data,unsigned long len);
unsigned int	ReadImgCtrlData(void * hFile,unsigned char * data,unsigned long len);
void *			GetAVShareMemAddress(void* hFile);
void			ReleaseShareMem(void *hFile,unsigned int UserAddress);
unsigned int	ReadAVTSBuffer(void *pShareMem, unsigned char *data, unsigned int len);
#endif

#if OS_LINUX

#define UTIUSB_IOC_MAGIC 'k'
#define UTIUSB_IO_READ _IO(UTIUSB_IOC_MAGIC,1)
#define UTIUSB_IO_WRITE _IO(UTIUSB_IOC_MAGIC,2)
#define UTIUSB_CTRL_REQUEST _IO(UTIUSB_IOC_MAGIC,3)
#define UTIUSB_CTRL_READ_PSIDATA _IO(UTIUSB_IOC_MAGIC,4)
#define UTIUSB_CTRL_SET_PIDS _IO(UTIUSB_IOC_MAGIC,5)
#define UTIUSB_CTRL_SET_INT_DATA_LEN _IO(UTIUSB_IOC_MAGIC,6)
//#define UTIUSB_CTRL_SENDCTRLCMD _IO(UTIUSB_IOC_MAGIC,7)
#define UTIUSB_CHECK_STATUS _IO(UTIUSB_IOC_MAGIC,7)
#define UTIUSB_CTRL_REQUEST_RESET _IO(UTIUSB_IOC_MAGIC,8)

#define UTIUSB_EP_READ _IO(UTIUSB_IOC_MAGIC, 9)
#define UTIUSB_EP_WRITE _IO(UTIUSB_IOC_MAGIC, 10)
#define UTIUSB_CLEAR_AVBUF _IO(UTIUSB_IOC_MAGIC,11)



#define MAX_MODULE_NUM 5
typedef struct _usbrw_params_
{
	UTIU8 EPNum;
	UTIU8*data;
	UTIU32 len;
}USBRW_PARAM;

static UTIS32 usb_fd[MAX_MODULE_NUM];

#endif


unsigned int g_UtiWritePacketNum=0;
unsigned int g_UtiReadPacketNum=0;
unsigned int g_UtiWriteStop=0;
unsigned int g_UtiReadStop=0;
extern int g_print_debug_data_message;

UTIS32 UTIPhyInit( void )
{
#if OS_LINUX
	int i;

	for(i=0;i<MAX_MODULE_NUM;i++)
	{
		usb_fd[i] = -1;
	}
#endif
	return 0;
}

UTIS32 UTIPhyWrite(UTIS32 index, UTIU8*data, UTIU32 len)
{
	UTIS32 iRet = 0;
	int i;
#if OS_LINUX
	if( usb_fd[index] == -1 )
	{
		printf("UTIPhyWrite(): Device has not been opened.\n");
		return 0;
	}
  
	do{
		ioctl(usb_fd[index],UTIUSB_CTRL_SET_INT_DATA_LEN,&len);
		iRet = ioctl(usb_fd[index],UTIUSB_IO_WRITE,data);
		if(g_print_debug_data_message){
			printf("write len:%02x ret=%d",len, iRet);
			if(iRet>0){
				printf("write iRet:%02x\n",iRet);
				for(i=0;i<len;i++){
					if(i%16==0) printf("\n");
					printf("%02x ",data[i]);
				}printf("\n");
			}
		}
	}while(0);
#elif OS_WINDOWS
	void * hfile;

	while (g_UtiWriteStop)
	{
		Sleep(200);
	}
	
	hfile = OpenDevice();
	if(hfile==0)
		return 0;
	iRet=WriteCtrlData(hfile,data,len);
	CloseDevice(hfile); 
	if (iRet>0)
	{
		g_UtiWritePacketNum++;
	}
	
#else
	iRet = usb_write(data,len);

#endif

	return iRet;
}


/*return: if read  sucessful, return len;
if connect error, return -1;
else return 0, for example, busy.*/
UTIS32 UTIPhyRead(UTIS32 index, UTIU8*data, UTIU32 len)
{
	UTIS32 iRet = 0;
	int i;
#if OS_LINUX
	if( usb_fd[index] == -1 )
	{
		printf("UTIPhyRead(): Device has not been opened.\n");
		return 0;
	}

	do{
		ioctl(usb_fd[index],UTIUSB_CTRL_SET_INT_DATA_LEN,&len);
		iRet = ioctl(usb_fd[index],UTIUSB_IO_READ,data);
		if(g_print_debug_data_message){
			printf("read iRet:%02x\n",iRet);
			if(iRet > 0){
				printf("read len:%02x",len);
				for(i=0;i<len;i++){
					if(i%16==0) printf("\n");
					printf("%02x ",data[i]);
				}printf("\n\n");		
			}
		}
	}while(0);
#elif OS_WINDOWS
	void * hfile;

	while (g_UtiReadStop)
	{
		Sleep(200);
	}

	hfile = OpenDevice();
	if(hfile==0)
		return 0;
	iRet=ReadCtrlData(hfile,data,len);
	CloseDevice(hfile);
	if (iRet>0)
	{
		g_UtiReadPacketNum++;
	}
#else
	iRet = usb_read(data,len);
#endif

	return iRet;
}

/*
EPNum: endpoint number.
return: if write sucessful, return len;
if connect error, return -1;
else return 0, for example, busy.*/
UTIS32 UTIPhyWriteEx(UTIS32 index, UTIU8 EPNum, UTIU8*data, UTIU32 len)
{
	UTIS32 iRet = 0;
	
#if OS_LINUX
	USBRW_PARAM params;

	if( usb_fd[index] == -1 )
	{
		printf("UTIPhyWrite(): Device has not been opened.\n");
		return 0;
	}

	params.EPNum = EPNum;
	params.data = data;
	params.len = len;
	do{
		iRet = ioctl(usb_fd[index], UTIUSB_EP_WRITE, &params);
	}while(0);
#elif OS_WINDOWS
	void * hfile;
    hfile = OpenDevice();
	if(hfile==0)
		return 0;
    iRet=WriteImgCtrlData(hfile,data,len);
    CloseDevice(hfile);
#else
	iRet = usb_write(EPNum, data,len);
#endif
	//if(iRet<0)
		//iRet = 0;

	return iRet;
}

/*
EPNum: endpoint number.
return: if read  sucessful, return len;
if connect error, return -1;
else return 0, for example, busy.*/
UTIS32 UTIPhyReadEx(UTIS32 index, UTIU8 EPNum, UTIU8*data, UTIU32 len)
{
	UTIS32 iRet = 0;
#if OS_LINUX
	USBRW_PARAM params;

	if( usb_fd[index] == -1 )
	{
		printf("UTIPhyRead(): Device has not been opened.\n");
		return 0;
	}

	params.EPNum = EPNum;
	params.data = data;
	params.len = len;
	do{
		iRet = ioctl(usb_fd[index], UTIUSB_EP_READ, &params);
	}while(0);
#elif OS_WINDOWS
	void * hfile;
    hfile = OpenDevice();
	if(hfile==0)
		return 0;
    iRet=ReadImgCtrlData(hfile,data,len);
    CloseDevice(hfile);
#else
	iRet = usb_read(EPNum, data,len);
#endif
	//if(iRet<0)
		//iRet = 0;

	return iRet;
}


/*Return 0 ok, else failed*/
UTIS32 UTIPhyOpen(UTIS32 index)
{
	if(index>0)
		return -1;
#if OS_LINUX
	if( usb_fd[index] != -1 )
		return 0;

	usb_fd[index] = open("/dev/utiusb", O_RDWR | O_TRUNC);
	if( usb_fd[index] == -1 )
	{
		printf("UTIPhyOpen(): Open usb[%d] device failed.\n",index);
		return -1;
	}
	printf("UTI: Open usb[%d] device[%d] successed. \n", index, usb_fd[index]);
	return usb_fd[index];
#elif OS_WINDOWS
    //OpenDevice();
#endif	
	return 0;
}

/*Return 0 ok, else failed*/
UTIS32 UTIPhyClose(UTIS32 index)
{
	UTIS32 iRet = 0;
#if OS_LINUX
  if( usb_fd[index] == -1 )
     return -1;
 
  iRet = close(usb_fd[index]);
  if( iRet == -1 )
     return -1;
  printf("UTIPhyClose:%d\n",usb_fd[index]);
  usb_fd[index] = -1;// for push card, tian
#elif OS_WINDOWS
  //CloseDevice(dev);
#endif	                                                                                
  return iRet;
}

UTIS32 UTIPhyCheckStatus(UTIS32 index)
{
	UTIS32  iFlag = 0,iRet;

#if OS_LINUX
	if( usb_fd[index] == -1 )
	{
		UTIPhyOpen(index);	//for push card,tian
		return -1;
	}
	iRet = ioctl(usb_fd[index], UTIUSB_CHECK_STATUS, &iFlag);
	//printf("CheckUSBDevice(): iRet[%d], iFlag[%d]\n", iRet, iFlag);
	return iRet;
#elif OS_WINDOWS
//	iFlag = (UTIU32)OpenDevice();
	static int checktime = 0;
	void * hfile;

	//if (checktime==0) {
		//checktime = 1;
		//return 0;
//	}
    hfile = OpenDevice();
	if(hfile==0){
		iFlag = 0;
	}else{
		iFlag = 1;
	}
    CloseDevice(hfile);
//	PRINTF("check:%d index:%d\n",iFlag,index);

	return iFlag;
#endif
}

/*
* ReadUSBTSPackets
* Read tansport stream packets from UTI/USB driver
*/
unsigned int ReadUSBTSPackets(UTIU8 *data, UTIU32 len)
{
	UTIU32 iRet=0;
#if OS_WINDOWS
	void *hfile;
	void *pShareMem;	

    hfile = OpenDevice();
	if(hfile==0)
		return 0;
	pShareMem = GetAVShareMemAddress(hfile);
    iRet = ReadAVTSBuffer(pShareMem, data, len);
	ReleaseShareMem(hfile, (unsigned int )pShareMem);
    CloseDevice(hfile);	

#endif
	return iRet;	
}

//----------------------------------------------------------------------------
//
//?��?����y?Yo����y
//
//----------------------------------------------------------------------------
#if OS_LINUX
UTIS32 ReadAVData(UTIS32 index,UTIU8 *pdata,unsigned long len)
{
	UTIS32 l;
	l = read(usb_fd[index],pdata,len);
	return l;
}

UTIS32 ClearAVBuffer(UTIS32 index)
{
	return ioctl(usb_fd[index],UTIUSB_CLEAR_AVBUF,0);
}

UTIS32 StartTsDMA(UTIS32 index)
{
	return ioctl(usb_fd[index],UTIUSB_CTRL_REQUEST,0);
}

UTIS32 UTIHardwareReset(UTIS32 index)
{
	printf("UTIHardwareReset send//////////////////////////////////////////////////////////////\n");
	return ioctl(usb_fd[index],UTIUSB_CTRL_REQUEST_RESET,0);
}

#endif

