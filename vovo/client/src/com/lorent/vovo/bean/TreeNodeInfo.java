package com.lorent.vovo.bean;

import java.text.CollationKey;
import java.text.Collator;

import javax.swing.ImageIcon;

import com.lorent.common.tree.DepartmentBean;
import com.lorent.common.tree.MemberBean;
import com.lorent.vovo.util.Constants;

public class TreeNodeInfo /*implements java.lang.Comparable*/{

	
	private boolean departmentFlag = false;
	private DepartmentBean department;
	private MemberBean member;
	
	
	
	
	public DepartmentBean getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentBean department) {
		this.department = department;
	}
	public MemberBean getMember() {
		return member;
	}
	public void setMember(MemberBean member) {
		this.member = member;
	}
	
	public boolean isDepartmentFlag() {
		return departmentFlag;
	}
	public void setDepartmentFlag(boolean departmentFlag) {
		this.departmentFlag = departmentFlag;
	}
	
//	@Override
//	public int hashCode(){
//		if(isDepartmentFlag()){
//			return department.getId();
//		}else{
//			return member.getId();
//		}
//	}
//	
//	public boolean equals(Object obj) {  
//	    if (obj instanceof TreeNodeInfo) {  
//	    	TreeNodeInfo info = (TreeNodeInfo)obj;
//	    	if(isDepartmentFlag()){
//				return department.getId()==info.getDepartment().getId();
//			}else{
//				return member.getId()==info.getMember().getId();
//			}
//	    }  
//	    return false;  
//    }
	
//	@Override
//	public int compareTo(Object o) {
//		TreeNodeInfo o1 = this;
//		TreeNodeInfo o2 = (TreeNodeInfo)o;
//		if(o1.isDepartmentFlag()){
//			if(!o2.isDepartmentFlag()){
//				return -1;
//			}
//		}
//		if(o2.isDepartmentFlag()){
//			if(!o1.isDepartmentFlag()){
//				return 1;
//			}
//		}
//		if(!o1.isDepartmentFlag()&&!o2.isDepartmentFlag()){
////			System.out.println("o1:" + o1.getMember().getRealName() + "," + o1.getMember().getState());
////			System.out.println("o2:" + o2.getMember().getRealName() + "," + o2.getMember().getState());
//			if(o1.getMember().getState()>Constants.STATUS_OFFLINE&&o2.getMember().getState()>Constants.STATUS_OFFLINE){
////				System.out.println("one =========" + charCompare(o1.getMember().getRealName(),o2.getMember().getRealName()));
//				return charCompare(o1.getMember().getRealName(),o2.getMember().getRealName());
//			}else if(o1.getMember().getState()>Constants.STATUS_OFFLINE&&o2.getMember().getState()<=Constants.STATUS_OFFLINE){
////				System.out.println("two =========" + -1);
//				return -1;
//			}else if(o1.getMember().getState()<=Constants.STATUS_OFFLINE&&o2.getMember().getState()>Constants.STATUS_OFFLINE){
////				System.out.println("three =========" + 1);
//				return 1;
//			}else if(o1.getMember().getState()<=Constants.STATUS_OFFLINE&&o2.getMember().getState()<=Constants.STATUS_OFFLINE){
////				System.out.println("four =========" + charCompare(o1.getMember().getRealName(),o2.getMember().getRealName()));
//				return charCompare(o1.getMember().getRealName(),o2.getMember().getRealName());
//			}
//		}else{
//			return charCompare(o1.getDepartment().getName(),o2.getDepartment().getName());
//		}
//		
//		return 0;
//	}
//	
//	Collator collator = Collator.getInstance();
//	
//	public int charCompare(String s1,String s2){
//		CollationKey key1 = collator.getCollationKey(s1.toLowerCase());
//	    CollationKey key2 = collator.getCollationKey(s2.toLowerCase());
//	    return key1.compareTo(key2);
//	}
	
}
