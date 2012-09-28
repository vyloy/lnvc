package com.lorent.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.lorent.dao.GenericDaoImpl;
import com.lorent.dao.SipConfDao;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.SipConfBean;

public class SipConfDaoImpl extends GenericDaoImpl<SipConfBean,Integer> implements SipConfDao {
	public SipConfBean getSipConfByLcc(String lcc) throws CustomSqlException {
		SipConfBean example = new SipConfBean();
		example.setName(lcc);
		List<SipConfBean>list;
		try {
			list = getHibernateTemplate().findByExample(example);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new CustomSqlException("sql",e);
		}
		if(list==null||list.size()==0)return null;
		return list.get(0);
	}

}
