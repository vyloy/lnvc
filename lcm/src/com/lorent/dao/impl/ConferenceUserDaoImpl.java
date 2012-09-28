package com.lorent.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.lorent.dao.ConferenceUserDao;
import com.lorent.dao.GenericDaoImpl;
import com.lorent.model.ConferenceUserBean;
import com.lorent.util.StringUtil;

public class ConferenceUserDaoImpl extends GenericDaoImpl<ConferenceUserBean,Integer> implements ConferenceUserDao{

	@Override
	public void removeConferenceUserByConferenceId(Integer conferenceId)
			throws Exception {
		List params = new ArrayList();
		params.add(conferenceId);
		executeUpdate("delete from ConferenceUserBean cu where cu.conferenceId=?", params);
		params = null;
	}

	public void removeConferenceUserByConferenceIds(Integer[] conferenceId) throws Exception{
		List list = null;
		String strIds = StringUtil.changeIntegerArrayToString(conferenceId);
		executeUpdate("delete from ConferenceUserBean ur where ur.conferenceId in ("+strIds+")", list);
	}

	@Override
	public boolean userHasConfRole(String confno, String lccno)
			throws Exception {
		//不在ConfUser的用户没有角色
		String hql = "select cu.id " +
				"from ConferenceNewBean c ,ConferenceUserBean cu ,UserBean u " +
				"where c.id = cu.conferenceId and cu.userId = u.id and " +
				"u.lccAccount = '" + lccno + "' and c.confNo = '" + confno + "'";
		List ret = this.queryByHql(hql);
		if(ret.size() == 0){
			return false;
		}else{
			return true;
		}
	}
	
}
