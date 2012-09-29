/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class ConfigUtil {
    private static Logger log = Logger.getLogger(ConfigUtil.class);
    private static Properties properties;
    private static final String fileName = "test.conf";
    
    static{
        init();
    }
    
    private static void init(){
        properties = new Properties();
        InputStreamReader inputStreamReader = null;
        try {
            String filepath = fileName;
            File file = new File(filepath);
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    try {
                        throw new Exception("创建文件: "+filepath+"  失败!");
                    } catch (Exception ex) {
                        log.info(ex);
                    }
                }
                properties.load(ConfigUtil.class.getResourceAsStream("/com/lorent/lvmc/config/lvmc.conf"));
                properties.store(new FileOutputStream(file), "");
            }else{
                inputStreamReader = new InputStreamReader(new FileInputStream(filepath), "UTF-8");
                properties.load(inputStreamReader);
            }
        }catch (Exception ex) {
            log.error("", ex);
        }
    }
    
    public static void setProperty(String key,String value) throws Exception{
        properties.setProperty(key, value);
        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
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
    
}
