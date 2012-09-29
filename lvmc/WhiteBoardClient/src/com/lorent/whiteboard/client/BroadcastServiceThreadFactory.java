package com.lorent.whiteboard.client;

import java.util.concurrent.ThreadFactory;

public class BroadcastServiceThreadFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setDaemon(true);
		t.setName("ClientBroadcastThread");
		return t;
	}

}
