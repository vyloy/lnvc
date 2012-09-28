package com.lorent.vovo.bean;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

import com.lorent.vovo.util.Constants;

public class DepartmentTreeNode {

	private DepartmentTreeNode parent;
	private static java.util.Comparator comparator = new DepartmentTreeNodeComparator();
	private TreeMap<TreeNodeInfo,DepartmentTreeNode> children = new TreeMap<TreeNodeInfo,DepartmentTreeNode>(comparator);
//	private DepartmentTreeNodePanel panel;
	private TreeNodeInfo info = new TreeNodeInfo();
	private int index;
	
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public TreeNodeInfo getInfo() {
		return info;
	}

	public void setInfo(TreeNodeInfo info) {
		this.info = info;
	}

//	public DepartmentTreeNodePanel getPanel() {
//		return panel;
//	}
//
//	public void setPanel(DepartmentTreeNodePanel panel) {
//		this.panel = panel;
//	}
//
//	public DepartmentTreeNode(DepartmentTreeNodePanel p){
//		panel = p;
//	}

	public DepartmentTreeNode getParent() {
		return parent;
	}

	public void setParent(DepartmentTreeNode parent) {
		this.parent = parent;
	}

	public TreeMap<TreeNodeInfo,DepartmentTreeNode> getChildren() {
		return children;
	}

	public void setChildren(TreeMap<TreeNodeInfo,DepartmentTreeNode> children) {
		this.children = children;
		Set<TreeNodeInfo> set = children.keySet();
		for(TreeNodeInfo info : set){
			children.get(info).setParent(this);
		}
	}
	
	public void addChildNode(DepartmentTreeNode node){
		node.setParent(this);
		synchronized (Constants.USER_STATE_LOCK){
			children.put(node.getInfo(), node);
		}
	}
	
	
	public void removeChildNode(DepartmentTreeNode node){
		if(node == null){
			return ;
		}else if(node.getInfo() == null){
			return ;
		}
//		print(children);
		synchronized (Constants.USER_STATE_LOCK){
			children.remove(node.getInfo());
		}
//		System.out.println("del after");
//		print(children);
	}
	
	/*
	public synchronized void removeChildNode(DepartmentTreeNode node){
		if(node == null){
			return ;
		}else if(node.getInfo() == null){
			return ;
		}
		print(children);
		TreeMap<TreeNodeInfo,DepartmentTreeNode> temp = new TreeMap<TreeNodeInfo,DepartmentTreeNode>(comparator);
		ArrayList<TreeNodeInfo> keys = new ArrayList<TreeNodeInfo>();
		ArrayList<DepartmentTreeNode> values = new ArrayList<DepartmentTreeNode>();
		java.util.Set<TreeNodeInfo> set = children.keySet();
		for(TreeNodeInfo key:set){
			if(node.getInfo().isDepartmentFlag() && key.isDepartmentFlag()){
				if(node.getInfo().getDepartment().getId()==key.getDepartment().getId()){
					keys.add(key);
				}else{
					System.out.println(children.get(key));
					temp.put(key, children.get(key));
				}
			}else if(!node.getInfo().isDepartmentFlag() && !key.isDepartmentFlag()){
				if(node.getInfo().getMember().getLccAccount().equals(key.getMember().getLccAccount())){
					keys.add(key);
				}else{
					System.out.println(children.get(key));
					temp.put(key, children.get(key));
				}
			}else{
				System.out.println(children.get(key));
				temp.put(key, children.get(key));
			}
		}
		
		java.util.Collection<DepartmentTreeNode> col = children.values();
		for(DepartmentTreeNode n:col){
			if(n!=null){
				if(node.getInfo().isDepartmentFlag() && n.getInfo().isDepartmentFlag()){
					if(node.getInfo().getDepartment().getId()==n.getInfo().getDepartment().getId()){
						;
					}else{
						temp.put(n.getInfo(), n);
					}
				}else if(!node.getInfo().isDepartmentFlag() && !n.getInfo().isDepartmentFlag()){
					if(node.getInfo().getMember().getLccAccount().equals(n.getInfo().getMember().getLccAccount())){
						;
					}else{
						temp.put(n.getInfo(), n);
					}
				}else{
					temp.put(n.getInfo(), n);
				}
			}
		}
		
//		System.out.println("remove count==================" + keys.size());
//		for(TreeNodeInfo key : keys){
//			System.out.println(key.getMember().getRealName() + "=" + key.getMember().getId() + "=" + key.hashCode());
//			children.remove(key);
//		}
		for(DepartmentTreeNode v : values){
			System.out.println(v.getInfo().getMember().getRealName() + "=" + v.getInfo().getMember().getId() + "=" + v.getInfo().hashCode());
			children.remove(v.getInfo());
		}
		
		children.clear();
		children = temp;
		System.out.println("del after");
		print(children);
//		print(temp);
//		System.out.println("remove count==================" + keys.size());
		node.setParent(null);
	}*/
	
	public static void print(TreeMap<TreeNodeInfo,DepartmentTreeNode> map){
		java.util.Collection<DepartmentTreeNode> col = map.values();
		for(DepartmentTreeNode node:col){
			if(node!=null){
				
				if(node.getInfo().isDepartmentFlag()){
					System.out.println("(o)" + node.getInfo().getDepartment().getName()+"="+node.getInfo().hashCode());
				}else{
					System.out.println("(m)" + node.getInfo().getMember().getRealName()+"="+node.getInfo().hashCode());
				}
			}
		}
	}
	
}
