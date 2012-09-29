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
public class DataUtil {

    private final Map<String, Object> globalVar = new HashMap<String, Object>();
    
    public <T> T getValue(String key) {
        return (T)globalVar.get(key);
    }
    
    public void clear(){
        globalVar.clear();
    }
    
    public void setValue(String key, Object value){
        globalVar.put(key, value);
    }
    
    public <T> T removeByKey(String key){
    	return (T)globalVar.remove(key);
    }


}
