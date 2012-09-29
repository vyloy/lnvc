package com.lorent.service.impl;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Hibernate;
import org.quartz.SchedulerException;
import com.lorent.dao.CronConferenceDao;
import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.ConferenceBean;
import com.lorent.model.ConferenceNoBean;
import com.lorent.model.CronConferenceBean;
import com.lorent.model.CustomerBean;
import com.lorent.service.CronConferenceService;
import com.lorent.trigger.CronConfStartTrigger;
import com.lorent.trigger.QuartzTrigger;
import com.lorent.util.Constant;
import com.lorent.util.MailUtil;
import com.lorent.util.StringUtil;
import com.lorent.util.ThreadLocaleUtil;

public class CronConferenceServiceImpl extends GenericServiceImpl<CronConferenceDao,CronConferenceBean,Integer> implements
		CronConferenceService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 创建会议
	 * @param conference
	 * @return
	 * @throws CustomSqlException
	 * @throws ArgsException
	 * @throws SchedulerException
	 */
	public Integer createCronConference(CronConferenceBean conference)
		throws CustomSqlException, ArgsException,SchedulerException {
		//设置周期会议的cron表达式
		setCronExpression(conference);
		conference.setStatus(Constant.RECORD_STATUS_VALID);
		//将周期会议信息持久化
		Integer confId = daoFacade.getCronConferenceDao().save(conference);
		//conference.setConferenceNo(daoFacade.getStaticDao().get(conference.getConferenceNo().getId(), ConferenceNoBean.class));
		initConfCustomer(conference);
//		Hibernate.initialize(conference.getUsers());
		//将周期会议触发任务添加到任务列表
		QuartzTrigger.addTask(new CronConfStartTrigger(conference), serviceFacade);
		MailUtil.conferenceInviteAdvice(conference, ThreadLocaleUtil.getUser());
		return confId;
	}
	/**
	 * 更新会议信息
	 * @param conference
	 * @throws CustomSqlException
	 * @throws ArgsException
	 * @throws SchedulerException
	 */
	public void renewCronConference(CronConferenceBean conference) 
		throws CustomSqlException, ArgsException,SchedulerException {
		//设置周期会议的cron表达式
		setCronExpression(conference);
		//将周期会议触发任务添加到任务列表
		QuartzTrigger.addTask(new CronConfStartTrigger(conference), serviceFacade);
		//将周期会议信息持久化
		daoFacade.getCronConferenceDao().update(conference);
		MailUtil.conferenceChangeAdvice(conference, ThreadLocaleUtil.getUser());
	}
	/**
	 * 删除会议
	 * @param ids
	 * @throws CustomSqlException
	 * @throws ArgsException
	 */
	public void removeConf(Integer[] ids) throws CustomSqlException, ArgsException,SchedulerException {
		//正在进行的会议不能删除
		List<CronConferenceBean> confs = get(ids);
		for(CronConferenceBean conf : confs){
			if(Constant.CONF_STATUS_ONGOING.equals(conf.getConfStatus())){
				throw new ArgsException("args.confisgoing");
			}
		}
		for (int i = 0; i < ids.length; i++) {	
			removeConf(ids[i]);
		}
	}
	/**
	 * 删除会议
	 * @param id
	 * @throws CustomSqlException
	 * @throws ArgsException
	 */
	public void removeConf(Integer id) throws CustomSqlException, ArgsException,SchedulerException {
		CronConferenceBean conf = daoFacade.getCronConferenceDao().get(id); 
		if(conf==null)
			throw new ArgsException("");
		//改变会议记录的状态
		conf.setStatus(Constant.RECORD_DELETED);
		//从任务列表中移除会议触发任务
		QuartzTrigger.removeTask(Constant.TRIGGER_CRON_CONF_PREFIX+conf.getId().toString(), CronConferenceBean.class.toString());
		//更新会议记录
		daoFacade.getCronConferenceDao().update(conf);
	}
	
	/* ================================= private methods ================================= */
	/**
	 * 设置会议的cron表达式
	 */
	private void setCronExpression(CronConferenceBean conference) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String cronTime = sdf.format(conference.getCronHours());
		cronTime = cronTime.substring(cronTime.indexOf(" ")+1);
		String hour = cronTime.split(":")[0];
		String min = cronTime.split(":")[1];
		String expression = "0 "+min+" "+hour+" ";
		switch (conference.getCronType()) {
		case Constant.CRON_TYPE_MONTH:
			expression += conference.getCronTime()+" * ?";
			break;
		case Constant.CRON_TYPE_WEEK:
			expression += "? * "+conference.getCronTime();
			break;
		default:
			break;
		}
		conference.setCronExpression(expression);
	}
	/**
	 * 初始化会议对象对应的客户对象的各种
	 * @param conference
	 * @throws CustomSqlException
	 */
	private void initConfCustomer(ConferenceBean conference)throws CustomSqlException {
		CustomerBean customer = daoFacade.getCustomerDao().get(conference.getCustomer().getId());
		Hibernate.initialize(customer);
		Hibernate.initialize(customer.getMcuServer());
		Hibernate.initialize(customer.getMcuServer().getMixers());
		Hibernate.initialize(customer.getRates());
		conference.setCustomer(customer);
	}
	
//	/**
//	 * 生成周期会议号码
//	 * @param customerCode
//	 * @return
//	 */
//	public String createPerConfNo(String customerCode)throws Exception{
//		String no = "";
//		if(StrUtil.isEmpty(customerCode)){
//			throw new ArgsException("args.customercodeisempty");
//		}
//		//生成周期会议号码 周期会议号码=客户代码+“1”+随机数，并判断是否重复
//		boolean isOK = true;
//		String hql = "select c from CronConferenceBean c where c.status = 1 and c.confno = ";
//		while(isOK){
//			no = customerCode + "1" + StrUtil.getRandom(Constant.CONFERENCE_NO_LENGTH - Constant.CUSTOMER_CODE_LENGTH - 1);
//			List list = daoFacade.getCronConferenceDao().queryByHql(hql+"'"+no+"'");
//			if(list.size() == 0){
//				isOK = false;
//			}
//		}
//		return no;
//	}
//	public boolean canCreatePerConf(CustomerBean customer) throws Exception {
//		String codePrefix = "'" + customer.getCustomerCode() + "1%'";
//		String hql = "select count(c) from CronConferenceBean c where c.status = 1 and c.confno like " + codePrefix;
//		List list = daoFacade.getCronConferenceDao().queryByHql(hql);
//		long num = (Long)(list.get(0));
//		if(num < customer.getPerConfNoLimit()){
//			return true;
//		}else{
//			return false;
//		}
//	}
}
