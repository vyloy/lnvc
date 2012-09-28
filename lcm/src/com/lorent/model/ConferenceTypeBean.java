package com.lorent.model;

public class ConferenceTypeBean extends BaseModel implements AbstractAclModel{

	private Integer del = new Integer(1);
	private String typeName;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getDel() {
		return del;
	}

	public void setDel(Integer del) {
		this.del = del;
	}
	
}
