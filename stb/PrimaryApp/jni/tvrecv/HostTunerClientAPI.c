// UTITunerClientAPI.cpp: implementation of the CUTITunerClientAPI class.
//
//////////////////////////////////////////////////////////////////////

#include "HostIncludeApi.h"

UTIU8	g_tunerType				= 1;
UTIU8	g_tunerLock				= 0;
UTIU32	g_tunerStrength			= 0;
UTIU32	g_tunnerQuality			= 0;
UTIU32	g_tunerErrorRate		= 0;
UTIU32	g_tunerFreq				= 0;

UTIU32	g_tunerStatusCallbackTime	= 0;
UTIU32	g_tunerSetCallbackTime		= 0;

void UTITunerStatusCallback(UTIS32 Module_Index, UTIU8 type, UTIU8 lock, UTIU32 strength, UTIU32 quality, UTIU32 error_rate,UTIU32 freq)
{
	{
		PRINTF("UTITunerStatusCallback: type[0x%x],lock[0x%x],strength[0x%x],quality[0x%x],error[0x%x],freq[%d]\n",(UTIS32)type, (UTIS32)lock, strength, quality,error_rate,freq);
	}

	g_tunerType		= type;
	g_tunerLock		= lock;
	g_tunerStrength	= strength;
	g_tunnerQuality	= quality;
	g_tunerErrorRate= error_rate;
	g_tunerFreq		=freq/1000;
	g_tunerStatusCallbackTime	= 0;
	
}

void UTITunerSetCallback(UTIS32 Module_Index, UTIU8 status,UTIU32 freq)
{
	PRINTF("UTITunerSetCallback: staus is %d freq:%d\n",(UTIS32)status,freq);
	g_tunerSetCallbackTime		= 0;	
}
