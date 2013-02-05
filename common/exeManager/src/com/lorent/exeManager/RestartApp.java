package com.lorent.exeManager;

import org.apache.log4j.Logger;

public class RestartApp {
	private static final Logger log = Logger.getLogger(RestartApp.class);
	
	public void execute(String appPath, String appName)throws Exception{
		log.info("RestartApp appPath = " + appPath + " & appName = " + appName);
		boolean flag = true;
		while(flag){
			if(!ProcessUtil.getInstance().processExists(appName)){
				flag = false;
			}else{
				Thread.sleep(1000);
			}
		}
		String cmd = "createprocess.exe IntegrityLevel=High \"" + appPath + "/" + appName + "\"";
		log.info("command = " + cmd);
		Runtime.getRuntime().exec(cmd);
		log.info("RestartApp finish");
	}
}
