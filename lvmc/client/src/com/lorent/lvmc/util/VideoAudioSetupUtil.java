package com.lorent.lvmc.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.lorent.lvmc.ui.VideoPixelButtonSetAction;
import com.lorent.lvmc.ui.HighQualityVideoPixelButtonSetAction;
import com.lorent.lvmc.ui.StandardQualityVideoPixelButtonSetAction;

public class VideoAudioSetupUtil {

	private static Map<String,HashMap<String,Object>> map = new ConcurrentHashMap<String,HashMap<String,Object>>();
	
	static{
		VideoAudioSetupUtil util = new VideoAudioSetupUtil();
		HashMap<String,Object> highQualityPerforms = new HashMap<String,Object>();
		highQualityPerforms.put(SetVideoPixelConfigAction.class.getName(), util.new SetHighQualityVideoPixelConfigAction());
		highQualityPerforms.put(GetVideoPixConfigAction.class.getName(), util.new GetHighQualityVideoPixelConfigAction());
		highQualityPerforms.put(VideoPixelButtonSetAction.class.getName(), new HighQualityVideoPixelButtonSetAction());
		
		HashMap<String,Object> standardQualityPerforms = new HashMap<String,Object>();
		standardQualityPerforms.put(SetVideoPixelConfigAction.class.getName(), util.new SetStandardQualityVideoPixelConfigAction());
		standardQualityPerforms.put(GetVideoPixConfigAction.class.getName(), util.new GetStandardQualityVideoPixelConfigAction());
		standardQualityPerforms.put(VideoPixelButtonSetAction.class.getName(), new StandardQualityVideoPixelButtonSetAction());
		
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
	
	public abstract class SetVideoPixelConfigAction{
		public abstract void execute(int w,int h);
	}
	
	public class SetHighQualityVideoPixelConfigAction extends SetVideoPixelConfigAction{
		public void execute(int w,int h){
			try{
				ConfigUtil.setProperty("videoheight", String.valueOf(h));
				ConfigUtil.setProperty("videowidth", String.valueOf(w));
			}catch(Exception ex){
				
			}
		}
	}
	
	public class SetStandardQualityVideoPixelConfigAction extends SetVideoPixelConfigAction{
		public void execute(int w,int h){
			try{
				ConfigUtil.setProperty("svideoheight", String.valueOf(h));
				ConfigUtil.setProperty("svideowidth", String.valueOf(w));
			}catch(Exception ex){
				
			}
		}
	}
	
	public abstract class GetVideoPixConfigAction{
		public abstract int[] execute();
	}
	
	public class GetHighQualityVideoPixelConfigAction extends GetVideoPixConfigAction{
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
	
	public class GetStandardQualityVideoPixelConfigAction extends GetVideoPixConfigAction{
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
