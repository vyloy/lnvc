package com.lorent.client.model;



public interface MemberListUpdater extends java.io.Serializable{
	String getUsername();
	void change(MemberList list, MemberListItem listItem);
}
