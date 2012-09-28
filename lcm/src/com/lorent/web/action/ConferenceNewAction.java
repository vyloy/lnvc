package com.lorent.web.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.lorent.exception.ArgsException;
import com.lorent.model.ConferenceNewBean;
import com.lorent.model.ConferenceRoleBean;
import com.lorent.model.ConferenceTypeBean;
import com.lorent.model.UserBean;
import com.lorent.service.impl.ServiceCallBack;
import com.lorent.util.Constant;
import com.lorent.util.PageUtil;
import com.lorent.util.PropertiesUtil;
import com.lorent.util.ThreadLocaleUtil;

public class ConferenceNewAction extends BaseAction<ConferenceNewBean,Integer>{

	private static final Logger log = Logger.getLogger(ConferenceNewAction.class);
	private ConferenceNewBean conferenceNewSearchModel;
	private ConferenceNewBean conferenceNew;
	private boolean searchConferenceNew = false;
	private List<ConferenceTypeBean> conferenceTypes;
	private Map<Integer,String> conferenceRoles;
	private List<UserBean> allUsers;
	private Map<Integer,List<Integer>> selectedUser;
	private Map<Integer,String> conferenceTypeMap;

	/**
	 * 跳转到会议列表页面
	 * @return
	 */
	public String toConferenceNewList() throws Exception{
		searchConferenceNew = false;
		initConferenceType();
//		setSubPageMap("select cn.id,cn.conferenceName,cr.roleName,ct.typeName,ub.username from ConferenceNewBean cn,ConferenceRoleBean cr,ConferenceTypeBean ct,UserBean ub where cn.del=1 and cn.defaultRoleId=cr.id and cn.conferenceTypeId=ct.id and cn.creator=ub.id order by cn." + orderString + " " + ascOrDesc, serviceFacade.getConferenceNewService());
		final Map<String,Object> params = new HashMap<String,Object>();
		params.put("orderString", orderString);
		params.put("ascOrDesc", ascOrDesc);
		searchSubPageMap(new ServiceCallBack(){
			public void searchCallBack() throws Exception{
				serviceFacade.getConferenceNewService().getInitSubPageMapResult(pageMap,params);
			}
		});
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/conferenceNewAction_toConferenceNewList_conferencenewlist.action");
		return SUCCESS;
	}
	
	
	
	public void initConferenceType() throws Exception{
		conferenceTypes = serviceFacade.getConferenceTypeService().getByExample(new ConferenceTypeBean());
		Map<Integer,String> conferenceTypeMap = new HashMap<Integer,String>();
		if(conferenceTypes != null){
			for(ConferenceTypeBean bean:conferenceTypes){
				conferenceTypeMap.put(bean.getId(), bean.getTypeName());
			}
		}
		this.setConferenceTypeMap(conferenceTypeMap);
	}
	
	public String sortConferenceNew()throws Exception{
		setSortField();
		if(searchConferenceNew){
			return searchConferenceNew();
		}
		return toConferenceNewList();
	}
	
	public String toSearchTypeRoleUserInfo() throws Exception{
		this.setAllUsers(serviceFacade.getUserService().getAll());//--add by chard
		HttpServletRequest request = ServletActionContext.getRequest();
		String typeStr = request.getParameter("conferenceType");
		Integer[] typeIds = {Integer.parseInt(typeStr)};
		List<ConferenceRoleBean> roles = serviceFacade.getConferenceRoleService().getRolesByConferenceTypeId(typeIds);
		Map<Integer,String> conferenceRoles = new HashMap<Integer,String>();
		for(int i=0;i<roles.size();i++){
			conferenceRoles.put(roles.get(i).getId(), roles.get(i).getRoleName());
		}
		this.setConferenceRoles(conferenceRoles);
		return SUCCESS;
	}
	
