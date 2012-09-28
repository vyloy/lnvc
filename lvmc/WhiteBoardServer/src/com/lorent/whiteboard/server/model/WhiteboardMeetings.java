package com.lorent.whiteboard.server.model;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lorent.whiteboard.command.MeetingCommand;
import com.lorent.whiteboard.command.impl.Attend;
import com.lorent.whiteboard.model.Meeting;
import com.lorent.whiteboard.model.Meetings;
import com.lorent.whiteboard.server.db.DatabaseFactory;

public class WhiteboardMeetings implements Meetings {
	
	private static final Logger logger = LoggerFactory.getLogger(WhiteboardMeetings.class);
	
	private final ConcurrentHashMap<String, SoftReference<Meeting>> meetings = new ConcurrentHashMap<String, SoftReference<Meeting>>();
	
	public static final ThreadLocal<Meetings> currentMeetings=new ThreadLocal<Meetings>(){}; 
	
	public void execute(IoSession session, MeetingCommand command) {
		Meeting meeting=null;
		if(command.getMeetingId()!=null){
			meeting = get(command.getMeetingId());
		}
		if(meeting!=null){
			currentMeetings.set(this);
			logger.info("meeting {} execute:{}",command.getMeetingId(),command);
			meeting.execute(session, command);
		}else{
			logger.info("no meeting {} command {} execute",command.getMeetingId(),command);
			command.execute(this, session);
		}
	}

	public Meeting get(String meetingId) {
		SoftReference<Meeting> meetingRef = meetings.get(meetingId);
		Meeting meeting;
		if(meetingRef!=null&&(meeting = meetingRef.get())!=null){
			return meeting;
		}
		return _get(meetingId);
	}
	
	private Meeting _get(String meetingId){
		synchronized(meetings){
			SoftReference<Meeting> meetingRef = meetings.get(meetingId);
			Meeting meeting;
			if(meetingRef!=null&&(meeting = meetingRef.get())!=null){
				return meeting;
			}
			return loadAndPut(meetingId);
		}
	}
	
	private Meeting loadAndPut(String meetingId){
		Meeting meeting =loadMeeting(meetingId);
		if(meeting!=null){
			SoftReference<Meeting> meetingRef = new SoftReference<Meeting>(meeting);
			meetings.put(meetingId, meetingRef);
			return meeting;
		}
		return null;
	}
	
	private Meeting loadMeeting(String meetingId){
		File mf = DatabaseFactory.getFileForMeeting(meetingId);
		if(!mf.exists()){
			logger.debug("加载会议{}失败，文件不存在！",meetingId);
			return null;
		}
		List<Meeting> m = DatabaseFactory.load(this, mf);
		if(m==null||m.isEmpty()){
			logger.debug("加载会议{}失败！",meetingId);
			return null;
		}
		return m.get(0);
	}

	public Meeting create(String meetingId) {
		synchronized(meetings){
			Meeting meeting = get(meetingId);
			if(meeting==null){
				meeting=new WhiteboardMeeting(this, meetingId);
				meetings.put(meetingId, new SoftReference<Meeting>(meeting));
			}
			return meeting;
		}
	}
	
	public Meeting remove(String meetingId){
		SoftReference<Meeting> result = meetings.remove(meetingId);
		if(result==null)
			return null;
		else
			return result.get();
	}
	
	public Set<String> getMeetingIds(){
		return meetings.keySet();
	}

	public void kick(IoSession session) {
		String meetingId = (String)session.getAttribute(Attend.class);
		if(meetingId==null)
			return;
		Meeting meeting = get(meetingId);
		if(meeting!=null){
			meeting.remove(session);
		}
	}

	@Override
	public Meeting add(Meeting meeting) {
		SoftReference<Meeting> result = meetings.put(meeting.getMeetingId(), new SoftReference<Meeting>(meeting));
		if(result==null)
			return null;
		else
			return result.get();
	}
}
