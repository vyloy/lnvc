/**
 * 
 */
package com.lorent.astpack.service;

import java.util.Map;


/**
 * @author Administrator
 *
 */
public interface IConfService {
	 /**
	  * 添加指定的用户列表到指定会议
	  * @param confno 会议号码
	  * @param lccnos  lcc号码数组
	  * @throws Exception
	  */
	 public Map addUsersInConf(String confno, String[] lccnos)throws Exception;
	 
	 /**
	 * 在指定会议播放指定媒体
	 * @param confno	会议号码
	 * @param mediaPath		播放绝对媒体路径
	 * @param play		播放或停止
	 * @throws Exception
	 */
	public Map playMediaInConf(String confno, String mediaPath, boolean play)throws Exception;
	

	/**
	 * 强插,由lccnofrom插入lccnoto所在channel
	 * @param lccnofrom lcc号码
	 * @param lccnoto  lcc号码
	 * @return 
	 * @throws Exception
	 */
	public Map joinExtenSpy(String lccnofrom,String lccnoto,String prefix) throws Exception;
	
	
	/**
	 * 强拆,强拆某LCC号码连接的Channel
	 * @param lccno lcc号码
	 * @return
	 * @throws Exception
	 */
	public Map forceDisconnect(String lccno) throws Exception;
	
	/**
	 * 监听
	 * @param lccno lcc号码
	 * @param lccTarget 监听目标
	 * @return
	 * @throws Exception
	 */
	public Map listenExtenSpy(String lccno,String targetlccno,String prefix) throws Exception;
	
	/**
	 * 单拨
	 * @param lccnoFrom 主叫lcc号码
	 * @param lccnoTo 被叫lcc号码
	 * @return
	 * @throws Exception
	 */
	public Map call(String lccnoFrom,String lccnoTo) throws Exception;
	
//	public Map callAsync(String lccnoFrom,String lccnoTo) throws Exception;
	
	
	/**
	 * 代答(转接)，由lccnoHost来接听lccnoBy需要接听的呼叫,备注：双方在呼叫，未接通的状态
	 * @param lccnoHost
	 * @param lccnoBy
	 * @return
	 * @throws Exception
	 */
	public Map answer(String lccnoHost,String lccnoBy) throws Exception;
	
	
	/**
	 * 静音
	 * @param confno 会议号码
	 * @param lccno 被静音号码
	 * @return
	 * @throws Exception
	 */
	public Map meetMeMute(String confno,String lccno) throws Exception;
	
	/**
	 * 获得该lcc号码的状态
	 * @param lccno lcc号码
	 * @return map.getValue("Status")=com.lorent.astpack.status.XXXXX
	 * @throws Exception
	 */
	public Map getPeerStatus(String lccno) throws Exception;
}
