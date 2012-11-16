//////////////////////////////////////////////////////////////////////
#include "HostIncludeApi.h"

short networkid ;
short ServiceID ;
short ProgramNumber ;
short TSID ;
short Frequency ;

struct ServiceInfo_S ServiceInfo[200];
int m_numService=0;
char str[50];


/*********************************************************************************
功能说明：
	获取主机系统的特性。
参数说明：
pGraphicInfo：存储系统特性信息。
返回值：
   无。
*********************************************************************************/
void UTIGetGraphicCapability(UTIS32 iModule, PGRAPHICINFO pGraphicInfo)
{
}

/*********************************************************************************
功能说明：
	卡通过该函数通知主机退出应用。
	主机在此释放应用程序运行是所分配的资源。
参数说明：
	AppId：退出的应用程序的标识号。
返回值：
	无。
*********************************************************************************/

void UTINotifyStopApp(UTIS32 iModule, UTIU8 cAppId)
{
//	UTI_DB_Print("UTINotifyStopApp(%d)", cAppId);
}

/*********************************************************************************
功能说明：
	卡通过该函数在主机为卡分配的绘图区域绘图。
参数说明：
	AppId：应用程序的标识号；
	x：图像的x坐标；
y：图像的y坐标；
w：图像的宽度；
h：图像的高度；
pPixels:图像的缓冲区；
返回值：
	无。
*********************************************************************************/
unsigned long gSystemColorMode; //=8： 8bit深度,256色模式,调色板
unsigned char palltebytes;

char ImageAvail = 0;
int imagex, imagey, imagew, imageh;
unsigned char imagebuff[720*576*2];
unsigned long g_time;

void UTIDrawImage(UTIS32 iModule, UTIU8 cAppId, UTIU16 x, 
	UTIU16 y, UTIU16 w, UTIU16 h, UTIU8 * pPixels, UTIU8 PixelBytes, UTIU8 BitMapFormat)
{

    //gSystemColorMode = palltebytes;
	gSystemColorMode = PixelBytes*8;
	imagex = x; imagey = y; imagew =w; imageh= h;
	UTIMemCopy(imagebuff, pPixels, w*h*(gSystemColorMode/8));
//	memcpy(imagebuff, pPixels, w*h*PixelBytes);
	ImageAvail = 1;
}
/*********************************************************************************
功能说明：
	设置调色板。当像素格式为8bpp的时候需要调用该函数。
参数说明：
	pPallete：调色板缓冲。索引0代表的颜色为透明色。
 	ColorNum：调色板的颜色数量。
返回值：
	无。
*********************************************************************************/

unsigned long gSystemPallete[256];
void UTISetPallete(UTIS32 iModule, UTIU32 *pPallete, UTIU32 ColorNum)
{
	unsigned int i;
	//FILE * f;
	for(i=0; i<ColorNum; i++)
	{
		gSystemPallete[i] = pPallete[i];
	}
}



/*********************************************************************************
功能说明：
	请求获得业务信息。主机收到该请求后，
	应当整理本机存储的业务信息，
	调用UTIS32 UTISendServiceInfo(UTIS32 module, PSVRINFO pServiceInfo, UTIU16 Number)
	将业务信息发给指定模块。
参数说明：
	module：模块号。
返回值：
	无。
*********************************************************************************/
PSVRINFO pServiceInfo;

int sendprogramnum = 60;

