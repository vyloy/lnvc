package com.lorent.whiteboard.server.trigger;

import java.util.Date;
import java.util.List;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;

import com.lorent.whiteboard.server.DeleteMeetingFileUtil;


public class DeleteMeetingTrigger extends QuartzTrigger{
	
	
	private int intevalTime = Integer.parseInt(QuartzTrigger.config.getProperty("intevalTime"));
	
	
	public DeleteMeetingTrigger(){}
	
	public DeleteMeetingTrigger(int intevalTime){
		this.intevalTime = intevalTime;
	}
	/**
	 * override方法
	 */ 

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
//		System.out.println("intevalTime=============================" + intevalTime);
		DeleteMeetingFileUtil deleteMeetingFileUtil = null;
		try{
			Object service = context.getJobDetail()
			.getJobDataMap().get("service");
			if(service==null){
				deleteMeetingFileUtil = new DeleteMeetingFileUtil();
			}else{
				deleteMeetingFileUtil = (DeleteMeetingFileUtil)service;
			}
			deleteMeetingFileUtil.execute();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try {
			DeleteMeetingTrigger nextTrigger = new DeleteMeetingTrigger();
			QuartzTrigger.addTask(nextTrigger, deleteMeetingFileUtil);
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
		temp.setName("DeleteFileTrigger");
		this.trigger = temp;
	}

	@Override
	public String jobName() {
		//if(conf==null||conf.getId()==null)
			return "delete file trigger";
		//return Constant.TRIGGER_CONF_STOP_PREFIX+conf.getId();
	}

}
