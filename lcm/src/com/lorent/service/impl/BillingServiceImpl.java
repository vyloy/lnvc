package com.lorent.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lorent.dao.BillingDao;
import com.lorent.dto.BillingStatisticsData;
import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.BillingBean;
import com.lorent.model.ConferenceBean;
import com.lorent.model.CustomerBean;
import com.lorent.service.BillingService;
import com.lorent.util.Constant;

public class BillingServiceImpl extends GenericServiceImpl<BillingDao,BillingBean,Integer> implements
		BillingService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 改变账单的结算状态
	 * @param id
	 * @throws CustomSqlException
	 * @throws ArgsException
	 */
	public void closeBilling(Integer id) throws CustomSqlException, ArgsException {
		BillingBean billing = daoFacade.getBillingDao().get(id);
		if(id==null)throw new ArgsException("args.recordnotexists");
		if(billing.getConference().getConfStatus().equals(Constant.CONF_STATUS_NOT_START)||
				billing.getConference().getConfStatus().equals(Constant.CONF_STATUS_ONGOING))
			return;
		billing.setIsPay(true);
		daoFacade.getBillingDao().update(billing);
	}
	/**
	 * 改变账单的结算状态
	 * @param ids
	 * @throws CustomSqlException
	 * @throws ArgsException
	 */
	public void closeBilling(Integer[] ids) throws CustomSqlException, ArgsException {
		for (int i = 0; i < ids.length; i++) {
			closeBilling(ids[i]);
		}
	}
	
	/**
	 * 按客户统计
	 * @param list 要统计的数据
	 * @return
	 */
	public List<BillingStatisticsData> statisticsByCustomer(List<BillingBean> list) {
		//统计临时数据存放
		Map<Integer, BillingStatisticsData> temp = new HashMap<Integer, BillingStatisticsData>();
		for(BillingBean bean : list){
			CustomerBean customer = null;
			try {
				customer = serviceFacade.getCustomerService().getByCustomerCode(bean.getCustomerCode());
				bean.setCustomer(customer);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(customer!=null){
				BillingStatisticsData data = createStatDataByBillingBean(bean);
				if(temp.containsKey(customer.getId())){//已存在
					BillingStatisticsData oldData = temp.get(customer.getId());
					BillingStatisticsData newData = oldData.add(data);
					temp.put(customer.getId(), newData);
				}else{
					temp.put(customer.getId(), data);
				}
			}
		}
		//存放返回结果
		List<BillingStatisticsData> datas = new ArrayList<BillingStatisticsData>();
		//将临时数据转化为页面显示数据
		datas.addAll(temp.values());
		
		return datas;
		
		
	}
	
	
	private BillingStatisticsData createStatDataByBillingBean(BillingBean bill){
		BillingStatisticsData data = new BillingStatisticsData();
		data.customer = bill.getCustomer();
		data.actualCost = bill.getActualCost();
		data.calcCost = bill.getTotalCost();
		try{
			ConferenceBean conf = serviceFacade.getConferenceService().get(bill.getConference().getId());
			data.totaltime = conf.getEndTime().getTime() - conf.getStartTime().getTime();
		}catch(Exception e){
			data.totaltime = 0L;
			e.printStackTrace();
		}
		return data;
	}
}
