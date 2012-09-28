package com.lorent.astpack.testcase;

import static org.junit.Assert.fail;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lorent.astpack.service.AstpackSerivceFactory;
import com.lorent.astpack.service.IConfService;
import com.lorent.astpack.service.impl.ConfService;


public class PeersTestCase {
	
	private String ipAddr;
	private String managerName;
	private String managerPsw;
	private int port = 5038;
	private String getStatusLccno1,getStatusLccno2,getStatusLccno3;
	private String getPeerPropertyLccno1,getPeerPropertyLccno2;
	
	
	private static Logger log = Logger.getLogger(PeersTestCase.class);
	@Before
	public void setUp() throws Exception {
		ipAddr = "10.168.100.202";
		managerName = "manager";
		managerPsw = "123";
		port = 5038;
		//---------------testGetPeerStatus
		getStatusLccno1 = "1069";
		getStatusLccno2 = "1666";
		//---------------testGetPeerProperty
		getPeerPropertyLccno1 = "1069";
		getPeerPropertyLccno2 = "1666";
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetPeerStatus() throws Exception {
		log.info("testGetPeerStatus");
		final IConfService service = AstpackSerivceFactory.getInstance(ipAddr,port, managerName, managerPsw);
		try {
//Runtime.getRuntime().exec("cmd /c " + path + "/startlcc.bat");
			service.getPeerStatus(getStatusLccno1);

			service.getPeerStatus(getStatusLccno2);

		} catch (Exception e) {
			
			e.printStackTrace();
			fail("Exception");
		}
	}
	
	@Test
	public void testGetPeerProperty() throws Exception {
		log.info("testgetPeerProperty");
		ConfService service = (ConfService) AstpackSerivceFactory.getInstance(ipAddr,port, managerName, managerPsw);
		
		try {
			String peerProperty = service.getPeerProperty(getPeerPropertyLccno1, "Expire");
			System.out.println(peerProperty);
			peerProperty = service.getPeerProperty(getPeerPropertyLccno2, "Expire");
			System.out.println(peerProperty);
		} catch (Exception e) {
			
			e.printStackTrace();
			fail("Exception");
		}
	}

}
