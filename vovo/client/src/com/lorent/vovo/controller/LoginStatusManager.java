/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.vovo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.lorent.vovo.dto.LoginStatus;
import com.lorent.vovo.util.Constants;

/**
 *
 * @author Administrator
 */
public class LoginStatusManager {
    
    public static List<LoginStatus> getLoginStatusList(){
    	java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/lorent/vovo/resource/i18n/view");
//        ResourceBundle bundle = ResourceManager.getResourceBundle();
        List<LoginStatus> list = new ArrayList<LoginStatus>();
        list.add(new LoginStatus(bundle.getString("Login.STATUS_ONLINE"),Constants.STATUS_ONLINE));
        //list.add(new LoginStatus(bundle.getString("Login.STATUS_OFFLINE"),Constants.STATUS_OFFLINE));
        list.add(new LoginStatus(bundle.getString("Login.STATUS_AWAY"),Constants.STATUS_AWAY));
        list.add(new LoginStatus(bundle.getString("Login.STATUS_BUSY"),Constants.STATUS_BUSY));
        return list;
    }
    
    public static LoginStatus getLoginStatus(int status){
    	java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/lorent/vovo/resource/i18n/view");
    	LoginStatus ret = null;
    	if(status == Constants.STATUS_ONLINE){
    		ret = new LoginStatus(bundle.getString("Login.STATUS_ONLINE"),Constants.STATUS_ONLINE);
    	}else if(status == Constants.STATUS_AWAY){
    		ret = new LoginStatus(bundle.getString("Login.STATUS_AWAY"),Constants.STATUS_AWAY);
    	}else if(status == Constants.STATUS_BUSY){
    		ret = new LoginStatus(bundle.getString("Login.STATUS_BUSY"),Constants.STATUS_BUSY);
    	}
    	return ret;
    }
    
    public static int getStatusCodeByActionCommand(String actionCommand){
    	java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/lorent/vovo/resource/i18n/view");
        int statusCode = -1;
        if(actionCommand.equals(bundle.getString("Login.STATUS_ONLINE"))){
            statusCode = Constants.STATUS_ONLINE;
        }else if(actionCommand.equals(bundle.getString("Login.STATUS_AWAY"))){
            statusCode = Constants.STATUS_AWAY;
        }else if(actionCommand.equals(bundle.getString("Login.STATUS_BUSY"))){
            statusCode = Constants.STATUS_BUSY;
        }else if(actionCommand.equals(bundle.getString("Login.STATUS_OFFLINE"))){
            statusCode = Constants.STATUS_OFFLINE;
        }
        return statusCode;
    }
    
    public static String getStatusContentByCode(int statusCode){
        String content = "";
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/lorent/vovo/resource/i18n/view");
        if(statusCode == Constants.STATUS_AWAY){
            content = bundle.getString("Login.STATUS_AWAY");
        }else if(statusCode == Constants.STATUS_BUSY){
            content = bundle.getString("Login.STATUS_BUSY");
        }else if(statusCode == Constants.STATUS_OFFLINE){
            content = bundle.getString("Login.STATUS_OFFLINE");
        }else if(statusCode == Constants.STATUS_ONLINE){
            content = bundle.getString("Login.STATUS_ONLINE");
        }
        return content;
    }
    
}
