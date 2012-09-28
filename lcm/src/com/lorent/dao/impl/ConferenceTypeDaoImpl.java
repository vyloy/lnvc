package com.lorent.dao.impl;

import java.util.List;

import com.lorent.dao.ConferenceTypeDao;
import com.lorent.dao.GenericDaoImpl;
import com.lorent.model.ConferenceTypeBean;
import com.lorent.util.StringUtil;

public class ConferenceTypeDaoImpl extends GenericDaoImpl<ConferenceTypeBean,Integer> implements ConferenceTypeDao{

	@Override
	public void removeConferenceTypesByIds(Integer[] ids) throws Exception {
		List list = null;
		String strIds = StringUtil.changeIntegerArrayToString(ids);
		executeUpdate("update ConferenceTypeBean ct set ct.del=0 where ct.id in ("+strIds+")", list);
	}

}
