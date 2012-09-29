package com.lorent.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.lorent.dto.StartEndDatePair;
import com.lorent.enums.ConfBelongType;
import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomAuthenticationException;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.ConferenceBean;
import com.lorent.model.ConferenceRoleBean;
import com.lorent.model.ConferenceTypeBean;
import com.lorent.model.ConferenceTypeRoleBean;
import com.lorent.model.MediaBean;
import com.lorent.model.RatesBean;
import com.lorent.model.UserBean;
import com.lorent.util.ClassUtil;
import com.lorent.util.Constant;
import com.lorent.util.PageUtil;
import com.lorent.util.PasswordUtil;
import com.lorent.util.PropertiesUtil;
import com.lorent.util.StringUtil;
import com.lorent.util.ThreadLocaleUtil;

public class ConferenceTypeAction extends BaseAction<ConferenceTypeBean,Integer> {

	private static final Logger log = Logger.getLogger(ConferenceTypeAction.class); 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ConferenceTypeBean conferenceType;
	private boolean searchConferenceType = false;
	private ConferenceTypeBean conferenceTypeSearchModel;
	private List<ConferenceRoleBean> conferenceRoles;
	private Map<Integer,Object> selectedConferenceRoles;
	/**
	 * 跳转到会议类型列表页面
	 * @return
	 */
	public String toConferenceTypeList() {
		searchConferenceType = false;
		conferenceType = new ConferenceTypeBean();
		setSubPageMap(conferenceType, serviceFacade.getConferenceTypeService(), orderString);
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/conferenceTypeAction_toConferenceTypeList_conferencetypelist.action");
		return SUCCESS;
	}
	
	public String sortConferenceType(){
		setSortField();
		if(searchConferenceType){
			return searchConferenceType();
		}
		return toConferenceTypeList();
	}
	
