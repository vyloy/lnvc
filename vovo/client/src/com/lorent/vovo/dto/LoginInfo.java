/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.vovo.dto;

import java.awt.image.BufferedImage;

/**
 *
 * @author jack
 */
public class LoginInfo {

    private String username;
    private String serverIP;
    private String serverPort;
    private String passWord;
    private String nickName;
    private boolean autoLogin;
    private boolean savePasswd;
    private BufferedImage image;
    private int status = -1;
    
    public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public boolean isAutoLogin() {
		return autoLogin;
	}

	public void setAutoLogin(boolean autoLogin) {
		this.autoLogin = autoLogin;
	}

	public boolean isSavePasswd() {
		return savePasswd;
	}

	public void setSavePasswd(boolean savePasswd) {
		this.savePasswd = savePasswd;
	}

	public void setUsername(String username) {
		this.username = username;
	}



	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

    
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getServerIP() {
        return serverIP;
    }

    public String getServerPort() {
        return serverPort;
    }

    public String getUsername() {
        return username;
    }

    public LoginInfo(String username, String serverIP, String serverPort) {
        this.username = username;
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    public LoginInfo(String username, String passWord, String serverIP, String serverPort) {
        this.username = username;
        this.passWord = passWord;
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }
    
    public LoginInfo(){
    	
    }

	@Override
	public String toString() {
		return this.username;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
    
    
}
