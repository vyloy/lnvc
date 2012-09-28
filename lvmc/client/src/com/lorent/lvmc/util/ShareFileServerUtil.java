/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.jhotdraw.samples.svg.SVGPanels;

import com.lorent.lvmc.controller.ControllerFacade;
import com.lorent.lvmc.event.MyEvent;
import com.lorent.lvmc.ui.LoadingFileDialog;
import com.lorent.sharefile.FileUtil;
import com.lorent.sharefile.ShareFileCommand;
import com.lorent.sharefile.ShareFileCommandResult;
import com.lorent.sharefile.client.ShareFileClient;
import com.lorent.sharescreen.RepeatCommand;
import com.lorent.whiteboard.client.Client;
import com.lorent.whiteboard.command.impl.LoadFile;

/**
 *
 * @author Administrator
 */
public class ShareFileServerUtil {
    
    static {
        ShareFileServerUtil.getInstance();
    }
    
    private String DOWNLOAD = "DOWNLOAD";
    private String DOWNLOADCANCEL = "DOWNLOADCANCEL";
    private String UPLOADCANCEL = "UPLOADCANCEL";
    
    private static Logger log = Logger.getLogger(ShareFileServerUtil.class);
    private static ShareFileServerUtil instance = null;
    
    private NioSocketConnector nioSocketConnector = null;
    private NioSocketConnector connectorKeepAlive = null;
    
    private IoSession session = null;
    private IoSession keepAliveSession = null;
//    private ArrayBlockingQueue eventNewList = new ArrayBlockingQueue(1000);
    private ExecutorService pool = Executors.newCachedThreadPool();
    
//    public static ConcurrentHashMap<String,Object[]> downLoadProgressConcurrentHashMap = new ConcurrentHashMap<String, Object[]>();//uniqueFileID,[uniqueFileID,position,filesize,true]
//    public static ConcurrentHashMap<String,Object[]> upLoadProgressConcurrentHashMap = new ConcurrentHashMap<String, Object[]>();
    
    //[download/upload,uniqueFileID,position,filesize,true]
    private ArrayBlockingQueue<Object[]> progressBlockingQueue = new ArrayBlockingQueue<Object[]>(2000);
    
    private ConcurrentHashMap<String, Boolean> processStateMap = new ConcurrentHashMap<String, Boolean>();
    
    private volatile int threadFlag = 0;
    private boolean isNewFlag = true;
    private List<Thread> threadList = new ArrayList<Thread>();
    
