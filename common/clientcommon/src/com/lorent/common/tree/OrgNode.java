package com.lorent.common.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrgNode<T1,T2> implements Serializable{

	private OrgNode<T1,T2> parent;
	private List<OrgNode<T1,T2>> childNodes = new ArrayList<OrgNode<T1,T2>>();;
	private T1 organization;
	private T2 member;
	public OrgNode<T1, T2> getParent() {
		return parent;
	}
	public void setParent(OrgNode<T1, T2> parent) {
		this.parent = parent;
	}
	public List<OrgNode<T1, T2>> getChildNodes() {
		return childNodes;
	}
	public void setChildNodes(List<OrgNode<T1, T2>> childNodes) {
		this.childNodes = childNodes;
	}
	
	public T1 getOrganization() {
		return organization;
	}
	public void setOrganization(T1 organization) {
		this.organization = organization;
	}
	public T2 getMember() {
		return member;
	}
	public void setMember(T2 member) {
		this.member = member;
	}
	
	public void addChild(OrgNode<T1,T2> child){
		child.setParent(this);
		getChildNodes().add(child);
	}
	
}
