package com.lorent.video.util;

import com.lorent.video.R;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

public class DialogUtil {

	public static ProgressDialog newLoadingVideoDialog(Context context,String title,CharSequence msg){
		ProgressDialog dialog = new ProgressDialog(context);
		dialog.setTitle(title);
		dialog.setMessage(msg);
		dialog.setCancelable(true);
		return dialog;
	}
	
	public static void dismissDialog(Dialog dialog){
		if(dialog!=null && dialog.isShowing()){
			dialog.hide();
			dialog.dismiss();
		}
	}
	
}