    private ShareFileServerUtil(){
        nioSocketConnector = new NioSocketConnector();
        nioSocketConnector.setConnectTimeoutMillis(30 * 1000L);
//        connector.getFilterChain().addLast("log", new LoggingFilter());
        nioSocketConnector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        SocketSessionConfig sessionConfig = nioSocketConnector.getSessionConfig();
        sessionConfig.setUseReadOperation(true);
        
        nioSocketConnector.setHandler(new IoHandlerAdapter());    
        
        connectorKeepAlive = new NioSocketConnector();
        connectorKeepAlive.setConnectTimeoutMillis(30 * 1000L);
        connectorKeepAlive.getFilterChain().addLast("codec",new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        connectorKeepAlive.setHandler(new IoHandlerAdapter(){

            @Override
            public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
                log.debug("exceptionCaught:"+session);
//                keepAliveSession = null;
            }

            @Override
            public void messageReceived(IoSession session, Object message) throws Exception {
                log.info("messageReceived: "+message);
                if (message instanceof ShareFileCommand) {
                    getInstance().processCommand((ShareFileCommand)message);
                }
                else if(message instanceof  ShareFileCommandResult){
                    getInstance().processResult((ShareFileCommandResult)message);
                }
                else if(message instanceof RepeatCommand){
                    getInstance().processCommand((RepeatCommand)message);
                }
                super.messageReceived(session, message);
            }
            
        });
        
        new Thread(){

        	
			@Override
			public void run() {
				this.setName("ShareFileServerUtil.Thread");
				MyEvent downLoadevent = new MyEvent();
                downLoadevent.setId("downLoadShareFileProgress");
                MyEvent upLoadevent = new MyEvent();
                upLoadevent.setId("upLoadShareFileProgress");
                Object[] downParameters = new Object[4];
                Object[] upParameters = new Object[5];
                do {
                	try {
                		Object[] poll = progressBlockingQueue.poll(1000, TimeUnit.MILLISECONDS);
                		if (poll != null) {
//                			Thread.sleep(500);
                			if (poll[0].equals("download")) {
                				downParameters[0] = poll[1];
                				downParameters[1] = poll[2];
                				downParameters[2] = poll[3];
                				downParameters[3] = poll[4];
                				downLoadevent.setParas(downParameters);
                                MessageUtil.getInstance().sendMessage(downLoadevent);
    						}
                    		else if (poll[0].equals("upload")) {
                    			upParameters[0] = poll[1];
                    			upParameters[1] = poll[2];
                    			upParameters[2] = poll[3];
                    			upParameters[3] = poll[4];
                    			upParameters[4] = poll[5];
                    			upLoadevent.setParas(upParameters);
                                MessageUtil.getInstance().sendMessage(upLoadevent);
    						}
                    		else if(poll[0].equals(DOWNLOADCANCEL)){
                    			MyEvent downLoadCancelevent = new MyEvent();
                    			downLoadCancelevent.setId("cancelDownLoadShareFileProgress");
                    			Object[] parameters = new Object[1];
                    			parameters[0] = poll[1];
                    			downLoadCancelevent.setParas(parameters);
                    			MessageUtil.getInstance().sendMessage(downLoadCancelevent);
                    		}
                    		else if(poll[0].equals(UPLOADCANCEL)){
                    			MyEvent upLoadCancelevent = new MyEvent();
                    			upLoadCancelevent.setId("cancelUpLoadShareFileProgress");
                    			Object[] parameters = new Object[1];
                    			parameters[0] = poll[1];
                    			upLoadCancelevent.setParas(parameters);
                    			MessageUtil.getInstance().sendMessage(upLoadCancelevent);
                    		}
						}
					} catch (Exception e) {
						log.error("ShareFileServerUtil.Thread", e);
						e.printStackTrace();
					}
                	
                } while (true);
				
			}
        	
        	
        }.start();
        
//        new SwingWorker<Object, Object>(){
//                @Override
//            protected Object doInBackground() throws Exception {
//                
//            }
//        }.execute();
        
    }
    
    public void processResult(ShareFileCommandResult result) throws Exception{
    	log.info("processResult "+result);
        if (result.getValue("FileNameArray") != null) {
            Object[] names = (Object[]) result.getValue("FileNameArray");
            if (names != null) {
                String[] newnames = new String[names.length];
                for (int i = 0; i < names.length; i++) {
                    newnames[i] = (String) names[i];
                }
                MyEvent event = new MyEvent();
                event.setId("serverReturnFileNames");
                event.setParas(new Object[]{newnames});
                MessageUtil.getInstance().sendMessage(event);
            }
        }
        else if(result.getValue("ResultID") != null){
        	Object value = result.getValue("ResultID");
        	if (value != null) {
				if (value.equals("GetRepeaterID")) {
					Map<String, Integer> repeaters = (Map<String, Integer>) result.getValue("RepeaterIDs");
					ShareFileCommand command = (ShareFileCommand) result.getValue("OriginalCommand");
					
					ControllerFacade.execute("shareDesktopController", "getRepeaterIDCallBack", repeaters);
				}
			}
        }
    }
    
