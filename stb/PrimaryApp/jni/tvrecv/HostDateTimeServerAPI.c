// HostDateTimeServerAPI.cpp: implementation of the CHostDateTimeServerAPI class.
//
//////////////////////////////////////////////////////////////////////
#include "HostIncludeApi.h"



UTIU32 ResponseMJDUTC(UTIU8 *utc_time_local_offset)
{

	UTIU32 len=0;
	UTIS32 MJD;

	UTIS32 year = 2006;
	UTIS32 month = 7;
	UTIS32 day = 6;
	UTIS32 hour = 13;
	UTIS32 minute = 43;
	UTIS32 second = 11;
	
	UTIS32 L;
	
	if(month == 1 || month == 2)
		L = 1;
	else L = 0;
	
	year -= 1900;

	MJD = 14956 + day + (UTIS32) ((year- L) * 36525/100) + (UTIS32)((month + 1 + L * 12) * 306001/10000);

	utc_time_local_offset[len++]=((MJD>>8)&0xff);
	utc_time_local_offset[len++]=(MJD&0xff);

	utc_time_local_offset[len++]=((hour/10)<<4)+(hour%10);
	utc_time_local_offset[len++]=((minute/10)<<4)+(minute%10);
	utc_time_local_offset[len++]=((second/10)<<4)+(second%10);

	utc_time_local_offset[len++]=0;	//local offset
	utc_time_local_offset[len++]=0;

	return len;
}

UTIU32 UTIMJDUTCRequest(UTIS32 ModuleIndex, UTIU8 response_interval)
{
	UTIU8 utc_time[30];
/*
	UTIS8 logstr[1024];
	UTIS32 loglen=0;

	sprintf(logstr, "主机收到来自UTI卡设备的日期查询请求，请求周期：%d秒。\n",response_interval);
	AddLogString(logstr);
*/
	ResponseMJDUTC(utc_time);

	UTISendDateTime(ModuleIndex, utc_time, 7);

	return 0;
}
