#define OS_LINUX 1
#define OS_WINDOWS 0

#define UTI_NO_ERROR
#define UTI_NOT_OK
#define UTI_BAD_PARAMATERS

#ifndef NULL
#define NULL ((void *)0)
#endif

#if OS_WINDOWS
#include <stdio.h>
#include <stdarg.h>
#include <string.h>
#include <stdlib.h>
#include <string.h>
#include <wtypes.h>
#include <winbase.h>
#include <time.h>
#endif

#if OS_LINUX
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>
#include <sys/ioctl.h>
#include <string.h>
#include <stdarg.h>
#include <time.h>
#include <sys/time.h>
#endif

#include "HostUTIAPI.h"



