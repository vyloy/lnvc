#ifndef _HOST_SCCLIENT_H_
#define _HOST_SCCLIENT_H_


/*�����򿨶˷���*/
UTIS32 UTISmartcardCmd(UTIS32 Module_Index, UTIU8 cmd);
UTIS32 UTISmartcardSend(UTIS32 Module_Index, UTIU8 *pData, UTIU32 iLen);

/*���˷��͸�������*/
void UTISmartcardReply(UTIS32 Module_Index, UTIU8 *pData, UTIU32 iLen);
void UTISmartcardRcv(UTIS32 Module_Index, UTIU8 *pData, UTIU32 iLen);
#endif
