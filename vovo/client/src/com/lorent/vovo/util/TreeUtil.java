package com.lorent.vovo.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.lorent.common.tree.DepartmentBean;
import com.lorent.common.tree.MemberBean;
import com.lorent.common.tree.OrgNode;
import com.lorent.common.tree.OrgTree;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.bean.DepartmentTreeNode;
import com.lorent.vovo.bean.MyFlag;
import com.lorent.vovo.bean.TreeNodeInfo;
import com.lorent.vovo.dto.DeptStatDto;

public class TreeUtil {

	private static Logger log = Logger.getLogger(TreeUtil.class);
	public static DepartmentTreeNode rootNode;
	private static Map<String, List<Integer>> memberDepartmentMap = new ConcurrentHashMap<String, List<Integer>>();
	private static Map<Integer, DepartmentTreeNode> memberMap = new HashMap<Integer, DepartmentTreeNode>();
	private static Map<Integer, DepartmentTreeNode> deptMap = new HashMap<Integer, DepartmentTreeNode>();
	private static boolean dataIsReady;
	
	public static boolean isDataReady(){
		return dataIsReady;
	}
	
	public static void removeMemberNode() {

	}

	public static void removeJidFromMemberDepartmentMap(String jid) {
		getMemberDepartmentMap().remove(jid);
	}

	public static int getNodeindex(DepartmentTreeNode parent,
			DepartmentTreeNode child) {
//		System.out.println(" add before");
//		print(parent);
		parent.addChildNode(child);
//		System.out.println(" add after");
//		print(parent);
		int idx = -1;
		java.util.Collection<DepartmentTreeNode> col = parent.getChildren()
				.values();
		for (DepartmentTreeNode n : col) {
			idx++;
			if (n==child) {
				break;
			}
		}
		return idx;
	}

	public static void removeMemberDepartmentMap(int departmentId) {
		Set<Map.Entry<String, List<Integer>>> set = TreeUtil
				.getMemberDepartmentMap().entrySet();
		for (Map.Entry<String, List<Integer>> entry : set) {
			String key = entry.getKey();
			List<Integer> list = entry.getValue();
			if (list.contains(departmentId)) {
				getMemberDepartmentMap().remove(key);
			}
		}
	}

	// 查找部门节点，在当前节点下遍历子节点,只遍历一层
	public static DepartmentTreeNode findDepartmentNodeByDepartmentId(
			DepartmentTreeNode node, int departmentId) {
		if (node != null && node.getInfo().isDepartmentFlag()
				&& node.getInfo().getDepartment().getId() == departmentId) {
			return node;
		}
		TreeMap<TreeNodeInfo, DepartmentTreeNode> map = node.getChildren();
		Collection<DepartmentTreeNode> col = map.values();
		for (DepartmentTreeNode child : col) {
			if (child!=null && child.getInfo().isDepartmentFlag()
					&& child.getInfo().getDepartment().getId() == departmentId) {
				return child;
			}
		}
		return null;
	}
	
	//根据用户JID，查找用户节点
	public static DepartmentTreeNode findMemberNodeByJid(String jid){
		DepartmentTreeNode dNode = TreeUtil.getRootDepartmentTreeNode();
		List<Integer> departments = TreeUtil.getMemberDepartmentMap().get(jid.split("@")[0]);
		dNode = findDepartmentNodeByMemberInfo(dNode,departments);
		DepartmentTreeNode node = findMemberNodeByDepartmentId(dNode,jid);
		return node;
	}

	// 查找用户节点，在当前节点下遍历子节点,只遍历一层
	public static DepartmentTreeNode findMemberNodeByDepartmentId(
			DepartmentTreeNode node, String jid) {
		TreeMap<TreeNodeInfo, DepartmentTreeNode> map = node.getChildren();
		Collection<DepartmentTreeNode> col = map.values();
		for (DepartmentTreeNode child : col) {
			if (child!=null && !child.getInfo().isDepartmentFlag()
					&& jid.split("@")[0].equals(child.getInfo().getMember().getLccAccount())) {
				return child;
			}
		}
		return null;
	}

