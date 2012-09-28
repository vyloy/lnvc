package com.lorent.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ConferenceNoBean extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String noCode;
	private Integer customerId;
	private Boolean isUsed;
//	private Date startTime;
//	private Date endTime;
	private Set<ConfNoStatusBean> noStatus = new HashSet<ConfNoStatusBean>();
	
	public ConferenceNoBean(String noCode) {
		this.noCode = noCode;
	}
	
	public ConferenceNoBean() {
		
	}
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getNoCode() {
		return noCode;
	}
	public void setNoCode(String noCode) {
		this.noCode = noCode;
	}
	@Override
	public boolean equals(Object obj) {
		if(this==obj)return true;
		if(this==null||obj==null)return false;
		if(!(obj instanceof ConferenceNoBean))return false;
		ConferenceNoBean no = (ConferenceNoBean)obj;
		return no.getNoCode().equals(this.noCode);
	}
	@Override
	public int hashCode() {
		if(this==null||this.noCode==null)return 0;
		return this.noCode.hashCode();
	}
	@Override
	public String toString() {
		if(this==null)return null;
		return this.noCode;
	}
	public Boolean getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}

	public Set<ConfNoStatusBean> getNoStatus() {
		return noStatus;
	}

	public void setNoStatus(Set<ConfNoStatusBean> noStatus) {
		this.noStatus = noStatus;
	}

//	public Date getEndTime() {
//		return endTime;
//	}
//
//	public void setEndTime(Date endTime) {
//		this.endTime = endTime;
//	}

//	public Date getStartTime() {
//		return startTime;
//	}
//
//	public void setStartTime(Date startTime) {
//		this.startTime = startTime;
//	}
}
