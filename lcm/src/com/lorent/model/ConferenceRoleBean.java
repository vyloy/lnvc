package com.lorent.model;

public class ConferenceRoleBean extends BaseModel implements AbstractAclModel{

	private Integer del = new Integer(1);
	private String roleName;
	
	public ConferenceRoleBean(){
		
	}

	public ConferenceRoleBean(Integer id,String roleName){
		this.setId(id);
		this.setRoleName(roleName);
	}
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getDel() {
		return del;
	}

	public void setDel(Integer del) {
		this.del = del;
	}
	
}
