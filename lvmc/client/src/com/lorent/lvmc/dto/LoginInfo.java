/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.dto;

/**
 *
 * @author jack
 */
public class LoginInfo {

    private String username;
    private String confno;
    private String serverIP;
    private String serverPort;
    private String passWord;
    private String nickName;
    private String conferenceTitle;
    private boolean autoLogin;
    private boolean savePasswd;
    
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

	public void setConfno(String confno) {
		this.confno = confno;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	public String getConferenceTitle() {
        return conferenceTitle;
    }

    public void setConferenceTitle(String conferenceTitle) {
        this.conferenceTitle = conferenceTitle;
    }


    
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getConfno() {
        return confno;
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

    public LoginInfo(String username, String confno, String serverIP, String serverPort) {
        this.username = username;
        this.confno = confno;
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    public LoginInfo(String username, String passWord, String confno, String serverIP, String serverPort) {
        this.username = username;
        this.confno = confno;
        this.passWord = passWord;
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }
    
    public LoginInfo(){
    	
    }
}
