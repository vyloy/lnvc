package com.lorent.service;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.lorent.exception.CustomSqlException;

public interface Service<E,ID> {
	/**
	 * 获取id对应的记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	E get(ID id)throws CustomSqlException;
	/**
	 * 获取id列表的数据list
	 * @param ids
	 * @return
	 * @throws CustomSqlException
	 */
	List<E> get(ID[] ids)throws CustomSqlException;
	/**
	 * 获取所有的记录
	 * @return
	 * @throws Exception
	 */
	List<E>getAll()throws CustomSqlException;
	/**
	 * 获取与example相似的记录
	 * @param example
	 * @return
	 * @throws Exception
	 */
	List<E>getByExample(E example)throws CustomSqlException;
	/**
	 * 将对象持久化
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	ID save(E entity)throws CustomSqlException;
	/**
	 * 将对象对应的记录从数据库中删除
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	boolean delete(E entity)throws CustomSqlException;
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
	boolean delete(List<E>entitys)throws CustomSqlException;
	/**
	 * 将数据跟新到数据库
	 * @param entity
	 * @throws Exception
	 */
	void update(E entity)throws CustomSqlException;
	/**
	 * 获取分页查询结果
	 * @param example
	 * @param pageMap
	 */
	void getSubPageResult(E example,Map<String,Object> pageMap) throws CustomSqlException;
	/**
	 * 获取分页查询结果
	 * @param hql
	 * @param pageMap
	 */
	void getSubPageResult(String hql, Map<String,Object> map);
	/**
	 * 获取分页查询结果
	 * @param pageMap
	 * @param criteriaMap
	 * @throws CustomSqlException
	 */
	void getSubPageResult(Map<String, Object>pageMap,E criteriaExample)throws CustomSqlException;
	/**
	 * 获取分页查询结果
	 * QBC 查询条件为detachedCriteria
	 * @param detachedCriteria
	 * @param pageMap
	 * @throws CustomSqlException
	 */
	void getSubPageResult(DetachedCriteria detachedCriteria,Map<String, Object>pageMap)throws CustomSqlException;
	
	List<E>getByCriteria(DetachedCriteria criteria)throws CustomSqlException;
	
	Integer getRecordCount(DetachedCriteria criteria)throws CustomSqlException;
	public void getSubPageResult(String hql, Map<String,Object> map,List params);
}
