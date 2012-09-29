package com.lorent.common.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.lorent.common.dto.LCMMobileBean;
import com.lorent.common.dto.LCMResultBean;
 

public class SMSUtil {
	
	
	private static Logger log = Logger.getLogger(SMSUtil.class);
	
	private static SMS sms = null;
	
	public static SMS getSMS() throws Exception{
		if (sms == null) {
			sms = new SMS("SMS");
		}
		return sms;
	}
	
	public static void startService(String comName) throws Exception{
		if (!getSMS().isStarted()) {
			getSMS().startService(comName);
		}
	}
	
	public static void stopService() throws Exception{
		getSMS().stopService();
	}
	
	public synchronized static boolean sendSMS(String mobile,String content,String comName) throws Exception {
		log.info("SMSUtil.sendSMS "+mobile+","+content+","+comName);
		if (!getSMS().isStarted()) {
			getSMS().startService(comName);
		}
		boolean sendSMS = getSMS().sendSMS(mobile, content);
//		sms.stopService();
		return sendSMS;
	}

	public synchronized static List<LCMResultBean> sendSMS(List<LCMMobileBean> list,String comName) throws Exception{
		log.info("SMSUtil.sendSMS "+list+","+comName);
		if (!getSMS().isStarted()) {
			getSMS().startService(comName);
		}
		ArrayList<LCMResultBean> resultList = new ArrayList<LCMResultBean>();
		for (LCMMobileBean mobileBean : list) {
			log.info("SMSUtil.sendSMS while: "+mobileBean.getMobile()+","+mobileBean.getContent());
			boolean sendedSMS = getSMS().sendSMS(mobileBean.getMobile(), mobileBean.getContent());
			LCMResultBean resultBean = new LCMResultBean();
			resultBean.setMobile(mobileBean.getMobile());
			resultBean.setSended(sendedSMS);
			resultList.add(resultBean);
		}
//		getSMS().stopService();
		return resultList;
		
	}

	
	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		final String comName = "/dev/ttyUSB0";
		try {
			SMSUtil.startService(comName);
			System.out.println("started service");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		new Thread(){

			@Override
			public void run() {
				try {
					System.out.println("thread 1");
					SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String datetime = tempDate.format(new java.util.Date());
					boolean sendSMS2 = SMSUtil.sendSMS("13760964581", "测试1,"+datetime, comName);
					System.out.println("send1: "+sendSMS2+","+datetime);
					
					datetime = tempDate.format(new java.util.Date());
					sendSMS2 = SMSUtil.sendSMS("13760964581", "测试2,"+datetime, comName);
					System.out.println("send2: "+sendSMS2+","+datetime);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
		
		new Thread(){

			@Override
			public void run() {
				try {
					System.out.println("thread 2");
					SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String datetime = tempDate.format(new java.util.Date());
					boolean sendSMS2 = SMSUtil.sendSMS("13760964581", "测试3"+datetime, comName);
					System.out.println("send3: "+sendSMS2+","+datetime);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
		
		
	}

}
