package com.lorent.lvmc.ucs;

import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class UCSClient {
	
	private static XmlRpcClient client;
	
	private static Logger log = Logger.getLogger(UCSClient.class);
	
	public static void init(int port)throws Exception{
		log.info("init : " + port);
    	XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
//        config.setServerURL(new URL("http://10.168.100.77:" + port + "/ucsRPC"));
        config.setServerURL(new URL("http://127.0.0.1:" + port + "/ucsRPC"));
        config.setEnabledForExtensions(true);
        config.setReplyTimeout(20 * 1000);
        client = new XmlRpcClient();
        client.setConfig(config);
	}
	
	private static final int REGISTER_OK = 0;
	private static final int REGISTER_FAIL = -1;
	private static final int REGISTER_UNREG_OK = 1;
	public static final int START_SUCCESS = 0;
	public static final int START_FAIL = -1;
	public static final int HEART = 1;
	
	
	public static void sendCommand(String cmd, Object...paras){
		try{
			client.execute(cmd, paras);
		}catch(Exception e){
			log.error(cmd, e);
		}
	}
	
	public static void sendRegisterOK(){
		log.info("sendRegisterOK");
		sendCommand("ucscb.registerstate", REGISTER_OK);
	}
	public static void sendRegisterFail(){
		log.info("sendRegisterFail");
		sendCommand("ucscb.registerstate", REGISTER_FAIL);
	}
	public static void sendUnRegisterOK(){
		log.info("sendUnRegisterOK");
		sendCommand("ucscb.registerstate", REGISTER_UNREG_OK);
	}	
	
	public static void sendMyState(int type)throws Exception{
		log.info("sendMyState : type = " + type);
		client.execute("ucscb.lvmcstate", new Object[]{type});
	}
	
	public static void sendIncoming(String username){
		log.info("sendIncoming : username = " + username);
		Start.calling = username;
		sendCommand("ucscb.incoming", username);
	}
	
	public static void sendConnected(String username){
		log.info("sendConnected : username = " + username);
		sendCommand("ucscb.connected", username);
	}
	
	public static void sendHangup(String username){
		log.info("sendHangup : username = " + username);
		sendCommand("ucscb.hangup", username);
	}
	
	public static void sendOtherHangup(String username){
		log.info("sendOtherHangup : username = " + username);
		sendCommand("ucscb.peerhangup", username);
	}
	
	public static void sendDataMessage(String msg, String username, int type, int status){
		log.info("sendDataMessage msg = " + msg + " & username = " + username + " & type = " + type + " & status = " + status);
		sendCommand("ucscb.updatemessage", msg, username, type, status);
	}
	
	public static void sendOutgoing(String username){
		log.info("sendOutgoing username = " + username);
		sendCommand("ucscb.outgoing", username);
	}
}
