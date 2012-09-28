package com.lorent.service.impl;

import java.util.List;

import com.lorent.dao.ConferenceTypeDao;
import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.ConfRoleAuthorityBean;
import com.lorent.model.ConferenceRoleBean;
import com.lorent.model.ConferenceTypeBean;
import com.lorent.model.ConferenceTypeRoleBean;
import com.lorent.model.RatesBean;
import com.lorent.service.ConferenceTypeService;
import com.lorent.util.Constant;
import com.lorent.util.StringUtil;

public class ConferenceTypeServiceImpl extends GenericServiceImpl<ConferenceTypeDao,ConferenceTypeBean,Integer> implements
ConferenceTypeService{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 创建会议类型
	 */
	public Integer createConferenceType(ConferenceTypeBean conferenceType,Integer[] roleIds) throws Exception {
		
		daoFacade.getConferenceTypeDao().save(conferenceType);
		for(int i =0;i<roleIds.length;i++){
			ConferenceTypeRoleBean bean = new ConferenceTypeRoleBean();
			bean.setConferenceRoleId(roleIds[i]);
			bean.setConferenceTypeId(conferenceType.getId());
			daoFacade.getConferenceTypeRoleDao().save(bean);
			bean = null;
		}
		return conferenceType.getId();
	}
	
	/**
	 * 更新会议类型
	 */
	public boolean renewConferenceType(ConferenceTypeBean conferenceType,Integer[] roleIds) throws Exception {
		daoFacade.getConferenceTypeDao().update(conferenceType);
		ConferenceTypeRoleBean raBean = new ConferenceTypeRoleBean();
		raBean.setConferenceTypeId(conferenceType.getId());
		List<ConferenceTypeRoleBean> raList = daoFacade.getConferenceTypeRoleDao().getByExample(raBean);
		daoFacade.getConferenceTypeRoleDao().delete(raList);
		for(int i =0;i<roleIds.length;i++){
			raBean = new ConferenceTypeRoleBean();
			raBean.setConferenceRoleId(roleIds[i]);
			raBean.setConferenceTypeId(conferenceType.getId());
			daoFacade.getConferenceTypeRoleDao().save(raBean);
			raBean = null;
		}
		return true;
	}
	
	/**
	 * 删除会议类型
	 */
	public boolean removeConferenceType(Integer[] ids) throws Exception{
//		ConferenceTypeRoleBean raBean = new ConferenceTypeRoleBean();
//		for(int i=0;i<ids.length;i++){
//			raBean.setConferenceTypeId(ids[i]);
//			List<ConferenceTypeRoleBean> raList = daoFacade.getConferenceTypeRoleDao().getByExample(raBean);
//			daoFacade.getConferenceTypeRoleDao().delete(raList);
//		}
//		daoFacade.getConferenceTypeDao().delete(ids);
		daoFacade.getConferenceTypeDao().removeConferenceTypesByIds(ids);
		daoFacade.getConferenceTypeRoleDao().removeConferenceTypeRoleByRoleIds(ids);
		return true;
	}
	
}
