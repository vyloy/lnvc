/**
 * 
 */
package com.lorent.vovo.utils;

import java.io.IOException;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;

/**
 * @author jimmy
 * 
 */
public class PlayAudio {
	/*
	 * private static MediaPlayer mMediaPlay = null;
	 * 
	 * public static void play(Context context,int resourceid){ mMediaPlay =
	 * MediaPlayer.create(context, resourceid);
	 * 
	 * mMediaPlay.setLooping(true); mMediaPlay.start(); }
	 * 
	 * 
	 * public static void stop(){ if (mMediaPlay != null) { mMediaPlay.stop();
	 * mMediaPlay.release(); mMediaPlay = null; } }
	 */
	public static void stop(){
		if (mMediaPlayer != null) {
			mMediaPlayer.stop();
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
	}
	private static MediaPlayer mMediaPlayer;

	public static void play(Context context) {
		
		Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
		mMediaPlayer = new MediaPlayer();
		try {
			mMediaPlayer.setDataSource(context, alert);
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		final AudioManager audioManager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		if (audioManager.getStreamVolume(AudioManager.STREAM_RING) != 0) {
			mMediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
			mMediaPlayer.setLooping(true);
			try {
				mMediaPlayer.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mMediaPlayer.start();
		}
	}
}
