/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jack
 */
public class ParaUtil {
    private Map<String, Object> map = new HashMap<String, Object>();
    private ParaUtil(){
        
    }
    public static ParaUtil newInstance(){
        return new ParaUtil();
    }
    public ParaUtil setInt(String name, int value){
        map.put(name, value);
        return this;
    }
    public ParaUtil setString(String name, String value){
        map.put(name, value);
        return this;
    }
    public ParaUtil setBoolean(String name, boolean value){
    	map.put(name, value);
    	return this;
    }
    public ParaUtil setObject(String name, Object value){
    	map.put(name, value);
    	return this;
    }
    
    public Map<String, Object> getMap(){
        return map;
    }
    public <T> T getValue(String name){
        return (T)map.get(name);
    }
}
