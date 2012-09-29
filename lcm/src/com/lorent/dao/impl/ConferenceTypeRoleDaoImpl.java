package com.lorent.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.lorent.dao.ConferenceTypeRoleDao;
import com.lorent.dao.GenericDaoImpl;
import com.lorent.model.ConferenceTypeRoleBean;
import com.lorent.util.StringUtil;

public class ConferenceTypeRoleDaoImpl extends GenericDaoImpl<ConferenceTypeRoleBean,Integer> implements ConferenceTypeRoleDao{

	@Override
	public void removeConferenceTypeRoleByRoleIds(Integer[] ids)
			throws Exception {
		List list = null;
		String strIds = StringUtil.changeIntegerArrayToString(ids);
		executeUpdate("delete from ConferenceTypeRoleBean tr where tr.conferenceRoleId in ("+strIds+")", list);
	}

	@Override
	public List<ConferenceTypeRoleBean> getConfRolesByType(int conftype) {
		List<ConferenceTypeRoleBean> ret = new ArrayList<ConferenceTypeRoleBean>();
		String sql = "select tr.id, tr.conference_type_id, t.type_name, tr.conference_role_id, r.role_name, tr.maxnum, tr.minnum " +
				"from conference_type_role tr " +
				"join conference_type t on tr.conference_type_id = t.id " +
				"join conference_role r on tr.conference_role_id = r.id " +
				"where t.id = " + conftype;
		List list = queryBySql(sql);
		if(list == null || list.size() == 0){
			return ret;
		}
		for(Object obj : list){
			Object[] temp = (Object[])obj;
			ConferenceTypeRoleBean bean = new ConferenceTypeRoleBean();
			bean.setId(((BigInteger)temp[0]).intValue());
			bean.setConferenceTypeId(((BigInteger)temp[1]).intValue());
			bean.setConferenceTypeName((String)temp[2]);
			bean.setConferenceRoleId(((BigInteger)temp[3]).intValue());
			bean.setConferenceRoleName((String)temp[4]);
			bean.setMaxnum((Integer)temp[5]);
			bean.setMinnum((Integer)temp[6]);
			ret.add(bean);
		}
		return ret;
	}

}
