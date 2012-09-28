package com.lorent.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public abstract class SessionContext {
	private static Map<String, HttpSession>sessionMap;
	public static void addSession(HttpSession session){
		if(sessionMap==null)sessionMap = new HashMap<String, HttpSession>();
		sessionMap.put(session.getId(),session);
	}
	public static HttpSession getSession(String sessionId){
		if(sessionMap==null)return null;
		return sessionMap.get(sessionId);
	}
	public static HttpSession removeSession(String sessionId){
		HttpSession temp = sessionMap.get(sessionId);
		sessionMap.remove(sessionId);
		return temp;
	}
}
