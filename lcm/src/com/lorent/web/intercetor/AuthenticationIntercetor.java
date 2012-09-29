package com.lorent.web.intercetor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthenticationIntercetor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
//		SecurityContextHolder.getContext().setAuthentication(ThreadLocaleUtil.getLocaleAuthentication());
		return invocation.invoke(); 
	}

}
