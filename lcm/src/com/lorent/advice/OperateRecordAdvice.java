package com.lorent.advice;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.aop.AfterReturningAdvice;

import com.lorent.annotation.AfterLogger;
import com.lorent.service.impl.ServiceFacade;
import com.lorent.util.PropertiesUtil;



public class OperateRecordAdvice implements AfterReturningAdvice {

	private ServiceFacade serviceFacade;
	
	private final static String LOGGER_FILENAME = "loggerMessage";
	
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
//		String methodName = method.getName();
//		String serviceName = target.getClass().getSimpleName().replace("Impl", "");
//		String getMsgStr = "logger." + serviceName + "." + methodName;
		
//		//获取日志格式字符串额外条件
//		String getMsgConditionStr = getMsgStr + ".con";
//		String msgCondition = PropertiesUtil.getProperty(LOGGER_FILENAME, getMsgConditionStr);
//		
//		if(msgCondition.equals(getMsgConditionStr)){//不存在
//
//		}else{
//			String[] conditionItems = msgCondition.split("&");
//			if(conditionItems.length < 2){
//				return;
//			}
//			if("addorupdate".equals(conditionItems[0])){
//				Integer index = Integer.parseInt(conditionItems[1].replace("arg", ""));
//				Object obj = args[index];
//				Method getIdMethod = obj.getClass().getMethod("getId");
//				Object objId = getIdMethod.invoke(obj);
//				if(objId==null){//add
//					getMsgStr = getMsgStr + "&add";
//				}else{//update
//					getMsgStr = getMsgStr + "&update";
//				}
//			}
//		}
		
		AfterLogger annotation = method.getAnnotation(AfterLogger.class);
		if(annotation==null){
			return;
		}
		String getMsgStr = annotation.msgKey();
		
		//获取日志格式字符串
		String msgStr = PropertiesUtil.getProperty(LOGGER_FILENAME, getMsgStr,true);
		if(msgStr.equals(getMsgStr)){
			return;
		}
		
		//获取日志格式字符串参数
		String getMsgArgStr = getMsgStr + ".arg";
		String msgArgStr = PropertiesUtil.getProperty(LOGGER_FILENAME, getMsgArgStr,true);
		List<String> msgArgValues = new ArrayList<String>();
		if(msgArgStr.equals(getMsgArgStr)){//没有参数
			
		}else{
			//获取参数相应的值
			String[] msgArgs = msgArgStr.split(",");
			
			for(String msgArg : msgArgs){
				String value = "";
				try{
					String[] msgArgItems = msgArg.split("&");
		
					Integer index = Integer.parseInt(msgArgItems[0].replace("arg", ""));
	
					Object obj = args[index];
					value = changeObjectToString(getObjectValue(msgArg,obj));
					
				}catch(Exception e){
					e.printStackTrace();
					value = "";
				}
				msgArgValues.add(value);
			}
		}
		//生成操作描述
		String msg = MessageFormat.format(msgStr, msgArgValues.toArray());
		System.out.println(msg);
		
		//保存日志
		serviceFacade.getOperateRecordService().record(msg);
	}
	
	
	public Object getObjectValue(String str, Object obj){
		
		String[] items = str.split("&");
		
		if(items.length == 1){
			return obj;
		}
		if(obj instanceof List){//list
			String returnStr = "";
			List list = (List)obj;
			try{
			for(Object o : list){
				Field field = o.getClass().getDeclaredField(items[1]);
				field.setAccessible(true);
				returnStr = returnStr + field.get(o) + ",";
			}
			}catch(Exception e){
				return "";
			}
			returnStr = returnStr.substring(0, returnStr.length()-1);
			return returnStr;
		}
		Object lastObj = obj;
		for(int i = 1; i < items.length; i++){
			try{
				Field field = lastObj.getClass().getDeclaredField(items[i]);
				field.setAccessible(true);
				lastObj = field.get(lastObj);
				
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
		return lastObj;
	}
	
	public String changeObjectToString(Object obj){//TODO
		if(obj==null){
			return "";
		}else{
			return obj.toString();
		}
	}

	public ServiceFacade getServiceFacade() {
		return serviceFacade;
	}

	public void setServiceFacade(ServiceFacade serviceFacade) {
		this.serviceFacade = serviceFacade;
	}
	
	public static void main(String[] args) {
		String temp = MessageFormat.format("{0} sfsdf {1}", new ArrayList().toArray());
		System.out.println(temp);
	}

}
