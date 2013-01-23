/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.common.controller;


import javax.swing.JOptionPane;

import com.lorent.common.app.AppContext;
import com.lorent.common.app.Context;

/**
 *
 * @author jack
 */
public abstract class BaseController implements Context{

	
    public void showErrorDialog(String title, String msg) {
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * 点击取消返回值为2，点击确定返回值为0，点击关闭返回值为-1
     */
    public int showConfirmDialog(String title, String msg) {
        return JOptionPane.showConfirmDialog(null, msg, title, JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
    }
    
    public void showMessageDialog(String title, String msg) {
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

	protected AppContext context;
	
	public void setContext(AppContext context) {
		this.context = context;
	}
	
	public String getUIString(String key){
		return context.getViewManager().getUIString(key);
	}
	
	
	public <T> T getValue(String key){
		return (T)context.getDataManager().getValue(key);
	}
	
	public void setValue(String key, Object o){
		context.getDataManager().setValue(key, o);
	}
    
}
