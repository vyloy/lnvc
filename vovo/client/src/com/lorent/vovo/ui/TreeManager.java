package com.lorent.vovo.ui;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.lorent.common.tree.DepartmentBean;
import com.lorent.common.tree.MemberBean;
import com.lorent.vovo.bean.DepartmentTreeNode;
import com.lorent.vovo.util.TreeUtil;

public class TreeManager {

	
	private static Logger log = Logger.getLogger(TreeManager.class);
	public static Map<Class,DepartmentTreeUI> map = new ConcurrentHashMap<Class,DepartmentTreeUI>();
	
	public static DepartmentTreeUI removeTree(Class key){
		return map.remove(key);
	}
	
	public static DepartmentTreeUI createNewTree(Class clazz,Class<? extends DepartmentTreeUI> departmentTreeUI,Class<? extends TreeNodeInfoPanel> pc){
		DepartmentTreeUI ui = null;
		try {
			Constructor ct = departmentTreeUI.getConstructor(Class.class);
			ui = (DepartmentTreeUI)ct.newInstance(pc);
			map.put(clazz, ui);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ui;
	}
	
	public static DepartmentTreeUI createTree(Class clazz,Class<? extends DepartmentTreeUI> departmentTreeUI,Class<? extends TreeNodeInfoPanel> pc){
		DepartmentTreeUI ui = map.get(clazz);
		if(ui==null){
			
			try {
				Constructor ct = departmentTreeUI.getConstructor(Class.class);
				ui = (DepartmentTreeUI)ct.newInstance(pc);
				map.put(clazz, ui);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ui;
	} 
	
	
	
	public static void removeMemberNode(String lccaccount){
		List<Integer> departments = TreeUtil.removeMemberNode(lccaccount);
		Set<Map.Entry<Class,DepartmentTreeUI>> set = map.entrySet();
		for(Map.Entry<Class,DepartmentTreeUI> entry:set){
			Class clazz = entry.getKey();
			DepartmentTreeUI ui = entry.getValue();
			ui.removeMemberNode(lccaccount,departments);
		}
//		GroupChatCreateDialog.getInstance().getTree().removeMemberNode(jid,departments);
	}
	
	
	public static void removeDepartmentNode(int departmentId){
		TreeUtil.removeDepartmentNode(departmentId);
//		GroupChatCreateDialog.getInstance().getTree().removeDepartmentNode(departmentId);
		Set<Map.Entry<Class,DepartmentTreeUI>> set = map.entrySet();
		for(Map.Entry<Class,DepartmentTreeUI> entry:set){
			Class clazz = entry.getKey();
			DepartmentTreeUI ui = entry.getValue();
			ui.removeDepartmentNode(departmentId);
		}
	}
	
	public static void reflashMemberNode(MemberBean bean){
		int idx = TreeUtil.reflashMemberNode(bean);
		log.info("reflashMemberNode   idx ===" + idx);
//		GroupChatCreateDialog.getInstance().getTree().reflashMemberNode(bean, idx);
		Set<Map.Entry<Class,DepartmentTreeUI>> set = map.entrySet();
		for(Map.Entry<Class,DepartmentTreeUI> entry:set){
			Class clazz = entry.getKey();
			DepartmentTreeUI ui = entry.getValue();
			ui.reflashMemberNode(bean, idx);
		}
	}
	
	public static synchronized void reflashMemberNodeByStatus(String lccno,int status) throws Exception{
		MemberBean bean = TreeUtil.getMemberBeanByLccno(lccno);
		TreeUtil.updateDeptNodeWhenStatusChange(status, bean.getState(), bean.getDeptId());
		MemberBean newBean = new MemberBean();
		newBean.copy(bean);
		newBean.setState(status);
		reflashMemberNode(newBean);
//		TreeUtil.updateUserNode(bean, true, status);
	}
	
	public static void reflashDepartmentNode(DepartmentBean bean){
		TreeUtil.reflashDepartmentNode(bean);
//		GroupChatCreateDialog.getInstance().getTree().reflashDepartmentNode(bean);
		Set<Map.Entry<Class,DepartmentTreeUI>> set = map.entrySet();
		for(Map.Entry<Class,DepartmentTreeUI> entry:set){
			Class clazz = entry.getKey();
			DepartmentTreeUI ui = entry.getValue();
			ui.reflashDepartmentNode(bean);
		}
	}
	
	public static void addMemberNode(MemberBean bean){
		DepartmentTreeNode dcNode = TreeUtil.addMemberNode(bean);
//		GroupChatCreateDialog.getInstance().getTree().addMemberNode(bean, dcNode.getIndex(), dcNode.getInfo());
		Set<Map.Entry<Class,DepartmentTreeUI>> set = map.entrySet();
		for(Map.Entry<Class,DepartmentTreeUI> entry:set){
			Class clazz = entry.getKey();
			DepartmentTreeUI ui = entry.getValue();
			ui.addMemberNode(bean, dcNode.getIndex(), dcNode.getInfo());
		}
	}
	
	public static void addDeparmentNode(DepartmentBean bean){
		DepartmentTreeNode dcNode = TreeUtil.addDepartmentNode(bean);
//		GroupChatCreateDialog.getInstance().getTree().addDepartmentNode(bean, dcNode.getIndex(), dcNode.getInfo());
		Set<Map.Entry<Class,DepartmentTreeUI>> set = map.entrySet();
		for(Map.Entry<Class,DepartmentTreeUI> entry:set){
			Class clazz = entry.getKey();
			DepartmentTreeUI ui = entry.getValue();
			ui.addDepartmentNode(bean, dcNode.getIndex(), dcNode.getInfo());
		}
	}
	
	public static void addMember(MemberBean bean)throws Exception{
		DepartmentTreeNode node = TreeUtil.addUserNode(bean);
		for(DepartmentTreeUI ui: map.values()){
			ui.addMember(node, bean);
		}
	}
	
	public static void updateMember(MemberBean bean, boolean changeStatus, int newStatus)throws Exception{
		int[] paras = TreeUtil.updateUserNode(bean, changeStatus, newStatus);
		for(DepartmentTreeUI ui: map.values()){
			ui.updateMember(bean, paras);
		}
	}
	
	public static void removeMember(MemberBean bean)throws Exception{
		TreeUtil.deleteUserNode(bean);
		for(DepartmentTreeUI ui: map.values()){
			ui.removeMember(bean);
		}
	}
	
	public static void updateDept(DepartmentBean bean)throws Exception{
		String[] strs = TreeUtil.updateDeptNode(bean);
		for(DepartmentTreeUI ui: map.values()){
			ui.updateDept(bean, strs);
		}
	}
	
	public static void addDept(DepartmentBean bean) throws Exception{
		DepartmentTreeNode node = TreeUtil.addDeptNode(bean);
		for(DepartmentTreeUI ui: map.values()){
			ui.addDept(node, bean.getParentId());
		}
	}
	
	public static void removeDept(int deptId) throws Exception{
		TreeUtil.deleteDeptNode(deptId);
		for(DepartmentTreeUI ui: map.values()){
			ui.removeDept(deptId);
		}
	} 
}
