package com.lorent.web.action;

import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.lorent.exception.ArgsException;
import com.lorent.model.CustomerBean;
import com.lorent.model.DepartmentBean;
import com.lorent.model.UserBean;
import com.lorent.util.Constant;
import com.lorent.util.PageUtil;
import com.lorent.util.PropertiesUtil;
import com.lorent.util.StringUtil;
import com.lorent.util.ThreadLocaleUtil;

public class OrganAction extends BaseAction{
	private static final Logger log = Logger.getLogger(CustomerAction.class);
	//添加修改用
	private DepartmentBean deptModel;
	private UserBean userModel;
	private int selectOrgId;
	private Map<String, String> genderMap;
	
	public String showOrgan(){
		return SUCCESS;
	}
	
	public String getDeptList()throws Exception {
		CustomerBean firstValidCustomer = serviceFacade.getCustomerService().getFirstValidCustomer();
		if(firstValidCustomer == null){
			result = "{fail:\"" + PropertiesUtil.getMsgResource("page.department.nocustomer") + "\"}";
		}else{
			List<DepartmentBean> depts = serviceFacade.getDepartmentService().getDeptByCustomer(firstValidCustomer.getId());
			//process json
//			StringBuffer sb = new StringBuffer("[");
			com.alibaba.fastjson.JSONArray array = new com.alibaba.fastjson.JSONArray();
			for(DepartmentBean dept : depts){
//				sb.append("{id:").append(dept.getId()).append(",")
//					.append("pId:").append(dept.getParentId()).append(",")
//					.append("name:\"").append(dept.getDepartmentName()).append("\"},");
				com.alibaba.fastjson.JSONObject d = new com.alibaba.fastjson.JSONObject();
				d.put("id", dept.getId());
				d.put("pId", dept.getParentId());
				d.put("name", dept.getDepartmentName());
				array.add(d);
			}
//			sb.deleteCharAt(sb.length() - 1).append("]");
//			result = sb.toString();
			result = array.toJSONString();
		}
		log.debug("getDeptList result=" + result);
		return SUCCESS;
	}
	
	public String toAddDept()throws Exception{
		int parentId = Integer.parseInt(getPara("parentId"));
		String parentName = URLDecoder.decode(getPara("parentName"), "utf8");
		log.info("toAddDept para: parentId=" + parentId + "&parentName=" + parentName);
		deptModel = new DepartmentBean();
		deptModel.setParentId(parentId);
		deptModel.setParentName(parentName);
		return SUCCESS;
	}
	
	public String toModifyDept()throws Exception{
		int parentId = Integer.parseInt(getPara("parentId"));
		String parentName = URLDecoder.decode(getPara("parentName"), "utf8");
		int nodeId = Integer.parseInt(getPara("nodeId"));
		log.info("toModifyDept para: nodeId=" + nodeId + "&parentId=" + parentId + "&parentName=" + parentName);
		deptModel = serviceFacade.getDepartmentService().get(nodeId);
		deptModel.setParentName(parentName);
		return SUCCESS;
	}
	
	public String doAddAndModifyDept()throws Exception{
		if(deptModel.getId()==null){//add
			log.info("add dept:" + deptModel);
			serviceFacade.getDepartmentService().createDepartment(deptModel);
			callBackUrl = "add_dept_ok";		
		}else{//modify
			log.info("modify dept:" + deptModel);
			if(deptModel.getId().equals(deptModel.getParentId())){
				throw new ArgsException("args.not_select_this_for_parentdept");
			}
			serviceFacade.getDepartmentService().renewDepartment(deptModel);
			callBackUrl = "modify_dept_ok";
		}
		return SUCCESS;
	}
	
	public String deleteDept(){
		int nodeId = Integer.parseInt(getPara("nodeId"));
		log.info("remove dept: nodeId=" + nodeId);
		try{
			serviceFacade.getDepartmentService().removeDepartment(nodeId);
			result="{success:\"success\"}";
		}catch(Exception e){
			log.error("deleteDept", e);
			result = "{fail:\"" + e.getMessage() + "\"}";
		}
		return SUCCESS;
	}
	
