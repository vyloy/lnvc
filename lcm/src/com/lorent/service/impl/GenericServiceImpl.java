package com.lorent.service.impl;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.lorent.dao.IGenericDao;
import com.lorent.dao.impl.DaoFacade;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.BaseModel;
import com.lorent.service.IGenericService;
import com.lorent.util.ClassUtil;
import com.lorent.util.StringUtil;
public class GenericServiceImpl<T extends IGenericDao<E, ID>,E,ID extends Serializable>
									implements IGenericService<T, E, ID>,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private T dao;
	protected DaoFacade daoFacade;
	protected ServiceFacade serviceFacade;
	
	public boolean delete(E entity) throws CustomSqlException {
		return dao.delete(entity);
	}
	
	public boolean delete(ID[]ids) throws CustomSqlException{
		return dao.delete(ids);
	}
	
	public boolean delete(List<E>entitys) throws CustomSqlException{
		return dao.delete(entitys);
	}

	public E get(ID id) throws CustomSqlException {
		return dao.get(id);
	}

	public List<E> get(ID[] ids) throws CustomSqlException {
		return dao.get(ids);
	}

	public List<E> getAll() throws CustomSqlException {
		return dao.getAll();
	}

	public List<E> getByExample(E example) throws CustomSqlException {
		return dao.getByExample(example);
	}

	public ID save(E entity) throws CustomSqlException {
		return dao.save(entity);
	}

	public void update(E entity) throws CustomSqlException {
		dao.update(entity);
	}

	public T getDao() {
		return dao;
	}

	public void setDao(T dao) {
		this.dao = dao;
	}

	public DaoFacade getDaoFacade() {
		return daoFacade;
	}

	public void setDaoFacade(DaoFacade daoFacade) {
		this.daoFacade = daoFacade;
	}
	public void getSubPageResult(E example, Map<String,Object> pageMap)throws CustomSqlException {
		dao.getSubPageResult(example, pageMap);
	}
	public void getSubPageResult(String hql, Map<String,Object> map){
		dao.getSubPageResult(hql, map);
	}
	public void getSubPageResult(String hql, Map<String,Object> map,List params){
		dao.getSubPageResult(hql, map,params);
	}
	/**
	 * 查找与criteriaExample相匹配的方式
	 * 支持QBC的查询方式
	 * 不支持criteriaExample中的集合查询
	 */
	public void getSubPageResult(Map<String, Object> pageMap, E criteriaExample) throws CustomSqlException {
		Map<String, Object> criteriaMap = new HashMap<String, Object>();
		parseExampleToCriteriaMap(criteriaMap, criteriaExample, null);
		dao.getSubPageResult(pageMap, criteriaMap);
	}
	/**
	 * 将example转换为criteria查询条件集合
	 * @param criteriaMap
	 * @param example
	 * @param prefix
	 */
	private void parseExampleToCriteriaMap(Map<String, Object>criteriaMap,Object example,String prefix) {
		prefix = prefix==null?"":prefix;
		Class clazz = example.getClass();
		List<Field> fields = ClassUtil.getClassField(clazz);
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				String name = field.getName();
				if(field.get(example)!=null){
					Object object = field.get(example);
					if (object instanceof BaseModel) {
//						BaseModel model = (BaseModel)object;
//						if(model.getId()!=null)criteriaMap.put(prefix+name, model.getId());
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
					}else if(object instanceof String){
						if(StringUtil.isNotEmpty((String)object)){
							criteriaMap.put(prefix+name, object);
						}
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
	/**
	 * 分页查询数据
	 * QBC
	 */
	public void getSubPageResult(DetachedCriteria detachedCriteria, Map<String, Object> pageMap) throws CustomSqlException {
		dao.getSubPageResult(detachedCriteria, pageMap);
	}

	public List<E> getByCriteria(DetachedCriteria criteria) throws CustomSqlException {
		return dao.getByCriteria(criteria);
	}

	public Integer getRecordCount(DetachedCriteria criteria) throws CustomSqlException {
		return dao.getRecordCount(criteria);
	}

	public ServiceFacade getServiceFacade() {
		return serviceFacade;
	}

	public void setServiceFacade(ServiceFacade serviceFacade) {
		this.serviceFacade = serviceFacade;
	}

	
	public void saveOrUpdate(E entity) throws CustomSqlException {
		dao.saveOrUpdate(entity);
	}

	
}