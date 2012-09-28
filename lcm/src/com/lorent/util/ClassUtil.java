package com.lorent.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.lorent.exception.ArgsException;
import com.lorent.model.BaseModel;
import com.lorent.model.CronConferenceBean;
import com.lorent.model.CustomerBean;
import com.lorent.model.UserBean;

public class ClassUtil {
	public static<P,C extends P> void getSuperObjectFromChild(P parent,C child)throws Exception {
		if(parent==null)
			throw new ArgsException("args.objnotinit");
		for(Method method:child.getClass().getMethods()){
			String methodName = method.getName();
			if(methodName.indexOf("get")!=0)continue;
			methodName = methodName.replace("get", "");
			Object result = method.invoke(child, null);
			if(result==null)continue;
			Class clazz = result.getClass();
			if(result instanceof Class)continue;
			if(result instanceof Collection||result instanceof Map)
				clazz = clazz.getInterfaces()[0];
			try {
				Method setMethod = parent.getClass().getMethod("set"+methodName, new Class[]{clazz});
				setMethod.invoke(parent, new Object[]{result});
			} catch (Exception e) {
				continue;
			}
		}
	}
	
	
	public static DetachedCriteria createCriteriaByMap(Map<String, Criterion> criteriaMap, Class clazz){
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);
		Set<String> temp = new HashSet<String>();
		for (String key:criteriaMap.keySet()) {
			Criterion criterion = criteriaMap.get(key);
			String[]keyStrs = key.split("\\.");
			if(keyStrs.length>1){ 
				String alias = "";
				String path = "";
				for(int i=0;i<keyStrs.length-1;i++){
					path = path + keyStrs[i];
					alias = alias + keyStrs[i];
					if(temp.contains(alias)){//去掉重复
						path = alias + ".";
						continue;
					}else{
						temp.add(alias);
						dc.createAlias(path, alias);
						path = alias + ".";
					}
				}
			}
			dc.add(criterion);
		}
		return dc;
	}
	
	public static void parseExampleToCriteriaMap(Map<String, Criterion> map, Object example, String prefix){
		prefix = prefix==null?"":prefix;
		if(example==null){
			return;
		}
		Class clazz = example.getClass();
		List<Field> fields = getClassField(clazz);	
		try{
			for (Field field : fields) {
					
					field.setAccessible(true);
					String name = field.getName();
					if(field.get(example)!=null){
						Object object = field.get(example);
						if (object instanceof BaseModel) {
							parseExampleToCriteriaMap(map, object, prefix+name+".");
						}else if (object instanceof Collection) {
	
						}else {
							if(name.equals("serialVersionUID")){
								continue;
							}else{
								String temp = "";
								if("".equals(prefix)){
									temp = name;
								}else{
									temp = prefix.replace(".", "") + "." + name;
								}
								if(object instanceof String){
									if(StringUtil.isNotEmpty((String)object)){
										map.put(prefix + name, Restrictions.like(temp, (String)object, MatchMode.ANYWHERE));
									}
								}else{
									map.put(prefix + name, Restrictions.eq(temp, object));
								}
							}
						}
					}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static List<Field> getClassField(Class clazz){
		List<Field> fields = new ArrayList<Field>();
		while(!clazz.equals(Object.class)){
			fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
			clazz = clazz.getSuperclass();
		}
		return fields;
	}
	
	public static void main(String[] args) {
		Map<String, Criterion> map = new HashMap<String, Criterion>();
		CronConferenceBean conf = new CronConferenceBean();
		conf.setConfSubject("conf1353");
		UserBean user = new UserBean();
		conf.setOwner(user);
		user.setUsername("rgm");
		CustomerBean customer = new CustomerBean();
		customer.setCustomerName("customer");
		user.setCustomer(customer);
		parseExampleToCriteriaMap(map, conf, null);
		
//		getClassField(CronConferenceBean.class);
		
		System.out.println();
		
	}
}
