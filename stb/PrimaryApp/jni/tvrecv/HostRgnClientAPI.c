#include "HostIncludeApi.h"

#define LOG_REGIONALITY_CLIENT		0

/******************************************/
/* Regional configuration, following are descriptor tags */

#define NETWORK_CONFIG 0x80
#define AUDIO_TRACK_CONFIG 0x81
#define AUDIO_VOLUME_CONFIG 0x82
#define TDT_DES_CONFIG 0x83
#define EPG_CONFIG 0x84
#define NIT_CONFIG 0x85
#define FREQLIST_CONFIG 0x86

#define TUNER_DESCRIPTOR 0x90

enum TTunner_Type{
	DVB_C,
	DVB_S,
	DVB_T,
	DMB_TH
};

typedef struct _TChannInfo
{
	//BOOL bValid ; // i think it's not necessay , so omit it.
	UTIU16 Logic_No;
	UTIU16 TS_ID;
	UTIU16 NET_ID;
	UTIU16 SER_ID;
}TChannInfo;

TChannInfo ChannInfo[1024];

/*
	please according to the protocal to parse tuner descriptor . 
*/
static void ParseTunnerDescriptor(UTIU8 *data, UTIU32 len)
{
	/**** DVB-C parameter ****/
	UTIU32 freq;
	UTIU32 rate;
	UTIU8 modulation;
	UTIU8 FEC_outter;
	UTIU8 FEC_inner;
	
	/**** DVB-S parameter ****/
	UTIU16 orbital_position;
	UTIU8 west_east_flag;
	UTIU8 polarization;
	
	/**** DVB-T parameter ****/
	UTIU8 Bandwidth;
	UTIU8 Constellation;
	UTIU8 hierarchy_information;
	UTIU8 code_rate_HP_stream;
	UTIU8 code_rate_LP_stream;
	UTIU8 guard_interval;
	UTIU8 transmission_mode;
	UTIU8 other_frequency_flag;
	
	/**** Tuner Status parameter ****/
	UTIU8 tuner_type;
	//UTIU8 lock_flag;
	//UTIU32 strength;
	//UTIU32 quality;

	if ( data[0] != TUNER_DESCRIPTOR)
	{
		PRINTF("[ParseTunnerDescriptor]: not tunner descriptor pdata= %x.\n", data[0]);
	}
	else
	{
		tuner_type = (data[1] ) && 0xc0;

		switch(tuner_type)
		{
		/*NOTE:	DVB_C type has been verified ok, other type please reassure it's correctance.*/
		case DVB_C:
		{
			UTIS32 fre0 =0; 
			UTIS32 symbolrate0 =0; 
			UTIS32 symbolrate1=0;
			
			freq=(data[2] *100)+data[3] ; //整数部分
			fre0=(data[4] *100)+data[5] ; //小数点部分
			PRINTF(" freq>>>>>>>>>>>>%d.%d \n",freq, fre0);	
			//please refer to protocal, FEC_outer is the 5th
			FEC_outter=(data[7] ) &0x0f;
			modulation=(data[8] ) ;

			symbolrate0=(data[9] *100)+data[10] ; //整数部分
			symbolrate1=(data[11] *100)+(data[12]&0xf0) ; //小数点部分
			PRINTF("symbolrate >>>>>>>>>>>>%d.%d \n",symbolrate0,symbolrate1);	

			FEC_inner=data[12] & 0x0f;

			PRINTF(" freq =%d FEC_outter =%d modulation=%d FEC_inner=%d \n",freq>>16, FEC_outter,modulation,FEC_inner);
			break;
		}
		case DVB_S:

			freq=((UTIU32)data[6]<<24)+((UTIU32)data[7]<<16)+((UTIU32)data[8]<<8)+data[9];
			orbital_position=((UTIU16)data[10]<<8)+data[11];
			
			west_east_flag=((data[12]&0x80)>>7);
			polarization=((data[12]&0x60)>>5);
			modulation=(data[12]&0x1f);
			rate=((UTIU32)data[13]<<24)+((UTIU32)data[14]<<16)+((UTIU32)data[15]<<8)+data[16];
			FEC_inner=rate&0x0f;
			rate=(rate>>4);

			break;
		case DVB_T:
			freq=((UTIU32)data[6]<<24)+((UTIU32)data[7]<<16)+((UTIU32)data[8]<<8)+data[9];
			Bandwidth=((data[10]>>5)&0x07);
			Constellation=((data[11]>>6)&0x03);
			hierarchy_information=((data[11]>>3)&0x07);
			code_rate_HP_stream=(data[11]&0x07);
			code_rate_LP_stream=((data[12]>>5)&0x07);
			guard_interval=((data[12]>>3)&0x03);
			transmission_mode=((data[12]>>1)&0x03);
			other_frequency_flag=(data[12]&0x01);
						
			break;
		case DMB_TH:
			freq=((UTIU32)data[6]<<24)+((UTIU32)data[7]<<16)+((UTIU32)data[8]<<8)+data[9];
			Bandwidth=((data[10]>>5)&0x07);
			modulation=(data[12]&0x1f);
			guard_interval=((data[12]>>3)&0x06);
			other_frequency_flag=(data[12]&0x01);

			break;
		}
	}
}

