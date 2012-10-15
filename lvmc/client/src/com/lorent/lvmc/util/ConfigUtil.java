/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class ConfigUtil {
    private static Logger log = Logger.getLogger(ConfigUtil.class);
    private static Properties properties;
    private static final String fileName = "lvmc.conf";
    
    static{
        init();
    }
    
    private static void init(){
        properties = new Properties();
        InputStreamReader inputStreamReader = null;
        try {
            String filepath = com.lorent.lvmc.util.Constants.UserPath + "/lorent/lvmc/" + fileName;
            File file = new File(filepath);
            if (!file.exists()) {
            	log.info("create " + fileName);
            	File path = new File(com.lorent.lvmc.util.Constants.UserPath + "/lorent/lvmc/");
            	path.mkdirs();
            	FileUtil.fileCopy("/com/lorent/lvmc/config/"+fileName, filepath);
            }else{
            	Properties newProp = new Properties();
            	newProp.load(FileUtil.class.getResourceAsStream("/com/lorent/lvmc/config/" + fileName));
            	String newVersion = newProp.getProperty("confversion");
            	inputStreamReader = new InputStreamReader(new FileInputStream(filepath), "UTF-8");
            	properties.load(inputStreamReader);
            	inputStreamReader.close();
            	String curVersion = properties.getProperty("confversion");
            	if(curVersion == null || Integer.parseInt(newVersion) > Integer.parseInt(curVersion)){//重新加载
            		log.info("reload " + fileName);
            		FileUtil.fileCopy("/com/lorent/lvmc/config/"+fileName, filepath);
            	}
            	properties.clear();
            }
            inputStreamReader = new InputStreamReader(new FileInputStream(filepath), "UTF-8");
            properties.load(inputStreamReader);
            inputStreamReader.close();
        }catch (Exception ex) {
            log.error("init", ex);
        }
    }
    
    public static void setProperty(String key,String value) throws Exception{
        properties.setProperty(key, value);
        try {
            FileOutputStream outputStream = new FileOutputStream(com.lorent.lvmc.util.Constants.UserPath + "/lorent/lvmc/"+fileName);
            properties.store(outputStream, "");
            outputStream.close();
        } catch (IOException ex) {
            //java.util.logging.Logger.getLogger(ConfigUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    public static String getProperty(String key){
        return  properties.getProperty(key);
    }
    
    public static int getIntProperty(String key){
        //log.info (key+"==============");
        return Integer.parseInt(getProperty(key));
    }
    
    public static int getIntProperty(String key,int defaultValue){
        //log.info (key+"==============");
        return Integer.parseInt(getProperty(key,String.valueOf(defaultValue)));
    }
    
    public static long getLongProperty(String key){
        return Long.parseLong(getProperty(key));
    }
    
    public static boolean getBoolProperty(String key){
        return Boolean.parseBoolean(getProperty(key));
    }
    
    public static boolean getBoolProperty(String key, Boolean defaultValue){
        return Boolean.parseBoolean(getProperty(key, defaultValue.toString()));
    }
    
    public static String getProperty(String key, String defaultValue){
        return  properties.getProperty(key, defaultValue);
    }
    
    public static void main(String args[]){
        System.out.println(com.lorent.lvmc.util.Constants.AppPath);
    }
    
    public static Map<String,String> getLayOutMap(){
    	String tag1 = ";";
    	String tag2 = ":";
    	Map<String,String> map = new HashMap<String,String>();
    	String layouts = ConfigUtil.getProperty("video.layout","");
    	String[] infos = layouts.split(tag1);
    	for(int i=0;i<infos.length;i++){
    		if(infos[i]!=null&&infos[i].indexOf(tag2)>0){
    			String[] kv = infos[i].split(tag2);
    			map.put(kv[0], kv[1]);
    		}
    	}
    	return map;
    }
}
