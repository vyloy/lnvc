package com.lorent.video.service;



import java.util.ArrayList;
import java.util.List;

import org.xmlrpc.android.XMLRPCClient;
import org.xmlrpc.android.XMLRPCException;

import android.app.Activity;
import android.util.Log;

import com.lorent.common.dto.LCMVideoClip;
import com.lorent.video.MainActivity;
import com.lorent.video.R;
import com.lorent.video.bean.VideoInfo;
import com.lorent.video.util.MeasureUtil;
import com.lorent.video.util.MyXMLRPCClient;

public class VideoService {

	private int pageSize = 0;
	private XMLRPCClient client;
	private MainActivity activity;
	private String ip;
	private String port;
	
	public VideoService(){}
	
	public VideoService(MainActivity activity) {
		this.activity = activity;
		pageSize = MeasureUtil.getPageSize(activity);//MeasureUtil.getPageSize(activity);//Integer.parseInt(activity.getResources().getText(R.string.pageSize)+"");
		Log.i("pageSize", pageSize + "");
	}

	public List<LCMVideoClip> getVideoInfo(int currentPage) throws Exception{
//    	int startP = (currentPage-1) * pageSize;
//    	int endP = currentPage * pageSize;
    	List<LCMVideoClip> list = new ArrayList<LCMVideoClip>();
    	client = new XMLRPCClient("http://"+activity.ip+":6090/lcm/lcmRpc");
    	Object result = client.callEx("lcmVideo.getVideoClipList",new Object[]{(currentPage-1),pageSize});
    	if(result!=null){
    		Object[] lcmVideoClipbjects = (Object[]) result;
    		if(lcmVideoClipbjects.length>0){
    			for(int i=0;i<lcmVideoClipbjects.length;i++){
    				Log.i("picture", ((LCMVideoClip)lcmVideoClipbjects[i]).getThumbnailUrl());
    				list.add((LCMVideoClip)lcmVideoClipbjects[i]);
    			}
    		}
    	}
    	
//    	for(int i=startP;i<endP;i++){
//    		VideoInfo info = new VideoInfo();
//    		info.setImageUrl(""+i);
//    		info.setTitle("title"+i);
//    		list.add(info);
//    	}
    	return list;
    }
	
	public List<LCMVideoClip> getVideoInfo(int currentPage,String category) throws Exception{
//    	int startP = (currentPage-1) * pageSize;
//    	int endP = currentPage * pageSize;
    	List<LCMVideoClip> list = new ArrayList<LCMVideoClip>();
    	client = new XMLRPCClient("http://"+activity.ip+":6090/lcm/lcmRpc");
    	Object result = client.callEx("lcmVideo.getVideoClipList",new Object[]{(currentPage-1),pageSize,category});
    	if(result!=null){
    		Object[] lcmVideoClipbjects = (Object[]) result;
    		if(lcmVideoClipbjects.length>0){
    			for(int i=0;i<lcmVideoClipbjects.length;i++){
    				Log.i("picture", ((LCMVideoClip)lcmVideoClipbjects[i]).getThumbnailUrl());
    				list.add((LCMVideoClip)lcmVideoClipbjects[i]);
    			}
    		}
    	}
    	return list;
    }
	
	public List<LCMVideoClip> getVideoInfo(MyXMLRPCClient client) throws Exception{
		List<LCMVideoClip> list = new ArrayList<LCMVideoClip>();
    	Object result = client.getResult();
    	if(result!=null){
    		Object[] lcmVideoClipbjects = (Object[]) result;
    		if(lcmVideoClipbjects.length>0){
    			for(int i=0;i<lcmVideoClipbjects.length;i++){
    				Log.i("picture", ((LCMVideoClip)lcmVideoClipbjects[i]).getThumbnailUrl());
    				list.add((LCMVideoClip)lcmVideoClipbjects[i]);
    			}
    		}
    	}
    	return list;
	}
	
	
}