void UTIRequestServiceInfo(UTIS32 iModule)
{
	int i;
	unsigned int FREQ;

	FREQ = 586;
/*
	ServiceInfo[0].Type = 1;
	ServiceInfo[0].OriginalNetworkId = 0x1FB1;
   ServiceInfo[0].TsId = 0x06; 
   ServiceInfo[0].ServiceId = 601;
   ServiceInfo[0].ChannelNo = 1;
   sprintf(ServiceInfo[0].Name, "��������");
   ServiceInfo[0].Frequency = FREQ;

   ServiceInfo[1].Type = 1;
   ServiceInfo[1].OriginalNetworkId = 0x1FB1;
   ServiceInfo[1].TsId = 0x06; 
   ServiceInfo[1].ServiceId = 602;
   ServiceInfo[1].ChannelNo = 2;
   sprintf(ServiceInfo[1].Name, "�����ٶ�");
   ServiceInfo[1].Frequency = FREQ;

   ServiceInfo[2].Type = 1;
   ServiceInfo[2].OriginalNetworkId = 0x1FB1;
   ServiceInfo[2].TsId = 0x06; 
   ServiceInfo[2].ServiceId = 603;
   ServiceInfo[2].ChannelNo = 3;
   sprintf(ServiceInfo[2].Name, "������������");
   ServiceInfo[2].Frequency = FREQ;

   ServiceInfo[3].Type = 1;
   ServiceInfo[3].OriginalNetworkId = 0x1FB1;
   ServiceInfo[3].TsId = 0x06; 
   ServiceInfo[3].ServiceId = 604;
   ServiceInfo[3].ChannelNo = 4;
   sprintf(ServiceInfo[3].Name, "�Ϻ�Ϸ��");
   ServiceInfo[3].Frequency = FREQ;

   ServiceInfo[4].Type = 1;
   ServiceInfo[4].OriginalNetworkId = 0x1FB1;
   ServiceInfo[4].TsId = 0x06; 
   ServiceInfo[4].ServiceId = 605;
   ServiceInfo[4].ChannelNo = 5;
   sprintf(ServiceInfo[4].Name, "�ຣ����");
   ServiceInfo[4].Frequency = FREQ;

   ServiceInfo[5].Type = 1;
   ServiceInfo[5].OriginalNetworkId = 0x1FB1;
   ServiceInfo[5].TsId = 0x06; 
   ServiceInfo[5].ServiceId = 606;
   ServiceInfo[5].ChannelNo = 6;
   sprintf(ServiceInfo[5].Name, "��ͨ");
   ServiceInfo[5].Frequency = FREQ;

   ServiceInfo[6].Type = 1;
   ServiceInfo[6].OriginalNetworkId = 0x1D80;
   ServiceInfo[6].TsId = 0x06; 
   ServiceInfo[6].ServiceId = 607;
   ServiceInfo[6].ChannelNo = 7;
   sprintf(ServiceInfo[6].Name, "��������");
   ServiceInfo[6].Frequency = FREQ;
   */

   ServiceInfo[0].Type = 1;
   ServiceInfo[0].OriginalNetworkId = 0x1D80;
   ServiceInfo[0].TsId = 0x65; 
   ServiceInfo[0].ServiceId = 101;
   ServiceInfo[0].ChannelNo = 1;
   sprintf(ServiceInfo[0].Name, "CCTV1");
   ServiceInfo[0].Frequency = FREQ;

   ServiceInfo[1].Type = 1;
   ServiceInfo[1].OriginalNetworkId = 0x1D80;
   ServiceInfo[1].TsId = 0x65; 
   ServiceInfo[1].ServiceId = 102;
   ServiceInfo[1].ChannelNo = 2;
   sprintf(ServiceInfo[1].Name, "CCTV2��������̫�ͿƼ�����");
   ServiceInfo[1].Frequency = FREQ;

   ServiceInfo[2].Type = 1;
   ServiceInfo[2].OriginalNetworkId = 0x1D80;
   ServiceInfo[2].TsId = 0x65; 
   ServiceInfo[2].ServiceId = 103;
   ServiceInfo[2].ChannelNo = 3;
   sprintf(ServiceInfo[2].Name, "CCTV3");
   ServiceInfo[2].Frequency = FREQ;

   ServiceInfo[3].Type = 1;
   ServiceInfo[3].OriginalNetworkId = 0x1D80;
   ServiceInfo[3].TsId = 0x65; 
   ServiceInfo[3].ServiceId = 104;
   ServiceInfo[3].ChannelNo = 4;
   sprintf(ServiceInfo[3].Name, "CCTV4");
   ServiceInfo[3].Frequency = FREQ;

   ServiceInfo[4].Type = 1;
   ServiceInfo[4].OriginalNetworkId = 0x1D80;
   ServiceInfo[4].TsId = 0x65; 
   ServiceInfo[4].ServiceId = 105;
   ServiceInfo[4].ChannelNo = 5;
   sprintf(ServiceInfo[4].Name, "CCTV5");
   ServiceInfo[4].Frequency = FREQ;

   ServiceInfo[5].Type = 1;
   ServiceInfo[5].OriginalNetworkId = 0x1D80;
   ServiceInfo[5].TsId = 0x65; 
   ServiceInfo[5].ServiceId = 106;
   ServiceInfo[5].ChannelNo = 6;
   sprintf(ServiceInfo[5].Name, "CCTV6��������̫�ͿƼ�����");
   ServiceInfo[5].Frequency = FREQ;

   ServiceInfo[6].Type = 1;
   ServiceInfo[6].OriginalNetworkId = 0x1D80;
   ServiceInfo[6].TsId = 0x65; 
   ServiceInfo[6].ServiceId = 107;
   ServiceInfo[6].ChannelNo = 7;
   sprintf(ServiceInfo[6].Name, "���̨");
   ServiceInfo[6].Frequency = FREQ;

   ServiceInfo[7].Type =1;
   ServiceInfo[7].OriginalNetworkId = 0x1D80;
   ServiceInfo[7].TsId = 0x65; 
   ServiceInfo[7].ServiceId = 108;
   ServiceInfo[7].ChannelNo = 8;
   sprintf(ServiceInfo[7].Name, "����̨");
   ServiceInfo[7].Frequency = FREQ;

   ServiceInfo[9].Type = 0x0c;
   ServiceInfo[9].OriginalNetworkId = 0x4080;
   ServiceInfo[9].TsId = 0xe; 
   ServiceInfo[9].ServiceId = 31;
   ServiceInfo[9].ChannelNo = 9;
   sprintf(ServiceInfo[9].Name, "�����軪");
   ServiceInfo[9].Frequency = FREQ;

   ServiceInfo[8].Type = 0x0c;
   ServiceInfo[8].OriginalNetworkId = 0x0001;
   ServiceInfo[8].TsId = 0x61; 
   ServiceInfo[8].ServiceId = 805;
   ServiceInfo[8].ChannelNo = 10;
   sprintf(ServiceInfo[8].Name, "����805");
   ServiceInfo[8].Frequency = FREQ;

   ServiceInfo[10].Type = 0x0c;
   ServiceInfo[10].OriginalNetworkId = 0x0001;
   ServiceInfo[10].TsId = 0x61; 
   ServiceInfo[10].ServiceId = 804;
   ServiceInfo[10].ChannelNo = 11;
   sprintf(ServiceInfo[10].Name, "����804");
   ServiceInfo[10].Frequency = FREQ;

   ServiceInfo[11].Type = 0x0c;
   ServiceInfo[11].OriginalNetworkId = 0x0001;
   ServiceInfo[11].TsId = 0x61; 
   ServiceInfo[11].ServiceId = 803;
   ServiceInfo[11].ChannelNo =12;
   sprintf(ServiceInfo[11].Name, "����803");
   ServiceInfo[11].Frequency = FREQ;

   ServiceInfo[12].Type = 0x0c;
   ServiceInfo[12].OriginalNetworkId = 0x0001;
   ServiceInfo[12].TsId = 0x61; 
   ServiceInfo[12].ServiceId = 806;
   ServiceInfo[12].ChannelNo = 13;
   sprintf(ServiceInfo[12].Name, "����806");
   ServiceInfo[12].Frequency = FREQ;

   ServiceInfo[13].Type = 0x0c;
   ServiceInfo[13].OriginalNetworkId = 0x0001;
   ServiceInfo[13].TsId = 0x5f; 
   ServiceInfo[13].ServiceId = 808;
   ServiceInfo[13].ChannelNo = 14;
   sprintf(ServiceInfo[13].Name, "����808");
   ServiceInfo[13].Frequency = 474;

   ServiceInfo[14].Type = 0x0c;
   ServiceInfo[14].OriginalNetworkId = 0x0001;
   ServiceInfo[14].TsId = 0x60; 
   ServiceInfo[14].ServiceId = 807;
   ServiceInfo[14].ChannelNo = 15;
   sprintf(ServiceInfo[14].Name, "����807");
   ServiceInfo[14].Frequency = 474;

    ServiceInfo[15].Type = 0x0c;
   ServiceInfo[15].OriginalNetworkId = 0x0001;
   ServiceInfo[15].TsId = 0x17; 
   ServiceInfo[15].ServiceId = 800;
   ServiceInfo[15].ChannelNo = 14;
   sprintf(ServiceInfo[15].Name, "����800");
   ServiceInfo[15].Frequency = 323;

   ServiceInfo[16].Type = 0x0c;
   ServiceInfo[16].OriginalNetworkId = 0x0001;
   ServiceInfo[16].TsId = 0x17; 
   ServiceInfo[16].ServiceId = 802;
   ServiceInfo[16].ChannelNo = 15;
   sprintf(ServiceInfo[16].Name, "����802");
   ServiceInfo[16].Frequency = 323;


  for(i=17;i<200;i++)
  {
    ServiceInfo[i].Type = 1;
   ServiceInfo[i].OriginalNetworkId = 0x1D80;
   ServiceInfo[i].TsId = 0x65; 
   ServiceInfo[i].ServiceId = 108+i;
   ServiceInfo[i].ChannelNo = 15+i;
   sprintf(str, "��Ŀ%d", i);
   sprintf(ServiceInfo[i].Name,"%s", str);
   ServiceInfo[i].Frequency = FREQ;
  
  }
   pServiceInfo = &ServiceInfo[0];

   m_numService  = 100;
   UTISendServiceInfo(0, pServiceInfo, 100);
 //  sendprogramnum = sendprogramnum - 10;
 //  if(sendprogramnum < 10)
//	sendprogramnum = 10;
}

