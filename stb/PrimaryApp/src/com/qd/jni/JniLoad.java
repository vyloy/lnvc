package com.qd.jni;

import com.qd.bean.TunerParam;
import com.qd.bean.EPGInfo;
import com.qd.bean.EventBean;
public class JniLoad
{
	static
	{
		System.loadLibrary("jnidvb");
	}	
	/**
	 * searchChannel method
	 * 
	 */
	
	public static native int InitDVB(Object o);        //获取system/bin/dvb_server服务接口
	public static native int Init();   //搜台初始化
	public static native int Config(int searchType, int freqStart,int freqEnd, int symbolRate, int modulation);//主频点设置
	public static native int Start();          //开始搜索
	public static native int Stop();           //ֹͣ停止搜索
	public static native int Exit();           //去初始化
	public static native TunerParam GetTunerAttribute();
	
	/**
	 * 
	 * EPG  method
	 */
	
	
	public static native int SetFreqset(int freqset,int symbolRate,int qam);//锁频
	public static native int SetAV(int audioPid, int videoPid, int pmtPid, int tsid, int serviceid);
	public static native int DelAV();
	public static native int ConfigEPG(int freqset,int type);          //type:1表示PF	0:表示schedle
	public static native int StartEPG();                         //开始搜索
	public static native int StopEPG();                                    //搜索停止
	public static native int exitEPG();                                     //去初始化
	public static native EPGInfo[] EpgArray(int ServiceId);                  //传递PF信息
	public static native EPGInfo[] ScheduleEpgArray(int serviceId,int day); //传递schedule信息
	public static native String GetShortDescriptor(int serviceId, int EventId);//节目简介
	/*
	 * order method
	 */
	public static native int AddOrder(int ServiceId,int EventId,int OrderWay);
	public static native int DelOrder(int ServiceId,int EventId);
	public static native int DelAll();
	public static native EventBean GetOrderBySeridEid(int ServiceId,int EventId);
	public static native EventBean[]GetOrderInfo();
	public static native int Save();
	public static native int Cancel();
	
	
	/**
	 *                       
	 * pvr method
	 */
	
	public static native int PVRAdd(int serviceid, int EventId, int orderWay);
	public static native int PVRDel(int ServiceId, int EventId);
	public static native int PVRDelAll();
	public static native EventBean PVRGetBySerIdEventId(int ServiceId, int EventId, int orderWay);
	public static native EventBean[] PVRGetAll();
	public static native int PVRSave();
	public static native int PVRCancel();
	public static native int PVRSetMediaType(int videoType,int audioType);
	public static native int PVRSetMediaPID(int pcr,int videoPid,int audioPid);
	public static native int PVRSetMediaName(String fileName);
	public static native int PVRResetMediaFile(String fileName);
	public static native int PVRStart();
	public static native int PVRStop();
	
	/**
	 * nvod method
	 */
	public static native int NVODConfig(int freqNum);
	public static native int NVODStart();
}
