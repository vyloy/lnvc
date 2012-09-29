package com.lorent.dao;

import com.lorent.model.AuthorityBean;

public interface AuthorityDao extends IGenericDao<AuthorityBean,Integer>{

	public void removeAuthoritysByIds(Integer[] ids) throws Exception;
	
}
