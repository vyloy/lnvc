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
	
}
