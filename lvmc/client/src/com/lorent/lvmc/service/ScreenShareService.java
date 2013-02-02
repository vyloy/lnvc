/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.service;

import com.lorent.common.util.ProcessUtil;
import com.lorent.lvmc.util.ConfigUtil;
import com.lorent.lvmc.util.DataUtil;
import com.lorent.lvmc.util.ShareFileServerUtil;
import com.lorent.lvmc.util.StringUtil;

import java.io.File;
import org.apache.log4j.Logger;

/**
 *
 * @author jack
 */
public class ScreenShareService extends BaseService{
    
    private static Logger log = Logger.getLogger(ScreenShareService.class);
    
    public Process startScreenShareProcess() throws Exception{
    	String filepath = com.lorent.lvmc.util.Constants.AppPath + "/vnc/winvnc.exe";
        File file = new File(filepath);
        Process process = null;
        if (file.exists()) {
        	log.info("startScreenShareProcess  "+filepath);
        	//判断进程是否存在
        	if (!ProcessUtil.getInstance().processExists("winvnc.exe")) {
        		log.info("startScreenShareProcess process not Exists");
        		process = Runtime.getRuntime().exec("cmd /c createprocess.exe IntegrityLevel=High \"" + StringUtil.convertFilePath2DOSCommandStr(filepath)+" -run\"");
//        		process = Runtime.getRuntime().exec("cmd /c " + StringUtil.convertFilePath2DOSCommandStr(filepath)+" -run");
//                process.waitFor();
                log.info("startScreenShareProcess  started");
			}
        } else {
            throw new Exception("找不到文件: " + filepath);
        }
        return process;
    }
    
    public void stopScreenShareProcess() throws Exception{
    	String filepath = com.lorent.lvmc.util.Constants.AppPath + "/vnc/winvnc.exe";
        File file = new File(filepath);
        if (file.exists()) {
        	log.info("stopScreenShareProcess  "+filepath);
        	//判断进程是否存在
        	if (ProcessUtil.getInstance().processExists("winvnc.exe")) {
        		log.info("stopScreenShareProcess process Exists");
        		Process process = Runtime.getRuntime().exec("cmd /c taskkill /f /im winvnc.exe /t ");
                process.waitFor();
                log.info("stopScreenShareProcess  stoped");
			}
        } else {
            throw new Exception("找不到文件: " + filepath);
        }
    }
    
    public void unInitScreenShareService() throws Exception{
    	String taskListLine = ProcessUtil.getInstance().getTaskListLine("winvnc.exe");
    	log.info("unInitScreenShareService  taskListLine:"+taskListLine);
    	if (taskListLine != "" && taskListLine.indexOf("console") != -1) {
			//process
    		Process process = DataUtil.getValue(DataUtil.Key.ScreenShareProcess);
    		if (process != null) {
				process.destroy();
			}
    		DataUtil.setValue(DataUtil.Key.ScreenShareProcess, null);
    		stopScreenShareProcess();
//    		do {
//				Thread.sleep(1000);
//			} while (ProcessUtil.getInstance().getTaskListLine("winvnc.exe") == "");
		}
    	else if(taskListLine != "" && taskListLine.indexOf("services") != -1){
    		//services
    		uninstallScreenShareService();
//    		do {
//				Thread.sleep(1000);
//			} while (ProcessUtil.getInstance().getTaskListLine("winvnc.exe") == "");
    	}
    	log.info("unInitScreenShareService unInited");
    }
    
    public void uninstallScreenShareService() throws Exception{
    	String filepath = com.lorent.lvmc.util.Constants.AppPath + "/vnc/winvnc.exe";
        File file = new File(filepath);
        if (file.exists()) {
        	log.info("uninstallScreenShareService  "+filepath);
        	//判断进程是否存在
        	if (ProcessUtil.getInstance().processExists("winvnc.exe")) {
        		log.info("uninstallScreenShareService  process Exists");
        		Process process = Runtime.getRuntime().exec("cmd /c sc delete uvnc_service");
                process.waitFor();
                log.info("uninstallScreenShareService uninstalled");
			}
        } else {
            throw new Exception("找不到文件: " + filepath);
        }
    }
    
    public void installScreenShareService() throws Exception{
    	String filepath = com.lorent.lvmc.util.Constants.AppPath + "/vnc/winvnc.exe";
        File file = new File(filepath);
        if (file.exists()) {
        	log.info("installScreenShareService  "+filepath);
            Process process = Runtime.getRuntime().exec("cmd /c " + StringUtil.convertFilePath2DOSCommandStr(filepath) + " -install");
            process.waitFor();
            log.info("installScreenShareService  installed");
        } else {
            throw new Exception("找不到文件: " + filepath);
        }
    }
        
