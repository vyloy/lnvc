package com.lorent.dao;

import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.RoleBean;

public interface RoleDao extends IGenericDao<RoleBean,Integer> {
	RoleBean createRole(RoleBean role)throws CustomSqlException,ArgsException;
}
