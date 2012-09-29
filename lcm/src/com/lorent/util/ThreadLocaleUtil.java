package com.lorent.util;

import java.util.Locale;
import org.springframework.security.context.SecurityContextHolder;

//import com.lorent.exception.ModelCheckFailException;
import com.lorent.model.UserBean;

public class ThreadLocaleUtil {
	private static ThreadLocal<Locale>localeThread = new ThreadLocal<Locale>();
	private static ThreadLocal<UserBean>userThread = new ThreadLocal<UserBean>();
	private static ThreadLocal<Object>tempThread = new ThreadLocal<Object>();
	
	public static void setLocale(Locale locale){
		localeThread.set(locale);
	}
	public static Locale getLocale() {
		if(localeThread.get()==null)return Locale.CHINA;
		return localeThread.get();
	}
	
	public static void setUser(UserBean user){
		userThread.set(user);
	}
	
	public static UserBean getUser() {
		if(userThread.get()!=null){
			UserBean temp = userThread.get();
			removeUser();
			return temp;
		}
		if(SecurityContextHolder.getContext().getAuthentication()==null)
			return null;
		return (UserBean)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	public static void removeUser(){
		userThread.set(null);
	}
	
	public static void setTemp(Object temp){
		tempThread.set(temp);
	}
	
	public static<T> T getTemp(){
		return (T)tempThread.get();
	} 
}
