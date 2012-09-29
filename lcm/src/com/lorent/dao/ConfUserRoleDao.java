package com.lorent.dao;

import java.util.List;

import com.lorent.model.ConfUserRoleBean;

public interface ConfUserRoleDao extends IGenericDao<ConfUserRoleBean,Integer>{

	public void removeConfUserRolesByConferenceId(Integer conferenceId) throws Exception;
	
	public void removeConfUserRolesByConferenceIds(Integer[] conferenceIds) throws Exception;
	
	public List<String> getConfUserRole(String confno, String lccno)throws Exception;
}
