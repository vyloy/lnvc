package com.lorent.dao;

import java.util.List;
import java.util.Map;

import com.lorent.common.dto.LCMConferenceDto;
import com.lorent.common.dto.LCMRoleDto;
import java.util.Map;
import com.lorent.model.ConferenceNewBean;
import com.lorent.model.UserBean;

public interface ConferenceNewDao extends IGenericDao<ConferenceNewBean,Integer>{
	public LCMRoleDto getMyRoleAndPermission(String confno, String lccno)throws Exception;
	public Map<String, LCMRoleDto> getConfUserRole(String confno, String[] lccnos)throws Exception;
	public List getConferenceNewsByConfNo(String confNo) throws Exception;
	
	public List getUsersByConferenceId(Integer conferenceId) throws Exception;
	
	public void removeConferenceNewBeanByIds(Integer[] ids) throws Exception;
	
	public List getConferenceNoByConferenceIds(Integer[] ids) throws Exception;
	
	void getSearchSubPageMapResult(Map<String, Object> map, Map<String,Object> params) throws Exception;
	
	void getInitSubPageMapResult(Map<String,Object> map, Map<String,Object> params) throws Exception;
	
	public List getConferenceDto() throws Exception;
	
	public int getValidConfNum();
	
	//查询某一会议某一角色的用户
	public List<UserBean> getConfRoleUser(String confno, int roleId);
}
