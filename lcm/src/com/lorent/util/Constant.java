package com.lorent.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Constant {
	
	public final static String MODULE_NAME = "lcm"; 
	
	public final static int RECORD_NEW = 0;
	public final static int RECORD_DELETED = -1;
	public final static int RECORD_VALID = 1;
	public final static int RECORD_EXPIRED = 2;
	
	public final static int RECORD_STATUS_NEW=0;
	public final static int RECORD_STATUS_DELETED=-1;
	public final static int RECORD_STATUS_VALID=1;
	
	public final static String ROLE_CUSTOMER_ADMIN = "ROLE_CUSTOMER_ADMIN";
	public final static String ROLE_ADMIN = "ROLE_ADMIN";
	public final static String ROLE_CUSTOMER_USER = "ROLE_CUSTOMER_USER";
	public final static String ROLE_MEMBER_USER = "ROLE_MEMBER_USER";
	public final static String ROLE_CUSTOMER_DEP_ADMIN = "ROLE_CUSTOMER_DEP_ADMIN";
	
	public final static Integer USER_TYPE_ADMIN = 0;
	public final static Integer USER_TYPE_INNERUSER = 1;
	public final static Integer USER_TYPE_MEMBER_USER = 2;
	
	public final static Integer CONF_STATUS_NOT_START = 0;
	public final static Integer CONF_STATUS_ONGOING = 1;
	public final static Integer CONF_STATUS_FINISHED = 2;
	public final static Integer CONF_STATUS_DELETED = 3;
	public final static Integer CONF_STATUS_STOPED = 4;
	
	public final static String MEMBER_USER_LCC_PREFIX = "8000000";
	
	public final static Integer CONF_TYPE_APPCONF = 2;
	public final static Integer CONF_TYPE_IMDCONF = 1;
	public final static Integer CONF_TYPE_PERCONF = 3;
	public final static Integer CONF_TYPE_MEETING = 4;
	public final static Integer CONF_TYPE_OTHER = 5;
	public final static Integer CONF_TYPE_NEWCONFERENCE = 6;
	
	public final static Integer CONF_MODE_HOSTMODE = 1;
	public final static Integer CONF_MODE_FREEMODE = 2;
	
	public final static Map<String, String>MCU_QUALITYS = new LinkedHashMap<String, String>();
	public final static Map<Integer, String>MCU_MEDIA_LAYOUT = new LinkedHashMap<Integer, String>();
	

	
	public final static int RATES_TYPE_BYTIME = 0;
	public final static int RATES_TYPE_BYHOUR = 1;
	public final static int RATES_TYPE_BYMIN = 2;
	
	public final static int CUSTOMER_CONFNO_LIMIT = 100;
	public final static int CUSTOMER_CONFUSER_LIMIT = 10000;
	
	public final static int YES = 1;
	public final static int NO = 2;
	
	public final static int MIX = 1;
	public final static int NOTMIX = 2;
	
	public final static int CRON_TYPE_MONTH = 1;
	public final static int CRON_TYPE_WEEK = 2;
	
	public final static int MEDIA_PLAY = 1;
	public final static int MEDIA_STOP = 0;
	
	public final static String WEEK_DAY_SUN = "1";
	public final static String WEEK_DAY_MON = "2";
	public final static String WEEK_DAY_TUS = "3";
	public final static String WEEK_DAY_WEB = "4";
	public final static String WEEK_DAY_THURS = "5";
	public final static String WEEK_DAY_FRI = "6";
	public final static String WEEK_DAY_SAT = "7";
	public final static String WEEK_WORK_DAY = "2-7";
	public final static String WEEK_EVERY_DAY = "*";
	
	public final static String TRIGGER_CONF_START_PREFIX = "conf start trigger ";
	public final static String TRIGGER_CONF_STOP_PREFIX = "conf stop trigger ";
	public final static String TRIGGER_CRON_CONF_PREFIX = "cron conf trigger ";
	public final static String TRIGGER_MCU_RESTORE_PREFIX = "mcu restore trigger ";
	public final static String TRIGGER_HEART_BEAT = "heart beat trigger ";
	
	static{
		MCU_QUALITYS.put("H264-CIF-Profile@384:30:1","H264-CIF-Profile");
		MCU_QUALITYS.put("H264-QCIF-Profile@384:30:0","H264-QCIF-Profile");
		MCU_QUALITYS.put("H264-VGA-Profile@2048:30:2","H264-VGA-Profile");
		MCU_QUALITYS.put("H264-SVGA-Profile@2048:30:3","H264-SVGA-Profile");
		MCU_QUALITYS.put("H264-D1-Profile@2048:30:4","H264-D1-Profile");
		MCU_QUALITYS.put("H264-D4-Profile@2048:30:6","H264-720p-Profile");
		MCU_QUALITYS.put("H264-D2-Profile@2048:30:5","H264-1080p-Profile");
//		MCU_QUALITYS.put("H263p-CIF-Profile@2048:30:1","H263p-CIF-Profile");
		MCU_MEDIA_LAYOUT.put(0, "1x1");
		MCU_MEDIA_LAYOUT.put(1, "2x2");
		MCU_MEDIA_LAYOUT.put(2, "3x3");
		MCU_MEDIA_LAYOUT.put(3, "3+4");
		MCU_MEDIA_LAYOUT.put(4, "1+7");
		MCU_MEDIA_LAYOUT.put(5, "1+5");
	}
	
	private final static String CUSTOMER_CODE_LENGTH = "CUSTOMER_CODE_LENGTH";
	private final static String CONFERENCE_NO_LENGTH = "CONFERENCE_NO_LENGTH";
	private final static String LCC_NO_LENGTH = "LCC_NO_LENGTH";
	private final static String HEARTBEAT_INTEVAL_TIME = "HEARTBEAT_INTEVAL_TIME";
	
	public static int getCustomerCodeLength(){
		return Integer.parseInt(PropertiesUtil.getConstant(CUSTOMER_CODE_LENGTH));
	}
	
	public static int getConferenceNoLength(){
		return Integer.parseInt(PropertiesUtil.getConstant(CONFERENCE_NO_LENGTH));
	}
	
	public static int getLccnoLength(){
		return Integer.parseInt(PropertiesUtil.getConstant(LCC_NO_LENGTH));
	}
	
	public static int getHeartBeatIntevalTime(){
		return Integer.parseInt(PropertiesUtil.getConstant(HEARTBEAT_INTEVAL_TIME));
	}
	
	public enum RequestAtrributeKeys{
		SysServerConfigEidt
	}
 }
