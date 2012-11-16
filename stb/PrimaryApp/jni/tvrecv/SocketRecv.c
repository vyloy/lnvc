#include "SocketHeader.h"
#include <errno.h>

#define LOCAL_IP	"127.0.0.1"
#define LOCAL_DATA_PORT	9000
#define LOCAL_LEN_PORT	9001
static int datafd = -1;
static int lenfd = -1;

int create_and_bind(const char *addr, int port)
{
	int err;
	int optval = 1;
	int sock=-1;
	struct sockaddr_in saddr;

	printf ("%s %d. errno:%d\n", __FUNCTION__, __LINE__, errno);

	saddr.sin_family = AF_INET;
	//err = inet_aton (addr, &saddr.sin_addr);
	if (NULL != addr)
	{
		printf ("%s %d. errno:%d\n", __FUNCTION__, __LINE__, errno);
		saddr.sin_addr.s_addr = inet_addr(addr);
	}
	else
	{
		printf ("%s %d. errno:%d\n", __FUNCTION__, __LINE__, errno);
		saddr.sin_addr.s_addr = htonl(INADDR_ANY);
	}
	printf ("%s %d. errno:%d\n", __FUNCTION__, __LINE__, errno);

	if (err < 0)
	{
		//printf ("Error in socket address:%s.", getSocketError());
		printf ("%s %d. errno:%d\n", __FUNCTION__, __LINE__, errno);
		return -1;
	}
	saddr.sin_port = htons (port);

	printf ("%s %d. errno:%d\n", __FUNCTION__, __LINE__, errno);

	sock = socket (PF_INET, SOCK_DGRAM, 0);
	if (sock==-1)
	{
		printf ("%s %d. errno:%d\n", __FUNCTION__, __LINE__, errno);
		return -1;
	}

	printf ("%s %d. errno:%d\n", __FUNCTION__, __LINE__, errno);
	if (1)
	{
		err = setsockopt (sock, SOL_SOCKET, SO_REUSEADDR, &optval, sizeof (optval));
		if (err < 0)
		{
			printf ("%s %d. errno:%d\n", __FUNCTION__, __LINE__, errno);
			//ortp_warning ("Fail to set rtp address reusable: %s.",getSocketError());
		}
	}

	err = bind(sock, (struct sockaddr*) &saddr, sizeof (saddr));

	if (err != 0)
	{
		printf ("%s %d. errno:%d\n", __FUNCTION__, __LINE__, errno);
		//ortp_warning ("Fail to bind rtp socket to port %i: %s.", port, getSocketError());
		close (sock);
		return -1;
	}

	if (sock!=-1)
	{
		//set_non_blocking_socket (sock);
		printf ("%s %d. errno:%d\n", __FUNCTION__, __LINE__, errno);
	}

	return sock;
}

int create_recv_socket(void)
{
	datafd = create_and_bind(LOCAL_IP, LOCAL_DATA_PORT);
	if(datafd < 0)
	{
		return datafd;
	}

	lenfd = create_and_bind(LOCAL_IP, LOCAL_LEN_PORT);
	if(lenfd < 0)
	{
		return lenfd;
	}

	fcntl (datafd, F_SETFL, O_NONBLOCK);
	fcntl (lenfd, F_SETFL, O_NONBLOCK);
	return 1;
}

ssize_t data_socket_recv(char* buf, int size) {
	struct sockaddr fromaddr;
	socklen_t fromlen;

	return recvfrom(datafd, (void*)buf, size, 0, &fromaddr, &fromlen);
}

ssize_t len_socket_recv(char* buf, int size) {
	struct sockaddr fromaddr;
	socklen_t fromlen;

	return recvfrom(lenfd, (void*)buf, size, 0, &fromaddr, &fromlen);
}
