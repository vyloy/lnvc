package com.lorent.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.lorent.dto.BillingStatisticsData;
import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.BillingBean;
import com.lorent.model.CustomerBean;
import com.lorent.util.PageUtil;
public class BillingAction extends BaseAction<BillingBean,Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BillingBean billing;
	private BillingBean searchModel;
	private Map<Integer, String>payList;
	private Integer ispay;
	private static final Logger log = Logger.getLogger(BillingAction.class);
	/**
	 * 跳转到账单列表页面
	 * @return
	 */
	public String toBillingList() {
		searchMode = false;
		ispay = null;
		billing = new BillingBean();
		setSubPageMap(billing, serviceFacade.getBillingService(), orderString);
		buttonMap.put("edit", false);
		buttonMap.put("add", false);
		buttonMap.put("delete", false);
		buttonMap.put("closeBilling", true);
		buttonMap.put("statistics", true);
		initPayList();
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/billingAction_toBillingList_billinglist.action");
		return SUCCESS;
	}
	
	public String sortBilling(){
		setSortField();
		if(searchMode){
			return searchBilling();
		}
		return toBillingList();
	}
	
	/**
	 * 结算
	 * @return
	 * @throws Exception
	 */
	public String closeBilling()throws Exception {
		log.info("----- com.lorent.web.action.BillingAction  closeBilling() 开始结算-----");
		Integer[]ids = getSelectedIds();
		if(ids==null)return SUCCESS;
		try {
			serviceFacade.getBillingService().closeBilling(ids);
			log.info("-----结算完成-----");
		} catch (CustomSqlException e) {
			e.printStackTrace();
			setErrorMsg(e.getMessage());
			log.error("",e);
		} catch (ArgsException e) {
			e.printStackTrace();
			setErrorMsg(e.getMessage());
			log.error("",e);
		}
		if(searchMode)
			return searchBilling();
		else 
			return toBillingList();
	}
	/**
	 * 搜索账单
	 * @return
	 */
	public String searchBilling() {
		log.info("---- com.lorent.web.action.BillingAction  searchBilling() 搜索账单 ----");
		if(!"".equals(getIsSearch())){
			searchModel = billing;
			setIsSearch("");
			searchMode = true;
		}
		if(ispay==null)
			searchModel.setIsPay(null);
		else {
			searchModel.setIsPay(ispay.equals(1)?true:false);
		}
		setSubPageMap(searchModel, serviceFacade.getBillingService(), orderString);
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/billingAction_searchBilling_billinglist.action");
		return SUCCESS;
	}
	/**
	 * 初始化是否支付列表
	 *
	 */
	private void initPayList() {
		if(payList==null){
			payList = new HashMap<Integer, String>();
			payList.put(1, getText("page.billing.haspay"));
			payList.put(0, getText("page.billing.notpay"));
		}
	}
	

	
	//跳转到统计页面
	public String toStatistics(){
		//按客户统计当前数据
		List<BillingBean> list = (List<BillingBean>)pageMap.get(PageUtil.PAGEMAP_KEY_PAGERESULT);
		List<BillingStatisticsData> datas = serviceFacade.getBillingService().statisticsByCustomer(list);
		pageMap.put("billingStatics", datas);
		System.out.println();
		
		return SUCCESS;
	}
	
	/*==================================== getters and setters ==============================================*/
	public BillingBean getModel() {
		return billing;
	}

	public BillingBean getBilling() {
		return billing;
	}

	public void setBilling(BillingBean billing) {
		this.billing = billing;
	}
	public BillingBean getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(BillingBean searchModel) {
		this.searchModel = searchModel;
	}
	public Map<Integer, String> getPayList() {
		return payList;
	}
	public void setPayList(Map<Integer, String> payList) {
		this.payList = payList;
	}
	public Integer getIspay() {
		return ispay;
	}
	public void setIspay(Integer ispay) {
		this.ispay = ispay;
	}

}
