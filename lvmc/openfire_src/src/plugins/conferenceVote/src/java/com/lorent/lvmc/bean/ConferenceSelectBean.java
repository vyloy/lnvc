package com.lorent.lvmc.bean;

import java.util.ArrayList;
import java.util.List;

public class ConferenceSelectBean extends BaseBean {

	private Integer conferenceVoteId;
	private Integer type;//表决项类型；1表示单选，2表示多选
	private Integer selectNum;//可以选多少个选项
	private String selectTitle;
	private String selectRemark;
	private List<ConferenceOptionBean> options = new ArrayList<ConferenceOptionBean>();
	private String roomJid;
	private Integer totalNum;//表决项投票总数
	
	
	
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public String getRoomJid() {
		return roomJid;
	}
	public void setRoomJid(String roomJid) {
		this.roomJid = roomJid;
	}
	public Integer getConferenceVoteId() {
		return conferenceVoteId;
	}
	public void setConferenceVoteId(Integer conferenceVoteId) {
		this.conferenceVoteId = conferenceVoteId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getSelectNum() {
		return selectNum;
	}
	public void setSelectNum(Integer selectNum) {
		this.selectNum = selectNum;
	}
	public String getSelectTitle() {
		return selectTitle;
	}
	public void setSelectTitle(String selectTitle) {
		this.selectTitle = selectTitle;
	}
	public String getSelectRemark() {
		return selectRemark;
	}
	public void setSelectRemark(String selectRemark) {
		this.selectRemark = selectRemark;
	}
	public List<ConferenceOptionBean> getOptions() {
		return options;
	}
	public void setOptions(List<ConferenceOptionBean> options) {
		this.options = options;
	}
	
	
	
}
