package com.lorent.vovo.util;

import java.util.Date;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.PrivateDataManager;
import org.jivesoftware.smackx.packet.DefaultPrivateData;

import com.lorent.common.util.OpenfireUtil;
import com.lorent.vovo.dto.PrivateData;

public class PrivateDataUtil {
	
	private static final String namespace = "vovo";
	private static final String NAME_PROPERTY = "property";
	
	
	public static void setLogoutTime(Date date)throws XMPPException{
		DefaultPrivateData data = getPrivateData(NAME_PROPERTY);
		data.setValue("logout", date.getTime() + "");
		setPrivateData(data);
	}
	
	public static void setLoginTime(Date date)throws XMPPException{
		DefaultPrivateData data = getPrivateData(NAME_PROPERTY);
		data.setValue("login", date.getTime() + "");
		setPrivateData(data);
	}
	
	public static PrivateData getLogInfo() throws XMPPException{
		DefaultPrivateData data = getPrivateData(NAME_PROPERTY);
		PrivateData pri = new PrivateData();
		String temp = data.getValue("login");
		if(temp != null){
			pri.setLastLoginTime(Long.parseLong(temp));
		}
		temp = data.getValue("logout");
		if(temp != null){
			pri.setLastLogoutTime(Long.parseLong(temp));
		}
		return pri;
	}
	
	private static DefaultPrivateData getPrivateData(String name) throws XMPPException{
		PrivateDataManager pdm = new PrivateDataManager(OpenfireUtil.getInstance().getConn());
		DefaultPrivateData data = (DefaultPrivateData)pdm.getPrivateData(name, namespace);
		return data;
	}
	
	private static void setPrivateData(DefaultPrivateData data) throws XMPPException{
		PrivateDataManager pdm = new PrivateDataManager(OpenfireUtil.getInstance().getConn());
		pdm.setPrivateData(data);
	}
}
