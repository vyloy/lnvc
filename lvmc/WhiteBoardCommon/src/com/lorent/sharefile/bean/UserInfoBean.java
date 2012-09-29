package com.lorent.sharefile.bean;

import org.apache.mina.core.session.IoSession;

public class UserInfoBean {
	private String userName;
	private String ip;
	private IoSession aliveSession;
	/**
	 * @return the aliveSession
	 */
	public IoSession getAliveSession() {
		return aliveSession;
	}
	/**
	 * @param aliveSession the aliveSession to set
	 */
	public void setAliveSession(IoSession aliveSession) {
		this.aliveSession = aliveSession;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
