/****************************************************************************
* Module    : Host CA Interface.
* History   : 2007/01/05 Creat
* Copyright(c) 2006. Unitend Technologies Inc, All rights reserved.
*****************************************************************************/

#ifndef _HOSTCACLIENT_H_
#define _HOSTCACLIENT_H_

#define NOOFTOPMENU 0 /*最高一级菜单号*/

/*定义UTI接口实现函数的返回值*/
typedef enum{
	UTI_OK,
	UTI_POINTER_ERR,        /*指针为空*/
	UTI_MEMALLOCATE_ERR,    /*分配内存失败*/
	UTI_MODULEINDEX_ERR,    /*模块号不正确，一般是因为未建立连接或连接失败*/
	UTI_CARES_ERR,          /*在模块上未找到CA资源*/
	UTI_SOCKET_CONNECT_ERR, /*Socket连接失败*/
	UTI_SOCKET_SEND_ERR     /*Socket发送失败*/
}UTI_RETURN;

/*文本*/
typedef struct text_t
{
	UTIU8 code_type; /*字符编码类型，参考GY/T 数字视频广播中文业务信息规范。*/
	UTIU16 length;   /*文本长度*/
	UTIU8 *content;  /*文本数据*/
}UTI_TEXT;

/*菜单*/
typedef struct menu_t
{
	UTIU16 menu_id;/*标识消息，发送消息的UTI卡管理应用程序可以通过该menu id作响应处理。*/
							/*主机仅仅在选定这个菜单之后使用，把它发送给UTI卡。*/
	UTIU8 submenu_flag;/*标示menu_id对应的菜单是否含有子菜单，主机可使用该标志遍历子菜单，*/
							/*以便树状结构显示菜单，选择使用。*/
							/*submenu_flag:00 无子菜单;01 有子菜单;other values reserved*/
	UTI_TEXT text;/*菜单文本*/
}UTI_MENU;

typedef struct menu_query_reply_t
{
	UTIU16 parent_menu_id;/*当用户取消选择时，应视为退到上一级菜单，须向UTI卡返回当前菜单列表更上一级的父菜单号，*/
								/*以使UTI列出前一级的菜单，因最高一级的菜单与其他非UTI菜单用同一个窗口，*/
								/*所以最高一级的菜单parent_menu_id规定为0xffff。用户需判断当前菜单的ID是否为0，以退出当前UTI菜单。*/
								/*HOST端可选择是否使用该ID，如果不使用，将直接退回到uti卡的上层。*/
	UTI_TEXT text;/*title*/
	UTIU16 submenu_number;/*当前菜单包含子菜单数目*/
	UTI_MENU *submenu;/*子菜单*/
}MENU_QUERY_REPLY_OBJECT;

/*提示信息*/
typedef struct prompt_msg_t
{
	UTIU8 message_type;/*value:00 一般消息;01 finger;
	02 邮件通知; 03 字幕	字幕可设计为滚动型文字显示。other values reserved*/
	UTIU8 reserved;
	UTI_TEXT text;/*提示信息*/
}PROMPT_MSG_OBJECT;

/*确认*/
typedef struct confirm_query_t
{
	UTIU16 confirm_window_id;/*该ID应能够区分可能并发的各个确认窗口消息。*/
	UTI_TEXT text;/*确认提示信息*/
}CONFIRM_QUERY_OBJECT;

/*输入*/
typedef struct input_query_t
{
	UTIU16 input_window_id;/*该ID应能够区分可能并发的各个输入窗口消息*/
	UTIU16 input_need_length;/*希望用户输入的字符数目*/
	UTIU8 blind;/*标识用户做输入时是否回显可识别字符，例如在输入PIN码的时候，应该在屏幕上出现星号而不是实际输入的数字。*/
	UTI_TEXT text;/*输入提示信息*/
}INPUT_QUERY_OBJECT;

typedef struct input_query_reply_t
{
	UTIU16 input_window_id;/*该ID应能够区分可能并发的各个确认窗口消息*/
	UTIU8 input_result;/*input_result:00 cancel;01 yes;other values reserved*/
	UTI_TEXT in_data;/*用户输入数据*/
}INPUT_QUERY_REPLY_OBJECT;


/*一般信息*/
typedef struct normal_info_t
{
	UTIU16 normal_window_id;
	UTIU8 type;/*对这几类信息窗口，主机端可共用一种或多种显示方式。为支持多种显示方式，*/
						/*我们在发送消息时通过type给于标示，用户可选用或不用该标示。目前未对该类型值定义*/
	UTI_TEXT title;/*标题文本*/
	UTIU8 operate_number;/*该信息窗口的可操作性，如果operate_no>0，那么用户可根据操作提示对显示的信息项进行操作，*/
								/*一般习惯是针对选定的行操作。主机端需要根据operate_number设置不同的功能键给该信息窗口，*/
								/*映射到不同的operate，如"F9键：删除 F10键：编辑"或者画几个按键，*/
								/*鼠标单击后要把映射的操作发送给UTI卡。*/
								/*如果operate_number为0，主机端仅仅显示即可，不需要反馈信息给UTI卡。*/
	UTI_TEXT *operate_text;/*指向包含operate_number个操作说明的内存地址。0xffff0080：[操作说明1][操作说明2]...[操作说明n]*/
	UTIU16 x;/*信息列数*/
	UTIU16 y;/*规定第一行的项为说明项，不能被操作。在我们的应用中主机端不应返回第一行的项。*/
	UTI_TEXT *content;/*指向包含2维数据信息的首单元地址，数据单元为UTI_TEXT，先行再列*/
					/*0xffff0080:[x0,y0][x1,y0]...[xn,y0][x1,y1][x2,y1]...[xn,y1]......[xn,yn]*/
	UTI_TEXT bottom;/*状态文本*/
}NORMAL_INFO_OBJECT;

typedef struct normal_info_reply_t
{
	UTIU16 normal_window_id;/*该ID应能够区分可能并发的各个窗口消息*/
	UTIU8 operate_no;
	UTIU16 info_y;
}NORMAL_INFO_REPLY_OBJECT;



typedef struct UTI_SCREEN_PARAM_t
{
	UTIU8 Data[16];
}UTI_SCREEN_PARAM;




/*主机端调用*/
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

/*发送给主机*/
void UTIMenuQueryReply(UTIS32 iMoudleIndex, MENU_QUERY_REPLY_OBJECT menu_query_reply);
void UTIConfirmQuery(UTIS32 iMoudleIndex, CONFIRM_QUERY_OBJECT confirm_query);
void UTIInputQuery(UTIS32 iMoudleIndex, INPUT_QUERY_OBJECT input_query);
void UTIPromptMsg(UTIS32 iMoudleIndex, PROMPT_MSG_OBJECT promt_msg);
void UTINormalInfo(UTIS32 iMoudleIndex, NORMAL_INFO_OBJECT normal_info);
void UTICAInfo (UTIS32 iMoudleIndex, UTIU8 ca_number, UTIU16 *pca_system_id);
void UTICAPmtReply(UTIS32 iMoudleIndex, UTIU16 service_id, UTIU16 status, UTIU8 *msg, UTIU16 msg_len);
void UTIPIDDiscardReply(UTIS32 iMoudleIndex);
void UTIIRKey(UTIS32 iMoudleIndex, UTIU32 key);

/*主机初始化协议，最好放在USB设备初始化之后*/
void UTIProtocolInit(void);
/*每次从未插入到检测到UTI设备之后调用一次*/
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

