#ifndef _HOST_RGNCLIENTAPI_H_
#define _HOST_RGNCLIENTAPI_H_

/*UTI ʵ�ֵĽӿ�*/
UTIS32 UTIRgnQueryConfig(UTIS32 Module_Index);
UTIS32 UTIRgnQueryChannelList(UTIS32 Module_Index);

/*����Ӧ��ʵ�ֵĽӿ�*/
void UTIRgnConfigDescriptor(UTIS32 Module_Index, UTIU8 *cBuf, UTIU32 len);
void UTIRgnChannelListDescriptor(UTIS32 Module_Index, UTIU8 *cBuf, UTIU32 len);

#endif
