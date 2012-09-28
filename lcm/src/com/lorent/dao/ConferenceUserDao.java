package com.lorent.dao;

import com.lorent.model.ConferenceUserBean;

public interface ConferenceUserDao extends IGenericDao<ConferenceUserBean,Integer>{

	public void removeConferenceUserByConferenceId(Integer conferenceId) throws Exception;
	
	public void removeConferenceUserByConferenceIds(Integer[] conferenceId) throws Exception;
	
	/**
	 * 判断是否已经制定用户角色
	 * @return
	 */
	public boolean userHasConfRole(String confno, String lccno)throws Exception;
}
