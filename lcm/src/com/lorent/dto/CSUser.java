package com.lorent.dto;

public class CSUser {
	private String ip;
	private int port;
	private String username;
	private String passwd;
	
	public CSUser(String ip, int port, String username, String passwd) {
		super();
		this.ip = ip;
		this.port = port;
		this.username = username;
		this.passwd = passwd;
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public String getUsername() {
		return username;
	}

	public String getPasswd() {
		return passwd;
	}
	
	@Override
	public String toString() {
		return "CSUser{ip=" + ip + "&port=" + port + "&username=" + username + "&passwd=" + passwd + "}";
	}
	
	

}
