/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.exeManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.URLEncoder;

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
//        ArrayList<String> namesList = new ArrayList<String>();
        Process process = Runtime.getRuntime().exec("cmd /c tasklist ");
        
        InputStreamReader ipsr = new InputStreamReader(process.getInputStream());//把得到的输入流转换为字节流
        BufferedReader br = new BufferedReader(ipsr);// 把字节流转换为字符流
        String result=null;
        boolean bFlag = false;
        while ((result = br.readLine()) != null) {
            int indexOf = result.toLowerCase().indexOf(processName.toLowerCase());
            if (indexOf != -1) {
//                return true;
            	bFlag = true;
//            	break;
            }
        }
        process.waitFor();
        return  bFlag;
    }

    public String getTaskListLine(String processName) throws Exception{
        Process process = Runtime.getRuntime().exec("cmd /c tasklist ");
        InputStreamReader ipsr = new InputStreamReader(process.getInputStream());//把得到的输入流转换为字节流
        BufferedReader br = new BufferedReader(ipsr);// 把字节流转换为字符流
        String result="";
        String sTemp = "";
        while ((sTemp = br.readLine()) != null) {
            int indexOf = sTemp.toLowerCase().indexOf(processName.toLowerCase());
            if (indexOf != -1) {
                result = sTemp.toLowerCase();
            }
        }
        process.waitFor();
        return  result;
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
         
        ProcessUtil processUtil = new ProcessUtil();
        try {
            processUtil.killProcessByName("Monitor_Lcc_Mfc.exe");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        */
    	/*
    	ProcessUtil processUtil = new ProcessUtil();
    	try {
			boolean processExists = processUtil.processExists("winvnc.exe");
			System.out.println("processExists: "+processExists);
			
			String taskListLine = ProcessUtil.getInstance().getTaskListLine("winvnc.exe");
			System.out.println(taskListLine);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
    	String duration = " 29mn 10s";
		Integer hours = 0;
		Integer minutes = 0;
		Integer seconds = 0;
		if (duration.indexOf("h") != -1) {
			String trim1 = duration.substring(0, duration.indexOf("h")).trim();
			hours = Integer.parseInt(trim1);
			duration = duration.substring(duration.indexOf("h")+1);
		}
		else{
			hours = 0;
		}
		if (duration.indexOf("mn") != -1) {
			String trim2 = duration.substring(0, duration.indexOf("mn")).trim();
			minutes = Integer.parseInt(trim2);
			duration = duration.substring(duration.indexOf("mn")+2);
		}
		if (duration.indexOf("s") != -1) {
			String trim3 = duration.substring(0,duration.indexOf("s")).trim();
			seconds = Integer.parseInt(trim3);
		}
		System.out.println(hours+":"+minutes+":"+seconds);
		
		String lccno="sip:101010@10.168.250.12:5666";
		lccno = "22222";
		String lccnostr = lccno;
		if (lccno.indexOf("sip:") != -1 && lccno.indexOf("@") != -1) {
			int begin = lccno.indexOf("sip:")+4;
			int end = lccno.indexOf("@");
			lccnostr = lccno.substring(begin, end);
		}
		System.out.println(lccnostr);
		
		String stemp1 = "http://10.168.130.213:8800/20121123135924_642_NASA月球的变迁_1080P_4800K_mp4.jpg";
		String stemp2 = "NASA月球的变迁_1080P_4800K_mp4.jpg";
		String stemp3 = "http://10.168.130.213:8800/20121123135924_642_NASA%E6%9C%88%E7%90%83%E7%9A%84%E5%8F%98%E8%BF%81_1080P_4800K_mp4.jpg";
		System.out.println(stemp3);
		System.out.println("http://10.168.130.213:8800/20121123135924_642_"+URLEncoder.encode(stemp2));
		
		int indexOf = stemp1.indexOf(":8800/");
		String endstr = stemp1.substring(indexOf+6);
		String beginstr = stemp1.substring(0, indexOf+6);
		String url = beginstr+ URLEncoder.encode(endstr);
		System.out.println(url);
		
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
