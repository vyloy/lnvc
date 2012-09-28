package com.jtattoo.plaf;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class WhiteboardPermission {
	
	public static boolean isEnable(){
		boolean result=false;
		try {
			Class<?> permissionUtilClazz = Class.forName("com.lorent.lvmc.util.PermissionUtil");
			Method method = permissionUtilClazz.getMethod("hasPermission", String.class);
			Field field = permissionUtilClazz.getField("WHITE_BOARD");
			result = (Boolean) method.invoke(null, field.get(null));
		} catch (Exception e) {
		}
		return result;
	}
}
