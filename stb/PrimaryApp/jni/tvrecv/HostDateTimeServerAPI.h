// HostDateTimeServer.h: interface for the CHostDateTimeServer class.
//
//////////////////////////////////////////////////////////////////////

#ifndef _HOSTDATATIMESERVER_H_
#define _HOSTDATATIMESERVER_H_

#include "HostPlatForm.h"

/*UTI 协议实现的API*/
UTIS32 UTISendDateTime(UTIS32 ModuleIndex, UTIU8 *utc_time_local_offset, UTIU8 time_len);

/*主机实现的API*/
extern UTIU32 UTIMJDUTCRequest(UTIS32 ModuleIndex, UTIU8 response_interval);
#endif
