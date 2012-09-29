package com.lorent.astpack.testcase;

import static org.junit.Assert.*;

import java.net.ServerSocket;
import java.util.Map;

import javax.print.attribute.standard.Severity;
import javax.sound.sampled.Port;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lorent.astpack.service.AstpackSerivceFactory;
import com.lorent.astpack.service.IConfService;
import com.lorent.astpack.service.impl.ConfService;

public class ConfServiceTestCase {

	private String ipAddr;
	private String managerName;
	private String managerPsw;
	private String confno;
	private String[] lccnos;
	private String PlayMediaInConf_mediaPath;
	private String JoinExtenSpyInConf_lccnofrom,JoinExtenSpyInConf_lccnoto,JoinExtenSpyNotInConf_lccnofrom,JoinExtenSpyNotInConf_lccnoto2,JoinExtenSpyNotInConf_lccnoto1;
	private String forceDisLccNo2,forceDisLccNo1;
	private String JoinExtenSpyInConf_Prefix;
	private String listenlccno, listentargetlccno1,listentargetlccno2,listenprefix;
	private String callLccnoFrom, callLccnoTo;
	private String answerlccnoHost, answerlccnoFrom,answerlccnoTo;
	private String switchlccnoHost,switchlccnoFrom,switchlccnoTo;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	private String AddUsersInConf_confno;
	private String[] AddUsersInConf_lccnos;
	private String PlayMediaInConf_confno;
	private String JoinExtenSpyNotInConf_Prefix;
	private static Logger log = Logger.getLogger(ConfServiceTestCase.class);
	private int port = 5038;
	@Before
	public void setUp() throws Exception {
		ipAddr = "10.168.100.201";
		managerName = "manager";
		managerPsw = "123";
		port = 5038;
		//---------------testAddUsersInConf
		AddUsersInConf_confno = "51089";
		AddUsersInConf_lccnos = new String[]{"5305"};
		//confno = "15549";
		//lccnos = new String[]{"1501"};
		//---------------testPlayMediaInConf_MoviePlay
		PlayMediaInConf_confno = "15549";
		PlayMediaInConf_mediaPath = "/media/movie.mp4";
		//---------------JoinExtenSpyInConf
		JoinExtenSpyInConf_lccnofrom = "1000000000002";
		JoinExtenSpyInConf_lccnoto = "1000000000001";
		JoinExtenSpyInConf_Prefix = "888888";
		//---------------JoinExtenSpyNotInConf
		//强插,非会议
		JoinExtenSpyNotInConf_lccnofrom = "1069";
		JoinExtenSpyNotInConf_lccnoto1 = "1771";
		JoinExtenSpyNotInConf_lccnoto2 = "1666";
		JoinExtenSpyNotInConf_Prefix="888888";
		//---------------testForceDisconnect
		forceDisLccNo1 = "1069";
		forceDisLccNo2 = "1771";
		//---------------testListenExtenSpy
		listenlccno="1666";
		listentargetlccno1="1069";
		listentargetlccno2="1771";
		listenprefix="88888";
		//---------------testCall
		callLccnoFrom = "1069";
		callLccnoTo = "1771";
		//---------------testAnswerNotInConf
		//代答,测试需要手动,from->to,host接
		answerlccnoHost = "1771";//用lcc打开
		answerlccnoFrom = "1346";//用lcc打开
		answerlccnoTo="1069";//用lcc打开
		//---------------testAnswerSwitch
		//转接,测试需要手动,from->host,host让to接
		switchlccnoHost = "1346";//用lcc打开
		switchlccnoFrom = "1069";//用lcc打开
		switchlccnoTo = "1771";//用lcc打开

	}

	@After
	public void tearDown() throws Exception {
	}

	
	
	@Test
	public void testAddUsersInConf() {
		log.info("testAddUsersInConf");
		IConfService service = AstpackSerivceFactory.getInstance(ipAddr);
		boolean condition =false;
		try {
			Map addUsersInConf = service.addUsersInConf(AddUsersInConf_confno, AddUsersInConf_lccnos);
			condition = true;
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception");
		}
	}

	@Test
	public void testPlayMediaInConf_MoviePlay() {
		log.info("testPlayMediaInConf_MoviePlay");
		IConfService service = AstpackSerivceFactory.getInstance(ipAddr);
		try {
			Map playMediaInConf = service.playMediaInConf(PlayMediaInConf_confno,PlayMediaInConf_mediaPath , true);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception");
		}
	}

	@Test
	public void testPlayMediaInConf_MovieStop() {
		log.info("testPlayMediaInConf_MovieStop");
		IConfService service = AstpackSerivceFactory.getInstance(ipAddr);
		try {
			Map playMediaInConf = service.playMediaInConf(PlayMediaInConf_confno, PlayMediaInConf_mediaPath, false);
		} catch (Exception e) {
			
			e.printStackTrace();
			fail("Exception");
		}
	}
	
