package com.lorent.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.lorent.dao.ConfUserRoleDao;
import com.lorent.dao.GenericDaoImpl;
import com.lorent.model.ConfUserRoleBean;
import com.lorent.util.StringUtil;

public class ConfUserRoleDaoImpl extends GenericDaoImpl<ConfUserRoleBean,Integer> implements ConfUserRoleDao{

	@Override
	public void removeConfUserRolesByConferenceId(Integer conferenceId)
			throws Exception {
		List list = new ArrayList();
		list.add(conferenceId);
		executeUpdate("delete from ConfUserRoleBean ua where ua.conferenceUserId in (select cu.userId from ConferenceUserBean cu where cu.conferenceId=?)", list);
		list = null;
	}

	public void removeConfUserRolesByConferenceIds(Integer[] conferenceIds) throws Exception{
		List list = null;
		String strIds = StringUtil.changeIntegerArrayToString(conferenceIds);
		executeUpdate("delete from ConfUserRoleBean ur where ur.conferenceUserId in (select id from ConferenceUserBean cu where cu.conferenceId in ("+strIds+"))", list);
	}

	@Override
	public List<String> getConfUserRole(String confno, String lccno)
			throws Exception {
		
		return null;
	}
	
}
