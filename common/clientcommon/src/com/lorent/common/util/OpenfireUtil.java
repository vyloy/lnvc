/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.common.util;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.RoomInfo;

/**
 * 
 * @author jack
 */
public class OpenfireUtil {

	private static Logger log = Logger.getLogger(OpenfireUtil.class);
	private  XMPPConnection conn = null;
	public XMPPConnection getConn() {
		return conn;
	}

	private  String resourcePath = "com/lorent/common/resource/i18n/view";

	private static OpenfireUtil instance = null;

	private OpenfireUtil() {
	}

	public static OpenfireUtil getInstance() {
		if (instance == null) {
			instance = new OpenfireUtil();
		}
		return instance;
	}
	
	public void disconnect(){
		if(conn != null){
			conn.disconnect();
			conn = null;
		}
	}

	public void init(String serverIP, int serverPort, int timeout) throws Exception {
		XMPPConnection.DEBUG_ENABLED = false;
		ConnectionConfiguration config = new ConnectionConfiguration(serverIP,
				serverPort);
//		config.setReconnectionAllowed(true);
		config.setSASLAuthenticationEnabled(false);
		// config.setCompressionEnabled(true);
		config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
		SmackConfiguration.setPacketReplyTimeout(timeout);

		conn = new XMPPConnection(config);
		conn.connect();
		
	}
	
	public void login(String username, String passwd, String resource) throws Exception {
		log.info("建立连接");
		if (!conn.isConnected()) {
			try {
				conn.connect();
			} catch (Exception ex) {
				String resourceString = StringUtil.getResourceString(
						resourcePath, "error.openfire.connerror");
				log.error("openfire " + resourceString, ex);
				throw new Exception(resourceString);
			}
		}

		log.info("登录");
		try {
			conn.login(username, passwd, resource);
		} catch (Exception e) {
			// throw new
			// Exception(StringUtil.getErrorString("error.username_password.msg"));
			String resourceString = StringUtil.getResourceString(resourcePath,
					"error.openfire.loginerror");
			log.error("openfireutil login " + resourceString, e);
			throw new Exception(resourceString);
		}
	}

	public String getServiceName(){
		return conn.getServiceName();
	}
	
	public int getMUCOccupants(String confno) throws Exception{
		Collection<String> serviceNames = MultiUserChat.getServiceNames(conn);
		for(String serviceName : serviceNames){
			RoomInfo roomInfo = MultiUserChat.getRoomInfo(conn, confno + "@" + serviceName);
			return roomInfo.getOccupantsCount();
		}
		return -1;
	}
	
	public static void main(String[] args){
		try {
			OpenfireUtil.getInstance().init("10.168.250.12", 5222, 5000);
			OpenfireUtil.getInstance().login("3000000", "123456", "vovo");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
