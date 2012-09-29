package com.lorent.xmlrpc;

import static org.junit.Assert.*;

import java.net.URL;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lorent.dto.XmlrpcConf;
import com.lorent.test.BaseTest;

public class ConferenceHandlerTest  {

	

	@Before
	public void beforeTest(){
		removeAllConf();
	}
	
	@After
	public void afterTest(){
		removeAllConf();
	}
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	XmlRpcClientConfigImpl config;
	String createConf_lccno;
	String[] createConf_lccnos;
	int createConf_confLayout;
	String createConf_confQuality;
	XmlRpcClient client;
	int createConf_confid;
	int updateConf_confLayout;
	int updateConf_confid;
	int addConfUser_confid;
	String[] addConfUser_lccnos;
	int removeConfUser_confid;
	String[] removeConfUser_lccnos;
	int removeConf_confid;
	
	//-------------
	boolean testCreateConf = true;
	boolean testUpdateConf = true;
	boolean testAddConfUser = true;
	boolean testRemoveConfUser =true;
	boolean testRemoveConf = true;
	String xmlrpcUrl ;

	String updateConf_createConf_lccno;
	String[] updateConf_createConf_lccnos;
	int updateConf_createConf_confLayout;
	String updateConf_createConf_confQuality;
	
	String addConfUser_createConf_lccno;
	String[] addConfUser_createConf_lccnos;
	int addConfUser_createConf_confLayout;
	String addConfUser_createConf_confQuality;
	
	String removeConfUser_createConf_lccno;
	String[] removeConfUser_createConf_lccnos;
	int removeConfUser_createConf_confLayout;
	String removeConfUser_createConf_confQuality;
	
	String removeConf_createConf_lccno;
	String[] removeConf_createConf_lccnos;
	int removeConf_createConf_confLayout;
	String removeConf_createConf_confQuality;
	
	String call_lccno,call_lccnotarget;
	String joinExtenSpy_lccnoHost,joinExtenSpy_lccnoTo1,joinExtenSpy_lccnoTo2;
	String switchAnswer_host,switchAnswer_from,switchAnswer_to;	
	String getPeerStatus1,getPeerStatus2;
	
	String lccno1,lccno2,lccno3,lccno4,lccno5;
	String lccno_1,lccno_2,lccno_3,lccno_4,lccno_5;
	private String listenExtenSyp_lccnoHost;
	private String listenExtenSyp_lccnoTo1;
	private String listenExtenSyp_lccnoTo2;
	
	@Before
	public void setUp() throws Exception {
		testCreateConf = true;
		testUpdateConf = true;
		testAddConfUser = true;
		testRemoveConfUser =true;
		testRemoveConf = true;
		xmlrpcUrl = "http://10.168.100.202:6080/lcmRPC";
		
		config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL("http://127.0.0.1:6090/lcm/lcmRpc"));
		client = new XmlRpcClient();
		client.setConfig(config);
		
		
		//----------自动接听的号码-----------
		lccno1 = "1069";
		lccno2 = "1771";
		lccno3 = "1761";
		lccno4 = "1666";
		lccno5 = "1501";
		//----------非自动接听的号码----------
		lccno_1 = "1326";
		lccno_2 = "1787";
		
		//----------testCreateConf-------------
		
		createConf_lccno = lccno1;
		createConf_lccnos = new String[]{lccno2,lccno4,lccno1,lccno3};
		createConf_confLayout = 2;
		createConf_confQuality = "H264-CIF-Profile@384:30:1";
		createConf_confid = 0;
		//-----------testUpdateConf--------------
		
		updateConf_createConf_lccno = lccno1;
		updateConf_createConf_lccnos = new String[]{lccno2,lccno4,lccno1};
		updateConf_createConf_confLayout = 0;
		updateConf_createConf_confQuality = "H264-CIF-Profile@384:30:1";
		
		updateConf_confLayout = 2;
		updateConf_confid = 0;
		//-----------testAddConfUser-------------
		
		addConfUser_createConf_lccno = lccno1;
		addConfUser_createConf_lccnos = new String[]{lccno2,lccno4,lccno1};
		addConfUser_createConf_confLayout = 2;
		addConfUser_createConf_confQuality = "H264-CIF-Profile@384:30:1";
		
		addConfUser_confid = 0;
		addConfUser_lccnos = new String[]{lccno3,lccno5};
		//-----------testRemoveConfUser--------------
		removeConfUser_createConf_lccno = lccno1;
		removeConfUser_createConf_lccnos = new String[]{lccno2,lccno4,lccno1,lccno3,lccno5};
		removeConfUser_createConf_confLayout = 2;
		removeConfUser_createConf_confQuality = "H264-CIF-Profile@384:30:1";
		
