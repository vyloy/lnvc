package com.lorent.whiteboard.server;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.textline.TextLineDecoder;
import org.apache.mina.filter.codec.textline.TextLineEncoder;

import com.lorent.whiteboard.server.text.TextDeserializer;
import com.lorent.whiteboard.server.text.TextSerializer;

public class SkippableTextProtocolCodecFilter extends SkippableProtocolCodecFilter{
	
	private static final Charset CHARSET = Charset.forName("utf-8");
	
	public SkippableTextProtocolCodecFilter(String id) {
		super(new JsonTextLineEncoder(CHARSET),new JsonTextLineDecoder(CHARSET),id);
	}

	@Override
	public void messageReceived(NextFilter nextFilter, IoSession session,
			Object message) throws Exception {
		if ((message instanceof IoBuffer)) {
			IoBuffer in = (IoBuffer) message;
			if(in.hasRemaining()){
				Object value = session.getAttribute(ACCEPTED_PROTOCOL);
				if (value == null) {
					int position = in.position();
					byte first = in.get();
					in.position(position);
					if(first == '{'){
						session.setAttributeIfAbsent(ACCEPTED_PROTOCOL, id);
						fireMessageReceived(nextFilter, session, message);
						return;
					}
				}else if(value == id){
					fireMessageReceived(nextFilter, session, message);
					return;
				}
			}
		}
        nextFilter.messageReceived(session, message);
	}
	
	private static class JsonTextLineDecoder extends TextLineDecoder {

		private final TextDeserializer textDeserializer;

		public JsonTextLineDecoder(Charset charset) {
			super(charset);
			setMaxLineLength(Integer.MAX_VALUE);
			textDeserializer = new TextDeserializer();
		}

		@Override
		protected void writeText(IoSession session, String text,
				ProtocolDecoderOutput out) {
			out.write(textDeserializer.deserialize(text));
		}
	}
	
	private static class JsonTextLineEncoder extends TextLineEncoder{
		private final TextSerializer textSerializer;

		public JsonTextLineEncoder(Charset charset) {
			super(charset);
			setMaxLineLength(Integer.MAX_VALUE);
			textSerializer = new TextSerializer();
		}

		@Override
		public void encode(IoSession session, Object message,
				ProtocolEncoderOutput out) throws Exception {
			super.encode(session, textSerializer.serialize(message), out);
		}
		
	}
}