	/**
	 * 搜索会议角色记录
	 * @return
	 */
	public String searchConferenceNew() throws Exception{
		log.info("com.lorent.web.action.ConferenceNewAction searchConferenceNew() -- 搜索会议记录");
		final Map<String,Object> params = new HashMap<String,Object>();
//		List list = new ArrayList();
//		StringBuffer hql = new StringBuffer();
//		hql.append("select cn.id,cn.conferenceName,cr.roleName,ct.typeName,ub.username from ConferenceNewBean cn,ConferenceRoleBean cr,ConferenceTypeBean ct,UserBean ub where cn.del=1 and cn.defaultRoleId=cr.id and cn.conferenceTypeId=ct.id and cn.creator=ub.id ");
		params.put("isSearch", isSearch);
		params.put("conferenceNewSearchModel", conferenceNewSearchModel);
		params.put("ascOrDesc", ascOrDesc);
		params.put("orderString", orderString);
		if(!"".equals(isSearch)){
			//conferenceNewSearchModel = conferenceNew;
			searchConferenceNew = true;
			setIsSearch("");
//			if(conferenceNewSearchModel.getConferenceName()!=null && conferenceNewSearchModel.getConferenceName().length()>0){
//				hql.append(" and cn.conferenceName like ?");
//				list.add("%" + conferenceNewSearchModel.getConferenceName() + "%");
//			}
//			if(conferenceNewSearchModel.getConferenceTypeId()!=null && conferenceNewSearchModel.getConferenceTypeId().intValue()>0){
//				list.add(conferenceNewSearchModel.getConferenceTypeId());
//				hql.append(" and ct.id=?");
//			}
			
		}
//		hql.append("order by cn." + orderString + " " + ascOrDesc);
//		
//		//String hql = "select cn.id,cn.conferenceName,cr.roleName,ct.typeName,ub.username from ConferenceNewBean cn,ConferenceRoleBean cr,ConferenceTypeBean ct,UserBean ub where cn.conferenceName like ? and ct.id=? and cn.del=1 and cn.defaultRoleId=cr.id and cn.conferenceTypeId=ct.id and cn.creator=ub.id order by cn." + orderString + " " + ascOrDesc;
//		setSubPageMap(hql.toString(), serviceFacade.getConferenceNewService(), list);
		searchSubPageMap(new ServiceCallBack(){
			public void searchCallBack() throws Exception{
				serviceFacade.getConferenceNewService().getSearchSubPageMapResult(pageMap,params);
			}
		});
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/conferenceNewAction_searchConferenceNew_conferencenewlist.action");
		return SUCCESS;
	}
	
	
	
	/**
	 * 跳转到会议角色编辑页面
	 * @return
	 */
	public String toEditConferenceNew()throws Exception {
		log.info("com.lorent.web.action.ConferenceNewAction toEditConferenceNew() -- 跳转到会议编辑页面");
		Integer[]ids = getSelectedIds();
		if(ids==null)return null;
		setConferenceNew(serviceFacade.getConferenceNewService().get(ids[0]));
		//查询所有的会议类型
		this.setConferenceTypes(serviceFacade.getConferenceTypeService().getByExample(new ConferenceTypeBean()));
		Integer[] typeIds = {this.getConferenceNew().getConferenceTypeId()};
		//查询所选会议类型的所有角色
		List<ConferenceRoleBean> roles = serviceFacade.getConferenceRoleService().getRolesByConferenceTypeId(typeIds);
		Map<Integer,String> conferenceRoles = new HashMap<Integer,String>();
		this.setAllUsers(serviceFacade.getUserService().getAll());
		
		Integer[] roleIds = new Integer[roles.size()];
		for(int i=0;i<roleIds.length;i++){
			roleIds[i] = roles.get(i).getId();
			conferenceRoles.put(roleIds[i], roles.get(i).getRoleName());
		}
		Map<Integer,List<Integer>> map = serviceFacade.getConferenceUserService().getConferenceUserIdsByRoleId(this.getConferenceNew().getId(),roleIds);
		this.setSelectedUser(map);
		this.setConferenceRoles(conferenceRoles);	
		
		return SUCCESS;
	}
	
	
	
	/**
	 * 添加会议角色资料
	 * @return
	 */
	public String addConferenceNew() throws Exception{
		log.info("com.lorent.web.action.ConferenceBeanAction addConferenceNew() -- 添加会议");
		initCallBackUrl();
		ConferenceNewBean example = new ConferenceNewBean();
		example.setDel(1);
		List list = serviceFacade.getConferenceNewService().getByExample(example);
		if(list != null 
				&& list.size() >= Integer.parseInt(PropertiesUtil.getConstant("max.newconf"))){//TODO 暂时增加会议限制
			initCallBackUrl();
			throw new ArgsException("validate.ismaxconf");
		}
		initConferenceType();
		this.setConferenceNew(new ConferenceNewBean());
		this.setConferenceTypes(serviceFacade.getConferenceTypeService().getByExample(new ConferenceTypeBean()));
		this.setAllUsers(serviceFacade.getUserService().getAll());
		this.setSelectedUser(null);
		this.setConferenceRoles(null);
		this.setConferenceRoles(null);
		this.setConferenceNewSearchModel(null);
		return SUCCESS;
	}
	
