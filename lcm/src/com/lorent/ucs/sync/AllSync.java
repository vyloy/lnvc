package com.lorent.ucs.sync;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import qflag.ucstar.webservice.bean.UcstarDept;
import qflag.ucstar.webservice.bean.UcstarUser;

import com.lorent.exception.CustomSqlException;
import com.lorent.service.UserService;
import com.lorent.service.impl.ServiceFacade;

public class AllSync extends DeleteSync {
	private Logger logger = Logger.getLogger(AllSync.class);

	public AllSync(ServiceFacade serviceFacade) {
		super(serviceFacade);
	}

	@Override
	public void run() {
		try {
			UserService userService = serviceFacade.getUserService();
			LinkedList<UcstarDept> depts=new LinkedList<UcstarDept>();
			findSubDepts(depts, "0");
			depts.add(ucstarwebservice.getDepartInfo("0"));
			
			for(UcstarDept d:depts){
				UcstarUser[] userList = ucstarwebservice.getUserList(d.getDeptid());
				if(userList==null||userList.length==0)
					continue;
				Object[] users=new Object[userList.length];
				for (int i=0;i<userList.length;i++) {
					UcstarUser u=userList[i];
					String[] info=new String[4];
					u = ucstarwebservice.getUser(u.getUsername());
					info[0]=u.getUsername();
					info[1]=u.getName();
					info[2]=u.getVoipphone();
					info[3]=u.getVoipphone();
					//info[3]=u.getVoipphonePass();
					users[i]=info;
				}
				userService.createOrUpdateUCSUser(users);
			}
		} catch (CustomSqlException e) {
			logger.error("执行Sql出错", e);
		} catch (RemoteException e) {
			logger.error("远程操作出错", e);
		} catch (Exception e) {
			logger.error("未知错误", e);
		}

	}
	
	private void findSubDepts(List<UcstarDept> result,String id) throws RemoteException{
		UcstarDept[] deptList = ucstarwebservice.getDeptList(id);
		if(deptList==null||deptList.length==0)
			return;
		result.addAll(Arrays.asList(deptList));
		for(UcstarDept d:deptList){
			findSubDepts(result, d.getDeptid());
		}
	}
}
