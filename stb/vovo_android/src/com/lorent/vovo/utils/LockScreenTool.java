package com.lorent.vovo.utils;

import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class LockScreenTool {
	
	
	private static WakeLock mWakeLock;
	/** ÆÁÄ»¼ÓËø*/
	public static void  lockScreen(Context context){
		if (mWakeLock == null) {
			PowerManager pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
			/*mWakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK,
					"XYTEST");*/
			mWakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "Gank");   
			mWakeLock.acquire();
		}
	}
	
	/**ÆÁÄ»½âËø*/
	public static void cancelLock(){
		if (mWakeLock != null) {
			mWakeLock.release();
			mWakeLock = null;
		}
	}
	
	

}
