package com.lorent.sharefile.client;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.lorent.sharefile.ShareFileCommand;
import com.lorent.util.ConfigUtil;

public class ShareFileClient {
	
	private static ShareFileClient instance = null;
	
	private NioSocketConnector nioSocketConnector = null;
	private IoSession session = null;
	
	/**
	 * @return the session
	 */
	public IoSession getSession() {
		return session;
	}

	private ShareFileClient() {
		nioSocketConnector = new NioSocketConnector();
		nioSocketConnector.setConnectTimeoutMillis(30 * 1000L);
		nioSocketConnector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		nioSocketConnector.getFilterChain().addFirst("executor",
				new ExecutorFilter());
		SocketSessionConfig sessionConfig = nioSocketConnector.getSessionConfig();
		sessionConfig.setUseReadOperation(true);
		nioSocketConnector.setHandler(new IoHandlerAdapter());
	}
	
	public static ShareFileClient newInstance(){
		//instance = new ShareFileClient();
		return new ShareFileClient();
	}
	
	
	public  void connnect(String hostname,int port){
		ConnectFuture future = nioSocketConnector.connect(new InetSocketAddress(hostname, port));
		future.awaitUninterruptibly();
		session = future.getSession();
	}
	
	public void connect() throws Exception{
		String sPort = ConfigUtil.getProperty("FileServerPort");
		if (sPort == null || sPort.equals("")) {
			sPort = "8889";
		}
		connnect("127.0.0.1", Integer.parseInt(sPort));
	}
	
	public  void close(){
		session.close(true);
	}
	
	public void sendCommand(ShareFileCommand command){
		WriteFuture write = session.write(command);
		write.awaitUninterruptibly();
	}
	
	public ReadFuture readResult(){
		return session.read();
	}
}
