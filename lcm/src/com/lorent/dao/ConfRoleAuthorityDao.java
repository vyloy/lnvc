package com.lorent.dao;

import com.lorent.model.ConfRoleAuthorityBean;

public interface ConfRoleAuthorityDao extends IGenericDao<ConfRoleAuthorityBean,Integer>{

	
	public void removeByRoleIds(Integer[] ids) throws Exception;
	
	public void removeByAuthorityId(Integer[] ids) throws Exception;
	
}
