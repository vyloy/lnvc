/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.util;

import java.io.File;
import java.math.BigDecimal;

/**
 *
 * @author Administrator
 */
public class MathUtil {
    private static String[] lenUnits = {"B","KB","MB","GB","TB"};
    public static String convertByte(double size,int i){
        
        if(size < 1024){
            return getLengthInfo(size,lenUnits[i]);
        }
        i ++ ;
        double s = size / 1024;
//        double r = size % 1024;
//        if(r != 0){
//            s = s + 1;
//        }
        
        if(s > 1024){
            return convertByte(s,i);
        }else{
            int temp = (int)(s * 100);
            s = (double)temp / 100;
            
            return getLengthInfo(s,lenUnits[i]);
        }
    }
    
    public static String getLengthInfo(double s,String lenUnit){
        return isZero(s)?(int)s + lenUnit : s + lenUnit;
    }
    
    public static boolean isZero(double num){
        boolean isZero = true;
        String str = String.valueOf(num);
        int lastIndex = str.lastIndexOf(".");
        for(int t=lastIndex+1;t<str.length();t++){
            if(str.charAt(t)!='0'){
                isZero = false;
                break;
            }
        }
        return isZero;
    }
    
    public static String convertByteToKB(long size){
        long s = size / 1024;
        long r = size % 1024;
        if(r != 0){
            s = s + 1;
        }
        return s + "KB";
    }
    
    public static String convertTransferSpeed(long size){
        return "   " + convertByte(size,0) + "/S   ";
    }
    
    public static String convertFileSize(long size){
        return convertByte(size,0);
    }
    
    public static void main(String args[]){
        System.out.println(convertByte(52428800,0));
        Object obj = "dsfasd";
        String str = convertType(obj);
        System.out.println(str);
    }
    
    public static <T> T convertType(Object obj){
        return (T)obj;
    } 
    
    public static double getTwoDecimal(double d){
    	BigDecimal bg = new BigDecimal(d);
        double f = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f;
    }
    
}
