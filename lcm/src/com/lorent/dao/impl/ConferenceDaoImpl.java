package com.lorent.dao.impl;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.lorent.dao.ConferenceDao;
import com.lorent.dao.GenericDaoImpl;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.ConferenceBean;
import com.lorent.util.Constant;

public class ConferenceDaoImpl extends GenericDaoImpl<ConferenceBean,Integer> implements ConferenceDao {

	public List<ConferenceBean> getUnTriggerConfs() throws CustomSqlException {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ConferenceBean.class);
			criteria.add(Restrictions.or(
								Restrictions.eq("confStatus", Constant.CONF_STATUS_NOT_START), 
								Restrictions.eq("confStatus", Constant.CONF_STATUS_ONGOING)));
			return getByCriteria(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomSqlException("sql",e);
		}
	}
}
