package com.lorent.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.ResourceBundle;

public class PropertiesUtil {
	private final static String PACKAGE_NAME="com.lorent.resources.";
	/**
	 * 获取filename为baseName的属性文件中key对应的值，locale指定是否进行本地化
	 * @param fileName
	 * @param key
	 * @param locale
	 * @return
	 */
	public static String getProperty(String fileName,String key,boolean locale) {
		try {
			if(locale){
				return ResourceBundle.getBundle(PACKAGE_NAME+fileName, ThreadLocaleUtil.getLocale()).getString(key);
			}else 
				return getProperty(fileName, key);
		} catch (Exception e) {
			return key;
		}
	}
	/**
	 * 读取fileName指定的属性文件
	 * @param fileName
	 * @param key
	 * @return
	 */
	public static String getProperty(String fileName,String key) {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(PACKAGE_NAME+fileName);
			return bundle.getString(key);
		} catch (Exception e) {
			return key;
		}
	}
	
	public static String getMsgResource(String key){
		return getProperty("messageResource", key, true);
	}
	
	public static String getErrorResource(String key){
		return getProperty("exceptionResource", key, true);
	}
	
	public static String getConstant(String key){
		return getProperty("Const", key);
	}
	
	public static String getConstant(String key, String defaultValue){
		String value = getConstant(key);
		if(value.equals(key)){
			return defaultValue;
		}else{
			return value;
		}
	}
	
	public static void setConstant(String key,String value){
		String fileName = PropertiesUtil.class.getResource("/").getPath();
		fileName += "com/lorent/resources/";
		fileName += "Const.properties";
		System.out.println("++++++++++++++++++++++++++++++"+fileName);
//		InputStream is = PropertiesUtil.class.getResourceAsStream(fileName);
		File file = new File(fileName);
		Properties props = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(fileName));
			props.load(in);
			OutputStream os = new FileOutputStream(fileName);
			props.setProperty(key, value);
			props.store(os, "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
//		ThreadLocaleUtil.setLocale(Locale.CHINA);
//		System.out.println(getProperty("exceptionResource", "modelcheckfail.notinit", true));
//		ModelCheckFailException e = new ModelCheckFailException("modelcheckfail.notinit");
//		System.out.println(e.getMessage());
//		System.out.println(PropertiesUtil.getConstant("record_new"));
//		System.out.println(getConstant("rsm.xmlrpc"));
		setConstant("record_new", "1");
		for(int i = 0; i < 10; i ++){
			setConstant("record_new", i + "");
			System.out.println(getConstant("record_new"));
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
