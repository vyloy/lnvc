package com.lorent.service.impl;

import java.util.List;
import java.util.Set;

import com.lorent.dao.impl.DaoFacade;
import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.CityBean;
import com.lorent.model.ConferenceBean;
import com.lorent.model.ConferenceNoBean;
import com.lorent.model.SystemParamBean;
import com.lorent.util.PropertiesUtil;

public class StaticService {
	private static final String TAG = "StaticService::";
	private DaoFacade daoFacade;
	private ServiceFacade serviceFacade;
	/**
	 * 获取城市信息集合
	 * @return
	 */
	public Set<CityBean> getCitySet() {
		return daoFacade.getStaticDao().getCitySet();
	}
	/**
	 * 获取最后一条记录
	 * @param clazz
	 * @param idStr
	 * @return
	 */
	public<T> T lastRecord(Class<T> clazz,String idStr){
		return (T)daoFacade.getStaticDao().lastRecord(clazz, idStr);
	}
	/**
	 * 获取有ID和clazz指定的对象
	 * @param <T>
	 * @param id
	 * @param clazz
	 * @return
	 */
	public<T> T get(Integer id,Class<T>clazz) {
		return daoFacade.getStaticDao().get(id, clazz);
	}
	
	public<T> void update(T obj){
		daoFacade.getStaticDao().update(obj);
	}
	
	public<T> T merge(T obj){
		return daoFacade.getStaticDao().merge(obj);
	}
	/**
	 * 获取customerID对应的会议号码
	 * @param customerId
	 * @return
	 */
	public List<ConferenceNoBean> getNos(int customerId) {
		return daoFacade.getStaticDao().getConfNos(customerId);
	}
	public void removeConfNoStatus(ConferenceBean conference) {
		daoFacade.getStaticDao().removeNoStatus(conference);
	}
	
	public<T> List<T> getList(Class<T> clazz){
		return daoFacade.getStaticDao().getList(clazz);
	}
	
	public List<SystemParamBean> getSystemParamsByModule(String module)throws Exception{
		return daoFacade.getStaticDao().getSystemParamsByModule(module);
	}
	
	public void save(Object obj){
		daoFacade.getStaticDao().save(obj);
	}
	
	public void delete(Object entity){
		daoFacade.getStaticDao().delete(entity);
	}
	
	
	/* =========================== getters and setters ========================= */
	public DaoFacade getDaoFacade() {
		return daoFacade;
	}
	public void setDaoFacade(DaoFacade daoFacade) {
		this.daoFacade = daoFacade;
	}
	public ServiceFacade getServiceFacade() {
		return serviceFacade;
	}
	public void setServiceFacade(ServiceFacade serviceFacade) {
		this.serviceFacade = serviceFacade;
	}
}
