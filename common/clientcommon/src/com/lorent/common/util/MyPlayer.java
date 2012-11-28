package com.lorent.common.util;

public class MyPlayer {
	private static final String RING_IN_FILE = "/com/lorent/vovo/resource/sound/ringin.wav";
	private static final String RING_OUT_FILE = "/com/lorent/vovo/resource/sound/ringout.wav";
	
	public static final int TYPE_RING_IN = 1;
	public static final int TYPE_RING_OUT = 2;
	
	private static AudioPlayer player;
	
	public static void play(int type){
		stop();
		if(TYPE_RING_IN == type){
			player = new AudioPlayer(MyPlayer.class.getResource(RING_IN_FILE));
			player.start(true, 0);
		}else if(TYPE_RING_OUT == type){
			player = new AudioPlayer(MyPlayer.class.getResource(RING_OUT_FILE));
			player.start(true, 1000);
		}
	}
	
	public static void stop(){
		if(player != null){
			player.stop();
		}
	}
}
