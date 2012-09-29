package com.lorent.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.GrantedAuthority;

/**
 * 角色Bean，与sys_role表对应
 * @author gary
 * @version 1.0
 * @since 2010-09-13
 */
public class RoleBean extends BaseModel implements AbstractAclModel, GrantedAuthority {

	public  RoleBean(String roleName,String roleCode,
				String roleDesc,int customerId,int status) {
		this.roleName = roleName;
		this.roleCode = roleCode;
		this.roleDesc = roleDesc;
		this.customerId = customerId;
	}
	
	public RoleBean(String roleCode) {
		this.roleCode = roleCode;
	}
	
	public RoleBean(){
		
	}
	
	private static final long serialVersionUID = 1L;
	private String roleName; //角色名称
	private String roleCode;//角色代码
	private Integer customerId;//客户ID
	private String roleDesc;//角色详细信息
//	private Set<UserBean>users = new HashSet<UserBean>();
	private Integer status;
	
//	public Set<UserBean> getUsers() {
//		return users;
//	}
//	public void setUsers(Set<UserBean> users) {
//		this.users = users;
//	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getAuthority() {
		return this.roleCode;
	}
	
	public int compareTo(Object o) {
		if(o!=null&&o instanceof GrantedAuthority){
			String role = ((GrantedAuthority)o).getAuthority();
			if(role==null)return -1;
			return role.compareTo(this.roleCode);
		}
		return -1;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this==obj)return true;
		if(obj==null)return false;
		if(this==null)return false;
		if(obj instanceof String)
			return obj.equals(this.roleCode);
		if(obj instanceof GrantedAuthority){
			GrantedAuthority attr = (GrantedAuthority)obj;
			return this.roleCode.equals(attr.getAuthority());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		if(this==null||this.roleCode==null)return 0;
		return this.roleCode.hashCode();
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
}
