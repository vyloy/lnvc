/**
 * 
 */
package com.lorent.astpack.service;

import com.lorent.astpack.service.impl.ConfService;


public class AstpackSerivceFactory {
	
	private static String mIpAddr = "127.0.0.1";
	private static String mManagerName = "manager";
	private static String mManagerPsw = "123";
	private static int mPort = 5038;
	private static IConfService confService=null;
	
//	public static IConfService newInstance(String ipAddr){
//		return new ConfService(ipAddr);
//	}
//	
//	public static IConfService newInstance(String ipAddr,String managerName,String managerPsw){
//		return new ConfService(ipAddr, managerName, managerPsw);
//	}
	
	public static IConfService getInstance(String ipAddr){
		if(mIpAddr.equals(ipAddr)){
			if (confService == null) {
				confService = new ConfService(ipAddr, mPort, mManagerName, mManagerPsw);
			}
		}else{
			confService = new ConfService(ipAddr, mPort, mManagerName, mManagerPsw);
		}
		mIpAddr = ipAddr;
		return confService;
	}
	
	public static IConfService getInstance(String ipAddr,String managerName,String managerPsw){
		if(mIpAddr.equals(ipAddr) && mManagerName.equals(managerName) && mManagerPsw.equals(managerPsw)){
			if (confService == null) {
				confService =  new ConfService(ipAddr, mPort,managerName,managerPsw);
			}
		}else{
			confService =  new ConfService(ipAddr, mPort,managerName,managerPsw);
		}
		mIpAddr = ipAddr;
		mManagerName = managerName;
		mManagerPsw = managerPsw;
		return confService;
	}
	
	public static IConfService getInstance(String ipAddr, int port, String managerName,String managerPsw){
		if(mIpAddr.equals(ipAddr) && mPort == port && mManagerName.equals(managerName) && mManagerPsw.equals(managerPsw)){
			if (confService == null) {
				confService =  new ConfService(ipAddr, port,managerName,managerPsw);
			}
		}else{
			confService =  new ConfService(ipAddr, port,managerName,managerPsw);
		}
		mIpAddr = ipAddr;
		mPort = port;
		mManagerName = managerName;
		mManagerPsw = managerPsw;
		return confService;
	}
	
}
