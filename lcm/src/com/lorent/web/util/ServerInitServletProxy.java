package com.lorent.web.util;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lorent.trigger.QuartzTrigger;

/**
 * 会议发起触发器Servlet代理类，用于在web.xml中配置
 * @author gary
 *
 */
public class ServerInitServletProxy extends GenericServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Servlet proxy;
	private String tagetBean;
	private static ApplicationContext context;
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		this.proxy.service(request, response);
	}
	
	private void getServletBean(){
//		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		setContext(getServletContext());
		this.proxy = (Servlet)getContext().getBean(this.tagetBean);
	}
	@Override
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		this.tagetBean = config.getInitParameter("targetBean");
		getServletBean();
		proxy.init(getServletConfig());
	}

	@Override
	public void destroy(){
		super.destroy();
		try {
			QuartzTrigger.SCHEDULER.shutdown();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	public static ApplicationContext getContext() {
		return context;
	}
	
	private static void setContext(ServletContext servletContext){
		if(context==null)
			context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
	}
	
}
