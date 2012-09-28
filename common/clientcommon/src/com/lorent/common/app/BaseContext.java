package com.lorent.common.app;

import org.springframework.context.ApplicationContext;

import com.lorent.common.manager.ConfigManager;
import com.lorent.common.manager.DataManager;
import com.lorent.common.manager.ExecuteManager;
import com.lorent.common.manager.FilterManager;
import com.lorent.common.manager.MessageManager;
import com.lorent.common.manager.ViewManager;
import com.lorent.common.permission.PermissionUtil;

public class BaseContext implements AppContext {
	private ApplicationContext springContext;
	private FilterManager filterManager;
	private MessageManager messageManager;
	private ViewManager viewManager;
	private ExecuteManager executeManager;
	private ConfigManager configManager;
	private DataManager dataManager;
	private PermissionUtil permissionUtil;
	
	
	public PermissionUtil getPermissionUtil() {
		return permissionUtil;
	}
	public void setPermissionUtil(PermissionUtil permissionUtil) {
		this.permissionUtil = permissionUtil;
	}
	public ConfigManager getConfigManager() {
		return configManager;
	}
	public void setConfigManager(ConfigManager configManager) {
		this.configManager = configManager;
	}
	public DataManager getDataManager() {
		return dataManager;
	}
	public void setDataManager(DataManager dataManager) {
		this.dataManager = dataManager;
	}
	public ExecuteManager getExecuteManager() {
		return executeManager;
	}
	public void setExecuteManager(ExecuteManager executeManager) {
		this.executeManager = executeManager;
	}
	public ApplicationContext getSpringContext() {
		return springContext;
	}
	public void setSpringContext(ApplicationContext springContext) {
		this.springContext = springContext;
	}
	public FilterManager getFilterManager() {
		return filterManager;
	}
	public void setFilterManager(FilterManager filterManager) {
		this.filterManager = filterManager;
	}
	public MessageManager getMessageManager() {
		return messageManager;
	}
	public void setMessageManager(MessageManager messageManager) {
		this.messageManager = messageManager;
	}
	public ViewManager getViewManager() {
		return viewManager;
	}
	public void setViewManager(ViewManager viewManager) {
		this.viewManager = viewManager;
	}

}
