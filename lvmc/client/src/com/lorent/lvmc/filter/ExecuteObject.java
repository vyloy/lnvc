/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.filter;

import java.lang.reflect.Method;

/**
 *
 * @author jack
 */
public class ExecuteObject {
    private Object bean;
    private Method method;
    private Object[] paras;

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object[] getParas() {
        return paras;
    }

    public void setParas(Object[] paras) {
        this.paras = paras;
    }
    
}
