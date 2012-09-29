/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.common.filter;


/**
 *
 * @author jack
 */
public interface Filter {
    public void doFilter(ExecuteObject bean, String[] paras)throws Exception;
}
