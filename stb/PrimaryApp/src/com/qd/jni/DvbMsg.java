package com.qd.jni;

import lorent.stb.tv.MyServer;

import android.os.Bundle;
import android.os.Message;
public class DvbMsg {	
	
	private static final String TAG="DvbMsg.java";	
	private MyServer receiver; 
	
	public DvbMsg(MyServer receiver)
	{
		  this.receiver=receiver;
		   
	}
	
	public void msgInit(){
		JniLoad.InitDVB(this);
	}
	
	public void callbackInfo(int type,int arg1,int arg2){
		   
		  
		   String[] args =new String[]{arg1+"",arg2+""};
		   Message msg=receiver.getMyHandler().obtainMessage();
		   Bundle b =new Bundle();
		   b.putStringArray(type+"", args);
		   msg.setData(b);
		   receiver.getMyHandler().sendMessage(msg);
		 
		   System.out.println("-----"+TAG+"   callbackInfo-----------"+type+"  "+arg1+"  "+arg2);
		  
		   
	}
	
	
	
	
  

}
