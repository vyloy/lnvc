package com.lorent.vovo.dto;

import java.util.Date;


public class ChatRecord {
	private int senderId;
	private String senderName;
	private String msg;
	private Date date;
	private FontStyle style;
	private String sessionID;
	
	
	public ChatRecord(int senderId, String senderName, String msg, Date date,
			FontStyle style, String sessionID) {
		super();
		this.senderId = senderId;
		this.senderName = senderName;
		this.msg = msg;
		this.date = date;
		this.style = style;
		this.sessionID = sessionID;
	}


	public int getSenderId() {
		return senderId;
	}


	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}


	public String getSenderName() {
		return senderName;
	}


	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public FontStyle getStyle() {
		return style;
	}


	public void setStyle(FontStyle style) {
		this.style = style;
	}


	public String getSessionID() {
		return sessionID;
	}


	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	
	
}