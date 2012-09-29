package com.lorent.dao;

import com.lorent.model.ConfUserAuthorityBean;

public interface ConfUserAuthorityDao extends IGenericDao<ConfUserAuthorityBean,Integer>{

	public void insertConfUserAuthoritysByRoleId(Integer userId,String roleId) throws Exception;
	
	public void removeConfUserAuthoritysByConferenceId(Integer conferenceId) throws Exception;
	
	public void removeConfUserAuthoritysByConferenceIds(Integer[] conferenceId) throws Exception;
}
