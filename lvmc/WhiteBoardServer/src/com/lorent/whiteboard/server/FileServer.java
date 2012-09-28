package com.lorent.whiteboard.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.lorent.fileserver.convertor.FileConvertors;

public class FileServer {
	
	public final static String AppPath = System.getProperty("user.dir").replace("\\", "/");
	public static int port = 0;
	
	private final NioSocketAcceptor acceptor = new NioSocketAcceptor();

	public FileServer() {
		acceptor.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		acceptor.getFilterChain().addLast("log", new LoggingFilter());

		acceptor.setHandler(new FileHandler());
		acceptor.setReuseAddress(true);
		
		port  = ServerConfig.getFileServerPort();
		FileConvertors.open();
	}
	
	public void bind() throws IOException{
		acceptor.bind(new InetSocketAddress(port));
	}
	
	public void bind(int port) throws IOException{
		acceptor.bind(new InetSocketAddress(port));
	}
	
	public void close(){
		acceptor.dispose();
		FileConvertors.close();
	}
	public void close(boolean awaitTermination){
		acceptor.dispose(awaitTermination);
		FileConvertors.close();
	}
}
