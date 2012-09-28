package com.lorent.whiteboard.server;

import java.lang.Thread.UncaughtExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lorent.whiteboard.server.trigger.DeleteMeetingTrigger;
import com.lorent.whiteboard.server.trigger.QuartzTrigger;


public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	private static final Server server = new Server();
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				logger.error(t.getName()+" throw exception",e);
			}
		});
		ServerConfig.init();
		server.bind(8888);
		FileServer fileServer = new FileServer();
		fileServer.bind();
		QuartzTrigger.addTask(new DeleteMeetingTrigger(), null);
	}

	public static Server getServer(){
		return server;
	}
}