	// 给定某个部门ID，遍历整个树结构
	public static void findDepartmentNodeByDepartmentId(
			DepartmentTreeNode node, int departmentId, MyFlag flag) {
		if (node != null && node.getInfo().isDepartmentFlag()
				&& node.getInfo().getDepartment().getId() == departmentId) {
			flag.setValue(true);
			flag.setNode1(node);
			return;
		}
		TreeMap<TreeNodeInfo, DepartmentTreeNode> map = node.getChildren();
		Collection<DepartmentTreeNode> col = map.values();
		for (DepartmentTreeNode child : col) {
			node = child;
			findDepartmentNodeByDepartmentId(node, departmentId, flag);
			if (flag.getValue()) {
				return;
			}
		}
	}

	private static void removeDepartment(int departmentId) {
		MyFlag flag = new MyFlag();
		DepartmentTreeNode node = TreeUtil.getRootDepartmentTreeNode();
		findDepartmentNodeByDepartmentId(node, departmentId, flag);
		if (flag.getValue()) {
			removeNode(flag.getNode1());
		}
	}
	
	
	public static void print(DepartmentTreeNode departmentNode){
		TreeMap<TreeNodeInfo,DepartmentTreeNode> map = departmentNode.getChildren();
		java.util.Collection<DepartmentTreeNode> col = map.values();
		for(DepartmentTreeNode node:col){
			
			if(node!=null && node.getInfo().isDepartmentFlag()){
				System.out.println("(o)" + node.getInfo().getDepartment().getName());
			}else{
				if(node==null){
					System.out.println("node is null");
				}else{
					System.out.println("(m)" + node.getInfo().getMember().getRealName());
				}
			}
		}
	}

	public static DepartmentTreeNode removeMemberNode(
			DepartmentTreeNode departmentNode, String jid) {
//		System.out.println("del before");
//		print(departmentNode);
		DepartmentTreeNode node = findMemberNodeByDepartmentId(departmentNode,
				jid);
		removeNode(node);
//		System.out.println("del after");
//		print(departmentNode);
		return node;
	}

	public static void removeNode(DepartmentTreeNode node) {
		if (node != null) {
//			node.getParent().getChildren().remove(node.getInfo());
//			node.setParent(null);
			node.getParent().removeChildNode(node);
		}
	}

	public static DepartmentTreeNode getRootDepartmentTreeNode() {
		if (rootNode == null) {
			convertTreeNode();
		}
		return rootNode;
	}

	public static Map<String, List<Integer>> getMemberDepartmentMap() {
		return memberDepartmentMap;
	}

	private static OrgTree<DepartmentBean, MemberBean> initData() {
		OrgTree<DepartmentBean, MemberBean> orgTree = new OrgTree<DepartmentBean, MemberBean>();
		OrgNode<DepartmentBean, MemberBean> orgNode = orgTree.getRoot();
		orgNode.setOrganization(new DepartmentBean(1, "络威", 10, 4));
		OrgNode<DepartmentBean, MemberBean> o1 = new OrgNode<DepartmentBean, MemberBean>();
		o1.setOrganization(new DepartmentBean(2, "研发", 4, 2));
		orgNode.addChild(o1);
		OrgNode<DepartmentBean, MemberBean> o2 = new OrgNode<DepartmentBean, MemberBean>();
		o2.setOrganization(new DepartmentBean(3, "市场", 6, 2));
		orgNode.addChild(o2);
		// int id,String jid,String desc,int state,String post,String
		// lccAccount,String defaultImg,ImageIcon icon,String realName
		OrgNode<DepartmentBean, MemberBean> o11 = new OrgNode<DepartmentBean, MemberBean>();
		o11.setMember(new MemberBean(1, "dd", "dd", 1, null, "dd", null, null,
				"zhangsan"));
		o1.addChild(o11);
		OrgNode<DepartmentBean, MemberBean> o12 = new OrgNode<DepartmentBean, MemberBean>();
		o12.setMember(new MemberBean(2, "aa", "aa", -1, null, "aa", null, null,
				"lisi"));
		o1.addChild(o12);
		OrgNode<DepartmentBean, MemberBean> o21 = new OrgNode<DepartmentBean, MemberBean>();
		o21.setMember(new MemberBean(3, "ee", "ee", -1, null, "ee", null, null,
				"wangwu"));
		o2.addChild(o21);
		OrgNode<DepartmentBean, MemberBean> o22 = new OrgNode<DepartmentBean, MemberBean>();
		o22.setMember(new MemberBean(4, "cc", "cc", 1, null, "cc", null, null,
				"maliu"));
		o2.addChild(o22);
		return orgTree;
	}

