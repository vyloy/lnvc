package com.lorent.web.xmlrpc.handler;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.lorent.service.impl.ServiceFacade;
import com.lorent.web.util.ServerInitServletProxy;

public class BaseHandler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ServiceFacade serviceFacade = (ServiceFacade)ServerInitServletProxy.getContext().getBean("serviceFacade");
	public static final Map<String, String>validateMap = new HashMap<String, String>();
}
