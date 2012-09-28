package com.lorent.trigger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Hibernate;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.lorent.model.ConferenceBean;
import com.lorent.model.ConferenceNoBean;
import com.lorent.model.CronConferenceBean;
import com.lorent.model.CustomerBean;
import com.lorent.model.UserBean;
import com.lorent.service.impl.ServiceFacade;
import com.lorent.util.ClassUtil;
import com.lorent.util.Constant;
import com.lorent.util.ThreadLocaleUtil;

public class CronConfStartTrigger extends QuartzTrigger {
	private CronConferenceBean conference;
	public CronConfStartTrigger(CronConferenceBean conference){
		this.conference = conference;
		this.data = conference;
	}
	public CronConfStartTrigger(){}
	
	@Override
	public String jobName() {
		if(conference==null||conference.getId()==null)
			return Constant.TRIGGER_CRON_CONF_PREFIX+"test";
		return Constant.TRIGGER_CRON_CONF_PREFIX+ conference.getId().toString();
	}

	@Override
	public void setTrigger() {	
		CronExpression expression = null;
		try {
			expression = new CronExpression(conference.getCronExpression());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		CronTrigger temp = new CronTrigger();
		temp.setCronExpression(expression);
		temp.setName("confCronTrigger"+conference.getClass()+conference.getId());
		this.trigger = temp;
	}

	public void execute(JobExecutionContext context) throws JobExecutionException {
		ServiceFacade service = (ServiceFacade)context.getJobDetail().getJobDataMap().get("service");
		CronConferenceBean conf = (CronConferenceBean)context.getJobDetail().getJobDataMap().get("0");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		System.out.println("in task conference cron---------------------"+service);
		System.out.println("in tase conference cron---------------------"+conf);
		ConferenceBean realConf = new ConferenceBean();
		try {
			ClassUtil.getSuperObjectFromChild(realConf, conf);
//			realConf.setConferenceNo(conf.getConferenceNo());
			ConferenceNoBean no = new ConferenceNoBean();
//			no.setCustomerId(conf.getCustomer().getId());
			no.setIsUsed(false);
			no.setNoCode(conf.getConfno());
			service.getConferenceNoService().save(no);
			
			conf.setConferenceNo(no);
			realConf.setConferenceNo(no);
			Date now = new Date();
			Date endTime = new Date(now.getTime()+conf.getLength()*60*1000);
			realConf.setStartTime(new Date());
			realConf.setEndTime(endTime);
			realConf.setConfType(Constant.CONF_TYPE_PERCONF);
			ThreadLocaleUtil.setUser(realConf.getOwner());
			service.getConferenceService().createConf(realConf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
