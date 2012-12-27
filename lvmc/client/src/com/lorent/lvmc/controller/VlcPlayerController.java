package com.lorent.lvmc.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.infonode.docking.DockingWindow;
import net.infonode.docking.TabWindow;
import net.infonode.docking.View;

import org.apache.log4j.Logger;

import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;

import com.lorent.common.component.VlcPlayer;
import com.lorent.common.event.VlcPlayerEventAdater;
import com.lorent.lvmc.dto.LoginInfo;
import com.lorent.lvmc.dto.MemberDto;
import com.lorent.lvmc.ui.DockingLayoutMeetingPanel;
import com.lorent.lvmc.util.DataUtil;
import com.lorent.lvmc.util.LvmcUtil;
import com.lorent.lvmc.util.StringUtil;

public class VlcPlayerController extends BaseController {
	private Logger log = Logger.getLogger(VlcPlayerController.class);
	
	static{
//		System.out.println(System.getProperty("user.dir"));
		VlcPlayer.init(System.getProperty("user.dir")+"\\vlc");
	}
	
	private void sendCommand(HashMap command) throws Exception{
		long systemTime = LvmcUtil.getLCMUtil().getSystemTime();
		command.put("systemtime", systemTime);
		List<MemberDto> members = (List<MemberDto>) ControllerFacade.execute("mainController", "getOpenfireMemberList");
		String[] targetusers = new String[members.size()];
		for (int i = 0; i < members.size(); i++) {
			MemberDto memberDto = members.get(i);
			targetusers[i] = memberDto.getName();
		}
		LoginInfo loginInfo=DataUtil.getValue(DataUtil.Key.LoginInfo);
		//广播
		LvmcUtil.getLCMUtil().broadcastVideoCommand(loginInfo.getConfno(), loginInfo.getUsername(), targetusers, command);
	}
	
	public void sendPlayCommand(String filename) throws Exception{
		log.info("sendPlayCommand:"+filename);
		String ext = filename.substring(filename.lastIndexOf(".")+1).toLowerCase();
		LoginInfo loginInfo=DataUtil.getValue(DataUtil.Key.LoginInfo);
		if (ext.equals("mp4")) {
			
			String serverRealFileName = services.getShareFileService().getServerRealFileName(loginInfo.getConfno(), filename);
			log.info(""+serverRealFileName);
			String mrl = "http://"+loginInfo.getServerIP()+":8800/"+loginInfo.getConfno()+"/"+serverRealFileName;
//			startPlay(mrl);
			//广播
			HashMap command = new HashMap();
			command.put("action", "SHOW_AND_PLAYVIDEO");
			command.put("mediaurl", mrl);
			sendCommand(command);
		}
	}
	
