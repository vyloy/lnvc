package com.lorent.util;

import java.util.Map;

public interface McuUtil2 {
	
	//------------------------mcu client-----------------------------
	/**
	 * mcu回调(mcu调用)
	 * @param type 操作类别
	 * @param result	操作结果
	 * @throws Exception
	 */
	public void mcuCallBack(int type, int result)throws Exception;
	
	/**
	 * 当lcc呼入一个mcu不存在的会议时，mcu询问lcm是否创建(mcu调用)
	 * @param mcuip
	 * @param confno	会议号码
	 * @return
	 * @throws Exception
	 */
	public boolean canCreateConf(String mcuip, String confno)throws Exception;
	
	//------------------------mcu server-----------------------------
	/**
	 * 创建会议
	 * @param confno	会议号码
	 * @param layout	布局
	 * @param quality	音视频编码
	 */
	public boolean createConf(String confno, int layout, int quality)throws Exception;
	
	/**
	 * 修改会议
	 * @param confno	会议号码
	 * @param layout	布局
	 * @param quality	音视频编码 TODO 可能修改不了
	 */
	public boolean updateConf(String confno, int layout, int quality)throws Exception;
	
	/**
	 * 删除会议
	 * @param confno	会议号码
	 */
	public boolean removeConf(String confno)throws Exception;
	
	//------------------------下面的为可选，均属于mcu server-----------------------------
	
	/**
	 * 获取指定会议的在线用户
	 * @param confno	会议号码
	 * @return	在线用户lcc号码数组
	 * @throws Exception
	 */
	public String[] getConfUsers(String confno)throws Exception;
	
	/**
	 * 获取所有会议号码
	 * @return	会议号码数组
	 * @throws Exception
	 */
	public String[] getOnLineConfs()throws Exception;
	
	/**
	 * 设置指定会议指定用户的视频
	 * @param confno	会议号码
	 * @param lccno		lcc号码数组
	 * @param open		是否开启
	 * @throws Exception
	 */
	public boolean setUserVideo(String confno, String[] lccnos, boolean open)throws Exception;
	
	/**
	 * 设置指定会议指定用户的音频
	 * @param confno	会议号码
	 * @param lccnos	lcc号码数组
	 * @param open		是否开启
	 * @throws Exception
	 */
	public boolean setUserAudio(String confno, String[] lccnos, boolean open)throws Exception;
	
	/**
	 * 设置指定会议的视频
	 * @param confno	会议号码
	 * @param open	是否开启
	 */
	public boolean setConfVideo(String confno, boolean open)throws Exception;
	
	/**
	 * 设置指定会议的音频
	 * @param confno	会议号码
	 * @param open	是否开启
	 */
	public boolean setConfAudio(String confno, boolean open)throws Exception;	
	
	/**
	 * 从指定会议剔除指定用户
	 * @param confno	会议号码
	 * @param lccnos		lcc号码数组
	 * @throws Exception
	 */
	public boolean removeUserFromConf(String confno, String[] lccnos)throws Exception;
	
	/**
	 * 剔除指定会议的所有用户
	 * @param confno	会议号码
	 * @param lccnos		lcc号码数组
	 * @throws Exception
	 */
	public boolean removeUsersInConf(String confno)throws Exception;
	

	
	//------------------------cs-----------------------------
	
	
	/**
	 * 判断该lcc号是否在线
	 * @param lccno		lcc号码
	 * @return		true 在线 false 非在线状态
	 */
	public boolean isOnline(String lccno)throws Exception;
	
	/**
	 * 获取所有在线用户
	 * @return	在线用户lcc号码数组
	 * @throws Exception
	 */
	public Object[] getAllOnline()throws Exception;
	
	/**
	 * 获取指定用户状态
	 * @param lccnos	lcc号码数组
	 * @return	key-lcc号码 value-用户状态
	 * @throws Exception
	 */
	public Map getUsersStatus(String[] lccnos)throws Exception;
	/**
	 * 添加指定的用户列表到指定会议
	 * @param confno	会议号码
	 * @param lccnos		lcc号码数组
	 * @throws Exception
	 */
	public void addUsersInConf(String confno, String[] lccnos)throws Exception;
	/**
	 * 在指定会议播放指定媒体
	 * @param confno	会议号码
	 * @param mediaPath		播放绝对媒体路径
	 * @param play		播放或停止
	 * @throws Exception
	 */
	public void playMediaInConf(String confno, String mediaPath, boolean play)throws Exception;
	
}
