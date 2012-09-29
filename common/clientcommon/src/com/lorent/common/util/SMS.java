package com.lorent.common.util;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.smslib.GatewayException;
import org.smslib.InboundMessage;
import org.smslib.OutboundMessage;
import org.smslib.SMSLibException;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.smslib.InboundMessage.MessageClasses;
import org.smslib.Message.MessageEncodings;
import org.smslib.modem.SerialModemGateway;


public class SMS {

	//读取全部消息
	public static final org.smslib.InboundMessage.MessageClasses ALL_MESSAGE=org.smslib.InboundMessage.MessageClasses.ALL;
	//读取已读消息
	public static final  org.smslib.InboundMessage.MessageClasses READED_MESSAGE=org.smslib.InboundMessage.MessageClasses.READ;
	//读取未读消息
	public static final org.smslib.InboundMessage.MessageClasses UNREAD_MESSAGE =org.smslib.InboundMessage.MessageClasses.UNREAD;

	private static Logger log = Logger.getLogger(SMS.class);
	private Service srv=null;
	private String gatewayid;
	int baudRate = 9600;
	String manufacturer = "";
	String model = "";
	private boolean isStarted = false;
	
	public boolean isStarted() {
		return isStarted;
	}


	public SMS(String gatewayid) throws Exception{
		if (gatewayid == null || gatewayid.equals("")) {
			throw new Exception("gatewayid can not null or empty!");
		}
		this.gatewayid = gatewayid;
		srv = Service.getInstance();
		
	}

	
	public boolean startService(String comName) throws Exception{
		
		SerialModemGateway gateway = new SerialModemGateway(gatewayid, comName,baudRate, manufacturer, model);
		gateway.setInbound(true);
		gateway.setOutbound(true);
		
		srv.S.SERIAL_POLLING = true;
		srv.addGateway(gateway);
		srv.startService();
//		System.out.println("Modem connected. "+srv);
		log.info("SMS.startService  connected. "+srv);
		this.isStarted = true;
		return true;
	}
	
	public void stopService() throws Exception{
		srv.stopService();
//		System.out.println("Modem disconnected. "+srv);
		srv = null;
		log.info("SMS.stopService disconnected. "+srv);
		this.isStarted = false;
	}
	
	public boolean sendSMS(String mobile,String content){
		log.info("SMS.sendSMS "+mobile+","+content);
		OutboundMessage obMsg = new OutboundMessage(mobile, content);
		obMsg.setEncoding(MessageEncodings.ENCUCS2);
		try {
			boolean sended = srv.sendMessage(obMsg);
			return sended;
		} catch (Exception e) {
			log.error("SMS.sendSMS", e);
			e.printStackTrace();
			return false;
		} 
	}

	public List<InboundMessage> readSMS(MessageClasses messageType) throws Exception{
		log.info("readSMS "+messageType);
		List <InboundMessage>  smss=new LinkedList<InboundMessage>();
		srv.readMessages(smss,messageType,gatewayid);
		return smss;
	}
	
	
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) {

		final SMS sms;
		try {
			sms = new SMS("SMS");
			sms.startService("COM3");
			
//			SMS sms2 = new SMS("SMS2");
//			sms2.startService("/dev/ttyUSB0");
			
//			boolean sendSMS = sms.sendSMS("13760964581", "测试");
//			System.out.println("sended: "+sendSMS);
			List<InboundMessage> readSMS = sms.readSMS(SMS.ALL_MESSAGE);
			for (InboundMessage inboundMessage : readSMS) {
				System.out.println(inboundMessage);
			}
			
			new Thread(){

				@Override
				public void run() {
					sms.sendSMS("13760964581", "测试");
				}
				
			}.start();
			
			new Thread(){

				@Override
				public void run() {
					sms.sendSMS("13760964581", "测试");
				}
				
			}.start();
			
//			sms.stopService();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}

}
