package com.lorent.web.util;

import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoaderListener;

import com.lorent.util.PropertiesUtil;

public class CustomContextLoaderListener extends ContextLoaderListener {

	private static final Logger log = Logger.getLogger(CustomContextLoaderListener.class); 
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		//输出版本信息
		log.info("=======================LCM VERSION " + PropertiesUtil.getConstant("lcm.version") + "=======================");
		super.contextInitialized(event);
	}
	
	@Override
	protected void customizeContext(ServletContext servletContext,
			ConfigurableWebApplicationContext applicationContext) {
		String[] configLocations = applicationContext.getConfigLocations();
		log.info("spring config files:"+Arrays.asList(configLocations));
		if(configLocations==null||configLocations.length==0)
			return;
		String load = PropertiesUtil.getConstant("LOAD_MONITOR_DATA");
		log.info("LOAD_MONITOR_DATA:"+load);
    	if("true".equalsIgnoreCase(load)){
    		String[] configs= new String[configLocations.length+1];
    		System.arraycopy(configLocations, 0, configs, 0, configLocations.length);
    		configs[configs.length-1]="/WEB-INF/monitor-data-dao.xml";
    		log.info("spring config files:"+Arrays.asList(configs));
    		applicationContext.setConfigLocations(configs);
    	}
	}

}
