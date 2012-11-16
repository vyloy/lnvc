package com.qd.jni;

import android.view.Surface;



public class JniPlayer
{	
	static
	{
		//System.loadLibrary("tv");
	}
	
	
	
	/**
	 * player
	 */
	/*
	//播放
	public static native int changeProgram(int fre,int vid,int vtype,int aid,int atype,int tsid,int pmt,int serverid);
	
	//public static native int setVideoSurface(Object obj);
	
	//public static native int changeProgram(int freqency, int modulation, int symboRate, int org_net_id, int net_id, int pmtPid, 
	//		int videoPid, int audioPid, int pcrPid, int serviceId, int tsId);
	
	public static native int stopProgram();   //ֹͣ停止播放
    public static native int setWindowSize(int x,int y, int a, int b); //设置视频窗坐标和大小
    */
	public static int setVideoSurface(Object obj) {
		return 0;
	}
	
	public static int changeProgram(int freqency, int modulation, int symboRate, int org_net_id, int net_id, int pmtPid, 
			int videoPid, int audioPid, int pcrPid, int serviceId, int tsId) 
	{
		return 0;
	}
	
	public static int stopProgram()
	{
		return 0;//ֹͣ停止播放
	}
    public static int setWindowSize(int x,int y, int a, int b) 
    {
    	return 0;
    }
    
}
