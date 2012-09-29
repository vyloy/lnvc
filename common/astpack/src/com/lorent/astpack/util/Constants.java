package com.lorent.astpack.util;

public class Constants {
	/**
	 * 忙
	 */
	//public static final int PEER_STATUS_BUSY = org.asteriskjava.manager.event.ExtensionStatusEvent.BUSY;//2
	/**
	 * 占线
	 */
	public static final int PEER_STATUS_INUSE = 1;//org.asteriskjava.manager.event.ExtensionStatusEvent.INUSE;//1
	/**
	 * 空闲
	 */
	public static final int PEER_STATUS_NOT_INUSE= 0;//org.asteriskjava.manager.event.ExtensionStatusEvent.NOT_INUSE;//0
	
	/**
	 * 呼出
	 */
	public static final int PEER_STATUS_RING = 7;
	/**
	 * 振铃(呼入)
	 */
	public static final int PEER_STATUS_RINGING= 8;//org.asteriskjava.manager.event.ExtensionStatusEvent.RINGING;//8
	/**
	 * 线路故障(离线)
	 */
	public static final int PEER_STATUS_UNAVAILABLE= 4;//org.asteriskjava.manager.event.ExtensionStatusEvent.UNAVAILABLE;//4
	
	/**
	 * 状态未知(错误)
	 */
	public static final int PEER_STATUS_UNKNOWN=-1;//
}
