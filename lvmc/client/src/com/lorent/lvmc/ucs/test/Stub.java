package com.lorent.lvmc.ucs.test;

import java.util.Scanner;

import com.lorent.lvmc.ucs.Start;

public class Stub {
	private static final String dllPort = "20001";
	private static final String exePort = "20002";
	private static final String serverIP = "10.168.250.12";
	private static final int serverPort = 5060;
	private static final String username = "33013";
	private static final String passwd = "123456";
	private static final String callusername = "33039";
	private static final String confno = "900003";
	
	
	
	public static void main(String[] args) throws Exception{
		StubClient.init(Integer.parseInt(exePort));
		StubServer.init(Integer.parseInt(dllPort));
		new Start().execute(new String[]{dllPort, exePort});
		
		String info = 				
			"1.初始化组件\n" +
			"2.设置sipserver\n" +
			"3.设置账号\n" +
			"4.设置密码\n" +
			"5.注册\n" +
			"6.是否注册\n" +
			"7.注销\n" +
			"8.反注册\n" +
			"9.单呼\n" +
			"10.挂机\n" +
			"11.接听\n" +
			"12.创建会议\n" +
			"13.删除会议\n" +
			"14.进入会议\n" +
			"15.获取设备列表\n" +
			"16.应答会议\n" +
			"99.1-5\n" +
			"";
		System.out.println(info);
		Scanner sc = new Scanner(System.in);
//		int sel = sc.nextInt();
		int sel = 99;
		while(sel != 0){
			if(sel == 1){
				StubClient.init();
			}else if(sel == 2){
				StubClient.setsipserver(serverIP, serverPort);
			}else if(sel == 3){
				StubClient.setusername(username);
			}else if(sel == 4){
				StubClient.setpassword(passwd);
			}else if(sel == 5){
				StubClient.register();
			}else if(sel == 6){
				StubClient.isregister();
			}else if(sel == 7){
				StubClient.uninit();
			}else if(sel == 8){
				StubClient.unregister();
			}else if(sel == 9){
				StubClient.call(callusername);
			}else if(sel == 10){
				StubClient.hangup();
			}else if(sel == 11){
				StubClient.answercall(callusername);
			}else if(sel == 12){
				StubClient.createconf(username, confno);
			}else if(sel == 13){
				StubClient.removeconf(username, confno);
			}else if(sel == 14){
				StubClient.callmeeting(confno);
			}else if(sel == 15){
				StubClient.getDevList();
			}else if(sel == 16){
				StubClient.answermeeting(confno);
			}else if(sel == 99){
				StubClient.init();
				StubClient.setsipserver(serverIP, serverPort);
				StubClient.setusername(username);
				StubClient.setpassword(passwd);
				StubClient.register();
				StubClient.setconfserverip(serverIP);
				StubClient.callmeeting(confno);
			}
			System.out.println(info);
			sel = sc.nextInt();
		}
		System.exit(0);
	}
}
