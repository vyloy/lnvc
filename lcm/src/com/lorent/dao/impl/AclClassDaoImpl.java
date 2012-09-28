package com.lorent.dao.impl;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import com.lorent.dao.GenericDaoImpl;
import com.lorent.dao.IAclClassDao;
import com.lorent.model.AclClass;
/**
 * 用于操作acl_class表数据的dao实现类
 * @author gary
 * @version 1.0
 * @since 2010-09-15
 */
public class AclClassDaoImpl extends GenericDaoImpl<AclClass, Long>  implements IAclClassDao{
	/**
	 * 根据class name读取acl_class的数据
	 * @return AclClass
	 */
    public AclClass findByClass(String cls) {
        DetachedCriteria criteria = DetachedCriteria.forClass(AclClass.class);
        criteria.add(Expression.eq("cls", cls));
        List<AclClass> list = getHibernateTemplate().findByCriteria(criteria);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

}