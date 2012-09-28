package com.lorent.dao.impl;

import com.lorent.dao.GenericDaoImpl;
import com.lorent.dao.RoleDao;
import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.RoleBean;

public class RoleDaoImpl extends GenericDaoImpl<RoleBean,Integer> implements RoleDao {

	public RoleBean createRole(RoleBean role) throws CustomSqlException,
			ArgsException {
		if(role==null)throw new ArgsException("args.inputisrequired");
		if(role.getRoleCode()==null)throw new ArgsException("args.rolecodeisrequired");
		RoleBean temp =  getByExample(role).get(0);
		if(temp!=null)role = temp;
		else {
			save(role);
			role = getByExample(role).get(0);
		}
		return role;
	}
}
