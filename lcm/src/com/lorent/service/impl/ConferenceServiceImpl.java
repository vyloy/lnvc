package com.lorent.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.quartz.SchedulerException;

import com.lorent.dao.ConferenceDao;
import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.exception.CustomXmlRpcException;
import com.lorent.exception.ValidateException;
import com.lorent.model.BillingBean;
import com.lorent.model.ConfNoStatusBean;
import com.lorent.model.ConferenceBean;
import com.lorent.model.ConferenceNewBean;
import com.lorent.model.ConferenceNoBean;
import com.lorent.model.CustomerBean;
import com.lorent.model.McuServerBean;
import com.lorent.model.MediaBean;
import com.lorent.model.RatesBean;
import com.lorent.model.SipConfBean;
import com.lorent.model.UserBean;
import com.lorent.model.UserStatusBean;
import com.lorent.service.ConferenceService;
import com.lorent.trigger.ConfStartTrigger;
import com.lorent.trigger.ConfStopTrigger;
import com.lorent.trigger.QuartzTrigger;
import com.lorent.util.BaseThread;
import com.lorent.util.CSUtil;
import com.lorent.util.Constant;
import com.lorent.util.MailUtil;
import com.lorent.util.McuUtil;
import com.lorent.util.PropertiesUtil;
import com.lorent.util.StringUtil;
import com.lorent.util.ThreadLocaleUtil;

