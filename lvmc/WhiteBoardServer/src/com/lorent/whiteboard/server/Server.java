package com.lorent.whiteboard.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.lorent.whiteboard.server.db.DatabaseFactory;

public class Server {
	private final CommandHandler handler = new CommandHandler();
	private final NioSocketAcceptor acceptor = new NioSocketAcceptor();
	private ExecutorService pool=Executors.newCachedThreadPool();

	public Server() {
		acceptor.getFilterChain().addLast("codec1",
				new SkippableTextProtocolCodecFilter("TEXT"));
		acceptor.getFilterChain().addLast("codec2",
				new SkippableProtocolCodecFilter(new ObjectSerializationCodecFactory(),"JAVA_SERIALIZE"));
		acceptor.getFilterChain().addLast("pool", new ExecutorFilter(pool));
		acceptor.getFilterChain().addLast("logger", new LoggingFilter("data"));
		acceptor.setHandler(handler);
		acceptor.setReuseAddress(true);
	}
	
	public void bind() throws IOException{
		acceptor.bind(new InetSocketAddress(8888));
	}
	
	public void bind(int port) throws IOException{
		acceptor.bind(new InetSocketAddress(port));
	}
	
	public void close(){
		close(false);
	}
	
	public void close(boolean awaitTermination){
		pool.shutdownNow();
		DatabaseFactory.close();
		acceptor.dispose(awaitTermination);
	}

	public CommandHandler getHandler() {
		return handler;
	}
	
}