void UTIRgnConfigDescriptor(UTIS32 Module_Index, UTIU8 *data, UTIU32 len)
{
	  UTIS32 iNoOfBytesParsed=0;
	  UTIU8 *ptrSection = data;
	  UTIS32 totlelen = len;
	  UTIS32 tag=0;
	  //UTIU32  LenField=0;
	  UTIU32 i;
	  /*************/
     	  UTIS32 network_type=0;
      	  UTIS32 audio_track=0;
   	  //UTIS32 audio_volume=0;
   	  UTIS32 TDT_err=0;
   	  UTIS32 is_eit_schedule_on_single_frequency=0;
   	  UTIU32 frequency_num=0;
   	  UTIS32 frequency_list[110];
	  UTIS32 total_levels=0;
	  UTIS32 initial_level=0;

   	  for(i=0;i<len;i++)
   	  {
   		PRINTF("%02x ",data[i]);
   		if(((i+1)%32)==0)
   		  PRINTF("\n");
   	  }
   	  PRINTF("\n");
  
	  while (iNoOfBytesParsed < totlelen)
	  {
	 	tag  = ptrSection [ 0 ];
		PRINTF("[UTIRgnConfigDescriptor]: tag =%x \n", tag);
		switch(tag)
		{
		  case NETWORK_CONFIG:
			ptrSection = ptrSection + 2;
			network_type = * ptrSection++;
			iNoOfBytesParsed = ptrSection - data;
			continue;
		  case AUDIO_TRACK_CONFIG :
			ptrSection = ptrSection + 2;
		  	audio_track = * ptrSection++;
			iNoOfBytesParsed = ptrSection - data;
			continue;
   		 case AUDIO_VOLUME_CONFIG :
   		  {
   			ptrSection = ptrSection + 2;		  	
   			total_levels = *ptrSection++;
   			initial_level = *ptrSection++;
   			iNoOfBytesParsed = ptrSection - data;
   		  }
   		  continue;		
   		  case TDT_DES_CONFIG :
   			ptrSection = ptrSection + 2;		  	
   		  	 TDT_err= *ptrSection++;
   			iNoOfBytesParsed = ptrSection - data;			 
   			 continue;		
   		  case EPG_CONFIG :
   			ptrSection = ptrSection + 2;		  	
   		  	is_eit_schedule_on_single_frequency = *ptrSection++;
   			if (is_eit_schedule_on_single_frequency )
   			{
   				ParseTunnerDescriptor(ptrSection, 0);//TODO:
				ptrSection+=34;//according to the protocal
			}   
   			iNoOfBytesParsed = ptrSection - data;			 
   			continue;						
   		  case NIT_CONFIG :
   			ptrSection = ptrSection + 2;		  	
   			ParseTunnerDescriptor(&ptrSection[0], 0);//TODO:
			ptrSection+=34;//according to the protocal
   			iNoOfBytesParsed = ptrSection - data;			 			
   			continue;		
   		  case FREQLIST_CONFIG :
   			{
				ptrSection++;
   				//Decode_len_field(ptrSection, &LenField);//fix to 16 bit.
   				ptrSection = ptrSection + 2;//LenField;
   				frequency_num = *ptrSection++;
   				for (i=0;i<frequency_num ;i++)
   				{
   					frequency_list[i] = ((UTIU32)ptrSection[4*i]<<24)+((UTIU32)ptrSection[4*i+1]<<16)+((UTIU32)ptrSection[4*i+2]<<8)+(ptrSection[4*i+3]);
   				}
   		  	}
			ptrSection += frequency_num * 4;		  
   			iNoOfBytesParsed = ptrSection - data;			 			
   			continue;
		default:
			PRINTF("Please check the code , something wrong , ptrSection [ 0 ] =%x \n",ptrSection [ 0 ]);
			ptrSection ++;
			continue;
   		}
 	 }
}

void UTIRgnChannelListDescriptor(UTIS32 Module_Index, UTIU8 *data, UTIU32 len)
{
	UTIU32 channel_num=0;
	UTIU8 *pdata=data;
	UTIU32 i;

	for(i=0;i<len;i++)
	{
		PRINTF("%02x ",data[i]);
		if(((i+1)%32)==0)
			PRINTF("\n");
	}
	PRINTF("\n");

	channel_num = ((UTIU32)pdata[0]<<24) + ((UTIU32)pdata[1]<<16) + ((UTIU32)pdata[2]<<8) + pdata[3];
	channel_num = (channel_num);///sizeof(TChannInfo);
	PRINTF("in ParseRegional_ChannelListDescriptor():...channel_num = %d \n",channel_num);	
	pdata = data + 4 ;
	for (i=0;i<channel_num;i++)
	{
		ChannInfo[i].NET_ID	=  (((UTIU16)pdata[i*8])<<8)      + (pdata[i*8+1]);
		ChannInfo[i].TS_ID  	=  (((UTIU16)pdata[i*8+2])<<8)  + (pdata[i*8+3]);
		ChannInfo[i].SER_ID	=  (((UTIU16)pdata[i*8+4])<<8)  + (pdata[i*8+5]);
		ChannInfo[i].Logic_No   =  (((UTIU16)pdata[i*8+6])<<8)  + (pdata[i*8+7]);
		PRINTF("[%d]---Logic_No=%x   ts_id=%x net_id=%x ser_id = %x \n ",i, ChannInfo[i].Logic_No ,ChannInfo[i].TS_ID,ChannInfo[i].NET_ID,ChannInfo[i].SER_ID);
	}
}

