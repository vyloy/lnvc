package com.lorent.common.dto;

import java.io.Serializable;

public class LCMConferenceDto implements Serializable {
	private Integer id;//
	private String conferenceName;//                             会议名称
	private Integer creator;//                                     创建者
	private String creatorName;
	private Integer needApply;//                                  是否要通过申请
	private Integer defaultRoleId;//                             默认角色
	private String defaultRoleName;
	private Integer conferenceTypeId;//                          会议类型
	private String conferenceTypeName;
	private Integer del = new Integer(1);                        //del=0表示该会议已经删除
	private String confNo;
	private String topic;//										主题
	private String description;//								描述
	private String password;//									密码
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConferenceName() {
		return conferenceName;
	}
	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	public Integer getNeedApply() {
		return needApply;
	}
	public void setNeedApply(Integer needApply) {
		this.needApply = needApply;
	}
	public Integer getDefaultRoleId() {
		return defaultRoleId;
	}
	public void setDefaultRoleId(Integer defaultRoleId) {
		this.defaultRoleId = defaultRoleId;
	}
	public Integer getConferenceTypeId() {
		return conferenceTypeId;
	}
	public void setConferenceTypeId(Integer conferenceTypeId) {
		this.conferenceTypeId = conferenceTypeId;
	}
	public Integer getDel() {
		return del;
	}
	public void setDel(Integer del) {
		this.del = del;
	}
	public String getConfNo() {
		return confNo;
	}
	public void setConfNo(String confNo) {
		this.confNo = confNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public String getDefaultRoleName() {
		return defaultRoleName;
	}
	public void setDefaultRoleName(String defaultRoleName) {
		this.defaultRoleName = defaultRoleName;
	}
	public String getConferenceTypeName() {
		return conferenceTypeName;
	}
	public void setConferenceTypeName(String conferenceTypeName) {
		this.conferenceTypeName = conferenceTypeName;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
