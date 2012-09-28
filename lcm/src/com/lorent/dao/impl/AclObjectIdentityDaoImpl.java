package com.lorent.dao.impl;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import com.lorent.model.*;
import com.lorent.dao.GenericDaoImpl;
import com.lorent.dao.IAclObjectIdentityDao;
/**
 * 用于操作表acl_objectidentity数据的dao实现类
 * @author gary
 * @version 1.0
 * @since 2010-09-15
 */
public class AclObjectIdentityDaoImpl extends GenericDaoImpl<AclObjectIdentity, Long>  implements IAclObjectIdentityDao {
	/**
	 * 根据acl_class id和oid获取acl_objectidentity表的数据
	 */
    public AclObjectIdentity findByObjectId(long objectIdClass,
            long objectIdIdentity) {
        DetachedCriteria criteria = DetachedCriteria.forClass(AclObjectIdentity.class);
        criteria.add(Expression.eq("objectId", objectIdIdentity))
                .createCriteria("aclClass")
                .add(Expression.eq("id", objectIdClass));

        List<AclObjectIdentity> list = getHibernateTemplate().findByCriteria(criteria);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }


}