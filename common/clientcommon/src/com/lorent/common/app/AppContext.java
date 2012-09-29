package com.lorent.common.app;

import org.springframework.context.ApplicationContext;

import com.lorent.common.manager.ConfigManager;
import com.lorent.common.manager.DataManager;
import com.lorent.common.manager.ExecuteManager;
import com.lorent.common.manager.FilterManager;
import com.lorent.common.manager.MessageManager;
import com.lorent.common.manager.ViewManager;
import com.lorent.common.permission.PermissionUtil;

public interface AppContext {

	public abstract ConfigManager getConfigManager();

	public abstract void setConfigManager(ConfigManager configManager);

	public abstract DataManager getDataManager();

	public abstract void setDataManager(DataManager dataManager);

	public abstract ExecuteManager getExecuteManager();

	public abstract void setExecuteManager(ExecuteManager executeManager);

	public abstract ApplicationContext getSpringContext();

	public abstract void setSpringContext(ApplicationContext springContext);

	public abstract FilterManager getFilterManager();

	public abstract void setFilterManager(FilterManager filterManager);

	public abstract MessageManager getMessageManager();

	public abstract void setMessageManager(MessageManager messageManager);

	public abstract ViewManager getViewManager();

	public abstract void setViewManager(ViewManager viewManager);

	void setPermissionUtil(PermissionUtil util);
	
	PermissionUtil getPermissionUtil();
}