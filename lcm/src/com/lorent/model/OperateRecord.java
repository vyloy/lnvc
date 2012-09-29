package com.lorent.model;

import java.sql.Timestamp;

public class OperateRecord extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//操作时间
	private Timestamp optime;
	//操作内容
	private String opdisc;
	//操作用户ID
	private Integer userId;
	//操作用户名
	private String userName;
	
	public Timestamp getOptime() {
		return optime;
	}
	public void setOptime(Timestamp optime) {
		this.optime = optime;
	}
	public String getOpdisc() {
		return opdisc;
	}
	public void setOpdisc(String opdisc) {
		this.opdisc = opdisc;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}


	
	
}
