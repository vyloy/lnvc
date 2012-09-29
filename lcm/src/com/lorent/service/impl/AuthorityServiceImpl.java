package com.lorent.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.lorent.dao.AuthorityDao;
import com.lorent.model.AuthorityBean;
import com.lorent.service.AuthorityService;
import com.lorent.util.StringUtil;

public class AuthorityServiceImpl extends GenericServiceImpl<AuthorityDao,AuthorityBean,Integer> implements
AuthorityService{

	public boolean removeAuthorities(Integer[] ids)throws Exception{
		//List list = null;
//		StringBuffer strb = new StringBuffer();
//		for(int i=0;i<ids.length;i++){
//			strb.append(ids[i]).append(",");
//		}
//		strb.append("-1");
//		String strIds = StrUtil.changeIntegerArrayToString(ids);
//		strIds += ",-1";
		daoFacade.getAuthorityDao().removeAuthoritysByIds(ids);
		daoFacade.getConfRoleAuthorityDao().removeByAuthorityId(ids);
		return true;
	}
	
}
