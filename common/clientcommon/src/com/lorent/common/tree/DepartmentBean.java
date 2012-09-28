package com.lorent.common.tree;

import java.io.Serializable;
import java.util.List;

public class DepartmentBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int total;
	private int onlineNum;
	private List<Integer> departments;//新增时用,顺序：从父级到根级
	private String searchStr;
	private Integer parentId;


	public void copy(DepartmentBean bean){
		this.setDepartments(bean.getDepartments());
		this.setId(bean.getId());
		this.setName(bean.getName());
		this.setOnlineNum(bean.getOnlineNum());
		this.setParentId(bean.getParentId());
		this.setSearchStr(bean.getSearchStr());
		this.setTotal(bean.getTotal());
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public DepartmentBean(){
		
	}
	
	public DepartmentBean(int id,String name,int total,int onlineNum){
		this.id = id;
		this.name = name;
		this.total = total;
		this.onlineNum = onlineNum;
	}
	
	public List<Integer> getDepartments() {
		return departments;
	}
	public void setDepartments(List<Integer> departments) {
		this.departments = departments;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getOnlineNum() {
		return onlineNum;
	}
	public void setOnlineNum(int onlineNum) {
		this.onlineNum = onlineNum;
	}

	public String getSearchStr() {
		return searchStr;
	}

	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}
	
	
	
}
