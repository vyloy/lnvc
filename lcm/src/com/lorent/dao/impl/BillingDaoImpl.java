package com.lorent.dao.impl;
import java.io.Serializable;
import java.sql.SQLException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.lorent.dao.BillingDao;
import com.lorent.dao.GenericDaoImpl;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.BillingBean;

public class BillingDaoImpl extends GenericDaoImpl<BillingBean,Integer> implements BillingDao, Serializable {

	private static final long serialVersionUID = 1L;

	public BillingBean getBillingByConfId(final Integer confId) throws CustomSqlException {
		BillingBean billing = null;
		try {
			billing = (BillingBean)getHibernateTemplate().execute(new HibernateCallback(){
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					Criteria criteria = session.createCriteria(BillingBean.class);
					return criteria.add(Restrictions.eq("conference.id", confId)).uniqueResult();
				}
				
			});
		} catch (DataAccessException e) {
			e.printStackTrace();
			billing = null;
		}
		return billing;
	}
}
