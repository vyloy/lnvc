package com.lorent.service.impl;

import java.util.List;

import com.lorent.dao.ConferenceRoleDao;
import com.lorent.model.ConfRoleAuthorityBean;
import com.lorent.model.ConferenceBean;
import com.lorent.model.ConferenceRoleBean;
import com.lorent.model.UserBean;
import com.lorent.service.ConferenceRoleService;
import com.lorent.util.Constant;
import com.lorent.util.MailUtil;
import com.lorent.util.StringUtil;
import com.lorent.util.ThreadLocaleUtil;

public class ConferenceRoleServiceImpl extends GenericServiceImpl<ConferenceRoleDao,ConferenceRoleBean,Integer> implements
ConferenceRoleService{

	private static final long serialVersionUID = 1L;

	/**
	 * 创建会议角色
	 */
	public Integer createConferenceRole(ConferenceRoleBean conferenceRole,Integer[] authorityIds) throws Exception {
		
		daoFacade.getConferenceRoleDao().save(conferenceRole);
		for(int i =0;i<authorityIds.length;i++){
			ConfRoleAuthorityBean raBean = new ConfRoleAuthorityBean();
			raBean.setAuthorityId(authorityIds[i]);
			raBean.setRoleId(conferenceRole.getId());
			daoFacade.getConfRoleAuthorityDao().save(raBean);
			raBean = null;
		}
		return conferenceRole.getId();
	}
	
	/**
	 * 更新会议角色
	 */
	public boolean renewConferenceRole(ConferenceRoleBean conferenceRole,Integer[] authorityIds) throws Exception {
		daoFacade.getConferenceRoleDao().update(conferenceRole);
		ConfRoleAuthorityBean raBean = new ConfRoleAuthorityBean();
		raBean.setRoleId(conferenceRole.getId());
		List<ConfRoleAuthorityBean> raList = daoFacade.getConfRoleAuthorityDao().getByExample(raBean);
		daoFacade.getConfRoleAuthorityDao().delete(raList);
		for(int i =0;i<authorityIds.length;i++){
			raBean = new ConfRoleAuthorityBean();
			raBean.setAuthorityId(authorityIds[i]);
			raBean.setRoleId(conferenceRole.getId());
			daoFacade.getConfRoleAuthorityDao().save(raBean);
			raBean = null;
		}
		return true;
	}
	
	public boolean removeConferenceRole(Integer[] ids) throws Exception{
//		for(int i=0;i<ids.length;i++){
//			raBean.setRoleId(ids[i]);
//			List<ConfRoleAuthorityBean> raList = daoFacade.getConfRoleAuthorityDao().getByExample(raBean);
//			daoFacade.getConfRoleAuthorityDao().delete(raList);
//		}
		
//		for(int i=0;i<ids.length;i++){
//			strIds.append(ids[i]).append(",");
//		}
//		strIds.append("-1");
		
		daoFacade.getConfRoleAuthorityDao().removeByRoleIds(ids);
		daoFacade.getConferenceTypeRoleDao().removeConferenceTypeRoleByRoleIds(ids);
		daoFacade.getConferenceRoleDao().batchUpdateByIds(ids);
		return true;
	}

	@Override
	public List<ConferenceRoleBean> getRolesByConferenceTypeId(Integer[] typeIds)
			throws Exception {
		
		List<ConferenceRoleBean> list = daoFacade.getConferenceRoleDao().getRolesByConferenceTypeId(typeIds);
		return list;
	}
}
