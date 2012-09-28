package com.lorent.trigger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import com.lorent.service.impl.ServiceFacade;

public abstract class QuartzTrigger implements Job {
	
	public static final Scheduler SCHEDULER ;                 //全局Scheduler
	protected Object data;                                    //需要保存到JobDataMap中的数据
	protected Trigger trigger;                          //trigger
	public abstract void setTrigger();                        //抽象方法，用于设置trigger
	public abstract String jobName();					      //抽象方法，用于户指定任务名称，通常使用data对象的ID
	/**
	 * 获取Scheduler
	 * @return 
	 */
	private static final Scheduler getScheduler(){
		try {
			return new StdSchedulerFactory().getScheduler();
		} catch (SchedulerException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 启动SCHEDULER
	 */
	static{
		SCHEDULER = getScheduler();
		try {
			if(SCHEDULER!=null&&!SCHEDULER.isStarted())SCHEDULER.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 将任务添加到scheduler任务队列
	 * @param task
	 * @throws SchedulerException
	 */
	public static void addTask(QuartzTrigger task,ServiceFacade service) throws SchedulerException{
		task.setTrigger(); 
		JobDetail jobDetail = new JobDetail(task.jobName(),task.getClass().toString(),task.getClass());
		if(SCHEDULER.getJobDetail(task.jobName(), task.getClass().toString())!=null){
			SCHEDULER.deleteJob(task.jobName(), task.getClass().toString());
		}
		JobDataMap map = jobDetail.getJobDataMap();
		if(map.get("service")==null)map.put("service", service);
		if(task.data!=null)map.put(new Integer(map.size()-1).toString(),task.data);
		jobDetail.setJobDataMap(map);
		SCHEDULER.scheduleJob(jobDetail,task.trigger);
		task.trigger.validate();
	}
	/**
	 * 将任务从scheduler任务队列中移除
	 * @param groupName
	 * @param jobName
	 * @throws SchedulerException
	 */
	public static void removeTask(String jobName,String groupName) throws SchedulerException{
		if(SCHEDULER.getJobDetail(jobName, groupName)==null)return;
		SCHEDULER.deleteJob(jobName, groupName);
	}
	
	
	/* ===================================================================================================== */
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}

