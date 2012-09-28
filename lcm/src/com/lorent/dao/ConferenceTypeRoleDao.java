package com.lorent.dao;

import java.util.List;

import com.lorent.model.ConferenceTypeRoleBean;

public interface ConferenceTypeRoleDao extends IGenericDao<ConferenceTypeRoleBean,Integer>{

	public void removeConferenceTypeRoleByRoleIds(Integer[] ids) throws Exception;
	
	public List<ConferenceTypeRoleBean> getConfRolesByType(int conftype);
}
