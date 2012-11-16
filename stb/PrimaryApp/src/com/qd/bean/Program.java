package com.qd.bean;



//存储音视频参数信息
public class Program
{	
	private String name;
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	private int freqency;
	private int modulation;
	private int symboRate;
	private int pmtPid;
	private int videoPid;
	private int audioPid;
	private int pcrPid;
	private int serviceId;
	private int tsId;
	
	
	
	public int getServiceId()
	{
		return serviceId;
	}
	
	public void setServiceId(int serviceId)
	{
		this.serviceId = serviceId;
	}
	
	public int getTsId()
	{
		return tsId;
	}
	
	public void setTsId(int tsId)
	{
		this.tsId = tsId;
	}
	
	public int getFreqency()
	{
		return freqency;
	}
	
	public void setFreqency(int freqency)
	{
		this.freqency = freqency;
	}
	
	public int getModulation()
	{
		return modulation;
	}
	
	public void setModulation(int modulation)
	{
		this.modulation = modulation;
	}
	
	public int getSymboRate()
	{
		return symboRate;
	}
	
	public void setSymboRate(int symboRate)
	{
		this.symboRate = symboRate;
	}
	
	public int getPmtPid()
	{
		return pmtPid;
	}
	
	public void setPmtPid(int pmtPid)
	{
		this.pmtPid = pmtPid;
	}
	
	public int getVideoPid()
	{
		return videoPid;
	}
	
	public void setVideoPid(int videoPid)
	{
		this.videoPid = videoPid;
	}
	
	public int getAudioPid()
	{
		return audioPid;
	}
	
	public void setAudioPid(int audioPid)
	{
		this.audioPid = audioPid;
	}
	
	public int getPcrPid()
	{
		return pcrPid;
	}
	
	public void setPcrPid(int pcrPid)
	{
		this.pcrPid = pcrPid;
	}

}
