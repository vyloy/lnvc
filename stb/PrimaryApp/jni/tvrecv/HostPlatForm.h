// HostPlatForm.h: interface for the HostPlatForm class.
//
//////////////////////////////////////////////////////////////////////

#ifndef _UTIPLATFORM_H_
#define _UTIPLATFORM_H_

//#include <stdlib.h>
//#include <stdio.h>

//#define PLATFORM_NO_OS

typedef int UTIS32;
typedef unsigned int UTIU32 ;
typedef short UTIS16 ;
typedef unsigned short UTIU16 ;
typedef char UTIS8;
typedef unsigned char UTIU8;


void *UTIMalloc(UTIU32 len);
void UTIFree(void *ptr);
void *UTICreateMutex(void);
void UTIFreeMutex(void *mux_handler);
void UTIKeepMutex(void *mux_handler);
void UTIReleaseMutex(void *mux_handler);
UTIU32 UTICreateTask(void *ppFunc, UTIU32 *pID, UTIU32 priority, UTIU32 stack_size);
void UTISleep(UTIU32 ms);
UTIU8 *UTIMemCopy(UTIU8* dest, UTIU8* src, UTIS32 len);
UTIU8* UTIMemSet(UTIU8* ptr, UTIS32 val, UTIS32 len);
UTIS32 UTIMemCmp(const void *ptr1, const void *ptr2, UTIS32 len);


UTIS32 UTIOSGetTime(UTIU32 *ms);


#endif // #ifndef _UTIPLATFORM_H_

