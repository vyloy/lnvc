package com.lorent.dao.impl;

import java.util.List;

import com.lorent.dao.ConfRoleAuthorityDao;
import com.lorent.dao.GenericDaoImpl;
import com.lorent.model.ConfRoleAuthorityBean;
import com.lorent.util.StringUtil;

public class ConfRoleAuthorityDaoImpl extends GenericDaoImpl<ConfRoleAuthorityBean,Integer> implements ConfRoleAuthorityDao{

	@Override
	public void removeByRoleIds(Integer[] ids) throws Exception {
		List list = null;
		String strIds = StringUtil.changeIntegerArrayToString(ids);
		executeUpdate("delete from ConfRoleAuthorityBean ra where ra.roleId in ("+strIds+")", list);
	}

	@Override
	public void removeByAuthorityId(Integer[] ids) throws Exception {
		List list = null;
		String strIds = StringUtil.changeIntegerArrayToString(ids);
		executeUpdate("delete ConfRoleAuthorityBean ra where ra.authorityId in ("+strIds+")",list);
	}
	
	

}
