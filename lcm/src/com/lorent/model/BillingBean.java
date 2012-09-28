package com.lorent.model;

import java.math.BigDecimal;

public class BillingBean extends BaseModel implements AbstractAclModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private ConferenceBean conference;
	private RatesBean rates;
	private Boolean isPay;
	private BigDecimal totalCost;
	private BigDecimal actualCost;
	private String confName;
	private String customerCode;
	private String customerName;
	private CustomerBean customer;
	
	public CustomerBean getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerBean customer) {
		this.customer = customer;
	}
	public Boolean getIsPay() {
		return isPay;
	}
	public void setIsPay(Boolean isPay) {
		this.isPay = isPay;
	}
	public RatesBean getRates() {
		return rates;
	}
	public void setRates(RatesBean rates) {
		this.rates = rates;
	}
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	public ConferenceBean getConference() {
		return conference;
	}
	public void setConference(ConferenceBean conference) {
		this.conference = conference;
	}
	public BigDecimal getActualCost() {
		return actualCost;
	}
	public void setActualCost(BigDecimal actualCost) {
		this.actualCost = actualCost;
	}
	public String getConfName() {
		return confName;
	}
	public void setConfName(String confName) {
		this.confName = confName;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
