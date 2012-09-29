package com.lorent.model;

public class ConferenceUserBean extends BaseModel implements AbstractAclModel{

	private Integer conferenceId;
	private Integer userId;
	public Integer getConferenceId() {
		return conferenceId;
	}
	public void setConferenceId(Integer conferenceId) {
		this.conferenceId = conferenceId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}
