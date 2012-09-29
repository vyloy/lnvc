package com.lorent.vovo.ui;

import java.awt.Component;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;

import com.lorent.common.tree.DepartmentBean;
import com.lorent.common.tree.MemberBean;
import com.lorent.vovo.bean.DepartmentTreeNode;
import com.lorent.vovo.bean.MyFlag;
import com.lorent.vovo.bean.TreeNodeInfo;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.TreeUtil;

public class DepartmentTreeUI extends JTree {

	// private static Map<String,String[]> userDepartMap = new
	// HashMap<String,String[]>();
	//	
	// private static DepartmentTreeNode departmentTreeNode = new
	// DepartmentTreeNode();

	protected Class panelClass;
	private Map<Integer, DefaultMutableTreeNode> memberMap = new HashMap<Integer, DefaultMutableTreeNode>();
	private Map<Integer, DefaultMutableTreeNode> deptMap = new HashMap<Integer, DefaultMutableTreeNode>();

	public DepartmentTreeUI(Class<? extends TreeNodeInfoPanel> pc) {
		this.panelClass = pc;
		DefaultTreeModel model = (DefaultTreeModel) this.getModel();
		DefaultMutableTreeNode node = createNodes();
		model.setRoot(node);
		// this.panelClass = (Class)((ParameterizedType)getClass()
		// .getGenericSuperclass()).getActualTypeArguments()[0];

		init();
	}

	public void init() {
//		this.setRootVisible(false);
		setCellRenderer(new CheckRenderer());
		addMouseListener(new DepartmentCheckboxListener());
	}

