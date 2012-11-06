package com.lorent.video.util;

import android.util.Log;
import android.view.KeyEvent;

public class LogUtil {

	public static void logKeyEvent(KeyEvent event){
    	Log.i("keyCode", "event.getCharacters()" + event.getCharacters());
//    	Log.i("keyCode", "event.getDevice().getName()" + event.getDevice().getName());
    	Log.i("keyCode", "event.getNumber()" + event.getNumber());
    	Log.i("keyCode", "event.getAction()" + event.getAction());
    	Log.i("keyCode", "event.getDeviceId()" + event.getDeviceId());
    	Log.i("keyCode", "event.getKeyCode()" + event.getKeyCode());
    	Log.i("keyCode", "event.getMetaState()" + event.getMetaState());
//    	Log.i("keyCode", "event.getModifiers()" + event.getModifiers());
    	Log.i("keyCode", "event.getScanCode()" + event.getScanCode());
//    	Log.i("keyCode", "event.getSource()" + event.getSource());
    	Log.i("keyCode", "event.getUnicodeChar()" + event.getUnicodeChar());
	}
	
}
