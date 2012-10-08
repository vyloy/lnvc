package com.lorent.ucs.sync;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.rpc.ServiceException;

import qflag.ucstar.webservice.UcstarWebservice;
import qflag.ucstar.webservice.UcstarWebserviceServiceLocator;

public class WebserviceManager {
	protected UcstarWebservice ucstarwebservice = null;
	protected String ipaddress;
	protected String port;
	protected String ucstarUrl;

	public void bindWebService(String _ipaddress, String _port)
			throws MalformedURLException, ServiceException {
		this.ipaddress = _ipaddress;
		this.port = _port;
		this.ucstarUrl = "http://" + this.ipaddress + ":" + this.port
				+ "/services/UcstarWebservice?WSDL";
		this.connect();
	}

	public void bindWebService(String _ucstarUrl) throws MalformedURLException,
			ServiceException {
		URL u = new URL(_ucstarUrl);
		this.ipaddress = u.getHost();
		this.port = "" + u.getPort();
		this.ucstarUrl = _ucstarUrl;
		this.connect();
	}

	protected void connect() throws ServiceException, MalformedURLException {
		UcstarWebserviceServiceLocator service = new UcstarWebserviceServiceLocator();
		URL url = null;
		url = new java.net.URL(ucstarUrl);
		if (url != null) {
			ucstarwebservice = service.getUcstarWebservice(url);
		}
	}

	public UcstarWebservice getWebservice() {
		return this.ucstarwebservice;
	}

	protected void setWebservice(UcstarWebservice _service) {
		this.ucstarwebservice = _service;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}