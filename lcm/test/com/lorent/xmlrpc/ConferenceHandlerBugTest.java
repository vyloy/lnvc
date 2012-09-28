package com.lorent.xmlrpc;

import static org.junit.Assert.assertNotNull;

import java.net.URL;
import java.util.List;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lorent.dto.XmlrpcConf;


public class ConferenceHandlerBugTest {

	
	private String lccno1;
	private String lccno2;
	private String lccno3;
	private String lccno4;
	private String lccno5;
	private String lccno6;
	String xmlrpcUrl ;
	@Before
	public void setUp() throws Exception {
		xmlrpcUrl = "http://10.168.100.202:6080/lcmRPC";
		//----------自动接听的号码-----------
		lccno1 = "1069";
		lccno2 = "1771";
		lccno3 = "1761";
		lccno4 = "1666";
		lccno5 = "1501";
		lccno6 = "1277";
	}
	
	@Before
	public void beforeTest(){
		removeAllConf();
	}
	
	@After
	public void afterTest(){
//		removeAllConf();
	}
	
	private void removeAllConf(){
		try {
			List<XmlrpcConf> conflist = McuXmlrpc.getConfList(xmlrpcUrl);
			for(XmlrpcConf conf : conflist){
				McuXmlrpc.removeConf(xmlrpcUrl, conf.getConfno());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 调度台反映，两个不同的的客户端分别调用创建会议的xmlrpc，其中的后一个不能创建会议
	 * @throws Exception
	 */
	@Test
	public void testTwoClientCreateConf() throws Exception {
		XmlRpcClient client1,client2;
		XmlRpcClientConfigImpl config1,config2;
		
		String createConf_lccno;
		String[] createConf_lccnos ;
		int createConf_confLayout;
		String createConf_confQuality;
		int createConf_confid ;
		
		Object execute;
		
		config1 = new XmlRpcClientConfigImpl();
		config1.setServerURL(new URL("http://127.0.0.1:6090/lcm/lcmRpc"));
		client1 = new XmlRpcClient();
		client1.setConfig(config1);
		createConf_lccno = lccno1;
		createConf_lccnos= new String[]{lccno2,lccno3,lccno1};
		createConf_confLayout = 2;
		createConf_confQuality = "H264-CIF-Profile@384:30:1";
		createConf_confid = 0;
		
		execute = client1.execute("lcmConf.createConf",new Object[]{createConf_lccno,createConf_lccnos,createConf_confLayout,createConf_confQuality});
		Thread.sleep(2000);
		createConf_confid = Integer.parseInt(execute.toString());
		assertNotNull(execute);
		System.out.println("CreateConf1: "+createConf_confid);
		
		
		
		config2 = new XmlRpcClientConfigImpl();
		config2.setServerURL(new URL("http://127.0.0.1:6090/lcm/lcmRpc"));
		client2 = new XmlRpcClient();
		client2.setConfig(config2);
		createConf_lccno = lccno4;
		createConf_lccnos= new String[]{lccno5,lccno6};
		createConf_confLayout = 2;
		createConf_confQuality = "H264-CIF-Profile@384:30:1";
		createConf_confid = 0;
		
		execute = client2.execute("lcmConf.createConf",new Object[]{createConf_lccno,createConf_lccnos,createConf_confLayout,createConf_confQuality});
		Thread.sleep(2000);
		createConf_confid = Integer.parseInt(execute.toString());
		assertNotNull(execute);
		System.out.println("CreateConf2: "+createConf_confid);
		
		
	}
	
}
