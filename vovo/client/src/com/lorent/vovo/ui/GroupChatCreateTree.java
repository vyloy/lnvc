package com.lorent.vovo.ui;

import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.lorent.common.tree.MemberBean;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.bean.DepartmentTreeNode;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.DataUtil;
import com.lorent.vovo.util.TreeUtil;

public class GroupChatCreateTree extends DepartmentTreeUI {

	public GroupChatCreateTree(Class<? extends TreeNodeInfoPanel> pc) {
		super(pc);
	}
	
	public DefaultMutableTreeNode removeMember(MemberBean bean){
		DefaultMutableTreeNode node = super.removeMember(bean);
		DepartmentTreeNodePanel panel = (DepartmentTreeNodePanel) (node
				.getUserObject());
		GroupChatCreateDialog dialog = Vovo.getMyContext().getViewManager().getView(Constants.ViewKey.groupChatCreateDialog.toString());
		dialog.getSelectedUserList()
		.removeDepartmentTreeNodePanel(
				(DepartmentTreeNodePanel) panel);
		return node;
	}
	
	public DefaultMutableTreeNode removeDept(int deptId){
		DefaultMutableTreeNode node = super.removeDept(deptId);
		removeSelectedUserListElement(node);
		return node;
	}
	
	public DefaultMutableTreeNode updateMember(MemberBean bean, int[] paras)throws Exception{
		DefaultMutableTreeNode node = super.updateMember(bean, paras);
		TreeNodeInfoPanel panel = (TreeNodeInfoPanel)node.getUserObject();
		panel.setInfo(panel.getInfo());
		GroupChatCreateDialog dialog = Vovo.getMyContext().getViewManager().getView(Constants.ViewKey.groupChatCreateDialog.toString());
		if(dialog != null){
			dialog.getSelectedUserList()
			.reflashMemberNode(
					(DepartmentTreeNodePanel) panel);
		}
		return node;
	}

	public TreeNodeInfoPanel removeMemberNode(String jid,List<Integer> departments){
		TreeNodeInfoPanel panel = super.removeMemberNode(jid,departments);
		GroupChatCreateDialog dialog = Vovo.getMyContext().getViewManager().getView(Constants.ViewKey.groupChatCreateDialog.toString());
		dialog.getSelectedUserList()
		.removeDepartmentTreeNodePanel(
				(DepartmentTreeNodePanel) panel);
		return panel;
	}
	
	public DefaultMutableTreeNode removeDepartmentNode(int departmentId){
		DefaultMutableTreeNode node = super.removeDepartmentNode(departmentId);
		removeSelectedUserListElement(node);
		return null;
	}
	
	public void removeSelectedUserListElement(DefaultMutableTreeNode node){
		Enumeration<DefaultMutableTreeNode> en = node.children();
		GroupChatCreateDialog dialog = Vovo.getMyContext().getViewManager().getView(Constants.ViewKey.groupChatCreateDialog.toString());
		while (en.hasMoreElements()) {
			DefaultMutableTreeNode childNode = en.nextElement();
			TreeNodeInfoPanel panel = (TreeNodeInfoPanel) childNode.getUserObject();
			if(panel.getInfo().isDepartmentFlag()){
				removeSelectedUserListElement(childNode);
			}else{
				dialog.getSelectedUserList()
				.removeDepartmentTreeNodePanel(
						(DepartmentTreeNodePanel) panel);
			}
		}
	}
	
	public TreeNodeInfoPanel reflashMemberNode(MemberBean bean,int idx){
		DepartmentTreeNodePanel panel = (DepartmentTreeNodePanel)super.reflashMemberNode(bean, idx);
		panel.setInfo(panel.getInfo());
		GroupChatCreateDialog dialog = Vovo.getMyContext().getViewManager().getView(Constants.ViewKey.groupChatCreateDialog.toString());
		if(dialog != null){
			dialog.getSelectedUserList()
			.reflashMemberNode(
					(DepartmentTreeNodePanel) panel);
		}
		return panel;
	}
	
}
