package com.lorent.model;

/**
 * 系统参数
 * @author jack
 *
 */
public class SystemParamBean extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	//模块名称
	private String module;
	private String key;
	private String value;
	private String description;
	
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

	
}
