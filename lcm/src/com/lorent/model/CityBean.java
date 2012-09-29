package com.lorent.model;

public class CityBean extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cityName;
	private String cityCode;
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj==this)return true;
		if(this==null||obj==null)return false;
		if(!(obj instanceof CityBean ))return false;
		CityBean city = (CityBean)obj;
		return this.cityName.equals(city.cityName)||
				this.cityCode.equals(city.cityCode);
	}
	@Override
	public int hashCode() {
		if(this==null||this.cityName==null)return 0;
		return this.cityName.hashCode();
	}
	@Override
	public String toString() {
		if(this==null)return null;
		return this.cityName+" "+this.cityCode;
	}
}