	public String doEditConferenceNew() throws Exception{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String[]urIds = request.getParameterValues("userRole");
		ConferenceNewBean conferenceNewPo = new ConferenceNewBean();
		BeanUtils.copyProperties(conferenceNewPo,conferenceNew);
		if(conferenceNew.getId()==null){
			UserBean user = ThreadLocaleUtil.getUser();
			conferenceNewPo.setCreator(user.getId());
			String customerCode = user.getCustomer().getCustomerCode();
			try{
				serviceFacade.getConferenceNewService().createConfNo(conferenceNewPo, customerCode, Constant.CONF_TYPE_NEWCONFERENCE);
				serviceFacade.getConferenceNewService().createConferenceNew(conferenceNewPo, urIds);
			}catch(Exception e){
				serviceFacade.getConferenceNewService().removeSipConfByConfno(conferenceNewPo.getConfNo());
				conferenceNewPo.setId(null);
				throw e;
			}
		}else{
			
			serviceFacade.getConferenceNewService().renewConferenceNew(conferenceNewPo, urIds);
		}
		
		
		initCallBackUrl();
		return SUCCESS;
	}
	
	
	/**
	 * 删除会议角色记录
	 * @return
	 */
	public String deleteConferenceNew()throws Exception {
		log.info("com.lorent.web.action.conferenceRoleAction deleteRates() -- 删除会议角色记录");
		Integer[]ids = getSelectedIds();
		log.info("删除会议角色记录:"+ids);
		if(ids==null)return SUCCESS;
		serviceFacade.getConferenceNewService().removeConferenceNew(ids);
		if(searchConferenceNew)
			return searchConferenceNew();
		else 
			return this.toConferenceNewList();
	}
	
