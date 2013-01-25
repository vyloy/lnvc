package com.lorent.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.lorent.common.util.PasswordUtil;
import com.lorent.model.UserBean;
import com.lorent.util.PropertiesUtil;

public class RegisterUserAction  extends BaseAction {
	
	public String activeRegisterUser() throws Exception{
		String idstr = getPara("id").trim();
		if (idstr == null) {
			return ERROR;
		}
		String psw = PropertiesUtil.getConstant("reg.user.psw");
		idstr = PasswordUtil.baseDecryptString(PasswordUtil.getDesString(idstr), psw);
		int id = Integer.parseInt(idstr);
		UserBean userBean = serviceFacade.getUserService().get(id);
		if (userBean != null) {
			if (userBean.getUserEnabled() == false) {
				userBean.setUserEnabled(true);		
				serviceFacade.getUserService().renewUser(userBean);
			}
		}
		return SUCCESS;
	}

	@Override
	public Object getModel() {
		return null;
	}


}
