package com.lorent.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lorent.dao.ConferenceUserDao;
import com.lorent.model.ConferenceUserBean;
import com.lorent.service.ConferenceUserService;
import com.lorent.util.StringUtil;

public class ConferenceUserServiceImpl extends GenericServiceImpl<ConferenceUserDao,ConferenceUserBean,Integer> implements
ConferenceUserService{

	@Override
	public Map<Integer, List<Integer>> getConferenceUserIdsByRoleId(
			Integer conferenceId, Integer[] roleIds) throws Exception {
		
		String strIds = StringUtil.changeIntegerArrayToString(roleIds);
		List list = daoFacade.getConferenceUserDao().queryByHql("select cu.userId,ur.roleId from ConferenceUserBean cu,ConfUserRoleBean ur where ur.roleId in ("+strIds+") and cu.id=ur.conferenceUserId and cu.conferenceId=" + conferenceId.intValue());
		Map<Integer,List<Integer>> map = new HashMap<Integer,List<Integer>>();
		for(int i=0;i<list.size();i++){
			Object[] obj=(Object[])list.get(i);
			Integer userId = (Integer)obj[0];
			Integer roleId = (Integer)obj[1];
			if(map.containsKey(roleId)){
				map.get(roleId).add(userId);
			}else{
				List<Integer> users = new ArrayList<Integer>();
				users.add(userId);
				map.put(roleId, users);
			}
		}
		return map;
	}

}
