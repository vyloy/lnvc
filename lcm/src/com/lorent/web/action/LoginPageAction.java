package com.lorent.web.action;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.lorent.model.UserBean;
import com.lorent.util.ThreadLocaleUtil;
import com.opensymphony.xwork2.ActionContext;

public class LoginPageAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String j_username;
	private String j_password;
	private String code;
	private String requestLocale;
	
	public String toLoginPage() throws Exception {
		if(ServletActionContext.getRequest().getSession().getAttribute("WW_TRANS_I18N_LOCALE")==null){
			ActionContext.getContext().setLocale(ThreadLocaleUtil.getLocale());
			HttpServletRequest request = ServletActionContext.getRequest();
			request.getSession().setAttribute("WW_TRANS_I18N_LOCALE", ThreadLocaleUtil.getLocale());
			if(request.getParameter("error")!=null){
				request.getSession().setAttribute("login_error", getText("login."+request.getParameter("error")));
			}
		}
		return SUCCESS;	
	}
	/**
	 * change application language
	 * @return
	 */
	public String changeLanguage()throws Exception {
		if(getRequestLocale()==null)return SUCCESS;
		String[]localeStrs = getRequestLocale().split("_");
		Locale locale = new Locale(localeStrs[0],localeStrs[1]);
		ThreadLocaleUtil.setLocale(locale);
		ActionContext.getContext().setLocale(ThreadLocaleUtil.getLocale());
		ServletActionContext.getRequest().getSession().setAttribute("WW_TRANS_I18N_LOCALE", ThreadLocaleUtil.getLocale());
		return LOGIN;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getJ_password() {
		return j_password;
	}
	public void setJ_password(String j_password) {
		this.j_password = j_password;
	}
	public String getJ_username() {
		return j_username;
	}
	public void setJ_username(String j_username) {
		this.j_username = j_username;
	}
	public String getRequestLocale() {
		return requestLocale;
	}
	public void setRequestLocale(String requestLocale) {
		this.requestLocale = requestLocale;
	}
	@Override
	public String getErrorMsg() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getSuccessMsg() {
		// TODO Auto-generated method stub
		return null;
	}
	public Object getModel() {
		return null;
	}
}
