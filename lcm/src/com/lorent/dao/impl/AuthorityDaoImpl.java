package com.lorent.dao.impl;

import java.util.List;

import com.lorent.dao.AuthorityDao;
import com.lorent.dao.GenericDaoImpl;
import com.lorent.model.AuthorityBean;
import com.lorent.util.StringUtil;

public class AuthorityDaoImpl extends GenericDaoImpl<AuthorityBean,Integer> implements AuthorityDao{

	@Override
	public void removeAuthoritysByIds(Integer[] ids) throws Exception {
		List list = null;
		String strIds = StringUtil.changeIntegerArrayToString(ids);
		executeUpdate("update AuthorityBean au set au.del=0 where au.id in ("+strIds+")", list);
	}

}