	// 将OrgTree转换成DepartmentTreeNode
	private static void convertTreeNode() {
		OrgTree<DepartmentBean, MemberBean> orgTree = null;
		try {
			orgTree = Vovo.getLcmUtil().getOrgTree();
		} catch (Exception e) {
			e.printStackTrace();
		}//initData();
		// 设置初始值
		OrgNode<DepartmentBean, MemberBean> orgNode = orgTree.getRoot();

		rootNode = new DepartmentTreeNode();

		DeptStatDto dto = new DeptStatDto();
		setDepartmentTreeNode(rootNode, orgNode, dto);
		dataIsReady = true;
		Vovo.sendMessage("dataIsReady", new Object[]{});
	}

	private static void setMemberDepartmentMap(DepartmentTreeNode node) {
		if (node.getInfo().isDepartmentFlag()) {
			return;
		} else {
			List<Integer> list = new ArrayList<Integer>();
			setDepartmentNameList(node, list);
			memberDepartmentMap.put(node.getInfo().getMember().getLccAccount(), list);
		}
	}

	private static void setDepartmentNameList(DepartmentTreeNode node,
			List<Integer> list) {

		if (node.getParent().getParent() == null) {
			return;
		}
		list.add(node.getParent().getInfo().getDepartment().getId());
		setDepartmentNameList(node.getParent(), list);
	}

	// 将服务器发送组织结构树转成客户端组织结构树
	private static void setDepartmentTreeNode(DepartmentTreeNode dNode,
			OrgNode<DepartmentBean, MemberBean> oNode, DeptStatDto dto) {
		if (oNode.getMember() == null) {
			deptMap.put(oNode.getOrganization().getId(), dNode);
			dNode.getInfo().setDepartmentFlag(true);
			dNode.getInfo().setDepartment(oNode.getOrganization());
			List<OrgNode<DepartmentBean, MemberBean>> coNodes = oNode.getChildNodes();
			DeptStatDto childDto = new DeptStatDto();
			for (OrgNode<DepartmentBean, MemberBean> coNode : coNodes) {
				DepartmentTreeNode cdNode = new DepartmentTreeNode();
				cdNode.setParent(dNode);
				setDepartmentTreeNode(cdNode, coNode, childDto);
				dNode.addChildNode(cdNode);
			}
			dNode.getInfo().getDepartment().setTotal(childDto.total);
			dNode.getInfo().getDepartment().setOnlineNum(childDto.online);
			dto.total = dto.total + childDto.total;
			dto.online = dto.online + childDto.online;
		} else {
			memberMap.put(oNode.getMember().getId(), dNode);
			dto.total = dto.total + 1;
			dNode.getInfo().setDepartmentFlag(false);
			try {
				int state = MyOpenfireUtil.getUserState(oNode.getMember().getJid());
				if(state!=0){
					dto.online = dto.online + 1;
				}
				oNode.getMember().setState(state);
			} catch (Exception e) {
				e.printStackTrace();
			}
			dNode.getInfo().setMember(oNode.getMember());
			setMemberDepartmentMap(dNode);
		}
	}
	
	public static DepartmentTreeNode findDepartmentNodeByMemberInfo(DepartmentTreeNode dNode,List<Integer> departments){
		if (departments != null && departments.size() > 0) {
			for (int i = departments.size() - 1; i > -1; i--) {
				dNode = TreeUtil.findDepartmentNodeByDepartmentId(dNode,
						departments.get(i));
			}
		}
		return dNode;
	}

