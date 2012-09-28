package com.lorent.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import com.lorent.exception.CustomSqlException;
/**
 * 通用DAO接口
 * @author gary
 * @since 2010-09-14
 * @version 1.0
 * @param <T>
 * @param <ID>
 */
public interface IGenericDao<T,ID extends Serializable> extends Serializable{
	/**
	 * 获取id对应的记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	T get(ID id)throws CustomSqlException;
	
	/**
	 * 获取id列表的数据list
	 * @param ids
	 * @return
	 * @throws CustomSqlException
	 */
	List<T> get(ID[] ids)throws CustomSqlException;
	/**
	 * 获取所有的记录
	 * @return
	 * @throws Exception
	 */
	List<T>getAll()throws CustomSqlException;
	/**
	 * 获取与example相似的记录
	 * @param example
	 * @return
	 * @throws Exception
	 */
	List<T> getByExample(T example)throws CustomSqlException;
	/**
	 * 获取与Example相似的记录，通过enableLike指定结果集中的对象
	 * 相应的属性和example是否完全一致
	 * @param example
	 * @param enaableLike
	 * @return
	 * @throws CustomSqlException
	 */
	List<T> getByExample(T example,boolean enableLike)throws CustomSqlException;
	/**
	 * 将对象持久化
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	ID save(T entity)throws CustomSqlException;
	/**
	 * 将对象对应的记录从数据库中删除
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	boolean delete(T entity)throws CustomSqlException;
	/**
	 * 将对象对应的记录从数据库中删除
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	boolean delete(ID[]ids)throws CustomSqlException;
	/**
	 * 将对象对应的记录从数据库中删除
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	boolean delete(Collection<T>entitys)throws CustomSqlException;
	/**
	 * 将数据跟新到数据库
	 * @param entity
	 * @throws Exception
	 */
	void update(T entity)throws CustomSqlException;
	/**
	 * 将对象持久化
	 * @param entity
	 * @throws CustomSqlException
	 */
	void saveOrUpdate(T entity)throws CustomSqlException;
	/**
	 * 获取分页查询的记录
	 * @param example
	 * @param pageResultMap
	 * @throws CustomSqlException
	 */
	void getSubPageResult(T example,Map<String,Object> pageResultMap) throws CustomSqlException;
	/**
	 * 获取分页查询的记录
	 * QBC
	 * @param pageResultMap
	 * @param criteriaMap
	 * @throws CustomSqlException
	 */
	void getSubPageResult(Map<String, Object>pageResultMap,Map<String, Object>criteriaMap)throws CustomSqlException;
	/**
	 * 获取分页查询结果
	 * @param hql
	 * @param map
	 * @return
	 */
	public void getSubPageResult(String hql, Map<String,Object> map);
	/**
	 * 获取分页查询结果
	 * QBC
	 * @param hql
	 * @param map
	 * @return
	 */
	public void getSubPageResult(DetachedCriteria criteria,Map<String, Object>pageMap)throws CustomSqlException;
	/**
	 * 通过criteria获取对象
	 * @param criteria
	 * @return
	 * @throws CustomSqlException
	 */
	public List<T> getByCriteria(DetachedCriteria criteria)throws CustomSqlException;
	/**
	 * 获取满足条件的对象的数量
	 * @param criteria
	 * @return
	 * @throws CustomSqlException
	 */
	public Integer getRecordCount(DetachedCriteria criteria)throws CustomSqlException;
	/**
	 * 
	 * @param sql
	 * @return
	 */
	public List queryBySql(final String sql);
	/**
	 * 
	 * @param hql
	 * @return
	 */
	public List queryByHql(final String hql);
	public void merge(T entity)throws CustomSqlException;
	public int executeUpdate(final String hql,final Map<String,Object> params)throws CustomSqlException;
	public int executeUpdate(final String hql,final List list)throws CustomSqlException;
	public void getSubPageResult(String hql, Map<String,Object> map, List list);
	public int queryByHqlGetCount(final String hql);
	public Session fetchSession();
	public void commitTransaction();
	public void beginTransaction();
}
