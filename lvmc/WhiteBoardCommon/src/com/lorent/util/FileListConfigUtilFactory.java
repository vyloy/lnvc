package com.lorent.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import com.lorent.commonconfig.ConfigUtil;


public class FileListConfigUtilFactory {

	private static Logger log = Logger.getLogger(FileListConfigUtilFactory.class);
	public static Map<String,ConfigUtil> map = new HashMap<String,ConfigUtil>();
	private static Object lock1 = new Object();
	private static Object lock2 = new Object();
	public static final String DELETE_CONF_NAME = "_deleting";
	
	public static ConfigUtil getFileListConfUtilByMeetingID(String meetingId,IoSession session){
		synchronized(lock1){
			return getConfigUtilObj(meetingId,session,0);
		}
	}
	
	
	public static ConfigUtil getDeleteFileListConfUtilByMeetingID(String meetingId,IoSession session){
		synchronized(lock2){
			return getConfigUtilObj(meetingId,session,1);
		}
	}
	
	/**
	 * 
	 * @param meetingId 会议ID
	 * @param session
	 * @param type  0表示获取存在文件列表；1表示获取即将删除文件列表
	 * @return
	 */
	public static ConfigUtil getConfigUtilObj(String meetingId,IoSession session,int type){
		String apppath = (String) session.getAttribute("AppPath");
        String filepath = apppath  + "/files/" + meetingId + "/" + "conf/";
        File dir = new File(filepath);
        if(!dir.exists()){
        	dir.mkdirs();
        }
        String key = meetingId;
        if(type==0){
        	filepath = filepath + meetingId + ".conf";
        }else if(type==1){
        	filepath = filepath + meetingId + DELETE_CONF_NAME + ".conf";
        	key = meetingId + DELETE_CONF_NAME;
        }
		if(map.containsKey(key)){
			if(map.get(key)!=null){
				return map.get(key);
			}
		}
        
        ConfigUtil configUtil = new ConfigUtil(filepath,false);
        map.put(key, configUtil);
        return configUtil;
	}
	
	public static ConfigUtil getDeleteFileListConfUtilByMeetingID(String meetingId){
		return getConfigUtilObj(meetingId,1);
	}
	
	public static ConfigUtil getConfigUtilObj(String meetingId,int type){
		String key = meetingId;
		if(type==1){
			key = meetingId + DELETE_CONF_NAME;
		}
		if(map.containsKey(key)){
			if(map.get(key)!=null){
				return map.get(key);
			}
		}
		return null;
	}
	
}
