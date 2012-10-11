package com.lorent.test;

import com.lorent.common.event.JNIEvent;
import com.lorent.common.event.JNIEventAdapter;
import com.lorent.util.LCCUtil;

public class TestLCCUtil {
	
	public static void main(String[] args) {
		new TestLCCUtil().execute();
	}
	
	String username = ConfigUtil.getProperty("username");
	String passwd = ConfigUtil.getProperty("passwd");
	String confno = ConfigUtil.getProperty("confno");
	String serverIP = ConfigUtil.getProperty("serverIP");
	int port = ConfigUtil.getIntProperty("port");
	
	public void execute(){
		
		LCCUtil.getInstance().addEventListener(new MyListener());
//		LCCUtil.getInstance().register(username, passwd, serverIP, port);
		LCCUtil.getInstance().register(username, serverIP, port+"", passwd, 0);
	}
	
	public class MyListener extends JNIEventAdapter{
		@Override
		public void registerOkCallBack(JNIEvent event) {
			
		}
	}
}
