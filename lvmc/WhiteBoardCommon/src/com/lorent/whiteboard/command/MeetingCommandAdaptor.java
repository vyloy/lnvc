package com.lorent.whiteboard.command;

import org.apache.mina.core.session.IoSession;

import com.alibaba.fastjson.JSON;
import com.lorent.whiteboard.model.Meeting;
import com.lorent.whiteboard.model.Meetings;

public abstract class MeetingCommandAdaptor implements MeetingCommand,Cloneable{

	private static final long serialVersionUID = 1L;
	
	static{
		int features = 0;
        features |= com.alibaba.fastjson.serializer.SerializerFeature.QuoteFieldNames.getMask();
        features |= com.alibaba.fastjson.serializer.SerializerFeature.SkipTransientField.getMask();
        features |= com.alibaba.fastjson.serializer.SerializerFeature.WriteEnumUsingToString.getMask();
		JSON.DEFAULT_GENERATE_FEATURE=features;
	}
	
	protected String meetingId;
	
	protected long sessionId;
	
	protected MeetingCommandAdaptor(String meetingId) {
		this.meetingId = meetingId;
	}

	@Override
	public String getMeetingId() {
		return meetingId;
	}


	@Override
	public void setSessionId(long sessionId) {
		this.sessionId=sessionId;
	}

	@Override
	public void execute(Meetings meetings, IoSession session) {
		throw new UnsupportedOperationException();
	}
	

	@Override
	public void execute(Meeting meeting, IoSession session) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName()+" [meetingId=" + meetingId + "]";
	}

	@Override
	protected MeetingCommandAdaptor clone(){
		try {
			return (MeetingCommandAdaptor) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((meetingId == null) ? 0 : meetingId.hashCode());
		result = prime * result + (int) (sessionId ^ (sessionId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeetingCommandAdaptor other = (MeetingCommandAdaptor) obj;
		if (meetingId == null) {
			if (other.meetingId != null)
				return false;
		} else if (!meetingId.equals(other.meetingId))
			return false;
		if (sessionId != other.sessionId)
			return false;
		return true;
	}

	
}
