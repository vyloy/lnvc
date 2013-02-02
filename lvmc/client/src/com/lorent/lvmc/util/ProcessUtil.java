/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;   
import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;

import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class ProcessUtil {
    
    
    private static ProcessUtil instance = new ProcessUtil();
    private Logger log = Logger.getLogger(ProcessUtil.class);
    private ProcessUtil() {

    }

    public static ProcessUtil getInstance() {
        return instance;
    }
    
    
    /**
     * 终止processName名称的进程
     * @param processName  进程的名称
     * @throws Exception 
     */
    public void killProcessByName(String processName) throws Exception{
        Runtime.getRuntime().exec("cmd /c taskkill /F /im " + processName);
    }
    
    public Process startProcess(String Command)throws Exception{
        Process process = Runtime.getRuntime().exec(Command);
        return process;
    }
    
    public Process startProcess(String[] Command)throws Exception{
        Process process = Runtime.getRuntime().exec(Command);
        return process;
    }
    
    public boolean  processExists(String processName) throws Exception{
        ArrayList<String> namesList = new ArrayList<String>();
        Process process = Runtime.getRuntime().exec("cmd /c tasklist ");
        InputStreamReader ipsr = new InputStreamReader(process.getInputStream());//把得到的输入流转换为字节流
        BufferedReader br = new BufferedReader(ipsr);// 把字节流转换为字符流
        String result=null;
        while ((result = br.readLine()) != null) {
            int indexOf = result.toLowerCase().indexOf(processName.toLowerCase());
            if (indexOf != -1) {
                return true;
            }
        }
        return  false;
    }
    
    /**
     * 重启LCC
     * @throws Exception 
     */
    public void restartApplication() throws Exception {
        //log.info("重启程序");
        String path = System.getProperty("user.dir");
        String cmd = "createprocess.exe \"exeManager.exe 1 \"" + path + "\" " + Constants.APPLICATION_NAME + "\"";
        log.info(cmd);
        Runtime.getRuntime().exec(cmd);
//        Thread.sleep(100);
        System.exit(0);
    }
    
    public static void main(String args[]){
        /*
        try {
        //getInstance().startProcess("cmd /c C:\\Documents and Settings\\Administrator.NDJXPG-2372D204\\桌面\\debug\\Monitor_Lcc_Mfc.exe 5080 10.168.130.203 4192 123456 352 288 256 1 10001 10.168.130.102 1000");
        String[] command = {"F:\\debug\\Monitor_Lcc_Mfc.exe", "5080", "10.168.130.203", "4192", "123456", "352", "288", "256", "1", "10001", "10.168.130.102", "1000"};
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader buf = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String str = "";
        while((str = buf.readLine())!= null)
        {
        System.out.println(str);
        }
        //process.destroy();
        //getInstance().restartApplication();
        } catch (Exception ex) {
        System.out.println("dfasdfdf");
        Logger.getLogger(ProcessUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
        ProcessUtil processUtil = new ProcessUtil();
        try {
            processUtil.killProcessByName("Monitor_Lcc_Mfc.exe");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public int getOwnPid(){
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();   
        String name = runtime.getName(); // format: "pid@hostname"   
        try {   
            return Integer.parseInt(name.substring(0, name.indexOf('@')));   
        } catch (Exception e) {   
        	log.error("getOwnPid", e);
            return -1;   
        }   
    }
    
}
