package com.lorent.lvmc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.lorent.lvmc.dto.LoginInfo;

public class UserInfoUtil {
	
	private static final String FILE_PATH = "userinfo.properties";
	private static final String AUTOLOGIN_SUFFIX = "_autologin";
	private static final String SAVEPASSWD_SUFFIX = "_savepasswd";
	
	private static String getProperty(String key)throws Exception{
		File file = new File(FILE_PATH);
		if(file.exists()){
			Properties props = new Properties();
			props.load(new FileInputStream(file));
			return props.getProperty(key);
		}else{
			return null;
		}
	}
	
	private static Map<String, String> getProperty(String[] keys)throws Exception{
		File file = new File(FILE_PATH);
		if(file.exists()){
			Properties props = new Properties();
			props.load(new FileInputStream(file));
			Map<String, String> map = new HashMap<String, String>();
			for(String key : keys){
				map.put(key, props.getProperty(key));
			}
			return map;
		}else{
			return null;
		}
	}
	
	public static void setProperty(String key, String value)throws Exception{
		File file = new File(FILE_PATH);
		if(!file.exists()){
			file.createNewFile();
		}
		Properties props = new Properties();
		props.load(new FileInputStream(file));
		props.setProperty(key, value);
		props.store(new FileOutputStream(file), null);
	}
	
	public static List<String> getAllUserNames()throws Exception{
		File file = new File(FILE_PATH);
		if(file.exists()){
			Properties props = new Properties();
			props.load(new FileInputStream(file));
			Set<Object> keySet = props.keySet();
			List<String> list = new ArrayList<String>();
			for(Object key : keySet){
				String temp = (String)key;
				if(!temp.contains("_")){
					list.add((String)key);
				}
			}
			return list;
		}else{
			return new ArrayList<String>();
		}
	}
	
	public static String getPassword(String id)throws Exception{
		String value = getProperty(id);
		if(value != null){
			return PasswordUtil.getDesString(value);
		}else{
			return "";
		}
	}
	
	public static void setInfo(String id, String password, boolean autoLogin, boolean savePasswd)throws Exception{
		if(savePasswd){
			setProperty(id, PasswordUtil.getEncString(password));
		}else{
			setProperty(id, "");
		}
		setProperty(id + AUTOLOGIN_SUFFIX, autoLogin + "");
		setProperty(id + SAVEPASSWD_SUFFIX, savePasswd + "");
	}
	
	public static LoginInfo getInfo(String id)throws Exception{
		String[] keys = new String[]{id, id + AUTOLOGIN_SUFFIX, id + SAVEPASSWD_SUFFIX};
		Map<String, String> map = getProperty(keys);
		if(map == null){
			return null;
		}
		LoginInfo info = new LoginInfo();
		info.setUsername(id);
		info.setPassWord(PasswordUtil.getDesString(map.get(id)));
		info.setAutoLogin(Boolean.parseBoolean(map.get(id + AUTOLOGIN_SUFFIX)));
		info.setSavePasswd(Boolean.parseBoolean(map.get(id + SAVEPASSWD_SUFFIX)));
		return info;
	}
	
	
	
	public static void main(String[] args) {
		List<String> a = new ArrayList<String>();
		a.add("aaa");
		a.add("ccc");
		a.add("bbb");
//		a.remove(new String("ccc"));
		a.add(0, "ccc");
		for(String temp : a){
			System.out.println(temp);
		}
		
	}
}
