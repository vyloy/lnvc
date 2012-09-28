package com.lorent.lvmc.bean;

import java.io.Serializable;
import java.util.List;

public class ConferenceVoteMessage implements Serializable{
	
	private String command;//对应之前XML的operate标签的NAME值
	private List<ConferenceVoteBean> ConferenceVoteBean;
	private String resultMessage;//处理结果信息
	
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public List<ConferenceVoteBean> getConferenceVoteBean() {
		return ConferenceVoteBean;
	}
	public void setConferenceVoteBean(List<ConferenceVoteBean> conferenceVoteBean) {
		ConferenceVoteBean = conferenceVoteBean;
	}
	
	
}
