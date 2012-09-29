package com.lorent.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ConferenceBean extends BaseModel implements AbstractAclModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String confSubject;
	private String confPass;
	private Long confAllowBefore;
	private Integer confMode;
	private Integer confType;
	private Integer confMemberCount;
	private Integer confVideoCount;
	private UserBean confHost;
	private String confDesc;
	private Integer confPublic;
	private Integer ismix;
	private Integer requiredLogin;
	private Date startTime;
	private Date endTime;
	private UserBean owner;
	private CustomerBean customer;
	private Integer confStatus;
//	private String callNo;
	private ConferenceNoBean conferenceNo;
	private Set<UserBean>users = new HashSet<UserBean>();
	private String mcuMixerKey;
	private Integer mcuMediaLayOut;
	private String mcuMediaQuality;
	private String confUID;
	private String realPassword;
//	private String cronExpression;
	//播放媒体ID
	protected Integer mediaId;
	//0-停止 1-播放
	private Integer mediaOperate;
	private String confno;
	private Integer sipId;
	
	
	
	public Integer getIsmix() {
		return ismix;
	}

	public void setIsmix(Integer ismix) {
		this.ismix = ismix;
	}

	public Integer getSipId() {
		return sipId;
	}

	public void setSipId(Integer sipId) {
		this.sipId = sipId;
	}

	public Integer getMediaId() {
			return mediaId;
	}

	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}

	public Integer getMediaOperate() {
		return mediaOperate;
	}

	public void setMediaOperate(Integer mediaOperate) {
		this.mediaOperate = mediaOperate;
	}

	//	Constrators
	public ConferenceBean() {
		
	}
	
	public ConferenceBean(Integer confStatus) {
		this.confStatus = confStatus;
	}
	
//	getters and setters
	public Long getConfAllowBefore() {
		return confAllowBefore;
	}
	public void setConfAllowBefore(Long confAllowBefore) {
		this.confAllowBefore = confAllowBefore;
	}
	public String getConfDesc() {
		return confDesc;
	}
	public void setConfDesc(String confDesc) {
		this.confDesc = confDesc;
	}
	public UserBean getConfHost() {
		return confHost;
	}
	public void setConfHost(UserBean confHost) {
		this.confHost = confHost;
	}
	public Integer getConfMemberCount() {
		return confMemberCount;
	}
	public void setConfMemberCount(Integer confMemberCount) {
		this.confMemberCount = confMemberCount;
	}
	public Integer getConfMode() {
		return confMode;
	}
	public void setConfMode(Integer confMode) {
		this.confMode = confMode;
	}
	public String getConfPass() {
		return confPass;
	}
	public void setConfPass(String confPass) {
		this.confPass = confPass;
	}
	public Integer getConfPublic() {
		return confPublic;
	}
	public void setConfPublic(Integer confPublic) {
		this.confPublic = confPublic;
	}
	public String getConfSubject() {
		return confSubject;
	}
	public void setConfSubject(String confSubject) {
		this.confSubject = confSubject;
	}
	public Integer getConfType() {
		return confType;
	}
	public void setConfType(Integer confType) {
		this.confType = confType;
	}
	public Integer getConfVideoCount() {
		return confVideoCount;
	}
	public void setConfVideoCount(Integer confVideoCount) {
		this.confVideoCount = confVideoCount;
	}
	public CustomerBean getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerBean customer) {
		this.customer = customer;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getRequiredLogin() {
		return requiredLogin;
	}
	public void setRequiredLogin(Integer requiredLogin) {
		this.requiredLogin = requiredLogin;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Integer getConfStatus() {
		return confStatus;
	}
	public void setConfStatus(Integer confStatus) {
		this.confStatus = confStatus;
	}
//	public String getCallNo() {
//		return callNo;
//	}
//	public void setCallNo(String callNo) {
//		this.callNo = callNo;
//	}

	public Set<UserBean> getUsers() {
		return users;
	}

	public void setUsers(Set<UserBean> users) {
		this.users = users;
	}

	public UserBean getOwner() {
		return owner;
	}

	public void setOwner(UserBean owner) {
		this.owner = owner;
	}

	public Integer getMcuMediaLayOut() {
		return mcuMediaLayOut;
	}

	public void setMcuMediaLayOut(Integer mcuMediaLayOut) {
		this.mcuMediaLayOut = mcuMediaLayOut;
	}

	public String getMcuMediaQuality() {
		return mcuMediaQuality;
	}

	public void setMcuMediaQuality(String mcuMediaQuality) {
		this.mcuMediaQuality = mcuMediaQuality;
	}

	public String getMcuMixerKey() {
		return mcuMixerKey;
	}

	public void setMcuMixerKey(String mcuMixerKey) {
		this.mcuMixerKey = mcuMixerKey;
	}

	public String getConfUID() {
		return confUID;
	}

	public void setConfUID(String confUID) {
		this.confUID = confUID;
	}

	public ConferenceNoBean getConferenceNo() {
		return conferenceNo;
	}

	public void setConferenceNo(ConferenceNoBean conferenceNo) {
		this.conferenceNo = conferenceNo;
	}

	public String getRealPassword() {
		return realPassword;
	}

	public void setRealPassword(String realPassword) {
		this.realPassword = realPassword;
	}

	public String getConfno() {
		return confno;
	}

	public void setConfno(String confno) {
		this.confno = confno;
	}

//	public String getCronExpression() {
//		return cronExpression;
//	}
//
//	public void setCronExpression(String cronExpression) {
//		this.cronExpression = cronExpression;
//	}
}
