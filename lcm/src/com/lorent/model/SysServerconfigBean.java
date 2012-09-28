package com.lorent.model;

public class SysServerconfigBean implements Cloneable{

	private Integer id;
	private String mcuport;
//	private String csport;
	private String cstimeout;
	private String fileport;
	private String filemaxlimit;
	private String desktopport;
	private String desktopport2;
	private String desktopport3;
	private String whiteport;
	private String whitenumber;
	private String emailport;
	private String openfireport;
	private String def0;
	private String def1;
	private String def2;
	private String def3;
	private String def4;
	private String def5;
//	private String csusername;
//	private String cspassword;
	private String allownfilestyle;
	private String desktoppassword;
	private String emailacount;
	private String emailpassword;
	private String serverip;
	
	private String mcuserverip;
	private String openfireip;
	private String openfiretimeout;
	private String customcodelength;
	private String confnolength;
	private String lcmconfnumber;
	private String lccnolength;
	private String emailserver;
	
	private String appserverip;
	private String appservermanager;
	private String appserverpwd;
	
	//原来mcu配置
	private String serverName;
	private String serverUrl;
	private String csIP;
	private String csPort;
	private String csUsername;
	private String csUserPasswd;
	
	private String serialport;
	
	public String getSerialport() {
		return serialport;
	}
	public void setSerialport(String serialport) {
		this.serialport = serialport;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getServerUrl() {
		return serverUrl;
	}
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	public String getCsIP() {
		return csIP;
	}
	public void setCsIP(String csIP) {
		this.csIP = csIP;
	}
	public String getCsPort() {
		return csPort;
	}
	public void setCsPort(String csPort) {
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
	public String getAppserverip() {
		return appserverip;
	}
	public void setAppserverip(String appserverip) {
		this.appserverip = appserverip;
	}
	public String getAppservermanager() {
		return appservermanager;
	}
	public void setAppservermanager(String appservermanager) {
		this.appservermanager = appservermanager;
	}
	public String getAppserverpwd() {
		return appserverpwd;
	}
	public void setAppserverpwd(String appserverpwd) {
		this.appserverpwd = appserverpwd;
	}
	public String getOpenfireip() {
		return openfireip;
	}
	public void setOpenfireip(String openfireip) {
		this.openfireip = openfireip;
	}
	public String getOpenfiretimeout() {
		return openfiretimeout;
	}
	public void setOpenfiretimeout(String openfiretimeout) {
		this.openfiretimeout = openfiretimeout;
	}
	public String getCustomcodelength() {
		return customcodelength;
	}
	public void setCustomcodelength(String customcodelength) {
		this.customcodelength = customcodelength;
	}
	public String getConfnolength() {
		return confnolength;
	}
	public void setConfnolength(String confnolength) {
		this.confnolength = confnolength;
	}
	public String getLcmconfnumber() {
		return lcmconfnumber;
	}
	public void setLcmconfnumber(String lcmconfnumber) {
		this.lcmconfnumber = lcmconfnumber;
	}
	public String getLccnolength() {
		return lccnolength;
	}
	public void setLccnolength(String lccnolength) {
		this.lccnolength = lccnolength;
	}
	public String getEmailserver() {
		return emailserver;
	}
	public void setEmailserver(String emailserver) {
		this.emailserver = emailserver;
	}
	public String getMcuserverip() {
		return mcuserverip;
	}
	public void setMcuserverip(String mcuserverip) {
		this.mcuserverip = mcuserverip;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMcuport() {
		return mcuport;
	}
	public void setMcuport(String mcuport) {
		this.mcuport = mcuport;
	}
	
	public String getCstimeout() {
		return cstimeout;
	}
	public void setCstimeout(String cstimeout) {
		this.cstimeout = cstimeout;
	}
	public String getFileport() {
		return fileport;
	}
	public void setFileport(String fileport) {
		this.fileport = fileport;
	}
	public String getFilemaxlimit() {
		return filemaxlimit;
	}
	public void setFilemaxlimit(String filemaxlimit) {
		this.filemaxlimit = filemaxlimit;
	}
	public String getDesktopport() {
		return desktopport;
	}
	public void setDesktopport(String desktopport) {
		this.desktopport = desktopport;
	}
	public String getDesktopport2() {
		return desktopport2;
	}
	public void setDesktopport2(String desktopport2) {
		this.desktopport2 = desktopport2;
	}
	public String getDesktopport3() {
		return desktopport3;
	}
	public void setDesktopport3(String desktopport3) {
		this.desktopport3 = desktopport3;
	}
	public String getWhiteport() {
		return whiteport;
	}
	public void setWhiteport(String whiteport) {
		this.whiteport = whiteport;
	}
	public String getWhitenumber() {
		return whitenumber;
	}
	public void setWhitenumber(String whitenumber) {
		this.whitenumber = whitenumber;
	}
	public String getEmailport() {
		return emailport;
	}
	public void setEmailport(String emailport) {
		this.emailport = emailport;
	}
	public String getOpenfireport() {
		return openfireport;
	}
	public void setOpenfireport(String openfireport) {
		this.openfireport = openfireport;
	}
	public String getDef0() {
		return def0;
	}
	public void setDef0(String def0) {
		this.def0 = def0;
	}
	public String getDef1() {
		return def1;
	}
	public void setDef1(String def1) {
		this.def1 = def1;
	}
	public String getDef2() {
		return def2;
	}
	public void setDef2(String def2) {
		this.def2 = def2;
	}
	public String getDef3() {
		return def3;
	}
	public void setDef3(String def3) {
		this.def3 = def3;
	}
	public String getDef4() {
		return def4;
	}
	public void setDef4(String def4) {
		this.def4 = def4;
	}
	public String getDef5() {
		return def5;
	}
	public void setDef5(String def5) {
		this.def5 = def5;
	}
	
	public String getAllownfilestyle() {
		return allownfilestyle;
	}
	public void setAllownfilestyle(String allownfilestyle) {
		this.allownfilestyle = allownfilestyle;
	}
	public String getDesktoppassword() {
		return desktoppassword;
	}
	public void setDesktoppassword(String desktoppassword) {
		this.desktoppassword = desktoppassword;
	}
	public String getEmailacount() {
		return emailacount;
	}
	public void setEmailacount(String emailacount) {
		this.emailacount = emailacount;
	}
	public String getEmailpassword() {
		return emailpassword;
	}
	public void setEmailpassword(String emailpassword) {
		this.emailpassword = emailpassword;
	}
	public String getServerip() {
		return serverip;
	}
	public void setServerip(String serverip) {
		this.serverip = serverip;
	}

	
	
}
