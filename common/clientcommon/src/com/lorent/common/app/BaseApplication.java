package com.lorent.common.app;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lorent.common.manager.ConfigManager;
import com.lorent.common.manager.DataManager;
import com.lorent.common.manager.ExecuteManager;
import com.lorent.common.manager.FilterManager;
import com.lorent.common.manager.MessageManager;
import com.lorent.common.manager.ViewManager;
import com.lorent.common.permission.NoPermissionException;
import com.lorent.common.permission.PermissionUtil;
import com.lorent.common.util.PlatformUtil;

public abstract class BaseApplication {
	public Logger log = Logger.getLogger(BaseApplication.class);
	
	public void execute(){
		try{
			apps.put(this.getClass(), this);
			init();
			beforeStart();
			startApp();
		}catch(Exception e){
			log.error("BaseApplication.execute", e);
		}
	}
	
	private static Map<Class, BaseApplication> apps = new HashMap<Class, BaseApplication>();
	public static BaseApplication getInstance(Class clazz){
		return apps.get(clazz);
	}
	public static AppContext getContext(Class clazz){
		return getInstance(clazz).getContext();
	}
	

	protected void beforeStart()throws Exception{
	}
	
	protected void init() throws Exception{
		if(context == null){
			context = new BaseContext();
		}
		//init context(first)
		String[] paths = {getContextPath(), "classpath:com/lorent/common/config/applicationContext-base.xml"};
        context.setSpringContext(new FileSystemXmlApplicationContext(paths));
        //init filterManager
        FilterManager filterManager = new FilterManager();
        filterManager.init(getFilterPath());
        filterManager.setContext(context);
        context.setFilterManager(filterManager);
        //init ExecuteManager
        ExecuteManager executeManager = new ExecuteManager();
        executeManager.setContext(context);
        context.setExecuteManager(executeManager);
		//init viewManager
        ViewManager viewManager = new ViewManager();
        viewManager.init(getI18nPath());
        viewManager.setContext(context);
		context.setViewManager(viewManager);
		//init configManager
		ConfigManager configManager = new ConfigManager(getConfigFilePath());
		context.setConfigManager(configManager);
		//init dataManager
		DataManager dataManager = new DataManager();
		context.setDataManager(dataManager);
		//init messageManager(last)
        MessageManager messageManager = new MessageManager();
		messageManager.init(getListenerPath());
		messageManager.setContext(context);
		context.setMessageManager(messageManager);

		PermissionUtil permissionUtil = new PermissionUtil(getPermissionFilePath());
		context.setPermissionUtil(permissionUtil);
	}
	
	protected abstract void startApp() throws Exception;	
	protected abstract String getListenerPath();
	protected abstract String getFilterPath();
	protected abstract String getContextPath();
	protected abstract String getI18nPath();
	protected abstract String getConfigFilePath();
	protected abstract String getPermissionFilePath();
	
	protected AppContext context;
	
	public AppContext getContext() {
		return context;
	}
	
	public void setContext(AppContext context){
		this.context = context;
	}
	
	public void docheckPermission(String id,Object... paras) throws NoPermissionException{
		getContext().getPermissionUtil().checkPermission(id, paras);
	}
}
