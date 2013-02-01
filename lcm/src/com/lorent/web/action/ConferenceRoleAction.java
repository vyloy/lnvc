package com.lorent.web.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.lorent.model.ConfRoleAuthorityBean;
import com.lorent.model.ConferenceRoleBean;
import com.lorent.util.PageUtil;
import com.lorent.model.AuthorityBean;

public class ConferenceRoleAction extends BaseAction<ConferenceRoleBean,Integer> {

	private static final Logger log = Logger.getLogger(ConferenceRoleAction.class); 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ConferenceRoleBean conferenceRole;
	private boolean searchConferenceRole = false;
	private ConferenceRoleBean conferenceRoleSearchModel;
	private List<AuthorityBean> authorities;
	private List<ConfRoleAuthorityBean> raList;
	private Map<Integer,Object> selectedAuthorities;
	/**
	 * 跳转到会议角色列表页面
	 * @return
	 */
	public String toConferenceRoleList(){
		buttonMap.put("add", false);
		buttonMap.put("delete", false);
		buttonMap.put("search", false);
		searchConferenceRole = false;
		conferenceRole = new ConferenceRoleBean();
		setSubPageMap(conferenceRole, serviceFacade.getConferenceRoleService(), orderString);
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/conferenceRoleAction_toConferenceRoleList_conferencerolelist.action");
		return SUCCESS;
	}
	
	public String sortConferenceRole(){
		setSortField();
		if(searchConferenceRole){
			return searchConferenceRole();
		}
		return toConferenceRoleList();
	}
	
	public List<AuthorityBean> getAllAuthority() throws Exception{
		AuthorityBean bean = new AuthorityBean();
		List<AuthorityBean> list = serviceFacade.getAuthorityService().getByExample(bean);
		return list;
	}
	
	/**
	 * 跳转到会议角色编辑页面
	 * @return
	 */
	public String toEditConferenceRole()throws Exception {
		log.info("com.lorent.web.action.ConferenceRoleAction toEditConferenceRole() -- 跳转到会议角色编辑页面");
		Integer[]ids = getSelectedIds();
		if(ids==null)return null;
		setConferenceRole(serviceFacade.getConferenceRoleService().get(ids[0]));
		ConfRoleAuthorityBean raBean = new ConfRoleAuthorityBean();
		raBean.setRoleId(ids[0]);
		List<ConfRoleAuthorityBean> raList = serviceFacade.getConfRoleAuthorityService().getByExample(raBean);
		Map<Integer,Object> selectedAuthorities = new HashMap<Integer,Object>();
		for(int i=0;raList!=null&&i<raList.size();i++){
			selectedAuthorities.put(raList.get(i).getAuthorityId(), null);
		}
		this.setSelectedAuthorities(selectedAuthorities);
		//initConferenceRoleTypeList();
		this.setAuthorities(getAllAuthority());
		return SUCCESS;
	}
	/**
	 * 添加会议角色资料
	 * @return
	 */
	public String addConferenceRole() throws Exception{
		log.info("com.lorent.web.action.ConferenceRoleAction addConferenceRole() -- 添加会议角色资料");
		setConferenceRole(new ConferenceRoleBean());
		//initConferenceRoleTypeList();
		this.setAuthorities(getAllAuthority());
		this.setSelectedAuthorities(null);
		return SUCCESS;
	}
	/**
	 * 保存用户编辑的会议角色资料
	 * @return
	 */
	public String doEditConferenceRole()throws Exception {
//		if(conferenceRole.getId()==null){
//			log.info("添加套餐");
//			serviceFacade.getConferenceRoleService();
//		}else {
//			log.info("修改套餐");
//			log.info("套餐ID" + rates.getId());
//			serviceFacade.getRatesService().renewRates(rates);
//		}
		Integer[]authorityIds = getSelectedIdsNoException();
		ConferenceRoleBean conferenceRolePo = new ConferenceRoleBean();
		BeanUtils.copyProperties(conferenceRolePo,conferenceRole);
		if(conferenceRole.getId()==null){
			serviceFacade.getConferenceRoleService().createConferenceRole(conferenceRolePo, authorityIds);
		}else{
			serviceFacade.getConferenceRoleService().renewConferenceRole(conferenceRolePo, authorityIds);
		}
		
		
		initCallBackUrl();
		return SUCCESS;
	}
	/**
	 * 删除会议角色记录
	 * @return
	 */
	public String deleteConferenceRole()throws Exception {
		log.info("com.lorent.web.action.conferenceRoleAction deleteRates() -- 删除会议角色记录");
		Integer[]ids = getSelectedIds();
		log.info("删除会议角色记录:"+ids);
		if(ids==null||ids.length<1)return SUCCESS;
		serviceFacade.getConferenceRoleService().removeConferenceRole(ids);
		if(searchConferenceRole)
			return searchConferenceRole();
		else 
			return this.toConferenceRoleList();
	}
	/**
	 * 搜索会议角色记录
	 * @return
	 */
	public String searchConferenceRole() {
		log.info("com.lorent.web.action.ConferenceRoleAction searchConferenceRole() -- 搜索会议角色记录");
		if(!"".equals(isSearch)){
			//conferenceRoleSearchModel = conferenceRole;
			searchConferenceRole = true;
			setIsSearch("");
		}
		setSubPageMap(conferenceRoleSearchModel, serviceFacade.getConferenceRoleService(), orderString);
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/conferenceRoleAction_searchConferenceRole_conferencerolelist.action");
		return SUCCESS;
	}
	
	
	public String getCallBackUrl(){
		return callBackUrl;
	}
	/**
	 * 初始化编辑后跳转的url
	 * @return
	 */
	private void initCallBackUrl() {
		callBackUrl = "app/conferenceRoleAction_toConferenceRoleList_conferencerolelist.action";
	}
	/*===================================== getters and setters =============================================*/
	public ConferenceRoleBean getModel() {
		return this.conferenceRole;
	}
	public ConferenceRoleBean getConferenceRole() {
		return conferenceRole;
	}
	public void setConferenceRole(ConferenceRoleBean conferenceRole) {
		this.conferenceRole = conferenceRole;
	}
	

	public void setAuthorities(List<AuthorityBean> list){
		this.authorities = list;
	}
	
	public List<AuthorityBean> getAuthorities(){
		return this.authorities;
	}

	public Map<Integer, Object> getSelectedAuthorities() {
		return selectedAuthorities;
	}

	public void setSelectedAuthorities(Map<Integer, Object> selectedAuthorities) {
		this.selectedAuthorities = selectedAuthorities;
	}

	public ConferenceRoleBean getConferenceRoleSearchModel() {
		return conferenceRoleSearchModel;
	}

	public void setConferenceRoleSearchModel(
			ConferenceRoleBean conferenceRoleSearchModel) {
		this.conferenceRoleSearchModel = conferenceRoleSearchModel;
	}

//	public void setRaList(List<ConfRoleAuthorityBean> raList) {
//		this.raList = raList;
//	}
	
	
}