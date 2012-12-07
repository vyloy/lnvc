package com.lorent.video.util;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

public class NetWorkUtil {
	
	public static String sessionId;
	
	public static void setSessionId(String ip){
		sessionId = getInternetContent("http://"+ip+":6090/videoserver/video.jsp");
//		Log.i("livemethod", sessionId+"");
	}
	
	public static void clearSessionId(String ip){
		getInternetContent("http://"+ip+":6090/videoserver/logout.jsp?jsessionid="+String.valueOf(sessionId));
//		Log.i("livemethod", "jsessionid="+String.valueOf(sessionId+""));
	}

	public static String getInternetContent(String urlStr){
		
		URL url = null;
		HttpURLConnection httpConnection = null;
		InputStreamReader in = null;
		try {  
	        //实例化URL  
	        url = new URL(urlStr);  
	        //使用HttpURLConnection打开连接    
	        httpConnection = (HttpURLConnection) url.openConnection();
	        httpConnection.setReadTimeout(4000);
	        //得到输入流对象  
	        in = new InputStreamReader(httpConnection.getInputStream());  
	        BufferedReader bufReader = new BufferedReader(in);   
	        String lineStr = "";  
	        String resultStr = "";  
	        while ((lineStr = bufReader.readLine())!=null) {  
	            resultStr += lineStr;                  
	        }  
	          
	        Log.i("sessionid", resultStr);  
	        return resultStr;
	    } catch (Exception e) {  
	        e.printStackTrace();  
	        return null;
	    }finally{
	    	if(httpConnection!=null){
	    		httpConnection.disconnect();
	    	}
	    	if(in!=null){
	    		try {
					in.close();
				} catch (Exception e) {
				}
	    	}
	    }
	}
}
