package org.jivesoftware.openfire.plugin.groupchat.model;

public class GroupChatBean {

	private String roomJid;
	private String userJid;

	public String getUserJid() {
		return userJid;
	}

	public void setUserJid(String userJid) {
		this.userJid = userJid;
	}

	public String getRoomJid() {
		return roomJid;
	}

	public void setRoomJid(String roomJid) {
		this.roomJid = roomJid;
	}
	
}
