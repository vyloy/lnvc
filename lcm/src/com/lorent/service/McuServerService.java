package com.lorent.service;

import com.lorent.dao.McuServerDao;
import com.lorent.exception.ArgsException;
import com.lorent.exception.CustomSqlException;
import com.lorent.exception.CustomXmlRpcException;
import com.lorent.model.McuServerBean;

public interface McuServerService extends IGenericService<McuServerDao, McuServerBean, Integer> {
	/**
	 * 创建或跟新mcu
	 * @param server
	 * @return
	 * @throws Exception
	 */
	boolean createOrUpdate(McuServerBean server)
		throws CustomSqlException,ArgsException,CustomXmlRpcException;
	/**
	 * 删除mcu服务器信息记录
	 * @param server
	 * @return
	 * @throws CustomSqlException
	 */
	boolean remove(McuServerBean server)
		throws CustomSqlException,ArgsException,CustomXmlRpcException;
	boolean remove(Integer[] ids)
		throws CustomSqlException,ArgsException,CustomXmlRpcException;
	/**
	 * 校验来自mcu的远程调用是否合法
	 * @param mcuIp
	 * @param rpcPass
	 * @return
	 * @throws CustomSqlException
	 * @throws ArgsException
	 */
	String mcuRpcValidate(String mcuIp,String rpcPass)
		throws CustomSqlException,ArgsException;
	/**
	 * 在mcu重启时将lcm数据库中保存的媒体处理器恢复到mcu上
	 * @param mcuIp
	 * @param rpcPass
	 * @throws CustomSqlException
	 * @throws ArgsException
	 */
	void restoreMixersToMcu(String mcuIp)throws CustomSqlException,ArgsException,CustomXmlRpcException;
}
