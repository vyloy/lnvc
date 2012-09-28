package com.lorent.common.util;


import com.sun.jna.Native;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinDef.DWORD;
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
}

public class PlatformUtil {

	static MyKernel32 myKernel32 = (MyKernel32) Native.loadLibrary("kernel32.dll",MyKernel32.class);
	
	
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
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println(PlatformUtil.getOSVersion());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		boolean getVersionEx = PlatformUtil.myKernel32.GetVersionEx(osversioninfo);
//		System.out.println(osversioninfo);
	}

}
