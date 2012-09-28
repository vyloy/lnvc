package com.lorent.model;

public class ConferenceTypeRoleBean extends BaseModel implements AbstractAclModel{

	private Integer conferenceTypeId;
	private Integer conferenceRoleId;
	private Integer maxnum;
	private Integer minnum;
	private String conferenceTypeName;
	private String conferenceRoleName;
		
	public String getConferenceTypeName() {
		return conferenceTypeName;
	}
	public void setConferenceTypeName(String conferenceTypeName) {
		this.conferenceTypeName = conferenceTypeName;
	}
	public String getConferenceRoleName() {
		return conferenceRoleName;
	}
	public void setConferenceRoleName(String conferenceRoleName) {
		this.conferenceRoleName = conferenceRoleName;
	}
	public Integer getMaxnum() {
		return maxnum;
	}
	public void setMaxnum(Integer maxnum) {
		this.maxnum = maxnum;
	}
	public Integer getMinnum() {
		return minnum;
	}
	public void setMinnum(Integer minnum) {
		this.minnum = minnum;
	}
	public Integer getConferenceTypeId() {
		return conferenceTypeId;
	}
	public void setConferenceTypeId(Integer conferenceTypeId) {
		this.conferenceTypeId = conferenceTypeId;
	}
	public Integer getConferenceRoleId() {
		return conferenceRoleId;
	}
	public void setConferenceRoleId(Integer conferenceRoleId) {
		this.conferenceRoleId = conferenceRoleId;
	}
	
}
