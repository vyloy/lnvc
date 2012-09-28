package com.lorent.model;

import java.math.BigDecimal;
import java.util.Date;

public class RatesBean extends BaseModel implements AbstractAclModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ratesName;
	private Date startTime;
	private Date endTime;
	private Integer ratesType;
	private BigDecimal ratesTarriff;
	private Integer ratesStatus;
	
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getRatesName() {
		return ratesName;
	}
	public void setRatesName(String ratesName) {
		this.ratesName = ratesName;
	}
	public Integer getRatesType() {
		return ratesType;
	}
	public void setRatesType(Integer ratesType) {
		this.ratesType = ratesType;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public BigDecimal getRatesTarriff() {
		return ratesTarriff;
	}
	public void setRatesTarriff(BigDecimal ratesTarriff) {
		this.ratesTarriff = ratesTarriff;
	}
	public Integer getRatesStatus() {
		return ratesStatus;
	}
	public void setRatesStatus(Integer ratesStatus) {
		this.ratesStatus = ratesStatus;
	}
}
