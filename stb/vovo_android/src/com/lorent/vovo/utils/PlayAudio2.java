/**
 * 
 */
package com.lorent.vovo.utils;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * @author jimmy
 *
 */
public class PlayAudio2 {
	private static MediaPlayer mMediaPlay = null;

	public static void  play(Context context,int resourceid){
		mMediaPlay = MediaPlayer.create(context, resourceid);
		mMediaPlay.setLooping(true);
		mMediaPlay.start();
	}
	
	public static void stop(){
		if (mMediaPlay != null) {
			mMediaPlay.stop();
			mMediaPlay.release();
			mMediaPlay = null;
		}
	}
}
