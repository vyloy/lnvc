package com.lorent.lvmc.controller;

import java.util.Map;
import java.util.Set;

import com.lorent.lvmc.ui.VideoSetupDialog;
import com.lorent.lvmc.util.ConfigUtil;
import com.lorent.lvmc.util.Constants;
import com.lorent.lvmc.util.DataUtil;
import com.lorent.lvmc.util.StringUtil;
import com.lorent.lvmc.util.VideoAudioSetupUtil;
import com.lorent.util.LCCUtil;

public class VideoAudioSetupController extends BaseController {

	
	public void setVideoEquimentPara(Map<String,String> paras) throws Exception{
		if(paras.isEmpty()){
			return;
		}
		Set<String> keys = paras.keySet();
		for(String key:keys){
			if(key.equals(Constants.VideoParam.VideoEquipment.toString())){
				LCCUtil.getInstance().setCamera(Integer.parseInt(paras.get(key)));
				ConfigUtil.setProperty(key, paras.get(key));
			}
		}
	}
	
	public void setVideoParas(Map<String,String> paras,boolean isSave) throws Exception{
		if(paras.isEmpty()){
			return;
		}
		int vw = 0;
		int vh = 0;
		String pixType = null;
		int fps = 0;
		int bitRate = 0;
		Set<String> keys = paras.keySet();
		for(String key:keys){
			if(key.equals(Constants.VideoParam.PixelValue.toString())){
				String pixelValue = paras.get(key);
//				int w = Integer.parseInt(pixelValue.split("\\*")[0]);
//				int h = Integer.parseInt(pixelValue.split("\\*")[1]);
//				LCCUtil.getInstance().doSetVideoSize(w, h);
//				ConfigUtil.setProperty("videoheight", String.valueOf(h));
//				ConfigUtil.setProperty("videowidth", String.valueOf(w));
				vw = Integer.parseInt(pixelValue.split("\\*")[0]);
				vh = Integer.parseInt(pixelValue.split("\\*")[1]);
			}else if(key.equals(Constants.VideoParam.VideoBitrate.toString())){
				String bandWidth = paras.get(key);
//				int bw = (int)Double.parseDouble(bandWidth);
				bitRate = (int)Double.parseDouble(bandWidth);
//				LCCUtil.getInstance().doSetVideoBitrate(bw);
//				ConfigUtil.setProperty("VideoBitrate", String.valueOf(bw));
			}else if(key.equals(Constants.VideoParam.PixelType.toString())){
				pixType = paras.get(key);
			}else if(key.equals(Constants.VideoParam.FrameRate.toString())){
				fps = Integer.parseInt(paras.get(key));
			}
		}
		LCCUtil.getInstance().setVideoProperty(fps, bitRate, vw, vh);
		if(isSave){
//			if(paras.get(Constants.VideoParam.PixelType.toString()).equals(StringUtil
//				.getUIString("video.pixel.highquality"))){
//				ConfigUtil.setProperty("videoheight", String.valueOf(vh));
//				ConfigUtil.setProperty("videowidth", String.valueOf(vw));
//			}else{
//				ConfigUtil.setProperty("svideoheight", String.valueOf(vh));
//				ConfigUtil.setProperty("svideowidth", String.valueOf(vw));
//			}
			VideoAudioSetupUtil.SetVideoPixelConfigAction action = (VideoAudioSetupUtil.SetVideoPixelConfigAction)VideoAudioSetupUtil.getAction(paras.get(Constants.VideoParam.PixelType.toString()), VideoAudioSetupUtil.SetVideoPixelConfigAction.class.getName());
			action.execute(vw, vh);
			ConfigUtil.setProperty(Constants.VideoParam.PixelType.toString(), paras.get(Constants.VideoParam.PixelType.toString()));
			ConfigUtil.setProperty(Constants.VideoParam.VideoBitrate.toString(), paras.get(Constants.VideoParam.VideoBitrate.toString()));
			ConfigUtil.setProperty(Constants.VideoParam.FrameRate.toString(), paras.get(Constants.VideoParam.FrameRate.toString()));
			VideoSetupDialog.setParaMap();
		}
	}
	
	public void setAudioParas(Map<String,String> paras) throws Exception{
		if(paras.isEmpty()){
			return;
		}
		Set<String> keys = paras.keySet();
		for(String key:keys){
			ConfigUtil.setProperty(key, paras.get(key));
			if(key.equals(Constants.AudioParam.MicEquipment.toString())){
				LCCUtil.getInstance().setMic(Integer.parseInt(paras.get(key)));
			}else if(key.equals(Constants.AudioParam.NarratorEquipment.toString())){
				LCCUtil.getInstance().setPlayBack(Integer.parseInt(paras.get(key)));
			}else if(key.equals(Constants.AudioParam.MicVolume.toString())){
				int micVolume = Integer.parseInt(paras.get(key));
				if(micVolume==0){
					LCCUtil.getInstance().setMuteMic(true);
				}else{
					LCCUtil.getInstance().setMicVolume(micVolume);
				}
			}else if(key.equals(Constants.AudioParam.NarratorVolume.toString())){
				int narratorVolume = Integer.parseInt(paras.get(key));
				if(narratorVolume==0){
					LCCUtil.getInstance().setMutePlayback(DataUtil.getLoginInfo().getConfno(), true);
				}else{
					LCCUtil.getInstance().setPlaybackVolume(DataUtil.getLoginInfo().getConfno(), narratorVolume);
				}
			}
		}
	}
}
