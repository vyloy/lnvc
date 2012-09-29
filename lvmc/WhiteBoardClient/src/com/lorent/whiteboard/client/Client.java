package com.lorent.whiteboard.client;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Constructor;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;

import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.SynchronizedProtocolDecoder;
import org.apache.mina.filter.codec.serialization.ObjectSerializationDecoder;
import org.apache.mina.filter.codec.serialization.ObjectSerializationEncoder;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lorent.client.command.impl.ClientBroadcastCommand;
import com.lorent.client.command.impl.ClientSyncCommand;
import com.lorent.client.command.impl.CommandResult;
import com.lorent.client.model.ClientCommandsManager;
import com.lorent.client.model.MemberListUpdater;
import com.lorent.client.model.MissingCommander;
import com.lorent.whiteboard.command.ClientRunnable;
import com.lorent.whiteboard.command.MeetingCommand;
import com.lorent.whiteboard.command.ResultCommand;
import com.lorent.whiteboard.command.impl.Attend;
import com.lorent.whiteboard.command.impl.BroadcastCloseWhiteboardCommand;
import com.lorent.whiteboard.command.impl.BroadcastCommand;
import com.lorent.whiteboard.command.impl.Create;
import com.lorent.whiteboard.command.impl.Exit;
import com.lorent.whiteboard.command.impl.GetCurrentMeetingIds;
import com.lorent.whiteboard.command.impl.RemoveMeeting;
import com.lorent.whiteboard.command.impl.Shutdown;
import com.lorent.whiteboard.command.impl.SyncCheckCommand;
import com.lorent.whiteboard.model.CommandsManager;
import com.lorent.whiteboard.model.Initializable;
import com.lorent.whiteboard.model.Sendable;
import com.lorent.whiteboard.model.StateUpdater;
import com.lorent.whiteboard.model.Updater;

public class Client implements Sendable{
	protected static final Logger logger = LoggerFactory.getLogger(Client.class);

	protected static final int PORT = 8888;
	
	protected static final Object connectingLock=new Object();

	protected static volatile Client client;
	
	protected volatile NioSocketConnector connector;
	
	protected final CommandHandler handler = new CommandHandler();
	
	protected final SyncChecker checker = new SyncChecker();
	
	protected volatile boolean connecting=false;
	
	protected volatile CommandsManager manager;
	
	protected volatile ClientCommandsManager clientManager;

	protected volatile String ip = "127.0.0.1";

	protected volatile IoSession session;
	
	protected volatile String meetingId;
	
