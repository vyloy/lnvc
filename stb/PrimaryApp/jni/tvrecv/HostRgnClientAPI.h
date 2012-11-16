#ifndef _HOST_RGNCLIENTAPI_H_
#define _HOST_RGNCLIENTAPI_H_

/*UTI 实现的接口*/
UTIS32 UTIRgnQueryConfig(UTIS32 Module_Index);
UTIS32 UTIRgnQueryChannelList(UTIS32 Module_Index);

/*主机应用实现的接口*/
void UTIRgnConfigDescriptor(UTIS32 Module_Index, UTIU8 *cBuf, UTIU32 len);
void UTIRgnChannelListDescriptor(UTIS32 Module_Index, UTIU8 *cBuf, UTIU32 len);

#endif
