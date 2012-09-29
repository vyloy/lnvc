package com.lorent.vovo.iq;

public class QuitGroupChatBean extends BaseBean{
	private String roomJid;
	private boolean needToConfirm;
	public String getRoomJid() {
		return roomJid;
	}
	public void setRoomJid(String roomJid) {
		this.roomJid = roomJid;
	}
	public boolean isNeedToConfirm() {
		return needToConfirm;
	}
	public void setNeedToConfirm(boolean needToConfirm) {
		this.needToConfirm = needToConfirm;
	}
	
}