struct ServiceInfo_S CurServiceInfo;

void StartIPANEL(void)
{

    CurServiceInfo.OriginalNetworkId = 1;
	CurServiceInfo.TsId = 0x61;
	CurServiceInfo.ServiceId = 803;
	CurServiceInfo.Frequency = 586;
    //UTISendCurrentServiceInfo(0,CurServiceInfo);
}

void UTIGOServiceInfo(void)
{
   UTISendServiceInfo(0, pServiceInfo, ProgramNumber);
}
/*********************************************************************************
����˵����
	�����г��ĳ��Ƶ�㡣
����˵����
	tuner_param����Ƶ��ز���������һ����DVB-C���ͣ�
	UTI_TUNER_PARAM��ο�UTI Integration Guide -Tuner�ӿ��ĵ���
����ֵ��
	�ޡ�
*********************************************************************************/

/*********************************************************************************
����˵����
	������Ƶ���ڡ�
����˵����
	x���������Ͻ�x��ꡣ
	y���������Ͻ�y��ꡣ
	w�����ڳ���
	h�����ڿ?
����ֵ��
	�ޡ�
*********************************************************************************/
void UTISetVideoWindow (UTIS32 iModule, UTIS16 x, UTIS16 y, UTIS16 w, UTIS16 h)
{
	PRINTF("UTISetVideoWindow module:%d x:%d y:%d w:%d h:%d\n",iModule, x, y, w, h);
}

