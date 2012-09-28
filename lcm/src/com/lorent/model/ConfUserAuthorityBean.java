package com.lorent.model;

public class ConfUserAuthorityBean extends BaseModel implements AbstractAclModel{

	private Integer conferenceUserId;
	private Integer authorityId;
	
	
	public Integer getConferenceUserId() {
		return conferenceUserId;
	}
	public void setConferenceUserId(Integer conferenceUserId) {
		this.conferenceUserId = conferenceUserId;
	}
	public Integer getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}
	
	
}