	public static List<Integer> removeMemberNode(String jid) {
		DepartmentTreeNode dNode = TreeUtil.getRootDepartmentTreeNode();
		List<Integer> departments = TreeUtil.getMemberDepartmentMap().get(jid.split("@")[0]);
//		if (departments != null && departments.size() > 0) {
//			for (int i = departments.size() - 1; i > -1; i--) {
//				dNode = TreeUtil.findDepartmentNodeByDepartmentId(dNode,
//						departments.get(i));
//			}
//		}
		dNode = findDepartmentNodeByMemberInfo(dNode,departments);
		TreeUtil.removeJidFromMemberDepartmentMap(jid);
		TreeUtil.removeMemberNode(dNode, jid);
		return departments;
	}

	public static void removeDepartmentNode(int departmentId) {
		TreeUtil.removeMemberDepartmentMap(departmentId);
		TreeUtil.removeDepartment(departmentId);
	}

	public static int reflashMemberNode(MemberBean bean) {
		List<Integer> departments = TreeUtil.getMemberDepartmentMap().get(
				bean.getLccAccount());
		DepartmentTreeNode dNode = TreeUtil.getRootDepartmentTreeNode();
//		if (departments != null && departments.size() > 0) {
//			for (int i = departments.size() - 1; i > -1; i--) {
//				dNode = TreeUtil.findDepartmentNodeByDepartmentId(dNode,
//						departments.get(i));
//			}
//		}
		dNode = findDepartmentNodeByMemberInfo(dNode,departments);
		DepartmentTreeNode dlNode = TreeUtil.removeMemberNode(dNode, bean
				.getJid());
		dlNode.getInfo().setMember(bean);
//		dNode.addChildNode(dlNode);
		int idx = TreeUtil.getNodeindex(dNode, dlNode);
		return idx;
	}
	
	
	public static DepartmentTreeNode findDepartmentNode(DepartmentTreeNode dNode,int departmentId){
		MyFlag flag = new MyFlag();
		TreeUtil.findDepartmentNodeByDepartmentId(dNode, departmentId, flag);
		if (flag.getValue()) {
			dNode = flag.getNode1();
		}
		return dNode;
	}

	public static void reflashDepartmentNode(DepartmentBean bean) {
		DepartmentTreeNode dNode = TreeUtil.getRootDepartmentTreeNode();
//		MyFlag flag = new MyFlag();
//		TreeUtil.findDepartmentNodeByDepartmentId(dNode, bean.getId(), flag);
//		if (flag.getValue()) {
//			flag.getNode1().getInfo().setDepartment(bean);
//		}
		dNode = findDepartmentNode(dNode,bean.getId());
		dNode.getInfo().setDepartment(bean);
	}

	public static DepartmentTreeNode addMemberNode(MemberBean bean) {
		DepartmentTreeNode dNode = TreeUtil.getRootDepartmentTreeNode();
//		List<Integer> departments = bean.getDepartments();
//		if (departments != null && departments.size() > 0) {
//			for (int i = departments.size() - 1; i > -1; i--) {
//				dNode = TreeUtil.findDepartmentNodeByDepartmentId(dNode,
//						departments.get(i));
//			}
//		}
		/*MyFlag flag = new MyFlag();
		TreeUtil.findDepartmentNodeByDepartmentId(dNode, bean.getDeptId(), flag);
		if(flag.getValue()){
			dNode = flag.getNode1();
		}*/
		dNode = findDepartmentNode(dNode,bean.getDeptId());
		DepartmentTreeNode dcNode = new DepartmentTreeNode();
		dcNode.getInfo().setDepartmentFlag(false);
		dcNode.getInfo().setMember(bean);
//		dNode.addChildNode(dcNode);
		int idx = TreeUtil.getNodeindex(dNode, dcNode);
		dcNode.setIndex(idx);
		TreeUtil.getMemberDepartmentMap().put(bean.getJid(),
				bean.getDepartments());
		return dcNode;
	}

