package com.lorent.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class McuServerBean extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String serverName;
	private String serverIp;
	private String serverDesc;
	private Integer serverStatus;
	//mcu xmlrpc url
	private String serverUrl;
	private Set<McuMixerBean>mixers = new HashSet<McuMixerBean>();
//	private Set<CustomerBean>customers = new HashSet<CustomerBean>();
	private String rpcPass;
	private Integer customerSize;
	
	//cs连接参数
	private String csIP;
	private Integer csPort;
	private String csUsername;
	private String csUserPasswd;
	
	
	
	public String getCsIP() {
		return csIP;
	}
	public void setCsIP(String csIP) {
		this.csIP = csIP;
	}
	public Integer getCsPort() {
		return csPort;
	}
	public void setCsPort(Integer csPort) {
		this.csPort = csPort;
	}
	public String getCsUsername() {
		return csUsername;
	}
	public void setCsUsername(String csUsername) {
		this.csUsername = csUsername;
	}
	public String getCsUserPasswd() {
		return csUserPasswd;
	}
	public void setCsUserPasswd(String csUserPasswd) {
		this.csUserPasswd = csUserPasswd;
	}
	//	public Set<CustomerBean> getCustomers() {
//		return customers;
//	}
//	public void setCustomers(Set<CustomerBean> customers) {
//		this.customers = customers;
//	}
	public Set<McuMixerBean> getMixers() {
		return mixers;
	}
	public void setMixers(Set<McuMixerBean> mixers) {
		this.mixers = mixers;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getServerIp() {
		return serverIp;
	}
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	public String getServerDesc() {
		return serverDesc;
	}
	public void setServerDesc(String serverDesc) {
		this.serverDesc = serverDesc;
	}
	public Integer getServerStatus() {
		return serverStatus;
	}
	public void setServerStatus(Integer serverStatus) {
		this.serverStatus = serverStatus;
	}
//	@Override
//	public boolean equals(Object obj) {
//		if(obj==null)return false;
//		if(!(obj instanceof McuServerBean))return false;
//		McuServerBean server = (McuServerBean)obj;
//		return server.getServerName().equals(this.serverName)&&
//				server.getServerIp().equals(this.serverIp)&&
//				server.getServerStatus().equals(this.serverStatus);
//	}
//	@Override
//	public int hashCode() {
//		if(this==null)return 0;
//		return (this.serverName+this.serverIp).hashCode();
//	}
	public String getServerUrl() {
		return serverUrl;
	}
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	public String getRpcPass() {
		return rpcPass;
	}
	public void setRpcPass(String rpcPass) {
		this.rpcPass = rpcPass;
	}
	public Integer getCustomerSize() {
		return customerSize;
	}
	public void setCustomerSize(Integer customerSize) {
		this.customerSize = customerSize;
	}
}
