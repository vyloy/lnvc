package com.lorent.vovo.bean;
/**
 * ��Ӱ����
 * @author Administrator
 *
 */
public class MsgBean {
	
	private int s32Id;             //��Ϣid
    private String as8MsgTitle;    //��Ϣ����
    private int s32ADType;         //��Ϣ����
    private String as8FullName;    //�ļ���
    private int s32FileType;       //�ļ�����
    private String as8RecvTime;    //����ʱ��
    private int s32NewFlag;        //�Ƿ��Ѷ�
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
