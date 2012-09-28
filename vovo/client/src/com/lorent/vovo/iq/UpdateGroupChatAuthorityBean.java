package com.lorent.vovo.iq;

import java.util.ArrayList;
import java.util.List;

public class UpdateGroupChatAuthorityBean extends BaseBean {

	private List<String> marks = new ArrayList<String>();
	private String roomJid;
	public List<String> getMarks() {
		return marks;
	}
	public void setMarks(List<String> marks) {
		this.marks = marks;
	}
	public String getRoomJid() {
		return roomJid;
	}
	public void setRoomJid(String roomJid) {
		this.roomJid = roomJid;
	}
	
	
}
