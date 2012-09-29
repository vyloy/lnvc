package com.lorent.dao;
import com.lorent.dao.IGenericDao;
import com.lorent.model.AclClass;
/**
 * 操作acl_class表数据的dao接口
 * @author gary
 * @version 1.0
 * @since 2010-09-15
 */
public interface IAclClassDao extends IGenericDao<AclClass, Long>{
	/**
	 * find record from acl_class by class name
	 * @param cls
	 * @return
	 */
    public AclClass findByClass(String cls);
}