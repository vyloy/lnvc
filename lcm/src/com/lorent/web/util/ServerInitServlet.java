package com.lorent.web.util;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.lorent.common.util.SMSUtil;
import com.lorent.model.ConferenceBean;
import com.lorent.model.CronConferenceBean;
import com.lorent.service.impl.ServiceFacade;
import com.lorent.trigger.ConfStartTrigger;
import com.lorent.trigger.ConfStopTrigger;
import com.lorent.trigger.CronConfStartTrigger;
import com.lorent.trigger.HeartBeatTrigger;
import com.lorent.trigger.QuartzTrigger;
import com.lorent.ucs.sync.AllSync;
import com.lorent.ucs.sync.ConfigProcesser;
import com.lorent.ucs.sync.DeleteSync;
import com.lorent.util.Constant;
import com.lorent.util.OpenfireUtil;
import com.lorent.util.PropertiesUtil;
/**
 * 会议发起触发器Servlet，用于在Spring中配置Bean
 * 此类将通过Spring注入serviceFacade对象
 * 并通过Spring在其代理类中被创建
 * @author gary
 *
 */
public class ServerInitServlet extends GenericServlet implements Servlet {
	/**
	 * 
	 */
	private Logger log = Logger.getLogger(ServerInitServlet.class);
	private static final long serialVersionUID = 1L;
	private ServiceFacade serviceFacade;
	public void destroy() {
		super.destroy();
	}
	/**
	 * 在服务器启动时读取数据库记录触发会议 
	 */
	public void init(ServletConfig config) throws ServletException {

		//初始化数据库
		initDatabase();
		//添加会议触发任务
		addConfsTask();
		//添加周期会议触发任务
		addCronConfTask();
		//启动心跳机制
		startSYNUtil();
		//启动短信猫服务
		startSMSservice();
		//启动ucstar同步
		startUcstarSync();
	}

	private void startUcstarSync() {
		String ip = PropertiesUtil.getConstant("ucstar.ip");
		String port = PropertiesUtil.getConstant("ucstar.port");
		String delete = PropertiesUtil.getConstant("ucstar.deleteSync");
		String all = PropertiesUtil.getConstant("ucstar.allSync");
		ConfigProcesser dc = ConfigProcesser.parse(delete);
		ConfigProcesser ac = ConfigProcesser.parse(all);
		if(dc==null&&ac==null)
			return;
		ScheduledExecutorService pool;
		if(dc==null||ac==null){
			pool= Executors.newScheduledThreadPool(1,new ThreadFactory() {
				
				@Override
				public Thread newThread(Runnable r) {
					Thread t = new Thread(r,"UcstarSync");
					t.setDaemon(true);
					return t;
				}
			});
		}else{
			pool= Executors.newScheduledThreadPool(2,new ThreadFactory() {
				
				@Override
				public Thread newThread(Runnable r) {
					Thread t = new Thread(r,"UcstarSync");
					t.setDaemon(true);
					return t;
				}
			});
		}
		if(dc!=null){
			DeleteSync deleteSync = new DeleteSync(serviceFacade);
			try {
				deleteSync.bindWebService(ip, port);
				pool.scheduleWithFixedDelay(deleteSync, dc.getInitialDelay(), dc.getDelay(), dc.getTimeUnit());
			} catch (Exception e) {
				log.error("startUcstarDeleteSync",e);
				e.printStackTrace();
			}
		}
		if(ac!=null){
			AllSync allSync = new AllSync(serviceFacade);
			try {
				allSync.bindWebService(ip, port);
				pool.scheduleWithFixedDelay(allSync, ac.getInitialDelay(), ac.getDelay(), ac.getTimeUnit());
			} catch (Exception e) {
				log.error("startUcstarAllSync",e);
				e.printStackTrace();
			}
		}
		
	}
	private void startSMSservice(){
		try {
			String serialport = PropertiesUtil.getConstant("sms.serialport");
			log.info("SMS port: "+serialport);
			SMSUtil.startService(serialport);
//			System.out.println("SMS started service");
			log.info("SMS start service ");
		} catch (Exception e1) {
			log.error("startSMSservice ",e1);
			e1.printStackTrace();
		}
	}

	
	private void startSYNUtil() {
		try {
			QuartzTrigger.addTask(new HeartBeatTrigger(), serviceFacade);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ServiceFacade getServiceFacade() {
		return serviceFacade;
	}

	public void setServiceFacade(ServiceFacade serviceFacade) {
		this.serviceFacade = serviceFacade;
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		
	}
	
	/* ========================================= private methods ========================================== */
	/**
	 * 添加会议触发的任务
	 */
	private void addConfsTask(){
		try {
			List<ConferenceBean>confs = serviceFacade.getConferenceService().getUnTriggerConfs();
			if(confs==null||confs.size()==0)return;
			Iterator<ConferenceBean>it = confs.iterator();
			Date now = new Date();
			System.out.println("init trigger conference size ===================="+confs.size());
			while(it.hasNext()){
				ConferenceBean conf = it.next();
				//如果时候会议室则跳过
				if(conf.getConfType().equals(Constant.CONF_TYPE_MEETING)){
					continue;
				}
//				serviceFacade.getCronConferenceService().confHibernateInit(conf);
				System.out.println("==============================="+conf.getEndTime());
				System.out.println("==============================="+conf.getConfType());
				System.out.println("==============================="+conf.getConfStatus());
				//会议的结束时间<当前时间，并且会议是未结束状态
				if(conf.getEndTime()!=null){
					if(conf.getEndTime().before(now)){
						if(!conf.getConfStatus().equals(Constant.CONF_STATUS_FINISHED)){
							serviceFacade.getConferenceService().conferenceStopTrigger(conf);
						}
						continue;
					}
					//在任务列表中添加结束的会议的任务
					System.out.println("add imd conference stop trigger============"+conf.getConfSubject());
					QuartzTrigger.addTask(new ConfStopTrigger(conf),serviceFacade);
					//如果是即时会议，则不用创建会议，
					if(conf.getConfType().equals(Constant.CONF_TYPE_IMDCONF)){
						continue;
					}
					//否则将根据数据库记录中的会议开始时间触发会议
					if(conf.getConfStatus().equals(Constant.CONF_STATUS_NOT_START)){
						System.out.println("add conference trigger task============================="+conf.getConfSubject());
						QuartzTrigger.addTask(new ConfStartTrigger(conf),serviceFacade);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	/**
	 * 添加周期会议触发任务
	 *
	 */
	private void addCronConfTask(){
		try {
			//查找所有未删除的周期会议并加入任务列表
			DetachedCriteria criteria = DetachedCriteria.forClass(CronConferenceBean.class);
			criteria.add(Restrictions.eq("status", Constant.RECORD_STATUS_VALID));
			List<CronConferenceBean>cronConfs = serviceFacade.getCronConferenceService().getByCriteria(criteria);
			if(cronConfs==null||cronConfs.size()==0)return;
			System.out.println("server init cron conference trigger===========================");
			System.out.println("cron conference size==============================="+cronConfs.size());
			Iterator<CronConferenceBean>cronIterator = cronConfs.iterator();
			while (cronIterator.hasNext()) {
				CronConferenceBean cronConf = cronIterator.next();
				System.out.println("add cron conference task======================="+cronConf.getConfSubject());
				QuartzTrigger.addTask(new CronConfStartTrigger(cronConf), serviceFacade);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 初始化数据库
	 *
	 */
	private void initDatabase()throws ServletException{
		try{
			serviceFacade.getUserService().createInitData();
		}catch(Exception e){
			throw new ServletException(e);
		}
	}

}
