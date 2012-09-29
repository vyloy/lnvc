package com.lorent.web.filter;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.lorent.util.SessionContext;

public class SessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent event) {
		SessionContext.addSession(event.getSession());
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		SessionContext.removeSession(event.getSession().getId());
	}

}
