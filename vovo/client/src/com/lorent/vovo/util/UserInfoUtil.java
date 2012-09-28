package com.lorent.vovo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

import com.lorent.common.util.PasswordUtil;
import com.lorent.vovo.dto.LoginInfo;

public class UserInfoUtil {
	
	private static Logger log = Logger.getLogger(UserInfoUtil.class);
	
	private static final String FILE_PATH = "userinfo.properties";
	private static final String AUTOLOGIN_SUFFIX = "_autologin";
	private static final String SAVEPASSWD_SUFFIX = "_savepasswd";
	private static final String SERVERIP_SUFFIX = "_serverip";
	private static final String STATUS_SUFFIX = "_status";
	private static final String SUFFIX = "_";
	
	private static final Preferences preferences=Preferences.systemNodeForPackage(UserInfoUtil.class);
	
	public static String get(String key,String value){
		return preferences.get(key, value);
	}
	
	private static String getProperty(String key)throws Exception{
		return preferences.get(key, null);
	}
	
	private static Map<String, String> getProperty(String[] keys)throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		for(String key : keys){
			map.put(key, getProperty(key));
		}
		return map;
	}
	
	public static void setProperty(String key, String value)throws Exception{
		preferences.put(key, value);
		preferences.flush();
	}
	
	
	public static List<LoginInfo> getAllUserLoginInfos(){
		ArrayList<LoginInfo> list = new ArrayList<LoginInfo>();
		
		try {
			for(String key : preferences.keys()){
				if(!key.contains("_")){
					LoginInfo info = getInfo(key);
					list.add(info);
				}
			}
		} catch (Exception e) {
			log.error("getAllUserLoginInfos", e);
		}
		
		return list;
	}
	
	public static List<String> getAllUserNames()throws Exception{
		List<String> list = new ArrayList<String>();
		for(String key : preferences.keys()){
			if(!key.contains("_")){
				list.add((String)key);
			}
		}
		return list;
	}
	
	public static String getPassword(String id)throws Exception{
		String value = getProperty(id);
		if(value != null){
			return PasswordUtil.getDesString(value);
		}else{
			return "";
		}
	}
	
	public static void setInfo(String id, String password, boolean autoLogin, boolean savePasswd,String serverip, int status)throws Exception{
		if(savePasswd){
			setProperty(id, PasswordUtil.getEncString(password));
		}else{
			setProperty(id, "");
		}
		setProperty(id + AUTOLOGIN_SUFFIX, autoLogin + "");
		setProperty(id + SAVEPASSWD_SUFFIX, savePasswd + "");
		setProperty(id+SERVERIP_SUFFIX, serverip);
		setProperty(id + STATUS_SUFFIX, status + "");
	}
	
	public static void setInfo(LoginInfo info)throws Exception{
		setInfo(info.getUsername(), info.getPassWord(), info.isAutoLogin(), info.isSavePasswd(), info.getServerIP(), info.getStatus());
	}
	
	public static LoginInfo getInfo(String id)throws Exception{
		String[] keys = new String[]{id, id + AUTOLOGIN_SUFFIX, id + SAVEPASSWD_SUFFIX,id+SERVERIP_SUFFIX, id+STATUS_SUFFIX};
		Map<String, String> map = getProperty(keys);
		if(map == null){
			return null;
		}
		LoginInfo info = new LoginInfo();
		info.setUsername(id);
		info.setPassWord(PasswordUtil.getDesString(map.get(id)));
		info.setAutoLogin(Boolean.parseBoolean(map.get(id + AUTOLOGIN_SUFFIX)));
		info.setSavePasswd(Boolean.parseBoolean(map.get(id + SAVEPASSWD_SUFFIX)));
		info.setServerIP(map.get(id+SERVERIP_SUFFIX));
		String status = map.get(id + STATUS_SUFFIX);
		if(status != null){
			info.setStatus(Integer.parseInt(status));
		}else{
			info.setStatus(Constants.STATUS_ONLINE);
		}
		return info;
	}
	
	public static javax.swing.ImageIcon getHeadImg(int state){
		ImageIcon image = null;
		switch (state) {
		case Constants.STATUS_ONLINE:
			image = new ImageIcon(UserInfoUtil.class.getResource(Constants.SYSTEM_HEAD_IMAGE_PATH
					+ "default40.png"));
			break;
		case Constants.STATUS_OFFLINE:
			image = new ImageIcon(UserInfoUtil.class.getResource(Constants.SYSTEM_HEAD_IMAGE_PATH
					+ "default40offline.png"));
			break;
		case Constants.STATUS_AWAY:
			image = new ImageIcon(UserInfoUtil.class.getResource(Constants.SYSTEM_HEAD_IMAGE_PATH
					+ "default40away.png"));
			break;
		case Constants.STATUS_BUSY:
			image = new ImageIcon(UserInfoUtil.class.getResource(Constants.SYSTEM_HEAD_IMAGE_PATH
					+ "default40busy.png"));
			break;

		}
		return image;
	}
	
	public static String getProperty(String id,String propertyName) throws Exception{
		return getProperty(id+SUFFIX+propertyName);
	}
	
	public static void main(String[] args) {
		try {
			List<String> allUserNames = UserInfoUtil.getAllUserNames();
			for (String string : allUserNames) {
				System.out.println(string);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void clear() throws BackingStoreException{
		preferences.clear();
	}
}
