package com.lorent.service;

import java.util.List;
import java.util.Map;

import com.lorent.dao.BillingDao;
import com.lorent.dto.BillingStatisticsData;
import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.BillingBean;

public interface BillingService extends IGenericService<BillingDao,BillingBean,Integer> {
	/**
	 * 改变账单的结算状态
	 * @param id
	 * @throws CustomSqlException
	 * @throws ArgsException
	 */
	void closeBilling(Integer id)throws CustomSqlException,ArgsException;
	/**
	 * 改变账单的结算状态
	 * @param ids
	 * @throws CustomSqlException
	 * @throws ArgsException
	 */
	void closeBilling(Integer[]ids)throws CustomSqlException,ArgsException;
	/**
	 * 按客户统计
	 * @param list 要统计的数据
	 * @return
	 */
	public List<BillingStatisticsData> statisticsByCustomer(List<BillingBean> list);
}
