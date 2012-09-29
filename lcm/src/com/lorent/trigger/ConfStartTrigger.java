package com.lorent.trigger;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SimpleTrigger;
import com.lorent.model.ConferenceBean;
import com.lorent.service.impl.ServiceFacade;
import com.lorent.util.Constant;

public class ConfStartTrigger extends QuartzTrigger{
	private ConferenceBean conf;
	public ConfStartTrigger(ConferenceBean conf){
		this.data = conf;
		this.conf = conf;
	}
	public ConfStartTrigger(){
		
	}
	/**
	 * override方法
	 */ 
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ConferenceBean conf = (ConferenceBean)context.getJobDetail().getJobDataMap().get("0");
		ServiceFacade service = (ServiceFacade)context.getJobDetail().getJobDataMap().get("service");
		try {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			System.out.println("in task conference start---------------serviceFacade:"+service);
			System.out.println("in task conference start---------------conf:"+conf);
			service.getConferenceService().conferenceStartTrigger(conf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setTrigger() {
		SimpleTrigger temp = new SimpleTrigger();
		temp.setStartTime(conf.getStartTime());
		temp.setRepeatInterval(1000);
		temp.setRepeatCount(0);
		temp.setName("trigger "+conf.getClass().toString()+conf.getId());
		this.trigger = temp;
	}

	@Override
	public String jobName() {
		if(conf==null||conf.getId()==null)
			return Constant.TRIGGER_CONF_START_PREFIX+"test";
		return Constant.TRIGGER_CONF_START_PREFIX+conf.getId().toString();
	}

}
