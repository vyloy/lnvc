package com.lorent.common.tree;

import java.io.Serializable;

public class OrgTree<T1,T2> implements Serializable{

	private OrgNode<T1,T2> root = new OrgNode<T1,T2>();

	public OrgNode<T1,T2> getRoot() {
		return root;
	}

	public void setRoot(OrgNode<T1,T2> root) {
		this.root = root;
	}
	
	
	
}