/*********************************************************************************
����˵����
	����ҵ����Ƶ����Ƶ��Ŀ��
����˵����
	originalnetwordid: ��ʼ����ʶ��š�
	tsid����������ʶ�š�
	serviceid�� ҵ���ʶ�š�
����ֵ��
	�ޡ�
*********************************************************************************/
void UTIPlayService (UTIS32 iModule, UTIU16 originalnetworkid, UTIU16 tsid, UTIU16 serviceid)
{
	int i;//, ret;
	//void * hDevice;
	/*Tuner ����*/
	UTI_TUNER_PARAM tuner_parms;
//	return;     //for test

	for(i=0; i<m_numService; i++)
	{
		if(serviceid == ServiceInfo[i].ServiceId)
		{	
		//	hDevice = OpenDevice();
			//SetDVBCTunerParam(iModule, 5000, ServiceInfo[i].Frequency*1000000, 0,  6875000, 3, 0);
		//	ret = setDVBCTunerParam(hDevice, ServiceInfo[i].Frequency*1000000, 6875000, 3, 0, 0, 3000);
			//SetDVBCTunerParam(iModule, 200, ServiceInfo[i].Frequency*1000000, 0, 6875000, 3, 0);
			//Tunercard
			tuner_parms.type = Tuner_DVB_C;
			tuner_parms.parms.dvb_c.Frequency = ServiceInfo[i].Frequency*1000000;
			tuner_parms.parms.dvb_c.Modulation = 3;			
			tuner_parms.parms.dvb_c.Symbol_rate = 6875000;

			UTITunerSet(iModule, 0, tuner_parms);/*��Ƶ*/
//			CloseDevice(hDevice);
			break;
		}
	}
}

/*********************************************************************************
����˵����
	ֹͣ����ҵ����Ƶ����Ƶ��Ŀ��
����˵����
	module��ģ���
����ֵ��
	�ޡ�
*********************************************************************************/
void UTIStopService (UTIS32 iModule)
{
}

/*********************************************************************************
����˵����
	ͼ����Է�����
����˵����
	module��ģ���
    cAppId:Ӧ�ñ�ʾ��
����ֵ��
	�ޡ�
*********************************************************************************/
void UTIImageTestReply(UTIS32 iModule,UTIU8 cAppId)
{
  

}

UTIS32 UTIFilterSetParam( UTIU32 *dwFilterID, UTIU8 * pbFilterData, UTIU8 * pbMaskData, UTIU8 byLen, UTIU16 wPid, UTIU16 wWaitSeconds)
{
	return 0;
}

UTIS32 UTIFilterSetStatus(UTIU32 dwFilterID, UTIU8 bEnable)
{
	return 0;
}
