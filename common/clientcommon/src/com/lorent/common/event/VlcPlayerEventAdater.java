package com.lorent.common.event;

import org.apache.log4j.Logger;

public class VlcPlayerEventAdater  {
	private static Logger log = Logger.getLogger(VlcPlayerEventAdater.class);
	
	public void mediaPlayerPositionChanged(int position){
		log.info("VlcPlayerEventAdater.mediaPlayerPositionChanged");
	}
	
	public void mediaPlayerTimeChanged(long time){
		log.info("VlcPlayerEventAdater.mediaPlayerTimeChanged");
	}
	
	public void exitFullScreenMode() {
		log.info("VlcPlayerEventAdater.exitFullScreenMode");
	}

	public void enterFullScreenMode() {
		log.info("VlcPlayerEventAdater.enterFullScreenMode");
	}

}