	public void setDefaultMutableTreeNode(DepartmentTreeNode node,
			DefaultMutableTreeNode mnode) {
		try {
			TreeNodeInfoPanel panel = (TreeNodeInfoPanel) panelClass
					.newInstance();
			panel.setInfo(node.getInfo());
			mnode.setUserObject(panel);
			if(panel.getInfo().isDepartmentFlag()){
				deptMap.put(panel.getInfo().getDepartment().getId(), mnode);
			}else{
				memberMap.put(panel.getInfo().getMember().getId(), mnode);
			}
			TreeMap<TreeNodeInfo, DepartmentTreeNode> map = node.getChildren();
			java.util.Collection<DepartmentTreeNode> col = map.values();
			for (DepartmentTreeNode n : col) {
				DefaultMutableTreeNode cmnode = new DefaultMutableTreeNode();
				mnode.add(cmnode);
				setDefaultMutableTreeNode(n, cmnode);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public DefaultMutableTreeNode createNodes() {
		DefaultMutableTreeNode top = null;
		synchronized (Constants.USER_STATE_LOCK) {

			DepartmentTreeNode root = TreeUtil.getRootDepartmentTreeNode();

			top = new DefaultMutableTreeNode();
			setDefaultMutableTreeNode(root, top);

		}
		return top;
	}
	
	public DefaultMutableTreeNode findDepartmentNodeByMemberInfo(DefaultMutableTreeNode tempTreeNode,List<Integer> departments){
		if (departments != null && departments.size() > 0) {
			for (int i = departments.size() - 1; i > -1; i--) {
				tempTreeNode = findDepartmentNodeByDepartmentName(tempTreeNode,
						departments.get(i));

			}
		}
		return tempTreeNode;
	}
	
	public DefaultMutableTreeNode findDepartmentNode(DefaultMutableTreeNode tempTreeNode,int departmentId){
		MyFlag flag = new MyFlag();
		this.findChildNodeByDepartmentName(tempTreeNode, departmentId, flag);
		if(!flag.getValue()){
			return null;
		}else{
			tempTreeNode = flag.getNode2();
		}
		return tempTreeNode;
	}

	public TreeNodeInfoPanel removeMemberNode(String jid,
			List<Integer> departments) {

		DefaultTreeModel model = (DefaultTreeModel) this.getModel();
		DefaultMutableTreeNode tempTreeNode = (DefaultMutableTreeNode) model
				.getRoot();
//		if (departments != null && departments.size() > 0) {
//			for (int i = departments.size() - 1; i > -1; i--) {
//				tempTreeNode = findDepartmentNodeByDepartmentName(tempTreeNode,
//						departments.get(i));
//
//			}
//		}
		tempTreeNode = findDepartmentNodeByMemberInfo(tempTreeNode,departments);
//		tempTreeNode = findDepartmentNode(tempTreeNode,departments.get(0));
		DefaultMutableTreeNode leafNode = findMemberNodeByDepartmentName(
				tempTreeNode, jid);

		model.removeNodeFromParent(leafNode);
		// GroupChatCreateDialog.getInstance().getSelectedUserList()
		// .removeDepartmentTreeNodePanel(
		// (DepartmentTreeNodePanel) leafNode.getUserObject());
		this.updateUI();
		return (TreeNodeInfoPanel) leafNode.getUserObject();
	}

	public DefaultMutableTreeNode removeDepartmentNode(int departmentId) {

		DefaultTreeModel model = (DefaultTreeModel) this.getModel();
		DefaultMutableTreeNode tempTreeNode = (DefaultMutableTreeNode) model
				.getRoot();
		tempTreeNode = findDepartmentNode(tempTreeNode,departmentId);
//		MyFlag flag = new MyFlag();
//		findChildNodeByDepartmentName(tempTreeNode, departmentId, flag);
//		if (!flag.getValue()) {
//			tempTreeNode = null;
//		} else {
//			tempTreeNode = flag.getNode2();
//			model.removeNodeFromParent(tempTreeNode);
//		}
		if(tempTreeNode!=null){
			model.removeNodeFromParent(tempTreeNode);
		}
		this.updateUI();
		return tempTreeNode;
	}

	public TreeNodeInfoPanel reflashMemberNode(MemberBean bean, int idx) {
		List<Integer> departments = TreeUtil.getMemberDepartmentMap().get(
				bean.getLccAccount());
		DefaultTreeModel model = (DefaultTreeModel) this.getModel();
		DefaultMutableTreeNode tempTreeNode = (DefaultMutableTreeNode) model
				.getRoot();
//		if (departments != null && departments.size() > 0) {
//			for (int i = departments.size() - 1; i > -1; i--) {
//				tempTreeNode = findDepartmentNodeByDepartmentName(tempTreeNode,
//						departments.get(i));
//
//			}
//		}
		tempTreeNode = findDepartmentNodeByMemberInfo(tempTreeNode,departments);

		DefaultMutableTreeNode leafNode = findMemberNodeByDepartmentName(
				tempTreeNode, bean.getJid());

		if (leafNode == null) {
			return null;
		}
		javax.swing.tree.TreeNode[] ns = tempTreeNode.getPath();
		javax.swing.tree.TreePath path = new javax.swing.tree.TreePath(ns);
		boolean isExpend = this.isExpanded(path);
		model.removeNodeFromParent(leafNode);
		((TreeNodeInfoPanel) leafNode.getUserObject()).getInfo()
				.setMember(bean);
		model.insertNodeInto(leafNode, tempTreeNode, idx);
		if(isExpend){
			this.expandPath(path);
		}
		
//		this.updateUI();
		return (TreeNodeInfoPanel) leafNode.getUserObject();

	}

	public TreeNodeInfoPanel reflashDepartmentNode(DepartmentBean bean) {
		DefaultTreeModel model = (DefaultTreeModel) this.getModel();
		DefaultMutableTreeNode tempTreeNode = (DefaultMutableTreeNode) model
				.getRoot();
		
//		MyFlag flag = new MyFlag();
//		this.findChildNodeByDepartmentName(tempTreeNode, bean.getId(), flag);
//		
//		if (!flag.getValue()) {
//			return null;
//		}
//		tempTreeNode = flag.getNode2();
		tempTreeNode = findDepartmentNode(tempTreeNode,bean.getId());
		if(tempTreeNode == null){
			return null;
		}
		TreeNodeInfoPanel panel = (TreeNodeInfoPanel) tempTreeNode
				.getUserObject();
		panel.getInfo().setDepartment(bean);
		this.updateUI();
		return (TreeNodeInfoPanel) tempTreeNode.getUserObject();
	}

	public TreeNodeInfoPanel addMemberNode(MemberBean bean, int idx,
			TreeNodeInfo info) {
		try {
			TreeNodeInfoPanel panel = (TreeNodeInfoPanel) panelClass
					.newInstance();
			DefaultTreeModel model = (DefaultTreeModel) this.getModel();
			DefaultMutableTreeNode tempTreeNode = (DefaultMutableTreeNode) model
					.getRoot();
			List<Integer> departments = bean.getDepartments();
//			if (departments != null && departments.size() > 0) {
//				for (int i = departments.size() - 1; i > -1; i--) {
//					tempTreeNode = findDepartmentNodeByDepartmentName(
//							tempTreeNode, departments.get(i));
//
//				}
//			}
//			tempTreeNode = findDepartmentNodeByMemberInfo(tempTreeNode,departments);
			tempTreeNode = findDepartmentNode(tempTreeNode,bean.getDeptId());
			panel.setInfo(info);
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(panel);
			model.insertNodeInto(node, tempTreeNode, idx);
			this.updateUI();
			return panel;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public TreeNodeInfoPanel addDepartmentNode(DepartmentBean bean, int idx,
			TreeNodeInfo info) {
		try {
			TreeNodeInfoPanel panel = (TreeNodeInfoPanel) panelClass
					.newInstance();
			DefaultTreeModel model = (DefaultTreeModel) this.getModel();
			DefaultMutableTreeNode tempTreeNode = (DefaultMutableTreeNode) model
					.getRoot();
			List<Integer> departments = bean.getDepartments();
//			if (departments != null && departments.size() > 0) {
//				for (int i = departments.size() - 1; i < 0; i--) {
//					tempTreeNode = findDepartmentNodeByDepartmentName(
//							tempTreeNode, departments.get(i));
//				}
//			}
			tempTreeNode = findDepartmentNodeByMemberInfo(tempTreeNode,departments);
			panel.setInfo(info);
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(panel);
			model.insertNodeInto(node, tempTreeNode, idx);
			this.updateUI();
			return panel;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 查找下一层用户节点
	public DefaultMutableTreeNode findMemberNodeByDepartmentName(
			DefaultMutableTreeNode node, String jid) {
		if (node == null || node.isLeaf()) {
			return null;
		}
		Enumeration<DefaultMutableTreeNode> en = node.children();
		while (en.hasMoreElements()) {
			DefaultMutableTreeNode childNode = en.nextElement();
			TreeNodeInfoPanel panel = (TreeNodeInfoPanel) childNode
					.getUserObject();
			if (!panel.getInfo().isDepartmentFlag()
					&& panel.getInfo().getMember().getLccAccount().equals(jid.split("@")[0])) {
				return childNode;
			}
		}
		return null;
	}

	// 查找下一层部门节点
	public DefaultMutableTreeNode findDepartmentNodeByDepartmentName(
			DefaultMutableTreeNode node, int departmentId) {
		if (node == null || node.isLeaf()) {
			return null;
		}
		Enumeration<DefaultMutableTreeNode> en = node.children();
		while (en.hasMoreElements()) {
			DefaultMutableTreeNode childNode = en.nextElement();
			TreeNodeInfoPanel panel = (TreeNodeInfoPanel) childNode
					.getUserObject();
			if (panel.getInfo().isDepartmentFlag()
					&& panel.getInfo().getDepartment().getId() == departmentId) {
				return childNode;
			}
		}
		return null;
	}

	// 遍历所有节点
	public void findChildNodeByDepartmentName(DefaultMutableTreeNode node,
			int departmentId, MyFlag flag) {
		TreeNodeInfoPanel panel = (TreeNodeInfoPanel) node
				.getUserObject();
		if (panel.getInfo().isDepartmentFlag()
				&& panel.getInfo().getDepartment().getId() == departmentId) {
			flag.setValue(true);
			flag.setNode2(node);
			return;
		}
		Enumeration<DefaultMutableTreeNode> en = node.children();
		while (en.hasMoreElements()) {
			node = en.nextElement();
			findChildNodeByDepartmentName(node, departmentId, flag);
			if (flag.getValue()) {
				return;
			}
		}
	}

	class CheckRenderer implements TreeCellRenderer {

		@Override
		public Component getTreeCellRendererComponent(JTree tree, Object value,
				boolean sel, boolean expanded, boolean leaf, int row,
				boolean hasFocus) {

			DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
			DepartmentTreeNodePanel panel = (DepartmentTreeNodePanel) (node
					.getUserObject());

			panel.setBackground(tree.getBackground());
			panel.setForeground(tree.getForeground());
			panel.setEnabled(tree.isEnabled());
			panel.getCheckBox().setSelected(panel.isSelected());
			panel.setInfo(panel.getInfo());
			panel.repaint();
			panel.getjLabel4().repaint();
			
			// if(!nodeInfo.isDepartMent()){
			// nodeInfo.setFirstImgVisible(false);
			// nodeInfo.setSecondImgVisible(true);
			// }else{
			// nodeInfo.setFirstImgVisible(true);
			// nodeInfo.setSecondImgVisible(false);
			// }

			return panel;
		}

	}
	
	public DefaultMutableTreeNode updateMember(MemberBean bean, int[] paras)throws Exception{
		DefaultTreeModel treeModel = (DefaultTreeModel) this.getModel();
		DefaultMutableTreeNode node = memberMap.get(bean.getId());
		TreeNodeInfoPanel panel = (TreeNodeInfoPanel)node.getUserObject();
		if(paras[0] == paras[1]){
			treeModel.nodeChanged(node);
			updateParentDept(bean, treeModel);
		}else{//change dept
			bean.setDeptId(paras[0]);
			deleteMember(bean);
			bean.setDeptId(paras[1]);
			
			DefaultMutableTreeNode pNode = deptMap.get(bean.getDeptId());
			memberMap.put(panel.getInfo().getMember().getId(), node);
			DepartmentTreeNode temp = TreeUtil.getMemberNodeById(bean.getId());
			treeModel.insertNodeInto(node, pNode, temp.getIndex());
			updateParentDept(bean, treeModel);
		}
		this.repaint();
		return node;
	}
	
	public void addMember(DepartmentTreeNode node, MemberBean bean)throws Exception{
		DefaultTreeModel treeModel = (DefaultTreeModel) this.getModel();
		DefaultMutableTreeNode pNode = deptMap.get(bean.getDeptId());

		TreeNodeInfoPanel panel = (TreeNodeInfoPanel) panelClass.newInstance();
		panel.setInfo(node.getInfo());
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(panel);
		memberMap.put(panel.getInfo().getMember().getId(), newNode);
		
		treeModel.insertNodeInto(newNode, pNode, node.getIndex());
		updateParentDept(bean, treeModel);
		this.repaint();
	}
	
	public DefaultMutableTreeNode removeMember(MemberBean bean){
		return deleteMember(bean);
	}
	
	private DefaultMutableTreeNode deleteMember(MemberBean bean){
		DefaultTreeModel treeModel = (DefaultTreeModel) this.getModel();
		DefaultMutableTreeNode node = memberMap.get(bean.getId());
		treeModel.removeNodeFromParent(node);
		memberMap.remove(bean.getId());
		updateParentDept(bean, treeModel);
		this.repaint();
		return node;
	}
	
	private void updateParentDept(MemberBean bean, DefaultTreeModel treeModel){
		DepartmentBean dept = TreeUtil.getDetpNodeById(bean.getDeptId()).getInfo().getDepartment();
		String temp = null;
		if(!dept.getSearchStr().equals("root")){
			temp = dept.getSearchStr() + "@" + dept.getId();
		}else{
			temp = dept.getId() + "";
		}
		String[] parentIds =temp.split("@");
		for(String parentId : parentIds){
			treeModel.nodeChanged(deptMap.get(parentId));
		}
	}
	
	public void updateDept(DepartmentBean bean, String[] paras)throws Exception{
		DefaultTreeModel treeModel = (DefaultTreeModel) this.getModel();
		DefaultMutableTreeNode node = deptMap.get(bean.getId());
		TreeNodeInfoPanel panel = (TreeNodeInfoPanel)node.getUserObject();
//		System.out.println("paras[0]=" + paras[0] + "&paras[1]=" + paras[1]);
		if(paras[0].equals(paras[1])){//dept not change
			treeModel.nodeChanged(node);
		}else{
			DefaultMutableTreeNode newPNode = deptMap.get(bean.getParentId());
			DepartmentTreeNode treeNode = TreeUtil.getDetpNodeById(bean.getId());
			removeDept(bean.getId());
			treeModel.insertNodeInto(node, newPNode, treeNode.getIndex());
			deptMap.put(panel.getInfo().getDepartment().getId(), node);
			String[] temp = paras[0].split("@");
			String[] temp2 = paras[1].split("@");
			Set<String> pDeptIds = new HashSet<String>();
			pDeptIds.addAll(Arrays.asList(temp));
			pDeptIds.addAll(Arrays.asList(temp2));
			for(String pDeptId : pDeptIds){
				treeModel.nodeChanged(deptMap.get(pDeptId));
			}
		}
		this.repaint();
	}
	
	public void addDept(DepartmentTreeNode node, int parentId)throws Exception{
		DefaultTreeModel treeModel = (DefaultTreeModel) this.getModel();
		DefaultMutableTreeNode pNode = deptMap.get(parentId);

		TreeNodeInfoPanel panel = (TreeNodeInfoPanel) panelClass.newInstance();
		panel.setInfo(node.getInfo());
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(panel);
		deptMap.put(panel.getInfo().getDepartment().getId(), newNode);
		
		treeModel.insertNodeInto(newNode, pNode, node.getIndex());
		this.repaint();
	}
	
	public DefaultMutableTreeNode removeDept(int deptId){
		DefaultTreeModel treeModel = (DefaultTreeModel) this.getModel();
		DefaultMutableTreeNode node = deptMap.get(deptId);
		treeModel.removeNodeFromParent(node);
		deptMap.remove(deptId);
		this.repaint();
		return node;
	}
}
