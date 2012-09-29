package com.lorent.dao;
import com.lorent.model.AclObjectIdentity;
import com.lorent.dao.IGenericDao;
/**
 * 用于操作acl_objectidentity数据的dao接口
 * @author gary
 * @version 1.0
 * @since 2010-09-15
 */
public interface IAclObjectIdentityDao extends IGenericDao<AclObjectIdentity, Long>{
	/**
	 * 获取acl_objectidentity表中的记录
	 * @param objectIdClass
	 * @param objectIdIdentity
	 * @return
	 */
    AclObjectIdentity findByObjectId(long objectIdClass, long objectIdIdentity);
}