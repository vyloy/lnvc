package com.lorent.video.util;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

public class ShareAppUtil {

	/** 
	 * 查询手机内所有支持视频播放的应用 
	 * @param context 
	 * @return 
	 */  
	public static List<ResolveInfo> getVideoShareApps(Context context){  
	    List<ResolveInfo> mApps = new ArrayList<ResolveInfo>();    
	    Intent intent=new Intent(Intent.ACTION_VIEW,null);    
	    intent.addCategory(Intent.CATEGORY_DEFAULT);    
	    intent.setType("video/*");    
	    PackageManager pManager = context.getPackageManager();  
	    mApps = pManager.queryIntentActivities(intent,PackageManager.GET_ACTIVITIES);    
	      
	    return mApps;    
	}
	
}
