package com.lorent.dao;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lorent.exception.CustomSqlException;
import com.lorent.exception.NoDataYetException;
import com.lorent.util.PageUtil;
/**
 * 通用的DAO基类
 * @author gary
 * @since 2010-09-14
 * @version 1.0
 * @param <T>
 * @param <ID>
 */
public class GenericDaoImpl<T,ID extends Serializable> extends HibernateDaoSupport implements IGenericDao<T, ID> {
	private Class<T>presistentClass;
	/**
	 * 无参构造方法
	 */
	public GenericDaoImpl() {
		this.presistentClass = (Class<T>)((ParameterizedType)getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	/**
	 * 读取presistenClass
	 * @return
	 */
	public Class<T> getPresistentClass() {
		return this.presistentClass;
	}
	/**
	 * 将对象持久化
	 * @param entity
	 * @return
	 */
	public ID save(T entity)throws CustomSqlException {
		ID id;
		try {
			id = (ID)getHibernateTemplate().save(entity);
		} catch (DataAccessException e) {
			e.printStackTrace();
			String message = e.getMessage();
			if(message!=null && message.indexOf("com.lorent.model.AuthorityBean")>-1 && message.indexOf("org.hibernate.exception.ConstraintViolationException")>-1){
				throw new CustomSqlException("authority.insert.markerror",e);
			}else{
				throw new CustomSqlException("sql.savefail",e);
			}
		}
		return id;
	}
	/**
	 * 将对象的记录从数据库中删除
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public boolean delete(T entity)throws CustomSqlException {
		try {
			getHibernateTemplate().delete(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomSqlException("sql.deletefail",e);
		}
		return true;
	}
	
	public boolean delete(ID[]ids)throws CustomSqlException {
		try {
			List<T>list = new ArrayList<T>();
			for(int i=0;i<ids.length;i++){
				list.add(get(ids[i]));
			}
			getHibernateTemplate().deleteAll(list);
		}catch (CustomSqlException e) {
			e.printStackTrace();
			throw new CustomSqlException("sql.deletefail",e);
		}catch (Exception e) {
			e.printStackTrace();
			throw new CustomSqlException("sql.deletefail",e);
		}
		return true;
	}
	
	public boolean delete(Collection<T>entitys)throws CustomSqlException {
		try {
			if(entitys!=null && entitys.size()>0){
				getHibernateTemplate().deleteAll(entitys);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomSqlException("sql.deletefail",e);
		}
		return true;
	}
	/**
	 * 获取与example相似的记录
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public List<T> getByExample(T example)throws CustomSqlException {
		List<T>list = null;
		try {
			list = getHibernateTemplate().findByExample(example);
		} catch (DataAccessException e) {
			throw new CustomSqlException("sql",e);
		}
		if(list==null||list.size()==0)return null;
		return list;
	}
	public List<T> getByExample(final T example,boolean enableLike)throws CustomSqlException {
		if(!enableLike)return getByExample(example);
		else {
			List<T>resultList = null;
			resultList = (List<T>)getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					Criteria criteria = null;
					try {
						 criteria = session.createCriteria(example.getClass())
											.add(Example.create(example)
													.enableLike(MatchMode.ANYWHERE));
						return criteria.list();
					} finally{
						if(session!=null&&session.isOpen())
							session.close();
					}
				}
			});
			if(resultList==null||resultList.size()==0)return null;
			return resultList;
		}
	}
	/**
	 * 跟新记录
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public void update(T entity)throws CustomSqlException {
		try {
			getHibernateTemplate().update(getHibernateTemplate().merge(entity));
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			String message = e.getMessage();
			if(message!=null && message.indexOf("com.lorent.model.AuthorityBean")>-1 && message.indexOf("org.hibernate.exception.ConstraintViolationException")>-1){
				throw new CustomSqlException("authority.insert.markerror",e);
			}else{
				throw new CustomSqlException("sql.savefail",e);
			}
		} 
	}
	/**
	 * 获取所有记录
	 * @return T
	 */
	public List<T> getAll()throws CustomSqlException {
		List<T>list = null;
		try {
			list = getHibernateTemplate().loadAll(presistentClass);
		} catch (DataAccessException e) {
			throw new CustomSqlException("sql",e);
		}
		if(list==null||list.size()==0)return null;
		return list;
	}
	/**
	 * 获取id对应的记录
	 * @return T
	 * @throws Exception
	 */
	public T get(ID id) throws CustomSqlException {
		T entity;
		try {
			entity = (T)getHibernateTemplate().get(presistentClass, id);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new CustomSqlException("sql",e);
		}
//		if(entity==null)throw new CustomSqlException("sql.norecord");
		return entity;
	}
	
	public List<T> get(ID[] ids)throws CustomSqlException{
		List<T> list = new ArrayList<T>();
		try{
			for(ID id : ids){
				list.add(get(id));
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new CustomSqlException("sql",e);
		}
		return list;
	}
	
	/**
	 * 将对象持久化
	 */
	public void saveOrUpdate(T entity)throws CustomSqlException{
		 try {
			getHibernateTemplate().saveOrUpdate(entity);
		} catch (DataAccessException e) {
			//System.out.println("====================" + e.getMessage());
			e.printStackTrace();
			String message = e.getMessage();
			if(message!=null && message.indexOf("com.lorent.model.AuthorityBean")>-1 && message.indexOf("org.hibernate.exception.ConstraintViolationException")>-1){
				throw new CustomSqlException("authority.insert.markerror",e);
			}else{
				throw new CustomSqlException("sql.savefail",e);
			}
			
		}
	}
	/**
	 * 获取分页查询的结果
	 * QBE
	 */
	public void getSubPageResult(final T example, final Map<String, Object> map) throws CustomSqlException {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				initSubpageMap(map);
				DetachedCriteria dc = DetachedCriteria.forClass(presistentClass);
				Example cExample = Example.create(example);
				if(PageUtil.enableLike){
					dc.add(cExample.enableLike(MatchMode.ANYWHERE));
				}else {
					dc.add(cExample);
				}
				Criteria criteria = dc.getExecutableCriteria(session);
				try {
					setResultCont(criteria);
					fixPageMap(criteria, map);
				} catch (NoDataYetException e) {
					throw e;
				} catch (CustomSqlException e) {
					e.printStackTrace();
					throw new HibernateException(e.getMessage(),e);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					throw new HibernateException(e.getMessage(),e);
				} catch (Exception e) {
					throw new HibernateException(e.getMessage(),e);
				} finally{
					if(session!=null&&session.isOpen())
						session.close();
				}
				return null;
			}
		});
	}
	/**
	 * 获取分页查询的结果
	 * QBC 
	 */
	public void getSubPageResult(final Map<String, Object> map,final Map<String, Object> criteriaMap) throws CustomSqlException {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Set<String> temp = new HashSet<String>();
				initSubpageMap(map);
				DetachedCriteria dc = DetachedCriteria.forClass(presistentClass);
				try {
					for (String key:criteriaMap.keySet()) {
						Object value = criteriaMap.get(key);
						String[]keyStrs = key.split("\\.");
						if(keyStrs.length>=1){ 
							String alias = "";
							for(int i=0;i<keyStrs.length-1;i++){
								if(!temp.contains(keyStrs[i])){
									dc.createAlias(alias+keyStrs[i], keyStrs[i]);
									temp.add(keyStrs[i]);
								}
								alias = keyStrs[i]+".";		
							}
							key = alias+keyStrs[keyStrs.length-1];
						}
						if(value instanceof java.lang.String){
							if(PageUtil.enableLike){
								dc.add(Restrictions.like(key, value.toString(),MatchMode.ANYWHERE));
							}else {
								dc.add(Restrictions.eq(key, value.toString()));
							}
						}else {
							dc.add(Restrictions.eq(key, value));
						}
					}
					Criteria criteria = dc.getExecutableCriteria(session);
					setResultCont(criteria);
					fixPageMap(criteria,map);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (CustomSqlException e) {
					e.printStackTrace();
					throw new HibernateException(e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
					throw new HibernateException(e.getMessage());
				} finally{
					if(session!=null&&session.isOpen())
						session.close();
				}
				return null;
			}
			
		});
	}
	/**
	 * 初始化分页查询的Criteria对象
	 * @param session
	 * @param map
	 * @return
	 */
	private void initSubpageMap(Map<String, Object>map){
		PageUtil.pageRow = Integer.parseInt(map.get(PageUtil.PAGEMAP_KEY_PAGEROW).toString());
		PageUtil.currentPage = Integer.parseInt(map.get(PageUtil.PAGEMAP_KEY_CURRENTPAGE).toString());
		PageUtil.resultCont = 0;
		PageUtil.pageCont = 0;
		String enableLike = map.get(PageUtil.PAGEMAP_KEY_ENABLELIKE)==null
								?null:map.get(PageUtil.PAGEMAP_KEY_ENABLELIKE).toString();
		PageUtil.enableLike = enableLike!=null&&Boolean.parseBoolean(enableLike);
	}
	/**
	 * 获取并设置分页查询中的记录总数
	 * @param criteria
	 * @throws CustomSqlException
	 */
	private void setResultCont(Criteria criteria)throws CustomSqlException {
		criteria.setProjection(Projections.rowCount());
//				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		PageUtil.resultCont = Integer.parseInt(criteria.uniqueResult().toString());
		if(PageUtil.resultCont==0)throw new NoDataYetException();
		PageUtil.pageCont = PageUtil.resultCont/PageUtil.pageRow;
		if(PageUtil.resultCont%PageUtil.pageRow!=0)PageUtil.pageCont++;
		if(PageUtil.currentPage>PageUtil.pageCont)throw new CustomSqlException("sql.alreadylastpage");
	}
	/**
	 * 填充分页查询的Map对象
	 * @param criteria
	 * @param map
	 * @throws CustomSqlException
	 */
	private void fixPageMap(Criteria criteria,Map<String, Object>map)throws CustomSqlException {
		String orderBy = map.get(PageUtil.PAGEMAP_KEY_ORDERBY)==null
				?null
				:map.get(PageUtil.PAGEMAP_KEY_ORDERBY).toString();
		String orderDesc = map.get(PageUtil.PAGEMAP_KEY_ORDERDESC)==null
				?null
				:map.get(PageUtil.PAGEMAP_KEY_ORDERDESC).toString();
		criteria.setProjection(null)
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.setFirstResult((PageUtil.currentPage-1)*PageUtil.pageRow)
				.setFetchSize(PageUtil.pageRow)
				.setMaxResults(PageUtil.pageRow);
		if(orderBy!=null&&orderDesc!=null){
			if(orderDesc.equals(PageUtil.DESC))criteria.addOrder(Order.desc(orderBy));
			else if(orderDesc.equals(PageUtil.ASC))criteria.addOrder(Order.asc(orderBy));
		}
//		criteria.setResultTransformer(Criteria.ROOT_ENTITY);
//		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		PageUtil.pageResult = criteria.list();
		if(PageUtil.pageResult==null||PageUtil.pageResult.size()==0)
			throw new NoDataYetException();
		map.put(PageUtil.PAGEMAP_KEY_PAGECONT, PageUtil.pageCont);
		map.put(PageUtil.PAGEMAP_KEY_PAGERESULT, PageUtil.pageResult);
		map.put(PageUtil.PAGEMAP_KEY_RESULTCONT, PageUtil.resultCont);
		map.put(PageUtil.PAGEMAP_KEY_CURRENTPAGE, PageUtil.currentPage);
	}
	
	/**
	 * 获取分页查询结果
	 * @param hql
	 * @param map 参数
	 * @return
	 * @throws CustomSqlException 
	 */
	public void getSubPageResult(String hql, Map<String,Object> map){
		int pageRow = Integer.parseInt(map.get(PageUtil.PAGEMAP_KEY_PAGEROW).toString());
		int currentPage = Integer.parseInt(map.get(PageUtil.PAGEMAP_KEY_CURRENTPAGE).toString());
		Session session = getSession();
		try{
			Query countQuery = session.createQuery(hql);
			int rowcount = countQuery.list().size();//TODO 有待完善
			if(rowcount==0){
				map.put(PageUtil.PAGEMAP_KEY_PAGECONT, 0);
				map.put(PageUtil.PAGEMAP_KEY_PAGERESULT, new ArrayList());
				map.put(PageUtil.PAGEMAP_KEY_RESULTCONT, 0);
				map.put(PageUtil.PAGEMAP_KEY_CURRENTPAGE, 0);
				return;
			}
			Query listQuery = session.createQuery(hql);
			int maxPage = rowcount / pageRow + 1;
			if(currentPage <= 0 ){
				currentPage = 1;
			}else if(currentPage > maxPage){
				currentPage = maxPage;
			}
			listQuery.setFirstResult((currentPage -1)* pageRow);
			listQuery.setMaxResults(pageRow);
			List result = listQuery.list();
			map.put(PageUtil.PAGEMAP_KEY_PAGECONT, maxPage);
			map.put(PageUtil.PAGEMAP_KEY_PAGERESULT, result);
			map.put(PageUtil.PAGEMAP_KEY_RESULTCONT, rowcount);
			map.put(PageUtil.PAGEMAP_KEY_CURRENTPAGE, currentPage);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!=null&&session.isOpen())
				session.close();
		}
	}
	
	/**
	 * 获取分页查询结果
	 * @param hql
	 * @param map 参数
	 * @return
	 * @throws CustomSqlException 
	 */
	public void getSubPageResult(String hql, Map<String,Object> map, List list){
		int pageRow = Integer.parseInt(map.get(PageUtil.PAGEMAP_KEY_PAGEROW).toString());
		int currentPage = Integer.parseInt(map.get(PageUtil.PAGEMAP_KEY_CURRENTPAGE).toString());
		Session session = getSession();
		try{
			Query countQuery = session.createQuery(hql);
			for(int i=0;list!=null&&i<list.size();i++){
				countQuery.setParameter(i, list.get(i));
			}
			int rowcount = countQuery.list().size();//TODO 有待完善
			if(rowcount==0)throw new NoDataYetException();
			if(rowcount==0){
				map.put(PageUtil.PAGEMAP_KEY_PAGECONT, 0);
				map.put(PageUtil.PAGEMAP_KEY_PAGERESULT, new ArrayList());
				map.put(PageUtil.PAGEMAP_KEY_RESULTCONT, 0);
				map.put(PageUtil.PAGEMAP_KEY_CURRENTPAGE, 0);
				return;
			}
			Query listQuery = session.createQuery(hql);
			for(int i=0;list!=null&&i<list.size();i++){
				listQuery.setParameter(i, list.get(i));
			}
			int maxPage = rowcount / pageRow + 1;
			if(currentPage <= 0 ){
				currentPage = 1;
			}else if(currentPage > maxPage){
				currentPage = maxPage;
			}
			listQuery.setFirstResult((currentPage -1)* pageRow);
			listQuery.setMaxResults(pageRow);
			List result = listQuery.list();
			map.put(PageUtil.PAGEMAP_KEY_PAGECONT, maxPage);
			map.put(PageUtil.PAGEMAP_KEY_PAGERESULT, result);
			map.put(PageUtil.PAGEMAP_KEY_RESULTCONT, rowcount);
			map.put(PageUtil.PAGEMAP_KEY_CURRENTPAGE, currentPage);
		}catch(NoDataYetException nde){
			throw nde;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!=null&&session.isOpen())
				session.close();
		}
	}
	/**
	 * 获取分页查询结果
	 * QBC
	 */
	public void getSubPageResult(final DetachedCriteria dc, final Map<String, Object> pageMap) throws CustomSqlException {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				initSubpageMap(pageMap);
				Criteria criteria = dc.getExecutableCriteria(session);
				try {
					setResultCont(criteria);
					fixPageMap(criteria, pageMap);
				} catch (Exception e) {
					e.printStackTrace();
					throw new HibernateException(e.getMessage());
				} finally{
					if (session!=null&&session.isOpen()) {
						session.close();
					}
				}
				return null;
			}
		});
	}
	/**
	 * 通过criteria获取对象
	 */
	public List<T> getByCriteria(final DetachedCriteria criteria) throws CustomSqlException {
		final Map<String, List<T>>map = new HashMap<String, List<T>>();
		try {
			getHibernateTemplate().execute(new HibernateCallback(){
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					try {
						criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
						map.put("result", criteria.getExecutableCriteria(session).list());
					} catch (Exception e) {
						e.printStackTrace();
					} finally{
						if(session!=null&&session.isOpen())
							session.close();
					}
					return null;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomSqlException("sql");
		}
		return map.get("result");
	}
	
	public Integer getRecordCount(final DetachedCriteria criteria) throws CustomSqlException {
		final Map<String, Integer>map = new HashMap<String, Integer>();
		try {
			getHibernateTemplate().execute(new HibernateCallback(){
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					try {	
						criteria.setProjection(Projections.rowCount());
						map.put("count", Integer.parseInt(criteria.getExecutableCriteria(session).uniqueResult().toString()));
					} catch (Exception e) {
						e.printStackTrace();
					} finally{
						if(session!=null&&session.isOpen())
							session.close();
					}
					return null;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomSqlException("sql");
		}
		return map.get("count");
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
	
	public List queryByHql(final String hql){
		final List list = new ArrayList();
		getHibernateTemplate().execute(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				list.addAll(query.list());
				return null;
			}
			
		});
		return list;
	}
	
	
	public void merge(T entity) throws CustomSqlException {
		try {
			getHibernateTemplate().merge(entity);
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new CustomSqlException("sql");
		}
	}
	
	public int executeUpdate(final String hql,final Map<String,Object> params)throws CustomSqlException{
		try {
			int counts = (Integer)getHibernateTemplate().execute(new HibernateCallback(){

				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					Query query = session.createQuery(hql);
					if(params!=null){
						Iterator iter = params.entrySet().iterator();
						while (iter.hasNext()) {
							Map.Entry entry = (Map.Entry) iter.next();
							String key = (String)entry.getKey();
							Object val = entry.getValue();
							query.setParameter(key, val);
						}
					}
					int i = query.executeUpdate();
					return i;
				}
				
			});
			return counts;
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new CustomSqlException("sql");
		}
	}
	
	public int executeUpdate(final String hql,final List list)throws CustomSqlException{
		try {
			int counts = (Integer)getHibernateTemplate().execute(new HibernateCallback(){

				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					Query query = session.createQuery(hql);
					for(int i=0;list!=null&&i<list.size();i++){
						query.setParameter(i, list.get(i));
					}
					int i = query.executeUpdate();
					return i;
				}
				
			});
			return counts;
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new CustomSqlException("sql");
		}
	}
	@Override
	public int queryByHqlGetCount(String hql) {
		List list = queryByHql(hql);
		Object temp = list.get(0);
		if(temp instanceof BigInteger){
			return ((BigInteger)temp).intValue();
		}else if(temp instanceof Long){
			return ((Long)temp).intValue();
		}
		return -1;
	}
	
	public Session fetchSession(){
		return super.getSession();
	}
	
	public void beginTransaction(){
		fetchSession().beginTransaction();
	}
	
	public void commitTransaction(){
		try {
			fetchSession().connection().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
