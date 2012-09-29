package com.lorent.commonconfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ConfigUtil {

	private Properties properties;
	private String configPath;
	private boolean reload;
	private long modifyTime;
	private Object reloadLock = new Object();
	private InputStream is;
	/**
	 * 
	 * @param path 配置文件的路径
	 * @param reload 设置为true，手工修改配置文件会重新加载配置文件；设置为false，手工修改配置文件，不会自动加载
	 */
	public ConfigUtil(String path,boolean reload){
		configPath = path;
		this.reload = reload;
		load();
		/*if(reload){
			new Thread(){
				public void run(){
					while(true){
						try {
							Thread.sleep(60000);
						} catch (InterruptedException e) {
							
						}
						reload();
					}
				}
			}.start();
		}*/
	}
	
	public ConfigUtil(InputStream is){
		this.is = is;
		loadByStream();
	}
	
	public void loadByStream(){
		if(properties == null){
			properties = new Properties();
		}else{
			properties.clear();
		}
        InputStreamReader inputStreamReader = null;
        try {
        	inputStreamReader = new InputStreamReader(is, "UTF-8");
			properties.load(inputStreamReader);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	public void load(){
		if(properties == null){
			properties = new Properties();
		}else{
			properties.clear();
		}
        InputStreamReader inputStreamReader = null;
        try {
        	File file = new File(configPath);
        	if (!file.exists()) {
        		file.getParentFile().mkdirs();
        		file.createNewFile();
    		}
        	modifyTime = file.lastModified();
			inputStreamReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
			properties.load(inputStreamReader);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	public void reload(){
//		synchronized(reloadLock){
			if(this.reload){
				File file = new File(configPath);
				if(file.exists()){
					long newModifyTime = file.lastModified();
					if(newModifyTime!=modifyTime){
						load();
					}
				}
			}
//		}
	}
	
	
	public int getIntProperty(String key){
        //log.info (key+"==============");
        return Integer.parseInt(getProperty(key));
    }
    
    public long getLongProperty(String key){
        return Long.parseLong(getProperty(key));
    }
    
    public boolean getBoolProperty(String key){
        return Boolean.parseBoolean(getProperty(key));
    }
    
    public boolean getBoolProperty(String key, Boolean defaultValue){
        return Boolean.parseBoolean(getProperty(key, defaultValue.toString()));
    }
	
	
	public void setProperty(String key,String value) throws Exception{
		if(this.reload){
			synchronized(this){
				setProperty1(key, value);
			}
		}else{
			setProperty1(key,value);
		}
	}
	
	private void setProperty1(String key,String value) throws Exception{
        properties.setProperty(key, value);
        try {
            FileOutputStream outputStream = new FileOutputStream(configPath);
            properties.store(outputStream, "");
            outputStream.close();
            File file = new File(configPath);
            if(file.exists()){
            	modifyTime = file.lastModified();
            }
        } catch (IOException ex) {
            throw ex;
        }
    }
	
	public String getProperty(String key, String defaultValue){
		if(this.reload){
			synchronized(this){
				return getProperty1(key, defaultValue);
			}
		}else{
			return getProperty1(key,defaultValue);
		}
    }
	
	private String getProperty1(String key, String defaultValue){
		reload();
        String t = properties.getProperty(key);
        if(t==null){
        	t = defaultValue;
        }
        return t;
    }
    
    public String getProperty(String key){
        return  getProperty(key,null);
    }
	
    public synchronized void removeProperty(String key) throws Exception{
    	if(this.reload){
    		synchronized(this){
    			removeProperty1(key);
    		}
    	}else{
    		removeProperty1(key);
    	}
    }
    
    private void removeProperty1(String key) throws Exception{
    	properties.remove(key);
        try {
            FileOutputStream outputStream = new FileOutputStream(configPath);
            properties.store(outputStream, "");
            outputStream.close();
            File file = new File(configPath);
            if(file.exists()){
            	modifyTime = file.lastModified();
            }
        } catch (IOException ex) {
            throw ex;
        }
    }
    
    
    public static void main(String args[])throws Exception{
    	File f = new File("e:/aa/aa.txt");
    	if(!f.exists()){
    		f.getParentFile().mkdirs();
    		f.createNewFile();
    	}
    }
    
    public Properties getProperties(){
    	reload();
    	return properties;
    }
}
