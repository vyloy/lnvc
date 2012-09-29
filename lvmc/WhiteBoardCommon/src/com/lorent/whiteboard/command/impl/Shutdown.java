package com.lorent.whiteboard.command.impl;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.mina.core.session.IoSession;

import com.lorent.whiteboard.command.Command;

public class Shutdown implements Command {

	private static final long serialVersionUID = 1L;

	@Override
	public void execute(final IoSession session) {
		Thread thread = new Thread() {
			{this.setDaemon(true);}
			@Override
			public void run() {
				Map<Long, IoSession> sessions =
						session.getService().getManagedSessions();
				for(Entry<Long,IoSession> entry:sessions.entrySet()){
					entry.getValue().close(true);
				}
				session.getService().dispose(true);
			}
		};
		thread.start();
	}

}
