package com.lorent.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.lorent.dao.ConfUserAuthorityDao;
import com.lorent.dao.GenericDaoImpl;
import com.lorent.model.ConfUserAuthorityBean;
import com.lorent.util.StringUtil;

public class ConfUserAuthorityDaoImpl extends GenericDaoImpl<ConfUserAuthorityBean,Integer> implements ConfUserAuthorityDao{

	@Override
	public void insertConfUserAuthoritysByRoleId(Integer userId, String roleId)
			throws Exception {
		List list = new ArrayList();
		list.add(Integer.parseInt(roleId));
		executeUpdate("insert into ConfUserAuthorityBean (conferenceUserId,authorityId) select " + userId + ",ra.authorityId from ConfRoleAuthorityBean ra where ra.roleId=?", list);
		list = null;
	}

	@Override
	public void removeConfUserAuthoritysByConferenceId(Integer conferenceId)
			throws Exception {
		List list = new ArrayList();
		list.add(conferenceId);
		executeUpdate("delete from ConfUserAuthorityBean ua where ua.conferenceUserId in (select cu.userId from ConferenceUserBean cu where cu.conferenceId=?)", list);
		list = null;
	}
	
	public void removeConfUserAuthoritysByConferenceIds(Integer[] conferenceId)
		throws Exception {
		List list = null;
		String strIds = StringUtil.changeIntegerArrayToString(conferenceId);
		executeUpdate("delete from ConfUserAuthorityBean ua where ua.conferenceUserId in (select cu.id from ConferenceUserBean cu where cu.conferenceId in ("+strIds+"))", list);
	
	}

}
