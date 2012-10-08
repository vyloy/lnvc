package com.lorent.ucs.sync;

import java.rmi.RemoteException;
import java.util.List;

import org.apache.log4j.Logger;

import qflag.ucstar.webservice.bean.UcstarUser;

import com.lorent.exception.CustomSqlException;
import com.lorent.model.UserBean;
import com.lorent.service.UserService;
import com.lorent.service.impl.ServiceFacade;

public class DeleteSync extends WebserviceManager implements Runnable{

	private Logger logger = Logger.getLogger(DeleteSync.class);
	protected ServiceFacade serviceFacade;
	
	public DeleteSync(ServiceFacade serviceFacade) {
		this.serviceFacade = serviceFacade;
	}

	@Override
	public void run() {
		try {
			UserService userService = serviceFacade.getUserService();
			List<UserBean> users = userService.getAll();
			for (UserBean userBean : users) {
				String username = userBean.getUsername();
				UcstarUser user = ucstarwebservice.getUser(username);
				if(user==null){
					userService.deleteUCSUser(userBean);
				}
			}
			
		} catch (CustomSqlException e) {
			logger.error("执行Sql出错",e);
		} catch (RemoteException e) {
			logger.error("远程操作出错",e);
		} catch (Exception e) {
			logger.error("未知错误",e);
		}
		
		
	}
}
