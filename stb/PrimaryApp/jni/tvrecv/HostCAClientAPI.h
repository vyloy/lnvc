/****************************************************************************
* Module    : Host CA Interface.
* History   : 2007/01/05 Creat
* Copyright(c) 2006. Unitend Technologies Inc, All rights reserved.
*****************************************************************************/

#ifndef _HOSTCACLIENT_H_
#define _HOSTCACLIENT_H_

#define NOOFTOPMENU 0 /*���һ���˵���*/

/*����UTI�ӿ�ʵ�ֺ����ķ���ֵ*/
typedef enum{
	UTI_OK,
	UTI_POINTER_ERR,        /*ָ��Ϊ��*/
	UTI_MEMALLOCATE_ERR,    /*�����ڴ�ʧ��*/
	UTI_MODULEINDEX_ERR,    /*ģ��Ų���ȷ��һ������Ϊδ�������ӻ�����ʧ��*/
	UTI_CARES_ERR,          /*��ģ����δ�ҵ�CA��Դ*/
	UTI_SOCKET_CONNECT_ERR, /*Socket����ʧ��*/
	UTI_SOCKET_SEND_ERR     /*Socket����ʧ��*/
}UTI_RETURN;

/*�ı�*/
typedef struct text_t
{
	UTIU8 code_type; /*�ַ��������ͣ��ο�GY/T ������Ƶ�㲥����ҵ����Ϣ�淶��*/
	UTIU16 length;   /*�ı�����*/
	UTIU8 *content;  /*�ı�����*/
}UTI_TEXT;

/*�˵�*/
typedef struct menu_t
{
	UTIU16 menu_id;/*��ʶ��Ϣ��������Ϣ��UTI������Ӧ�ó������ͨ����menu id����Ӧ����*/
							/*����������ѡ������˵�֮��ʹ�ã��������͸�UTI����*/
	UTIU8 submenu_flag;/*��ʾmenu_id��Ӧ�Ĳ˵��Ƿ����Ӳ˵���������ʹ�øñ�־�����Ӳ˵���*/
							/*�Ա���״�ṹ��ʾ�˵���ѡ��ʹ�á�*/
							/*submenu_flag:00 ���Ӳ˵�;01 ���Ӳ˵�;other values reserved*/
	UTI_TEXT text;/*�˵��ı�*/
}UTI_MENU;

typedef struct menu_query_reply_t
{
	UTIU16 parent_menu_id;/*���û�ȡ��ѡ��ʱ��Ӧ��Ϊ�˵���һ���˵�������UTI�����ص�ǰ�˵��б����һ���ĸ��˵��ţ�*/
								/*��ʹUTI�г�ǰһ���Ĳ˵��������һ���Ĳ˵���������UTI�˵���ͬһ�����ڣ�*/
								/*�������һ���Ĳ˵�parent_menu_id�涨Ϊ0xffff���û����жϵ�ǰ�˵���ID�Ƿ�Ϊ0�����˳���ǰUTI�˵���*/
								/*HOST�˿�ѡ���Ƿ�ʹ�ø�ID�������ʹ�ã���ֱ���˻ص�uti�����ϲ㡣*/
	UTI_TEXT text;/*title*/
	UTIU16 submenu_number;/*��ǰ�˵������Ӳ˵���Ŀ*/
	UTI_MENU *submenu;/*�Ӳ˵�*/
}MENU_QUERY_REPLY_OBJECT;

/*��ʾ��Ϣ*/
typedef struct prompt_msg_t
{
	UTIU8 message_type;/*value:00 һ����Ϣ;01 finger;
	02 �ʼ�֪ͨ; 03 ��Ļ	��Ļ�����Ϊ������������ʾ��other values reserved*/
	UTIU8 reserved;
	UTI_TEXT text;/*��ʾ��Ϣ*/
}PROMPT_MSG_OBJECT;

/*ȷ��*/
typedef struct confirm_query_t
{
	UTIU16 confirm_window_id;/*��IDӦ�ܹ����ֿ��ܲ����ĸ���ȷ�ϴ�����Ϣ��*/
	UTI_TEXT text;/*ȷ����ʾ��Ϣ*/
}CONFIRM_QUERY_OBJECT;

