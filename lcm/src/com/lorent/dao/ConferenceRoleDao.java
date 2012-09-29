package com.lorent.dao;

import java.util.List;

import com.lorent.model.AuthorityBean;
import com.lorent.model.ConferenceRoleBean;

public interface ConferenceRoleDao extends IGenericDao<ConferenceRoleBean,Integer>{

	public void batchUpdateByIds(Integer[] ids) throws Exception;
	
	public List getRolesByConferenceTypeId(Integer[] typeIds) throws Exception;
	
	public List<AuthorityBean> getAuthorityByRoleId(int roleId)throws Exception;
}
