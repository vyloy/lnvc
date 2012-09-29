package com.lorent.model;

public class AuthorityBean extends BaseModel implements AbstractAclModel{

	private Integer del = new Integer(1);
	private String authorityName;
	private String mark;
	public String getAuthorityName() {
		return authorityName;
	}
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public Integer getDel() {
		return del;
	}
	public void setDel(Integer del) {
		this.del = del;
	}
	
	
}
