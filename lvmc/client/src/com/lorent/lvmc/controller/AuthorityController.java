package com.lorent.lvmc.controller;

import java.util.Collection;
import java.util.List;

import org.jivesoftware.smack.packet.Message;

import com.lorent.common.dto.LCMRoleDto;
import com.lorent.common.util.LCMUtil;
import com.lorent.lvmc.dto.LoginInfo;
import com.lorent.lvmc.dto.MemberDto;
import com.lorent.lvmc.event.AuthorityCheckListener;
import com.lorent.lvmc.ui.MemberListItem;
import com.lorent.lvmc.ui.MemberListPanel;
import com.lorent.lvmc.util.Constants;
import com.lorent.lvmc.util.DataUtil;
import com.lorent.lvmc.util.PermissionUtil;

public class AuthorityController extends BaseController {

	/**
	 * 
	 * @param item
	 * @param flag 1表示授予，-1表示取消
	 * @throws Exception 
	 */
	public void grantOrRevokeConfAuthority(MemberListItem item,int flag,String roleName) throws Exception{
		String lccno = item.getData().getName();
		LoginInfo loginInfo = DataUtil.getLoginInfo();
		
		if(Constants.REVOKE_AUTHORITY == flag){
			System.out.println("Constants.REVOKE_AUTHORITY============" + lccno + "====" + loginInfo.getConfno() + "flag" + flag);
			LCMUtil.newInstance(getXmlrpcUrl()).revokeConfAuthority(loginInfo.getConfno(), lccno, roleName);
		}else{
			System.out.println("Constants.GRANT_AUTHORITY============" + lccno + "====" + loginInfo.getConfno() + "flag" + flag);
			LCMUtil.newInstance(getXmlrpcUrl()).grantConfAuthority(loginInfo.getConfno(), lccno, roleName);
		}
	}
	
	
	public void updateAuthorityByRevokeAuthority(Message msg) throws Exception{
		String lccno = (String)msg.getProperty("lccno");
		String roleName = (String)msg.getProperty("roleName");
		LoginInfo loginInfo = DataUtil.getLoginInfo();
		System.out.println("RevokeAuthority============" + lccno);
		Collection<String> col = msg.getPropertyNames();
		MemberListPanel panel = ViewManager
		.getComponent(MemberListPanel.class);
		MemberDto memberDto = panel.getMemberByName(lccno);
//		int idx = -1;
//		for(int i=0;i<memberDto.getRole().getNames().size();i++){
//			if("主讲人".equals(memberDto.getRole().getNames().get(i))){
//				idx = i;
//				break;
//			}
//		}
		if(memberDto == null){
			return;
		}
		if(memberDto.getRole().getNames()!=null && memberDto.getRole().getNames().contains(roleName)){
			memberDto.getRole().getNames().remove(roleName);
		}
		MemberListItem memberListItem = panel.getMemberListItemByName(lccno);
		LCMRoleDto role = DataUtil.getValue(DataUtil.Key.myPermission);
		for (String key : col) {
			if (!key.equals("lccno")&&!key.equals("roleName")) {
				if (loginInfo.getUsername().equals(lccno)) {
					AuthorityCheckListener listener = PermissionUtil
							.getAuthorityCheckListener(key);
					if(listener!=null){
						listener.disable();
					}
					role.getPermissions().remove(key);
				}
				memberDto.getRole().getPermissions().remove(key);
			}
		}
		memberListItem.setData(memberDto, PermissionUtil.hasPermission(PermissionUtil.AUTHORITY_OPERATE));
		panel.revalidate();
		panel.repaint();
	}
	
	public void updateAuthorityByGrantAuthority(Message msg) throws Exception{
		String lccno = (String)msg.getProperty("lccno");
		String roleName = (String)msg.getProperty("roleName");
		LoginInfo loginInfo = DataUtil.getLoginInfo();
		System.out.println("GrantAuthority============" + lccno);
		Collection<String> col = msg.getPropertyNames();
		MemberListPanel panel = ViewManager
		.getComponent(MemberListPanel.class);
		LCMRoleDto role = DataUtil.getValue(DataUtil.Key.myPermission);
		if(roleName.equals(Constants.COMPERE_STR)){
			List<MemberListItem> items = panel.getAllMemberListItem();
			for(MemberListItem item:items){
				MemberDto dto = panel.getMemberByName(item.getData().getName());
				for (String key : col) {
					if (!key.equals("lccno")&&!key.equals("roleName")){
						dto.getRole().getPermissions().remove(key);
						if(dto.getName().equals(loginInfo.getUsername()) && dto.getRole().getNames().contains(Constants.COMPERE_STR)){
							AuthorityCheckListener listener = PermissionUtil
							.getAuthorityCheckListener(key);
							if(listener!=null){
								listener.disable();
							}
							role.getPermissions().remove(key);
						}
					}
				}
				dto.getRole().getNames().remove(roleName);
				item.setData(dto, PermissionUtil.hasPermission(PermissionUtil.AUTHORITY_OPERATE));
			}
		}
		MemberDto memberDto = panel.getMemberByName(lccno);
//		boolean isExist = false;
//		for(int i=0;i<memberDto.getRole().getNames().size();i++){
//			if("主讲人".equals(memberDto.getRole().getNames().get(i))){
//				isExist = true;
//				break;
//			}
//		}
		if(memberDto == null){
			return;
		}
		if(!memberDto.getRole().getNames().contains(roleName)){
			memberDto.getRole().getNames().add(roleName);
		}
		MemberListItem memberListItem = panel.getMemberListItemByName(lccno);
		for (String key : col) {
			if (!key.equals("lccno")&&!key.equals("roleName")) {
				if (loginInfo.getUsername().equals(lccno)) {
					AuthorityCheckListener listener = PermissionUtil
							.getAuthorityCheckListener(key);
					if(listener!=null){
						listener.enable();
					}
					role.getPermissions().put(key, (String)msg.getProperty(key));
				}
				memberDto.getRole().getPermissions().put(key,(String)msg.getProperty(key));
			}
		}
		memberListItem.setData(memberDto, false);	
		panel.revalidate();
		panel.repaint();
	}
	
}
