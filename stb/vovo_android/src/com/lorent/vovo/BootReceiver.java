package com.lorent.vovo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lorent.LCCUtil;

public class BootReceiver extends BroadcastReceiver  {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent.getAction().equals( Intent.ACTION_BOOT_COMPLETED )){ 
			Intent in = new Intent(context, LCCUtil.class);
			in.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );  
			context.startService(in);
		}
	}

}
