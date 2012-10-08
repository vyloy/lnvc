package com.lorent.ucs.sync;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import qflag.ucstar.webservice.bean.UcstarDept;
import qflag.ucstar.webservice.bean.UcstarUser;

public class Test extends WebserviceManager {
	public static void main(String[] args) throws MalformedURLException, ServiceException, RemoteException {
		Test t = new Test();
		t.bindWebService("192.168.2.30", "9090");
		UcstarDept[] user = t.getWebservice().getDeptList("0");
		UcstarUser[] result = t.getWebservice().getUserList(user[0].getDeptid());
		String password = result[0].getPassword();
		System.out.println(user);
	}
}
