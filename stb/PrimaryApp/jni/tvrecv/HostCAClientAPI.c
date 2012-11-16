// HostCAClientAPI.cpp: implementation of the CHostCAClientAPI class.
//
//////////////////////////////////////////////////////////////////////
//----------------------------------------------------------------
//
//这个文件由客户自己修改。添加需要的数据
//
//----------------------------------------------------------------
#include "HostIncludeApi.h"

// void PrintMenuQueryReply(MENU_QUERY_REPLY_OBJECT menu_query_reply)
// {
// 	//UTIS8 key;
// 	UTIU32 i;
// 
// 	for (i = 0; i < menu_query_reply.submenu_number; i++)
// 	{
// 		//char buffer[256];
// 		//sprintf(buffer,"##---%s\r\n",  menu_query_reply.submenu[i].text.content);
// 		//PRINTF(buffer);
// 		PRINTF("%d---%s\r\n", i, menu_query_reply.submenu[i].text.content);
// 		//save menu_id
// 		//...
// 	}
// 
// 	//PRINTF("q---Return MainMenu\n");
// 	//PRINTF("Input menu :\n");	
// }

void UTIMenuQueryReply(UTIS32 iMoudleIndex, MENU_QUERY_REPLY_OBJECT menu_query_reply)
{
	PRINTF("Menu iMoudleIndex[%d], submenu number[%d].\n", iMoudleIndex, menu_query_reply.submenu_number);
	//PrintMenuQueryReply(menu_query_reply);
}

void UTIConfirmQuery(UTIS32 iMoudleIndex, CONFIRM_QUERY_OBJECT confirm_query)
{
	PRINTF("iMoudleIndex: %d:confirm_window_id:%d\n",iMoudleIndex,confirm_query.confirm_window_id);

	if(confirm_query.text.length > 0)
	{
		PRINTF("Prompt Msg Text:%s\n",confirm_query.text.content);
	}
}

void UTIInputQuery(UTIS32 iMoudleIndex, INPUT_QUERY_OBJECT input_query)
{
	INPUT_QUERY_REPLY_OBJECT input_query_reply;

	PRINTF("iMoudleIndex: %d:INPUT_QUERY window id:%d\n",iMoudleIndex, input_query.input_window_id);

	if(input_query.text.length > 0)
	{
		PRINTF("INPUT_QUERY test:%s\n",input_query.text.content);
	}

	input_query_reply.input_window_id = input_query.input_window_id;
	input_query_reply.input_result = 1;
	input_query_reply.in_data.code_type = 0;
	input_query_reply.in_data.length = 0;
	input_query_reply.in_data.content = NULL;;

	UTIInputQueryReply(iMoudleIndex, input_query_reply);
}

void UTIPromptMsg(UTIS32 iMoudleIndex, PROMPT_MSG_OBJECT prompt_msg)
{
	PRINTF("UTIPromptMsg %s\n", prompt_msg.text.content);
}

void UTINormalInfo(UTIS32 iMoudleIndex, NORMAL_INFO_OBJECT normal_info)
{

	UTIU32 i,j;
	NORMAL_INFO_OBJECT *pNormalInfo = &normal_info;
	NORMAL_INFO_REPLY_OBJECT normal_info_reply;

	PRINTF("iMoudleIndex[%d], Normal Info Type[%d]\n",iMoudleIndex,pNormalInfo->type);

	PRINTF("Normal Info Title:%s\n",pNormalInfo->title.content);
	
	PRINTF("Normal Info operate_number:%d\n",pNormalInfo->operate_number);
	
	for(i=0;i< pNormalInfo->operate_number;i++)
	{
		PRINTF("Normal Info operate_number:%d Name:%s\n",i, pNormalInfo->operate_text[i].content);
	}
	
	PRINTF("Normal Infoinfo_coloum_number:%d info_line_number:%d\n", pNormalInfo->x, pNormalInfo->y);
	
	for(i=0;i<pNormalInfo->y;i++)
	{
		for(j=0; j < pNormalInfo->x; j++)
		{
			PRINTF("Normal Info context:%s \n", pNormalInfo->content[i * pNormalInfo->x + j].content);
		}
	}

	PRINTF("Normal Info context len:%d: \n", pNormalInfo->bottom.length);

	if(pNormalInfo->bottom.length > 0)
	{
		PRINTF("Normal Info context:%s \n", pNormalInfo->bottom.content);
	}

	normal_info_reply.normal_window_id = pNormalInfo->normal_window_id;
	normal_info_reply.info_y = 0;
	normal_info_reply.operate_no = 0;
	
	UTINormalInfoReply(iMoudleIndex,normal_info_reply);
}