/*����*/
typedef struct input_query_t
{
	UTIU16 input_window_id;/*��IDӦ�ܹ����ֿ��ܲ����ĸ������봰����Ϣ*/
	UTIU16 input_need_length;/*ϣ���û�������ַ���Ŀ*/
	UTIU8 blind;/*��ʶ�û�������ʱ�Ƿ���Կ�ʶ���ַ�������������PIN���ʱ��Ӧ������Ļ�ϳ����ǺŶ�����ʵ����������֡�*/
	UTI_TEXT text;/*������ʾ��Ϣ*/
}INPUT_QUERY_OBJECT;

typedef struct input_query_reply_t
{
	UTIU16 input_window_id;/*��IDӦ�ܹ����ֿ��ܲ����ĸ���ȷ�ϴ�����Ϣ*/
	UTIU8 input_result;/*input_result:00 cancel;01 yes;other values reserved*/
	UTI_TEXT in_data;/*�û���������*/
}INPUT_QUERY_REPLY_OBJECT;


/*һ����Ϣ*/
typedef struct normal_info_t
{
	UTIU16 normal_window_id;
	UTIU8 type;/*���⼸����Ϣ���ڣ������˿ɹ���һ�ֻ������ʾ��ʽ��Ϊ֧�ֶ�����ʾ��ʽ��*/
						/*�����ڷ�����Ϣʱͨ��type���ڱ�ʾ���û���ѡ�û��øñ�ʾ��Ŀǰδ�Ը�����ֵ����*/
	UTI_TEXT title;/*�����ı�*/
	UTIU8 operate_number;/*����Ϣ���ڵĿɲ����ԣ����operate_no>0����ô�û��ɸ��ݲ�����ʾ����ʾ����Ϣ����в�����*/
								/*һ��ϰ�������ѡ�����в�������������Ҫ����operate_number���ò�ͬ�Ĺ��ܼ�������Ϣ���ڣ�*/
								/*ӳ�䵽��ͬ��operate����"F9����ɾ�� F10�����༭"���߻�����������*/
								/*��굥����Ҫ��ӳ��Ĳ������͸�UTI����*/
								/*���operate_numberΪ0�������˽�����ʾ���ɣ�����Ҫ������Ϣ��UTI����*/
	UTI_TEXT *operate_text;/*ָ�����operate_number������˵�����ڴ��ַ��0xffff0080��[����˵��1][����˵��2]...[����˵��n]*/
	UTIU16 x;/*��Ϣ����*/
	UTIU16 y;/*�涨��һ�е���Ϊ˵������ܱ������������ǵ�Ӧ���������˲�Ӧ���ص�һ�е��*/
	UTI_TEXT *content;/*ָ�����2ά������Ϣ���׵�Ԫ��ַ�����ݵ�ԪΪUTI_TEXT����������*/
					/*0xffff0080:[x0,y0][x1,y0]...[xn,y0][x1,y1][x2,y1]...[xn,y1]......[xn,yn]*/
	UTI_TEXT bottom;/*״̬�ı�*/
}NORMAL_INFO_OBJECT;

typedef struct normal_info_reply_t
{
	UTIU16 normal_window_id;/*��IDӦ�ܹ����ֿ��ܲ����ĸ���������Ϣ*/
	UTIU8 operate_no;
	UTIU16 info_y;
}NORMAL_INFO_REPLY_OBJECT;



typedef struct UTI_SCREEN_PARAM_t
{
	UTIU8 Data[16];
}UTI_SCREEN_PARAM;




