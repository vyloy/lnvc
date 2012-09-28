package com.lorent.vovo.util;

import java.awt.Window;

import org.apache.log4j.Logger;

import com.jniwrapper.win32.system.VersionInfo;
import com.jniwrapper.win32.ui.FlashInfo;
import com.jniwrapper.win32.ui.Wnd;

public class FlashWindowUtil {

	private static Logger log = Logger.getLogger(FlashWindowUtil.class);
	
	private static void execute(java.awt.Window w,boolean flash){
		try{
			Wnd _decorator;
			boolean _flashing = flash;
			VersionInfo versionInfo = new VersionInfo();
			boolean isWin98 = false, isWin2k = false;
			if (versionInfo.getMajor() > 5) //Win2k has version 5.0 Since that windows all later versions do support transparency         
			{
				isWin98 = true;
				isWin2k = true;
			} else if ((versionInfo.getMajor() > 4)
					|| (versionInfo.getMajor() == 4 && versionInfo.getMinor() >= 10)) {
				isWin98 = true;
			}
			final Window parent = w;
			_decorator = new Wnd(parent);
			FlashInfo.FlashOptions flashOptions = new FlashInfo.FlashOptions();
			flashOptions.setFlashAll(_flashing);
			if (_flashing)
			{                     
				flashOptions.setFlashContinuously(true);                 
			}                 
			final Wnd windowHandle = _decorator;
			FlashInfo flashInfo = new FlashInfo(windowHandle, flashOptions, 0, 0);
			windowHandle.flashWindow(flashInfo);
		}catch(Exception ex){
			log.info("jniwrap is not support flash");
			ex.printStackTrace();
		}
	}
	
	public static void start(java.awt.Window w){
		if(!w.isActive()){
			execute(w,true);
		}
	}
	
	public static void stop(java.awt.Window w){
		execute(w,false);
	}
	
}
