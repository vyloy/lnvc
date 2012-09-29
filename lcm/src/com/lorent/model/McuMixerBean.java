package com.lorent.model;

import java.io.Serializable;

import com.lorent.util.McuUtil;
import com.lorent.util.PropertiesUtil;

public class McuMixerBean extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mixerName;
	private String mixerKey;
	private String mixerIp;
	private McuServerBean server;
	
	public String getMixerName() {
		return mixerName;
	}
	public void setMixerName(String mixerName) {
		this.mixerName = mixerName;
	}
	public String getMixerKey() {
		return mixerKey;
	}
	public void setMixerKey(String mixerKey) {
		this.mixerKey = mixerKey;
	}
	public String getMixerIp() {
		return mixerIp;
	}
	public void setMixerIp(String mixerIp) {
		this.mixerIp = mixerIp;
	}
	public McuServerBean getServer() {
		return server;
	}
	public void setServer(McuServerBean server) {
		this.server = server;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj==null)return false;
		if(!(obj instanceof McuMixerBean))return false;
		McuMixerBean mixer = (McuMixerBean)obj;
		return mixer.mixerKey.equals(this.mixerKey)&&
				mixer.mixerIp.equals(this.mixerIp);
	}
	@Override
	public int hashCode() {
		if(this==null)return 0;
		return (this.mixerIp+this.mixerKey).hashCode();
	}
	@Override
	public String toString() {
		if(this==null)return null;
		return this.mixerKey
				+PropertiesUtil.getConstant("mcu.mixer.middlefix")
				+this.mixerIp
				+PropertiesUtil.getConstant("mcu.mixer.sufix");
	}
	
}
