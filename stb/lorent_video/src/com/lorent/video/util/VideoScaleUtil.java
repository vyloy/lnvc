package com.lorent.video.util;

public class VideoScaleUtil {

	public static int[] computeScale(int screenW,int screenH,int videoW,int videoH){
		int result[] = new int[2];
		if(videoW==0 || videoH==0){
			result[0] = videoW;
			result[1] = videoH;
			return result;
		}
		double ws = ((float)screenW)/videoW;
		double hs = ((float)screenH)/videoH;
		if(ws>hs){
			videoH = screenH;
			videoW = (int)(videoW * hs);
		}else{
			videoW = screenW;
			videoH = (int)(videoH * ws);
		}
		result[0] = videoW;
		result[1] = videoH;
		return result;
	}
	
}
