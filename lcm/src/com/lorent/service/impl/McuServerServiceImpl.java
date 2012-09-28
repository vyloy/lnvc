package com.lorent.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.lorent.dao.McuServerDao;
import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.exception.CustomXmlRpcException;
import com.lorent.model.CustomerBean;
import com.lorent.model.McuServerBean;
import com.lorent.service.McuServerService;
import com.lorent.util.Constant;
import com.lorent.util.MD5Builder;
import com.lorent.util.McuUtil;
import com.lorent.web.action.McuAction;
import com.lorent.web.xmlrpc.handler.BaseHandler;

public class McuServerServiceImpl extends GenericServiceImpl<McuServerDao, McuServerBean, Integer>
		implements McuServerService {

	private static final Logger log = Logger.getLogger(McuServerServiceImpl.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 创建或更新MCU记录
	 */
	public boolean createOrUpdate(McuServerBean server)
		throws CustomSqlException,ArgsException,CustomXmlRpcException {
//		//如果未设置媒体处理器则抛出异常
//		if(server.getMixers()==null||server.getMixers().size()==0)
//			throw new ArgsException("args.mcumixerrequired");
		//判断是插入还是更新记录
//		if(server.getId()==null){
//			//判断记录是否已经存在于数据库中
//			if(daoFacade.getMcuServerDao().getByExample(server)!=null)
//				throw new ArgsException("arg.recordexists");
//			McuServerBean temp = new McuServerBean();
//			temp.setServerIp(server.getServerIp());
//			//判断mcu服务器IP是否已经存在
//			if(daoFacade.getMcuServerDao().getByExample(temp)!=null)
//				throw new ArgsException("args.serveripexists");
//		}
//		McuUtil.createMixerToMcu(server);
		//将mcu服务器信息更新到数据库
//		server.setRpcPass(MD5Builder.getMD5(server.getServerIp()));
		log.info("MCU设置IP：" + server.getServerIp());
		log.info("MCU设置url：" + server.getServerUrl());
		log.info("MCU设置激活状态：" + server.getServerStatus());
		if(server.getId()==null){
			log.info("添加MCU设置");
		}else{
			log.info("修改MCU设置");
		}
		daoFacade.getMcuServerDao().saveOrUpdate(server);
		return true;
	}

	public boolean remove(McuServerBean server) 
		throws CustomSqlException,ArgsException,CustomXmlRpcException {
		if(daoFacade.getMcuServerDao().getByExample(server)==null){
			throw new CustomSqlException("sql.recordnotexists");
		}
		daoFacade.getMcuServerDao().delete(server);
		return false;
	}

	public boolean remove(Integer[] ids) throws CustomSqlException,ArgsException,CustomXmlRpcException{
		//判断是否存在引用，有引用则不能删除
		List<McuServerBean> beans = this.get(ids);
		for(McuServerBean bean : beans){
			DetachedCriteria dc = DetachedCriteria.forClass(CustomerBean.class);
			dc.createAlias("mcuServer", "mcuServer")
				.add(Restrictions.eq("mcuServer.id", bean.getId()))
				.add(Restrictions.eq("status", Constant.RECORD_STATUS_VALID));
			List<CustomerBean> list = daoFacade.getCustomerDao().getByCriteria(dc);
			if(list.size()!=0){
				throw new CustomSqlException("validate.deleterelateerror");
			}
		}
		return daoFacade.getMcuServerDao().delete(beans);
	}

	public String mcuRpcValidate(String mcuIp, String rpcPass) throws CustomSqlException, ArgsException {
		McuServerBean server = getMcuByIP(mcuIp);
		if(server==null||!server.getRpcPass().equals(rpcPass))
			return null;
		String validateCode = MD5Builder.getMD5(server.getRpcPass(),server.getServerIp());
		BaseHandler.validateMap.put(server.getServerIp(), validateCode);
		return validateCode;
	}

	public void restoreMixersToMcu(String mcuIp) throws CustomSqlException, ArgsException,CustomXmlRpcException {
		McuServerBean server = getMcuByIP(mcuIp);
		if(server==null)return;
//		McuUtil.createMixerToMcu(server);
	}
	/* =============================================== private methods ==================================================== */
	private McuServerBean getMcuByIP(String mcuIp)throws CustomSqlException {
		McuServerBean server = new McuServerBean();
		server.setServerIp(mcuIp);
		List<McuServerBean>servers = daoFacade.getMcuServerDao().getByExample(server);
		if(servers==null||servers.size()==0)
			return null;
		return servers.get(0);
	}
	
}
