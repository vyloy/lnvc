package com.lorent.vovo.bean;

import java.text.CollationKey;
import java.text.Collator;

import com.lorent.vovo.ui.DepartmentTreeNodePanel;
import com.lorent.vovo.util.Constants;

public class DepartmentTreeNodeComparator implements java.util.Comparator<TreeNodeInfo> {
	Collator collator = Collator.getInstance();
	@Override
	public int compare(TreeNodeInfo o1,
			TreeNodeInfo o2) {
		if(o1.isDepartmentFlag()){
			if(!o2.isDepartmentFlag()){
				return -1;
			}
		}
		if(o2.isDepartmentFlag()){
			if(!o1.isDepartmentFlag()){
				return 1;
			}
		}
		if(!o1.isDepartmentFlag()&&!o2.isDepartmentFlag()){
//			System.out.println("o1:" + o1.getMember().getRealName() + "," + o1.getMember().getState());
//			System.out.println("o2:" + o2.getMember().getRealName() + "," + o2.getMember().getState());
			if(o1.getMember().getState()>Constants.STATUS_OFFLINE&&o2.getMember().getState()>Constants.STATUS_OFFLINE){
//				System.out.println("one =========" + charCompare(o1.getMember().getRealName(),o2.getMember().getRealName()));
				int r = charCompare(o1.getMember().getRealName(),o2.getMember().getRealName());
				if(r == 0){
					return charCompare(o1.getMember().getLccAccount(),o2.getMember().getLccAccount());
				}else{
					return r;
				}
			}else if(o1.getMember().getState()>Constants.STATUS_OFFLINE&&o2.getMember().getState()<=Constants.STATUS_OFFLINE){
//				System.out.println("two =========" + -1);
				return -1;
			}else if(o1.getMember().getState()<=Constants.STATUS_OFFLINE&&o2.getMember().getState()>Constants.STATUS_OFFLINE){
//				System.out.println("three =========" + 1);
				return 1;
			}else if(o1.getMember().getState()<=Constants.STATUS_OFFLINE&&o2.getMember().getState()<=Constants.STATUS_OFFLINE){
//				System.out.println("four =========" + charCompare(o1.getMember().getRealName(),o2.getMember().getRealName()));
//				return charCompare(o1.getMember().getRealName(),o2.getMember().getRealName());
				int r = charCompare(o1.getMember().getRealName(),o2.getMember().getRealName());
				if(r == 0){
					return charCompare(o1.getMember().getLccAccount(),o2.getMember().getLccAccount());
				}else{
					return r;
				}
			}
		}else{
			int r = charCompare(o1.getDepartment().getName(),o2.getDepartment().getName());
			if(r == 0){
				charCompare(String.valueOf(o1.getDepartment().getId()),String.valueOf(o2.getDepartment().getId()));
			}
			return charCompare(o1.getDepartment().getName(),o2.getDepartment().getName());
		}
		
		return 0;
	}
	
	public int charCompare(String s1,String s2){
		CollationKey key1 = collator.getCollationKey(s1.toLowerCase());
	    CollationKey key2 = collator.getCollationKey(s2.toLowerCase());
	    return key1.compareTo(key2);
	}
}