		removeConfUser_confid = 0;
		removeConfUser_lccnos = new String[]{lccno1,lccno2};
		//-----------testRemoveConf------------------
		removeConf_createConf_lccno = lccno1;
		removeConf_createConf_lccnos = new String[]{lccno2,lccno4,lccno1};
		removeConf_createConf_confLayout = 2;
		removeConf_createConf_confQuality = "H264-CIF-Profile@384:30:1";
		
		removeConf_confid = 0;
		//------------testCallAndForceDisconnect-----------------
		call_lccno = lccno1;
		call_lccnotarget = lccno2;
		//------------testJoinExtenSpy---------------
		joinExtenSpy_lccnoHost = lccno4;
		joinExtenSpy_lccnoTo1 = lccno1;
		joinExtenSpy_lccnoTo2 = lccno2;
		//------------testListenExtenSpy-------------
		listenExtenSyp_lccnoHost = lccno4;
		listenExtenSyp_lccnoTo1 = lccno1;
		listenExtenSyp_lccnoTo2 = lccno2;
		//------------testSwitchAnser----------------
		switchAnswer_host = lccno1;
		switchAnswer_from = lccno2;
		switchAnswer_to = lccno_2;
		//------------testGetPeerStauts-----------------
		getPeerStatus1 = lccno1;
		getPeerStatus2 = lccno2;
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testCreateConf(){
		try {
			
			Object execute = client.execute("lcmConf.createConf",new Object[]{createConf_lccno,createConf_lccnos,createConf_confLayout,createConf_confQuality});
			Thread.sleep(2000);
			createConf_confid = Integer.parseInt(execute.toString());
			assertNotNull(execute);
			System.out.println("testCreateConf: "+createConf_confid);
		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testUpdateConf() {
		try {
			
			Object execute0 = client.execute("lcmConf.createConf",new Object[]{updateConf_createConf_lccno,updateConf_createConf_lccnos,updateConf_createConf_confLayout,updateConf_createConf_confQuality});
			updateConf_confid = Integer.parseInt(execute0.toString());
			
			Thread.sleep(2000);
			Object execute = client.execute("lcmConf.updateConf", new Object[]{updateConf_confid, updateConf_confLayout});
			Thread.sleep(2000);
			assertNotNull(execute);
			System.out.println("testUpdateConf: "+execute+" confid:"+updateConf_confid);
			assertTrue(Boolean.parseBoolean(execute.toString()));
		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testAddConfUser() {
		try {
			Object execute0 = client.execute("lcmConf.createConf",new Object[]{addConfUser_createConf_lccno,addConfUser_createConf_lccnos,addConfUser_createConf_confLayout,addConfUser_createConf_confQuality});
			addConfUser_confid = Integer.parseInt(execute0.toString());
			
			Thread.sleep(2000);
			Object execute = client.execute("lcmConf.addConfUser", new Object[]{addConfUser_confid, addConfUser_lccnos});
			Thread.sleep(2000);
			assertNotNull(execute);
			System.out.println("testAddConfUser: "+execute+" confid:"+addConfUser_confid);
			assertTrue(Boolean.parseBoolean(execute.toString()));
		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}

	
	
	@Test
	public void testRemoveConf() {
		try {
			Object execute0 = client.execute("lcmConf.createConf",new Object[]{removeConf_createConf_lccno,removeConf_createConf_lccnos,removeConf_createConf_confLayout,removeConf_createConf_confQuality});
			removeConf_confid = Integer.parseInt(execute0.toString());
			
			Thread.sleep(5000);
			Object execute = client.execute("lcmConf.removeConf", new Object[]{removeConf_confid});
			Thread.sleep(1000);
			System.out.println("testRemoveConf: "+execute+" confid:"+removeConf_confid);
		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testJoinExtenSpy() {
		try {
			Object execute = client.execute("lcmConf.call",new Object[]{joinExtenSpy_lccnoTo1,joinExtenSpy_lccnoTo2});
			assertEquals(execute, true);
			Thread.sleep(2000);
			Object execute0 = client.execute("lcmConf.joinExtenSpy",new Object[]{joinExtenSpy_lccnoHost,joinExtenSpy_lccnoTo1});
			assertEquals(execute0, true);
			Thread.sleep(2000);
			execute = client.execute("lcmConf.forceDisconnect", new Object[]{joinExtenSpy_lccnoTo1});
			assertEquals(execute, true);
			execute = client.execute("lcmConf.forceDisconnect", new Object[]{joinExtenSpy_lccnoTo2});
			assertEquals(execute, true);
			execute = client.execute("lcmConf.forceDisconnect", new Object[]{joinExtenSpy_lccnoHost});
			assertEquals(execute, true);
		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testListenExtenSpy() throws Exception {
		try {
			Object execute = client.execute("lcmConf.call",new Object[]{listenExtenSyp_lccnoTo1,listenExtenSyp_lccnoTo2});
			assertEquals(execute, true);
			Thread.sleep(2000);
			Object execute0 = client.execute("lcmConf.listenExtenSpy",new Object[]{listenExtenSyp_lccnoHost,listenExtenSyp_lccnoTo1});
			assertEquals(execute0, true);
			Thread.sleep(2000);
			execute = client.execute("lcmConf.forceDisconnect", new Object[]{listenExtenSyp_lccnoTo1});
			assertEquals(execute, true);
			execute = client.execute("lcmConf.forceDisconnect", new Object[]{listenExtenSyp_lccnoTo2});
			assertEquals(execute, true);
			execute = client.execute("lcmConf.forceDisconnect", new Object[]{listenExtenSyp_lccnoHost});
			assertEquals(execute, true);
		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testSwitchAnswer() throws Exception {
		try {
			
			Thread thread = new Thread(){

				@Override
				public void run() {
					Object execute;
					try {
						Thread.sleep(3000);
						execute = client.execute("lcmConf.switchAnswer", new Object[]{switchAnswer_host,switchAnswer_to});
						assertEquals(execute, true);
						
						System.out.println(switchAnswer_host+"  switchAnswered...");
					} catch (XmlRpcException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				
			};
			thread.start();
			
			Object execute = client.execute("lcmConf.call",new Object[]{switchAnswer_from,switchAnswer_to});
			assertEquals(execute, true);
			Thread.sleep(2000);
			execute = client.execute("lcmConf.forceDisconnect", new Object[]{switchAnswer_host});
			assertEquals(execute, true);
			execute = client.execute("lcmConf.forceDisconnect", new Object[]{switchAnswer_to});
			assertEquals(execute, true);
		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testGetPeerStauts() throws Exception {
		try {
			Object execute = client.execute("lcmConf.call",new Object[]{getPeerStatus1,getPeerStatus2});
			assertEquals(execute, true);
			Thread.sleep(2000);
			Object execute2 = client.execute("lcmConf.getPeerStatus", new Object[]{getPeerStatus1});
			assertNotNull(execute2);
			Object execute3 = client.execute("lcmConf.getPeerStatus", new Object[]{getPeerStatus2});
			assertNotNull(execute3);
			execute = client.execute("lcmConf.forceDisconnect", new Object[]{getPeerStatus1});
			assertEquals(execute, true);
			execute = client.execute("lcmConf.forceDisconnect", new Object[]{getPeerStatus2});
			assertEquals(execute, true);
		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testCallAndForceDisconnect(){
		try {
			Object execute = client.execute("lcmConf.call",new Object[]{call_lccno,call_lccnotarget});
			assertEquals(execute, true);
			Thread.sleep(2000);
			Object execute2 = client.execute("lcmConf.forceDisconnect", new Object[]{call_lccno});
			assertEquals(execute2, true);
			Thread.sleep(2000);
			Object execute3 = client.execute("lcmConf.forceDisconnect", new Object[]{call_lccnotarget});
			assertEquals(execute3, true);
			Thread.sleep(2000);
		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testRemoveConfUser() {
		try {
			Object execute0 = client.execute("lcmConf.createConf",new Object[]{removeConfUser_createConf_lccno,removeConfUser_createConf_lccnos,removeConfUser_createConf_confLayout,removeConfUser_createConf_confQuality});
			removeConfUser_confid = Integer.parseInt(execute0.toString());
			System.out.println("removeConfUser_confid: "+removeConfUser_confid);
			Thread.sleep(8000);
			Object execute = client.execute("lcmConf.removeConfUser", new Object[]{removeConfUser_confid, removeConfUser_lccnos});
//			Thread.sleep(2000);
			assertNotNull(execute);
			System.out.println("testRemoveConfUser: "+execute+" confid:"+removeConfUser_confid);
			assertTrue(Boolean.parseBoolean(execute.toString()));
		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
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
	
}
