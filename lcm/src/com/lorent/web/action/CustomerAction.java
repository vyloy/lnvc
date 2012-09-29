package com.lorent.web.action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.exception.ValidateException;
import com.lorent.model.CustomerBean;
import com.lorent.model.McuServerBean;
import com.lorent.model.RatesBean;
import com.lorent.service.impl.McuServerServiceImpl;
import com.lorent.util.ClassUtil;
import com.lorent.util.Constant;
import com.lorent.util.PageUtil;
import com.lorent.util.PropertiesUtil;
import com.sun.org.apache.bcel.internal.Constants;
public class CustomerAction extends BaseAction<CustomerBean,Integer> {

	private static final Logger log = Logger.getLogger(CustomerAction.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomerBean customer;
	private CustomerBean searchModel;
	private List<McuServerBean>serverList;
	private Integer mcuId;
	private List<RatesBean>ratesList;
	
	/**
	 * 跳转到客户信息列表显示页面
	 * @return
	 */
	public String toCustomerList() {
		log.info("com.lorent.web.action.CustomerAction toCustomerList() -- 跳转到客户信息列表显示页面 ");
		searchMode = false;
		customer = new CustomerBean();

		Map<String, Criterion> map = new HashMap<String, Criterion>();
		setInitCondition(map);
		DetachedCriteria dc = ClassUtil.createCriteriaByMap(map, CustomerBean.class);
		setSubPageMap(dc, serviceFacade.getCustomerService(), orderString);
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/customerAction_toCustomerList_admin_customer.action");
		return SUCCESS;
	}
	
	//初始化条件
	private void setInitCondition(Map<String, Criterion> map){
		map.put("status", Restrictions.eq("status", Constant.RECORD_STATUS_VALID));
		map.put("customerCode", Restrictions.not(Restrictions.eq("customerCode", PropertiesUtil.getConstant("initdata.admin.customer.code"))));
	}
	
	public String sortCustomer(){
		setSortField();
		if(searchMode){
			return searchCustomer();
		}
		return toCustomerList();
	}
	
	/**
	 * 跳转到客户信息编辑页面
	 * @return
	 * @throws Exception
	 */
	public String toEditCustomer()throws Exception {
//		log.info("com.lorent.web.action.CustomerAction toEditCustomer() --  跳转到客户信息编辑页面 ");
//		HttpServletRequest request = ServletActionContext.getRequest();
//		int id = Integer.parseInt(request.getParameterValues("choose")[0]);
//		customer = serviceFacade.getCustomerService().get(id);
		customer = serviceFacade.getCustomerService().getFirstValidCustomer();
//		Hibernate.initialize(customer.getNos());
		Hibernate.initialize(customer.getRates());
		Hibernate.initialize(customer.getMcuServer());
//		customer.getNos().size();
		try {
			initRatesList();
		} catch (Exception e) {
			log.error("",e);
			e.printStackTrace();
		}
		buttonMap.put("edit", false);
		return SUCCESS;
	}
	/**
	 * 响应添加客户信息的请求
	 * @return
	 */
	public String toAddCustomer()throws Exception {
		log.info("com.lorent.web.action.CustomerAction toAddCustomer() --  响应添加客户信息的请求 ");
		int customerSize = serviceFacade.getCustomerService().getAll().size();
		if(Constant.getCustomerCodeLength()*10 == customerSize){
			log.error("",new ValidateException("validate.customerismax"));
			throw new ValidateException("validate.customerismax");
		}
		setCustomer(new CustomerBean());
		try {
			initRatesList();
		} catch (CustomSqlException e) {
			log.error("",e);
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 将用户输入的客户信息持久化
	 * @return
	 * @throws Exception
	 */
	public String doEditCustomer()throws Exception {
		log.info("com.lorent.web.action.CustomerAction doEditCustomer() --  将用户输入的客户信息持久化 ");
		if(customer.getId()==null){
			log.info("添加客户");
			McuServerBean mcu = serviceFacade.getMcuServerService().getAll().get(0);
			RatesBean rates = serviceFacade.getRatesService().getAll().get(0);
			customer.setMcuServer(mcu);
			customer.setRates(rates);
			customer.setImdConfNoLimit(10);
			customer.setAppConfNoLimit(10);
			customer.setConfMeetingNoLimit(10);
			customer.setPerConfNoLimit(10);
			serviceFacade.getCustomerService().createCustomer(customer);
		}else{
			log.info("修改客户");
			log.info("修改客户ID：" + customer.getId());
			serviceFacade.getCustomerService().renewCustomer(customer);
		}
		log.info("客户名称:" + customer.getCustomerName());
		log.info("客户代码:" + customer.getCustomerCode());
		throw new ArgsException("args.customereditsuccess");
	}
	/**
	 * 删除客户资料
	 * @return
	 * @throws Exception
	 */
	public String deleteCustomer()throws Exception {
		log.info("com.lorent.web.action.CustomerAction deleteCustomer() --  删除客户资料 ");
		Integer[]ids = getSelectedIds();
		log.info("删除客户:" + ids);
		if(ids!=null){
			List<CustomerBean> customer = serviceFacade.getCustomerService().get(ids);
			serviceFacade.getCustomerService().removeCustomer(customer);
		}
		if(getSearchMode())return searchCustomer();
		return toCustomerList();
	}
	/**
	 * 搜索客户资料
	 * @return
	 */
	public String searchCustomer() {
		log.info("com.lorent.web.action.CustomerAction searchCustomer() --  搜索客户资料 ");
		if(!"".equals(getIsSearch())){
			searchModel = customer;
			setIsSearch("");
			searchMode = true;
		}
		setSearchMode(true);
		Map<String, Criterion> map = new HashMap<String, Criterion>();
		setInitCondition(map);
		ClassUtil.parseExampleToCriteriaMap(map, searchModel, null);
		DetachedCriteria dc = ClassUtil.createCriteriaByMap(map, CustomerBean.class);
		setSubPageMap(dc, serviceFacade.getCustomerService(), orderString);
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/customerAction_searchCustomer_admin_customer.action");
		return SUCCESS;
	}
	
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	/**
	 * 获取回调URL
	 * @return
	 */
	public String getCallBackUrl() {
		if(callBackUrl==null||callBackUrl.equals("")){
			if(getSearchMode())
				return "app/customerAction_searchCustomer_admin_customer.action";
			else 
				return"app/customerAction_toCustomerList_admin_customer.action";
		}
		return callBackUrl;
	}
	/**
	 * 初始化mcu服务器列表
	 */
	public void initServerList(){
		McuServerBean server = new McuServerBean();
		server.setServerStatus(Constant.RECORD_VALID);
		try {
			serverList = serviceFacade.getMcuServerService().getByExample(server);
		} catch (CustomSqlException e) {
			e.printStackTrace();
		}
		if(serverList==null)serverList = new ArrayList<McuServerBean>();
	}
	/**
	 * 初始化费率列表
	 * @throws CustomSqlException
	 */
	private void initRatesList()throws CustomSqlException{
		RatesBean rates = new RatesBean();
		rates.setRatesStatus(Constant.RECORD_VALID);
		rates.setStatus(Constant.RECORD_STATUS_VALID);
		ratesList = serviceFacade.getRatesService().getByExample(rates);
		if(ratesList==null)
			ratesList = new ArrayList<RatesBean>();
	}
	
/*============================= getters and setters =============================*/
	public String getSuccessMsg() {
		return successMsg;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public CustomerBean getModel() {
		return customer;
	}

	public CustomerBean getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerBean customer) {
		this.customer = customer;
	}
	public CustomerBean getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(CustomerBean searchModel) {
		this.searchModel = searchModel;
	}
	public List<McuServerBean> getServerList() {
		initServerList();
		return serverList;
	}
	public void setServerList(List<McuServerBean> serverList) {
		this.serverList = serverList;
	}
	public Integer getMcuId() {
		return mcuId;
	}
	public void setMcuId(Integer mcuId) {
		this.mcuId = mcuId;
	}
	public List<RatesBean> getRatesList() {
		return ratesList;
	}
	public void setRatesList(List<RatesBean> ratesList) {
		this.ratesList = ratesList;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
