package com.lorent.video.util;

import org.xmlrpc.android.XMLRPCClient;

import android.util.Log;

import com.lorent.video.MainActivity;

public class MyXMLRPCClient{
	private XMLRPCClient client;
	int currentPage;
	String category;
	MainActivity activity;
	int pageSize;
	public MyXMLRPCClient(String ip,int currentPage,int pageSize,String category){
		this.currentPage = currentPage;
		this.category = category;
		client = new XMLRPCClient("http://" + ip + ":6090/lcm/lcmRpc");
		Log.i("MyXMLRPCClient", ip);
		this.pageSize = pageSize;
	}
	public Object getResult() throws Exception{
		Object result = null;
		if(category==null||"".equals(category)){
			result = client.callEx("lcmVideo.getVideoClipList",new Object[]{(currentPage-1),pageSize});
		}else{
			result = client.callEx("lcmVideo.getVideoClipList",new Object[]{(currentPage-1),pageSize,category});
		}
		return result;
	}
}