	@Test
	public void testJoinExtenSpyInConf(){
		log.info("强插(会议) testJoinExtenSpyInConf");
		IConfService service = AstpackSerivceFactory.getInstance(ipAddr);
		try {
			Map playMediaInConf = service.joinExtenSpy(JoinExtenSpyInConf_lccnofrom, JoinExtenSpyInConf_lccnoto,JoinExtenSpyInConf_Prefix);
		} catch (Exception e) {
			
			e.printStackTrace();
			fail("Exception");
		}
	}
	
	@Test
	public void testJoinExtenSpyNotInConf(){
		log.info("强插(非会议) testJoinExtenSpyNotInConf");
		IConfService service = AstpackSerivceFactory.getInstance(ipAddr, managerName, managerPsw);
		try {
			Map call = service.call(JoinExtenSpyNotInConf_lccnoto1, JoinExtenSpyNotInConf_lccnoto2);
			log.info("called");
			Thread.sleep(2000);
			Map playMediaInConf = service.joinExtenSpy(JoinExtenSpyNotInConf_lccnofrom, JoinExtenSpyNotInConf_lccnoto2,JoinExtenSpyNotInConf_Prefix);
			log.info("spyed");
			Thread.sleep(8000);
			service.forceDisconnect(JoinExtenSpyNotInConf_lccnofrom);
			service.forceDisconnect(JoinExtenSpyNotInConf_lccnoto1);
			service.forceDisconnect(JoinExtenSpyNotInConf_lccnoto2);
		} catch (Exception e) {
			
			e.printStackTrace();
			fail("Exception");
		}
	}
	
	@Test
	public void testForceDisconnect(){
		log.info("testForceDisconnect");
		IConfService service = AstpackSerivceFactory.getInstance(ipAddr, managerName, managerPsw);
		try {
			service.call(forceDisLccNo1, forceDisLccNo2);
			Thread.sleep(2000);
			service.forceDisconnect(forceDisLccNo2);
			service.forceDisconnect(forceDisLccNo1);
		} catch (Exception e) {
			
			e.printStackTrace();
			fail("Exception");
		}
	}
	
	@Test
	public void testListenExtenSpy(){
		log.info("testListenExtenSpy");
		IConfService service = AstpackSerivceFactory.getInstance(ipAddr, managerName, managerPsw);
		try {
			service.call(listentargetlccno1, listentargetlccno2);
			Thread.sleep(2000);
			service.listenExtenSpy(listenlccno, listentargetlccno1,listenprefix);
			Thread.sleep(5000);
			service.forceDisconnect(listentargetlccno1);
			service.forceDisconnect(listentargetlccno2);
			service.forceDisconnect(listenlccno);
		} catch (Exception e) {
			
			e.printStackTrace();
			fail("Exception");
		}
	}
	
	@Test
	public void testCall(){
		log.info("testCall");
		IConfService service = AstpackSerivceFactory.getInstance(ipAddr, managerName, managerPsw);
		try {
			Map playMediaInConf = service.call(callLccnoFrom, callLccnoTo);
			Thread.sleep(5000);
			service.forceDisconnect(callLccnoFrom);
			service.forceDisconnect(callLccnoTo);
		} catch (Exception e) {
			
			e.printStackTrace();
			fail("Exception");
		}
	}
	
	@Test
	public void testAnswerSwitch(){
		log.info("testAnswerSwitch");
		IConfService service = AstpackSerivceFactory.getInstance(ipAddr, managerName, managerPsw);
		try {
			service.call(switchlccnoFrom, switchlccnoHost);
			Thread.sleep(4000);
			service.answer(switchlccnoTo, switchlccnoHost);
		} catch (Exception e) {
			
			e.printStackTrace();
			fail("Exception");
		}
	}
	
	@Test
	public void testAnswerNotInConf(){
		log.info("testAnswerNotInConf");
		IConfService service = AstpackSerivceFactory.getInstance(ipAddr, managerName, managerPsw);
		try {
			service.call(answerlccnoFrom, answerlccnoTo);
//			service.call(answerlccnoTo, answerlccnoFrom);
			Thread.sleep(4000);
			service.answer(answerlccnoHost,answerlccnoTo);
		} catch (Exception e) {
			
			e.printStackTrace();
			fail("Exception");
		}
	}
	

	
	/*
	@Test
	public void testAnswerInConf(){
		IConfService service = AstpackSerivceFactory.getInstance(ipAddr);
		try {
			//Map call = service.call("10003", "10001");
			service.addUsersInConf(confno, lccnos);
			//Thread.sleep(2000);
			
			Map playMediaInConf = service.answer(answerlccnoHost, answerlccnoby);
		} catch (Exception e) {
			
			e.printStackTrace();
			fail("Exception");
		}
	}
	*/
	

	
	/*
	@Test
	public void testMeetMeMute(){
		IConfService service = AstpackSerivceFactory.getInstance(ipAddr);
		try {
			Map playMediaInConf = service.meetMeMute("", "");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception");
		}
	}
	*/
}
