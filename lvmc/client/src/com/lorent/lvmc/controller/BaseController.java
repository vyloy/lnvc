/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.controller;


import java.awt.Toolkit;

import com.lorent.lvmc.service.ServiceFactory;
import com.lorent.lvmc.util.ConfigUtil;
import com.lorent.lvmc.util.DataUtil;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author jack
 */
public abstract class BaseController {

    protected ServiceFactory services;

    public void setServices(ServiceFactory services) {
        this.services = services;
    }
    
//    private javax.swing.JLabel label = new javax.swing.JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/com/lorent/lvmc/resource/images/main_icon.png"))));
    
    protected void showErrorDialog(String title, String msg) {
//    	System.out.println(DataUtil.getTopWindow().getClass().getName());
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.ERROR_MESSAGE);
    }
    
    protected int showConfirmDialog(String title, String msg) {
//    	System.out.println(DataUtil.getTopWindow().getClass().getName());
        return JOptionPane.showConfirmDialog(null, msg, title, JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);//点击取消返回值为2，点击确定返回值为0，点击关闭返回值为-1
    }
    
    protected void showMessageDialog(String title, String msg) {
//    	System.out.println(DataUtil.getTopWindow().getClass().getName());
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }
//    
//    protected void insertCommanderToTrayIconController(Commander trayiconCommander){
//        Commander commander = new Commander();
//        commander.setClassName("TrayIconController");
//        commander.setMethodName("addListener");
//        commander.setParamter("TrayIconCommander", trayiconCommander);
//        ControllerFacade.execute(commander);
//    }
    
    protected String getXmlrpcUrl(){
    	return "http://" + ConfigUtil.getProperty("serverIP") + ConfigUtil.getProperty("lcm.xmlrpc");
    }
}
