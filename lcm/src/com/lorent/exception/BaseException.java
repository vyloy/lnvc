package com.lorent.exception;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lorent.util.PropertiesUtil;

public class BaseException extends Exception {
	private static final long serialVersionUID = 1L;
	protected static final String FILE_NAME = "exceptionResource";
	protected String key = null;
	private String excpMsg = "";
	private boolean initMsg = false;
	@Override
	public String getMessage() {
		if(initMsg){
			return excpMsg;
		}
		if(key==null)return null;
		//情况一：key = page.text.userrole
		//情况二（情况一抛异常）：key = 我的角色是{page.text.userrole}
		//情况三（情况二不满足）
		String value = PropertiesUtil.getProperty(FILE_NAME, key,true);
		if(value.equals(key)){
			return getFormatString(key, FILE_NAME);			
		}else{
			return value;
		}
	}
	
	public BaseException(String key) {
		super(key);
		this.key = key;
	}
	
	public BaseException(String key,Throwable throwable){
		super(key,throwable);
		this.key = key;
	}
	
	public BaseException(String msg,boolean initMsg){
		this.initMsg = initMsg;
		this.excpMsg = msg;
		this.key = msg;
	}
	
	//将格式字符串"abc:{validate.isnotemail}"转化为"abc:不合法的email"
	private String getFormatString(String key, String file){
		String regex = "\\{([^\\{]*)\\}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(key);
		List<String> list = new ArrayList<String>();
		boolean flag = false;
		while(matcher.find()){
			flag = true;
			String temp = matcher.group(1);
			try{
				temp = PropertiesUtil.getProperty(file, temp, true);
			}catch(Exception e){
				temp = "";
			}
			list.add(temp);
		}
		if(!flag){//没有匹配
			return key;
		}
		regex = "\\{[^\\{]*\\}";
		for(String str2 : list){
			key = key.replaceFirst(regex, str2);
		}
		return key;
	}
	
}
