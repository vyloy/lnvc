package com.lorent.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.util.CollectionUtils;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;

public class StringUtil {

	
	public static Integer toInteger(Object obj,Integer defaultValue){
		try{
			return Integer.parseInt(obj.toString());
		}catch(Exception e){
			return defaultValue;
		}
	}

	public static String toString(Object obj,String defaultValue){
		String returnvalue = defaultValue;
		if(obj != null) returnvalue = obj.toString();
		return returnvalue;
	}
	
	public static Integer[] toInteger(String[]numbers) {
		Integer[]ints = new Integer[numbers.length]; 
		for (int i = 0; i < numbers.length; i++) {
			try {
				ints[i] = Integer.parseInt(numbers[i]);
			} catch (Exception e) {
				continue;
			}
		}
		return ints;
	}
	/**
	 * 将对象中String类型的属性编码格式从ISO-8859-1转换为UTF-8
	 * @param <T>
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static <T> T changeEncoding(T obj)throws Exception{
		if(obj==null)return null;
		Class clazz = obj.getClass();
		if(clazz.getName().equals("java.lang.String")){
			String str = obj.toString();			
			str = new String(str.getBytes("ISO-8859-1"),"UTF-8");
			if(str.indexOf("??")<0)obj = (T)str;
			return obj;
		}
		Field[]fields = clazz.getDeclaredFields();
		for(int i=0;i<fields.length;i++){
			fields[i].setAccessible(true);
			if(fields[i].getType().getName().equals("java.lang.String")&&fields[i].get(obj)!=null){
				try{
					String field = fields[i].get(obj).toString();
					byte[]bytes = field.getBytes("ISO-8859-1");
					field = new String(bytes,"UTF-8");
					if(field.indexOf("??")>=0)continue;
					fields[i].set(obj, field);
				}catch(Exception e){
					e.printStackTrace();
					continue;
				}
			}
		}
		return obj; 
	}
	
	public static String isoStrToGBK(String str)throws Exception{
		return new String(str.getBytes("ISO-8859-1"),"GBK");
	}
	
	/**
	 * 判断字符串是否全部为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){
		if(isEmpty(str)){
			return false;
		}
	    Pattern pattern = Pattern.compile("[0-9]+");   
	    return pattern.matcher(str).matches();      
	}
	
	/**
	 * 判断字符串是否为Email
	 * @param str
	 * @return
	 */
	public static boolean isEmail(String str){
		if(isEmpty(str)){
			return false;
		}
////		Pattern pattern = Pattern.compile("\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+((\\.com)|(\\.net)|(\\.org)|(\\.info)|(\\.edu)|(\\.mil)|(\\.gov)|(\\.biz)|(\\.ws)|(\\.us)|(\\.tv)|(\\.cc)|(\\.aero)|(\\.arpa)|(\\.coop)|(\\.int)|(\\.jobs)|(\\.museum)|(\\.name)|(\\.pro)|(\\.travel)|(\\.nato)|(\\..{2,3})|(\\..{2,3}\\..{2,3}))$)\\b");
//		Pattern pattern = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
//		return pattern.matcher(str).matches(); 
		return org.apache.commons.validator.EmailValidator.getInstance().isValid(str);
	}
	
	
	
	
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str==null || str.trim().length() == 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断字符串是否非空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}
	
	public static boolean objectIsEmpty(Object obj){
		if(obj==null){
			return true;
		}
		if(obj instanceof String){
			return isEmpty((String)obj);
		}
		return false;
	}
	
	public static boolean objectIsNotEmpty(Object obj){
		return !objectIsEmpty(obj);
	}
	
	/**
	 * 传入带格式的hql和参数，得到可以查询的hql
	 * @param hql
	 * @param args
	 * @return
	 */
	public static String createHqlByArgs(String hql, Object...args){
		List<String> temp = new ArrayList<String>();
		for(Object arg : args){
			temp.add(changeObjToConditionValue(arg));
		}
		String result = MessageFormat.format(hql, temp.toArray());
		return result;
	}
	
	/**
	 * 创建hql查询条件
	 * @param field
	 * @param arg
	 * @return
	 */
	private static String createConditionStr(String field, Object arg){
		String value = "";
		if(arg instanceof String){
			value = field +  " like '%" + (String)arg + "%' ";
		}else if(arg instanceof Integer){
			value = field +  " = " + (((Integer)arg).toString());
		}
		return value;
	}
	
	/**
	 * 把obj转化为查询条件的值
	 * @param arg
	 * @return
	 */
	private static String changeObjToConditionValue(Object arg){
		String value = "";
		if(arg instanceof String){
			value = "'%" + (String)arg + "%'";
		}else if(arg instanceof Integer){
			value = (((Integer)arg).toString());
		}
		return value;
	}
	
	/**
	 * 根据传入的字符串，获取对象相应的值
	 * @param obj
	 * @param str
	 * @return
	 */
	public static Object getValueByStr(Object obj, String str){
		String[] items = str.split("\\.");
		Object lastObj = obj;
		for(int i = 0; i < items.length; i++){
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
	
	/**
	 * 创建hql查询条件
	 * @param <T>
	 * @param obj
	 * @param perfix
	 * @param fieldNames
	 * @return
	 */
	public static <T> String createSearchConditionByEntity(T obj, String perfix, String[] fieldNames){
		if(obj==null){
			return "";
		}
		String result = "";
		Class clazz = obj.getClass();
		try {
			for(String fieldName : fieldNames){
				Object fieldValue = getValueByStr(obj, fieldName);
				if(objectIsNotEmpty(fieldValue)){
					result = result + " and " + createConditionStr(perfix + "." + fieldName, fieldValue);
				}
			}
		} catch (Exception e){
			e.printStackTrace();
			result = "";
		}
		return result;
	}
	
	/**
	 * 获取i位随机数字字符串
	 * @param i
	 * @return
	 */
	public static String getRandom(int i){
		String code = Long.toString(UUID.randomUUID().getLeastSignificantBits());
		return code.substring(code.length()-i,code.length());
	}
	
	public static <T> List<T> parseObjectArrayToList(Object[] objs){
		List<T> list = new ArrayList<T>();
		for(Object obj : objs){
			list.add((T)obj);
		}
		return list;
	}
	
	public static <T> T[] parseObjectArrayToArray(Object[] objs, Class<T> clazz){
		T[] t = (T[])Array.newInstance(clazz, objs.length);
		for(int i = 0; i < objs.length; i++){
			t[i] = (T)objs[i];
		}
		return t;
	}
	
	public static <T> T[] parseListToArray(List<T> list, Class<T> clazz){
		T[] t = (T[])Array.newInstance(clazz, list.size());
		for(int i = 0; i < list.size(); i++){
			t[i] = list.get(i);
		}
		return t;
	}
	
	public static String collectionToDelimitedString(Collection coll, String delim, String prefix, String suffix) {
		if (CollectionUtils.isEmpty(coll)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Iterator it = coll.iterator();
		while (it.hasNext()) {
			sb.append(prefix).append(it.next()).append(suffix);
			if (it.hasNext()) {
				sb.append(delim);
			}
		}
		return sb.toString();
	}
	
	public static <T> String arrayToDelimitedString(T[] t, String delim, String prefix, String suffix) {
		if (t.length == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < t.length; i++) {
			sb.append(prefix).append(t[i]).append(suffix);
			if (i < t.length - 1) {
				sb.append(delim);
			}
		}
		return sb.toString();
	}
	
	public static String changeIntegerArrayToString(Integer[] ints){
		StringBuffer strb = new StringBuffer();
		for(int i=0;ints!=null&&i<ints.length;i++){
			strb.append(ints[i]).append(",");
		}
		if(strb.length()>1){
			strb.delete((strb.length()-1),strb.length());
		}
		return strb.toString();
	}
	
    public static String getFormatString(String oriStr, Object...paras){
        return MessageFormat.format(oriStr, paras);
    }
	
	
	public static void main(String[]args)throws Exception{
		 //Random ra = new Random();
//		 for(int i=0; i<500; i++){
//			 //System.out.println(ra.nextInt(900)+100);
//			 System.out.println(getRandom(3));
//		 }
		Integer[] is = {new Integer(1),new Integer(1)};
		System.out.println(changeIntegerArrayToString(is));
	}
}
