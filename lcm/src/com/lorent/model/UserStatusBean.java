package com.lorent.model;

import java.util.Date;

public class UserStatusBean extends BaseModel {
	private static final long serialVersionUID = 1L;
	private Date startTime;
	private Date endTime;
	private Integer userId;
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getNoId() {
		return userId;
	}
	public void setNoId(Integer userId) {
		this.userId = userId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof UserStatusBean))
			return false;
		if(obj==this)return true;
		if(obj==null||this==null)return false;
		UserStatusBean status = (UserStatusBean)obj;
		if(status.startTime==null||status.endTime==null
				||this.startTime==null||this.endTime==null)
			return false;
		if(status.startTime.equals(this.startTime)||
				status.endTime.equals(this.endTime))
			return true;
		return (status.startTime.after(this.startTime)&&status.startTime.before(this.endTime))
					||(status.endTime.after(this.startTime)&&status.endTime.before(this.endTime))
						||(this.startTime.after(status.startTime)&&this.startTime.before(status.endTime))
							||(this.endTime.after(status.startTime)&&this.endTime.before(status.endTime));
	}
	@Override
	public int hashCode() {
		if(this==null||this.startTime==null||this.endTime==null)
			return 0;
		return (this.startTime.hashCode()+this.endTime.hashCode());
	}
}