	public void startPlay(String mrl) throws Exception{
		log.info("startPlay:"+mrl);
		
		LoginInfo loginInfo=DataUtil.getValue(DataUtil.Key.LoginInfo);
		log.info(mrl);
		
		DockingLayoutMeetingPanel dockingLayoutMeetingPanel = ViewManager.getComponent(DockingLayoutMeetingPanel.class);
        View findInViewMap = dockingLayoutMeetingPanel.findInViewMap("vlcplayerview");
        VlcPlayer vlcPlayer = ViewManager.getComponent(VlcPlayer.class);
        
        //判断是否已经存在界面
        if(findInViewMap != null){
        	if (!vlcPlayer.getMediaMRL().equals(mrl)) {
        		vlcPlayer.setMediaMRL(mrl);
			}
        	findInViewMap.restoreFocus();
        	if (!vlcPlayer.isPlaying()) {
        		vlcPlayer.play();
			}
        }
        else{
        	MediaPlayer mediaPlayer = vlcPlayer.getMediaPlayer();
        	MediaPlayerEventAdapter mediaPlayerEventAdapter = new MediaPlayerEventAdapter(){

				@Override
				public void error(MediaPlayer mediaPlayer) {
					super.error(mediaPlayer);
				}

				@Override
				public void finished(MediaPlayer mediaPlayer) {
					super.finished(mediaPlayer);
					HashMap command = new HashMap();
					command.put("action", "FINISHED");
					try {
						sendCommand(command);
					} catch (Exception e) {
						e.printStackTrace();
						log.error("MediaPlayerEventAdapter.finished", e);
					}
				}

				@Override
				public void paused(MediaPlayer mediaPlayer) {
					super.paused(mediaPlayer);
					
					HashMap command = new HashMap();
					command.put("action", "PAUSED");
					command.put("isPlaying", mediaPlayer.isPlaying());
					try {
						sendCommand(command);
					} catch (Exception e) {
						e.printStackTrace();
						log.error("MediaPlayerEventAdapter.paused", e);
					}
				}

				@Override
				public void playing(MediaPlayer mediaPlayer) {
					super.playing(mediaPlayer);
				}

				@Override
				public void stopped(MediaPlayer mediaPlayer) {
					super.stopped(mediaPlayer);
					HashMap command = new HashMap();
					command.put("action", "STOPED");
					try {
						sendCommand(command);
					} catch (Exception e) {
						e.printStackTrace();
						log.error("MediaPlayerEventAdapter.stopped", e);
					}

				}
        		
				
        	};
        	mediaPlayer.addMediaPlayerEventListener(mediaPlayerEventAdapter);
        	
        	vlcPlayer.setVlcPlayerEventAdater(new VlcPlayerEventAdater(){

				@Override
				public void mediaPlayerPositionChanged(int position) {
					super.mediaPlayerPositionChanged(position);
					HashMap command = new HashMap();
					command.put("action", "POSITION_VALUE_CHANGED");
					command.put("position", position);
					try {
						sendCommand(command);
					} catch (Exception e) {
						e.printStackTrace();
						log.error("VlcPlayerEventAdater.positionSliderValueSetted", e);
					}
				}

				@Override
				public void mediaPlayerTimeChanged(long time) {
					super.mediaPlayerTimeChanged(time);
					HashMap command = new HashMap();
					command.put("action", "TIME_VALUE_CHANGED");
					command.put("time", time);
					try {
						sendCommand(command);
					} catch (Exception e) {
						e.printStackTrace();
						log.error("VlcPlayerEventAdater.mediaPlayerTimeChanged", e);
					}
				}
        	});
        	
        	View whiteboradView = dockingLayoutMeetingPanel.findInViewMap("whiteborad");
            DockingWindow windowParent = whiteboradView.getWindowParent();
            if (windowParent != null && windowParent instanceof TabWindow) {
            	TabWindow tabWindow = (TabWindow) windowParent;
            	vlcPlayer.setMediaMRL(mrl);
//        	dockingLayoutMeetingPanel.addPanel(vlcPlayer, "媒体播放", "vlcplayerview", null,StringUtil.getUIString("VlcPlayerPanel.img"));
            	dockingLayoutMeetingPanel.addPanelToTab(vlcPlayer, "媒体播放", "vlcplayerview", null,StringUtil.getUIString("VlcPlayerPanel.img"),tabWindow);
            	vlcPlayer.play();
			}
        }
	}
	
	public void recevieConfVideoCommand(String[] usernameList,String confNo,String from,Object command) throws Exception{
		HashMap commandMap = (HashMap) command;
		String mrl = (String) commandMap.get("mediaurl");
		log.info("recevieConfVideoCommand");
		
		//计算时效性
		Long sendtime = (Long) commandMap.get("systemtime");
		Long systemTime = LvmcUtil.getLCMUtil().getSystemTime();
		long deta =  systemTime - sendtime;
		if (deta > 2000L) {
			return;
		}
		log.info("deta time: "+deta);
		
		LoginInfo loginInfo=DataUtil.getValue(DataUtil.Key.LoginInfo);
		VlcPlayer vlcPlayer = ViewManager.getComponent(VlcPlayer.class);
		
		
		String action = (String) commandMap.get("action");
		if (action != null) {
			
			if (action.equals("SHOW_AND_PLAYVIDEO")) {
				if (!from.equals(loginInfo.getUsername())) {
					vlcPlayer.getPlayerButtonsPanel().setVisible(false);
					vlcPlayer.getPositionProgressBar().setVisible(true);
					vlcPlayer.getPositionSlider().setVisible(false);
				}
				else{
					vlcPlayer.getPlayerButtonsPanel().setVisible(true);
					vlcPlayer.getPositionProgressBar().setVisible(false);
					vlcPlayer.getPositionSlider().setVisible(true);
				}
				if (mrl != null) {
					startPlay(mrl);
				}
			}
			else if(action.equals("TIME_VALUE_CHANGED")){
				if (!from.equals(loginInfo.getUsername())) {
					Long time = (Long) commandMap.get("time");
					vlcPlayer.getMediaPlayer().setTime(time);
				}
				
			}
			else if(action.equals("STOPED")){
				if (!from.equals(loginInfo.getUsername())) {
					vlcPlayer.getMediaPlayer().stop();
				}
			}
			else if(action.equals("PAUSED")){
				if (!from.equals(loginInfo.getUsername())) {
					Boolean isPlaying = (Boolean) commandMap.get("isPlaying");
					if (vlcPlayer.getMediaPlayer().isPlaying() != isPlaying) {
						vlcPlayer.getMediaPlayer().pause();
					}
				}
			}
		}
	}
}
