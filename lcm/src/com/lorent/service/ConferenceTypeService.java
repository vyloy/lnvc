package com.lorent.service;

import com.lorent.dao.ConferenceTypeDao;
import com.lorent.model.ConferenceTypeBean;

public interface ConferenceTypeService extends IGenericService<ConferenceTypeDao,ConferenceTypeBean,Integer>{

	public Integer createConferenceType(ConferenceTypeBean conferenceType,Integer[] roleIds) throws Exception;
	
	public boolean renewConferenceType(ConferenceTypeBean conferenceType,Integer[] roleIds) throws Exception;
	
	public boolean removeConferenceType(Integer[] ids) throws Exception;
	
}