	public static DepartmentTreeNode addDepartmentNode(DepartmentBean bean) {
		List<Integer> departments = bean.getDepartments();
		DepartmentTreeNode dNode = TreeUtil.getRootDepartmentTreeNode();
//		if (departments != null && departments.size() > 0) {
//			for (int i = departments.size() - 1; i > -1; i--) {
//				dNode = TreeUtil.findDepartmentNodeByDepartmentId(dNode,
//						departments.get(i));
//			}
//		}
		dNode = findDepartmentNodeByMemberInfo(dNode,departments);
		DepartmentTreeNode dcNode = new DepartmentTreeNode();
		dcNode.getInfo().setDepartmentFlag(true);
		dcNode.getInfo().setDepartment(bean);
//		dNode.addChildNode(dcNode);
		int idx = TreeUtil.getNodeindex(dNode, dcNode);
		dcNode.setIndex(idx);
		return dcNode;
	}
	
	public static DepartmentTreeNode addDeptNode(DepartmentBean bean) {
		DepartmentTreeNode pNode = deptMap.get(bean.getParentId());
		DepartmentTreeNode dcNode = new DepartmentTreeNode();
		dcNode.getInfo().setDepartmentFlag(true);
		dcNode.getInfo().setDepartment(bean);
		int idx = TreeUtil.getNodeindex(pNode, dcNode);
		dcNode.setIndex(idx);
		deptMap.put(bean.getId(), dcNode);
		pNode.addChildNode(dcNode);
		return dcNode;
	}
	
	public static void deleteDeptNode(int deptId){
		DepartmentTreeNode node = deptMap.get(deptId);
		node.getParent().getChildren().remove(node.getInfo());
		node.setParent(null);
		deptMap.remove(deptId);
	}
	
	public static String[] updateDeptNode(DepartmentBean bean){
		DepartmentTreeNode node = deptMap.get(bean.getId());
		DepartmentBean oldBean = node.getInfo().getDepartment();
		String[] strs = new String[2];
		strs[0] = oldBean.getSearchStr();
		strs[1] = bean.getSearchStr();
		if(oldBean.getParentId().equals(bean.getParentId())){//没有更改部门
			oldBean.setName(bean.getName());//更改名字
		}else{
			bean.setOnlineNum(oldBean.getOnlineNum());
			bean.setTotal(oldBean.getTotal());
			updateParentNodeStatData(false, oldBean);//更新父级统计信息
			deleteDeptNode(oldBean.getId());
			DepartmentTreeNode dcNode = addDeptNode(bean);
			updateParentNodeStatData(true, bean);
			dcNode.setChildren(node.getChildren());
			updateMemberDepartmentMap(dcNode);
		}
		return strs;
	}
	
	public static void updateMemberDepartmentMap(DepartmentTreeNode dcNode){
		if(dcNode!=null && dcNode.getChildren()!= null && dcNode.getChildren().size()>0){
			Set<TreeNodeInfo> set = dcNode.getChildren().keySet();
			for(TreeNodeInfo info : set){
				if(info.isDepartmentFlag()){
					updateMemberDepartmentMap(dcNode.getChildren().get(info));
				}else{
					setMemberDepartmentMap(dcNode.getChildren().get(info));
				}
			}
		}
	}
	
	private static void updateParentNodeStatData(boolean add, DepartmentBean bean){
		int total = bean.getTotal();
		int online = bean.getOnlineNum();
		String[] parentIds = bean.getSearchStr().split("@");
		for(String parentId : parentIds){
			DepartmentTreeNode parentNode = deptMap.get(Integer.parseInt(parentId));
			DepartmentBean parentBean = parentNode.getInfo().getDepartment();
			if(add){
				parentBean.setTotal(parentBean.getTotal() + total);
				parentBean.setOnlineNum(parentBean.getOnlineNum() + online);
			}else{
				parentBean.setTotal(parentBean.getTotal() - total);
				parentBean.setOnlineNum(parentBean.getOnlineNum() - online);
			}
		}
	}
	
	private static void updateDeptNodeStatData(boolean add, int deptId, int status)throws Exception{
		int total = 1;
		int online = status != Constants.STATUS_OFFLINE? 1: 0;
		updateDeptNode(add, deptId, total, online);
	}

