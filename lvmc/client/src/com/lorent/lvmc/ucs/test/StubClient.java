package com.lorent.lvmc.ucs.test;

import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import com.lorent.common.util.LCMUtil;
import com.lorent.lvmc.util.ConfigUtil;
import com.lorent.util.LCCUtil;

public class StubClient {
	
	private static int port;
	private static XmlRpcClient client;
	
	private static Logger log = Logger.getLogger(StubClient.class);
	
	public static void init(int port)throws Exception{
		log.info("init : " + port);
		StubClient.port = port;
    	XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("http://127.0.0.1:" + port));
        config.setEnabledForExtensions(true);
//        config.setReplyTimeout(20 * 1000);
        client = new XmlRpcClient();
        client.setConfig(config);
	}
	
	public static Object sendCommand(String cmd, Object...paras){
		try{
			return client.execute(cmd, paras);
		}catch(Exception e){
			log.error(cmd, e);
			return null;
		}
	}

	public static boolean init(){
		log.info("init()");
		sendCommand("ucs.init");
		return true;
	}

	
	public static boolean setsipserver(String serverIP, int serverPort){
		log.info("setsipserver : serverIP = " + serverIP + " & serverPort = " + serverPort);
		sendCommand("ucs.setsipserver", serverIP, serverPort);
		return true;
	}
	
	public static boolean setusername(String username){
		log.info("setusername : " + username );
		sendCommand("ucs.setusername", username);
		return true;
	}
	
	public static boolean setpassword(String passwd){
		log.info("setpassword : " + passwd );
		sendCommand("ucs.setpassword", passwd);
		return true;
	}
	
	public static boolean register(){
		log.info("register");
		sendCommand("ucs.register");
		return true;
	}
	
	public static boolean isregister(){
		boolean flag = (Boolean)sendCommand("ucs.isregister");
		log.info("isregister = " + flag);
		return flag;
	}
	
	public static boolean uninit(){
		log.info("uninit");
		sendCommand("ucs.uninit");
		return true;
	}
	
	public static boolean unregister(){
		log.info("unregister");
		sendCommand("ucs.unregister");
		return true;
	}
	
	public static boolean call(String username){
		log.info("call : username = " + username);
		sendCommand("ucs.call", username);
		return true;
	}
	
	public static boolean hangup(){
		log.info("hangup");
		sendCommand("ucs.hangup");
		return true;
	}
	
	public static boolean answercall(String username){
		log.info("answercall");
		sendCommand("ucs.answercall", username);
		return true;
	}
	
	public static boolean createconf(String username, String confno){
		log.info("createconf : username = " + username + " & confno = " + confno);
		sendCommand("ucs.createconf", username, confno);
		return true;
	}
	
	public static boolean removeconf(String username, String confno){
		log.info("removeconf : username = " + username + " & confno = " + confno);
		sendCommand("ucs.removeconf", username, confno);
		return true;
	}
	
	public static boolean callmeeting(String confno){
		log.info("callmeeting : confno = " + confno);
		sendCommand("ucs.callmeeting", confno);
		return true;
	}
	
	public static boolean getDevList(){
		log.info("getDevList");
		sendCommand("ucs.getDevList");
		return true;
	}
	
	public static boolean answermeeting(String confno){
		log.info("answermeeting confno = " + confno);
		sendCommand("ucs.answermeeting", confno);
		return true;
	}
	
}
