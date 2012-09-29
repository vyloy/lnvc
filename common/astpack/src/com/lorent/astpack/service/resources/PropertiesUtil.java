package com.lorent.astpack.service.resources;

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
	private final static String PACKAGE_NAME="com.lorent.service.resources.";

	public static String getProperty(String fileName,String key,boolean locale) {
		try {
			if(locale)
				return ResourceBundle.getBundle(PACKAGE_NAME+fileName,ThreadLocaleUtil.getLocale()).getString(key);
			else 
				return getProperty(fileName, key);
		} catch (Exception e) {
			return key;
		}
	}

	public static String getProperty(String fileName,String key) {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(PACKAGE_NAME+fileName);
			return bundle.getString(key);
		} catch (Exception e) {
			return key;
		}
	}
	
	public static String getConstant(String key){
		return getProperty("Const", key);
	}
	
	public static void setConstant(String key,String value){
		String fileName = PropertiesUtil.class.getResource("/").getPath();
		fileName += "com/lorent/service/resources/";
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
			props.store(os, "Update "+key+" ");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
