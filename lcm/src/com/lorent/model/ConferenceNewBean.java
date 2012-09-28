package com.lorent.model;

public class ConferenceNewBean extends BaseModel implements AbstractAclModel{

	private String conferenceName;//                             会议名称
	private Integer creator;//                                     创建者
	private Integer needApply;//                                  是否要通过申请
	private Integer defaultRoleId;//                             默认角色
	private Integer conferenceTypeId;//                          会议类型
	private Integer del = new Integer(1);                        //del=0表示该会议已经删除
	private String confNo;
	private String topic;
	private String description;
	private String password;
	
	public String getConfNo() {
		return confNo;
	}
	public void setConfNo(String confNo) {
		this.confNo = confNo;
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
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
