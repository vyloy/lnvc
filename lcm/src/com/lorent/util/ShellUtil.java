package com.lorent.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class ShellUtil {
	private static Logger log = Logger.getLogger(ShellUtil.class);
	
	//Linux执行单条命令
	public static void execute(String cmd) throws Exception{
		log.info("ShellUtil执行命令："+cmd);
		String[] cmds = new String[]{"/bin/sh", "-c", cmd};
		Process pro;
		pro = Runtime.getRuntime().exec(cmds);
		int ret = pro.waitFor();
		BufferedReader br2 = new BufferedReader (new InputStreamReader(pro.getInputStream()));   
		String msg = null;
		while ((msg = br2.readLine()) != null) {
			log.info("ShellUtil修改命令执行结果"+msg);   
		}
		br2 = new BufferedReader (new InputStreamReader(pro.getErrorStream()));   
		msg = null;
		while ((msg = br2.readLine()) != null) {
			log.error("ShellUtil修改命令执行错误"+msg);
		}
		log.info("----------ShellUtil修改执行结束-----------");
		
	}
	
}
