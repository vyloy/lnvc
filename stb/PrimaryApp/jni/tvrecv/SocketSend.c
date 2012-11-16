#include "SocketHeader.h"

#define LOCAL_IP	INADDR_ANY//"192.168.0.202"//"192.168.2.99"//INADDR_ANY//
#define LOCAL_DATA_PORT	8000
#define LOCAL_LEN_PORT	8001
#define REMOTE_IP	"192.168.0.31"//"192.168.0.31"//"192.168.2.100"
#define REMOTE_DATA_PORT	9000
#define REMOTE_LEN_PORT		9001
static int datafd = -1;
//static int lenfd = -1;

int create_send_socket(void){
	datafd = create_and_bind(LOCAL_IP, LOCAL_DATA_PORT);
	if(datafd < 0)
	{
		return datafd;
	}
	//lenfd = create_and_bind(LOCAL_IP, LOCAL_LEN_PORT);
	//if(lenfd < 0)
	//	return lenfd;
	return 1;
}

ssize_t data_socket_send(const char* buf, int size) {
	static struct sockaddr *destaddr = NULL;
	static struct sockaddr_in saddr;
	const char *addr = REMOTE_IP;
	int port = REMOTE_DATA_PORT;
	int err;

	if(destaddr == NULL) {
		destaddr = (struct sockaddr*)&saddr;
		saddr.sin_family = AF_INET;
		err = inet_aton (addr, &saddr.sin_addr);
		if (err < 0)
		{
			//printf ("Error in socket address:%s.", getSocketError());
			return -100;
		}
		saddr.sin_port = htons (port);
	}

	return sendto(datafd, buf, size, 0, destaddr, sizeof(struct sockaddr));
}

ssize_t len_socket_send(const char* buf, int size) {
	static struct sockaddr *destaddr = NULL;
	static struct sockaddr_in saddr;
	const char *addr = REMOTE_IP;
	int port = REMOTE_LEN_PORT;
	int err;

	if(destaddr == NULL) {
		destaddr = (struct sockaddr*)&saddr;
		saddr.sin_family = AF_INET;
		err = inet_aton (addr, &saddr.sin_addr);
		if (err < 0)
		{
			//printf ("Error in socket address:%s.", getSocketError());
			return -1;
		}
		saddr.sin_port = htons (port);
	}

	return sendto(datafd, buf, size, 0, destaddr, sizeof(struct sockaddr));
}
