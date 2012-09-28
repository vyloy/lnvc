package com.lorent.dao;
import java.util.List;
import com.lorent.dao.IGenericDao;
import com.lorent.model.AclEntry;
/**
 * 操作acl_entry表记录的dao接口
 * @author gary
 * @version 1.0
 * @since 2010-09-15
 */
public interface IAclEntryDao extends IGenericDao<AclEntry, Long>{
	/**
	 * find acl_entry by oid
	 * @param aclObjectIdentity
	 * @return
	 */
    public List<AclEntry> findByIdentityId(long aclObjectIdentity);
    /**
     * delete record from acl_entry by oid
     * @param aclObjectId
     * @return
     */
    public int deleteByIdentityId(long aclObjectId);

}