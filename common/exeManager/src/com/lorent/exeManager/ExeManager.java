package com.lorent.exeManager;

import org.apache.log4j.Logger;

public class ExeManager {

	private static final Logger log = Logger.getLogger(ExeManager.class);

	public static void main(String[] args) {
		if (args[0] != null) {
			try {
				if (args[0].equals("1")) {// restart app
					if (args[1] != null && args[2] != null) {
						new RestartApp().execute(args[1], args[2]);
					}
				}
				System.exit(0);
			} catch (Exception e) {
				log.error("execute error", e);
			}
		} else {
			log.error("wrong args");
		}
	}
}
