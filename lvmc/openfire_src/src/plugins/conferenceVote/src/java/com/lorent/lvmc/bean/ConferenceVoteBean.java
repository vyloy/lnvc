package com.lorent.lvmc.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConferenceVoteBean extends BaseBean{

	private String title;
	private String titleRemark;
	private Integer effectiveTime;
	private Integer conferenceId;//服务端用，记录会议ID;
	private Integer creator;//服务端用，记录创建投票主题者的ID
	private Boolean isStart = null;
	private Boolean isClose = null;
	private Date beginTime;
	private List<ConferenceSelectBean> selects = new ArrayList<ConferenceSelectBean>();;
	private String roomJid;
	private String creatorStr;//客户端用，记录创建投票主题者的账号
	private Integer userId;//服务端用,记录投票人的ID
	private String userName;//客户端用，记录投票人的账号
//	private String command;//暂时不用，对应之前XML的operate标签的NAME值
//	private String resultMessage;//暂时不用，处理结果信息
	
	
//	public String getResultMessage() {
//		return resultMessage;
//	}
//	public void setResultMessage(String resultMessage) {
//		this.resultMessage = resultMessage;
//	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
//	public String getCommand() {
//		return command;
//	}
//	public void setCommand(String command) {
//		this.command = command;
//	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getCreatorStr() {
		return creatorStr;
	}
	public void setCreatorStr(String creatorStr) {
		this.creatorStr = creatorStr;
	}
	public String getRoomJid() {
		return roomJid;
	}
	public void setRoomJid(String roomJid) {
		this.roomJid = roomJid;
	}
	public List<ConferenceSelectBean> getSelects() {
		return selects;
	}
	public void setSelects(List<ConferenceSelectBean> selects) {
		this.selects = selects;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitleRemark() {
		return titleRemark;
	}
	public void setTitleRemark(String titleRemark) {
		this.titleRemark = titleRemark;
	}
	public Integer getEffectiveTime() {
		return effectiveTime;
	}
	public void setEffectiveTime(Integer effectiveTime) {
		this.effectiveTime = effectiveTime;
	}
	public Integer getConferenceId() {
		return conferenceId;
	}
	public void setConferenceId(Integer conferenceId) {
		this.conferenceId = conferenceId;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	public Boolean isStart() {
		return isStart;
	}
	public void setStart(Boolean isStart) {
		this.isStart = isStart;
	}
	public Boolean isClose() {
		return isClose;
	}
	public void setClose(Boolean isColse) {
		this.isClose = isColse;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	
	
}
