package com.lorent.video.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import com.lorent.video.dao.BaseDao;
import com.lorent.video.dao.MonitorDao;
import com.lorent.video.dao.MonitorDaoImpl;

public class DaoProxyFactory implements InvocationHandler{

	static DaoProxyFactory instance = new DaoProxyFactory();
	static Map map = new HashMap();
	static{
		map.put(MonitorDaoImpl.class, newProxyInstance(new MonitorDaoImpl()));
	}
	private DaoProxyFactory(){
		
	}
	
	public static DaoProxyFactory getInstance(){
		return instance;
	}
	
	public static Object getProxyDaoObj(Class clazz){
		return map.get(clazz);
	}
	
	private Object proxyObj;
	public DaoProxyFactory(Object proxyObj){
		this.proxyObj = proxyObj;
	}
	
	private static Object newProxyInstance(Object proxyObj){
		return Proxy.newProxyInstance(proxyObj.getClass().getClassLoader(), proxyObj.getClass().getInterfaces(), new DaoProxyFactory(proxyObj));
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		try{
			if(proxyObj instanceof BaseDao){
				((BaseDao)proxyObj).setDb(DBAdapterImpl.DBHelper.getWritableDatabase());
			}
			Object obj = method.invoke(proxyObj, args);
			return obj;
		}catch(Exception ex){
			throw ex;
		}finally{
			if(proxyObj instanceof BaseDao){
				if(DBAdapterImpl.DBHelper!=null){
					DBAdapterImpl.DBHelper.close();
				}
			}
		}
		
	}

}
