package com.lorent.video;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class ConfirmDialog {

	public void show(Context context,CharSequence title,DialogInterface.OnClickListener okListener,DialogInterface.OnClickListener cancelListener){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
	    builder.setTitle(title);
	    builder.setIcon(android.R.drawable.ic_dialog_info);
	    builder.setPositiveButton("确定",okListener
//	       new DialogInterface.OnClickListener()
//	       {
//	 
//	           @Override
//	           public void onClick(DialogInterface dialog, int which)
//	           {
//	               new AlertDialog.Builder(DialogTest2.this)
//	                  .setMessage(R.string.okMessage).create().show();
//	 
//	           }
//	       }
	    );
	    builder.setNegativeButton("取消",cancelListener
//	       new DialogInterface.OnClickListener()
//	       {
//	           @Override
//	           public void onClick(DialogInterface dialog, int which)
//	           {
//	               Toast.makeText(DialogTest2.this, "已经取消删除",
//	                  Toast.LENGTH_LONG).show();
//	 
//	           }
//	       }
	    );
	    builder.create().show();
	}
	
}
