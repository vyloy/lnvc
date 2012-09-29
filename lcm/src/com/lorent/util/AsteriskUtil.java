package com.lorent.util;

import java.util.Map;

import com.lorent.astpack.service.AstpackSerivceFactory;
import com.lorent.astpack.service.IConfService;
import com.lorent.dto.CSUser;

public class AsteriskUtil {
	
	public static void addUserInConf(CSUser user, String[] lccnos, String confno)throws Exception{
		IConfService csSer = getCSService(user);
		csSer.addUsersInConf(confno, lccnos);
	}
	
	public static void playMediaInConf(CSUser user, String confno, String mediaPath, boolean play)throws Exception{
		IConfService csSer = getCSService(user);
		csSer.playMediaInConf(confno, mediaPath, play);
	}
	
	public static void removeUserInConf(String csIP,String[] lccnos,String confno ) throws Exception{
		IConfService csSer  = AstpackSerivceFactory.getInstance(csIP);
		for (int i = 0; i < lccnos.length; i++) {
			csSer.forceDisconnect(lccnos[i]);
		}
	}
	
	private static IConfService getCSService(CSUser user){
		IConfService csSer = AstpackSerivceFactory.getInstance(user.getIp(), user.getPort(), user.getUsername(), user.getPasswd());
		return csSer;
	}
	
	public static void joinExtenSpy(CSUser user,String lccno,String lccnotarget,String prefix) throws Exception{
		IConfService service = getCSService(user);
		service.joinExtenSpy(lccno, lccnotarget, prefix);
	}
	
	public static void forceDisconnect(CSUser user,String lccno) throws Exception{
		IConfService service = getCSService(user);
		service.forceDisconnect(lccno);
	}
	
	public static void  listenExtenSpy(CSUser user,String lccno,String lccnotarget,String prefix) throws Exception {
		IConfService service = getCSService(user);
		service.listenExtenSpy(lccno, lccnotarget, prefix);
	}
	
	public static void switchAnswer(CSUser user,String lccno,String lccnotarget) throws Exception{
		IConfService service = getCSService(user);
		service.answer(lccno, lccnotarget);
	}
	
	public static void call(CSUser user,String lccno,String lccnotarget) throws Exception{
		IConfService service = getCSService(user);
		service.call(lccno, lccnotarget);
	}
	
	public static int getPeerStatus(CSUser user,String lccno) throws Exception{
		IConfService service = getCSService(user);
		Map peerStauts = service.getPeerStatus(lccno);
		Object status = peerStauts.get("Status");
		if (status != null && !status.equals("")) {
			return Integer.parseInt(status.toString()); 
		}
		else {
			return -1;
		}
	}
}
