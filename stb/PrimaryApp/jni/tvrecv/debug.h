#ifndef _DEBUG_H_
#define _DEBUG_H_

//#include <stdio.h>
//#include <stdarg.h>
#include "HostPlatForm.h"

#define  MFC 1
/*主机协议调试信息*/
//UTIS32 UTIPrint(UTIU32 level, const UTIS8 * format, ...);

UTIS32 UTIPrint(const UTIS8 * format, ...);
/*UTI卡传过来的调试信息*/
UTIS32 UTIDebugModuleInfo(UTIS32 iModuleIndex, UTIU32 level, UTIU32 length, UTIU8 *data);

#define UTI_DBG1(x) //(format, ...)
#define UTI_DBG2(x) //(format, ...)
#define UTI_DBG3(x) //(format, ...)
#define UTI_DBG4(x) //(format, ...)

#define UTI_DEBUG_LEVEL 4

//#define UTI_DBG(format, ...) UTIPrint(1, format, ##__VA_ARGS__)
#define UTI_DBG UTIPrint
//#define  UTI_DBG()

#if UTI_DEBUG_LEVEL > 0
	#undef UTI_DBG1
	#define UTI_DBG1(x) UTI_DBG x
#endif


 #if UTI_DEBUG_LEVEL > 1
 	#undef UTI_DBG2
 	#define UTI_DBG2(x) UTI_DBG x
 #endif
 
 
 #if UTI_DEBUG_LEVEL > 2
 	#undef UTI_DBG3
 	#define UTI_DBG3(x) UTI_DBG x
 #endif
 
 
 #if UTI_DEBUG_LEVEL > 3
 	#undef UTI_DBG4
 	#define UTI_DBG4(x) UTI_DBG x
 #endif



#define  PRINTF     UTIPrint

#endif
