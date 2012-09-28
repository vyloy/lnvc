package com.lorent.lvmc.ucs;

import org.apache.log4j.Logger;

import com.lorent.util.LCCUtil;

public class Start {
	
	private static Logger log = Logger.getLogger(Start.class);
	
	public static void main(String[] args){
//		new Start().execute(new String[]{"6082", "6081"});
		new Start().execute(args);
	}
	
	public static String calling;
	
	public void execute(String[] args){
		log.info("ucs phone module start");
		try{
			int dllPort = Integer.parseInt(args[0]);
			int myPort = Integer.parseInt(args[1]);
			UCSClient.init(dllPort);
			UCSServer.init(myPort);
			UCSClient.sendMyState(UCSClient.START_SUCCESS);
			new HeartThread().start();
		}catch(Exception e){
			log.error("main", e);
			try {
				UCSClient.sendMyState(UCSClient.START_FAIL);
			} catch (Exception e1) {
				log.error("main", e1);
			}
			System.exit(0);
		}
	}
	
	private class HeartThread extends Thread{
		@Override
		public void run() {
			while(true){
				try{
					sleep(5000);
					UCSClient.sendMyState(UCSClient.HEART);
				}catch(Exception e){
					log.error("HeartThread" ,e);
					break;
				}
			}
			log.info("dll is killed i will exit");
			LCCUtil.getInstance().doPostProcess();
		}
	}
}
