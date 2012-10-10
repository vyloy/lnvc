package com.lorent.lvmc.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VideoAudioSetupUtil {

	private static Map<String,HashMap<String,Object>> map = new ConcurrentHashMap<String,HashMap<String,Object>>();
	
	static{
		VideoAudioSetupUtil util = new VideoAudioSetupUtil();
		HashMap<String,Object> highQualityPerforms = new HashMap<String,Object>();
		highQualityPerforms.put(SetAction.class.getName(), util.new SetHighQualityAction());
		highQualityPerforms.put(GetAction.class.getName(), util.new GetHighQualityAction());
		HashMap<String,Object> standardQualityPerforms = new HashMap<String,Object>();
		standardQualityPerforms.put(SetAction.class.getName(), util.new SetStandardQualityAction());
		standardQualityPerforms.put(GetAction.class.getName(), util.new GetStandardQualityAction());
		map.put(StringUtil.getUIString("video.pixel.highquality"), highQualityPerforms);
		map.put(StringUtil.getUIString("video.pixel.standardquality"), standardQualityPerforms);
	}
	
	public static Object getAction(String key,String className){
		if(key==null || "".equals(key)){
			key = StringUtil.getUIString("video.pixel.standardquality");
		}
		HashMap<String,Object> performs = map.get(key);
		Object obj = performs.get(className);
		return obj;
	}
	
	public abstract class SetAction{
		public abstract void execute(int w,int h);
	}
	
	public class SetHighQualityAction extends SetAction{
		public void execute(int w,int h){
			try{
				ConfigUtil.setProperty("videoheight", String.valueOf(h));
				ConfigUtil.setProperty("videowidth", String.valueOf(w));
			}catch(Exception ex){
				
			}
		}
	}
	
	public class SetStandardQualityAction extends SetAction{
		public void execute(int w,int h){
			try{
				ConfigUtil.setProperty("svideoheight", String.valueOf(h));
				ConfigUtil.setProperty("svideowidth", String.valueOf(w));
			}catch(Exception ex){
				
			}
		}
	}
	
	public abstract class GetAction{
		public abstract int[] execute();
	}
	
	public class GetHighQualityAction extends GetAction{
		public int[] execute(){
			int[] pix = new int[2];
			try{
				pix[0] = ConfigUtil.getIntProperty("videowidth", 640);
				pix[1] = ConfigUtil.getIntProperty("videoheight", 360);
			}catch(Exception ex){
				
			}
			return pix;
		}
	}
	
	public class GetStandardQualityAction extends GetAction{
		public int[] execute(){
			int[] pix = new int[2];
			try{
				pix[0] = ConfigUtil.getIntProperty("svideowidth", 352);
				pix[1] = ConfigUtil.getIntProperty("svideoheight", 288);
			}catch(Exception ex){
				
			}
			return pix;
		}
	}
}
