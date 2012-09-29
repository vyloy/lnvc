package com.lorent.vovo.bean;

import javax.swing.tree.DefaultMutableTreeNode;

public class MyFlag {

	private boolean f = false;
	private DepartmentTreeNode node1;
	private DefaultMutableTreeNode node2;
	public DefaultMutableTreeNode getNode2() {
		return node2;
	}
	public void setNode2(DefaultMutableTreeNode node2) {
		this.node2 = node2;
	}
	
	
	public DepartmentTreeNode getNode1() {
		return node1;
	}
	public void setNode1(DepartmentTreeNode node1) {
		this.node1 = node1;
	}
	public void setValue(boolean a){f=a;}
	public boolean getValue(){return f;}
	
}
