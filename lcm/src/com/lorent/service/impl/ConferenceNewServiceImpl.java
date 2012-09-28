package com.lorent.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.lorent.common.dto.LCMConferenceDto;
import com.lorent.common.dto.LCMRoleDto;
import com.lorent.dao.ConferenceNewDao;
import com.lorent.exception.ArgsException;
import com.lorent.exception.ServerException;
import com.lorent.model.AuthorityBean;
import com.lorent.model.ConfUserRoleBean;
import com.lorent.model.ConferenceNewBean;
import com.lorent.model.ConferenceRoleBean;
import com.lorent.model.ConferenceTypeBean;
import com.lorent.model.ConferenceTypeRoleBean;
import com.lorent.model.ConferenceUserBean;
import com.lorent.model.SipConfBean;
import com.lorent.model.UserBean;
import com.lorent.service.ConferenceNewService;
import com.lorent.util.Constant;
import com.lorent.util.Counter;
import com.lorent.util.McuUtil;
import com.lorent.util.OpenfireUtil;
import com.lorent.util.PropertiesUtil;
import com.lorent.util.StringUtil;
import com.lorent.util.ThreadLocaleUtil;
import com.lorent.whiteboard.client.Client;

public class ConferenceNewServiceImpl extends GenericServiceImpl<ConferenceNewDao,ConferenceNewBean,Integer> implements
ConferenceNewService{
	private Logger log = Logger.getLogger(ConferenceNewServiceImpl.class);
	
	@Override
	public void renewConferenceNew(UserBean user,
			ConferenceNewBean conferenceNew, String[] urIds) throws Exception {
		try{
			validateConfData(urIds, conferenceNew.getConferenceTypeId());
			daoFacade.getConferenceNewDao().update(conferenceNew);
			ConferenceUserBean conferenceUser = new ConferenceUserBean();
			conferenceUser.setConferenceId(conferenceNew.getId());
//			List<ConferenceUserBean> list = daoFacade.getConferenceUserDao().getByExample(conferenceUser);
//			Integer[] cuIds = new Integer[list.size()];
//			List params = new ArrayList();
//			params.add(conferenceNew.getId());
			List<UserBean> oldUsers = daoFacade.getConferenceNewDao().getUsersByConferenceId(conferenceNew.getId());//getUserDao().queryByHql("select ub from UserBean ub where ub.id in (select cu.userId from ConferenceUserBean cu where cu.conferenceId="+conferenceNew.getId()+")" );
			List<String> confMembers = new ArrayList<String>();
			
			
			daoFacade.getConferenceUserDao().removeConferenceUserByConferenceId(conferenceNew.getId());//executeUpdate("delete from ConferenceUserBean cu where cu.conferenceId=?", params);
//			if(cuIds.length>0){
//				//params.clear(); 
//				String delIds = StrUtil.changeIntegerArrayToString(cuIds);
//				//删除用户权限
//				daoFacade.getConfUserAuthorityDao().removeConfUserAuthoritysByConferenceId(conferenceNew.getId());//executeUpdate("delete from ConfUserAuthorityBean ua where ua.conferenceUserId in (select cu.userId from ConferenceUserBean cu where cu.conferenceId=?)", params);
//				//删除用户角色
//				daoFacade.getConfUserRoleDao().removeConfUserRolesByConferenceId(conferenceNew.getId());//executeUpdate("delete from ConfUserRoleBean ua where ua.conferenceUserId in (select cu.userId from ConferenceUserBean cu where cu.conferenceId=?)", params);
//			}
			//删除用户权限
			daoFacade.getConfUserAuthorityDao().removeConfUserAuthoritysByConferenceId(conferenceNew.getId());//executeUpdate("delete from ConfUserAuthorityBean ua where ua.conferenceUserId in (select cu.userId from ConferenceUserBean cu where cu.conferenceId=?)", params);
			//删除用户角色
			daoFacade.getConfUserRoleDao().removeConfUserRolesByConferenceId(conferenceNew.getId());//executeUpdate("delete from ConfUserRoleBean ua where ua.conferenceUserId in (select cu.userId from ConferenceUserBean cu where cu.conferenceId=?)", params);
			Map<String,Integer> confUserMap = new HashMap<String,Integer>();
			for(int i=0;urIds!=null && i<urIds.length;i++){
				String[] stra = urIds[i].split("_");
				if(!confUserMap.containsKey(stra[1])){
					//插入参加会议人员记录
					ConferenceUserBean cubean = new ConferenceUserBean();
					cubean.setConferenceId(conferenceNew.getId());
					cubean.setUserId(Integer.parseInt(stra[1]));
					daoFacade.getConferenceUserDao().save(cubean);
					confUserMap.put(stra[1], cubean.getId());
				}
				
				
				//插入会议人员角色表
				ConfUserRoleBean urbean = new ConfUserRoleBean();
				urbean.setRoleId(Integer.parseInt(stra[0]));
				urbean.setConferenceUserId(confUserMap.get(stra[1]));
				daoFacade.getConfUserRoleDao().save(urbean);
				
				//插入会议人员权限表
//				params.clear();
//				params.add(Integer.parseInt(stra[0]));
				daoFacade.getConfUserAuthorityDao().insertConfUserAuthoritysByRoleId(confUserMap.get(stra[1]), stra[0]);//.executeUpdate("insert into ConfUserAuthorityBean (conferenceUserId,authorityId) select " + cubean.getId() + ",ra.authorityId from ConfRoleAuthorityBean ra where ra.roleId=?", params);
				confMembers.add(stra[2]);
			}
			
			OpenfireUtil.getInstance().updateConferenceRoom(conferenceNew, confMembers, user, oldUsers);
			
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	public void renewConferenceNew(ConferenceNewBean conferenceNew, String[] urIds) throws Exception{
		UserBean user = ThreadLocaleUtil.getUser();
		renewConferenceNew(user, conferenceNew, urIds);
	}
	
	@Override
	public void createConferenceNew(UserBean user, ConferenceNewBean conferenceNew,
			String[] urIds, boolean valid)
			throws Exception {
		try{
			if(valid){
				validateConfData(urIds, conferenceNew.getConferenceTypeId());
			}
			String confNo = conferenceNew.getConfNo();
//			String confNo = this.createConfNo(conferenceNew, customerCode, confType);
//			conferenceNew.setConfNo(confNo);
			daoFacade.getConferenceNewDao().save(conferenceNew);
			Map<String,Integer> confUserMap = new HashMap<String,Integer>();
			List<String> confMembers = new ArrayList<String>();
			
			for(int i=0;urIds!=null && i<urIds.length;i++){
				String[] stra = urIds[i].split("_");
				if(!confUserMap.containsKey(stra[1])){
					//插入参加会议人员记录
					ConferenceUserBean cubean = new ConferenceUserBean();
					cubean.setConferenceId(conferenceNew.getId());
					cubean.setUserId(Integer.parseInt(stra[1]));
					daoFacade.getConferenceUserDao().save(cubean);
					confUserMap.put(stra[1], cubean.getId());
				}
				
				
				//插入会议人员角色表
				ConfUserRoleBean urbean = new ConfUserRoleBean();
				urbean.setRoleId(Integer.parseInt(stra[0]));
				urbean.setConferenceUserId(confUserMap.get(stra[1]));
				daoFacade.getConfUserRoleDao().save(urbean);
				
				//插入会议人员权限表
//				list.clear();
//				list.add(Integer.parseInt(stra[0]));
				daoFacade.getConfUserAuthorityDao().insertConfUserAuthoritysByRoleId(confUserMap.get(stra[1]), stra[0]);//.executeUpdate("insert into ConfUserAuthorityBean (conferenceUserId,authorityId) select " + cubean.getId() + ",ra.authorityId from ConfRoleAuthorityBean ra where ra.roleId=?", list);
				confMembers.add(stra[2]);
			}
			
			//MCU创建会议
//			UserBean user = ThreadLocaleUtil.getUser();
			try{
				McuUtil.createForwardConference(confNo,user.getCustomer().getMcuServer().getServerUrl());
			}catch(Exception e){
				throw new ServerException("server.mcuCreateConfFail", e);
			}
			
			//在OPENFIRE创建会议
			//confMembers.add(user.getLccAccount());
			try{
				OpenfireUtil.getInstance().createConferenceRoom(conferenceNew, confMembers, false, user);
			}catch(Exception e){
				McuUtil.removeForwardConference(user.getCustomer().getMcuServer().getServerUrl(), confNo);
				throw new ServerException("server.imsConnectFail", e);
			}
			
			//在白板服务创建会议
			try{
				Client.create(PropertiesUtil.getConstant("lvmc.serverIP"), confNo);
			}catch(Exception e){
				e.printStackTrace();
			}
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	@Override
	public void createConferenceNew(ConferenceNewBean conferenceNew, String[] urIds)
			throws Exception {
		UserBean user = ThreadLocaleUtil.getUser();
		createConferenceNew(user, conferenceNew, urIds, true);
	}
	
	public void removeSipConfByConfno(String confno) throws Exception {
		if(confno == null){
			return;
		}
		SipConfBean example = new SipConfBean();
		example.setName(confno);
		List<SipConfBean> sipConfs = daoFacade.getSipConfDao().getByExample(
				example);
		if(sipConfs!=null){
			daoFacade.getSipConfDao().delete(sipConfs);
		}
	}
	
	public Map<String,String> getAllConferences() throws Exception {
		ConferenceNewBean example = new ConferenceNewBean();
		example.setDel(1);
		List<ConferenceNewBean> confs = daoFacade.getConferenceNewDao().getByExample(example);
		ConferenceNewBean conferenceNewBean=null;
		Map<String,String> map=new HashMap<String,String>();
		if(null!=confs)
		for(int i=0;i<confs.size();i++){
			conferenceNewBean=confs.get(i);
			map.put(conferenceNewBean.getConfNo(), conferenceNewBean.getConferenceName());
		}
		return map;
	}
	
	
	
	@Override
	public Map<String, LCMConferenceDto> getAllConferencesDto()
			throws Exception {
		List<LCMConferenceDto> confs = daoFacade.getConferenceNewDao().getConferenceDto();
		Map<String,LCMConferenceDto> map=new HashMap<String,LCMConferenceDto>();
		if(null!=confs)
		for(int i=0;i<confs.size();i++){
			LCMConferenceDto lcmConferenceDto = confs.get(i);
			map.put(lcmConferenceDto.getConfNo(), lcmConferenceDto);
		}
		return map;
	}

	public String createConfNo(ConferenceNewBean conf, String customerCode,
			Integer conftype) throws Exception {
		String no = "";
		if (StringUtil.isEmpty(customerCode)) {
			throw new ArgsException("args.customercodeisempty");
		}
		// 生成会议号码 会议号码=客户代码+会议类型+随机数，并判断是否重复
		boolean isOK = true;
		//String hql = "select c from ConferenceNewBean c where c.confNo = ";
		while (isOK) {
			no = customerCode
					+ conftype
					+ StringUtil.getRandom(Constant.getConferenceNoLength()
							- Constant.getCustomerCodeLength() - 1);
			List list = daoFacade.getConferenceNewDao().getConferenceNewsByConfNo(no);//.queryByHql(hql + "'" + no + "'");
			if (list.size() == 0) {
				isOK = false;
			}
		}
		SipConfBean sipConf = new SipConfBean();
		sipConf.setName(no);
		sipConf.setNonExpired(true);
		sipConf.setNonLocked(true);
		sipConf.setCanreinvite("yes");
		sipConf.setSecret("1qaz@WSX");
		int sipId = daoFacade.getSipConfDao().save(sipConf);
//		conf.setSipId(sipId);
		conf.setConfNo(no);
		return no;
	}

	@Override
	public LCMRoleDto getMyRoleAndPermission(String confno, String lccno) throws Exception {
		LCMRoleDto data = new LCMRoleDto();
		//获取会议
		List<ConferenceNewBean> confs = getDao().getConferenceNewsByConfNo(confno);
		if(confs.size() == 0){
			throw new ArgsException("args.confNoExist");
		}
		ConferenceNewBean conf = confs.get(0);
		boolean isUserInConf = daoFacade.getConferenceUserDao().userHasConfRole(confno, lccno);
		if(!isUserInConf){//创建会议时没有选择该用户角色
			if(conf.getNeedApply()==1){//需要申请
				return null;
			}else{//
				//获取默认角色
				Integer defaultRoleId = conf.getDefaultRoleId();
				
				//添加相应数据
				////插入ConferenceUserBean
				ConferenceUserBean cubean = new ConferenceUserBean();
				cubean.setConferenceId(conf.getId());
				UserBean userBean = serviceFacade.getUserService().getByLccAccount(lccno);
				if(userBean == null){
					throw new ArgsException("sql.usernotexits");
				}
				cubean.setUserId(userBean.getId());
				daoFacade.getConferenceUserDao().save(cubean);
				
				////插入ConfUserRoleBean
				ConfUserRoleBean urbean = new ConfUserRoleBean();
				urbean.setRoleId(defaultRoleId);
				urbean.setConferenceUserId(cubean.getId());
				daoFacade.getConfUserRoleDao().save(urbean);
				
				////插入ConfUserAuthorityBean
				daoFacade.getConfUserAuthorityDao().insertConfUserAuthoritysByRoleId(cubean.getId(), defaultRoleId + "");
				
				//设置返回数据
				ConferenceRoleBean role = daoFacade.getConferenceRoleDao().get(defaultRoleId);
				data.addName(role.getRoleName());
				data.setNickname(userBean.getUsername());
				data.setConferenceTitle(conf.getConferenceName());
				List<AuthorityBean> authoritys = daoFacade.getConferenceRoleDao().getAuthorityByRoleId(defaultRoleId);
				for(AuthorityBean authority : authoritys){
					data.addPermission(authority.getMark(), authority.getAuthorityName());
				}
				return data;
			}
		}else{
			data = daoFacade.getConferenceNewDao().getMyRoleAndPermission(confno, lccno);
			return data;
		}
	}

	@Override
	public void removeConferenceNew(Integer[] ids) throws Exception {
		UserBean user = ThreadLocaleUtil.getUser();
		removeConferenceNew(user, ids, true);
	}

	@Override
	public void removeConferenceNew(UserBean user, Integer[] ids, boolean removeSipConf)
			throws Exception {
		if(ids.length>0){
//			List list = null;
//			String strIds = StrUtil.changeIntegerArrayToString(ids);
			daoFacade.getConferenceNewDao().removeConferenceNewBeanByIds(ids);//executeUpdate("update ConferenceNewBean cn set cn.del=0 where cn.id in ("+strIds+")", list);
			daoFacade.getConfUserAuthorityDao().removeConfUserAuthoritysByConferenceIds(ids);//executeUpdate("delete from ConfUserAuthorityBean ua where ua.conferenceUserId in (select id from ConferenceUserBean cu where cu.conferenceId in ("+strIds+"))", list);
			daoFacade.getConfUserRoleDao().removeConfUserRolesByConferenceIds(ids);//executeUpdate("delete from ConfUserRoleBean ur where ur.conferenceUserId in (select id from ConferenceUserBean cu where cu.conferenceId in ("+strIds+"))", list);
			daoFacade.getConferenceUserDao().removeConferenceUserByConferenceIds(ids);//executeUpdate("delete from ConferenceUserBean ur where ur.conferenceId in ("+strIds+")", list);
			
			List<String> confNos = daoFacade.getConferenceNewDao().getConferenceNoByConferenceIds(ids);//.queryByHql("select cn.confNo from ConferenceNewBean cn where cn.id in ("+ strIds +")");
			
//			UserBean user = ThreadLocaleUtil.getUser();
			for(String confno:confNos){
				try{
					McuUtil.removeForwardConference(user.getCustomer().getMcuServer().getServerUrl(),confno);
				}catch(Exception e){
					log.error("removeConferenceNew", e);
					throw new ServerException("server.mcuRemoveConfFail", e);
				}
				if(removeSipConf){
					removeSipConfByConfno(confno);
				}
				try{
					//在白板服务删除会议
					Client.remove(PropertiesUtil.getConstant("lvmc.serverIP"), confno);
				}catch(Exception e){
					log.error("removeConferenceNew", e);
					throw new ServerException("server.lvmcRemoveConfFail", e);
				}
			}
			try{
				OpenfireUtil.getInstance().removeConferenceRoom(confNos);
			}catch(Exception e){
				log.error("removeConferenceNew", e);
				throw new ServerException("server.imsRemoveConfFail", e);
			}
		}
	}

	@Override
	public Map<String, LCMRoleDto> getConfUserRole(String confno, String[] lccnos) throws Exception {
		return daoFacade.getConferenceNewDao().getConfUserRole(confno, lccnos);
	}

	@Override
	public void getSearchSubPageMapResult(Map<String, Object> map, Map<String,Object> params)
			throws Exception {
		daoFacade.getConferenceNewDao().getSearchSubPageMapResult(map,params);
		
	}

	@Override
	public void getInitSubPageMapResult(Map<String, Object> map,
			Map<String, Object> params) throws Exception {
		daoFacade.getConferenceNewDao().getInitSubPageMapResult(map,params);
	}

	@Override
	public List<ConferenceNewBean> getMcuRestoreConfs() throws Exception {
		ConferenceNewBean example = new ConferenceNewBean();
		example.setDel(1);
		List<ConferenceNewBean> confs = daoFacade.getConferenceNewDao().getByExample(example);
		return confs;
	}

	@Override
	public List getConferenceRoles() throws Exception {
		List<ConferenceRoleBean> all = daoFacade.getConferenceRoleDao().getAll();
		return all;
	}

	@Override
	public List getConferenceTypes() throws Exception {
		List<ConferenceTypeBean> all = daoFacade.getConferenceTypeDao().getAll();
		return all;
	}

	@Override
	public boolean canCreateConf(String lccno) throws Exception {
		int confNum = daoFacade.getConferenceNewDao().getValidConfNum();
		int maxNum = Integer.parseInt(PropertiesUtil.getConstant("max.newconf"));
		if(confNum < maxNum){
			return true;
		}else{
			throw new ArgsException("validate.ismaxconf");
		}
	}

	@Override
	public boolean validateConfData(String[] paras, int conftype)
			throws Exception {
		Map<String, Counter> map = new HashMap<String, Counter>();
		for(String para : paras){
			String roleId = para.split("_")[0];
			if(map.containsKey(roleId)){
				map.get(roleId).add();
			}else{
				map.put(roleId, new Counter(1));
			}
		}
		List<ConferenceTypeRoleBean> roles = daoFacade.getConferenceTypeRoleDao().getConfRolesByType(conftype);
//		Map<String, ConferenceTypeRoleBean> condition = new HashMap<String, ConferenceTypeRoleBean>();
//		for(ConferenceTypeRoleBean role : roles){
//			condition.put(role.getConferenceRoleId() + "", role);
//		}
		for(ConferenceTypeRoleBean role : roles){
			String roleId = role.getConferenceRoleId() + "";
			int num = -1;
			Counter counter = map.get(roleId);
			if(counter == null){
				num = 0 ;
			}else{
				num = counter.getNum();
			}
			if(role.getMinnum() != -1 && num < role.getMinnum()){
				String msg = PropertiesUtil.getProperty("exceptionResource", "args.confRoleMinLimit", true);
				msg = StringUtil.getFormatString(msg, role.getConferenceTypeName(), role.getMinnum(), role.getConferenceRoleName());
				throw new ArgsException(msg);
			}
			if(role.getMaxnum() != -1 && num > role.getMaxnum()){
				String msg = PropertiesUtil.getProperty("exceptionResource", "args.confRoleMaxLimit", true);
				msg = StringUtil.getFormatString(msg, role.getConferenceTypeName(), role.getMaxnum(), role.getConferenceRoleName());
				throw new ArgsException(msg);
			}
		}
		return true;
	}

}
