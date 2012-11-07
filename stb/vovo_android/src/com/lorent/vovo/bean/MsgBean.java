package com.lorent.vovo.bean;
/**
 * 留影留言
 * @author Administrator
 *
 */
public class MsgBean {
	
	private int s32Id;             //消息id
    private String as8MsgTitle;    //消息标题
    private int s32ADType;         //消息类型
    private String as8FullName;    //文件名
    private int s32FileType;       //文件类型
    private String as8RecvTime;    //接收时间
    private int s32NewFlag;        //是否已读
	public int getS32Id() {
		return s32Id;
	}
	public void setS32Id(int s32Id) {
		this.s32Id = s32Id;
	}
	public String getAs8MsgTitle() {
		return as8MsgTitle;
	}
	public void setAs8MsgTitle(String as8MsgTitle) {
		this.as8MsgTitle = as8MsgTitle;
	}
	public int getS32ADType() {
		return s32ADType;
	}
	public void setS32ADType(int s32adType) {
		s32ADType = s32adType;
	}
	public String getAs8FullName() {
		return as8FullName;
	}
	public void setAs8FullName(String as8FullName) {
		this.as8FullName = as8FullName;
	}
	public int getS32FileType() {
		return s32FileType;
	}
	public void setS32FileType(int s32FileType) {
		this.s32FileType = s32FileType;
	}
	public String getAs8RecvTime() {
		return as8RecvTime;
	}
	public void setAs8RecvTime(String as8RecvTime) {
		this.as8RecvTime = as8RecvTime;
	}
	public int getS32NewFlag() {
		return s32NewFlag;
	}
	public void setS32NewFlag(int s32NewFlag) {
		this.s32NewFlag = s32NewFlag;
	}
}
