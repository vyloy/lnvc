package com.lorent.model;
import java.io.Serializable;
public class CustomerBean extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String customerName;
	private String customerDesc;
	private McuServerBean mcuServer;
	private String customerCode;
	private Integer customerStatus;
	private String customerAddress;
	private String customerContact;
	private String customerPhone;
	private Integer customerUserlimit;
	//总体会议数量限制
	private Integer customerNolimit;
//	private Set<ConferenceNoBean>nos = new HashSet<ConferenceNoBean>();
	private RatesBean rates;
	//周期会议数目限制
	private Integer perConfNoLimit;
	//即时会议数目限制
	private Integer imdConfNoLimit;
	//预约会议数目限制
	private Integer appConfNoLimit;
	//会议室数目限制
	private Integer confMeetingNoLimit;
	//会议人数限制
	private Integer confPeopleLimit;

	public Integer getConfPeopleLimit() {
		return confPeopleLimit;
	}
	public void setConfPeopleLimit(Integer confPeopleLimit) {
		this.confPeopleLimit = confPeopleLimit;
	}
	public Integer getPerConfNoLimit() {
		return perConfNoLimit;
	}
	public void setPerConfNoLimit(Integer perConfNoLimit) {
		this.perConfNoLimit = perConfNoLimit;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public Integer getCustomerStatus() {
		return customerStatus;
	}
	public void setCustomerStatus(Integer customerStatus) {
		this.customerStatus = customerStatus;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerDesc() {
		return customerDesc;
	}
	public void setCustomerDesc(String customerDesc) {
		this.customerDesc = customerDesc;
	}
	public McuServerBean getMcuServer() {
		return mcuServer;
	}
	public void setMcuServer(McuServerBean mcuServer) {
		this.mcuServer = mcuServer;
	}
	@Override
	public boolean equals(Object obj) {
		if(this==obj)return true;
		if(this!=null&&obj==null)return false;
		if(!(obj instanceof CustomerBean) )return false;
		CustomerBean customer = (CustomerBean)obj;
		return customer.getCustomerCode().equals(this.customerCode);
	}
	@Override
	public int hashCode() {
		if(this==null||this.customerCode==null)return 0;
		return this.customerCode.hashCode();
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerContact() {
		return customerContact;
	}
	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public Integer getCustomerUserlimit() {
		return customerUserlimit;
	}
	public void setCustomerUserlimit(Integer customerUserlimit) {
		this.customerUserlimit = customerUserlimit;
	}
	public Integer getCustomerNolimit() {
		return customerNolimit;
	}
	public void setCustomerNolimit(Integer customerNolimit) {
		this.customerNolimit = customerNolimit;
	}
//	public Set<ConferenceNoBean> getNos() {
//		return nos;
//	}
//	public void setNos(Set<ConferenceNoBean> nos) {
//		this.nos = nos;
//	}
	public RatesBean getRates() {
		return rates;
	}
	public void setRates(RatesBean rates) {
		this.rates = rates;
	}
	public Integer getImdConfNoLimit() {
		return imdConfNoLimit;
	}
	public void setImdConfNoLimit(Integer imdConfNoLimit) {
		this.imdConfNoLimit = imdConfNoLimit;
	}
	public Integer getAppConfNoLimit() {
		return appConfNoLimit;
	}
	public void setAppConfNoLimit(Integer appConfNoLimit) {
		this.appConfNoLimit = appConfNoLimit;
	}
	public Integer getConfMeetingNoLimit() {
		return confMeetingNoLimit;
	}
	public void setConfMeetingNoLimit(Integer confMeetingNoLimit) {
		this.confMeetingNoLimit = confMeetingNoLimit;
	}
}
