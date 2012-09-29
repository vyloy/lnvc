package com.lorent.web.action;

import java.util.Date;
import java.util.Locale;
import com.opensymphony.xwork2.ActionContext;

public class ToPageAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date now;
	private Locale locale;
	public String execute() throws Exception {
		setNow(new Date());
		setLocale(ActionContext.getContext().getLocale());
		return SUCCESS;
	}
	public Date getNow() {
		return now;
	}
	public void setNow(Date now) {
		this.now = now;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public Object getModel() {
		return null;
	}
	
}
