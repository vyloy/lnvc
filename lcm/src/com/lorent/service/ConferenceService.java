package com.lorent.service;

import java.util.List;

import org.quartz.SchedulerException;

import com.lorent.annotation.AfterLogger;
import com.lorent.dao.ConferenceDao;
import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.exception.CustomXmlRpcException;
import com.lorent.model.ConfNoStatusBean;
import com.lorent.model.ConferenceBean;
import com.lorent.model.CustomerBean;
import com.lorent.model.SipConfBean;

public interface ConferenceService extends IGenericService<ConferenceDao,ConferenceBean,Integer> {
	/**
	 * 创建会议
	 * @param conference
	 * @return
	 * @throws ArgsException
	 * @throws CustomSqlException
	 */
	@AfterLogger(msgKey="logger.ConferenceService.createConf")
	Integer createConf(ConferenceBean conference)throws Exception;	
	/**
	 * 更新会议
	 * @param conference
	 * @return
	 * @throws ArgsException
	 * @throws CustomSqlException
	 * @throws CustomSqlException
	 * @throws SchedulerException
	 */
	@AfterLogger(msgKey="logger.ConferenceService.renewConf")
	boolean renewConf(ConferenceBean conference)throws Exception;
	/**
	 * 删除会议
	 * @param conferenceId
	 * @return
	 * @throws ArgsException
	 * @throws CustomSqlException
	 */
	boolean removeConference(Integer conferenceId)throws Exception;
	
	/**
	 * 删除会议信息
	 * @param ids
	 * @return
	 * @throws ArgsException
	 * @throws CustomSqlException
	 */
	boolean removeConference(Integer[]ids)throws Exception;
	/**
	 * 终止会议
	 * @param conference
	 * @return
	 * @throws CustomSqlException
	 * @throws ArgsException
	 */
	boolean stopConference(Integer id)throws Exception;
	/**
	 * 终止会议
	 * @param ids
	 * @return
	 * @throws CustomSqlException
	 * @throws ArgsException
	 */
	boolean stopConference(Integer[]ids)throws Exception;
	/**
	 * 在mcu上创建会议并触发会议
	 * @param conference
	 * @throws CustomXmlRpcException
	 * @throws ArgsException
	 */
	void conferenceStartTrigger(ConferenceBean conference)throws Exception;
	/**
	 * 将会议从mcu上删除
	 * @param conference
	 * @throws CustomXmlRpcException
	 * @throws ArgsException
	 */
	void conferenceStopTrigger(ConferenceBean conference)throws Exception;
	/**
	 * 查找未触发的会议
	 * @return
	 * @throws CustomSqlException
	 */
	List<ConferenceBean>getUnTriggerConfs()throws Exception;
	/**
	 * 将lcm上的会议恢复到mcu上
	 * @param server
	 * @throws CustomSqlException
	 * @throws CustomXmlRpcException
	 */
	void restoreConferenceToMcu(String serverIp)throws Exception;
	
	public void mediaPlay(Integer confId, final boolean play)throws Exception;
	
	public void createConfNo(ConferenceBean conf, String customerCode, Integer conftype)throws Exception;
	
	public boolean canCreateConf(CustomerBean customer, Integer conftype) throws Exception;
	
	/**
	 * 获取默认的会议名称
	 * @return
	 */
	public String getDefaultConfSubject(String username)throws Exception;
	
	/**
	 * 创建会议室
	 * @param conf
	 * @return
	 * @throws Exception
	 */
	public Integer createConfMeeting(ConferenceBean conf)throws Exception;
	/**
	 * 修改会议室
	 * @param conf
	 * @return
	 * @throws Exception
	 */
	public boolean renewConfMeeting(ConferenceBean conf)throws Exception;
	/**
	 * 删除会议室
	 * @param conf
	 * @return
	 * @throws Exception
	 */
	public boolean removeConfMeeting(ConferenceBean conf)throws Exception;
	
	public boolean removeConfMeeting(Integer[] confIds)throws Exception;
	
	/**
	 * 获取要恢复到mcu的会议
	 * @param mcuIP
	 * @return
	 * @throws Exception
	 */
	public List<ConferenceBean> getMcuRestoreConfs(String mcuIP)throws Exception;
	
	/**
	 * 创建会议  供IP调度台使用(不带业务逻辑）
	 * @param conf
	 * @return
	 * @throws Exception
	 */
	public int createNoBusinessConf(ConferenceBean conf)throws Exception;
	
	
	
	/**
	 * 删除会议  供IP调度台使用(不带业务逻辑）
	 * @param confId
	 * @return
	 * @throws Exception
	 */
	public String removeNoBusinessConf(int confId) throws Exception;
	/**
	 * 更新会议  供IP调度台使用(不带业务逻辑）
	 * @param confId
	 * @param layout
	 * @return
	 * @throws Exception
	 */
	public boolean updateConf(int confId, int layout)throws Exception;
	
	public void doRestoreConf()throws Exception;
}

