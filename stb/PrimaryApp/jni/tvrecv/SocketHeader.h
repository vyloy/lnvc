#include <sys/types.h>          /* See NOTES */
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>

int create_and_bind(const char *addr, int port);

int create_recv_socket(void);
ssize_t data_socket_recv(char* buf, int size);
ssize_t len_socket_recv(char* buf, int size);

int create_send_socket(void);
ssize_t data_socket_send(const char* buf, int size);
ssize_t len_socket_send(const char* buf, int size);
