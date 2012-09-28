package com.lorent.trigger;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;

import com.lorent.model.McuServerBean;
import com.lorent.service.impl.ServiceFacade;
import com.lorent.util.Constant;

public class McuRestoreTrigger extends QuartzTrigger {
	private McuServerBean server;
	public McuRestoreTrigger(){}
	public McuRestoreTrigger(McuServerBean server){
		this.data = server;
		this.server = server;
	}

	@Override
	public String jobName() {
		if(server==null)
			return Constant.TRIGGER_MCU_RESTORE_PREFIX+"test";
		return Constant.TRIGGER_MCU_RESTORE_PREFIX+server.getServerUrl();
	}

	@Override
	public void setTrigger() {
		SimpleTrigger temp = new SimpleTrigger();
		temp.setStartTime(new Date());
//		temp.setRepeatInterval(1000);
//		temp.setRepeatCount(-1);
		temp.setName(Constant.TRIGGER_MCU_RESTORE_PREFIX+server.getServerUrl());
		this.trigger = temp;
	}

	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		McuServerBean server = (McuServerBean)context.getJobDetail().getJobDataMap().get("0");
		System.out.println("in task restore conferences and mixers to mcu ---------------server:"+server.getServerUrl());
//		if(!McuUtil.mcuReady(server.getServerUrl()))
//			return;
		ServiceFacade serviceFacade = (ServiceFacade)context.getJobDetail().getJobDataMap().get("service");
		try {
			System.out.println("in task restore conferences and mixers to mcu ---------------serviceFacade:"+serviceFacade);
			serviceFacade.getMcuServerService().restoreMixersToMcu(server.getServerIp());
			serviceFacade.getConferenceService().restoreConferenceToMcu(server.getServerIp());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			QuartzTrigger.removeTask(Constant.TRIGGER_MCU_RESTORE_PREFIX+server.getServerUrl(), this.getClass().toString());
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)throws Exception {
		McuServerBean server = new McuServerBean();
		server.setServerName("server");
		QuartzTrigger.addTask(new McuRestoreTrigger(server), new ServiceFacade());
	}

}
