/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.util;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lorent.common.util.ParaUtil;
import com.lorent.lvmc.Launcher;
import com.lorent.lvmc.controller.ControllerFacade;
import com.lorent.lvmc.dto.LoginInfo;

/**
 *
 * @author Administrator
 */
public class LvmcUtil {
    private Logger log = Logger.getLogger(LvmcUtil.class);
    private LoginInfo loginInfo;
    
    public void setTopWindow(Component c){
        DataUtil.setValue(DataUtil.Key.TopWindow, c);
    }
    
    public LvmcUtil(){
        init();
    } 
    
    public void init(){
        boolean reloadfile = ConfigUtil.getBoolProperty("reloadfile", Boolean.TRUE);
        if(reloadfile){
            try {
                Launcher.reloadfile();
            } catch (Exception ex) {
                log.error("init", ex);
            }
        }
            
        String[] contextPaths = new String[]{"classpath:com/lorent/lvmc/config/applicationContext-*.xml"};
        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(contextPaths);
        DataUtil.setApplicationContext(context);
    }
    
    public void setLccRegisterFlag(Boolean flag){
        DataUtil.setValue(DataUtil.Key.LccRegisterFlag, flag);
        
    }
    
    public void setUserInfo(String userName,String passWord,String confNo){
        loginInfo = new LoginInfo(userName,passWord,confNo,ConfigUtil.getProperty("serverIP", ""),ConfigUtil.getProperty("serverPort"));
    }
    
    public JPanel getLvmcPanel(){
//        DataUtil.setValue(DataUtil.Key.showFlag, false);
        if(DataUtil.getValue(DataUtil.Key.LoginInfo)!=null){
            log.info("关闭会议====");
            ControllerFacade.execute("mainController", "logout");
        }
        JPanel panel = (JPanel) ControllerFacade.execute("mainController", "getMainPanel", 
                ParaUtil.newInstance()
                .setString("username", loginInfo.getUsername())
                .setString("password", loginInfo.getPassWord())
                .setString("confno", loginInfo.getConfno())
                .setString("serverIP", loginInfo.getServerIP())
                );
//        DataUtil.setValue(DataUtil.Key.showFlag, true);
        return panel;
    }
    
    public static void main(String[] args){
        LvmcUtil util = new LvmcUtil();
        javax.swing.JFrame jframe = new javax.swing.JFrame();
        jframe.setLayout(new java.awt.BorderLayout());
        JPanel panel = (JPanel) ControllerFacade.execute("mainController", "getMainPanel", 
                ParaUtil.newInstance()
                .setString("username", "59456")
                .setString("password", "123456")
                .setString("confno", "416997")
                .setString("serverIP", "10.168.250.12")
                );
        jframe.add(panel, java.awt.BorderLayout.CENTER);
        jframe.setVisible(true);
        jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    public JPanel getMeetingPanel(){
        JPanel panel = (JPanel) ControllerFacade.execute("mainController", "getMainPanel", 
                ParaUtil.newInstance()
                .setString("username", "59456")
                .setString("password", "123456")
                .setString("confno", "416997")
                .setString("serverIP", "10.168.250.12")
                );
        return panel;
    }
    
    
    public static boolean isUCSAPP(){
    	boolean flag = false;
    	if(DataUtil.getAppName()!=null && DataUtil.getAppName().equals(Constants.AppName.UCS)){
    		flag = true;
    	}
    	return flag;
    }
    
}
