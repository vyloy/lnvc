// HostAppInfoClient.h: interface for the CHostAppInfoClient class.
//
//////////////////////////////////////////////////////////////////////

#ifndef _HOSTAPPINFPCLIENT_H_
#define _HOSTAPPINFPCLIENT_H_

#include "HostPlatForm.h"


/*UTI 协议实现的API*/
UTIS32 UTIQueryAppInfo(UTIS32 Module_Index);

/*主机实现的API*/
void UTIGetAppInfoCallback(UTIS32 Module_Index, UTIU16 app_type, UTIU16 app_manu, UTIU16 manu_id, UTIS8 *app_name, UTIU8 name_len);

#endif // !defined(AFX_HOSTAPPINFOCLIENT_H__E22BBC21_5C56_40E7_9A87_2AB1E6C78D03__INCLUDED_)
