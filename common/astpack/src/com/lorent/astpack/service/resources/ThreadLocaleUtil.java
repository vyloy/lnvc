package com.lorent.astpack.service.resources;

import java.util.Locale;

public class ThreadLocaleUtil {
	private static ThreadLocal<Locale>localeThread = new ThreadLocal<Locale>();
	private static ThreadLocal<Object>tempThread = new ThreadLocal<Object>();
	
	public static void setLocale(Locale locale){
		localeThread.set(locale);
	}
	public static Locale getLocale() {
		if(localeThread.get()==null)return Locale.CHINA;
		return localeThread.get();
	}
	
}
