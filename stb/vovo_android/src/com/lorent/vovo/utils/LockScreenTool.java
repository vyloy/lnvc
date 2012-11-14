package com.lorent.vovo.utils;

import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;

public class LockScreenTool {
	
	private static final String TAG = "LockScreenTool";
	private static WakeLock mWakeLock;
	/** 屏幕加锁*/
	public static void  lockScreen(Context context){
		Log.i(TAG, "lockScreen");
		if (mWakeLock == null) {
			PowerManager pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
			/*mWakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK,
					"XYTEST");*/
			mWakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "Gank");   
			mWakeLock.acquire();
		}
	}
	
	/**屏幕解锁*/
	public static void cancelLock(){
		Log.i(TAG, "cancelLock");
		if (mWakeLock != null) {
			mWakeLock.release();
			mWakeLock = null;
		}
	}
	
	

}
