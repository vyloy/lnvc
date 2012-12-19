package com.lorent.video.util;

import java.io.UnsupportedEncodingException;

public class StringUtil {

	public static String encodeUrl(String url){
		int sidx = url.lastIndexOf("/");
    	int eidx = url.lastIndexOf(".");
    	String picName = url.substring(sidx + "/".length(),eidx);
    	String encodeName = picName;
		try {
			encodeName = java.net.URLEncoder.encode(picName,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String encodeUrl = url.replaceFirst(picName, encodeName);
    	return encodeUrl;
	}
	
	public static String changeIPForUrl(String url,String ip){
		String result = null;
		String sTag = "://";
		int sidx = url.indexOf(sTag);
		if(sidx!=-1){
			int eidx = url.indexOf(":", sidx+sTag.length());
			if(eidx!=-1){
				result = url.substring(0, sidx+sTag.length()) + ip + url.substring(eidx);
			}else{
				eidx = url.indexOf("/", sidx+sTag.length());
				if(eidx!=-1){
					result = url.substring(0, sidx+sTag.length()) + ip + url.substring(eidx);
				}else{
					result = url.substring(0, sidx+sTag.length()) + ip;
				}
			}
		}else{
			result = url;
		}
		return result;
	}
	
	public static void main(String[] args){
		System.out.println(changeIPForUrl("http://119.145.165.218:8800/20121203133832_760_480P.mp4","10.168.130.20"));
	}
	
	public static int convertCharToInt(String text){
		int result = 0;
		try{
			result = Integer.parseInt(text);
		}catch(Exception ex){
			
		}
		return result;
	}
	
}
