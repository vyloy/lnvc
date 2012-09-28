/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.controller;

import com.lorent.lvmc.util.DataUtil;
import com.lorent.common.util.ParaUtil;
import com.lorent.lvmc.filter.ExecuteObject;
import com.lorent.lvmc.filter.FilterManager;
import com.lorent.lvmc.filter.FilterTag;
import com.lorent.lvmc.util.Constants;
import com.lorent.lvmc.util.MethodUtil;
import com.lorent.lvmc.util.StringUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Filter;
import org.apache.log4j.Logger;

/**
 *
 * @author jack
 */
public class ControllerFacade {

    private static Logger log = Logger.getLogger(ControllerFacade.class);
    private static Map<String, Method> methods = new HashMap<String, Method>();
    
    public static Object execute(String className, String methodName, Object... paras) {
        Object invoke = null;
        Object bean = null;
        try{
            bean= DataUtil.getApplicationContext().getBean(className);
            Method method = null;
            String key = className + "_" + methodName;
            if(methods.containsKey(key)){
                method = methods.get(key);
            }else{
                Method[] ts = bean.getClass().getMethods();
                for(Method t : ts){
                    if(t.getName().equals(methodName)){
                        method = t;
                        break;
                    }
                }
                if(method == null){
                    throw new Exception(className  + " no such method " + methodName);
                }
                methods.put(key, method);
            }
            boolean hasAnnotation = method.isAnnotationPresent(FilterTag.class);
            if(hasAnnotation){
                FilterTag filterAnnotation = method.getAnnotation(FilterTag.class);  
                ExecuteObject eo = new ExecuteObject();
                eo.setBean(bean);
                eo.setMethod(method);
                eo.setParas(paras);
//                Map<String,Object[]> filterParasMap = new HashMap<String,Object[]>();
//                filterParasMap.put(Constants.FILTER_STATIC_PARAS, filterAnnotation.paras());
//                if(filterDynamicParas!=null && filterDynamicParas.length>0){
//                    filterParasMap.put(Constants.FILTER_DYNAMIC_PARAS, filterDynamicParas);
//                }else{
//                    filterParasMap.put(Constants.FILTER_DYNAMIC_PARAS, null);
//                }
                invoke = FilterManager.getInstance().doFilter(eo, filterAnnotation.id(), filterAnnotation.paras());
            }else{
//                invoke = method.invoke(bean, paras);
                invoke = MethodUtil.invoke(bean, method, paras);
            }
        }catch(InvocationTargetException e){
//            e.printStackTrace();
            log.error("ControllerFacade.execute", e);
            ((BaseController) bean).showErrorDialog(StringUtil.getErrorString("error.title"), e.getTargetException().getMessage());
        } catch (Exception ex) {
//            ex.printStackTrace();
            log.error("ControllerFacade.execute", ex);
            ((BaseController) bean).showErrorDialog(StringUtil.getErrorString("error.title"), ex.getMessage());
        } 
        return invoke;
    }
    
    /**
     * 
     * @param className  
     * @param methodName
     * @param filterDynamicParas   传入过滤器的dofilter参数
     * @param paras 传入CONTROLLER执行方法的参数
     * @return 
     */
//    public static Object execute(String className, String methodName,String[] filterDynamicParas, Object... paras) {
//        Object invoke = null;
//        Object bean = null;
//        try{
//            bean= DataUtil.getApplicationContext().getBean(className);
//            Method method = null;
//            String key = className + "_" + methodName;
//            if(methods.containsKey(key)){
//                method = methods.get(key);
//            }else{
//                Method[] ts = bean.getClass().getMethods();
//                for(Method t : ts){
//                    if(t.getName().equals(methodName)){
//                        method = t;
//                        break;
//                    }
//                }
//                if(method == null){
//                    throw new Exception(className  + " no such method " + methodName);
//                }
//                methods.put(key, method);
//            }
//            boolean hasAnnotation = method.isAnnotationPresent(FilterTag.class);
//            if(hasAnnotation){
//                FilterTag filterAnnotation = method.getAnnotation(FilterTag.class);  
//                ExecuteObject eo = new ExecuteObject();
//                eo.setBean(bean);
//                eo.setMethod(method);
//                eo.setParas(paras);
//                Map<String,Object[]> filterParasMap = new HashMap<String,Object[]>();
//                filterParasMap.put(Constants.FILTER_STATIC_PARAS, filterAnnotation.paras());
//                if(filterDynamicParas!=null && filterDynamicParas.length>0){
//                    filterParasMap.put(Constants.FILTER_DYNAMIC_PARAS, filterDynamicParas);
//                }else{
//                    filterParasMap.put(Constants.FILTER_DYNAMIC_PARAS, null);
//                }
//                invoke = FilterManager.getInstance().doFilter(eo, filterAnnotation.id(), filterParasMap);
//            }else{
////                invoke = method.invoke(bean, paras);
//                invoke = MethodUtil.invoke(bean, method, paras);
//            }
//        }catch(InvocationTargetException e){
////            e.printStackTrace();
//            log.error("ControllerFacade.execute", e);
//            ((BaseController) bean).showErrorDialog(StringUtil.getErrorString("error.title"), e.getTargetException().getMessage());
//        } catch (Exception ex) {
////            ex.printStackTrace();
//            log.error("ControllerFacade.execute", ex);
//            ((BaseController) bean).showErrorDialog(StringUtil.getErrorString("error.title"), ex.getMessage());
//        } 
//        return invoke;
//    }
    
    
}
