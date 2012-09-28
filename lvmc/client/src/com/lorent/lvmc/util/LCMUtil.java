/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.util;

import com.lorent.common.dto.LCMRoleDto;
import java.net.URL;
import java.util.Map;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 *
 * @author jack
 */
public class LCMUtil {
    private static XmlRpcClient getClient()throws Exception{
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        String xmlrpcUrl = "http://" + ConfigUtil.getProperty("serverIP") + ConfigUtil.getProperty("lcm.xmlrpc");
        config.setServerURL(new URL(xmlrpcUrl));
        config.setEnabledForExtensions(true);
//          config.setConnectionTimeout(60 * 1000);
          config.setReplyTimeout(20 * 1000);
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);
        return client;
    }
    
    public static LCMRoleDto getMyRoleAndPermission(String confno, String lccno)throws Exception{
        LCMRoleDto data = (LCMRoleDto)getClient().execute("lcmPerm.getMyRoleAndPermission", new Object[]{confno, lccno});
        return data;
    }
    
    public static Map<String, LCMRoleDto> getConfUserRole(String confno, String[] lccnos) throws Exception {
        Map<String, LCMRoleDto> ret = (Map<String, LCMRoleDto>)getClient().execute("lcmPerm.getConfUserRole", new Object[]{confno, lccnos});
        return ret;
    }
    
    public static String getWhiteBoardNumber(String confno) throws Exception {
        String snum=(String) getClient().execute("lcmPerm.getWhiteBoardNumber",new Object[]{confno});
        return snum;
    }
    
    public static void main(String[] args)throws Exception {
        String m=LCMUtil.getWhiteBoardNumber("416997");
//        LCMRoleDto ms=LCMUtil.getMyRoleAndPermission("416997","41742");
//        System.out.println("================"+ms);
        System.out.println("================"+m);
    }
}
