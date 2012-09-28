/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.vovo.util;
import java.text.SimpleDateFormat;
import java.util.Date;
public class DateUtil {
    
    public static Date getDateByString(String dstr)throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(dstr);
        return date;
    }
    
    public static String getStringByDate(Date d){
        if(d == null){
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dstr = format.format(d);
        return dstr;
    }

}
