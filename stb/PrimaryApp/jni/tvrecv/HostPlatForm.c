#include "HostIncludeApi.h"

void *UTIMalloc(UTIU32 len)
{
#if (OS_LINUX||OS_OS20||OS_WINDOWS)
	void *pp= NULL;
	if(len == 0)
		return NULL;
	pp = malloc(len);
	//printf("malloc[%#x]\n", pp);
	if(pp == NULL)
	{
		printf("UTIMalloc error!!!\n");
	}
	//UTI_DBG1(("UTIMalloc 0x%x---%d\n",pp,len));
	return pp;//malloc(len);
#endif
}

void UTIFree(void *ptr)
{
#if (OS_LINUX||OS_OS20||OS_WINDOWS)
	if(ptr)
	{
		free(ptr);
		//printf("free[%#x]\n", ptr);
	}
#endif
}

void *UTICreateMutex(void)
{
#if OS_OS20
	((void *)semaphore_create_fifo(1));
#elif OS_WINDOWS
     return ((void *)CreateMutex(NULL,FALSE,NULL));
#else 
	return NULL;
#endif
}

void UTIFreeMutex(void *mutex_handler)
{
#if OS_OS20
	semaphore_delete((semaphore_t *)mutex_handler);
#elif OS_WINDOWS
	if(mutex_handler)
	{
		if(CloseHandle(mutex_handler))
		{
			return UTI_NO_ERROR;
		}
		else return UTI_NOT_OK;
	}

	return UTI_BAD_PARAMATERS;
#else
	return;
#endif
}

void UTIKeepMutex(void *mutex_handler)
{
#if OS_OS20
	semaphore_wait((semaphore_t *)mutex_handler);
#elif OS_WINDOWS
	if(!mutex_handler)
	{
		return UTI_BAD_PARAMATERS;
	}
	WaitForSingleObject(mutex_handler,INFINITE);
	return UTI_NO_ERROR;
#else
	return;
#endif
}

void UTIReleaseMutex(void *mutex_handler)
{
#if OS_OS20
	semaphore_signal((semaphore_t *)mutex_handler);
#elif OS_WINDOWS
	if(!mutex_handler)
	{
		return UTI_BAD_PARAMATERS;
	}
	if(ReleaseMutex(mutex_handler))
	{

		return UTI_NO_ERROR;
	}

	return UTI_NOT_OK;
#else
	return;
#endif
}

//typedef UTIU32 (__stdcall * pThread)(void *);

typedef void (*PVOIDFUN)(void *param);

typedef struct threadparam_s
{
	void (*pfun)(void *param);
	void * param;
}THREADPARAM;

unsigned long ThreadProc(void * lpParameter)
{
	THREADPARAM *pThreadparam;
	
	pThreadparam = (THREADPARAM *)lpParameter;
	pThreadparam->pfun(pThreadparam->param);

	return 0;
}

UTIU32 UTICreateTask(void *ppFunc, UTIU32 *pID, UTIU32 priority, UTIU32 stack_size)
{
#if OS_LINUX
	UTIS32 retcode;
	pthread_t threadid;

	pthread_attr_t threadAttr;
	struct sched_param param;

	pthread_attr_init(&threadAttr);

	pthread_attr_setstacksize(&threadAttr,stack_size);

	pthread_attr_setschedpolicy(&threadAttr,SCHED_RR); 
	pthread_attr_getschedparam(&threadAttr,&param);
	param.sched_priority = priority;
	pthread_attr_setschedparam(&threadAttr,&param);

	retcode=pthread_create(&threadid,&threadAttr,(void *)ppFunc,NULL);
	if(retcode != 0)
	{
		printf("Can't Create the Thread!\n");
	}
	*pID = (UTIU32)threadid;
	return retcode;
#elif OS_OS20
	{	
    //typedef UTIU32 (__stdcall * pThread)(void *);(pThread)	
	return (UTIU32)task_create((void (*)(void *))/*(ptemp)*/ppFunc, NULL, stack_size, priority, "UTI_test", 0);
	//return (UTIU32)CreateThread(NULL,stack_size,(pThread)ppFunc,0,0,pID);
	}
#elif OS_WINDOWS
	THREADPARAM  *pstThread;
	pstThread = (THREADPARAM*)malloc(sizeof(THREADPARAM));
	pstThread->param = 0;
	pstThread->pfun = (PVOIDFUN)ppFunc;
	PRINTF("###CreateThread\r\n");
	return (UTIU32)CreateThread(NULL, stack_size, (PTHREAD_START_ROUTINE)ThreadProc,(LPVOID)pstThread, 0, 0);
	//return (HTHREAD)pstThread;
#endif
}

void UTISleep(UTIU32 ms)
{
#if OS_LINUX
	usleep(1000*ms);
#elif OS_OS20
	task_delay(ms*ST_GetClocksPerSecondLow()/1000);
#elif OS_WINDOWS
    Sleep(ms);
#endif
}

UTIU8 *UTIMemCopy(UTIU8* dest, UTIU8* src, UTIS32 len)
{
	return memcpy(dest, src, len);
}

UTIU8* UTIMemSet(UTIU8* ptr, UTIS32 val, UTIS32 len)
{
	return memset(ptr, val, len);
}

UTIS32 UTIMemCmp(const void *ptr1, const void *ptr2, UTIS32 len)
{
	return memcmp(ptr1, ptr2, len);
}

 UTIS32 UTIOSGetTime(UTIU32 *ms)
{
#if OS_WINDOWS
	//*ms = time_now()*1000/WAIT_FOR_1_SEC;
	*ms = (clock()*1000/CLOCKS_PER_SEC);
#elif OS_LINUX
	struct timeval timeCur;
	gettimeofday(&timeCur,NULL);
	*ms =  1000*timeCur.tv_sec + timeCur.tv_usec/1000;
#endif

	
	return 0;
}


