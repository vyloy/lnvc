package com.lorent.web.xmlrpc.handler;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lorent.common.util.PasswordUtil;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.CustomerBean;
import com.lorent.model.DepartmentBean;
import com.lorent.model.SipConfBean;
import com.lorent.model.UserBean;
import com.lorent.util.Constant;
import com.lorent.util.MD5Builder;
import com.lorent.util.MailUtil;
import com.lorent.util.PropertiesUtil;

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
	
	public boolean addOrUpdateUCSUser(Object[] users)throws Exception{
		if(users == null || users.length == 0){
			return false;
		}
		serviceFacade.getUserService().createOrUpdateUCSUser(users);
		return true;
	}
	
	public boolean userIsValid(String lccno) throws Exception{
		UserBean byLccAccount = serviceFacade.getUserService().getByLccAccount(lccno);
		if (byLccAccount == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * @param paraters
	 * paraters[0]:username 登录名 ，必填
	 * paraters[1]:password 密码，必填
	 * paraters[2]:realname 真实姓名，必填
	 * paraters[3]:email 电子邮件地址，必填
	 * paraters[4]:phone 固定电话
	 * paraters[5]:gender 性别 male/female
	 * paraters[6]:mobile 移动电话
	 * paraters[7]:department 所属部门
	 * paraters[8]:position 职位
	 * paraters[9]:code 员工编号
	 * paraters[10]:lcc_account 指定lcc号码
	 * paraters[11]:serverip lcm服务器地址，必填
	 * @return
	 * @throws Exception
	 */
	public boolean registerUser(Object[] paraters) throws Exception{
		String username = (String) paraters[0];
		final String password = (String) paraters[1];
		String realname = (String) paraters[2];
		final String email = (String) paraters[3];
		String phone = (String) paraters[4];
		String gender = (String) paraters[5];
		String mobile = (String) paraters[6];
		String department_id = (String) paraters[7];
		
		
		String position = (String) paraters[8];
		String code = (String) paraters[9];
		String lcc_account = (String) paraters[10];
		String lcm_serverip = (String) paraters[11];
		
		UserBean userBean = new UserBean();
		userBean.setUserEnabled(false);
		userBean.setUsername(username);
		userBean.setNewPassword(password);
		userBean.setRepeatPassword(password);
		userBean.setPassword(password);//MD5Builder.getMD5(userBean.getNewPassword().trim(),userBean.getUsername().trim())
		userBean.setMd5passwd(MD5Builder.getMD5(userBean.getNewPassword().trim()));
		userBean.setUserCredentialsNonExpired(true);
		userBean.setRealName(realname);
		userBean.setMobile(mobile);
		userBean.setPhone(phone);
		userBean.setGender(gender);
		userBean.setEmail(email);
		CustomerBean firstValidCustomer = serviceFacade.getCustomerService().getFirstValidCustomer();
		List<DepartmentBean> deptByCustomer = serviceFacade.getDepartmentService().getDeptByCustomer(firstValidCustomer.getId());
		if (department_id == null || department_id.equals("")) {
			userBean.setDepartment(deptByCustomer.get(0));
		}
		else{
			try {
				int parseInt = Integer.parseInt(department_id);
				DepartmentBean departmentBean = serviceFacade.getDepartmentService().get(parseInt);
				userBean.setDepartment(departmentBean);
			} catch (Exception e) {
				userBean.setDepartment(deptByCustomer.get(0));
			}
		}
		userBean.setPosition(position);
		userBean.setCode(code);
		userBean.setLccAccount(lcc_account);
		userBean.setCustomer(serviceFacade.getCustomerService().getFirstValidCustomer());
//		userBean.setDepartment(serviceFacade.getDepartmentService().get(userBean.getDepartment().getId()));
		userBean.setUserType(Constant.USER_TYPE_INNERUSER);
		serviceFacade.getUserService().createUser(userBean);
		
		
		//发送邮件至邮箱
		String sgender = "";
		if (gender.equals("male")) {
			sgender = "男";
		}
		else{
			sgender = "女";
		}
		String psw = PropertiesUtil.getConstant("reg.user.psw", "lorent1234");
		
		String activeStr = PasswordUtil.getEncString(PasswordUtil.baseEncryptString(userBean.getId().toString(), psw));
		String http_link = "http://"+lcm_serverip+":6090/lcm/app/registerUserAction_activeRegisterUser_result.action?id="+activeStr;
		String content = PropertiesUtil.getProperty("messageResource", "page.mail.conference.registeruser.context",true);
		content = MessageFormat.format(content, new String[]{userBean.getLccAccount(),http_link,username,realname,email,password,sgender,mobile,phone});
		MailUtil.sendEmail(new String[]{email}, "帐号激活", content);
		
		return true;
	}
	
}
