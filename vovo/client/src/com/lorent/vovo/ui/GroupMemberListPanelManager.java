package com.lorent.vovo.ui;

import java.util.Map;

import com.lorent.common.tree.MemberBean;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.controller.ChatController;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.DataUtil;

public class GroupMemberListPanelManager {

	public static void reflashGroupMemberListPanelItemByStatus(MemberBean bean){
		String userJid = bean.getLccAccount();
		int status = bean.getState();
		Map<String, GroupMemberListPanel> map = DataUtil.getValue(Constants.DataKey.GroupMemberListPanel);
		if(map != null){
			java.util.Collection<GroupMemberListPanel> col = map.values();
			for(GroupMemberListPanel panel:col){
				panel.reflashItemByStatus(userJid, status);
			}
		}
		Map<String, GroupChatSetFrame> map1 = DataUtil.getValue(Constants.DataKey.GroupChatSetFrame);
		if(map1 != null){
			java.util.Collection<GroupChatSetFrame> col = map1.values();
			for(GroupChatSetFrame frame:col){
				frame.reflashItemByStatus(userJid, status);
			}
		}
		reflashMessageTabPanel(bean);
	}
	
	public static void reflashMessageTabPanel(MemberBean bean){
		MessageTabPanel tabPanel = Vovo.getViewManager().getView(Constants.ViewKey.MESSAGETABPANEL.toString());
		if(tabPanel!=null){
			FriendChatPanel panel = (FriendChatPanel)tabPanel.getTab(ChatController.getSessionID(bean.getId()));
			if(panel!=null){
				CloseableTabTitle closeableTabTitle = tabPanel.getCloseableTabTitle(ChatController.getSessionID(bean.getId()));
				closeableTabTitle.setMemberInfo(bean);
				panel.setInfo(bean);
			}
			tabPanel.revalidate();
			tabPanel.repaint();
		}
	}
	
	public static void reflashGroupMemberListPanelItem(MemberBean bean){
		Map<String, GroupMemberListPanel> map = DataUtil.getValue(Constants.DataKey.GroupMemberListPanel);
		if(map != null){
			java.util.Collection<GroupMemberListPanel> col = map.values();
			for(GroupMemberListPanel panel:col){
				panel.reflashItem(bean);
			}
		}
		Map<String, GroupChatSetFrame> map1 = DataUtil.getValue(Constants.DataKey.GroupChatSetFrame);
		if(map1 != null){
			java.util.Collection<GroupChatSetFrame> col = map1.values();
			for(GroupChatSetFrame frame:col){
				frame.reflashItem(bean);
			}
		}
		if(bean.getLccAccount().equals(DataUtil.getUserName())){
			MainFrame mainframe = Vovo.getMyContext().getViewManager().getView(Constants.ViewKey.MAINFRAME.toString());
			mainframe.setMyInfo(bean);
		}
		reflashMessageTabPanel(bean);
		
	}
	
	public static void deleteGroupMemberListPanelItem(String userJid){
		Map<String, GroupMemberListPanel> map = DataUtil.getValue(Constants.DataKey.GroupMemberListPanel);
		if(map != null){
			java.util.Collection<GroupMemberListPanel> col = map.values();
			for(GroupMemberListPanel panel:col){
				panel.removeItem(userJid);
			}
		}
		Map<String, GroupChatSetFrame> map1 = DataUtil.getValue(Constants.DataKey.GroupChatSetFrame);
		if(map1 != null){
			java.util.Collection<GroupChatSetFrame> col = map1.values();
			for(GroupChatSetFrame frame:col){
				frame.removeItem(userJid);
			}
		}
	}
	
}
