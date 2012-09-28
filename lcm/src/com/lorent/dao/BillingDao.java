package com.lorent.dao;

import com.lorent.exception.CustomSqlException;
import com.lorent.model.BillingBean;

public interface BillingDao extends IGenericDao<BillingBean,Integer> {
	BillingBean getBillingByConfId(Integer confId)throws CustomSqlException;
}
