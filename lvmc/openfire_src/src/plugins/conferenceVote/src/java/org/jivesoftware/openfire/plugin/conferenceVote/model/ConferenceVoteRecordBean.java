package org.jivesoftware.openfire.plugin.conferenceVote.model;

import java.util.Date;

public class ConferenceVoteRecordBean extends BaseBean {

	private Integer conferenceId;
	private Integer conferenceVoteId;
	private Integer conferenceSelectId;
	private Integer conferenceOptionId;
	private Integer userId;
	private Date voteTime;
	public Integer getConferenceId() {
		return conferenceId;
	}
	public void setConferenceId(Integer conferenceId) {
		this.conferenceId = conferenceId;
	}
	public Integer getConferenceVoteId() {
		return conferenceVoteId;
	}
	public void setConferenceVoteId(Integer conferenceVoteId) {
		this.conferenceVoteId = conferenceVoteId;
	}
	public Integer getConferenceSelectId() {
		return conferenceSelectId;
	}
	public void setConferenceSelectId(Integer conferenceSelectId) {
		this.conferenceSelectId = conferenceSelectId;
	}
	public Integer getConferenceOptionId() {
		return conferenceOptionId;
	}
	public void setConferenceOptionId(Integer conferenceOptionId) {
		this.conferenceOptionId = conferenceOptionId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getVoteTime() {
		return voteTime;
	}
	public void setVoteTime(Date voteTime) {
		this.voteTime = voteTime;
	}
	
	
}
