/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.util;

import java.net.URL;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 *
 * @author jack
 */
public class LCMUtil {
    private static XmlRpcClient getClient()throws Exception{
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        String xmlrpcUrl = "http://127.0.0.1:6090/lcm/lcmRpc";
        config.setServerURL(new URL(xmlrpcUrl));
        config.setEnabledForExtensions(true);
//          config.setConnectionTimeout(60 * 1000);
          config.setReplyTimeout(20 * 1000);
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);
        return client;
    }
    
    
    public static String getWhiteBoardNumber(String confno) throws Exception {
        String snum=(String) getClient().execute("lcmPerm.getWhiteBoardNumber",new Object[]{confno});
        return snum;
    }
    
}
