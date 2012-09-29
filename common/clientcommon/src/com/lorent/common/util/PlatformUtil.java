package com.lorent.common.util;


import org.apache.log4j.Logger;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.Advapi32;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Shell32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;


interface MyKernel32 extends Kernel32{
	
	/*
	 DWORD WTSGetActiveConsoleSessionId(void);
	 * */
	int WTSGetActiveConsoleSessionId();
	
	/*
	 * BOOL ProcessIdToSessionId(
		  __in          DWORD dwProcessId,
		  __out         DWORD* pSessionId
		);
	 * */
	boolean ProcessIdToSessionId(int dwProcessId, IntByReference pSessionId);
//	int GetThreadPriority(HANDLE hwnd);
//	boolean SetThreadPriority(HANDLE hwnd,int nPriority);
	DWORD GetPriorityClass(HANDLE hProcess);
	boolean SetPriorityClass(HANDLE hProcess,DWORD dwPriorityClass);
}

interface MyUser32 extends User32{

}

interface MyShell32 extends Shell32{
	boolean IsUserAnAdmin();
}

interface MyAdvApi32 extends Advapi32{
}

public class PlatformUtil {
	
	//----------The process's priority class----------
	public static DWORD ABOVE_NORMAL_PRIORITY_CLASS = new DWORD(0x00008000);
	public static DWORD BELOW_NORMAL_PRIORITY_CLASS = new DWORD(0x00004000);
	public static DWORD HIGH_PRIORITY_CLASS = new DWORD(0x00000080);
	public static DWORD IDLE_PRIORITY_CLASS = new DWORD(0x00000040);
	public static DWORD NORMAL_PRIORITY_CLASS = new DWORD(0x00000020);
	public static DWORD REALTIME_PRIORITY_CLASS = new DWORD(0x00000100);
	
	
	static Logger log = Logger.getLogger(PlatformUtil.class);
	
	static MyKernel32 myKernel32 = (MyKernel32) Native.loadLibrary("kernel32.dll",MyKernel32.class);
	static MyUser32 myUser32 = (MyUser32) Native.loadLibrary("user32.dll",MyUser32.class);
	static MyShell32 myShell32 = (MyShell32) Native.loadLibrary("shell32",MyShell32.class);
	static MyAdvApi32 myAdvApi32 = (MyAdvApi32) Native.loadLibrary("advapi32.dll",MyAdvApi32.class);
	
	
	
	public static boolean isLocalSession() throws Exception{
		
		IntByReference pSessionId = new IntByReference();
		int getCurrentProcessId = myKernel32.GetCurrentProcessId();
		if (myKernel32.ProcessIdToSessionId(getCurrentProcessId, pSessionId)) {
			int activeConsoleSessionId = myKernel32.WTSGetActiveConsoleSessionId();
			if (activeConsoleSessionId != 0xFFFFFFFF && activeConsoleSessionId == pSessionId.getValue()) {
				return true;
			}
		}
		return false;
	}
	
	public static Double getOSVersion() throws Exception{
		DWORD version = PlatformUtil.myKernel32.GetVersion();
		double low = Double.parseDouble(version.getLow().toString());
		double high = Double.parseDouble(version.getHigh().toString());
		return low + high / 1000 ;
	}
	
	public static DWORD getCurrentPriorityClass() throws Exception{
		HANDLE getCurrentProcess = myKernel32.GetCurrentProcess();
		return myKernel32.GetPriorityClass(getCurrentProcess);
	}
	
	public static boolean setCurrentPriorityClass(DWORD dwPriorityClass) throws Exception{
		HANDLE getCurrentProcess = myKernel32.GetCurrentProcess();
		return myKernel32.SetPriorityClass(getCurrentProcess, dwPriorityClass);
	}
	
	public static boolean isUserAnAdmin() throws Exception{
		return myShell32.IsUserAnAdmin();
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.setProperty("jna.predictable_field_order","true");
//			System.out.println(PlatformUtil.getOSVersion());
			
			System.out.println("isuseranadmin: "+isUserAnAdmin());
			
			System.out.println(System.getProperty("user.home"));
			
			
			if (PlatformUtil.getOSVersion() >= 6.0f) {
				if (PlatformUtil.isUserAnAdmin()) {
					String createProcessPath ="D:\\Program Files\\client\\createprocess.exe";
					createProcessPath = StringUtil.convertFilePath2DOSCommandStr(createProcessPath);
					String lvmcPath = "D:\\Program Files\\client\\lvmc.exe 22629 22885 SECURITY_MANDATORY_MEDIUM_RID";
					lvmcPath = StringUtil.convertFilePath2DOSCommandStr(lvmcPath);
					String cmdStr = " " +createProcessPath+" "+lvmcPath+" " ;
					log.info(cmdStr);
					System.out.println(cmdStr);
					Process process = Runtime.getRuntime().exec(cmdStr);
	                process.waitFor();
//	                System.exit(0);
				}
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		boolean getVersionEx = PlatformUtil.myKernel32.GetVersionEx(osversioninfo);
//		System.out.println(osversioninfo);
	}

}
