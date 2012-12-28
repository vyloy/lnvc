package com.lorent.model;

import java.util.Date;

public class ConfNoStatusBean extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date startTime;
	private Date endTime;
	private Integer noId;
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getNoId() {
		return noId;
	}
	public void setNoId(Integer noId) {
		this.noId = noId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof ConfNoStatusBean))
			return false;
		if(obj==this)return true;
		if(obj==null||this==null)return false;
		ConfNoStatusBean no = (ConfNoStatusBean)obj;
		if(no.startTime==null||no.endTime==null
				||this.startTime==null||this.endTime==null)
			return false;
		if(no.startTime.equals(this.startTime)||
				no.endTime.equals(this.endTime))
			return true;
		return (no.startTime.after(this.startTime)&&no.startTime.before(this.endTime))
					||(no.endTime.after(this.startTime)&&no.endTime.before(this.endTime))||
						(this.startTime.after(no.startTime)&&this.startTime.before(no.endTime))
							||(this.endTime.after(no.startTime)&&this.endTime.before(no.endTime));
	}
	@Override
	public int hashCode() {
		if(this==null||this.startTime==null||this.endTime==null)
			return 0;
		return (this.startTime.hashCode()+this.endTime.hashCode());
	}
	
}
