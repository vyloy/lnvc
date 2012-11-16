#ifndef _HOST_SCCLIENT_H_
#define _HOST_SCCLIENT_H_


/*主机向卡端发送*/
UTIS32 UTISmartcardCmd(UTIS32 Module_Index, UTIU8 cmd);
UTIS32 UTISmartcardSend(UTIS32 Module_Index, UTIU8 *pData, UTIU32 iLen);

/*卡端发送给主机端*/
void UTISmartcardReply(UTIS32 Module_Index, UTIU8 *pData, UTIU32 iLen);
void UTISmartcardRcv(UTIS32 Module_Index, UTIU8 *pData, UTIU32 iLen);
#endif
