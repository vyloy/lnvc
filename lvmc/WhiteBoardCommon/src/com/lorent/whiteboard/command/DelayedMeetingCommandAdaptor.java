package com.lorent.whiteboard.command;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public abstract class DelayedMeetingCommandAdaptor extends MeetingCommandAdaptor implements Delayed{

	private static final long serialVersionUID = 1L;
	
	private static final long DELAY = 15000;
	
	protected transient volatile long timeStamp;
	
	protected DelayedMeetingCommandAdaptor(String meetingId) {
		super(meetingId);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends DelayedMeetingCommandAdaptor>void addToQueue(DelayQueue<T> queue){
		timeStamp=System.currentTimeMillis()+DELAY;
		queue.offer((T) this);
	}

	@Override
	public int compareTo(Delayed o) {
		long result=this.timeStamp-o.getDelay(TimeUnit.MILLISECONDS);
		return result>0?1:result<0?-1:0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(timeStamp-System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}

}
