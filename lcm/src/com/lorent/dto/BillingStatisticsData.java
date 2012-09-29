package com.lorent.dto;

import java.math.BigDecimal;

import com.lorent.model.CustomerBean;

/**
 * 账单统计数据
 * @author jack
 *
 */
public class BillingStatisticsData {
	/**
	 * 账单所属客户
	 */
	public CustomerBean customer;
	/**
	 * 总时长
	 */
	public Long totaltime;
	/**
	 * 预计总费用
	 */
	public BigDecimal calcCost;
	/**
	 * 实际总费用
	 */
	public BigDecimal actualCost;
	
	public BillingStatisticsData add(BillingStatisticsData data){
		BillingStatisticsData temp = new BillingStatisticsData();
		temp.customer = this.customer;
		temp.totaltime = this.totaltime + data.totaltime;
		temp.calcCost = this.calcCost.add(data.calcCost);
		temp.actualCost = this.actualCost.add(data.actualCost);
		
		return temp; 
	}
}
