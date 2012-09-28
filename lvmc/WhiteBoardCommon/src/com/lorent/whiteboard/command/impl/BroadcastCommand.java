package com.lorent.whiteboard.command.impl;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lorent.whiteboard.command.DelayedMeetingCommandAdaptor;
import com.lorent.whiteboard.command.Savable;
import com.lorent.whiteboard.command.WhiteboardCommand;
import com.lorent.whiteboard.model.Detailed;
import com.lorent.whiteboard.model.Identifiable;
import com.lorent.whiteboard.model.RemoteFigure;
import com.lorent.whiteboard.model.RemoteFigures;
import com.lorent.whiteboard.model.Updater;
import com.lorent.whiteboard.model.View;
import com.lorent.whiteboard.model.Whiteboard;

public class BroadcastCommand extends DelayedMeetingCommandAdaptor implements WhiteboardCommand, Savable,Detailed{

	private static final long serialVersionUID = 1L;
	
	public static final int TYPE = 200;
	
	private static final Logger logger = LoggerFactory
			.getLogger(BroadcastCommand.class);
	
	protected static final AtomicLong ids=new AtomicLong();
	
	protected volatile Updater<?> updater;
	
	protected long commandId;
	
	protected final long id=ids.incrementAndGet();
	
	protected final long createStamp = System.currentTimeMillis();
	
	protected String whiteboardId;
	
	protected volatile int page;
	
	protected BroadcastCommand(String meetingId,Updater<?> updater,String whiteboardId,int page) {
		super(meetingId);
		if(updater==null)
			throw new IllegalArgumentException("updater can not be null!");
		if(whiteboardId==null)
			throw new IllegalArgumentException("whiteboardId == null!");
		this.updater=updater;
		this.whiteboardId=whiteboardId;
		this.page=page;
	}
	
	public BroadcastCommand(String meetingId,Updater<?> updater,long commandId,String whiteboardId,int page) {
		super(meetingId);
		if(updater==null)
			throw new IllegalArgumentException("updater can not be null!");
		if(whiteboardId==null)
			throw new IllegalArgumentException("whiteboardId == null!");
		this.updater=updater;
		this.commandId=commandId;
		if(updater instanceof Identifiable){
			((Identifiable) updater).setIdentifying(commandId);
		}
		this.whiteboardId=whiteboardId;
		this.page=page;
	}

	public BroadcastCommand(String meetingId, String whiteboardId) {
		super(meetingId);
		this.whiteboardId=whiteboardId;
	}

	@Override
	public void execute(Whiteboard board, IoSession session) {
		{
			Object resultCommand = session.getAttribute(this);
			if(resultCommand != null){
				session.write(resultCommand);
				logger.warn("Resend Missing resultCommand:{}",resultCommand);
				return;
			}
		}
		
		long oid=commandId;
		
		sync(board, session);
		
		generateResult(board, session, oid);
		
	}
	
	/**
	 * 保存到白板中，但不写入硬盘，会根据当前命令commandId检查同步情况，当不同步时，会增量同步。
	 * @param board
	 * @param session
	 */
	protected void sync(Whiteboard board, IoSession session){
		long id=this.commandId;
		long oid=id;
		while(!board.save(this,false)){
			logger.info("Command is not sychronized! command is {}",this);
			id = board.getCurrentCommandId()+1;
			this.setCommandId(id);
		}
		
		while (oid < id) {
			BroadcastCommand broadcastCommand = board.getCommand(oid++);
			if (broadcastCommand == null)
				throw new IllegalArgumentException(
						"Id of command is Illegal!Id is " + id + ",cid:" + oid);
			session.write(broadcastCommand);
		}
	}
	
	/**
	 * 这个方法会调用{@link #updater}的{@link Updater#createResultCommand}方法生成结果。<br>
	 * 然后保存到硬盘，并广播。
	 * @param board
	 * @param session
	 * @param oid
	 * @return
	 */
	protected void generateResult(Whiteboard board, IoSession session, long oid){
		BroadcastResultCommand resultCommand=updater.createResultCommand(board, oid, commandId);
		board.saveToDatabase(this);
		board.getMeeting().broadcast(session,this);
		if(updater instanceof RemoteFigure ||updater instanceof RemoteFigures){
			session.write(this);
		}
		session.write(resultCommand);
		session.setAttribute(this, resultCommand);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends View> Updater<T> getUpdater(){
		return (Updater<T>) updater;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends View> Updater<T> peekUpdater() {
		return (Updater<T>) updater;
	}

	public long getCommandId() {
		return commandId;
	}
	
	public void setCommandId(long commandId) {
		this.commandId = commandId;
		if(updater instanceof Identifiable){
			((Identifiable) updater).setIdentifying(commandId);
		}
	}

	
	public String getWhiteboardId() {
		return whiteboardId;
	}

	public int getPage() {
		return page;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (timeStamp ^ (timeStamp >>> 32));
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
		BroadcastCommand other = (BroadcastCommand) obj;
		if (id != other.id)
			return false;
		if (timeStamp != other.timeStamp)
			return false;
		return true;
	}
	
	@Override
	public JSON toJSON(boolean detail) {
		JSONObject object = new JSONObject(true);
		object.put("type", TYPE+updater.getType());
		object.put("meetingId", meetingId);
		object.put("whiteboardId", whiteboardId);
		object.put("commandId", commandId);
		object.put("page", page);
		if(detail&&updater instanceof Detailed){
			object.put("updater", ((Detailed)updater).toJSON(true));
		}else{
			object.put("updater", updater.toJSON());
		}
		return object;
	}

	@Override
	public String toDetailedString() {
		return toJSON(true).toJSONString();
	}

	@Override
	public String toString() {
		return toJSON(false).toJSONString();
	}
}
