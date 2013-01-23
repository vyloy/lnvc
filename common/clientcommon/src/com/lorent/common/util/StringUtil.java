/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.common.util;

import java.lang.reflect.Array;
import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import org.springframework.util.StringUtils;

/**
 *
 * @author jack
 */
public class StringUtil {
    
    public static String getResourceString(String file, String key){
        ResourceBundle bundle = ResourceBundle.getBundle(file); 
        return bundle.getString(key);
    }
    
    public static String getFormatString(String oriStr, Object...paras){
        return MessageFormat.format(oriStr, paras);
    }
    
    public static <T> T[] parseListToArray(List<T> list, Class<T> clazz){
            T[] t = (T[])Array.newInstance(clazz, list.size());
            for(int i = 0; i < list.size(); i++){
                    t[i] = list.get(i);
            }
            return t;
    }
    
    public static <T> T[] parseSetToArray(Set<T> set, Class<T> clazz){
    	T[] t = (T[])Array.newInstance(clazz, set.size());
        set.toArray(t);
	    return t;
    }
    
    public static String convertFilePath2DOSCommandStr(String filePath){
    	return "\"" + filePath + "\"";
    }
    
    public static String arrayToDelimitedString(Object[] paras, String delim){
    	return StringUtils.arrayToDelimitedString(paras, delim);
    }
    
    public StringUtil(){
    }
    
    private ResourceBundle bundle;
    
    public StringUtil(String file){
    	bundle = ResourceBundle.getBundle(file);
    }
    
    public String getString(String key){
    	return bundle.getString(key);
    }
}
