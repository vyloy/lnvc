package com.lorent.service;

import java.util.List;

import com.lorent.dao.ConferenceRoleDao;
import com.lorent.model.ConferenceRoleBean;

public interface ConferenceRoleService extends IGenericService<ConferenceRoleDao,ConferenceRoleBean,Integer>{

	public Integer createConferenceRole(ConferenceRoleBean conferenceRole,Integer[] authorityIds) throws Exception;
	
	public boolean renewConferenceRole(ConferenceRoleBean conferenceRole,Integer[] authorityIds) throws Exception;
	
	public boolean removeConferenceRole(Integer[] ids) throws Exception;
	
	public List<ConferenceRoleBean> getRolesByConferenceTypeId(Integer[] typeIds) throws Exception;
}
