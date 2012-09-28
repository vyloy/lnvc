/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.common.util;

import java.lang.reflect.Method;

/**
 *
 * @author Administrator
 */
public class MethodUtil {
    
    public static Object invoke(Object obj,Method method,Object... paras) throws Exception{
        Object returnObj = null;
        if(method.getParameterTypes().length<1){//判断方法是否有参数
            returnObj = method.invoke(obj);
        }else{
            if(paras==null||paras.length<1){
                    returnObj = method.invoke(obj, (Object)paras);
            }else{
                returnObj = method.invoke(obj, paras);
            }
        }
        return returnObj;
    }
    
}
