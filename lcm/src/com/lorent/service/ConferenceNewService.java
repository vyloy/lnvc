package com.lorent.service;

import java.util.List;
import java.util.Map;

import com.lorent.common.dto.LCMConferenceDto;
import com.lorent.common.dto.LCMRoleDto;
import com.lorent.dao.ConferenceNewDao;
import com.lorent.model.ConferenceNewBean;
import com.lorent.model.UserBean;

public interface ConferenceNewService extends IGenericService<ConferenceNewDao,ConferenceNewBean,Integer>{

	public void createConferenceNew(ConferenceNewBean bean,String[] urIds) throws Exception;
	public void createConferenceNew(UserBean user,ConferenceNewBean bean,String[] urIds,boolean valid) throws Exception;
	public LCMRoleDto getMyRoleAndPermission(String confno, String lccno) throws Exception;
	public void renewConferenceNew(ConferenceNewBean conferenceNew, String[] urIds) throws Exception;
	public void renewConferenceNew(UserBean user,ConferenceNewBean conferenceNew, String[] urIds) throws Exception;
	public void removeConferenceNew(Integer[]ids) throws Exception;
	public void removeConferenceNew(UserBean user,Integer[] ids, boolean removeSipConf) throws Exception;
	public Map<String, LCMRoleDto> getConfUserRole(String confno, String[] lccnos) throws Exception;
	public Map<String,String> getAllConferences() throws Exception;
	public Map<String, LCMConferenceDto> getAllConferencesDto() throws Exception;
	void getSearchSubPageMapResult(Map<String,Object> map, Map<String,Object> params) throws Exception;
	
	void getInitSubPageMapResult(Map<String,Object> map, Map<String,Object> params) throws Exception;
	public String createConfNo(ConferenceNewBean conf, String customerCode, Integer conftype) throws Exception; 
	public List<ConferenceNewBean> getMcuRestoreConfs()throws Exception;
	public void removeSipConfByConfno(String confno) throws Exception;
	
	List getConferenceRoles() throws Exception;
	List getConferenceTypes() throws Exception;
	
	boolean canCreateConf(String lccno)throws Exception;
	boolean validateConfData(String[] paras, int conftype)throws Exception;
	boolean grantAuthority(String confNo,String lccno,String roleName) throws Exception;
	boolean revokeAuthority(String confNo,String lccno,String roleName) throws Exception;
	int inviteUserFromLcm(String inviter, String confno, String invitee)throws Exception;
	Object[] getCurrentForwardConfInfo()throws Exception;
}
