#include "HostIncludeApi.h"

#define LOG_APPINFO_CLIENT_API	0

//extern STOSD_RegionHandle_t DrawHandle;

void UTIGetAppInfoCallback(UTIS32 Module_Index, UTIU16 app_type, UTIU16 app_menu, UTIU16 menu_id, UTIS8 *app_name, UTIU8 name_len)
{
#if LOG_APPINFO_CLIENT_API
	UTIS8 logstr[1024];
	UTIS32 loglen=0;
#endif
#if OS_OS20
	UTIS8 tmp_string[100];
	const UTIS32 CA_ACCOUNT_INFORMATION_W = 550;
	const UTIS32 CA_ACCOUNT_INFORMATION_H =230;
	UTIS32 x,y;
	UTIS32 i=0;
	
	x = (720 - CA_ACCOUNT_INFORMATION_W) / 2;
	y = (576 - CA_ACCOUNT_INFORMATION_H) / 2;    	
	
	// draw the background
	STOSD_FillRectangle(DrawHandle, x, y, CA_ACCOUNT_INFORMATION_W, CA_ACCOUNT_INFORMATION_H, bluecolor);

	sprintf(tmp_string, "[%d]UTI卡设备的返回的应用信息：",Module_Index);
	ShowHz(x+10, y+5, 24, whitecolor, tmp_string);

	sprintf(tmp_string, "应用类型：%04x\n",app_type);
	ShowHz(x+10, y+5+30, 24, whitecolor, tmp_string);

	sprintf(tmp_string, "应用提供商ID：%04x\n",app_menu);
	ShowHz(x+10, y+5+60, 24, whitecolor, tmp_string);

	sprintf(tmp_string, "应用生产商ID：%04x\n",menu_id);
	ShowHz(x+10, y+5+90, 24, whitecolor, tmp_string);

	app_name[name_len]='\0';
	sprintf(tmp_string, "应用名称：%s\n",app_name);
	ShowHz(x+10, y+5+120, 24, whitecolor, tmp_string);
#endif
#if LOG_APPINFO_CLIENT_API
	sprintf(logstr, "[%d]UTI卡设备的返回的应用信息：\n",Module_Index);
	AddLogString(logstr);

	sprintf(logstr, "应用类型：%04x\n",app_type);
	AddLogString(logstr);

	sprintf(logstr, "应用提供商ID：%04x\n",app_menu);
	AddLogString(logstr);

	sprintf(logstr, "应用生产商ID：%04x\n",menu_id);
	AddLogString(logstr);

	app_name[name_len]='\0';
	sprintf(logstr, "应用名称：%s\n",app_name);
	AddLogString(logstr);
#endif
}
