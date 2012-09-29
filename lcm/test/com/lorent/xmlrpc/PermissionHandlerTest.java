package com.lorent.xmlrpc;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.junit.Test;

import com.lorent.common.dto.LCMRoleDto;

public class PermissionHandlerTest {
	
	private XmlRpcClient client;
	private String url = "http://127.0.0.1:6090/lcm/lcmRpc";
	
	public PermissionHandlerTest() throws MalformedURLException{
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL(url));
		config.setEnabledForExtensions(true);
		client = new XmlRpcClient();
		client.setConfig(config);
	}
	
	@Test
	public void testGetMyPermission()throws Exception{
		LCMRoleDto data = (LCMRoleDto)client.execute("lcmPerm.getMyRoleAndPermission", new Object[]{"576281", "57920"});
		if(data == null){
			System.out.println("no permission");
			return;
		}
		System.out.println(data.getNames());
		System.out.println("nickname = " + data.getNickname());
		for(Iterator<String> it = data.getPermissions().keySet().iterator();it.hasNext();){
			String key = it.next();
			System.out.println("key = " + key + "&value = " + data.getPermissions().get(key));
		}
	}
	
	@Test
	public void testGetConfUserRole()throws Exception{
		String[] lccnos = new String[]{"57920"};
		Map<String, LCMRoleDto> data = (Map<String, LCMRoleDto>)client.execute("lcmPerm.getConfUserRole", new Object[]{"576597", lccnos});
		for(String lccno : lccnos){
			System.out.println("lccno = " + lccno + ":");
			System.out.println("nickname = " + data.get(lccno).getNickname());
			System.out.println(data.get(lccno).getNames());
		}
	}
}
