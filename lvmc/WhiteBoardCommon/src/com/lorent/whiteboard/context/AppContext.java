package com.lorent.whiteboard.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AppContext {
	private static final Map<String,Object> context=new ConcurrentHashMap<String,Object>();
	
	public static void put(Class<?> clazz,Object object){
		context.put(clazz.getName(), object);
	}
	
	public static <T> T get(Class<T> clazz){
		return clazz.cast(context.get(clazz.getName()));
	}
}
