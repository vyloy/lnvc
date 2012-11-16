package com.lorent.vovo.utils;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ComponentName;
import android.content.Context;

public class ProcessUtil {
	public static boolean isAppOnForeground(Context context, String packageName) {
		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> appProcesses = activityManager
				.getRunningAppProcesses();
		if (appProcesses == null) {
			return false;
		}
		for (RunningAppProcessInfo appProcess : appProcesses) {
			if (appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND
					&& appProcess.processName.equals(packageName)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isRunningForeground (Context context, String packageName)
	{
	    ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
	    ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
	    String currentPackageName = cn.getPackageName();
	    if(currentPackageName != null && currentPackageName.equals(packageName))
	    {
	        return true ;
	    }
	    return false ;
	}
}
