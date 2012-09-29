/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.common.service;

import com.lorent.common.app.AppContext;
import com.lorent.common.app.Context;

/**
 *
 * @author jack
 */
public abstract class BaseService implements Context{
	protected AppContext context;
	
	public void setContext(AppContext context) {
		this.context = context;
	}
}
