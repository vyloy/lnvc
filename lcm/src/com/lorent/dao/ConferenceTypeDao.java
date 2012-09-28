package com.lorent.dao;

import com.lorent.model.ConferenceTypeBean;

public interface ConferenceTypeDao extends IGenericDao<ConferenceTypeBean,Integer>{

	public void removeConferenceTypesByIds(Integer[] ids) throws Exception;
	
}