	private static void updateDeptNode(boolean add, int deptId, int total,
			int online) {
		DepartmentBean dept = deptMap.get(deptId).getInfo().getDepartment();
		String temp = null;
		if(!dept.getSearchStr().equals("root")){
			temp = dept.getSearchStr() + "@" + dept.getId();
		}else{
			temp = dept.getId() + "";
		}
		String[] parentIds =temp.split("@");
		for(String parentId : parentIds){
			DepartmentTreeNode parentNode = deptMap.get(Integer.parseInt(parentId));
			DepartmentBean parentBean = parentNode.getInfo().getDepartment();
			if(add){
				parentBean.setTotal(parentBean.getTotal() + total);
				parentBean.setOnlineNum(parentBean.getOnlineNum() + online);
			}else{
				parentBean.setTotal(parentBean.getTotal() - total);
				parentBean.setOnlineNum(parentBean.getOnlineNum() - online);
			}
		}
	}
	
	public static void updateDeptNodeWhenStatusChange(int newStatus, int oldStatus, int deptId){
		boolean addflag = false;
		if(oldStatus == Constants.STATUS_OFFLINE && newStatus > Constants.STATUS_OFFLINE){
			addflag = true;
		}else if(newStatus == Constants.STATUS_OFFLINE && oldStatus > Constants.STATUS_OFFLINE){
			addflag = false;
		}else{
			return;
		}
		updateDeptNode(addflag, deptId, 0, 1);
	}
	
	public static DepartmentTreeNode getDetpNodeById(int deptId){
		DepartmentTreeNode node = deptMap.get(deptId);
		return node;
	}
	
	public static DepartmentTreeNode getMemberNodeById(int memberId){
		DepartmentTreeNode node = memberMap.get(memberId);
		return node;
	}
	
	public static DepartmentTreeNode addUserNode(MemberBean bean)throws Exception{
		DepartmentTreeNode pNode = deptMap.get(bean.getDeptId());
		DepartmentTreeNode dcNode = new DepartmentTreeNode();
		dcNode.getInfo().setDepartmentFlag(false);
		dcNode.getInfo().setMember(bean);
		int idx = TreeUtil.getNodeindex(pNode, dcNode);
		dcNode.setIndex(idx);
		memberMap.put(bean.getId(), dcNode);
		updateDeptNodeStatData(true, bean.getDeptId(), bean.getState());
		setMemberDepartmentMap(dcNode);
		return dcNode;
	}
	
	public static void deleteUserNode(MemberBean bean)throws Exception{
		DepartmentTreeNode pnode = deptMap.get(bean.getDeptId());
		DepartmentTreeNode node = memberMap.get(bean.getId());
		pnode.getChildren().remove(node.getInfo());
		node.setParent(null);
		memberMap.remove(bean.getId());
		memberDepartmentMap.remove(bean.getLccAccount());
		updateDeptNodeStatData(false, bean.getDeptId(), node.getInfo().getMember().getState());
	}
	
	public static int[] updateUserNode(MemberBean bean, boolean changeStatus, int newStatus)throws Exception{
		DepartmentTreeNode node = memberMap.get(bean.getId());
		MemberBean oldBean = node.getInfo().getMember();
		int[] ret = new int[2];
		ret[0] = oldBean.getDeptId();
		ret[1] = bean.getDeptId();
		if(!changeStatus){
			bean.setState(oldBean.getState());
		}
		if(oldBean.getDeptId().equals(bean.getDeptId())){//没有更改部门
			node.getInfo().setMember(bean);
			if(changeStatus){
				updateDeptNodeWhenStatusChange(newStatus, oldBean.getState(), bean.getDeptId());
				bean.setState(newStatus);
			}
		}else{
			deleteUserNode(oldBean);
			addUserNode(bean);
		}
		return ret;
	}
	
	public static MemberBean getMemberBeanByLccno(String lccno){
		for(DepartmentTreeNode node : memberMap.values()){
			TreeNodeInfo info = node.getInfo();
			if(info.getMember().getLccAccount().equals(lccno)){
				return info.getMember();
			}
		}
		return null;
	}
	
	public static List<MemberBean> getMemberBeans(){
		ArrayList<MemberBean> resultList = new ArrayList<MemberBean>();
		for(DepartmentTreeNode node : memberMap.values()){
			TreeNodeInfo info = node.getInfo();
			resultList.add(info.getMember());
		}
		return resultList;
	}
}
