package com.lorent.service;

import java.util.List;
import java.util.Map;

import com.lorent.dao.ConferenceUserDao;
import com.lorent.model.ConferenceUserBean;

public interface ConferenceUserService extends IGenericService<ConferenceUserDao,ConferenceUserBean,Integer>{

	
	Map<Integer,List<Integer>> getConferenceUserIdsByRoleId(Integer conferenceId,Integer[] roleIds) throws Exception;
	

}
