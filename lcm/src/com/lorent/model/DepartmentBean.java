package com.lorent.model;

import java.io.Serializable;

public class DepartmentBean extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String departmentName;
	private String departmentDesc = "";
	private Integer customerId;
//	private Set<UserBean>users = new HashSet<UserBean>();
	private Integer parentId;
	private String parentName;
	private Boolean isRoot;
	private String code;
	private Integer order = 0;
	private String searchStr;
	
	
	
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public String getSearchStr() {
		return searchStr;
	}
	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentDesc() {
		return departmentDesc;
	}
	public void setDepartmentDesc(String departmentDesc) {
		this.departmentDesc = departmentDesc;
	}
//	public Set<UserBean> getUsers() {
//		return users;
//	}
//	public void setUsers(Set<UserBean> users) {
//		this.users = users;
//	}

	public Boolean getIsRoot() {
		return isRoot;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public void setIsRoot(Boolean isRoot) {
		this.isRoot = isRoot;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("DepartmentBean:{");
		sb.append("name:").append(departmentName)
			.append(", parentId:").append(parentId)
			.append(", parentName:").append(parentName)
			.append(", code:").append(code)
			.append(", order:").append(order)
			.append("}");
		return sb.toString();
	}
}
