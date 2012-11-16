// HostTunerCtrlClient.h: interface for the CHostTunerCtrlClient class.
//
//////////////////////////////////////////////////////////////////////

#ifndef _HOSTTUNERCTRLCLIENT_H_
#define _HOSTTUNERCTRLCLIENT_H_

#include "HostPlatForm.h"

enum
{
	Tuner_DVB_C = 0, /*DVB-C*/
	Tuner_DVB_S,     /*DVB-S*/
	Tuner_DVB_T,     /*DVB-T*/
	Tuner_DMB_T,     /*DMB-T*/
	Tuner_DMB_TH,    /*DMB-TH*/
	Tuner_ADTB_T     /*ADTB-T*/
};

/*DVB-C的输入参数*/
typedef struct tuner_dvbc_s
{
	UTIU32 Frequency;   /*频率*/
	UTIU16 Reserved;
	UTIU8 FEC_outer;  /*前向纠错外码*/
	UTIU8 Modulation; /*调制方式*/
	UTIU32 Symbol_rate; /*符号率*/
	UTIU8 FEC_inner;  /*前向纠错内码*/
}TUNER_DVB_C_PARAM;

/*DVB-S的输入参数*/
typedef struct tuner_dvbs_s
{
	/*Tuner*/
	UTIU32 Frequency;          /*频率*/
	UTIU16 orbital_position; /*轨道位置*/
	UTIU8 west_east_flag;    /*东西标志*/
	UTIU8 Polarization;      /*极化方式*/
	UTIU8 Modulation;        /*调制方式*/
	UTIU32 Symbol_rate;        /*符号率*/
	UTIU32 FEC_inner;          /*前向纠错内码*/
	/*LNB*/
	UTIU8 LNBPower;          /*LNB供电开关*/
	UTIU8 u22KHZ;            /*F22K开关*/
	UTIU8 UDiSEqCPort;
	UTIU8 Value_Burst;
	UTIU32 LNBLOFLowBand_Freq; /*低段参考频率*/
	UTIU32 LNBLOFHighBand_Freq;/*高段参考频率*/
	UTIU32 LNBLOFHiLoSW_Freq;  /*LNB开关参考频率*/
}TUNER_DVB_S_PARAM;

/*DVB-T的输入参数*/
typedef struct tuner_dvbt_s
{ 
	UTIU32 centre_frequency;       /*中心频率*/
	UTIU8 Bandwidth;             /*带宽*/
	UTIU8 Reserved1;
	UTIU8 Constellation;         /*星座*/
	UTIU8 hierarchy_information; /*分层信息*/
	UTIU8 code_rate_HP_stream;   /*高优先级层的码率*/
	UTIU8 code_rate_LP_stream;   /*低优先级层的码率*/
	UTIU8 guard_interval;        /*保护间隔*/
	UTIU8 transmission_mode;     /*传输模式*/
	UTIU8 other_frequency_flag;  /*其他频率标志*/
	UTIU32 Reserved2;
}TUNER_DVB_T_PARAM;

/*DMB-T的输入参数*/
typedef struct tuner_dmbt_s
{
	UTIU8 Reserved;
}TUNER_DMB_T_PARAM;

/*DMB-TH的输入参数*/
typedef struct tuner_dmbth_s
{ 
	UTIU32 centre_frequency;               /*中心频率*/
	UTIU8 Bandwidth;                     /*带宽*/
	UTIU8 Reserved1;
	UTIU8 modulation;                    /*内载波解调方式*/
	UTIU8 guard_interval;                /*保护间隔*/
	UTIU8 other_frequency_flag;          /*其他频率标志*/
	UTIU8 Forward_error_correction_rate; /*向前纠错率*/
	UTIU8 Time_de_interleaver;           /*时域交织深度*/
	UTIU8 Reserved2;
	UTIU32 Reserved3;
}TUNER_DMB_TH_PARAM;

/*ADTB-T的输入参数*/
typedef struct tuner_adtbt_s
{
	UTIU32 Centre_frequency; /*中心频率*/
	UTIU8 Modulation;      /*内载波解调方式*/
	UTIU32 Reserved1;
}TUNER_ADTB_T_PARAM;

typedef struct tuner_parm_s
{
	UTIU32 type;
	union
	{
		TUNER_DVB_C_PARAM dvb_c;
		TUNER_DVB_S_PARAM dvb_s;
		TUNER_DVB_T_PARAM dvb_t;
		TUNER_DMB_T_PARAM dmb_t;
		TUNER_DMB_TH_PARAM dmb_th;
		TUNER_ADTB_T_PARAM adtb_t;
	}parms;
}UTI_TUNER_PARAM;


extern UTIU8	g_tunerType;
extern UTIU8	g_tunerLock;
extern UTIU32	g_tunerStrength;
extern UTIU32	g_tunnerQuality;
extern UTIU32	g_tunerErrorRate;
extern UTIU32	g_tunerFreq;

extern UTIU32	g_tunerStatusCallbackTime;
extern UTIU32	g_tunerSetCallbackTime;

/*主机调用*/
UTIS32 UTITunerSet(UTIS32 Module_Index, UTIU8 timeout, UTI_TUNER_PARAM tuner_param);
UTIS32 UTITunerQueryStatus(UTIS32 Module_Index);

/*卡端返回给主机*/
extern void UTITunerSetCallback(UTIS32 Module_Index, UTIU8 status,UTIU32 freq);
extern void UTITunerStatusCallback(UTIS32 Module_Index, UTIU8 type, UTIU8 lock, UTIU32 strength, UTIU32 quality, UTIU32 error_rate,UTIU32 freq);
#endif // #ifndef _HOSTTUNERCTRLCLIENT_H_
