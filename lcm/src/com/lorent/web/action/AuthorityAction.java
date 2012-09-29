package com.lorent.web.action;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.lorent.model.AuthorityBean;
import com.lorent.util.PageUtil;

public class AuthorityAction extends BaseAction<AuthorityBean,Integer> {

	private static final Logger log = Logger.getLogger(ConferenceTypeAction.class); 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AuthorityBean authority;
	private boolean searchAuthority = false;
	private AuthorityBean authoritySearchModel;
	
	public AuthorityBean getAuthoritySearchModel() {
		return authoritySearchModel;
	}

	public void setAuthoritySearchModel(AuthorityBean authoritySearchModel) {
		this.authoritySearchModel = authoritySearchModel;
	}

	/**
	 * 跳转到权限列表页面
	 * @return
	 */
	public String toAuthorityList() {
		searchAuthority = false;
		authority = new AuthorityBean();
		setSubPageMap(authority, serviceFacade.getAuthorityService(), orderString);
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/authorityAction_toAuthorityList_authoritylist.action");
		return SUCCESS;
	}
	
	public String sortAuthority(){
		setSortField();
		if(searchAuthority){
			return searchAuthority();
		}
		return toAuthorityList();
	}
	
	/**
	 * 跳转到权限编辑页面
	 * @return
	 */
	public String toEditAuthority()throws Exception {
		log.info("com.lorent.web.action.AuthorityAction toEditAuthority() -- 跳转到权限编辑页面");
		Integer[]ids = getSelectedIds();
		if(ids==null)return null;
		setAuthority(serviceFacade.getAuthorityService().get(ids[0]));
		//initConferenceTypeTypeList();
		return SUCCESS;
	}
	/**
	 * 添加权限资料
	 * @return
	 */
	public String addAuthority() {
		log.info("com.lorent.web.action.AuthorityAction addAuthority() -- 添加权限资料");
		setAuthority(new AuthorityBean());
		//initConferenceTypeTypeList();
		return SUCCESS;
	}
	/**
	 * 保存用户编辑的权限资料
	 * @return
	 */
	public String doEditAuthority()throws Exception {
//		if(conferenceType.getId()==null){
//			log.info("添加套餐");
//			serviceFacade.getConferenceTypeService();
//		}else {
//			log.info("修改套餐");
//			log.info("套餐ID" + rates.getId());
//			serviceFacade.getRatesService().renewRates(rates);
//		}
		AuthorityBean authorityPo = new AuthorityBean();
		BeanUtils.copyProperties(authorityPo,authority);
		if(authority.getId()==null){
			serviceFacade.getAuthorityService().save(authorityPo);
		}else{
			serviceFacade.getAuthorityService().update(authorityPo);
		}
		
		
		initCallBackUrl();
		return SUCCESS;
	}
	/**
	 * 删除权限记录
	 * @return
	 */
	public String deleteAuthority()throws Exception {
		log.info("com.lorent.web.action.AuthorityAction deleteAuthority() -- 删除权限记录");
		Integer[]ids = getSelectedIds();
		log.info("删除权限记录:"+ids);
		if(ids==null||ids.length<1)return SUCCESS;
		serviceFacade.getAuthorityService().removeAuthorities(ids);
		if(this.searchAuthority){
			this.setCallBackUrl("app/authorityAction_searchAuthority_authoritylist.action");
			return searchAuthority();
		}else{
			initCallBackUrl();
			return this.toAuthorityList();
		}
			
	}
	/**
	 * 搜索权限记录
	 * @return
	 */
	public String searchAuthority() {
		log.info("com.lorent.web.action.ConferenceTypeAction searchConferenceType() -- 搜索权限记录");
		if(!"".equals(isSearch)){
			//this.authoritySearchModel = this.authority;
			searchAuthority = true;
			setIsSearch("");
		}
		setSubPageMap(authoritySearchModel, serviceFacade.getAuthorityService(), orderString);
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/authorityAction_searchAuthority_authoritylist.action");
		return SUCCESS;
	}
	
	public void setCallBackUrl(String callBackUrl){
		this.callBackUrl = callBackUrl;
	}
	
	public String getCallBackUrl(){
		return callBackUrl;
	}
	/**
	 * 初始化编辑后跳转的url
	 * @return
	 */
	private void initCallBackUrl() {
		callBackUrl = "app/authorityAction_toAuthorityList_authoritylist.action";
	}
	/*===================================== getters and setters =============================================*/
	public AuthorityBean getModel() {
		return this.authority;
	}
	public AuthorityBean getAuthority() {
		return authority;
	}
	public void setAuthority(AuthorityBean authority) {
		this.authority = authority;
	}
	

}