	static{
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {

			@Override
			public void uncaughtException(Thread t, Throwable e) {
				logger.error(t.getName() + " throw exception", e);
			}
		});
	}

	protected Client() {
		newConnector();
	}
	
	protected void newConnector(){
		if(connector!=null){
			connector.dispose(false);
		}
		connector= new NioSocketConnector();
		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new ObjectSerializationEncoder(),new SynchronizedProtocolDecoder(new ObjectSerializationDecoder())));
		connector.setHandler(handler);
	}

	public void setManager(CommandsManager manager) {
		this.manager=manager;
		whiteboardResender = new Resender(manager);
		whiteboardResender.start();
	}
	
	public void setClientCommandsManager(ClientCommandsManager clientManager) {
		this.clientManager = clientManager;
		clientResender = new Resender(clientManager);
		clientResender.start();
	}

	protected void connect(String ip){
		ConnectFuture future = connector
				.connect(new InetSocketAddress(ip, PORT));
		this.ip=ip;
		future.awaitUninterruptibly();

		try{
			session = future.getSession();
		}catch(Exception e){
			logger.error("Client.connect",e);
			try {
				tryToReconnect();
			} catch (InterruptedException e1) {
				throw new RuntimeException(e1);
			}
		}
		notifyForConnecting();
		logger.debug("connected.session is {}", session);
	}
	
	protected void connect(){
		ConnectFuture future = connector
				.connect(new InetSocketAddress(ip, PORT));

		future.awaitUninterruptibly();

		session = future.getSession();
		notifyForConnecting();
		logger.debug("connected.session is {}", session);
	}

	public void attend(final String meetingId) {
		if(meetingId==null)
			throw new IllegalArgumentException("meetingId can not be null!");
		if(this.meetingId!=null)
			throw new IllegalStateException("before attend the other meeting you must exit this meeting @"+this.meetingId);
		this.meetingId=meetingId;
		Attend attend = new Attend(meetingId);
		session.write(attend);
		logger.debug("attend meeting {}:{}", meetingId, attend);
		if(!checker.isAlive()&&manager!=null)checker.start();
	}
	
	protected void waitForConnecting() throws InterruptedException{
		if(connecting){
			synchronized(connectingLock){
				while(connecting){
					connectingLock.wait();
				}
			}
		}
	}
	
	protected void connecting(){
		synchronized (connectingLock) {
			MissingCommander m = manager;
			if(m!=null)
				m.clearMissingCommands();
			m = clientManager;
			if(m!=null)
				m.clearMissingCommands();
			
			connecting=true;
		}
	}
	private class GlassPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private volatile String state="正在同步中。。。";
		private BufferedImage background;
		private Component glassPane; 
		public GlassPanel(JRootPane rootPane){
			glassPane = rootPane.getGlassPane();
		}

		@Override
		public void setVisible(boolean isVisible) {
			boolean oldVisible = isVisible();
			super.setVisible(isVisible);
			JRootPane rootPane = SwingUtilities.getRootPane(this);
			if (rootPane != null && isVisible() != oldVisible) {
				rootPane.getLayeredPane().setVisible(!isVisible);
			}
		}

		@Override
		public void paintComponent(Graphics g) {
			if(background==null){
				JRootPane rootPane = SwingUtilities.getRootPane(this);
				if (rootPane != null) {
					background = new BufferedImage(rootPane.getLayeredPane().getWidth(), rootPane.getLayeredPane().getHeight(), BufferedImage.TYPE_INT_RGB);
					Graphics graphics = background.getGraphics();
					rootPane.getLayeredPane().print(graphics);
					graphics.dispose();
				}
			}
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(background, null, 0, 0);
			g2.setColor(new Color(100, 100, 100, 100));
			g2.fillRect(0, 0, getWidth(), getHeight());
			if(state!=null&&!state.isEmpty()){
				int w = g2.getFontMetrics().stringWidth(state);
				int h = g2.getFontMetrics().getHeight();
				g2.setColor(Color.black);
				g2.drawString(state, (getWidth()-w)/2, (getHeight()-h)/2);
			}
		}

		public void setState(String state) {
			this.state = state;
		}
		
	}

	private class ConnectThread extends Thread{
		
		public ConnectThread() {
			super("ConnectThread");
			setDaemon(true);
		}

		@Override
		public void run() {
			for(int i=0,times=1;!isInterrupted();times++){
				int waitTime=waitTimes[i];
				logger.info("Sleep {} seconds and then try to reconnect!",waitTime);
				try{
					for(int j=waitTime;j>=0;j--){
						updateReconnectPanelState(j+"秒后进行第"+times+"次重连。");
						TimeUnit.SECONDS.sleep(1);
					}
					newConnector();
					connect();
					attend();
				}catch(RuntimeException e){
					logger.error("There is an unexpected exception",e);
				} catch (InterruptedException e) {
					return;
				}
				if(Client.this.session.isConnected()&&!Client.this.session.isClosing()){
					logger.info("Reconnected!");
					connected();
					return;
				}
				if(i+1>=waitTimes.length)
					i=0;
				else
					i++;
			}
		}
		
		protected void updateReconnectPanelState(final String state){
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					try{
						glassPanel.setState(state);
						glassPanel.repaint();
					}catch(NullPointerException e){
						ConnectThread.this.interrupt();
					}
				}
			});
		}
		
		protected void connected(){
			updateReconnectPanelState("重连成功。正在同步中。。。");
		}
	}
	private static final int[] waitTimes={15,30,45,60,30};
	private void tryToReconnect() throws InterruptedException{
		connecting();
		IoSession s = session;
		if(s!=null)
			s.close(true);
		showGlassPanel();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Thread thread = new ConnectThread();
				thread.start();
				manager.removeAll();
			}
		});
	}
	
	private GlassPanel glassPanel;

	private Thread whiteboardResender;

	private Thread clientResender;
	
	private synchronized void showGlassPanel(){
		if(glassPanel==null&&manager!=null){
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					JRootPane rootPane = SwingUtilities.getRootPane(manager
							.getContainer());
					glassPanel = new GlassPanel(rootPane);
					rootPane.setGlassPane(glassPanel);
					glassPanel.setVisible(true);
				}
			});
		}
	}
	
	public synchronized void hideGlassPanel(){
		if(glassPanel==null)
			return;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if(glassPanel==null)
					return;
				glassPanel.setVisible(false);
				SwingUtilities.getRootPane(glassPanel).setGlassPane(
						glassPanel.glassPane);
				glassPanel = null;
			}
		});
	}
	
	protected void notifyForConnecting(){
		synchronized(connectingLock){
			connecting=false;
			connectingLock.notifyAll();
		}
	}
	
	public void attend() {
		if(meetingId==null)
			throw new IllegalArgumentException("meetingId can not be null!");
		String mid = meetingId;
		meetingId=null;
		attend(mid);
	}

	public void exit() {
		if(meetingId==null)
			throw new IllegalStateException("before exit the meeting you must be attended a meeting!");
		Exit exit = new Exit(meetingId);
		session.write(exit);
		this.meetingId=null;
		logger.debug("exit meeting {}:{}", meetingId, exit);
	}

	/**
	 * 广播命令
	 * @param object 命令
	 * @return 广播结果，true 为成功。
	 */
	public void broadcast(final Updater<?> object) {
		if(meetingId==null||session==null||manager==null)
			throw new NullPointerException("meetingId==null||session==null||manager==null");
		if(object instanceof Initializable){
			((Initializable) object).initialize();
		}
		BroadcastCommand command = manager.create(meetingId, object,ViewParam.getView());
		session.write(command);
		logger.debug("broadcast meeting {}:{}", meetingId, command);
	}
	
	public void broadcast(final MemberListUpdater updater) {
		broadcast(updater,false);
	}
	
	public void broadcast(final MemberListUpdater updater,final boolean simple) {
		if(session==null||meetingId==null||clientManager==null)
			throw new NullPointerException("session==null||meetingId==null||clientManager==null");
		ClientBroadcastCommand command;
		command = clientManager.create(meetingId,updater,simple);
		session.write(command);
		logger.debug("broadcast {}",command);
	}
	
	public void broadcast(final BroadcastCommand command) {
		if(session==null)
			throw new NullPointerException("session==null");
		session.write(command);
		logger.debug("broadcast {}",command);
	}
	
	/**
	 * 广播状态命令
	 * @param object 命令
	 * @return 广播结果，true 为成功。
	 */
	public void broadcastState(StateUpdater<?> object) {
		if(meetingId==null||session==null||manager==null)
			throw new NullPointerException("meetingId==null||session==null||manager==null");
		if(object instanceof Initializable){
			((Initializable) object).initialize();
		}
		BroadcastCommand command = manager.createState(meetingId, object,ViewParam.getView(),object.getStateType());
		session.write(command);
		logger.debug("broadcast meeting {}:{}", meetingId, command);
	}
	
	@Override
	public boolean send(MeetingCommand command){
		if(session==null)
			return false;
		session.write(command);
		logger.debug("broadcast {}",command);
		return true;
	}

	public void broadcastCloseWhiteboard(final String whiteboardId) {
		if(meetingId==null||session==null)
			throw new NullPointerException("meetingId==null||session==null");
		BroadcastCloseWhiteboardCommand command = manager.createCloseWhiteboardCommand(meetingId,whiteboardId);
		session.write(command);
		logger.debug("broadcast {}",command);
	}

	public void create(String meetingId) {
		Create create = new Create(meetingId);
		session.write(create);
		logger.debug("create meeting {}:{}", meetingId, create);
	}

	public void remove(String meetingId) {
		RemoveMeeting removeMeeting = new RemoveMeeting(meetingId);
		session.write(removeMeeting);
		logger.debug("remove meeting {}:{}", meetingId, removeMeeting);
	}

	public void close() {
		close(false);
	}

	public void close(final boolean immediately) {
		Thread close = new Thread(){
			{this.setDaemon(true);}
			@Override
			public void run() {
				_close(immediately);
			}
		};
		close.start();
	}
	
	protected void _close(boolean immediately){
		if(this.meetingId!=null){
			exit();
		}
		if(checker.isAlive())checker.interrupt();
		if(whiteboardResender!=null&&whiteboardResender.isAlive())whiteboardResender.interrupt();
		if(clientResender!=null&&clientResender.isAlive())clientResender.interrupt();
		IoSession _session=session;
		session=null;
		CloseFuture future = _session.close(immediately);
		if(!immediately){
			try {
				future.await();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		connector.dispose();
		client=null;
	}
	
	public void shutdownServer(){
		Shutdown shutdown = new Shutdown();
		session.write(shutdown);
		logger.debug("shutting down the server");
	}
	
	public void getMeetingIds(){
		GetCurrentMeetingIds c = new GetCurrentMeetingIds();
		session.write(c);
		logger.debug("getting meetingids");
	}
	
	public static void create(String ip,String meetingId){
		Client client=new Client();
		client.connect(ip);
		client.create(meetingId);
		client.close();
	}
	
	public static void remove(String ip,String meetingId){
		Client client=new Client();
		client.connect(ip);
		client.remove(meetingId);
		client.close();
	}
	
	public static void shutdownServer(String ip){
		Client client=new Client();
		client.connect(ip);
		client.shutdownServer();
		client.close();
	}
	
	public static void getMeetingIds(String ip){
		final Client client=new Client();
		client.connector.setHandler(new IoHandlerAdapter(){
			@Override
			public void messageReceived(IoSession session, Object message)
					throws Exception {
				if(message instanceof ResultCommand){
					((ResultCommand) message).subsequentlyRun(null);
				}
				client.close();
			}
		});
		client.connect(ip);
		client.getMeetingIds();
	}
	
	/**
	 * 返回当前Client实例，在调用此方法前，请确定已经调用{@link #newInstance()}方法。<br>
	 * 当{@link #newInstance()}还没被调用过时，此方法会被阻塞。
	 * @return 返回当前Client实例
	 */
	public static Client getInstance(){
		if(client==null){
			synchronized(Client.class){
				if(client!=null){
					return client;
				}
				try {
					Client.class.wait();
				} catch (InterruptedException e) {
					throw new IllegalStateException("client",e);
				}
			}
		}
		return client;
	}
	
	/**
	 * 新建Client实例，如果之前存在实例，会调用其{@link #close(boolean)}方法后，再新建。<br>
	 * 第一次调用后再调用时，应确保并发环境下，不会调用{@link #getInstance()}。
	 * @param ip4 服务器ip地址
	 * @param manager 
	 * @return 返回新建Client实例
	 */
	public synchronized static Client newInstance(String ip4, CommandsManager manager){
		if(client!=null){
			client.close(true);
		}
		Class<Client> c = ClientEnhancer.enhancedClient();
		try {
			Constructor<Client> constructor = c.getConstructor();
			constructor.setAccessible(true);
			client=constructor.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		client.setManager(manager);
		client.connect(ip4);
		Client.class.notifyAll();
		return client;
	}
	
	/**
	 * 新建Client实例，如果之前存在实例，会调用其{@link #close(boolean)}方法后，再新建。
	 * 第一次调用后再调用时，应确保并发环境下，不会调用{@link #getInstance()}。
	 * @return 返回新建Client实例
	 */
	public synchronized static Client newInstance(){
		if(client!=null){
			client.close(true);
		}
		client=new Client();
		client.connect();
		Client.class.notifyAll();
		return client;
	}

	public String getMeetingId() {
		return meetingId;
	}

	private class Resender extends Thread{
		private final MissingCommander commander;
		Resender(MissingCommander commander){
			this.setDaemon(true);
			this.commander=commander;
			this.setName("MissingCommandResender-"+commander.getClass().getSimpleName());
		}
		@Override
		public void run() {
			try {
				while(!Thread.currentThread().isInterrupted()){
					waitForConnecting();
					Object missingCommand = commander.getMissingCommand();
					IoSession s = session;
					if(s!=null&&missingCommand!=null){
						s.write(missingCommand);
						logger.info("resend command {}",missingCommand);
					}
				}
			} catch (InterruptedException e) {
			}
		}
		
	}
	
	private class SyncChecker extends Thread{
		
		AtomicInteger remainingTime = new AtomicInteger(8);
		
		SyncChecker() {
			this.setDaemon(true);
			this.setName("SyncChecker");
		}
		
		@Override
		public void run() {
			try {
				waitForConnecting();
				showGlassPanel();
				SwingUtilities.invokeLater(new Runnable(){
					@Override
					public void run() {
						sendSyncCheckCommand();
					}
				});
				while(!Thread.currentThread().isInterrupted()){
					int c = remainingTime.get();
					remainingTime.compareAndSet(c, c-1);
					if(remainingTime.compareAndSet(0, 8)){
						waitForConnecting();
						sendSyncCheckCommand();
					}
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
			}
			
		}
		
		public void sendSyncCheckCommand(){
			resetRemainingTime();
			CommandsManager _manager = manager;
			String _meetingId = meetingId;
			IoSession s = session;
			if(_meetingId!=null&&_manager!=null&&s!=null){
				SyncCheckCommand message;
				ClientCommandsManager _clientManager = clientManager;
				if(_clientManager!=null){
					message = new ClientSyncCommand(_meetingId, _manager.getFutureCommandIds(),_clientManager.getFutureCommandIds(),_manager.getStateCommandIds());
				}else{
					message = new SyncCheckCommand(_meetingId, _manager.getFutureCommandIds(),_manager.getStateCommandIds());
				}
				s.write(message);
				logger.debug("synccheck {}",message);
			}
		}
		
		public void resetRemainingTime(){
			remainingTime.set(8);
		}
	}
	
	public void resetRemaingingTime(){
		checker.resetRemainingTime();
	}
	
	public class CommandHandler extends IoHandlerAdapter{

		private final Logger logger = LoggerFactory
				.getLogger(CommandHandler.class);
		
		@Override
		public synchronized void exceptionCaught(IoSession session, Throwable cause)
				throws Exception {
			if(cause instanceof IOException){
				if(session!=Client.this.session){
					return;
				}
				tryToReconnect();
				waitForConnecting();
				logger.debug("Reconnecting by exception",cause);
			}else{
				logger.error("There is an unexpected exception",cause);
			}
		}
		
		@Override
		public void messageReceived(IoSession session, Object message)
				throws Exception {
			CommandsManager _manager = manager;
			if(_manager==null){
				return;
			}
			if (message instanceof ClientRunnable) {
				_manager.execute((ClientRunnable) message);
			} else if (message instanceof BroadcastCommand) {
				_manager.execute((BroadcastCommand) message);
			} else if (message instanceof ResultCommand) {
				_manager.execute((ResultCommand) message);
			} else {
				ClientCommandsManager _clientManager = clientManager;
				if (message instanceof ClientBroadcastCommand) {
					if (_clientManager != null) {
						_clientManager.execute(((ClientBroadcastCommand) message));
					}
				} else if (message instanceof CommandResult) {
					if (_clientManager != null) {
						((CommandResult) message).subsequentRun(_clientManager);
					}
				}
			}
			logger.debug("{} run on the client, session is {}", message, session);
		}

		@Override
		public synchronized void sessionClosed(IoSession session) throws Exception {
			logger.debug("closed");
			if(Client.this.session==null||session!=Client.this.session||manager==null){
				return;
			}
			tryToReconnect();
			waitForConnecting();
		}

	}
}
