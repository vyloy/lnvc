/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 *
 * @author Jack
 */
public class ConfigUtil {
    private Logger log = Logger.getLogger(ConfigUtil.class);
    private Properties properties;
    private String filePath;

	public ConfigUtil(String path){
		filePath = path;
        loadProperties();
    }

	private void loadProperties() {
		properties = new Properties();
        InputStreamReader inputStreamReader = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    try {
                        throw new Exception("创建文件: "+filePath+"  失败!");
                    } catch (Exception ex) {
                        log.error("init" ,ex);
                    }
                }
            }else{
                inputStreamReader = new InputStreamReader(new FileInputStream(filePath), "UTF-8");
                properties.load(inputStreamReader);
            }
        }catch (Exception ex) {
            log.error("init", ex);
        }
	}
    
    public void setProperty(String key,String value) throws Exception{
        properties.setProperty(key, value);
        try {
            FileOutputStream outputStream = new FileOutputStream(filePath);
            properties.store(outputStream, "");
            outputStream.close();
        } catch (IOException ex) {
            throw ex;
        }
    }
    
    public int getIntProperty(String key, int defaultValue){
        return Integer.parseInt(getProperty(key, defaultValue + "", false));
    }
    
    public long getLongProperty(String key, long defaultValue){
        return Long.parseLong(getProperty(key, defaultValue + "", false));
    }
    
    public boolean getBoolProperty(String key, boolean defaultValue){
        return Boolean.parseBoolean(getProperty(key, defaultValue + "", false));
    }
    
    public String getProperty(String key, String defaultValue, boolean reload){
    	if(reload){
    		loadProperties();
    	}
        return  properties.getProperty(key, defaultValue);
    }
}