	private void initCallBackUrl() {
		callBackUrl = "app/conferenceNewAction_toConferenceNewList_conferencenewlist.action";
	}
	
	
	/*
	public String enterConf()throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String confno = ServletActionContext.getRequest().getParameter("confno");
		String lccno = ThreadLocaleUtil.getUser().getLccAccount();
		log.info("enterConf:" + "confno=" +confno + "&lccno=" + lccno);
		
        String protocol = request.getScheme();   
        String ip = request.getServerName();   
        int port = request.getServerPort();   
        String app = request.getContextPath();
        String filename = confno + "_" + lccno + ".jnlp";
        
        response.setContentType("application/x-java-jnlp-file");
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        
        PrintWriter out = response.getWriter();   
        out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");   
        out.println("<jnlp spec=\"1.0+\" codebase=\"" + protocol + "://" + ip   
                + ":" + port + "/" + app + "/" + PropertiesUtil.getConstant("lvmc.dir") + "\" href=\"\">");   
           
        out.println("<information>");   
        out.println("  <title>lvmc</title>");   
        out.println("  <vendor>lorent</vendor>");   
        out.println("</information>");   
        out.println("<security>");   
        out.println("  <all-permissions/>");   
        out.println("</security>");   
        out.println("<resources>");   
        out.println("  <j2se version=\"1.6+\"/>");   
        out.println("  <jar href=\""+ PropertiesUtil.getConstant("lvmc.main.jar") +"\" main=\"true\"/>");     
        String basePath = request.getRealPath("");
        String libDir = basePath + PropertiesUtil.getConstant("lvmc.lib.dir");
        File libFile = new File(libDir);
        if(libFile.isDirectory()){
        	File[] jarFiles = libFile.listFiles();
        	for(File jarFile :jarFiles ){
        		out.println("  <jar href=\"" + libFile.getName() + "/" + jarFile.getName() +"\"/>");
        	}
        }
//        out.println("  <jar href=\"lib/smack.jar\"/>");
//        out.println("  <jar href=\"lib/smackx-debug.jar\"/>");
//        out.println("  <jar href=\"lib/smackx-jingle.jar\"/>");
//        out.println("  <jar href=\"lib/smackx.jar\"/>");
//        out.println("  <jar href=\"lib/commons-logging-api-1.1.jar\"/>");
//        out.println("  <jar href=\"lib/dom4j-1.6.1.jar\"/>");
//        out.println("  <jar href=\"lib/idw-gpl.jar\"/>");
//        out.println("  <jar href=\"lib/jna.jar\"/>");
//        out.println("  <jar href=\"lib/log4j-1.2.16.jar\"/>");
//        out.println("  <jar href=\"lib/ws-commons-util-1.0.2.jar\"/>");
//        out.println("  <jar href=\"lib/xmlrpc-client-3.1.3.jar\"/>");
//        out.println("  <jar href=\"lib/xmlrpc-common-3.1.3.jar\"/>");
//        out.println("  <jar href=\"lib/xmlrpc-server-3.1.3.jar\"/>");
//        out.println("  <jar href=\"lib/org.springframework.aop-3.0.5.RELEASE.jar\"/>");
//        out.println("  <jar href=\"lib/org.springframework.asm-3.0.5.RELEASE.jar\"/>");
//        out.println("  <jar href=\"lib/org.springframework.aspects-3.0.5.RELEASE.jar\"/>");
//        out.println("  <jar href=\"lib/org.springframework.beans-3.0.5.RELEASE.jar\"/>");
//        out.println("  <jar href=\"lib/org.springframework.context-3.0.5.RELEASE.jar\"/>");
//        out.println("  <jar href=\"lib/org.springframework.context.support-3.0.5.RELEASE.jar\"/>");
//        out.println("  <jar href=\"lib/org.springframework.core-3.0.5.RELEASE.jar\"/>");
//        out.println("  <jar href=\"lib/org.springframework.expression-3.0.5.RELEASE.jar\"/>");
//        out.println("  <jar href=\"lib/org.springframework.instrument-3.0.5.RELEASE.jar\"/>");
//        out.println("  <jar href=\"lib/org.springframework.instrument.tomcat-3.0.5.RELEASE.jar\"/>");
//        out.println("  <jar href=\"lib/org.springframework.jdbc-3.0.5.RELEASE.jar\"/>");
//        out.println("  <jar href=\"lib/org.springframework.jms-3.0.5.RELEASE.jar\"/>");
//        out.println("  <jar href=\"lib/org.springframework.orm-3.0.5.RELEASE.jar\"/>");
//        out.println("  <jar href=\"lib/org.springframework.oxm-3.0.5.RELEASE.jar\"/>");
//        out.println("  <jar href=\"lib/org.springframework.test-3.0.5.RELEASE.jar\"/>");
//        out.println("  <jar href=\"lib/org.springframework.transaction-3.0.5.RELEASE.jar\"/>");
//        out.println("  <jar href=\"lib/org.springframework.web-3.0.5.RELEASE.jar\"/>");
//        out.println("  <jar href=\"lib/org.springframework.web.portlet-3.0.5.RELEASE.jar\"/>");
//        out.println("  <jar href=\"lib/org.springframework.web.servlet-3.0.5.RELEASE.jar\"/>");
//        out.println("  <jar href=\"lib/org.springframework.web.struts-3.0.5.RELEASE.jar\"/>");
//        out.println("  <jar href=\"lib/clientcommon.jar\"/>");
//        out.println("  <jar href=\"lib/JHotDraw.jar\"/>");
//        out.println("  <jar href=\"lib/mina-core-2.0.4.jar\"/>");
//        out.println("  <jar href=\"lib/WhiteBoardCommon.jar\"/>");
        out.println("</resources>");   
           
        out.println("<application-desc main-class=\"com.lorent.lvmc.Launcher\">");        
        out.println("<argument>"+ lccno +"</argument>");      
        out.println("<argument>"+ confno +"</argument>");      
        out.println("<argument>"+ ip +"</argument>");      
        out.println("</application-desc>");     
  
        out.println("</jnlp>");   
        out.flush();   
        out.close(); 
		
		return NONE;
	}
	*/
	
	
	public String enterConf()throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String confno = ServletActionContext.getRequest().getParameter("confno");
		String lccno = ThreadLocaleUtil.getUser().getLccAccount();
		log.info("enterConf:" + "confno=" +confno + "&lccno=" + lccno);
		
