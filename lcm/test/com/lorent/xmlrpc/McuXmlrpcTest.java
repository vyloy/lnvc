package com.lorent.xmlrpc;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.lorent.dto.XmlrpcConf;
import com.lorent.dto.XmlrpcUser;
import com.lorent.test.BaseTest;
import com.lorent.util.AsteriskUtil;


public class McuXmlrpcTest extends BaseTest{

	private static final String xmlrpcUrl = "http://10.168.150.74:6080/lcmRPC";
	private static final String csIP = "10.168.150.74";
	private static final String lccno = "3200";
	
	
	@Before
	public void beforeTest(){
		removeAllConf();
	}
	
	@After
	public void afterTest(){
		removeAllConf();
	}
	
	@Test
	public void testCreateConference(){
		String confno = "3000";
		int layout = 1;
		int quality = 1;
		boolean flag = false;
		try {
			flag = McuXmlrpc.createConf(xmlrpcUrl, confno, layout, quality);
			Assert.assertEquals(true, flag);
			//test exist
			XmlrpcConf conf = getConfByNo(confno);
			flag = false;
			if(conf.getLayout()==layout && conf.getQuality()==quality){
				flag = true;
			}
			Assert.assertEquals(true, flag);
			
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void testUpdateConference(){
		String confno = "3000";
		int layout = 1;
		int quality = 1;
		int newLayout = 2;
		int newQuality = 2;
		boolean flag = false;
		try {
			McuXmlrpc.createConf(xmlrpcUrl, confno, layout, quality);
			McuXmlrpc.updateConf(xmlrpcUrl, confno, newLayout, newQuality);
			//test
			XmlrpcConf conf = getConfByNo(confno);
			flag = false;
			if(conf.getLayout()==newLayout && conf.getQuality()==newQuality){
				flag = true;
			}
			Assert.assertEquals(true, flag);
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void testRemoveConference(){
		String confno = "3000";
		int layout = 1;
		int quality = 1;
		try {
			McuXmlrpc.createConf(xmlrpcUrl, confno, layout, quality);
			McuXmlrpc.removeConf(xmlrpcUrl, confno);
			//test
			XmlrpcConf conf = getConfByNo(confno);
			Assert.assertNull(conf);
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void testAddUserInConf(){
		String confno = "3000";
		int layout = 1;
		int quality = 1;
		try {
			McuXmlrpc.createConf(xmlrpcUrl, confno, layout, quality);		
//			AsteriskUtil.addUserInConf(csIP, new String[]{lccno}, confno);
			//test
			List<XmlrpcUser> userList = McuXmlrpc.getConfUser(xmlrpcUrl, confno);
			XmlrpcUser user = getUserByLccno(userList, lccno);
			Assert.assertNotNull(user);
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void testRemoveConfUser(){
		String confno = "3000";
		int layout = 1;
		int quality = 1;
		try {
			McuXmlrpc.createConf(xmlrpcUrl, confno, layout, quality);
//			AsteriskUtil.addUserInConf(csIP, new String[]{lccno}, confno);
			McuXmlrpc.removeConfUser(xmlrpcUrl, confno, lccno);
			//test
			List<XmlrpcUser> userList = McuXmlrpc.getConfUser(xmlrpcUrl, confno);
			XmlrpcUser user = getUserByLccno(userList, lccno);
			Assert.assertNull(user);
		}catch(Exception e){
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	//----------------------help method----------------------------------
	
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
	
	private XmlrpcConf getConfByNo(String confno)throws Exception{
		List<XmlrpcConf> conflist = McuXmlrpc.getConfList(xmlrpcUrl);
		for(XmlrpcConf conf : conflist){
			if(conf.getConfno().equals(confno)){
				return conf;
			}
		}
		return null;
	}
	
	private XmlrpcUser getUserByLccno(List<XmlrpcUser> users, String lccno){
		for(XmlrpcUser user : users){
			if(user.getLccno().equals(lccno)){
				return user;
			}
		}
		return null;
	}
	
}
