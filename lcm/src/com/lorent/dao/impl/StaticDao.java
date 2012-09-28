package com.lorent.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.BaseModel;
import com.lorent.model.CityBean;
import com.lorent.model.ConfNoStatusBean;
import com.lorent.model.ConferenceBean;
import com.lorent.model.ConferenceNoBean;
import com.lorent.model.SystemParamBean;

public class StaticDao extends HibernateDaoSupport implements Serializable{
	private static final String TAG = "StaticDao::";
	
	/**
	 * 获取所有的城市信息
	 * @return
	 */
	public Set<CityBean> getCitySet() {
		Set<CityBean>citySet = new HashSet<CityBean>();
		List<CityBean> list = getHibernateTemplate().loadAll(CityBean.class);
		if(list!=null||list.size()!=0){
			for (CityBean city:list) {
				citySet.add(city);
			}
		}else {
			citySet = null;
		}
		return citySet;
	}
	public<T> T get(Integer id,Class<T>clazz) {
		return (T)getHibernateTemplate().get(clazz, id);
	}
	
	public<T> List<T> getList(Class<T> clazz){
		List<T> list = new ArrayList<T>();
		try{
			list = this.getHibernateTemplate().loadAll(clazz);
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}
		return list;
	}
	
	public<T> void update(T obj){
		getHibernateTemplate().update(obj);
	}
	
	public<T> T merge(T obj){
		return (T)(getHibernateTemplate().merge(obj));
	}
	
	public<T> List<T> getByExample(T example)throws Exception{
		return getHibernateTemplate().findByExample(example);
	}
	
	
	/**
	 * 获取数据库表中的最后一条记录
	 * @param clazz
	 * @param idStr
	 * @return
	 */
	public Object lastRecord(final Class clazz,final String idStr){
		return getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
					Criteria criteria = session.createCriteria(clazz)
							.setFirstResult(0)
							.setMaxResults(1)
							.addOrder(Order.desc(idStr));
					return criteria.uniqueResult();
			}
		});
	}
	/**
	 * 获取属于customerId对应的客户的会议号码
	 * @param customerId
	 * @return
	 */
	public List<ConferenceNoBean> getConfNos(int customerId) {
		ConferenceNoBean no = new ConferenceNoBean();
		no.setCustomerId(customerId);
		no.setIsUsed(false);
		return getHibernateTemplate().findByExample(no);
	}
	/**
	 * 根据会议号码获取会议号码对象
	 * @param code
	 * @return
	 */
	private ConferenceNoBean getNoByCode(String code) {
		ConferenceNoBean no = new ConferenceNoBean();
		no.setNoCode(code);
		List<ConferenceNoBean>list = getHibernateTemplate().findByExample(no);
		if(list==null||list.size()==0)return null;
		return list.get(0);
	}
	/**
	 * 切换会议号码的使用状态
	 * @param code
	 */
//	public void toggleNoUseStatus(String code) {
//	public void toggleNoUseStatus(ConferenceNoBean no) {
////		ConferenceNoBean no = getNoByCode(code);
//		if(no==null)no = new ConferenceNoBean();
//		if(no.getIsUsed()==null||no.getIsUsed())no.setIsUsed(false);
//		else no.setIsUsed(true);
//		getHibernateTemplate().saveOrUpdate(no);
//	}
	/**
	 * 删除会议号码中使用的时间段
	 * @param conference
	 */
	public void removeNoStatus(ConferenceBean conference){
		ConfNoStatusBean noStatus = new ConfNoStatusBean();
		noStatus.setStartTime(conference.getStartTime());
		noStatus.setEndTime(conference.getEndTime());
		Iterator<ConfNoStatusBean>it = conference.getConferenceNo().getNoStatus().iterator();
		while (it.hasNext()) {
			if(it.next().equals(noStatus))
				it.remove();
		}
//		toggleNoUseStatus(conference.getConferenceNo());
		getHibernateTemplate().saveOrUpdate(conference.getConferenceNo());
	}
	
	public void saveOrUpdate(BaseModel entity) {
		getHibernateTemplate().merge(entity);
	}
	
	public void deleteFreeNoStatus() {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				session.createQuery("delete from ConfNoStatusBean noStatus where noStatus.noId=null").executeUpdate();
				return null;
			}
			
		});
	}
	
	public Integer deleteByHql(final String hql) throws CustomSqlException {
		final Set<Integer>rowSet = new HashSet<Integer>();
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				rowSet.add(session.createQuery(hql).executeUpdate());
				return null;
			}
			
		});
		return rowSet.iterator().next();
	}
	
	public void delete(BaseModel entity) {
		getHibernateTemplate().delete(entity);
	}
	
	public List queryBySql(final String sql){
		final List list = new ArrayList();
		getHibernateTemplate().execute(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				SQLQuery query = session.createSQLQuery(sql);
				list.addAll(query.list());
				return null;
			}
			
		});
		return list;
	}
	
	public Object queryBySqlOneResult(final String sql){
		final List list = new ArrayList();
		getHibernateTemplate().execute(new HibernateCallback(){

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				SQLQuery query = session.createSQLQuery(sql);
				list.add(query.uniqueResult());
				return null;
			}
			
		});
		return list.get(0);
	}
	
	/**
	 * 获取系统参数
	 * @param key
	 * @return
	 */
	public String getSystemParams(String key)throws Exception{
		if(key==null){
			throw new ArgsException(TAG + "{args.temple} key");
		}
		SystemParamBean example = new SystemParamBean();
		example.setKey(key);
		example.setModule("lcm");
		List<SystemParamBean> objs = getByExample(example);
		if(objs.size()==0){
			return null;
		}
		if(objs.size() > 1){
			throw new CustomSqlException(TAG + "{sql.resultMoreThanOne}");
		}
		return objs.get(0).getValue();
	}
	
	public String getSystemParams(String key, String defaultValue)throws Exception{
		String value = getSystemParams(key);
		if(value == null){
			return defaultValue;
		}else{
			return value;
		}		
	}
	
	public void save(Object entity) {
		getHibernateTemplate().save(entity);
	}
	
	public void setSystemParams(String key, String value, String desc, boolean overwirte)throws Exception{
		SystemParamBean example = new SystemParamBean();
		example.setKey(key);
		example.setModule("lcm");
		List<SystemParamBean> objs = getByExample(example);
		if(objs.size() > 0){//存在该key
			if(overwirte){//更新
				SystemParamBean s = objs.get(0);
				s.setValue(value);
				s.setDescription(desc);
				update(s);
			}else{
				throw new CustomSqlException(TAG + "{sql.dataIsExist}");
			}
		}else{
			SystemParamBean sp = new SystemParamBean();
			sp.setDescription(desc);
			sp.setKey(key);
			sp.setValue(value);
			sp.setModule("lcm");
			save(sp);
		}
	}
}
