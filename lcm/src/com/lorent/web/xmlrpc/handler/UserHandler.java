package com.lorent.web.xmlrpc.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lorent.exception.CustomSqlException;
import com.lorent.model.DepartmentBean;
import com.lorent.model.SipConfBean;
import com.lorent.model.UserBean;

public class UserHandler extends BaseHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public synchronized String rpcLogin(int type,String ip,String pass)throws Exception{
		switch (type) {
		case 1:
			return serviceFacade.getMcuServerService().mcuRpcValidate(ip, pass);
		default:
			return null;
		}
	}
	
	public Integer getLoginUser(String username, String passwd)throws Exception{
		UserBean user = serviceFacade.getUserService().getLoginUser(username, passwd);
		return user.getId();
	}
	
	public String getLoginUserAccount(String username, String passwd)throws Exception{
		UserBean user = serviceFacade.getUserService().getLoginUser(username, passwd);
		return user.getLccAccount();
	}
	
	public Object[] getConfUser(Integer userId)throws Exception{
		UserBean me = serviceFacade.getUserService().get(userId);
		List<UserBean> userList = serviceFacade.getUserService().getConfUser(me, null);
		List list = new ArrayList();
		for(UserBean user : userList){
			Object[] temp = new Object[2];
			temp[0] = user.getId();
			temp[1] = user.getUsername();
			list.add(temp);
		}
		return list.toArray();
	}
	
	/**
	 * 获取所有部门
	 * @param param
	 */
	public List<String[]> getAllDepartments(String param){
		List<DepartmentBean> departMentBeanList=null;
        List<String[]> deptList=new ArrayList<String[]>();
		String[] dept=null;
		try {
			departMentBeanList=serviceFacade.getDepartmentService().getAll();
			if(null!=departMentBeanList)
			for(int i=0;i<departMentBeanList.size();i++){
				dept=new String[2];
				dept[0]=departMentBeanList.get(i).getId().toString();//部门id
				dept[1]=departMentBeanList.get(i).getDepartmentName();//部门名称
				deptList.add(dept);
			}
		} catch (CustomSqlException e) {
			e.printStackTrace();
		}
		return deptList;
	}
	/**
	 * 获取所有用户
	 * @param param 普通用户 isuser =0  监控用户isuser=1
	 */
	public List<String[]> getAllUsers(int param){
		List<SipConfBean> sipConfBeanList=null;
		SipConfBean sipConfBean=new SipConfBean();
		sipConfBean.setIsuser(param);
		List<String[]> userList=new ArrayList<String[]>();
		String[] user=null;
		try {
			sipConfBeanList=serviceFacade.getSipConfService().getByExample(sipConfBean);
			if(null!=sipConfBeanList)
			for(int i=0;i<sipConfBeanList.size();i++){
				user=new String[5];
				user[0]=sipConfBeanList.get(i).getId().toString();//userid
				user[1]=sipConfBeanList.get(i).getName();//用户名
				user[2]=sipConfBeanList.get(i).getMonitorname();//监控用户名
				user[3]=sipConfBeanList.get(i).getUsername();
				user[4]=sipConfBeanList.get(i).getDepartment_id()==null?null:sipConfBeanList.get(i).getDepartment_id().toString();//部门id
				userList.add(user);
			}
		} catch (CustomSqlException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	public boolean createUser(Map map)throws Exception{
		return true;
	}
	
	public boolean modifyUser(Map map)throws Exception{
		return true;
	}
	
	public boolean deleteUser(int userid)throws Exception{
		return true;
	}
	
	public boolean searchUser()throws Exception{
		return true;
	}
	
	
}
