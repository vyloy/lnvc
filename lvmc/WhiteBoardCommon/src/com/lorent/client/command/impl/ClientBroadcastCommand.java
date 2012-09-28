package com.lorent.client.command.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lorent.client.model.MemberListUpdater;
import com.lorent.whiteboard.command.DelayedMeetingCommandAdaptor;
import com.lorent.whiteboard.command.Savable;
import com.lorent.whiteboard.model.Meeting;
import com.lorent.whiteboard.model.Updater;

public class ClientBroadcastCommand extends DelayedMeetingCommandAdaptor implements Savable{

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory
			.getLogger(ClientBroadcastCommand.class);
	
	protected static final AtomicLong ids=new AtomicLong();
	
	protected long commandId;
	
	protected transient volatile MemberListUpdater updater;
	
	protected byte[] updaterBytes;
	
	protected final long id=ids.incrementAndGet();
	
	protected final long createStamp = System.currentTimeMillis();
	
	public ClientBroadcastCommand(String meetingId,long commandId,MemberListUpdater updater) {
		super(meetingId);
		this.commandId=commandId;
		this.updater=updater;
		updater2Bytes();
	}

	@Override
	public void execute(Meeting meeting, IoSession session) {
		{
			Object resultCommand = session.getAttribute(this);
			if(resultCommand != null){
				session.write(resultCommand);
				logger.warn("Resend Missing resultCommand:{}",resultCommand);
				return;
			}
		}
		
		long oid=commandId;
		
		sync(meeting, session);
		
		CommandResult resultCommand = generateResult(meeting, session, oid);
		session.write(resultCommand);
		
		broadcast(meeting,session);
		
		session.setAttribute(this, resultCommand);
	}
	
	protected void broadcast(Meeting meeting, IoSession session){
		meeting.broadcast(session,this);
	}
	
	protected void sync(Meeting meeting, IoSession session){
		long id=this.commandId;
		long oid=id;
		while(!meeting.save(this)){
			logger.info("Command is not sychronized! command is {}",this);
			id = meeting.getCurrentCommandId()+1;
			this.setCommandId(id);
		}
		
		if(oid<id){
			for(;id-oid!=1;oid++){
				ClientBroadcastCommand broadcastCommand = meeting.getCommand(oid);
				if(broadcastCommand==null)
					throw new IllegalArgumentException("Id of command is Illegal!Id is "+id+"cid:"+oid);
				session.write(broadcastCommand);
			}
		}
	}
	
	/**
	 * 这个方法会调用{@link #updater}的{@link Updater#createResultCommand}方法生成结果<br>
	 * 并广播这条命令。
	 * @param board
	 * @param session
	 * @param oid
	 * @return
	 */
	protected CommandResult generateResult(Meeting meeting, IoSession session, long oid){
		CommandResult resultCommand=new CommandResult( oid, commandId);
		return resultCommand;
	}
	
	public long getCommandId() {
		return commandId;
	}
	
	public void setCommandId(long commandId) {
		this.commandId = commandId;
	}

	public MemberListUpdater getUpdater() {
		if(updater==null){
			try {
				ObjectInputStream in=new ObjectInputStream(new ByteArrayInputStream(updaterBytes));
				updater = (MemberListUpdater) in.readObject();
				updaterBytes=null;
				in.close();
			} catch (NullPointerException e) {
				return updater;
			} catch (Exception e) {
				throw new IllegalStateException("updater can not recover from bytes!",e);
			}
		}
		return updater;
	}

	public void setUpdater(MemberListUpdater updater) {
		this.updater = updater;
		updater2Bytes();
	}
	
	protected void updater2Bytes(){
		ObjectOutputStream out=null;
		try {
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			out = new ObjectOutputStream(byteStream);
			out.writeObject(updater);
			out.flush();
			updaterBytes=byteStream.toByteArray();
		} catch (IOException e) {
			throw new IllegalStateException("updater can not write to bytes!",e);
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					throw new RuntimeException("Can not close?",e);
				}
			}
		}
		
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
		ClientBroadcastCommand other = (ClientBroadcastCommand) obj;
		if (id != other.id)
			return false;
		if (timeStamp != other.timeStamp)
			return false;
		return true;
	}
	
}
