package com.lorent.web.action;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.lorent.dto.ImportUserConfig;
import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.CustomerBean;
import com.lorent.model.DepartmentBean;
import com.lorent.model.RoleBean;
import com.lorent.model.UserBean;
import com.lorent.util.ClassUtil;
import com.lorent.util.Constant;
import com.lorent.util.MD5Builder;
import com.lorent.util.PageUtil;
import com.lorent.util.PropertiesUtil;
import com.lorent.util.ThreadLocaleUtil;

public class UserAction extends BaseAction<UserBean,Integer>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String oldPassword;
	private String newPassword;
	private String rePass;
	private int hideValue;
	private UserBean user;
	private List<CustomerBean>customerList; 
	private UserBean searchModel;
	private Integer searchLocked;
	private Integer searchExpired;
	private List<RoleBean>roleList;
	private String roleCode;
	private Map<Integer, String>userTypeList;
	private Integer locked;
	private Integer expired;
	private List<DepartmentBean>departmentList;
	private static final Logger log = Logger.getLogger(UserAction.class); 
	private int mode;
	private boolean changeSelf = false; 
	private ImportUserConfig importUserConfig;
	
	/**
	 * 跳转到内部用户编辑页面，系统管理员使用
	 * @return
	 */
	public String toInnerUserList() {
		user = new UserBean();
		user.setUserType(Constant.USER_TYPE_INNERUSER);
		user.setStatus(Constant.RECORD_STATUS_VALID);
		Map<String, Criterion> map = getAdminUserCondition();
		DetachedCriteria criteria = ClassUtil.createCriteriaByMap(map, UserBean.class);
		setSubPageMap(criteria,serviceFacade.getUserService(), orderString);
//		setCustomerMode(false);
		mode = 1;
		init();
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/userAction_toInnerUserList_user_list.action");
		return SUCCESS;
	}
	
	private Map<String, Criterion> getAdminUserCondition(){
		Map<String, Criterion> map = new HashMap<String, Criterion>();
		
		map.put("status", Restrictions.eq("status", Constant.RECORD_STATUS_VALID));
		map.put("roles.roleCode", Restrictions.eq("roles.roleCode", Constant.ROLE_CUSTOMER_ADMIN));
		map.put("id", Restrictions.not(Restrictions.eq("id", ThreadLocaleUtil.getUser().getId())));
		return map;
	}
	
	/**
	 * 跳转到客户内部用户界面，客户管理员使用
	 * @return
	 */
	public String toCustomerUserList() {
		log.info("com.lorent.web.action.UserAction toCustomerUserList() -- 跳转到客户内部用户界面，客户管理员使用");
//		buttonMap.put("importUser", true);
		user = new UserBean();
		user.setUserType(Constant.USER_TYPE_INNERUSER);
		user.setStatus(Constant.RECORD_STATUS_VALID);
//		DetachedCriteria criteria = DetachedCriteria.forClass(UserBean.class);
//		criteria.createAlias("customer", "customer")
//			.add(Restrictions.eq("customer.id", ThreadLocaleUtil.getUser().getCustomer().getId()))
//			.add(Restrictions.eq("status", Constant.RECORD_STATUS_VALID))
//			.add(Restrictions.not(Restrictions.eq("id", ThreadLocaleUtil.getUser().getId())));
		Map<String, Criterion> map = getCustomerUserCondition();
		DetachedCriteria criteria = ClassUtil.createCriteriaByMap(map, UserBean.class);
		setSubPageMap(criteria,serviceFacade.getUserService(), orderString);
//		setCustomerMode(true);
		mode = 2;
		init();
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/userAction_toCustomerUserList_user_list.action");
		return SUCCESS;
	}
	
	//获取客户内部用户初始条件
	private Map<String, Criterion> getCustomerUserCondition(){
		log.info("com.lorent.web.action.UserAction getCustomerUserCondition() -- 跳转到客户内部用户界面，客户管理员使用");
		Map<String, Criterion> map = new HashMap<String, Criterion>();
		
		map.put("customer.id", Restrictions.eq("customer.id", ThreadLocaleUtil.getUser().getCustomer().getId()));
		map.put("status", Restrictions.eq("status", Constant.RECORD_STATUS_VALID));
		map.put("id", Restrictions.not(Restrictions.eq("id", ThreadLocaleUtil.getUser().getId())));
		
		return map;
	}
	
	/**
	 * 跳转到会员用户列表
	 * @return
	 */
	public String toMemberUserList() {
		log.info("com.lorent.web.action.UserAction toMemberUserList() -- 跳转到会员用户列表");
		user = new UserBean();
		user.setUserType(Constant.USER_TYPE_MEMBER_USER);
		setSubPageMap(user, serviceFacade.getUserService(), orderString);
		mode = 3;
		init();
		return SUCCESS;
	}
	
	public void init(){
		searchLocked = null;
		searchExpired = null;
		searchMode = false;
	}
	
	/**
	 * 跳转至用户信息编辑页面
	 * @return
	 */
	public String toEditUser()throws Exception {
		log.info("com.lorent.web.action.UserAction toEditUser() -- 跳转至用户信息编辑页面");
		setUser(serviceFacade.getUserService().get(ThreadLocaleUtil.getUser().getId()));
		changeSelf = true;
		user.setRepeatPassword("password");
		user.setNewPassword("password");
		return SUCCESS;
	}
	/**
	 * 跳转到用户信息编辑界面
	 * @return
	 * @throws Exception
	 */
	public String toEditUserPage()throws Exception {
		log.info("com.lorent.web.action.UserAction toEditUserPage() -- 跳转到用户信息编辑界面");
		HttpServletRequest request = ServletActionContext.getRequest();
		String[]ids = request.getParameterValues("choose");
		changeSelf = false;
		if(ids==null||ids.length==0){
			setErrorMsg(getText("page.errormsg.chooserequired"));
		} else{
			setUser(serviceFacade.getUserService().get(Integer.parseInt(ids[0])));
		}
		Hibernate.initialize(user.getDepartment());
		Hibernate.initialize(user.getRoles());
		Hibernate.initialize(user.getCustomer());
		initRoles();
		initCustomerList();
		initDepartmentList();
		locked = user.getUserAccountNonLocked()?2:1;
		expired = user.getUserAccountNonExpired()?2:1;
		Set<RoleBean>roles = user.getRoles();
		//设置roleCode
		if(roles.contains(new RoleBean(Constant.ROLE_ADMIN))){
			roleCode = Constant.ROLE_ADMIN;
		} else if (roles.contains(new RoleBean(Constant.ROLE_CUSTOMER_ADMIN))) {
			roleCode = Constant.ROLE_CUSTOMER_ADMIN;
		} else if (roles.contains(new RoleBean(Constant.ROLE_CUSTOMER_DEP_ADMIN))) {
			roleCode = Constant.ROLE_CUSTOMER_DEP_ADMIN;
		} else {
			roleCode = Constant.ROLE_CUSTOMER_USER;
		}
		user.setRepeatPassword("password");
		user.setNewPassword("password");
		return SUCCESS;
	}
	/**
	 * 响应添加用户操作
	 * @return
	 */
	public String toAddUser()throws Exception {
		log.info("com.lorent.web.action.UserAction toAddUser() -- 响应添加用户操作");
		UserBean loginUser = ThreadLocaleUtil.getUser();
		CustomerBean customer = loginUser.getCustomer();
		user = new UserBean();
		//admin 增加用户时也要执行这个用户数量检查的限制
		if(!loginUser.getUserType().equals(Constant.USER_TYPE_ADMIN)){
			DetachedCriteria criteria = DetachedCriteria.forClass(UserBean.class);
			criteria.createAlias("customer", "customer")
				.add(Restrictions.eq("customer.id",customer.getId()))
				.add(Restrictions.eq("status",Constant.RECORD_STATUS_VALID));
			if(serviceFacade.getUserService().getRecordCount(criteria)>=customer.getCustomerUserlimit()){
				initCallBackUrl();	
				log.error("",new ArgsException(PropertiesUtil.getProperty("exceptionResource_zh_CN", "args.customeroveruserlimit")+":" + customer.getCustomerUserlimit()));
//				throw new ArgsException("{args.customeroveruserlimit}:" + customer.getCustomerUserlimit());
				throw new ArgsException(PropertiesUtil.getProperty("exceptionResource_zh_CN", "args.customeroveruserlimit")+":" + customer.getCustomerUserlimit());
			}
			user.setCustomer(customer);
			user.setUserType(Constant.USER_TYPE_INNERUSER);
		}
		initRoles();
		initCustomerList();
		initDepartmentList();
		changeSelf = false;
		roleCode = "";
		return SUCCESS;
	}
	
	public String sortUser(){
		setSortField();
		if(searchMode){
			return searchUser();
		}
		switch(mode){
			case 1: 
				return toInnerUserList();
			case 2: 
				return toCustomerUserList();
			case 3: 
				return toMemberUserList();
		}
		return SUCCESS;
		
	}
	
	/**
	 * 将修改后的用户信息持久化
	 * @return
	 */
	public String doEditUser()throws Exception{
		String msg = "";
		try {
			//如果hideValue！=0，则为编辑用户时进行的修改密码操作，调用方法进行检验
			if (hideValue != 0) {
				serviceFacade.getUserService().changePassword(user,
						oldPassword, newPassword, rePass);
				hideValue = 0;
			}
			
			if(user.getId()==null){
				msg = "新增用户";
				log.info(msg);
				// -- start modify by chard.wu 2011年11月28日10:31:59 增加用户确定时检查客户用户数量是否超过了限定的用户数量
				DetachedCriteria criteria = DetachedCriteria.forClass(UserBean.class);
				criteria.createAlias("customer", "customer")
					.add(Restrictions.eq("customer.id",user.getCustomer().getId()))
					.add(Restrictions.eq("status",Constant.RECORD_STATUS_VALID));
				if(serviceFacade.getUserService().getRecordCount(criteria)>=user.getCustomer().getCustomerUserlimit()){
					initCallBackUrl();				
					throw new ArgsException(PropertiesUtil.getProperty("exceptionResource_zh_CN", "args.customeroveruserlimit")+":" + user.getCustomer().getCustomerUserlimit());
				}
				// -- end 
			}else{
				msg = "修改用户" + user.getId();
				log.info(msg);
			}
			log.info("用户登录名:" + user.getLccAccount());
			log.info("真实姓名:" + user.getRealName());
			log.info("所属客户:" + user.getUserType());
			//如果是添加用户则进行下面的操作
			if(user.getId()==null){
				user.setPassword(MD5Builder.getMD5(user.getNewPassword().trim(),user.getUsername().trim()));
				user.setMd5passwd(MD5Builder.getMD5(user.getNewPassword().trim()));
				user.setUserCredentialsNonExpired(true);
				user.setUserEnabled(true);
			}
			if(roleCode!=null&&!roleCode.equals("")){
				if(roleCode.equals(Constant.ROLE_CUSTOMER_ADMIN)){
					user.setDepartment(null);
				}
				if(roleCode.equals(Constant.ROLE_MEMBER_USER))
					user.setUserType(Constant.USER_TYPE_MEMBER_USER);
				else
					user.setUserType(Constant.USER_TYPE_INNERUSER);
				RoleBean role = new RoleBean();
				if(user.getCustomer()!=null){
					role.setCustomerId(user.getCustomer().getId());

				}
				else role.setCustomerId(0);
				role.setRoleCode(roleCode);
				role = serviceFacade.getRoleService().getByExample(role).get(0);
				user.getRoles().clear();
				user.getRoles().add(role);
//				role = new RoleBean();
//				role.setRoleCode(Constant.ROLE_CUSTOMER_USER);
//				user.getRoles().add(serviceFacade.getRoleService().getByExample(role).get(0));
				roleCode = "";
				user.setUserAccountNonExpired(expired==1?false:true);
				user.setUserAccountNonLocked(locked==1?false:true);

				log.info("用户角色:" + user.getRoles());
			}
			if(user.getDepartment()!=null && user.getDepartment().getId()==-1){
				user.setDepartment(null);
			}
			if(user.getId()==null){
				serviceFacade.getUserService().createUser(user);
			}else{
				serviceFacade.getUserService().renewUser(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("",e);
			throw e;
		}
		if(changeSelf){
			callBackUrl = "";
		}
		else {
			initCallBackUrl();
		}
		log.info(msg + "成功");
		return SUCCESS;
	}
	/**
	 * 验证用户输入
	 * @throws Exception
	 */
	public void validateDoEditUser(){
		log.info("com.lorent.web.action.UserAction validateDoEditUser() -- 验证用户输入");
		try {
			CustomerBean customer = serviceFacade.getCustomerService().get(user.getCustomer().getId());
			UserBean loginUser = ThreadLocaleUtil.getUser();
			user.setCustomer(customer);
//			if(!loginUser.getUserType().equals(Constant.USER_TYPE_ADMIN)){
				if(user.getId()==null){
					DetachedCriteria criteria = DetachedCriteria.forClass(UserBean.class);
					criteria.createAlias("customer", "customer")
						.add(Restrictions.eq("customer.id",customer.getId()))
						.add(Restrictions.eq("status", Constant.RECORD_STATUS_VALID));
					Integer count = serviceFacade.getUserService().getRecordCount(criteria); 
					if(count.intValue() >= customer.getCustomerUserlimit().intValue()){
						log.error("",new ArgsException("args.customeroveruserlimit"));
						throw new ArgsException("args.customeroveruserlimit");
					}
				}
				if(roleCode!=null&&!Constant.ROLE_CUSTOMER_ADMIN.equals(roleCode)&&user.getDepartment()!=null&&user.getDepartment().getId()==-1){//选择角色为非客户管理员，则应该选择部门
					log.error("",new ArgsException("args.nodept"));
					throw new ArgsException("args.nodept");
				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("",e);
			addFieldError("error", e.getMessage());
		}
	}
	/**
	 * 删除选定的用户信息
	 * @return
	 * @throws Exception
	 */
	public String deleteUser()throws Exception {
		log.info("com.lorent.web.action.UserAction deleteUser() -- 删除选定的用户信息");
//		HttpServletRequest request  = ServletActionContext.getRequest();
//		String[]ids = request.getParameterValues("choose");
//		if(ids==null||ids.length==0){
//			setErrorMsg(getText("page.errormsg.chooserequired"));
//		}else {
		Integer[]ids = getSelectedIds();
		log.info("删除内部用户：" + ids);
		if(ids==null)return switchListPage();
		List<UserBean> users = serviceFacade.getUserService().get(ids);
		serviceFacade.getUserService().removeUser(users);
//		}
		if(getSearchMode())return searchUser();
		return switchListPage();
	}
	/**
	 * 查找用户信息
	 * @return
	 */
	public String searchUser() {
		log.info("com.lorent.web.action.UserAction searchUser() -- 查找用户信息");
		if(!"".equals(getIsSearch())){
			searchModel = user;
			setIsSearch("");
			searchMode = true;
		}
		switch(mode){
			case 2:
				CustomerBean customer = new CustomerBean();
				customer.setCustomerCode(ThreadLocaleUtil.getUser().getCustomer().getCustomerCode());
				searchModel.setCustomer(customer);
			case 1:
				searchModel.setUserType(Constant.USER_TYPE_INNERUSER);
				break;
			case 3:
				searchModel.setUserType(Constant.USER_TYPE_MEMBER_USER);
				break;
		}
		if(searchLocked!=null&&searchLocked==2)searchModel.setUserAccountNonLocked(true);
		else if(searchLocked!=null&&searchLocked==1)searchModel.setUserAccountNonLocked(false);
		else searchModel.setUserAccountNonLocked(null);
		if(searchExpired!=null&&searchExpired==2)searchModel.setUserAccountNonExpired(true);
		else if (searchExpired!=null&&searchExpired==1)searchModel.setUserAccountNonExpired(false);
		else searchModel.setUserAccountNonExpired(null);
		Map<String, Criterion> map = null;
		if(mode==2){
			map = getCustomerUserCondition();
		}else if(mode==1){
			map = getAdminUserCondition();
		}
		ClassUtil.parseExampleToCriteriaMap(map, searchModel, null);
		DetachedCriteria criteria = ClassUtil.createCriteriaByMap(map, UserBean.class);
		setSubPageMap(criteria,serviceFacade.getUserService(), orderString);
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/userAction_searchUser_user_list.action");
		return SUCCESS;
	}
	/**
	 * 获取编辑成功后跳转的URL
	 * @return
	 */
	public String getCallBackUrl() {
		return callBackUrl;
	}
	/**
	 * 初始化跳转的URL
	 *
	 */
	private void initCallBackUrl() {
		if(callBackUrl==null||callBackUrl.equals("")){
			if(getSearchMode())
				callBackUrl = "app/userAction_searchUser_user_list.action";
			else {
				switch(mode){
					case 1:
						callBackUrl = "app/userAction_toInnerUserList_user_list.action";
						break;
					case 2:
						callBackUrl = "app/userAction_toCustomerUserList_user_list.action";
						break;
					case 3:
						callBackUrl = "app/userAction_toMemberUserList_user_list.action";
						break;
				}
			}
		}
	}
	/**
	 * 初始化角色列表
	 *
	 */
	public void initRoles() {
		RoleBean role = new RoleBean();
		UserBean loginUser = ThreadLocaleUtil.getUser();
		roleList = new ArrayList<RoleBean>();
		if(loginUser.getUserType().equals(Constant.USER_TYPE_ADMIN)){
			role.setCustomerId(Integer.parseInt(PropertiesUtil.getConstant("initdata.admin.customer.id")));
			role.setRoleCode(Constant.ROLE_CUSTOMER_ADMIN);
		}
		else {
			role.setCustomerId(loginUser.getCustomer().getId());
			role.setRoleCode(Constant.ROLE_CUSTOMER_ADMIN);
		}
		try {
			roleList = serviceFacade.getRoleService().getByExample(role);
		} catch (CustomSqlException e) {
			roleList = null;
			log.error("",e);
		}
		if (roleList==null)
			roleList = new ArrayList<RoleBean>(); 
	}
	/**
	 * 初始化客户列表
	 *
	 */
	public void initCustomerList() {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(CustomerBean.class);
			criteria.add(Restrictions.eq("status", Constant.RECORD_STATUS_VALID))
					.add(Restrictions.eq("customerStatus",Constant.RECORD_VALID))
					.add(Restrictions.not(Restrictions.eq("id", Integer.parseInt(PropertiesUtil.getConstant("initdata.admin.customer.id")))));
			customerList = serviceFacade.getCustomerService().getByCriteria(criteria);
		} catch (CustomSqlException e) {
			log.error("",e);
			customerList = new ArrayList<CustomerBean>();
		}
	}
	/**
	 * 初始化部门列表
	 * @throws CustomSqlException
	 */
	private void initDepartmentList()throws CustomSqlException{
		UserBean loginUser = ThreadLocaleUtil.getUser();
		if(!loginUser.getUserType().equals(Constant.USER_TYPE_ADMIN)){
			DepartmentBean example = new DepartmentBean();
			example.setStatus(Constant.RECORD_STATUS_VALID);
			example.setCustomerId(loginUser.getCustomer().getId());
			example.setIsRoot(false);
			departmentList = serviceFacade.getDepartmentService().getByExample(example);
		}
		if(departmentList==null)
			departmentList = new ArrayList<DepartmentBean>();
	}
	
	private String switchListPage() {
		switch(mode){
		case 1:
			return toInnerUserList();
		case 2:
			return toCustomerUserList();
		case 3:
			return toMemberUserList();
		default:
			return SUCCESS;
		
		}
	}
	
	public String changePassword(){
		try{
			String oldpasswd = URLDecoder.decode(getPara("oldpasswd"), "utf8");
			String newpasswd = URLDecoder.decode(getPara("newpasswd"), "utf8");
			String reptpasswd = URLDecoder.decode(getPara("reptpasswd"), "utf8");
			serviceFacade.getUserService().changePassword(user, oldpasswd, newpasswd, reptpasswd);
			log.info("change password");
			result="{success:\"success\"}";
		}catch(Exception e){
			log.error("changePassword", e);
			result = "{fail:\"" + e.getMessage() + "\"}";
		}
		return SUCCESS;
	}
	
	//=======================309导入用户功能begin========================
	public String toImportUser()throws Exception{
//		importUserConfig = serviceFacade.getUserService().getImportUserConfig();
		return SUCCESS;
	}
	
	public String importSuccess(){
		return SUCCESS;
	}
	
	public void validateDoImportUser(){
		try {
			if (importUserConfig.getPasswd().equals("")
					|| importUserConfig.getUsername().equals("")
					|| importUserConfig.getUrl().equals("")) {

				throw new ArgsException("importuser.cannotnull");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addFieldError("error", e.getMessage());
		}
	}

//	public String doImportUser()throws Exception{
//		initCallBackUrl();
//		serviceFacade.getUserService().doImportUser(importUserConfig, ThreadLocaleUtil.getUser().getCustomer());
//		return SUCCESS;
//	}
	//=======================309导入用户功能end========================
	
	/*===================================== getters and setters ======================================*/
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) { 	
		this.oldPassword = oldPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public int getHideValue() {
		return hideValue;
	}
	public void setHideValue(int hideValue) {
		this.hideValue = hideValue;
	}
	public String getSuccessMsg() {
		return successMsg;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public String getNewPassword() {
		return newPassword;
	}
	
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;			
	}
	public UserBean getModel() {
		return user;
	}

	public List<CustomerBean> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<CustomerBean> customerList) {
		this.customerList = customerList;
	}

	public UserBean getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(UserBean searchModel) {
		this.searchModel = searchModel;
	}
	public String getOrderString() {
		return orderString;
	}
	public void setOrderString(String orderString) {
		this.orderString = orderString;
	}
	public Integer getSearchExpired() {
		return searchExpired;
	}
	public void setSearchExpired(Integer searchExpired) {
		this.searchExpired = searchExpired;
	}
	public Integer getSearchLocked() {
		return searchLocked;
	}
	public void setSearchLocked(Integer searchLocked) {
		this.searchLocked = searchLocked;
	}
	public List<RoleBean> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<RoleBean> roleList) {
		this.roleList = roleList;
	}
	public Map<Integer, String> getUserTypeList() {
		if(userTypeList==null){
			userTypeList = new HashMap<Integer, String>();
			userTypeList.put(Constant.USER_TYPE_ADMIN, getText("page.usertype.admin"));
			userTypeList.put(Constant.USER_TYPE_INNERUSER, getText("page.usertype.inneruser"));
			userTypeList.put(Constant.USER_TYPE_MEMBER_USER, getText("page.usertype.memberuser"));
		}
		return userTypeList;
	}
	public void setUserTypeList(Map<Integer, String> userTypeList) {
		this.userTypeList = userTypeList;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public Integer getExpired() {
		return expired;
	}
	public void setExpired(Integer expired) {
		this.expired = expired;
	}
	public Integer getLocked() {
		return locked;
	}
	public void setLocked(Integer locked) {
		this.locked = locked;
	}
	public List<DepartmentBean> getDepartmentList() {
		return departmentList;
	}
	public void setDepartmentList(List<DepartmentBean> departmentList) {
		this.departmentList = departmentList;
	}
	public String getRePass() {
		return rePass;
	}
	public void setRePass(String rePass) {
		this.rePass = rePass;
	}
	public boolean isChangeSelf() {
		return changeSelf;
	}
	public void setChangeSelf(boolean changeSelf) {
		this.changeSelf = changeSelf;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}

	public ImportUserConfig getImportUserConfig() {
		return importUserConfig;
	}

	public void setImportUserConfig(ImportUserConfig importUserConfig) {
		this.importUserConfig = importUserConfig;
	}
}
