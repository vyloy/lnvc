package com.lorent.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.lorent.dto.XmlrpcConf;
import com.lorent.model.ConferenceBean;
import com.lorent.xmlrpc.McuXmlrpc;

public class McuUtil {

	
	private static final Logger log = Logger.getLogger(McuUtil.class);
	
	public static void createConference(ConferenceBean conf)throws Exception{
		String confno = conf.getConfno();
		int layout = getConfLayout(conf);
		int quality = getConfQuality(conf);
		String xmlrpcUrl = conf.getCustomer().getMcuServer().getServerUrl();
		log.info("createConf:confno=" + confno + "&layout=" + layout + "&quality=" + quality + "&xmlrpcUrl=" + xmlrpcUrl);
		McuXmlrpc.createConf(xmlrpcUrl, conf.getConfno(), layout, quality);
	}
	
	public static boolean createForwardConference(String confNo,String xmlrpcUrl)throws Exception{
		//String xmlrpcUrl = "";//conf.getCustomer().getMcuServer().getServerUrl();
		//log.info("createConf:confno=" + confno + "&layout=" + layout + "&quality=" + quality + "&xmlrpcUrl=" + xmlrpcUrl);
		return McuXmlrpc.createForwardConference(xmlrpcUrl, confNo);
	}
	
//	public static void updateForwardConference(String confNo,String xmlrpcUrl)throws Exception{
//		//String xmlrpcUrl = "";//conf.getCustomer().getMcuServer().getServerUrl();
//		//log.info("createConf:confno=" + confno + "&layout=" + layout + "&quality=" + quality + "&xmlrpcUrl=" + xmlrpcUrl);
//		McuXmlrpc.updateForwardConference(xmlrpcUrl, confNo);
//	}
	
	public static void updateConference(ConferenceBean conf)throws Exception{
		String confno = conf.getConfno();
		int layout = getConfLayout(conf);
		int quality = getConfQuality(conf);
		String xmlrpcUrl = conf.getCustomer().getMcuServer().getServerUrl();
		log.info("updateConf:confno=" + confno + "&layout=" + layout + "&quality=" + quality + "&xmlrpcUrl=" + xmlrpcUrl);
		McuXmlrpc.updateConf(xmlrpcUrl, conf.getConfno(), layout, quality);
	}
	
	public static void removeConference(ConferenceBean conf)throws Exception{
		String confno = conf.getConfno();
		String xmlrpcUrl = conf.getCustomer().getMcuServer().getServerUrl();
		Object o = McuXmlrpc.getConfByConfNo(xmlrpcUrl, confno);
		if(o!=null){//if exist
			log.info("removeConf:confno=" + confno + "&xmlrpcUrl=" + xmlrpcUrl);
			McuXmlrpc.removeConf(xmlrpcUrl, conf.getConfno());
		}
	}
	
	public static void removeForwardConference(String xmlrpcUrl, String confno)throws Exception{
		
		Object o = McuXmlrpc.getForwardConferenceByConfNo(xmlrpcUrl, confno);
		if(o!=null){//if exist
			log.info("removeConf:confno=" + confno + "&xmlrpcUrl=" + xmlrpcUrl);
			McuXmlrpc.removeConf(xmlrpcUrl, confno);
		}
	}
	
	public static void removeConfUser(ConferenceBean conf, String[] lccnos)throws Exception{
		String confno = conf.getConfno();
		String xmlrpcUrl = conf.getCustomer().getMcuServer().getServerUrl();
		for(String lccno : lccnos){
			McuXmlrpc.removeConfUser(xmlrpcUrl, confno, lccno);
		}
	}
	
	public static int getExistConfSize(String mcuXmlrpcUrl)throws Exception{
		List<XmlrpcConf> confList = McuXmlrpc.getConfList(mcuXmlrpcUrl);
		return confList.size();
	}
	
	public static void setConfUserVideo(ConferenceBean conf, String[] lccnos, int open)throws Exception{
		String xmlrpcUrl = conf.getCustomer().getMcuServer().getServerUrl();
		for(String lccno : lccnos){
			McuXmlrpc.setConfUserVideo(xmlrpcUrl, conf.getConfno(), lccno, open);
		}
	}
	
	public static void setConfUserAudio(ConferenceBean conf, String[] lccnos, int open)throws Exception{
		String xmlrpcUrl = conf.getCustomer().getMcuServer().getServerUrl();
		for(String lccno : lccnos){
			McuXmlrpc.setConfUserAudio(xmlrpcUrl, conf.getConfno(), lccno, open);
		}
	}
	
	public static boolean inviteConfUser(String xmlrpcUrl, String confno ,String lccno) throws Exception{
		log.info("inviteConfUser: "+confno+" , "+lccno);
		return McuXmlrpc.inviteConfUser(xmlrpcUrl, confno, lccno);
	}
	
	//------------------------help method------------------------
	private static int getConfLayout(ConferenceBean conf){
		return conf.getMcuMediaLayOut();
	}
	
	private static int getConfQuality(ConferenceBean conf){
		return MCU_QUALITYS.get(conf.getMcuMediaQuality());
	}
	
	public final static Map<String, Integer>MCU_QUALITYS = new LinkedHashMap<String, Integer>();
//	public final static Map<String, Integer>MCU_MEDIA_LAYOUT = new LinkedHashMap<String, Integer>();
	static{
		MCU_QUALITYS.put("H264-CIF-Profile@384:30:1",1);
		MCU_QUALITYS.put("H264-QCIF-Profile@384:30:0",0);
		MCU_QUALITYS.put("H264-VGA-Profile@2048:30:2",2);
		MCU_QUALITYS.put("H264-SVGA-Profile@2048:30:3",3);
		MCU_QUALITYS.put("H264-D1-Profile@2048:30:4",4);
		MCU_QUALITYS.put("H264-D2-Profile@2048:30:5",5);
		MCU_QUALITYS.put("H264-D4-Profile@2048:30:6",6);
		MCU_QUALITYS.put("H263p-CIF-Profile@2048:30:1",7);
		
//		MCU_MEDIA_LAYOUT.put("1x1", 0);
//		MCU_MEDIA_LAYOUT.put("2x2", 1);
//		MCU_MEDIA_LAYOUT.put("3x3", 2);
//		MCU_MEDIA_LAYOUT.put("3+4", 3);
//		MCU_MEDIA_LAYOUT.put("1+7", 4);
//		MCU_MEDIA_LAYOUT.put("1+5", 5);
	}
}
