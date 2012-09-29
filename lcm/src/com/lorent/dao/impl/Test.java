package com.lorent.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lorent.dao.RoleDao;
import com.lorent.model.BaseModel;
import com.lorent.model.BillingBean;
import com.lorent.model.CityBean;
import com.lorent.model.ConfNoStatusBean;
import com.lorent.model.ConferenceBean;
import com.lorent.model.ConferenceNoBean;
import com.lorent.model.CronConferenceBean;
import com.lorent.model.CustomerBean;
import com.lorent.model.DepartmentBean;
import com.lorent.model.McuMixerBean;
import com.lorent.model.McuServerBean;
import com.lorent.model.RoleBean;
import com.lorent.model.UserBean;
import com.lorent.model.UserStatusBean;
import com.lorent.service.impl.ServiceFacade;
import com.lorent.trigger.CronConfStartTrigger;
import com.lorent.trigger.QuartzTrigger;
import com.lorent.util.ClassUtil;
import com.lorent.util.Constant;
import com.lorent.util.MD5Builder;
import com.lorent.util.McuUtil;
import com.lorent.util.PageUtil;
import com.lorent.util.PropertiesUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		ApplicationContext ac = new FileSystemXmlApplicationContext(new String[]{
				"/WebRoot/WEB-INF/applicationContext-dao.xml",
		});
		DaoFacade daoFacade = (DaoFacade)ac.getBean("daoFacade");
		DetachedCriteria criteria = DetachedCriteria.forClass(ConferenceBean.class);
		criteria.createAlias("customer", "customer")
			.createAlias("customer.mcuServer", "server")
			.add(Restrictions.eq("server.serverIp", "10.168.150.73"))
			.add(Restrictions.eq("server.serverStatus", Constant.RECORD_VALID))
			.add(Restrictions.eq("confStatus", Constant.CONF_STATUS_ONGOING));
		List<ConferenceBean>confs = daoFacade.getConferenceDao().getByCriteria(criteria);
		for(ConferenceBean conf:confs){
			System.out.println(conf.getConfSubject());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static void parseExampleToCriteriaMap(Map<String, Object>criteriaMap,Object example,String prefix) {
		prefix = prefix==null?"":prefix;
		Class clazz = example.getClass();
		Field[]fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			try {
				fields[i].setAccessible(true);
				String name = fields[i].getName();
				if(fields[i].get(example)!=null){
					Object object = fields[i].get(example);
					if (object instanceof BaseModel) {
						parseExampleToCriteriaMap(criteriaMap,object, prefix+name+".");
					}else if (object instanceof Collection) {
//						Collection collection = (Collection)object;			
//						Iterator it = collection.iterator();
////						parseExampleToCriteriaMap(criteriaMap, it.next(), prefix+name+".");
//						prefix += name+".";
//						Object itObject = it.next();
//						if(itObject==null)continue;
//						Class itClass = it.next().getClass();
//						Field[]itFields = itClass.getDeclaredFields();
//						for(int j=0;j<itFields.length;j++){
//							itFields[j].setAccessible(true);
//							Object itFieldObject = itFields[j].get(itObject);
//							if(itFieldObject==null)continue;
//							if(itFieldObject instanceof BaseModel|| itFieldObject instanceof Collection)continue;
//							criteriaMap.put(prefix, itFieldObject);
//						}
					}else {
						if(name.equals("serialVersionUID"))continue;
						criteriaMap.put(prefix+name, object);
					}
				}
//					System.out.println(fields[i].get(example).getClass());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} 
			catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}