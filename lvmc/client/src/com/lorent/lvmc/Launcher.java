/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc;

import java.io.File;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.jivesoftware.smack.Connection;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.orm.jpa.vendor.Database;

import com.lorent.lvmc.controller.ControllerFacade;
import com.lorent.lvmc.util.ConfigUtil;
import com.lorent.lvmc.util.Constants;
import com.lorent.lvmc.util.DataUtil;
import com.lorent.lvmc.util.FileUtil;
import com.lorent.lvmc.util.LvmcOpenfireUtil;
import com.lorent.lvmc.util.DataUtil.Key;


/**
 *
 * @author jack
 */
public class Launcher {
    private static Logger log = Logger.getLogger(Launcher.class);
    
    public static boolean isStartedFromOutSide;
    
    public static void main(String[] args)throws Exception{
//        ClassLoader loader = new MyClassLoader(findParentClassLoader(), new File("lib"));
//        Thread.currentThread().setContextClassLoader(loader);
    	Connection.DEBUG_ENABLED = false;
    	try{
    		startLvmc(args);
    	}catch(Exception e){
    		log.error("main", e);
    		throw e;
    	}
    }
    
    public static void startLvmcFromOutSide(Object[] args, Constants.AppName app, boolean isAnswer) throws Exception{
    	String[] contextPaths = new String[]{"classpath:com/lorent/lvmc/config/applicationContext-*.xml"};
        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(contextPaths);
        DataUtil.setApplicationContext(context);
    	System.setProperty("java.awt.im.style","on-the-spot");
//doLoginFromOutSide(String confName,String confNo,String username,String password,String serverip)
    	isStartedFromOutSide = true;
    	DataUtil.setAppName(app);
    	DataUtil.setValue(DataUtil.Key.IsAnswer, isAnswer);
    	ControllerFacade.execute("mainController", "doLoginFromOutSide",args[0],args[1],args[2],args[3],args[4]);
    	
    }
    
    public static void stopLvmcFromOutSide() throws Exception{
//    	ControllerFacade.execute("mainController", "doLogoutFromOutSide");
//    	isStartedFromOutSide = false;
    	try{
    		LvmcOpenfireUtil.leaveRoom();
    	}catch(Exception e){//即使不能离开会议室也继续
    		log.error("stopLvmcFromOutSide", e);
    		ControllerFacade.execute("mainController", "doLogoutFromOutSide");
    	}
    	if(listener != null){
    		listener.stopLvmc();
    	}
    }
    
    public static void startLvmc(String[] args)throws Exception{
        log.info("user dir:" + Constants.AppPath);
        boolean reloadfile = ConfigUtil.getBoolProperty("reloadfile", Boolean.TRUE);
        if(reloadfile){
            reloadfile();
        }//else{
            if(args == null || args.length != 0){//load from jnlp
                ConfigUtil.setProperty("username", args[0]);
                ConfigUtil.setProperty("confno", args[1]);
                ConfigUtil.setProperty("serverIP", args[2]);
            }

            String[] contextPaths = new String[]{"classpath:com/lorent/lvmc/config/applicationContext-*.xml"};
            FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(contextPaths);
            DataUtil.setApplicationContext(context);
            DataUtil.setValue(Key.Restart, ConfigUtil.getBoolProperty("restart"));
            ConfigUtil.setProperty("restart", false + "");
            System.setProperty("java.awt.im.style","on-the-spot");
//            NativeInterface.open();
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {

                    ControllerFacade.execute("mainController", "showLogin");
                }
            });
//            NativeInterface.runEventPump();
        //}
    }
    
    public static void reloadfile()throws Exception{
        log.info("reloadfile");
        String userhome = System.getProperty("user.home");
        String javahome = System.getProperty("java.home");
        log.info("userhome:" + userhome);
        log.info("javahome:" + javahome);
        
        //set reload flag
        ConfigUtil.setProperty("reloadfile", "false");
    }
    
    public static ClassLoader findParentClassLoader() {
        ClassLoader parent = Thread.currentThread().getContextClassLoader();
        if (parent == null) {
            parent = ClassLoader.getSystemClassLoader();
        }    
        return parent;
    }
    
	private static EventListener listener;

	public interface EventListener {
		void stopLvmc();
	}

	public static void setEventListener(EventListener listener) {
		Launcher.listener = listener;
	}
}
