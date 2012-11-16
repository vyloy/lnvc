package com.qd.bean;

public class EPGInfo {
	
	private String programName;
	private int programStartTime;
	private int programDurationTime;
	private String programDate;
	private String programIntroduce;
	private int orderFlag;
	private int eventID;
	
	public int getEventID()
	{
		return eventID;
	}
	
	public void setEventID(int eventID)
	{
		this.eventID = eventID;
	}
	
	public int getOrderFlag()
	{
		return orderFlag;
	}
	
	public void setOrderFlag(int orderFlag)
	{
		this.orderFlag = orderFlag;
	}
	
	public String getProgramIntroduce()
	{
		return programIntroduce;
	}
	
	public void setProgramIntroduce(String programIntroduce)
	{
		this.programIntroduce = programIntroduce;
	}
	
	public String getProgramName()
	{
		return programName;
	}
	
	public void setProgramName(String programName)
	{
		this.programName = programName;
	}
	
	
	public int getProgramStartTime()
	{
		return programStartTime;
	}
	
	public void setProgramStartTime(int programStartTime)
	{
		this.programStartTime = programStartTime;
	}
	
	public int getProgramDurationTime()
	{
		return programDurationTime;
	}
	
	public void setProgramDurationTime(int programDurationTime)
	{
		this.programDurationTime = programDurationTime;
	}
	
	public String getProgramDate()
	{
		return programDate;
	}
	
	public void setProgramDate(String programDate)
	{
		this.programDate = programDate;
	}

}
