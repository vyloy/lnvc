package com.lorent.whiteboard.server;

import java.io.File;

import com.lorent.util.ConfigUtil;

public class ServerConfig {
	public final static String AppPath = System.getProperty("user.dir").replace("\\", "/");
	

	/**
	 * @return the maxConvertFilePages
	 */
	public static int getMaxConvertFilePages() {
		return ConfigUtil.getIntProperty("MaxConvertFilePages");
	}

	/**
	 * @return the fileServerPort
	 */
	public static int getFileServerPort() {
		return ConfigUtil.getIntProperty("FileServerPort");
	}



	public static void init() throws Exception{
		File file = new File(AppPath+"/tmp");
		if (!file.exists()) {
			file.mkdirs();
		}
		file = new File(AppPath+"/files");
		if (!file.exists()) {
			file.mkdirs();
		}
		String sPort = ConfigUtil.getProperty("FileServerPort");
		if (sPort == null || sPort.equals("")) {
			sPort = "8889";
			ConfigUtil.setProperty("FileServerPort", sPort);
		}
		
		String sMaxConvertFileNumber = ConfigUtil.getProperty("MaxConvertFilePages");
		if (sMaxConvertFileNumber == null || sMaxConvertFileNumber.equals("")) {
			sMaxConvertFileNumber = "5";
			ConfigUtil.setProperty("MaxConvertFilePages", sMaxConvertFileNumber);
		}
	}
}
