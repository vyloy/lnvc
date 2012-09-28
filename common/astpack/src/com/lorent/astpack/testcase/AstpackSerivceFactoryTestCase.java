package com.lorent.astpack.testcase;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lorent.astpack.service.AstpackSerivceFactory;
import com.lorent.astpack.service.IConfService;

public class AstpackSerivceFactoryTestCase {

	private String ipAddr;
	private String managerName;
	private String managerPsw;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ipAddr = "10.168.250.12";
		managerName = "manager";
		managerPsw = "123";
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNewInstanceOneParams() {
		IConfService IConfService = AstpackSerivceFactory.getInstance(ipAddr);
		assertNotNull(IConfService);
	}

	@Test
	public void testNewInstanceThreeParams() {
		
		IConfService newInstance = AstpackSerivceFactory.getInstance(ipAddr, managerName, managerPsw);
		assertNotNull(newInstance);
	}

}
