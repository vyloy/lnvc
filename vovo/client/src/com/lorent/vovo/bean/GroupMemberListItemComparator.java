package com.lorent.vovo.bean;

import java.text.CollationKey;
import java.text.Collator;

import com.lorent.vovo.ui.GroupMemberListItem;
import com.lorent.vovo.util.Constants;

public class GroupMemberListItemComparator implements java.util.Comparator<GroupMemberListItem>{

	Collator collator = Collator.getInstance();
	
	@Override
	public int compare(GroupMemberListItem o1, GroupMemberListItem o2) {
		if(o1.getMemberBean().getState()>Constants.STATUS_OFFLINE&&o2.getMemberBean().getState()>Constants.STATUS_OFFLINE){
//			return charCompare(o1.getMemberBean().getRealName(),o2.getMemberBean().getRealName());
			int r = charCompare(o1.getMemberBean().getRealName(),o2.getMemberBean().getRealName());
			if(r == 0){
				return charCompare(o1.getMemberBean().getLccAccount(),o2.getMemberBean().getLccAccount());
			}else{
				return r;
			}
		}else if(o1.getMemberBean().getState()>Constants.STATUS_OFFLINE&&o2.getMemberBean().getState()<=Constants.STATUS_OFFLINE){
			return -1;
		}else if(o1.getMemberBean().getState()<=Constants.STATUS_OFFLINE&&o2.getMemberBean().getState()>Constants.STATUS_OFFLINE){
			return 1;
		}else if(o1.getMemberBean().getState()<=Constants.STATUS_OFFLINE&&o2.getMemberBean().getState()<=Constants.STATUS_OFFLINE){
//			return charCompare(o1.getMemberBean().getRealName(),o2.getMemberBean().getRealName());
			int r = charCompare(o1.getMemberBean().getRealName(),o2.getMemberBean().getRealName());
			if(r == 0){
				return charCompare(o1.getMemberBean().getLccAccount(),o2.getMemberBean().getLccAccount());
			}else{
				return r;
			}
		}
		return 0;
	}

	
	public int charCompare(String s1,String s2){
		CollationKey key1 = collator.getCollationKey(s1.toLowerCase());
	    CollationKey key2 = collator.getCollationKey(s2.toLowerCase());
	    return key1.compareTo(key2);
	}
}
