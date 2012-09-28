package com.lorent.service;

import com.lorent.dao.AuthorityDao;
import com.lorent.model.AuthorityBean;

public interface AuthorityService extends IGenericService<AuthorityDao,AuthorityBean,Integer>{

	public boolean removeAuthorities(Integer[]ids)throws Exception;

}
