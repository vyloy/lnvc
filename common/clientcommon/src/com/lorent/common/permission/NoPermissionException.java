package com.lorent.common.permission;

public class NoPermissionException extends Exception{

		public NoPermissionException(){}
		
		public NoPermissionException(String str){
			super(str);
		}
}