	public String showUserList() throws Exception{
		if(getPara("nodeId")!=null){
			selectOrgId = Integer.parseInt(getPara("nodeId"));
		}
		log.info("show user list: nodeId=" + selectOrgId);
		DepartmentBean dept = serviceFacade.getDepartmentService().get(selectOrgId);
		String hql = null;
		if(dept.getIsRoot()){
			hql = "select u from UserBean u left join u.department d where u.status = " + 
				Constant.RECORD_STATUS_VALID + " and d.customerId = " + serviceFacade.getCustomerService().getFirstValidCustomer().getId();
		}else{
			Integer id = dept.getId();
			hql = "select u from UserBean u left join u.department d where u.status = " +
				Constant.RECORD_STATUS_VALID + " and (d.id = " + id + " or d.searchStr like '" + dept.getSearchStr()+ "@" + id +"%')";
		}
		setSubPageMap(hql, serviceFacade.getUserService());
		initGenderMap();
		buttonMap.put("search", false);
		buttonMap.put("exportUser", true);
		buttonMap.put("importUser", true);
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/organAction_showUserList_user_list.action");
		return SUCCESS;
	}
	
	public String toAddUser() throws Exception{
		log.info("to add user");
		initGenderMap();
		DepartmentBean dept = serviceFacade.getDepartmentService().get(selectOrgId);
		userModel = new UserBean();
		userModel.setDepartment(dept);
		return SUCCESS;
	}
	
	public String toEditUser() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String[]ids = request.getParameterValues("choose");
		log.info("to modify user: id=" + ids[0]);
		initGenderMap();
		userModel = serviceFacade.getUserService().get(Integer.parseInt(ids[0]));
		return SUCCESS;
	}
	
	public String toEditMyInfo() throws Exception{
		initGenderMap();
		userModel = serviceFacade.getUserService().get((ThreadLocaleUtil.getUser().getId()));
		return SUCCESS;
	}
	
	public String doAddAndModifyUser()throws Exception{
		if(userModel.getId()==null){//add
			log.info("add user:" + userModel);
			userModel.setCustomer(serviceFacade.getCustomerService().getFirstValidCustomer());
			userModel.setDepartment(serviceFacade.getDepartmentService().get(userModel.getDepartment().getId()));
			userModel.setUserType(Constant.USER_TYPE_INNERUSER);
			serviceFacade.getUserService().createUser(userModel);
		}else{//modify
			log.info("modify user:" + userModel);
			userModel.setDepartment(serviceFacade.getDepartmentService().get(userModel.getDepartment().getId()));
			serviceFacade.getUserService().renewUser(userModel);
		}
		initCallBackUrl();
		return SUCCESS;
	}
	
	public String changePassword(){
		try{
			String oldpasswd = URLDecoder.decode(getPara("oldpasswd"), "utf8");
			String newpasswd = URLDecoder.decode(getPara("newpasswd"), "utf8");
			String reptpasswd = URLDecoder.decode(getPara("reptpasswd"), "utf8");
			serviceFacade.getUserService().changePassword(userModel, oldpasswd, newpasswd, reptpasswd);
			log.info("change password");
			result="{success:\"success\"}";
		}catch(Exception e){
			log.error("changePassword", e);
			result = "{fail:\"" + e.getMessage() + "\"}";
		}
		return SUCCESS;
	}
	
	public String deleteUser()throws Exception {
		Integer[]ids = getSelectedIds();
		log.info("delete user：" + ids);
		List<UserBean> users = serviceFacade.getUserService().get(ids);
		serviceFacade.getUserService().removeUser(users);
		showUserList();
		return SUCCESS;
	}

	private void initGenderMap(){
		genderMap = new LinkedHashMap<String, String>();
		genderMap.put("male", PropertiesUtil.getMsgResource("page.user.gender.male"));
		genderMap.put("female", PropertiesUtil.getMsgResource("page.user.gender.female"));
	}
	
	private void initCallBackUrl() {
		if(getSearchMode()){
			
		}else {
			callBackUrl = "app/organAction_showUserList_user_list.action?nodeId=" + selectOrgId;
		}
	}
	
	public String importNothing() throws ArgsException{
		throw new ArgsException("user.realname.empty");
	}
	
	@Override
	public Object getModel() {
		return null;
	}
	
	//=======================getter/setter=============================

	public DepartmentBean getDeptModel() {
		return deptModel;
	}

	public void setDeptModel(DepartmentBean deptModel) {
		this.deptModel = deptModel;
	}

	public UserBean getUserModel() {
		return userModel;
	}

	public void setUserModel(UserBean userModel) {
		this.userModel = userModel;
	}

	public Map<String, String> getGenderMap() {
		return genderMap;
	}

	public void setGenderMap(Map<String, String> genderMap) {
		this.genderMap = genderMap;
	}
	
}
