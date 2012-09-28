package com.lorent.model;

public class ConfUserRoleBean extends BaseModel implements AbstractAclModel{

	private Integer conferenceUserId;
	private Integer roleId;
	
	public Integer getConferenceUserId() {
		return conferenceUserId;
	}
	public void setConferenceUserId(Integer conferenceUserId) {
		this.conferenceUserId = conferenceUserId;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	
}
