#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/select.h>
#include <fcntl.h>
#include <sys/errno.h>

#include "../../tvdaemon/ipc/child_body.h"
#include "../../tvdaemon/ipc/fun_typedef.h"
#include "../../tvdaemon/ipc/manage_pid.h"

char *p_log_tag_str = "tvsend";

static int g_fifo_fd = -1;

int g_main_b_runing = 1;

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

	return;
}

int	main(int argc, char *argv[])
{
	kill_self_process_image();

	int read_fd = -1;
	int write_fd = -1;
	int use_pipe = -1;
	int index = 1;

	char buf[30 * 188];
	int len = 0;
	int read_len = 0;

	struct PipeIpcPacket packet;

	int udp_fd = -1;
	struct sockaddr_in addr;
	struct sockaddr_in data_dest_addr;
	struct sockaddr_in ctl_dest_addr;
	int addr_len = 0;
	fd_set read_fdset;

	struct timeval last_tv;
	struct timeval tv;
	int data_size = 0;

	int access_ret = 0;
	int access_mode = 0;

	while(index < argc - 1)
	{
		if(0 == strcasecmp(argv[index], "-read_fd"))
		{
			read_fd = atoi(argv[++index]);
		}
		else if(0 == strcasecmp(argv[index], "-write_fd"))
		{
			write_fd = atoi(argv[++index]);
		}

		++index;

	}

	if (read_fd > 0 && write_fd > 0)
	{
		use_pipe = 0;
		child_init(read_fd, write_fd, use_pipe);
	}

    //test
	while(1 == g_main_b_runing)
	{
		child_run();
        usleep(100000);
    }
    goto out;

	g_fifo_fd = open("/data/test/ts_fifo", O_RDONLY);
	if (g_fifo_fd < 0)
	{
		log_printf("%s %d\n", __FUNCTION__, __LINE__);
		goto out;
	}

	data_dest_addr.sin_family = AF_INET;
	if (inet_aton ("192.168.0.31", &data_dest_addr.sin_addr) < 0)
	{
		printf("%s %d\n", __FUNCTION__, __LINE__);
		goto out;
	}

	data_dest_addr.sin_port = htons (9000);
	ctl_dest_addr = data_dest_addr;
	ctl_dest_addr.sin_port = htons (9001);

	udp_fd = socket(AF_INET, SOCK_DGRAM, 0);
	if(-1 == udp_fd)
	{
		log_printf("%s %d\n", __FUNCTION__, __LINE__);
		goto out;
	}

	addr.sin_family = AF_INET;
	addr.sin_addr.s_addr = htonl(INADDR_ANY);
	addr.sin_port = htons(8000);

	addr_len = sizeof(addr);

	if (-1 == bind(udp_fd, (struct sockaddr *)&addr, addr_len))
	{
		goto out;
	}

	if((access_ret = fcntl(udp_fd, F_GETFL, 0)) >= 0)
	{
		access_mode = access_ret & (~O_NONBLOCK | 0xFFFFFFFF);
		if((access_ret = fcntl(udp_fd, F_SETFL, access_mode)) < 0)
		{
			goto out;
		}
	}
	else
	{
		goto out;
	}

	gettimeofday(&last_tv, NULL);
	gettimeofday(&tv, NULL);

	sendto(udp_fd, "s", 1, 0, (struct sockaddr*)(&ctl_dest_addr), sizeof(struct sockaddr));
	sendto(udp_fd, "p", 1, 0, (struct sockaddr*)(&ctl_dest_addr), sizeof(struct sockaddr));

	FD_ZERO(&read_fdset);

	while(1 == g_main_b_runing)
	{
		child_run();

		struct timeval sel_tv = {0, 10000};
		int sel_ret = 0;
		FD_SET(g_fifo_fd, &read_fdset);
		sel_ret = select(g_fifo_fd + 1, &read_fdset, NULL, NULL, &sel_tv);
		if (-1 == sel_ret)
		{
			ipc_printf("select error, errno:%d", errno);
			break;
		}

		if (!FD_ISSET(g_fifo_fd, &read_fdset))
		{
			continue;
		}

		len = read(g_fifo_fd, buf + read_len, sizeof(buf) - read_len);
		if (len < 0)
		{
			LOGE("%s %d\n", __FUNCTION__, __LINE__);
			break;
		}
		if (0 == len)
		{
			LOGE("%s %d\n", __FUNCTION__, __LINE__);
			continue;
		}

		read_len += len;

		if (read_len < sizeof(buf))
		{
			continue;
		}

		data_size += read_len;
		gettimeofday(&tv, NULL);
		if (tv.tv_sec >= last_tv.tv_sec + 5)
		{
			last_tv = tv;
			log_printf("%dM  \t%dk per sec.\n", data_size / (1024 * 1024 * 5), data_size / (1024 * 5));
			data_size = 0;
		}

		len = sendto(udp_fd, buf, read_len, 0, (struct sockaddr*)(&data_dest_addr), sizeof(struct sockaddr));
		if (len != read_len)
		{
			LOGE("%s %d\n", __FUNCTION__, __LINE__);
			break;
		}
		read_len = 0;
	}

	sendto(udp_fd, "s", 1, 0, (struct sockaddr*)(&ctl_dest_addr), sizeof(struct sockaddr));

out:
	packet.m_command = CHILD_EXIT_COMMAND;
	send_packet(&packet);

	if (udp_fd >= 0)
	{
		close(udp_fd);
	}

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

	return 0;
}