        String protocol = request.getScheme();   
        String ip = request.getServerName();   
        int port = request.getServerPort();   
        String app = request.getContextPath();
        String filename = confno + "_" + lccno + ".jnlp";
        
        response.setContentType("application/x-java-jnlp-file");
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        
        PrintWriter out = response.getWriter();   
        out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");   
        out.println("<jnlp spec=\"1.0+\" codebase=\"" + protocol + "://" + ip   
                + ":" + port + "/" + app + "/" + PropertiesUtil.getConstant("jnlp.dir") + "\" href=\"\">");   
           
        out.println("<information>");   
        out.println("  <title>lvmc</title>");   
        out.println("  <vendor>lorent</vendor>");   
        out.println("</information>");   
        out.println("<security>");   
        out.println("  <all-permissions/>");   
        out.println("</security>");   
        out.println("<resources>");   
        out.println("  <j2se version=\"1.6+\"/>");   
        out.println("  <jar href=\""+ PropertiesUtil.getConstant("jnlp.main.jar") +"\" main=\"true\"/>");     
        String basePath = request.getRealPath("");
        String libDir = basePath + PropertiesUtil.getConstant("jnlp.lib.dir");
        File libFile = new File(libDir);
        if(libFile.isDirectory()){
        	File[] jarFiles = libFile.listFiles();
        	for(File jarFile :jarFiles ){
        		out.println("  <jar href=\"" + libFile.getName() + "/" + jarFile.getName() +"\"/>");
        	}
        }
        out.println("</resources>");   
        /*
         * args[0]表示命令lvmc
         * args[1]表示LVMC.JAR包的下载HTTP路径
         * args[2]表示LVMC.JAR解压路径
         * args[3]表示要启动的BAT文件路径（相对路径）
         * args[4]表示BAT文件名称
         * args[5]表示用户名
         * args[6]表示会议号码
         * args[7]表示服务器IP
         * args[8]表示bat文件行号
         */   
        out.println("<application-desc main-class=\"com.lorent.jnlp.Launcher\">");
        out.println("<argument>lvmc</argument>");
        out.println("<argument>"+protocol+"://"+ip+":"+port+"/lcm/lvmc/lvmc.zip</argument>");
        out.println("<argument>lvmc</argument>");
        out.println("<argument>null</argument>");
        out.println("<argument>start.bat</argument>");
        out.println("<argument>"+ lccno +"</argument>");      
        out.println("<argument>"+ confno +"</argument>");      
        out.println("<argument>"+ ip +"</argument>");
        out.println("<argument>"+ PropertiesUtil.getConstant("updateBatFileLineNum") +"</argument>");
        out.println("</application-desc>");
        out.println("</jnlp>");   
        out.flush();   
        out.close(); 
		
		return NONE;
	}
	
	
	@Override
	public ConferenceNewBean getModel() {
		return this.conferenceNew;
	}

	public ConferenceNewBean getConferenceNew() {
		return conferenceNew;
	}

	public void setConferenceNew(ConferenceNewBean conferenceNew) {
		this.conferenceNew = conferenceNew;
	}
	
	public List<ConferenceTypeBean> getConferenceTypes() {
		return conferenceTypes;
	}

	public Map<Integer,String> getConferenceRoles() {
		return conferenceRoles;
	}

	public void setConferenceRoles(Map<Integer,String> conferenceRoles) {
		this.conferenceRoles = conferenceRoles;
	}

	public void setConferenceTypes(List<ConferenceTypeBean> conferenceTypes) {
		this.conferenceTypes = conferenceTypes;
	}

	public List<UserBean> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(List<UserBean> allUsers) {
		this.allUsers = allUsers;
	}

	public Map<Integer, List<Integer>> getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(Map<Integer, List<Integer>> selectedUser) {
		this.selectedUser = selectedUser;
	}

	public Map<Integer, String> getConferenceTypeMap() {
		return conferenceTypeMap;
	}

	public void setConferenceTypeMap(Map<Integer, String> conferenceTypeMap) {
		this.conferenceTypeMap = conferenceTypeMap;
	}

	public ConferenceNewBean getConferenceNewSearchModel() {
		return conferenceNewSearchModel;
	}

	public void setConferenceNewSearchModel(
			ConferenceNewBean conferenceNewSearchModel) {
		this.conferenceNewSearchModel = conferenceNewSearchModel;
	}

}
