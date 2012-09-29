package com.lorent.vovo.dto;

public class GroupInfo {
	private String roomJID;
	private String topic;
	private String desc;
	
	public GroupInfo(){
		
	}
	
	public GroupInfo(String roomJID,String topic,String desc){
		this.roomJID = roomJID;
		this.topic = topic;
		this.desc = desc;
	}
	
	public String getRoomJID() {
		return roomJID;
	}
	public void setRoomJID(String roomJID) {
		this.roomJID = roomJID;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
