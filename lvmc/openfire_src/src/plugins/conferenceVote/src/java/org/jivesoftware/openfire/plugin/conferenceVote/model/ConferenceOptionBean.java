package org.jivesoftware.openfire.plugin.conferenceVote.model;

public class ConferenceOptionBean extends BaseBean {

	private Integer conferenceSelectId;
	private String optionName;
	private Integer num;//选中次数
	private Double percent;//得票占总投票
	
	
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Double getPercent() {
		return percent;
	}
	public void setPercent(Double percent) {
		this.percent = percent;
	}
	public Integer getConferenceSelectId() {
		return conferenceSelectId;
	}
	public void setConferenceSelectId(Integer conferenceSelectId) {
		this.conferenceSelectId = conferenceSelectId;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	
	
	
}
