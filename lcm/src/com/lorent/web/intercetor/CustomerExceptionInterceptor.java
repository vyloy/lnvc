package com.lorent.web.intercetor;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CustomerExceptionInterceptor extends AbstractInterceptor {

	private Logger log = Logger.getLogger(CustomerExceptionInterceptor.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			return invocation.invoke();
		} catch (Exception e) {
			log.error("CustomerExceptionInterceptor", e);
			invocation.getStack().push(invocation.getAction());
			throw e;
		}
	}

}
