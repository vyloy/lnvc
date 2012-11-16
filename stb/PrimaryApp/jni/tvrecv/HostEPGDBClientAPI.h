#ifndef _HOSTEPGDBCLIENT_H_
#define _HOSTEPGDBCLIENT_H_

#include "HostPlatForm.h"
#include "HostTunerClientAPI.h"
#include "HostEPGDBClientAPI.h"


typedef struct AppInfo
{
	UTIU8 Name[30];
	UTIU8 AppId;  //0 CA; 1:EPG; 2:数据广播；
} APPINFO, *PAPPINFO;
/*
Name表示应用的名字。
AppId是应用的标识号，定义如下：

取值	描述
01          CA
02          EPG
03          数据
04          证券
05          NVOD
06          VOD
07          PVR
08          游戏
Other     保留
*/

typedef struct s_GraphicInfo
{
	UTIU16 Width;
	UTIU16 Height; 
	UTIU16 LineLen;
	UTIU8  BitsPerPixel;
	UTIU8  PixelFormat; 
}*PGRAPHICINFO;
/*
Width：对卡应用绘图所提供的显示能力的宽。
Height：对卡应用绘图所提供的显示能力的高。
LineLen：一行的长度。
BitsPerPixel：每个像素的位数。
PixelFormat：主机为卡的显示功能所能支持的显示模式的值。
一般主机同一时间可能只能在事先选定的一种像素格式下工作，
所以通常主机应当只提供其事先选定的像素格式，
而不要传送所有可支持支持的像素格式。
像素格式的值：
8bpp           0
RGB565      1
ARGB1555  2
ARGB0555  3
ARGB8888  4
*/

typedef struct ServiceInfo_S
{
UTIS8   Type;       //0x01:数字电视业务，0x0A：数据广播
UTIU16 OriginalNetworkId;
UTIU16 TsId;
UTIU16 ServiceId;   
UTIU16 ChannelNo; 
UTIS8   Name[30];    //频道名
UTIU32  Frequency;   //频点
} SVRINFO, * PSVRINFO;

//struct ServiceInfo_S ServiceInfo[10];


  

/*UTI协议实现的API*/
UTIS32 UTIGetAppcations(UTIS32 module, PAPPINFO pAppInfo, UTIU8 * AppNum);
//UTIS32 UTIStartApp (UTIS32 module, UTIU8 AppId,  void* pBuf);
//UTIS32 UTISendKey(UTIS32 iModule, UTIU8 cAppId, UTIU8 cKeyCode);
UTIS32 UTIImageTest(UTIS32 iModule, UTIU8 cAppId, UTIU32 iWidth, UTIU32 iHeight);
UTIS32 UTISendServiceInfo(UTIS32 module, PSVRINFO pServiceInfo, UTIU16 Number);
UTIS32 UTISendCurrentServiceInfo(UTIS32 iModule, SVRINFO ServiceInfo);

int UTIStartApp (int module, unsigned char AppId,  void* pBuf);
int UTISendKey(int iModule, unsigned char cAppId, unsigned char cKeyCode);

   void UTIGOServiceInfo(void);

/*主机实现的API*/
void UTIGetGraphicCapability(UTIS32 iModule, PGRAPHICINFO pGraphicInfo);
void UTINotifyStopApp(UTIS32 iModule, UTIU8 cAppId);
void UTIDrawImage(UTIS32 iModule, UTIU8 cAppId, UTIU16 x, 
	UTIU16 y, UTIU16 w, UTIU16 h, UTIU8 * pPixels, UTIU8 PixelBytes, UTIU8 BitMapFormat);
void UTISetPallete(UTIS32 iModule, UTIU32 *pPallete, UTIU32 ColorNum);
void UTIRequestServiceInfo(UTIS32 iModule);
void UTITuneReq(UTIS32 iModule, UTI_TUNER_PARAM tuner_param);
void UTISetVideoWindow (UTIS32 iModule, UTIS16 x, UTIS16 y, UTIS16 w, UTIS16 h);
void UTIPlayService (UTIS32 iModule, UTIU16 originalnetworkid, UTIU16 tsid, UTIU16 serviceid);
void UTIStopService (UTIS32 iModule);
void UTIImageTestReply(UTIS32 iModule,UTIU8 cAppId);
void UTIPlayAVByPID (UTIS32 iModule, UTIU32 video_pid, UTIU32 audio_pid, UTIU32 pcr_pid);
void UTIStopAVByPID (UTIS32 iModule, UTIU32 video_pid, UTIU32 audio_pid, UTIU32 pcr_pid);
#endif /*_HOSTEPGDBCLIENT_H_*/