	/**
	 * 跳转到会议类型编辑页面
	 * @return
	 */
	public String toEditConferenceType()throws Exception {
		log.info("com.lorent.web.action.ConferenceTypeAction toEditConferenceType() -- 跳转到会议类型编辑页面");
		Integer[]ids = getSelectedIds();
		if(ids==null)return null;
		setConferenceType(serviceFacade.getConferenceTypeService().get(ids[0]));
		ConferenceRoleBean crBean = new ConferenceRoleBean();
		this.setConferenceRoles(serviceFacade.getConferenceRoleService().getByExample(crBean));
		ConferenceTypeRoleBean trBean = new ConferenceTypeRoleBean();
		trBean.setConferenceTypeId(ids[0]);
		List<ConferenceTypeRoleBean> trList = serviceFacade.getConferenceTypeRoleService().getByExample(trBean);
		HashMap<Integer,Object> selectedConferenceRoles = new HashMap<Integer,Object>();
		for(int i=0;trList!=null&&i<trList.size();i++){
			selectedConferenceRoles.put(trList.get(i).getConferenceRoleId(), null);
		}
		this.setSelectedConferenceRoles(selectedConferenceRoles);
		//initConferenceTypeTypeList();
		return SUCCESS;
	}
	/**
	 * 添加会议类型资料
	 * @return
	 */
	public String addConferenceType() throws Exception{
		log.info("com.lorent.web.action.ConferenceTypeAction addConferenceType() -- 添加会议类型资料");
		setConferenceType(new ConferenceTypeBean());
		this.setSelectedConferenceRoles(null);
		ConferenceRoleBean crBean = new ConferenceRoleBean();
		this.setConferenceRoles(serviceFacade.getConferenceRoleService().getByExample(crBean));
		//initConferenceTypeTypeList();
		return SUCCESS;
	}
	/**
	 * 保存用户编辑的会议类型资料
	 * @return
	 */
	public String doEditConferenceType()throws Exception {
//		if(conferenceType.getId()==null){
//			log.info("添加套餐");
//			serviceFacade.getConferenceTypeService();
//		}else {
//			log.info("修改套餐");
//			log.info("套餐ID" + rates.getId());
//			serviceFacade.getRatesService().renewRates(rates);
//		}
		Integer[]roleIds = getSelectedIdsNoException();
		ConferenceTypeBean conferenceTypePo = new ConferenceTypeBean();
		BeanUtils.copyProperties(conferenceTypePo,conferenceType);
		if(conferenceType.getId()==null){
			serviceFacade.getConferenceTypeService().createConferenceType(conferenceTypePo, roleIds);
		}else{
			serviceFacade.getConferenceTypeService().renewConferenceType(conferenceTypePo, roleIds);
		}
		
		initCallBackUrl();
		return SUCCESS;
	}
	/**
	 * 删除会议类型记录
	 * @return
	 */
	public String deleteConferenceType()throws Exception {
		log.info("com.lorent.web.action.conferenceTypeAction deleteRates() -- 删除会议类型记录");
		Integer[]ids = getSelectedIds();
		log.info("删除会议类型记录:"+ids);
		if(ids==null||ids.length<1)return SUCCESS;
		serviceFacade.getConferenceTypeService().removeConferenceType(ids);
		if(searchConferenceType)
			return searchConferenceType();
		else 
			return this.toConferenceTypeList();
	}
	/**
	 * 搜索会议类型记录
	 * @return
	 */
	public String searchConferenceType() {
		log.info("com.lorent.web.action.ConferenceTypeAction searchConferenceType() -- 搜索会议类型记录");
		if(!"".equals(isSearch)){
			//conferenceTypeSearchModel = conferenceType;
			searchConferenceType = true;
			setIsSearch("");
		}
		setSubPageMap(conferenceTypeSearchModel, serviceFacade.getConferenceTypeService(), orderString);
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/conferenceTypeAction_searchConferenceType_conferencetypelist.action");
		return SUCCESS;
	}
	/**
	 * 初始化付费方式列表
	 *
	 */
	private void  initRatesTypeList(){
		log.info("com.lorent.web.action.conferenceTypeAction initRatesTypeList() --  初始化付费方式列表");
//		if(ratesTypeList==null){
//			ratesTypeList = new HashMap<Integer, String>();
//			ratesTypeList.put(Constant.RATES_TYPE_BYTIME, getText("page.billing.ratestype.bytime"));
//			ratesTypeList.put(Constant.RATES_TYPE_BYHOUR, getText("page.billing.ratestype.byhour"));
//			ratesTypeList.put(Constant.RATES_TYPE_BYMIN, getText("page.billing.ratestype.bymin"));
//		}
	}
	
	public String getCallBackUrl(){
		return callBackUrl;
	}
	/**
	 * 初始化编辑后跳转的url
	 * @return
	 */
	private void initCallBackUrl() {
		callBackUrl = "app/conferenceTypeAction_toConferenceTypeList_conferencetypelist.action";
	}
	/*===================================== getters and setters =============================================*/
	public ConferenceTypeBean getModel() {
		return this.conferenceType;
	}
	public ConferenceTypeBean getConferenceType() {
		return conferenceType;
	}
	public void setConferenceType(ConferenceTypeBean conferenceType) {
		this.conferenceType = conferenceType;
	}

	public List<ConferenceRoleBean> getConferenceRoles() {
		return conferenceRoles;
	}

	public void setConferenceRoles(List<ConferenceRoleBean> conferenceRoles) {
		this.conferenceRoles = conferenceRoles;
	}

	public Map<Integer, Object> getSelectedConferenceRoles() {
		return selectedConferenceRoles;
	}

	public void setSelectedConferenceRoles(
			Map<Integer, Object> selectedConferenceRoles) {
		this.selectedConferenceRoles = selectedConferenceRoles;
	}

	public ConferenceTypeBean getConferenceTypeSearchModel() {
		return conferenceTypeSearchModel;
	}

	public void setConferenceTypeSearchModel(
			ConferenceTypeBean conferenceTypeSearchModel) {
		this.conferenceTypeSearchModel = conferenceTypeSearchModel;
	}
	

}
