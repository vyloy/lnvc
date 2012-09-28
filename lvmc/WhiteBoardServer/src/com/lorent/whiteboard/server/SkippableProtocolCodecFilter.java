package com.lorent.whiteboard.server;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class SkippableProtocolCodecFilter extends ProtocolCodecFilter{
	
	protected static final String ACCEPTED_PROTOCOL = "accepted_protocol";
	protected final String id;
	
	public SkippableProtocolCodecFilter(ProtocolCodecFactory factory,String id) {
		super(factory);
		this.id=id;
	}

	public SkippableProtocolCodecFilter(ProtocolEncoder encoder,
			ProtocolDecoder decoder,String id) {
		super(encoder, decoder);
		this.id=id;
	}

	@Override
	public void messageReceived(NextFilter nextFilter, IoSession session,
			Object message) throws Exception {
		Object value = session.getAttribute(ACCEPTED_PROTOCOL);
		if(value==null){
			session.setAttributeIfAbsent(ACCEPTED_PROTOCOL, id);
			super.messageReceived(nextFilter, session, message);
		}else if(value==id){
			super.messageReceived(nextFilter, session, message);
		}else{
			nextFilter.messageReceived(session, message);
		}
	}

	@Override
	public void messageSent(NextFilter nextFilter, IoSession session,
			WriteRequest writeRequest) throws Exception {
		Object value = session.getAttribute(ACCEPTED_PROTOCOL);
		if (value != null && value != id) {
			nextFilter.messageSent(session, writeRequest);
			return;
		}
		super.messageSent(nextFilter, session, writeRequest);
	}

	@Override
	public void filterWrite(NextFilter nextFilter, IoSession session,
			WriteRequest writeRequest) throws Exception {
		Object value = session.getAttribute(ACCEPTED_PROTOCOL);
		if (value != null && value != id) {
			nextFilter.filterWrite(session, writeRequest);
			return;
		}
		super.filterWrite(nextFilter, session, writeRequest);
	}

	@Override
	public void sessionClosed(NextFilter nextFilter, IoSession session)
			throws Exception {
		Object value = session.getAttribute(ACCEPTED_PROTOCOL);
		if (value != null && value != id) {
			nextFilter.sessionClosed(session);
			return;
		}
		super.sessionClosed(nextFilter, session);
	}

	protected void fireMessageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception{
		super.messageReceived(nextFilter, session, message);
	}
}
