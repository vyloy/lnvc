package com.lorent.model;

import java.util.Date;

public class CronConferenceBean extends ConferenceBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer cronType;
	private String cronTime;
	private String cronExpression;
	private Date cronHours;
	private Integer length;
	//自动生成的周期会议号码
	private String confno;
	
	public String getConfno() {
		return confno;
	}
	public void setConfno(String confno) {
		this.confno = confno;
	}
	public Integer getCronType() {
		return cronType;
	}
	public void setCronType(Integer cronType) {
		this.cronType = cronType;
	}
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public String getCronTime() {
		return cronTime;
	}
	public void setCronTime(String cronTime) {
		this.cronTime = cronTime;
	}
	public Date getCronHours() {
		return cronHours;
	}
	public void setCronHours(Date cronHours) {
		this.cronHours = cronHours;
	}

}