    public void addRepeaterID(String repeaterhost,Integer connid) throws Exception{
    	
    	String filepath = com.lorent.lvmc.util.Constants.AppPath + "/vnc/winvnc.exe";
        File file = new File(filepath);
        if (file.exists()) {
        	Process process = Runtime.getRuntime().exec("cmd /c " + StringUtil.convertFilePath2DOSCommandStr(filepath) + " -repeaterhost "+repeaterhost+" -connid "+connid +";");
        	process.waitFor();
        	log.info("addRepeaterID: "+repeaterhost+","+connid);
        }
    	
    }
    
    public void getRepeaterIDsFromServer(String[] targetusers) throws Exception{
    	log.info("getRepeaterIDFromServer "+targetusers);
//    	ShareFileServerUtil.getInstance().connect("127.0.0.1", Integer.parseInt(ConfigUtil.getProperty("fileServerPort", "8889")));
    	ShareFileServerUtil.getInstance().getRepeaterIDs(DataUtil.getLoginInfo().getConfno(), DataUtil.getLoginInfo().getUsername(), targetusers);
    }
    
//    public synchronized void startScreenShareProcess() throws Exception{
//        String filepath = com.lorent.lvmc.util.Constants.AppPath + "/vnc/winvnc.exe";
//        File file = new File(filepath);
//        if (file.exists()) {
//            boolean processExists = ProcessUtil.getInstance().processExists("winvnc.exe");
//            if (!processExists) {
//                log.debug("Runtime.getRuntime().exec: "+filepath);
//                Runtime.getRuntime().exec("cmd /c " + StringUtil.convertFilePath2DOSCommandStr(filepath) + " -run");
//                log.info("startScreenShareProcess cmd /c " + filepath + " -run");
//                Thread.sleep(3000);
//            }
//        }
//        else{
//            throw new Exception("找不到文件: " + filepath);
//        }
//    }
    
    public synchronized void stopScreenShare() throws Exception{
//        ShareFileServerUtil.getInstance().connect("127.0.0.1", Integer.parseInt(ConfigUtil.getProperty("fileServerPort", "8889")));
        ShareFileServerUtil.getInstance().connect(DataUtil.getLoginInfo().getServerIP(), Integer.parseInt(ConfigUtil.getProperty("fileServerPort", "8889")));
        ShareFileServerUtil.getInstance().stopScreenShare(DataUtil.getLoginInfo().getConfno(), DataUtil.getLoginInfo().getUsername());
//        ShareFileServerUtil.getInstance().close();
    }
    
    public synchronized void askforControlDesktop(String remoteUserName) throws Exception{
//        ShareFileServerUtil.getInstance().connect("127.0.0.1", Integer.parseInt(ConfigUtil.getProperty("fileServerPort", "8889")));
        ShareFileServerUtil.getInstance().connect(DataUtil.getLoginInfo().getServerIP(), Integer.parseInt(ConfigUtil.getProperty("fileServerPort", "8889")));
        ShareFileServerUtil.getInstance().askforControlDesktop(DataUtil.getLoginInfo().getConfno(), remoteUserName, DataUtil.getLoginInfo().getUsername(), DataUtil.getLoginInfo().getNickName());
    }
    
    public synchronized void agreeControlDesktop(String remoteUserName) throws Exception{
//        ShareFileServerUtil.getInstance().connect("127.0.0.1", Integer.parseInt(ConfigUtil.getProperty("fileServerPort", "8889")));
        ShareFileServerUtil.getInstance().connect(DataUtil.getLoginInfo().getServerIP(), Integer.parseInt(ConfigUtil.getProperty("fileServerPort", "8889")));
        ShareFileServerUtil.getInstance().agreeControlDesktop(DataUtil.getLoginInfo().getConfno(), remoteUserName, DataUtil.getLoginInfo().getUsername(), DataUtil.getLoginInfo().getNickName());
    }
    
    public synchronized void notAgreeControlDesktop(String remoteUserName) throws Exception{
    	ShareFileServerUtil.getInstance().connect(DataUtil.getLoginInfo().getServerIP(), Integer.parseInt(ConfigUtil.getProperty("fileServerPort", "8889")));
        ShareFileServerUtil.getInstance().notAgreeControlDesktop(DataUtil.getLoginInfo().getConfno(), remoteUserName, DataUtil.getLoginInfo().getUsername(), DataUtil.getLoginInfo().getNickName());
    }
}
