package com.lorent.web.intercetor;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationException;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.ui.webapp.AuthenticationProcessingFilter;

import com.lorent.exception.CustomAuthenticationException;
import com.lorent.model.CustomerBean;
import com.lorent.model.OperateRecord;
import com.lorent.model.UserBean;
import com.lorent.service.impl.ServiceFacade;
import com.lorent.util.Constant;
import com.lorent.util.PropertiesUtil;
import com.lorent.util.ThreadLocaleUtil;

public class CustomAuthenticationProcessingFilter extends
		AuthenticationProcessingFilter {
	private ServiceFacade serviceFacade;
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request)
			throws AuthenticationException {
		if(SecurityContextHolder.getContext().getAuthentication()!=null)
			return SecurityContextHolder.getContext().getAuthentication();
		LocaleContextHolder.setLocale(ThreadLocaleUtil.getLocale());
		HttpSession session = request.getSession();
		session.setAttribute("login_error", null);
		String code = request.getParameter("code");
		String sessionName = PropertiesUtil.getProperty("Const", "session.name.imageCode");
		Authentication authentication = null;
		UserBean user = null;
		try{
			authentication = super.attemptAuthentication(request);
			user = (UserBean)authentication.getPrincipal();
			if(user!=null){
				if(user.getRoles()==null||user.getRoles().size()==0)
					throw new CustomAuthenticationException("authen.wrong.norole");
				CustomerBean customer = user.getCustomer();
				if(customer!=null&&!customer.getStatus().equals(Constant.RECORD_STATUS_VALID))
					throw new CustomAuthenticationException("authen.wrong.customerdelete");
//				Hibernate.initialize(user.getUserStatus());
				Hibernate.initialize(customer);
				if(customer!=null){
					Hibernate.initialize(customer.getMcuServer());
//					Hibernate.initialize(customer.getNos());
					Hibernate.initialize(customer.getRates());
				}
				if(customer.getMcuServer()!=null)
					Hibernate.initialize(customer.getMcuServer().getMixers());
				OperateRecord record = new OperateRecord();
				record.setOpdisc(PropertiesUtil.getProperty("loggerMessage", "logger.loginmsg",true));
				record.setOptime(new Timestamp(System.currentTimeMillis()));
				record.setUserId(user.getId());
				record.setUserName(user.getUsername());
				serviceFacade.getOperateRecordService().record(record);
			}
			if(session.getAttribute(sessionName)!=null){
				if(code==null||(!code.equals(session.getAttribute(sessionName).toString()))){
					throw new CustomAuthenticationException("authen.wrong.code");
				}
			}
		}catch (Exception e){
			String key = e.getMessage();
			if(e instanceof AuthenticationException){
				key = key.replaceAll("\\s", ".");
				key = PropertiesUtil.getProperty("exceptionResource", key,true);
			}
			request.getSession().setAttribute("login_error", key);
			request.getRequestDispatcher(this.getAuthenticationFailureUrl());
			throw new CustomAuthenticationException(key);
		}
		return authentication;
	}

	public ServiceFacade getServiceFacade() {
		return serviceFacade;
	}

	public void setServiceFacade(ServiceFacade serviceFacade) {
		this.serviceFacade = serviceFacade;
	}
}
