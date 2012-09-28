package com.lorent.common.manager;

import com.lorent.common.app.AppContext;
import com.lorent.common.app.Context;

public class BaseManager implements Context{
	
	protected AppContext context;

	public void setContext(AppContext context) {
		this.context = context;
	}
	
}
