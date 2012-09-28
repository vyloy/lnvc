package com.lorent.dao;

import com.lorent.exception.CustomSqlException;
import com.lorent.model.SipConfBean;

public interface SipConfDao extends IGenericDao<SipConfBean,Integer> {
	SipConfBean getSipConfByLcc(String lcc)throws CustomSqlException;
}
