package com.lorent.dao.impl;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import com.lorent.dao.GenericDaoImpl;
import com.lorent.dao.IAclEntryDao;
import com.lorent.model.AclEntry;
/**
 * 用于操作acl_entry表数据的dao实现类
 * @author gary
 * @version 1.0
 * @since 2010-09-15
 */
public class AclEntryDaoImpl extends GenericDaoImpl<AclEntry, Long> implements IAclEntryDao {
    public List<AclEntry> findByIdentityId(long aclObjectIdentity) {
        DetachedCriteria criteria = DetachedCriteria.forClass(AclEntry.class);
        criteria.createCriteria("aclObject")
                .add(Expression.eq("id", aclObjectIdentity));

        return getHibernateTemplate().findByCriteria(criteria);
    }
    public int deleteByIdentityId(long aclObjectId) {
        return getHibernateTemplate().bulkUpdate("delete AclEntry acl where acl.aclObject.id = ?",aclObjectId);
        
    }    
}