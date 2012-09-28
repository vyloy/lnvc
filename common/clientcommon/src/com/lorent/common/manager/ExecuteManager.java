/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.common.manager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.lorent.common.app.Context;
import com.lorent.common.controller.BaseController;
import com.lorent.common.filter.ExecuteObject;
import com.lorent.common.filter.FilterTag;
import com.lorent.common.service.BaseService;
import com.lorent.common.util.MethodUtil;
import com.lorent.common.util.StringUtil;

/**
 *
 * @author jack
 */
public class ExecuteManager extends BaseManager{

    private Map<String, Method> methods = new HashMap<String, Method>();
	private Logger log = Logger.getLogger(ExecuteManager.class);
	private String resourcePath = "com/lorent/common/resource/i18n/view";	
	
    private Object execute(Object bean, String className, String methodName, Object... paras)throws Exception{
        Object invoke = null;
        
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
            ((Context)bean).setContext(context);
            methods.put(key, method);
        }
        boolean hasAnnotation = method.isAnnotationPresent(FilterTag.class);
        if(hasAnnotation){
            FilterTag filterAnnotation = method.getAnnotation(FilterTag.class);  
            ExecuteObject eo = new ExecuteObject();
            eo.setBean(bean);
            eo.setMethod(method);
            eo.setParas(paras);
            invoke = context.getFilterManager().doFilter(eo, filterAnnotation.id(), filterAnnotation.paras());
        }else{
            invoke = MethodUtil.invoke(bean, method, paras);
        }

        return invoke;
    }
    
    public Object executeController(String className, String methodName, Object... paras){
    	Object bean = null;
    	Object invoke = null;
    	try{
    		className = className + "Controller";
            bean= context.getSpringContext().getBean(className);
            if(bean instanceof BaseController){
            	invoke = execute(bean, className, methodName, paras);
            }else{
            	throw new Exception(className  + " is not controller ");
            }
	    } catch (InvocationTargetException e) {
			log.error("ControllerFacade.execute", e);
			if(e.getTargetException() instanceof InvocationTargetException){
				((BaseController) bean).showErrorDialog(StringUtil
						.getResourceString(resourcePath, "error.title"), ((InvocationTargetException)e
						.getTargetException()).getTargetException().getMessage());
			}else{
				((BaseController) bean).showErrorDialog(StringUtil
						.getResourceString(resourcePath, "error.title"), e
						.getTargetException().getMessage());
			}
		} catch (Exception ex) {
			log.error("ControllerFacade.execute", ex);
			((BaseController) bean).showErrorDialog(StringUtil
					.getResourceString(resourcePath, "error.title"), ex
					.getMessage());
		}
		return invoke;
    }
    
    public Object executeService(String className, String methodName, Object... paras)throws Exception{
    	className = className + "Service";
    	Object bean = context.getSpringContext().getBean(className);
        if(bean instanceof BaseService){
        	return execute(bean, className, methodName, paras);
        }else{
        	throw new Exception(className  + " is not service ");
        }
    }
    
    
}
