package com.lorent.web.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.lorent.model.RatesBean;
import com.lorent.util.Constant;
import com.lorent.util.PageUtil;

public class RatesAction extends BaseAction<RatesBean,Integer> {

	private static final Logger log = Logger.getLogger(RatesAction.class); 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RatesBean rates;
	private Map<Integer, String>ratesTypeList;
	private boolean searchRates = false;
	private RatesBean ratesSearchModel;
	/**
	 * 跳转到费率列表页面
	 * @return
	 */
	public String toRatesList() {
		searchRates = false;
		rates = new RatesBean();
		setSubPageMap(rates, serviceFacade.getRatesService(), orderString);
		initRatesTypeList();
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/ratesAction_toRatesList_rateslist.action");
		return SUCCESS;
	}
	
	public String sortRates(){
		setSortField();
		if(searchRates){
			return searchRates();
		}
		return toRatesList();
	}
	
	/**
	 * 跳转到费率编辑页面
	 * @return
	 */
	public String toEditRates()throws Exception {
		log.info("com.lorent.web.action.RatesAction toEditRates() -- 跳转到费率编辑页面");
		Integer[]ids = getSelectedIds();
		if(ids==null)return null;
		setRates(serviceFacade.getRatesService().get(ids[0]));
		initRatesTypeList();
		return SUCCESS;
	}
	/**
	 * 添加费率资料
	 * @return
	 */
	public String addRates() {
		log.info("com.lorent.web.action.RatesAction addRates() -- 添加费率资料");
		setRates(new RatesBean());
		initRatesTypeList();
		return SUCCESS;
	}
	/**
	 * 保存用户编辑的费率资料
	 * @return
	 */
	public String doEditRates()throws Exception {
		if(rates.getId()==null){
			log.info("添加套餐");
			serviceFacade.getRatesService().createRates(rates);
		}else {
			log.info("修改套餐");
			log.info("套餐ID" + rates.getId());
			serviceFacade.getRatesService().renewRates(rates);
		}
		log.info("套餐名称:" + rates.getRatesName());
		log.info("套餐计费方式:" + rates.getRatesType());
		log.info("套餐费率" + rates.getRatesTarriff());
		log.info("套餐状态" + rates.getRatesStatus());
		initCallBackUrl();
		return SUCCESS;
	}
	/**
	 * 删除费率记录
	 * @return
	 */
	public String deleteRates()throws Exception {
		log.info("com.lorent.web.action.RatesAction deleteRates() -- 删除费率记录");
		Integer[]ids = getSelectedIds();
		log.info("删除费率记录:"+ids);
		if(ids==null)return SUCCESS;
		serviceFacade.getRatesService().delete(ids);
		if(searchRates)
			return searchRates();
		else 
			return toRatesList();
	}
	/**
	 * 搜索费率记录
	 * @return
	 */
	public String searchRates() {
		log.info("com.lorent.web.action.RatesAction searchRates() -- 搜索费率记录");
		if(!"".equals(isSearch)){
			ratesSearchModel = rates;
			searchRates = true;
			setIsSearch("");
		}
		setSubPageMap(ratesSearchModel, serviceFacade.getRatesService(), orderString);
		pageMap.put(PageUtil.PAGEMAP_KEY_TOPAGE, "app/ratesAction_searchRates_rateslist.action");
		return SUCCESS;
	}
	/**
	 * 初始化付费方式列表
	 *
	 */
	private void  initRatesTypeList(){
		log.info("com.lorent.web.action.RatesAction initRatesTypeList() --  初始化付费方式列表");
		if(ratesTypeList==null){
			ratesTypeList = new HashMap<Integer, String>();
			ratesTypeList.put(Constant.RATES_TYPE_BYTIME, getText("page.billing.ratestype.bytime"));
			ratesTypeList.put(Constant.RATES_TYPE_BYHOUR, getText("page.billing.ratestype.byhour"));
			ratesTypeList.put(Constant.RATES_TYPE_BYMIN, getText("page.billing.ratestype.bymin"));
		}
	}
	
	public String getCallBackUrl(){
		return callBackUrl;
	}
	/**
	 * 初始化编辑后跳转的url
	 * @return
	 */
	private void initCallBackUrl() {
		callBackUrl = "app/ratesAction_toRatesList_rateslist.action";
	}
	/*===================================== getters and setters =============================================*/
	public RatesBean getModel() {
		return rates;
	}
	public RatesBean getRates() {
		return rates;
	}
	public void setRates(RatesBean rates) {
		this.rates = rates;
	}
	public Map<Integer, String> getRatesTypeList() {
		return ratesTypeList;
	}
	public void setRatesTypeList(Map<Integer, String> ratesTypeList) {
		this.ratesTypeList = ratesTypeList;
	}

}
