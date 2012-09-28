package com.lorent.dao.impl;

import java.util.List;

import com.lorent.dao.ConferenceRoleDao;
import com.lorent.dao.GenericDaoImpl;
import com.lorent.model.AuthorityBean;
import com.lorent.model.ConferenceRoleBean;
import com.lorent.util.StringUtil;

public class ConferenceRoleDaoImpl extends GenericDaoImpl<ConferenceRoleBean,Integer> implements ConferenceRoleDao{

	@Override
	public void batchUpdateByIds(Integer[] ids) throws Exception {
		
		List list = null;
		String strIds = StringUtil.changeIntegerArrayToString(ids);
		executeUpdate("update ConferenceRoleBean cr set cr.del=0 where cr.id in ("+strIds+")",list);
		
	}

	@Override
	public List getRolesByConferenceTypeId(Integer[] typeIds) throws Exception {
		String strIds = StringUtil.changeIntegerArrayToString(typeIds);
		List list = queryByHql("select new ConferenceRoleBean(cr.id,cr.roleName) from ConferenceRoleBean cr,ConferenceTypeRoleBean tr where cr.del=1 and cr.id=tr.conferenceRoleId and tr.conferenceTypeId in ("+strIds+")");
		return list;
	}

	@Override
	public List<AuthorityBean> getAuthorityByRoleId(int roleId)
			throws Exception {
		String hql = "select a from AuthorityBean a, ConferenceRoleBean r, ConfRoleAuthorityBean ra " +
				"where a.id = ra.authorityId and r.id = ra.roleId " +
				"and r.id = " + roleId;
		List<AuthorityBean> ret = queryByHql(hql);
		return ret;
	}
	
	

}