public class ConferenceServiceImpl extends
		GenericServiceImpl<ConferenceDao, ConferenceBean, Integer> implements
		ConferenceService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(ConferenceServiceImpl.class);

	/**
	 * 创建会议(即时会议，预约会议，周期会议触发)
	 */
	public Integer createConf(ConferenceBean conference) throws Exception {
		conference.setStatus(Constant.RECORD_STATUS_VALID);
		conference.setId(daoFacade.getConferenceDao().save(conference));
		switchConfType(conference);
		if (!conference.getConfType().equals(Constant.CONF_TYPE_PERCONF)) {
			UserBean user = conference.getOwner();
			MailUtil.conferenceInviteAdvice(conference, user);
		}
		return conference.getId();
	}

	/**
	 * 更新会议
	 */
	public boolean renewConf(ConferenceBean conference) throws Exception {
		daoFacade.getConferenceDao().update(conference);
		switchConfType(conference);
		if (conference.getConfStatus().equals(Constant.CONF_STATUS_ONGOING)) {
			updateConference(conference);
		}
		MailUtil.conferenceChangeAdvice(conference, ThreadLocaleUtil.getUser());
		return true;
	}

	/**
	 * 删除会议信息
	 * 
	 * @param conferenceId
	 * @return
	 * @throws ArgsException
	 * @throws CustomSqlException
	 * @throws SchedulerException
	 */
	public boolean removeConference(Integer conferenceId) throws Exception {
		ConferenceBean conference = daoFacade.getConferenceDao().get(
				conferenceId);
		if (conference == null)
			throw new CustomSqlException("sql.recordnotexists");
		conference.setStatus(Constant.RECORD_STATUS_DELETED);
		daoFacade.getConferenceDao().update(conference);
		return true;
	}

	/**
	 * 删除会议信息
	 * 
	 * @param ids
	 * @return
	 * @throws ArgsException
	 * @throws CustomSqlException
	 */
	public boolean removeConference(Integer[] ids) throws Exception {
		// 正在进行的会议不能删除
		List<ConferenceBean> confs = get(ids);
		for (ConferenceBean conf : confs) {
			if (Constant.CONF_STATUS_ONGOING.equals(conf.getConfStatus())) {
				throw new ArgsException("args.confisgoing");
			}
		}
		for (int i = 0; i < ids.length; i++) {
			removeConference(ids[i]);
		}
		return true;
	}

	/**
	 * 强制终止会议
	 * 
	 * @param ids
	 * @return
	 * @throws CustomSqlException
	 * @throws ArgsException
	 */
	public boolean stopConference(Integer[] ids) throws Exception {
		for (int i = 0; i < ids.length; i++) {
			stopConference(ids[i]);
		}
		return true;
	}

	/**
	 * 强制终止会议
	 * 
	 * @param conference
	 * @return
	 * @throws CustomSqlException
	 * @throws ArgsException
	 */
	public boolean stopConference(Integer id) throws Exception {
		ConferenceBean conference = daoFacade.getConferenceDao().get(id);
		conference.setConfStatus(Constant.CONF_STATUS_STOPED);
		conference.setEndTime(new Date());
		removeConference(conference);
		endConf(conference);
		return true;
	}

	/**
	 * 根据会议类型执行不同的操作
	 * 
	 * @param conference
	 * @throws CustomSqlException
	 * @throws CustomXmlRpcException
	 * @throws SchedulerException
	 * @throws ArgsException
	 */
	private void switchConfType(ConferenceBean conference) throws Exception {
		// 如果是即时会议或者是周期会议触发，则立即在mcu上创建会议
		if (conference.getConfType().equals(Constant.CONF_TYPE_IMDCONF)
				|| conference.getConfType().equals(Constant.CONF_TYPE_PERCONF)) {
			conferenceStartTrigger(conference);
			// 如果是预约会议，则将会议触发的任务添加到任务列表
		} else if (conference.getConfType().equals(Constant.CONF_TYPE_APPCONF)) {
			QuartzTrigger.addTask(new ConfStartTrigger(conference),
					serviceFacade);
		}
		// 将终止会议的任务添加到任务列表
		QuartzTrigger.addTask(new ConfStopTrigger(conference), serviceFacade);
		// 更新账单记录
		billingByConference(conference);
	}

	/**
	 * 删除或终止会议时进行的操作
	 * 
	 * @param conference
	 * @throws CustomSqlException
	 * @throws ArgsException
	 * @throws CustomXmlRpcException
	 * @throws SchedulerException
	 */
	private void endConf(ConferenceBean conference) throws Exception {
		// 更新会议表中的记录
		daoFacade.getConferenceDao().update(conference);
		// 更新会议对应的账单
		billingByConference(conference);
		// 将会议触发从任务列表中清除
		removeTask(conference);
	}

	/**
	 * 在MCU上创建会议并触发
	 * 
	 * @throws CustomSqlException
	 */
	public void conferenceStartTrigger(ConferenceBean conference)
			throws Exception {
		if (!Constant.CONF_STATUS_ONGOING.equals(conference.getConfStatus())) {
			// 如果是新建会议，则在mcu上创建会议并设置会议的UID
			createConference(conference);
			// 更新会议状态
			conference.setConfStatus(Constant.CONF_STATUS_ONGOING);
			daoFacade.getConferenceDao().update(conference);
		}
		// 邀请用户加入会议
		try {
			if(conference.getUsers()!=null && conference.getUsers().size()!=0){
				CSUtil.addUserInConf(conference);
			}
		} catch (Exception e) {
			removeConference(conference);
			throw new CustomXmlRpcException("xmlrpc.clickdialfail", e);
		}
	}

	/**
	 * 从mcu删除会议
	 */
	public void conferenceStopTrigger(ConferenceBean conference)
			throws Exception {
		conference.setConfStatus(Constant.CONF_STATUS_FINISHED);
		removeConference(conference);
		endConf(conference);
	}

	/**
	 * 移除全局timer上与conference相关的任务
	 * 
	 * @param conference
	 * @throws SchedulerException
	 */
	private void removeTask(ConferenceBean conference) throws Exception {
		QuartzTrigger.removeTask(Constant.TRIGGER_CONF_STOP_PREFIX
				+ conference.getId(), ConfStopTrigger.class.toString());
		QuartzTrigger.removeTask(Constant.TRIGGER_CONF_START_PREFIX
				+ conference.getId(), ConfStartTrigger.class.toString());
	}

	/**
	 * 获取尚未触发的会议
	 */
	public List<ConferenceBean> getUnTriggerConfs() throws Exception {
		return daoFacade.getConferenceDao().getUnTriggerConfs();
	}

	/**
	 * 根据会议记录创建账单记录
	 */
	private void billingByConference(ConferenceBean conference)
			throws Exception {
		if (conference.getId() == null) {
			throw new ArgsException("args.objectnotpresist");
		}
		BillingBean billing = new BillingBean();
		billing.setConference(conference);
		if (daoFacade.getBillingDao().getBillingByConfId(conference.getId()) != null) {
			billing = daoFacade.getBillingDao().getBillingByConfId(
					conference.getId());
			billing.setConference(conference);
		} else {
			billing.setIsPay(false);
			billing.setRates(conference.getCustomer().getRates());
		}
		boolean confStoped = conference.getConfStatus().equals(
				Constant.CONF_STATUS_FINISHED)
				|| conference.getConfStatus().equals(
						Constant.CONF_STATUS_STOPED)
				|| conference.getConfStatus().equals(
						Constant.CONF_STATUS_DELETED);
		ConferenceBean temp = billing.getConference();
		billing.setConfName(temp.getConfSubject());
		billing.setCustomerCode(temp.getCustomer().getCustomerCode());
		billing.setCustomerName(temp.getCustomer().getCustomerName());
		setBillingCost(billing, confStoped);
		daoFacade.getBillingDao().saveOrUpdate(billing);
	}

	/**
	 * 计算账单花费
	 * 
	 * @param billing
	 * @param conferenceStoped
	 */
	private void setBillingCost(BillingBean billing, boolean conferenceStoped)
			throws Exception {
		ConferenceBean conference = billing.getConference();
		RatesBean rates = billing.getRates();
		long time = (conference.getEndTime().getTime() - conference
				.getStartTime().getTime()) / 1000;
		int costTime = 0;
		switch (rates.getRatesType()) {
		case Constant.RATES_TYPE_BYTIME:
			costTime = 1;
			break;
		case Constant.RATES_TYPE_BYHOUR:
			costTime = (int) (time / 3600);
			if (time % 3600 != 0)
				costTime++;
			break;
		case Constant.RATES_TYPE_BYMIN:
			costTime = (int) time / 60;
			if (time % 60 != 0)
				costTime++;
			break;
		default:
			costTime = 0;
		}
		billing.setTotalCost(rates.getRatesTarriff().multiply(
				new BigDecimal(costTime)));
		if (conferenceStoped) {
			billing.setActualCost(rates.getRatesTarriff().multiply(
					new BigDecimal(costTime)));
		} else {
			billing.setActualCost(new BigDecimal(0));
		}
	}

	/**
	 * 将lcm中正在进行的会议恢复到mcu上
	 */
	public void restoreConferenceToMcu(String serverIp) throws Exception {
		DetachedCriteria criteria = DetachedCriteria
				.forClass(ConferenceBean.class);
		criteria.createAlias("customer", "customer").createAlias(
				"customer.mcuServer", "server").add(
				Restrictions.eq("server.serverIp", serverIp)).add(
				Restrictions.eq("server.serverStatus", Constant.RECORD_VALID))
				.add(
						Restrictions.eq("confStatus",
								Constant.CONF_STATUS_ONGOING))
				.add(Restrictions.eq("status", Constant.RECORD_STATUS_VALID));
		List<ConferenceBean> confs = daoFacade.getConferenceDao()
				.getByCriteria(criteria);
		if (confs == null || confs.size() == 0)
			return;
		for (ConferenceBean conf : confs) {
			try {
				createConference(conf);
				daoFacade.getConferenceDao().update(conf);
				if (conf.getConfType().equals(Constant.CONF_TYPE_MEETING)) {

				} else {
					QuartzTrigger.addTask(new ConfStopTrigger(conf),
							serviceFacade);
					CSUtil.addUserInConf(conf);
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
	}

	public void mediaPlay(Integer confId, final boolean play) throws Exception {
		final ConferenceBean conf = serviceFacade.getConferenceService().get(
				confId);
		if (conf.getMediaId() == null) {
			throw new ArgsException("args.nomedia");
		}
		final MediaBean media = serviceFacade.getStaticService().get(
				conf.getMediaId(), MediaBean.class);
		final String confno = conf.getConfno();
		final String mcuIp = conf.getCustomer().getMcuServer().getServerIp();
		if (play) {
			conf.setMediaOperate(Constant.MEDIA_PLAY);
		} else {
			conf.setMediaOperate(Constant.MEDIA_STOP);
		}
		new BaseThread() {

			public void run() {
				try {
					CSUtil.playMediaInConf(conf, media.getMediaPath(), play);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}.start();
	}

	/*
	 * 生成会议号码 @param customerCode @return
	 */
	public void createConfNo(ConferenceBean conf, String customerCode,
			Integer conftype) throws Exception {
		String no = "";
		if (StringUtil.isEmpty(customerCode)) {
			throw new ArgsException("args.customercodeisempty");
		}
		// 生成会议号码 会议号码=客户代码+会议类型+随机数，并判断是否重复
		boolean isOK = true;
		String hql = "select c from ConferenceBean c where c.status = 1 and c.confno = ";
		while (isOK) {
			no = customerCode
					+ conftype
					+ StringUtil.getRandom(Constant.getConferenceNoLength()
							- Constant.getCustomerCodeLength() - 1);
			List list = daoFacade.getConferenceDao().queryByHql(
					hql + "'" + no + "'");
			if (list.size() == 0) {
				isOK = false;
			}
		}
		SipConfBean sipConf = new SipConfBean();
		sipConf.setName(no);
		sipConf.setNonExpired(true);
		sipConf.setNonLocked(true);
		int sipId = daoFacade.getSipConfDao().save(sipConf);
		conf.setSipId(sipId);
		conf.setConfno(no);
	}

	/*
	 * 检查是否可以创建会议
	 */
	public boolean canCreateConf(CustomerBean customer, Integer conftype)
			throws Exception {
		String codePrefix = "'" + customer.getCustomerCode() + conftype + "%'";
		String hql = "select count(c) from ConferenceBean c where c.status = 1 and (c.confStatus = 0 or c.confStatus = 1) and c.confno like "
				+ codePrefix;
		List list = daoFacade.getConferenceDao().queryByHql(hql);
		long num = (Long) (list.get(0));
		if (Constant.CONF_TYPE_APPCONF == conftype) {
			if (num >= customer.getAppConfNoLimit()) {
//				throw new ValidateException("{validate.appconfnoismax}:"+ customer.getAppConfNoLimit());
				throw new ValidateException(PropertiesUtil.getProperty("exceptionResource_zh_CN", "validate.appconfnoismax")+":"+ customer.getAppConfNoLimit());
			}
		} else if (Constant.CONF_TYPE_IMDCONF == conftype) {
			if (num >= customer.getImdConfNoLimit()) {
//				throw new ValidateException("{validate.imdconfnoismax}:"+ customer.getImdConfNoLimit());
				throw new ValidateException(PropertiesUtil.getProperty("exceptionResource_zh_CN", "validate.imdconfnoismax")+":"+ customer.getImdConfNoLimit());
			}
		} else if (Constant.CONF_TYPE_PERCONF == conftype) {
			hql = "select count(c) from CronConferenceBean c where c.status = 1 and c.confno like "
					+ codePrefix;
			list = daoFacade.getConferenceDao().queryByHql(hql);
			num = (Long) (list.get(0));
			if (num >= customer.getPerConfNoLimit()) {
//				throw new ValidateException("{validate.perconfnoismax}:"+ customer.getPerConfNoLimit());
				throw new ValidateException(PropertiesUtil.getProperty("exceptionResource_zh_CN", "validate.perconfnoismax")+":"+ customer.getPerConfNoLimit());
			}
		} else if (Constant.CONF_TYPE_MEETING == conftype) {
			if (num >= customer.getConfMeetingNoLimit()) {
//				throw new ValidateException("{validate.confmeetingnoismax}:"+ customer.getConfMeetingNoLimit());
				throw new ValidateException(PropertiesUtil.getProperty("exceptionResource_zh_CN", "validate.confmeetingnoismax")+":"+ customer.getConfMeetingNoLimit());
			}
		}
		return true;
	}

	public String getDefaultConfSubject(String username) throws Exception {
		// 默认会议名称=创建会议时间_创建会议人
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String ret = sdf.format(new Date()) + "_" + username;
		return ret;
	}

	private int createSipConfByConfno(String confno) throws Exception {
		// save sipconf
		SipConfBean sipConf = new SipConfBean();
		sipConf.setName(confno);
		daoFacade.getSipConfDao().save(sipConf);
		return sipConf.getId();
	}

	private void renewSipConfByConfno(String confno) throws Exception {
		SipConfBean example = new SipConfBean();
		example.setName(confno);
		List<SipConfBean> sipConfs = daoFacade.getSipConfDao().getByExample(
				example);
		daoFacade.getSipConfDao().update(sipConfs.get(0));
	}

	private void removeSipConfByConfno(String confno) throws Exception {
		SipConfBean example = new SipConfBean();
		example.setName(confno);
		List<SipConfBean> sipConfs = daoFacade.getSipConfDao().getByExample(
				example);
		if(sipConfs!=null){
			daoFacade.getSipConfDao().delete(sipConfs);
		}
	}

	private void createConference(ConferenceBean conf) throws Exception {
		// create conf
		try{
			McuUtil.createConference(conf);
		}catch(Exception e){
			removeSipConfByConfno(conf.getConfno());
			throw new CustomXmlRpcException("xmlrpc.mcucreateconffail", e);
		}
	}

	private void updateConference(ConferenceBean conf) throws Exception {
		// update
		McuUtil.updateConference(conf);
	}

	private void removeConference(ConferenceBean conf) throws Exception {
		// remove
		McuUtil.removeConference(conf);
		conf.setSipId(null);
		removeSipConfByConfno(conf.getConfno());
	}

	@Override
	public Integer createConfMeeting(ConferenceBean conf) throws Exception {
		conf.setStatus(Constant.RECORD_STATUS_VALID);
		conf.setId(daoFacade.getConferenceDao().save(conf));
		createConference(conf);
		// conf.setConfUID(confUID);
		conf.setConfStatus(Constant.CONF_STATUS_ONGOING);
		conf.setStartTime(new Date());
		daoFacade.getConferenceDao().update(conf);
		return conf.getId();
	}

	public boolean renewConfMeeting(ConferenceBean conf) throws Exception {
		daoFacade.getConferenceDao().update(conf);
		if (conf.getConfStatus().equals(Constant.CONF_STATUS_ONGOING)) {
			updateConference(conf);
		}
		return true;
	}

	public boolean removeConfMeeting(ConferenceBean conf) throws Exception {
		// 先停止会议
		conf.setEndTime(new Date());
		removeConference(conf);
		conf.setConfStatus(Constant.CONF_STATUS_DELETED);
		conf.setStatus(Constant.RECORD_STATUS_DELETED);
		daoFacade.getConferenceDao().update(conf);
		return true;
	}
	
	public boolean removeConfMeeting(Integer[] confIds)throws Exception{
		List<ConferenceBean> confs = get(confIds);
		for(ConferenceBean conf : confs){
			removeConfMeeting(conf);
		}
		return true;
	}

	@Override
	public List<ConferenceBean> getMcuRestoreConfs(String mcuXmlrpcUrl)
			throws Exception {
		//所有正在进行的会议+会议室 剔除其他会议
		DetachedCriteria criteria = DetachedCriteria.forClass(ConferenceBean.class);
		criteria
			.createAlias("customer", "customer")
			.createAlias("customer.mcuServer", "server")
			.add(Restrictions.eq("server.serverUrl", mcuXmlrpcUrl))
			.add(Restrictions.eq("server.serverStatus", Constant.RECORD_STATUS_VALID))
			.add(Restrictions.eq("confStatus",Constant.CONF_STATUS_ONGOING))
			.add(Restrictions.eq("status", Constant.RECORD_STATUS_VALID))
			.add(Restrictions.not(Restrictions.eq("confType", Constant.CONF_TYPE_OTHER)));
		List<ConferenceBean> ret = getByCriteria(criteria);
		return ret;
	}

	@Override
	public int createNoBusinessConf(ConferenceBean conf) throws Exception {
		try{
			int confId = serviceFacade.getConferenceService().save(conf);
			return confId;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public String removeNoBusinessConf(int confId) throws Exception {
		
		try {
			ConferenceBean conf = get(confId);
			conf.setEndTime(new Date());
			conf.setConfStatus(Constant.CONF_STATUS_FINISHED);
			conf.setStatus(Constant.RECORD_STATUS_DELETED);
			
//			McuUtil.removeConferenceFromMcu(conf);
			McuUtil.removeConference(conf);
			String confno = conf.getConfno();
			update(conf);
			removeSipConfByConfno(confno);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "";
	}

	@Override
	public boolean updateConf(int confId, int layout) throws Exception {
		ConferenceBean conf = serviceFacade.getConferenceService().get(confId);
		conf.setMcuMediaLayOut(layout);
		McuUtil.updateConference(conf);
//		McuUtil.updateMediaLayout(conf);
//		update(conf);
		return true;
	}

	@Override
	public void doRestoreConf() throws Exception {
		List<McuServerBean> list = null;
		List<ConferenceBean> conferences = null;
		List<ConferenceNewBean> newConfs = null;
		try {
			list = serviceFacade.getMcuServerService().getAll();
		} catch (CustomSqlException e1) {
			e1.printStackTrace();
		}
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				McuServerBean bean = list.get(i);
				if (bean != null) {
					try {
						conferences = getMcuRestoreConfs(bean.getServerUrl());//获取正在进行的会议
						newConfs = serviceFacade.getConferenceNewService().getMcuRestoreConfs();
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}
					if ((conferences != null && conferences.size() > 0) 
							|| (newConfs != null && newConfs.size() > 0)) {
						int csize = 0;
						try {
							csize = McuUtil.getExistConfSize(bean.getServerUrl());// 连通MCU并查询现有会议
							if (csize == 0) {
								if(conferences != null){
									for (ConferenceBean conf : conferences) {
										if(Constant.MIX==conf.getIsmix())
										     McuUtil.createConference(conf);// 恢复混合型会议
										else if(Constant.NOTMIX==conf.getIsmix())
											McuUtil.createForwardConference(conf.getConfno(),conf.getCustomer().getMcuServer().getServerUrl());// 恢复转发型会议
										else
											McuUtil.createConference(conf);// 恢复混合型会议
									}
								}
								if(newConfs != null){
									for (ConferenceNewBean conf : newConfs) {
										McuUtil.createForwardConference(conf.getConfNo(), bean.getServerUrl());// 恢复转发型会议
									}
								}
								Thread.sleep(2000);//查看打印信息
								log.info("===恢复MCU会议");
							} else {
								log.info("===查询MCU会议不为零");
								conferences = null;
								newConfs = null;
								continue;
							}
						} catch (Exception ex) {
							log.info("===连通不了MCU");
							conferences = null;
							newConfs = null;
							continue;
						}
						conferences = null;
						newConfs = null;
					} else {
						log.info("===没有正在进行的会议");
						continue;
					}
				}
			}
			list = null;
		}
	}
}
