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
        String lvmchome = userhome + "/lvmc/";
        String javahome = System.getProperty("java.home");
        log.info("userhome:" + userhome);
        log.info("lvmchome:" + lvmchome);
        log.info("javahome:" + javahome);
        
        File maindir = new File(lvmchome);
        if(!maindir.exists()){
            maindir.mkdirs();
        }
        //copy dll
//        FileUtil.extraJarFile("/com/lorent/lvmc/extra/dll.jar", javahome + "/bin/");
        //copy vnc
        File vncdir = new File("vnc");
        if(!vncdir.exists()){
            vncdir.mkdirs();
        }
        FileUtil.extraJarFile("/com/lorent/lvmc/extra/vnc.jar", "vnc/");
        
        //copy java.policy
        log.info("copy java.policy");
        FileUtil.fileCopy(Launcher.class.getResourceAsStream("/com/lorent/lvmc/config/java.policy"), javahome + "/lib/security/java.policy");
        //copy lnt.avi
        log.info("copy lnt.avi");
        FileUtil.fileCopy(Launcher.class.getResourceAsStream("/com/lorent/lvmc/config/lnt.avi"), "lnt.avi");
        //copy layout
        File layoutdir = new File("layouts");
        if(!layoutdir.exists()){
            layoutdir.mkdirs();
        }
        log.info("copy layout default.obj");
        FileUtil.fileCopy(Launcher.class.getResourceAsStream("/com/lorent/lvmc/config/default.obj"), "layouts/default.obj");
        FileUtil.fileCopy(Launcher.class.getResourceAsStream("/com/lorent/lvmc/config/1xN.obj"), "layouts/1xN.obj");
        FileUtil.fileCopy(Launcher.class.getResourceAsStream("/com/lorent/lvmc/config/Not1xN.obj"), "layouts/Not1xN.obj");
        //copy config file
        log.info("copy lvmc.conf");
        FileUtil.fileCopy(Launcher.class.getResourceAsStream("/com/lorent/lvmc/config/lvmc.conf"), "lvmc.conf");
        
        File skinDir = new File("skin");
        if(!skinDir.exists()){
        	skinDir.mkdirs();
        }
        FileUtil.fileCopy(Launcher.class.getResourceAsStream("/com/lorent/lvmc/config/defualt_skin.conf"), "skin/defualt_skin.conf");
        
        File logoFile = new File("logo.png");
        if(!logoFile.exists()){
        	FileUtil.fileCopy(Launcher.class.getResourceAsStream("/com/lorent/lvmc/config/logo.png"), "logo.png");
        }
        
        //set reload flag
        ConfigUtil.setProperty("reloadfile", "false");
//        JOptionPane.showMessageDialog(null, StringUtil.getUIString("needrestart"));
        //String path = System.getProperty("user.dir");
        //Runtime.getRuntime().exec("cmd /c \"" + path + "/start.bat\"");
//        System.exit(0);
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
