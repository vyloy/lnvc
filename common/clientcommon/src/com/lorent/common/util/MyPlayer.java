package com.lorent.common.util;

import java.io.File;
import java.net.MalformedURLException;

import org.apache.log4j.Logger;


public class MyPlayer {
	private static final String RING_IN_FILE_NAME = "ringin.wav";
	private static final String RING_OUT_FILE_NAME = "ringout.wav";
	private static final String CALL_ERROR_FILE_NAME = "callerror.wav";
	
	private static final String RING_IN_FILE = "/com/lorent/common/resource/sound/ringin.wav";
	private static final String RING_OUT_FILE = "/com/lorent/common/resource/sound/ringout.wav";
	private static final String CALL_ERROR_FILE = "/com/lorent/common/resource/sound/callerror.wav";
	public static final String AppPath = System.getProperty("user.dir").replace("\\", "/");
	public static final String SoundPath = AppPath + "/sound/";
	
	public static final int TYPE_RING_IN = 1;
	public static final int TYPE_RING_OUT = 2;
	public static final int TYPE_CALL_ERROR = 3;
	
	private static Logger log = Logger.getLogger(MyPlayer.class);
	
	private static AudioPlayer player;
	
	public static void play(int type){
		stop();
		if(TYPE_RING_IN == type){
			
			player = createAudioPlayer(RING_IN_FILE_NAME,RING_IN_FILE);
			player.start(true, 0);
		}else if(TYPE_RING_OUT == type){
			player = createAudioPlayer(RING_OUT_FILE_NAME,RING_OUT_FILE);//new AudioPlayer(MyPlayer.class.getResource(RING_OUT_FILE));
			player.start(true, 1000);
		}else if(TYPE_CALL_ERROR == type){
			player = createAudioPlayer(CALL_ERROR_FILE_NAME,CALL_ERROR_FILE);//new AudioPlayer(MyPlayer.class.getResource(CALL_ERROR_FILE));
			player.start(false, 12000);
		}
	}
	
	public static AudioPlayer createAudioPlayer(String path,String defaultPath){
		AudioPlayer player = null;
		File f = new File(SoundPath + path);
		log.info("sound path:" + f.getAbsolutePath());
		if(f.exists()){
			try {
				player = new AudioPlayer(f.toURI().toURL());
			} catch (MalformedURLException e) {
				player = new AudioPlayer(MyPlayer.class.getResource(defaultPath));
			}
		}else{
			player = new AudioPlayer(MyPlayer.class.getResource(defaultPath));
		}
		return player;
	}
	
	public static void stop(){
		if(player != null){
			player.stop();
		}
	}
	
	public static void main(String[] args){
		MyPlayer.play(TYPE_CALL_ERROR);
	}
}