/*�����˵���*/
extern UTIS32 UTIMenuQuery(UTIS32 iModuleIndex, UTIU16 menu_id);
extern UTIS32 UTISetLanguage(UTIS32 iModuleIndex, UTIU16 type);
extern UTIS32 UTIConfrimQueryReply(UTIS32 iModuleIndex, UTIU16 confirm_window_id, UTIU8 result);
extern UTIS32 UTIInputQueryReply(UTIS32 iModuleIndex, INPUT_QUERY_REPLY_OBJECT input_query_reply);
extern UTIS32 UTINormalInfoReply(UTIS32 iModuleIndex, NORMAL_INFO_REPLY_OBJECT normal_info_reply);
extern UTIS32 UTICAInfoEnq(UTIS32 iModuleIndex );
extern UTIS32 UTICAPmt(UTIS32 iModuleIndex, UTIU16 service_id, UTIU8 *pmt_data, UTIU32 pmt_data_len);
extern UTIS32 UTICASetPmtPID(UTIS32 iModuleIndex, UTIU16 service_id, UTIU16 ts_id, UTIU16 net_id, UTIU16 org_net_id, UTIU16 pmt_pid);
extern UTIS32 UTICASetFixCW(UTIS32 iModuleIndex, UTIU16 v_pid, UTIU16 a_pid, UTIU8 *v_key, UTIU8 *a_key);
extern UTIS32 UTIPIDDiscard(UTIS32 iModuleIndex, UTIU8 pid_num, UTIU8 flag, UTIU8 *pid);
extern UTIS32 UTIDRMEnable(UTIS32 iModuleIndex, UTIU8 iEnable);

/*���͸�����*/
void UTIMenuQueryReply(UTIS32 iMoudleIndex, MENU_QUERY_REPLY_OBJECT menu_query_reply);
void UTIConfirmQuery(UTIS32 iMoudleIndex, CONFIRM_QUERY_OBJECT confirm_query);
void UTIInputQuery(UTIS32 iMoudleIndex, INPUT_QUERY_OBJECT input_query);
void UTIPromptMsg(UTIS32 iMoudleIndex, PROMPT_MSG_OBJECT promt_msg);
void UTINormalInfo(UTIS32 iMoudleIndex, NORMAL_INFO_OBJECT normal_info);
void UTICAInfo (UTIS32 iMoudleIndex, UTIU8 ca_number, UTIU16 *pca_system_id);
void UTICAPmtReply(UTIS32 iMoudleIndex, UTIU16 service_id, UTIU16 status, UTIU8 *msg, UTIU16 msg_len);
void UTIPIDDiscardReply(UTIS32 iMoudleIndex);
void UTIIRKey(UTIS32 iMoudleIndex, UTIU32 key);

/*������ʼ��Э�飬��÷���USB�豸��ʼ��֮��*/
void UTIProtocolInit(void);
/*ÿ�δ�δ���뵽��⵽UTI�豸֮�����һ��*/
UTIS32 UTISetModuleIndex(UTIS32 dev, void *ReadFunc ,void *WriteFunc , void *OpenFunc, void *CloseFunc);
void UTILockService(UTIS32 iModuleIndex,int param);
void UTIUnlockService(UTIS32 iModuleIndex,int param);
void UTIUdrmRecvData(UTIS32 iModuleIndex,UTIU8 *pData, UTIU32 data_len);
void UTISetVolume(UTIS32 iModuleIndex,UTIS32 volume);
void UTIGetVolume(UTIS32 iModuleIndex);
void UTISetMute(UTIS32 iModuleIndex);
void UTIClearImage(UTIS32 iModuleIndex);
UTIS32 UTIGetVolumeReply(UTIS32 iModule, UTIS32 volume);

void UTIGetScreenParameter(UTIS32 iModuleIndex);
void UTISetScreenParameter(UTIS32 iModuleIndex, UTI_SCREEN_PARAM *pscreen_param);
UTIS32 UTIGetScreenParameterReply(UTIS32 iModuleIndex, UTI_SCREEN_PARAM *pscreen_param);
UTIS32 UTIServiceHostSendCmd( UTIS32 Module_Index, UTIU32 send_cmd, UTIU8 *send_data, UTIU32 send_datalen );

#define UTI_SERVICE_CMD_START_DTV       	0x00000100 
#define UTI_SERVICE_CMD_END_DTV		       	0x00000101 



#endif // #ifndef  _HOSTCACLIENT_H_

