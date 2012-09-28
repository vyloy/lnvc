/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.vovo.dto;

/**
 *
 * @author Administrator
 */
public class LoginStatus {
    
    
    
    private String status;
    private int code;
    public LoginStatus(String status,int code){
        this.status = status;
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String toString(){
        return status;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if(obj == null || !(obj instanceof LoginStatus)){
    		return false;
    	}
    	LoginStatus temp = (LoginStatus)obj;
    	if(temp.getCode() == code && temp.getStatus().equals(status)){
    		return true;
    	}else{
    		return false;
    	}
    }
}
