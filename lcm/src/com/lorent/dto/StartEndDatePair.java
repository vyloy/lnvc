package com.lorent.dto;

import java.util.Date;

/**
 * 开始结束日期时间传递数据载体
 * @author jack
 *
 */
public class StartEndDatePair {
	private Date startDate;
	private String startTime;
	private Date endDate;
	private String endTime;
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
