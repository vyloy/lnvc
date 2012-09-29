package com.lorent.trigger;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;

import com.lorent.exception.CustomSqlException;
import com.lorent.model.ConferenceBean;
import com.lorent.model.McuServerBean;
import com.lorent.service.impl.ServiceFacade;
import com.lorent.util.Constant;
import com.lorent.util.McuUtil;

public class HeartBeatTrigger extends QuartzTrigger{
	
	private static final Logger log = Logger.getLogger(HeartBeatTrigger.class);
	
	private int intevalTime;
	
	//private ConferenceBean conf;
	public HeartBeatTrigger(ConferenceBean conf){
		this.data = conf;
		//this.conf = conf;
	}
	public HeartBeatTrigger(){}
	
	public HeartBeatTrigger(int intevalTime){
		this.intevalTime = intevalTime;
	}
	/**
	 * override方法
	 */ 

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		ServiceFacade service = (ServiceFacade) context.getJobDetail()
				.getJobDataMap().get("service");

		try{
			service.getConferenceService().doRestoreConf();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try {
			HeartBeatTrigger nextTrigger = new HeartBeatTrigger();
			nextTrigger.intevalTime = Constant.getHeartBeatIntevalTime();
			QuartzTrigger.addTask(nextTrigger, service);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}


	}

	@Override
	public void setTrigger() {
		SimpleTrigger temp = new SimpleTrigger();
		temp.setStartTime(new Date(System.currentTimeMillis() + intevalTime));
		temp.setRepeatInterval(1);
		temp.setRepeatCount(0);
		temp.setName("HeartBeatTrigger");
		this.trigger = temp;
	}

	@Override
	public String jobName() {
		//if(conf==null||conf.getId()==null)
			return Constant.TRIGGER_HEART_BEAT+"test";
		//return Constant.TRIGGER_CONF_STOP_PREFIX+conf.getId();
	}

}