void UTICAInfo(UTIS32 iMoudleIndex, UTIU8 ca_number, UTIU16 *pca_system_id)
{
	UTI_DBG1(("UTICAInfo pca_system_id[0x%x] \n", *pca_system_id));
}

void UTICAPmtReply(UTIS32 iMoudleIndex, UTIU16 service_id, UTIU16 status, UTIU8 *msg, UTIU16 msg_len)
{
	UTI_DBG1(("UTICASetPmtPid callback service_id[%d]\n", service_id));
}

void UTIPIDDiscardReply(UTIS32 iMoudleIndex)
{
}

void UTIIRKey(UTIS32 iMoudleIndex, UTIU32 key)
{
}




void UTIPlayAVByPID(UTIS32 iModuleIndex, UTIU32 video_pid, UTIU32 audio_pid, UTIU32 pcr_pid)
{
	PRINTF("UTIPlayAVByPID module:%d video pid:%d audio pid:%d prc pid:%d\n",iModuleIndex, video_pid, audio_pid, pcr_pid);

}

void UTIStopAVByPID(UTIS32 iModuleIndex, UTIU32 video_pid, UTIU32 audio_pid, UTIU32 pcr_pid)
{
	PRINTF("UTIStopAVByPID module:%d video pid:%d audio pid:%d prc pid:%d\n",iModuleIndex, video_pid, audio_pid, pcr_pid);
}

void UTITuneReq(UTIS32 iModuleIndex,UTI_TUNER_PARAM tuner_param)
{
	PRINTF("UTITuneReq\n");
}

void UTILockService(UTIS32 iModuleIndex,int param)
{	
	PRINTF("UTILockService\n");
}


void UTIUnlockService(UTIS32 iModuleIndex,int param)
{
	PRINTF("UTIUnlockService\n");
}

void UTIUdrmRecvData(UTIS32 iModuleIndex,UTIU8 *pData, UTIU32 data_len)
{
	PRINTF("UTIUdrmRecvData data_len%d\n",data_len);
}

void UTISetVolume(UTIS32 iModuleIndex,int volume)
{
	PRINTF("UTISetVolume volume:%d\n",volume);
}

void UTIGetVolume(UTIS32 iModuleIndex)
{
	int volume;
	
	PRINTF("UTIGetVolume \n");
	volume = 51;
	UTIGetVolumeReply( iModuleIndex,  volume);

	
}

void UTISetMute(UTIS32 iModuleIndex)
{
	PRINTF("UTISetMute\n");
}

void UTIClearImage(UTIS32 iModuleIndex)
{
	PRINTF("UTIClearImage\n");
}

void UTIGetScreenParameter(UTIS32 iModuleIndex)
{
	UTI_SCREEN_PARAM screen_param;
	PRINTF("UTIGetScreenParameter\n");

	screen_param.Data[0] = 50;
	screen_param.Data[1] = 60;
	screen_param.Data[2] = 70;
	
	UTIGetScreenParameterReply(iModuleIndex, &screen_param);
}

void UTISetScreenParameter(UTIS32 iModuleIndex,UTI_SCREEN_PARAM *pscreen_param)
{
	PRINTF("UTISetScreenParameter\n");
	PRINTF("data[0]:%d\n",pscreen_param->Data[0]);
	PRINTF("data[1]:%d\n",pscreen_param->Data[1]);
	PRINTF("data[2]:%d\n",pscreen_param->Data[2]);
	
}

void UTIServiceHostRevcCmd( UTIS32 Module_Index, UTIU32 recv_cmd, UTIU8 *recv_data, UTIU32 recv_datalen )
{
	PRINTF("UTIServiceHostRevcCmd\n");
	PRINTF("cmd:0x%08x\n", recv_cmd);
	PRINTF("recv_datalen:%d\n", recv_datalen);

}




