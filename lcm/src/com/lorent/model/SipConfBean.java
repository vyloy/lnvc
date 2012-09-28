package com.lorent.model;

import java.math.BigDecimal;

public class SipConfBean extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String secret;
	private String context;
	private String host;
	private String nat;
	private String type;
	private String disAllow;
	private String allow;
	private String username;
	private Boolean nonExpired;
	private Boolean nonLocked;
	private String canreinvite;	
	
    private BigDecimal latitude;
	private Integer department_id;
	private Integer isuser;
	private BigDecimal longitude;
    private Boolean warning;
	private String monitorname;
	
	
	
	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	public Integer getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}
	public Integer getIsuser() {
		return isuser;
	}
	public void setIsuser(Integer isuser) {
		this.isuser = isuser;
	}
	public BigDecimal getLongitude() {
		return longitude;
	}
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	public Boolean getWarning() {
		return warning;
	}
	public void setWarning(Boolean warning) {
		this.warning = warning;
	}
	public String getMonitorname() {
		return monitorname;
	}
	public void setMonitorname(String monitorname) {
		this.monitorname = monitorname;
	}
	public String getAllow() {
		return allow;
	}
	public void setAllow(String allow) {
		this.allow = allow;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getDisAllow() {
		return disAllow;
	}
	public void setDisAllow(String disAllow) {
		this.disAllow = disAllow;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNat() {
		return nat;
	}
	public void setNat(String nat) {
		this.nat = nat;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Boolean getNonExpired() {
		return nonExpired;
	}
	public void setNonExpired(Boolean nonExpired) {
		this.nonExpired = nonExpired;
	}
	public Boolean getNonLocked() {
		return nonLocked;
	}
	public void setNonLocked(Boolean nonLocked) {
		this.nonLocked = nonLocked;
	}
	public String getCanreinvite() {
		return canreinvite;
	}
	public void setCanreinvite(String canreinvite) {
		this.canreinvite = canreinvite;
	}
}
