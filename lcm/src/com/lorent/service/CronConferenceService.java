package com.lorent.service;

import org.quartz.SchedulerException;

import com.lorent.dao.CronConferenceDao;
import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.CronConferenceBean;
import com.lorent.model.CustomerBean;

public interface CronConferenceService extends IGenericService<CronConferenceDao,CronConferenceBean,Integer> {
	/**
	 * 创建会议
	 * @param conference
	 * @return
	 * @throws CustomSqlException
	 * @throws ArgsException
	 * @throws SchedulerException
	 */
	Integer createCronConference(CronConferenceBean conference)throws CustomSqlException,ArgsException,SchedulerException;
	/**
	 * 更新会议信息
	 * @param conference
	 * @throws CustomSqlException
	 * @throws ArgsException
	 * @throws SchedulerException
	 */
	void renewCronConference(CronConferenceBean conference)throws CustomSqlException,ArgsException,SchedulerException;
	/**
	 * 删除会议
	 * @param ids
	 * @throws CustomSqlException
	 * @throws ArgsException
	 */
	void removeConf(Integer[]ids)throws CustomSqlException,ArgsException,SchedulerException;
	/**
	 * 删除会议
	 * @param id
	 * @throws CustomSqlException
	 * @throws ArgsException
	 */
	void removeConf(Integer id)throws CustomSqlException,ArgsException,SchedulerException;
//	/**
//	 * 生成周期会议号码
//	 * @param customerCode
//	 * @return
//	 */
//	public String createPerConfNo(String customerCode)throws Exception;
//	
//	/**
//	 * 判断用户是否能够创建周期会议
//	 * @param customer
//	 * @return
//	 * @throws Exception
//	 */
//	public boolean canCreatePerConf(CustomerBean customer)throws Exception;
}