    public void processCommand(RepeatCommand command) throws Exception{
        if (command.getOperation().equals("askforControlDesktop")) {
            log.info("processCommand: askforControlDesktop "+command);
            ControllerFacade.execute("shareDesktopController", "showConfirmControlDesktopDialog", command.getParameter("username"),command.getParameter("usernickname"));
        }
        else if(command.getOperation().equals("agreeControlDesktop")){
            log.info("processCommand: agreeControlDesktop "+command);
            ControllerFacade.execute("shareDesktopController", "remoteAgreeControlDesktop", command.getParameter("username"));
        }
        else if(command.getOperation().equals("startScreenShareToOneClient")){
            log.info("processCommand: startScreenShareToOne "+command);
            ControllerFacade.execute("shareDesktopController", "startScreenShareToOneClientFromSharer", command.getParameter("SharerUserName"),command.getParameter("connid"),command.getParameter("Args"));
        }
        else if(command.getOperation().equals("startScreenShareToOneClientCallBack")){
            log.info("processCommand: startScreenShareToOneClientCallBack "+command);
            ControllerFacade.execute("shareDesktopController", "startScreenShareToOneClientCallBack", command.getParameter("SharerUserName"),command.getParameter("UserName"),command.getParameter("Status"),command.getParameter("RepeaterID"));
        }
        else if(command.getOperation().equals("notAgreeControlDesktop")){
        	log.info("processCommand: notAgreeControlDesktop "+command);
            ControllerFacade.execute("shareDesktopController", "stopControlRemoteDesktop", command.getParameter("username"));
        }
    }
    
    public void processCommand(ShareFileCommand command) throws Exception{
        if (command.getOperation().equals("ServerCallClientGetFileList")) {
            ControllerFacade.execute("shareFileListController", "serverCallClientGetFileList");
        }else if(command.getOperation().equals("startScreenShare")){
            String ip = (String)command.getParameter("ip");
            int port = (Integer)command.getParameter("port");
            String passwd = (String)command.getParameter("passwd");
            String username = (String)command.getParameter("username");
            Map<String, Integer> repeaterids = (Map<String, Integer>) command.getParameter("repeaterids");
            log.info("receive startScreenShare:ip=" + ip + "&port=" + port + "&passwd=" + passwd + "&username=" + username);
            ControllerFacade.execute("shareDesktopController", "createDesktopView", ip,port,passwd,username,repeaterids);
        }else if(command.getOperation().equals("stopScreenShare")){
            String username = (String)command.getParameter("username");
            log.info("receive stopScreenShare:username=" + username);
            ControllerFacade.execute("shareDesktopController", "closeDesktopView", username);
        }
    }
    
    public long getServerFileSize(String meetingID,String serverFileName,IoSession session) throws Exception{
        try{
        	ShareFileCommand shareFileCommand = new ShareFileCommand(meetingID);
            
            shareFileCommand.setParameter("FileName", serverFileName);
            shareFileCommand.setOperation("GetServerFileSize");
            shareFileCommand.setParameter("UserName", DataUtil.getLoginInfo().getUsername());
            session.write(shareFileCommand);
            ReadFuture read = session.read();
            if (read.awaitUninterruptibly(10, TimeUnit.SECONDS)){
                ShareFileCommandResult result = (ShareFileCommandResult) read.getMessage();
                return (Long) result.getValue("FileSize");
            }else{
                throw new Exception(StringUtil.getErrorString("getFileSize.error"));
            }
        }catch(Exception ex){
        	throw ex;
        }finally{
        	close(session);
        }
    	
    }
    
    
    public void deleteFile(String meetingID,String serverFileName,IoSession session) throws Exception{
    	try{
    		ShareFileCommand shareFileCommand = new ShareFileCommand(meetingID);
            
            shareFileCommand.setParameter("FileName", serverFileName);
            shareFileCommand.setOperation("deleteFile");
            shareFileCommand.setParameter("UserName", DataUtil.getLoginInfo().getUsername());
            session.write(shareFileCommand);
    	}catch(Exception ex){
        	throw ex;
        }finally{
        	close(session);
        }
    	
    }
    
    public void setTransferState(String uniqueFileID,Boolean bRunning) throws Exception{
    	processStateMap.put(uniqueFileID, bRunning);
    }
    
    public void removeTransferState(String uniqueFileID) throws Exception{
    	processStateMap.remove(uniqueFileID);
    }
    
