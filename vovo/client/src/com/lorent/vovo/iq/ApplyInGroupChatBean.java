package com.lorent.vovo.iq;

public class ApplyInGroupChatBean extends BaseBean{

	
	private String applicantJid;
	private String applicantName;
	private String roomJid;
	private String roomName;
	
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getRoomJid() {
		return roomJid;
	}
	public void setRoomJid(String roomJid) {
		this.roomJid = roomJid;
	}
	
	public String getApplicantJid() {
		return applicantJid;
	}
	public void setApplicantJid(String applicantJid) {
		this.applicantJid = applicantJid;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	
}
