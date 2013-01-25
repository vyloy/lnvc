/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.util;

import java.lang.reflect.Array;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author jack
 */
public class StringUtil {
    public static String getUIString(String key){
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/lorent/lvmc/resource/i18n/view"); 
        return bundle.getString(key);
    }
    
    public static String getErrorString(String key){
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/lorent/lvmc/resource/i18n/error"); 
        return bundle.getString(key);
    }
    
    public static String getFormatString(String oriStr, Object...paras){
        return MessageFormat.format(oriStr, paras);
    }
    
    public static String getAppString(String key){
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/lorent/lvmc/config/app"); 
        return bundle.getString(key);
    }
    
    public static String utf8Togb2312(String str)throws Exception{
      StringBuffer sb = new StringBuffer();
      for(int i=0; i<str.length(); i++) {
          char c = str.charAt(i);
          switch (c) {
             case '+':
                 sb.append(' ');
             break;
             case '%':
                 try {
                      sb.append((char)Integer.parseInt(
                      str.substring(i+1,i+3),16));
                 }
                 catch (NumberFormatException e) {
                     throw new IllegalArgumentException();
                }
                i += 2;
                break;
             default:
                sb.append(c);
                break;
           }
      }
      // Undo conversion to external encoding
      String result = sb.toString();
      String res=null;
      byte[] inputBytes = result.getBytes("8859_1");
      res= new String(inputBytes,"gb2312");
     
      return res;
    }
    
    public static <T> T[] parseListToArray(List<T> list, Class<T> clazz){
            T[] t = (T[])Array.newInstance(clazz, list.size());
            for(int i = 0; i < list.size(); i++){
                    t[i] = list.get(i);
            }
            return t;
    }
    
    public static String convertSpecialCharString(String content){
        if(content == null){
            return null;
        }
        Map<String,String> map = new HashMap<String,String>();
        map.put("&", "&amp;");
        Set<Entry<String, String>> set = map.entrySet();
        for(Entry<String, String> entry:set){
            String key = entry.getKey();
            String value =entry.getValue();
            content = content.replaceAll(key, value);
        }
        return content;
    }
    
    public static String convertFilePath2DOSCommandStr(String filePath){
    	return "\"" + filePath + "\"";
    }
}
