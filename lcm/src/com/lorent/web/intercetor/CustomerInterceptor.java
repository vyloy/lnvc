package com.lorent.web.intercetor;

import org.apache.struts2.ServletActionContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lorent.model.UserBean;
import com.lorent.service.impl.ServiceFacade;
import com.lorent.util.ThreadLocaleUtil;
import com.lorent.web.action.BaseAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
public class CustomerInterceptor extends AbstractInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			if(invocation.getAction() instanceof BaseAction){
				UserBean user = ThreadLocaleUtil.getUser();
				if(user!=null){
					WebApplicationContext wc = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
					ServiceFacade serviceFacade = (ServiceFacade)wc.getBean("serviceFacade");
					boolean isvalid = serviceFacade.getUserService().userIsValid(user);
					if(!isvalid){
						SecurityContextHolder.getContext().setAuthentication(null);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		((ActionSupport)invocation.getAction()).setFieldErrors(null);
		return invocation.invoke();
	}
}
