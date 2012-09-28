package com.lorent.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.lorent.dto.CSUser;
import com.lorent.model.ConferenceBean;
import com.lorent.model.McuServerBean;
import com.lorent.model.UserBean;

public class CSUtil {
	private static final Logger log = Logger.getLogger(CSUtil.class);
	
	public static void addUserInConf(ConferenceBean conf)throws Exception{

		List<String> temp = new ArrayList<String>();
		for(UserBean user : conf.getUsers()){
			temp.add(user.getLccAccount());
		}
		String[] lccnos = new String[temp.size()];
		temp.toArray(lccnos);
		String confno = conf.getConfno();
				
		CSUser csUser = getCSUser(conf);
		log.info("addUserInConf:" + csUser + "&lccnos=" + lccnos + "&confno=" + confno);
		AsteriskUtil.addUserInConf(csUser, lccnos, confno);
	}
	
	public static void playMediaInConf(ConferenceBean conf, String mediaPath, boolean play)throws Exception{
		String confno = conf.getConfno();
		CSUser csUser = getCSUser(conf);
		log.info("playMediaInConf:" + csUser + "&confno=" + confno + "&mediaPath=" + mediaPath + "&play=" + play);
		AsteriskUtil.playMediaInConf(csUser, confno, mediaPath, play);
	}
	
	private static CSUser getCSUser(ConferenceBean conf){
		McuServerBean mcuServer = conf.getCustomer().getMcuServer();
		String csIP = mcuServer.getCsIP();
		int csPort = mcuServer.getCsPort();
		String csUsername = mcuServer.getCsUsername();
		String csUserPasswd = mcuServer.getCsUserPasswd();
		CSUser csUser = new CSUser(csIP, csPort, csUsername, csUserPasswd);
		return csUser;
	}
	
	public static void removeUserInConf(ConferenceBean conf)throws Exception{
		String csIP  = conf.getCustomer().getMcuServer().getServerIp();
		List<String> temp = new ArrayList<String>();
		for(UserBean user : conf.getUsers()){
			temp.add(user.getLccAccount());
		}
		String[] lccnos = new String[temp.size()];
		temp.toArray(lccnos);
		String confno = conf.getConfno();
		log.info("removeUserInConf:csIP=" + csIP + "&lccnos=" + lccnos +"&confno=" + confno );
		AsteriskUtil.removeUserInConf(csIP, lccnos, confno);
	}
	
	public static void  listenExtenSpy(McuServerBean mcuserver,UserBean user,UserBean targetuser) throws Exception {
		String csIP = mcuserver.getCsIP();
		Integer csPort = mcuserver.getCsPort();
		String csUsername = mcuserver.getCsUsername();
		String csUserPasswd = mcuserver.getCsUserPasswd();
		CSUser csUser = new CSUser(csIP,csPort,csUsername,csUserPasswd);
		String prefix = PropertiesUtil.getConstant("listenExtenSpy_prefix");
		log.info("listenExtenSpy:csIP=" + csIP + "&user=" + user.getLccAccount() +"&targetuser=" + targetuser.getLccAccount()+"&prefix="+prefix);
		AsteriskUtil.listenExtenSpy(csUser, user.getLccAccount(), targetuser.getLccAccount(), prefix);
	}
	
	public static void joinExtenSpy(McuServerBean mcuserver,UserBean user,UserBean targetuser) throws Exception{
		String csIP = mcuserver.getCsIP();
		Integer csPort = mcuserver.getCsPort();
		String csUsername = mcuserver.getCsUsername();
		String csUserPasswd = mcuserver.getCsUserPasswd();
		CSUser csUser = new CSUser(csIP,csPort,csUsername,csUserPasswd);
		String prefix = PropertiesUtil.getConstant("joinExtenSpy_prefix");
		log.info("joinExtenSpy:csIP=" + csIP + "&user=" + user.getLccAccount() +"&targetuser=" + targetuser.getLccAccount()+"&prefix="+prefix);
		AsteriskUtil.joinExtenSpy(csUser, user.getLccAccount(), targetuser.getLccAccount(), prefix);
	}
	
	public static void call(McuServerBean mcuserver,UserBean user,UserBean targetuser) throws Exception{
		String csIP = mcuserver.getCsIP();
		Integer csPort = mcuserver.getCsPort();
		String csUsername = mcuserver.getCsUsername();
		String csUserPasswd = mcuserver.getCsUserPasswd();
		CSUser csUser = new CSUser(csIP,csPort,csUsername,csUserPasswd);
		log.info("call:csIP=" + csIP + "&user=" + user.getLccAccount() +"&targetuser=" + targetuser.getLccAccount());
		AsteriskUtil.call(csUser, user.getLccAccount(), targetuser.getLccAccount());
	}
	
	public static void  forceDisconnect(McuServerBean mcuserver,UserBean user) throws Exception {
		String csIP = mcuserver.getCsIP();
		Integer csPort = mcuserver.getCsPort();
		String csUsername = mcuserver.getCsUsername();
		String csUserPasswd = mcuserver.getCsUserPasswd();
		CSUser csUser = new CSUser(csIP,csPort,csUsername,csUserPasswd);
		log.info("forceDisconnect:csIP=" + csIP + "&user=" + user.getLccAccount() );
		AsteriskUtil.forceDisconnect(csUser, user.getLccAccount());		
	}
	
	public static void switchAnswer(McuServerBean mcuserver,UserBean user,UserBean targeruser) throws Exception{
		String csIP = mcuserver.getCsIP();
		Integer csPort = mcuserver.getCsPort();
		String csUsername = mcuserver.getCsUsername();
		String csUserPasswd = mcuserver.getCsUserPasswd();
		CSUser csUser = new CSUser(csIP,csPort,csUsername,csUserPasswd);
		log.info("switchAnswer:csIP=" + csIP + "&user=" + user.getLccAccount()+"&targetuser="+targeruser.getLccAccount() );
		AsteriskUtil.switchAnswer(csUser, user.getLccAccount(), targeruser.getLccAccount());
	}
	
	public static int getPeerStatus(McuServerBean mcuserver,UserBean user) throws Exception{
		String csIP = mcuserver.getCsIP();
		Integer csPort = mcuserver.getCsPort();
		String csUsername = mcuserver.getCsUsername();
		String csUserPasswd = mcuserver.getCsUserPasswd();
		CSUser csUser = new CSUser(csIP,csPort,csUsername,csUserPasswd);
		log.info("switchAnswer:csIP=" + csIP + "&user=" + user.getLccAccount());
		return AsteriskUtil.getPeerStatus(csUser, user.getLccAccount());
	}
}