    public void downLoadFileFromServer(String uniqueFileID,String meetingID,String serverFileName,String localPath,IoSession session) throws Exception{
        try{
        	MyEvent event = new MyEvent();
            event.setId("downLoadShareFileProgress");
//                    event.setParas(new Object[]{});
            
            
            ShareFileCommand shareFileCommand = new ShareFileCommand(meetingID);
            
            shareFileCommand.setParameter("FileName", serverFileName);
            shareFileCommand.setOperation("GetServerFileSize");
            shareFileCommand.setParameter("UserName", DataUtil.getLoginInfo().getUsername());
            session.write(shareFileCommand);
            ReadFuture read = session.read();
            if (read.awaitUninterruptibly(10, TimeUnit.SECONDS)) {
                //            finishedLength = finishedLength + offset;
                ShareFileCommandResult result = (ShareFileCommandResult) read.getMessage();
                long filesize = (Long) result.getValue("FileSize");
                log.info("download getsize:" + result.getValue("FileSize"));
                long position = 0;
                File file = new File(localPath + ".tmp");
                if (file.exists()) {
                    FileUtil.deleteFile(localPath + ".tmp");
                }
                ShareFileCommand getFileCommand = new ShareFileCommand(meetingID);
                getFileCommand.setOperation("ClientDownLoadFileFromServer");
                getFileCommand.setParameter("FileName", serverFileName);
                getFileCommand.setParameter("RealFileName", (String)result.getValue("RealFileName"));
                getFileCommand.setParameter("readBufferSize", 65536);
                Object[] parameters  = new Object[]{"download",uniqueFileID,position,filesize,true};
                
                byte[] writeBuffer;
                do {
                	if (processStateMap.get(uniqueFileID) == null || processStateMap.get(uniqueFileID) == false) {
                		parameters = new Object[]{DOWNLOADCANCEL,uniqueFileID};
                		progressBlockingQueue.offer(parameters);
						break;
					}
                	long time = System.currentTimeMillis();
                    getFileCommand.setParameter("filePosition", position);
                    getFileCommand.setParameter("time", time);
                    log.debug(time + ":download realFileName =============" + (String)result.getValue("RealFileName"));
                    session.write(getFileCommand);
                    ReadFuture read1 = session.read();
                    if (read1.awaitUninterruptibly(20, TimeUnit.SECONDS)) {
                        ShareFileCommandResult result1 = (ShareFileCommandResult) read1.getMessage();
                        byte[] buffer = (byte[]) result1.getValue("buffer");
                        int offset = (Integer) result1.getValue("offset");
                        
                        if (offset == -1) {
                            File file1 = new File(localPath);
                            if (file1.exists()) {
                                file1.delete();
                            }
                            //重命名文件
                            file.renameTo(new File(localPath));
                            break;
                        }
                        writeBuffer = new byte[offset];
                        System.arraycopy(buffer, 0, writeBuffer, 0, offset);
                        position = position + offset;
                        parameters[2] = position;
//                        event.setParas(parameters);
//                        MessageUtil.getInstance().sendMessage(event);
//                        downLoadProgressConcurrentHashMap.put(uniqueFileID, parameters);
                        progressBlockingQueue.offer(parameters);
//                        log.info("offset"+offset);
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                            fileOutputStream.write(writeBuffer);
                            fileOutputStream.close();
                        } catch (Exception e) {
                            log.error("downLoadFileFromServer", e);
                        }
                    }
                    else{
                        //超时
                        log.error("下载文件数据包 超时");
                        parameters[4] = false;
//                        event.setParas(parameters);
//                        MessageUtil.getInstance().sendMessage(event);
//                        downLoadProgressConcurrentHashMap.put(uniqueFileID, parameters);
                        progressBlockingQueue.offer(parameters);
                        break;
                    }
                } while (true);
                
            } else {
                //超时
                log.info("获得文件大小 超时");
            }
        }catch(Exception ex){
        	throw ex;
        }finally{
        	close(session);
        }
        
    }
    
    public void loadFileToBoard(final String meetingID,final String serverFileName,IoSession session) throws Exception{
//    	try{
//    		ShareFileCommand shareFileCommand = new ShareFileCommand(meetingID);
//        	
//        	shareFileCommand.setParameter("FileName", serverFileName);
//        	shareFileCommand.setOperation("LoadToWhiteBoard");
//        	shareFileCommand.setParameter("UserName", DataUtil.getLoginInfo().getUsername());
//        	session.write(shareFileCommand);
//    	}catch(Exception ex){
//        	throw ex;
//        }finally{
//        	close(session);
//        }
    	SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				LoadingFileDialog d = new LoadingFileDialog();
				LoadingFileDialog _d = (LoadingFileDialog) SVGPanels.getInstance().putAttributeIfAbsent(serverFileName, d);
				if(_d==null){
					Client.getInstance().send(new LoadFile(meetingID,serverFileName));
					d.loading();
				}else{
					_d.loading();
				}
			}
		});
    }
    
    public String[] getFileListFromServer(String meetingID,IoSession session) throws Exception{
    	try{
    		String[] result = null;
            ShareFileCommand shareFileCommand = new ShareFileCommand(meetingID);
            shareFileCommand.setOperation("GetFileList");
            shareFileCommand.setParameter("UserName", DataUtil.getLoginInfo().getUsername());
            keepAliveSession.write(shareFileCommand);
            /*
            session.write(shareFileCommand);
            ReadFuture read = session.read();
            if (read.awaitUninterruptibly(20, TimeUnit.SECONDS)) {
                
                ShareFileCommandResult shareFileCommandResult = (ShareFileCommandResult) read.getMessage();
                Object[] names = (Object[]) shareFileCommandResult.getValue("FileNameArray");
                if (names != null) {
                    result = new String[names.length];
                    for (int i = 0; i < names.length; i++) {
                        result[i] = (String) names[i];
                    }
                }
            } else {
                //超时
                log.error("获得文件列表超时");
//                throw new Exception("超时");
            }
            */
            return result;
    	}catch(Exception ex){
        	throw ex;
        }finally{
        	close(session);
        }
        
    }
    
    public void sendFileToServer(String uniqueFileID,String meetingID,String filenPath,IoSession session) throws Exception{
        sendFileToServer(uniqueFileID, meetingID, filenPath, false,session);
    }
    
    public void sendFileToServer(String uniqueFileID,String meetingID,String filenPath,boolean loadToWhiteBoard,IoSession session) throws Exception{
    	try{
    		log.info("发送文件:"+filenPath+",meetingID:"+meetingID+",uniqueFileID:"+uniqueFileID+",loadToWhiteBoard:"+loadToWhiteBoard);
        	MyEvent event = new MyEvent();
        	event.setId("upLoadShareFileProgress");
        	
        	ShareFileCommand shareFileCommand = new ShareFileCommand(meetingID);
        	
        	File file = new File(filenPath);
        	FileInputStream fileInputStream = new FileInputStream(file);
        	long length = file.length();
        	long finishedLength = 0;
        	byte[] buf = new byte[8192]; 
        	int offset = 0;
        	Object[] parameters  = new Object[]{"upload",uniqueFileID, filenPath, finishedLength, length, false};
        	while (true) {            
        		if (processStateMap.get(uniqueFileID) == null || processStateMap.get(uniqueFileID) == false) {
            		parameters = new Object[]{UPLOADCANCEL,uniqueFileID};
            		progressBlockingQueue.offer(parameters);
					break;
				}
        		shareFileCommand.setParameter("FilePath", filenPath);
        		shareFileCommand.setParameter("UniqueFileID", uniqueFileID);
        		offset = fileInputStream.read(buf);
        		if (offset == -1) {
        			shareFileCommand.setOperation("ClientUpFileToServerFinished");
        			shareFileCommand.setParameter("UserName", DataUtil.getLoginInfo().getUsername());
        			if(loadToWhiteBoard){
        				shareFileCommand.setParameter("loadToWhiteBoard", loadToWhiteBoard);
        			}
        			session.write(shareFileCommand).awaitUninterruptibly();
        			break;
        		}
        		shareFileCommand.setParameter("Buffer", buf);
                shareFileCommand.setParameter("BufferSize",offset);
        		shareFileCommand.setOperation("ClientUpFileToServer");
        		session.write(shareFileCommand).awaitUninterruptibly();
        		ReadFuture read = session.read();
        		if (read.awaitUninterruptibly(20, TimeUnit.SECONDS)) {
        			finishedLength  = finishedLength + offset;
//        			log.info("上传文件，服务器返回：..."+length+"" + read.getMessage()+",offset:"+offset+","+finishedLength);
        		} else {
        			//超时
        			log.info("上传文件失败: 超时");
//                        event.setParas(new Object[]{uniqueFileID, filenPath, finishedLength, length, false});
//                        MessageUtil.getInstance().sendMessage(event);
//                    upLoadProgressConcurrentHashMap.put(uniqueFileID, new Object[]{uniqueFileID, filenPath, finishedLength, length, false});
        			parameters[3] = finishedLength;
        			parameters[5] = false;
        			progressBlockingQueue.offer(parameters);
        			break;
        		}
//        		event.setParas(new Object[]{uniqueFileID,filenPath,finishedLength,length,true});
//        		MessageUtil.getInstance().sendMessage(event);
//                    upLoadProgressConcurrentHashMap.put(uniqueFileID, new Object[]{uniqueFileID,filenPath,finishedLength,length,true});
        		parameters[3] = finishedLength;
        		parameters[5] = true;
        		progressBlockingQueue.offer(parameters);
        	}
        	fileInputStream.close();
    	}catch(Exception ex){
        	throw ex;
        }finally{
        	close(session);
        }
    }
    
    public static ShareFileServerUtil getInstance(){
        if (instance == null) {
            instance = new ShareFileServerUtil();
        }
        return instance;
    }
    
    public void connectKeepAlive(final String hostname,final int port,final String meetingID,final String username) throws Exception{
        log.info("保持事件响应的连接");
//        ConnectFuture connect = connectorKeepAlive.connect(new InetSocketAddress(hostname, port));
//        ConnectFuture awaitUninterruptibly = connect.awaitUninterruptibly();
//        keepAliveSession = connect.getSession();
        
        
        Thread thread = new ShareFileThread(){
            
            @Override
            public void run() {
                do {
                    
                    if (keepAliveSession == null || (keepAliveSession != null && !keepAliveSession.isConnected())) {
                        ConnectFuture connect = connectorKeepAlive.connect(new InetSocketAddress(hostname, port));
                        if (connect.awaitUninterruptibly(10, TimeUnit.SECONDS)) {
                            if (connect.isConnected()) {
                                keepAliveSession = connect.getSession();
                                ControllerFacade.execute("shareFileListController", "serverCallClientGetFileList");
                                continue;
                            }
                        }
                        log.info("keepAlive currentstate: notalive meetingID:"+meetingID+",username:"+username);
                    } else {

                        ShareFileCommand shareFileCommand = new ShareFileCommand(meetingID);
                        shareFileCommand.setOperation("ClientKeepAliveConnect");
                        shareFileCommand.setParameter("UserName", username);
                        keepAliveSession.write(shareFileCommand);
                        log.info("keepAlive currentstate: alive meetingID:"+meetingID+",username:"+username);
                    }
                    try {
                        Thread.sleep(30000);
                    } catch (InterruptedException ex) {
                        log.error("connectKeepAlive.ShareFileThread", ex);
                    }
                } while (this.getFlag());
            }
        };
        threadList.add(thread);
        thread.start();
        
    }
    
    class ShareFileThread extends Thread{
        private boolean flag = true;
        public void setFlag(boolean flag){
            this.flag = flag;
        }
        public boolean getFlag(){
            return this.flag;
        }
    }
    
    public void connect(String hostname,int port) throws Exception{
        
        ConnectFuture future = nioSocketConnector.connect(new InetSocketAddress(hostname, port));
//        session = future.getSession();
//        session.setAttribute("1", "1");
        ConnectFuture awaitUninterruptibly = future.awaitUninterruptibly();
        session = future.getSession();
//        future.getSession().getCloseFuture().awaitUninterruptibly();
//        nioSocketConnector.dispose();
    }
    
    public IoSession getSession(){
    	ConnectFuture future = nioSocketConnector.connect(new InetSocketAddress(DataUtil.getLoginInfo().getServerIP(), Integer.parseInt(ConfigUtil.getProperty("fileServerPort","8889"))));
    	ConnectFuture awaitUninterruptibly = future.awaitUninterruptibly();
    	IoSession session = future.getSession();
    	return session;
    }
    
    public void close(IoSession session) throws Exception{
    	if(session!=null){
    		session.close(true);
    		session = null;
    	}
    }
    
    public void close() throws Exception{
        session.close(true);
        
//        nioSocketConnector.dispose();
    }
    
    public void closeSessionAndkeepAliveSession() throws Exception{
        if(session!=null){
            session.close(true);
        }
        if(keepAliveSession!=null){
            keepAliveSession.close(true);
        }
        for(Thread thread:threadList){
            if(thread instanceof ShareFileThread){
                ShareFileThread sthread = (ShareFileThread)thread;
                sthread.setFlag(false);
            }
        }
        threadList.clear();
    }
    
    public boolean getRepeaterIDs(String confno,String username,String[] targetusers) throws Exception{
    	log.info("getRepeaterIDs username:"+username+" targetusers:"+targetusers);
    	ShareFileCommand shareFileCommand = new ShareFileCommand(confno);
        shareFileCommand.setOperation("GetRepeaterIDs");
        shareFileCommand.setParameter("username", username);
        shareFileCommand.setParameter("targetusers", targetusers);
        keepAliveSession.write(shareFileCommand);
    	return true;
    }
    
    public boolean startScreenShare(String confno, String username,Map<String, Integer> repeaterids) throws Exception{
        ShareFileCommand shareFileCommand = new ShareFileCommand(confno);
        shareFileCommand.setOperation("startScreenShare");
        String ip = InetAddress.getLocalHost().getHostAddress();
        int port = ConfigUtil.getIntProperty("screenSharePort");
        String passwd = ConfigUtil.getProperty("screenSharePasswd");
        log.info("startScreenShare:ip=" + ip + "&port=" + port + "&passwd=" + passwd + "&username=" + username);
        shareFileCommand.setParameter("ip", ip);
        shareFileCommand.setParameter("port", port);
        shareFileCommand.setParameter("passwd", passwd);
        shareFileCommand.setParameter("username", username);
        shareFileCommand.setParameter("repeaterids", repeaterids);
        keepAliveSession.write(shareFileCommand);
        return true;
    }
    
    public void startScreenShareToOneClient(String confno,String remoteUserName,String connid,String[] args) throws Exception {
    	log.info("startScreenShareToOneClient:"+confno+",remoteUserName="+remoteUserName+",connid=" + connid);
    	RepeatCommand repeatCommand = new RepeatCommand(confno,remoteUserName);
    	repeatCommand.setOperation("startScreenShareToOneClient");
    	
    	String ip = InetAddress.getLocalHost().getHostAddress();
        int port = ConfigUtil.getIntProperty("screenSharePort");
        String passwd = ConfigUtil.getProperty("screenSharePasswd");
    	
        
        repeatCommand.setParameter("SharerUserName", DataUtil.getLoginInfo().getUsername());
        repeatCommand.setParameter("connid", connid);
        repeatCommand.setParameter("ip", ip);
        repeatCommand.setParameter("port", port);
        repeatCommand.setParameter("passwd", passwd);
        repeatCommand.setParameter("Args", args);
        keepAliveSession.write(repeatCommand);
	}
    
    public void  startScreenShareToOneClientCallBack(String confno,String sharerUserName,String UserName,String status,String repeaterid) throws Exception {
    	log.info("startScreenShareToOneClientCallBack:"+confno+",sharerUserName="+sharerUserName+",UserName=" + UserName);
    	RepeatCommand repeatCommand = new RepeatCommand(confno,sharerUserName);
    	repeatCommand.setOperation("startScreenShareToOneClientCallBack");
        repeatCommand.setParameter("SharerUserName", sharerUserName);
        repeatCommand.setParameter("UserName", UserName);
        repeatCommand.setParameter("Status", status);
        repeatCommand.setParameter("RepeaterID", repeaterid);
        keepAliveSession.write(repeatCommand);
	}
    
    
    public boolean stopScreenShare(String confno, String username) throws Exception{
        ShareFileCommand shareFileCommand = new ShareFileCommand(confno);
        shareFileCommand.setOperation("stopScreenShare");
        log.info("stopScreenShare:username=" + username);
        shareFileCommand.setParameter("username", username);
        keepAliveSession.write(shareFileCommand);
        return true;
    }
    
    public void askforControlDesktop(String confno,String remoteUserName,String username,String usernickname) throws Exception{
        RepeatCommand repeatCommand = new RepeatCommand(confno,remoteUserName);
//        ShareFileCommand shareFileCommand = new ShareFileCommand(confno);
        repeatCommand.setOperation("askforControlDesktop");
        log.info("askforControlDesktop:confno:"+confno+",remoteUserName="+remoteUserName+",username=" + username+",usernickname="+usernickname);
        repeatCommand.setParameter("remoteUserName", remoteUserName);
        repeatCommand.setParameter("username", username);
        repeatCommand.setParameter("usernickname", usernickname);
        keepAliveSession.write(repeatCommand);
    }
    
    public void agreeControlDesktop(String confno,String askforUserName,String username,String usernickname) throws Exception{
        RepeatCommand repeatCommand = new RepeatCommand(confno,askforUserName);
//        ShareFileCommand repeatCommand = new ShareFileCommand(confno);
        repeatCommand.setOperation("agreeControlDesktop");
        log.info("agreeControlDesktop:askforUserName="+askforUserName+",username=" + username+",usernickname="+usernickname);
        repeatCommand.setParameter("askforUserName", askforUserName);
        repeatCommand.setParameter("username", username);
        repeatCommand.setParameter("usernickname", usernickname);
        keepAliveSession.write(repeatCommand);
    }
    
    public void notAgreeControlDesktop(String confno,String askforUserName,String username,String usernickname) throws Exception{
    	RepeatCommand repeatCommand = new RepeatCommand(confno,askforUserName);
    	repeatCommand.setOperation("notAgreeControlDesktop");
    	log.info("notAgreeControlDesktop:askforUserName="+askforUserName+",username=" + username+",usernickname="+usernickname);
    	repeatCommand.setParameter("askforUserName", askforUserName);
    	repeatCommand.setParameter("username", username);
    	repeatCommand.setParameter("usernickname", usernickname);
    	keepAliveSession.write(repeatCommand);
    }
    
    public static void main(String[] args){
        /*
        try {
            ShareFileServerUtil.getInstance();
            ShareFileServerUtil.getInstance().connect("127.0.0.1", 8889);
            //            ShareFileServerUtil.getInstance().sendFileToServer("10000","C:\\debug.zip");
            //            ShareFileServerUtil.getInstance().downLoadFileFromServer("10000", "debug.zip", "D:\\debut123.zip");
            String[] fileListFromServer = ShareFileServerUtil.getInstance().getFileListFromServer("10000");
            for (int i = 0; i < fileListFromServer.length; i++) {
                System.out.println("name:"+fileListFromServer[i]);
            }
            ShareFileServerUtil.getInstance().close();
              
        } catch (Exception ex) {
            log.error(ex);
        }
        */
        ShareFileCommand shareFileCommand = new ShareFileCommand("576603");
        shareFileCommand.setOperation("RemoveConferenceFiles");
        ShareFileClient client = ShareFileClient.newInstance();
        try {
            client.connnect("10.168.250.12", 8889);
            client.sendCommand(shareFileCommand);
            client.close();
        } catch (Exception e) {
            log.error(e);
        }
        log.info("test");
    }
}
