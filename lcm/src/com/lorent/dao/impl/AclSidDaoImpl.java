package com.lorent.dao.impl;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projections;
import com.lorent.model.*;
import com.lorent.dao.GenericDaoImpl;
import com.lorent.dao.IAclSidDao;
/**
 * 用于操作acl_sid 表数据的dao实现类
 * @author gary
 * @version 1.0
 * @since 2010-09-15
 */
public class AclSidDaoImpl extends GenericDaoImpl<AclSid, Long>  implements IAclSidDao {
	/**
	 * 根据sid和principal获取acl_sid表的记录
	 */
    public AclSid findBySid(String sid, boolean principal) {
        DetachedCriteria criteria = DetachedCriteria.forClass(AclSid.class);
        criteria.add(Expression.eq("sid", sid))
                .add(Expression.eq("principal", principal));

        List<AclSid> list = getHibernateTemplate().findByCriteria(criteria);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }    
}