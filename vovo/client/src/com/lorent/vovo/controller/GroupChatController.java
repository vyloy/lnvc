package com.lorent.vovo.controller;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.jivesoftware.smackx.muc.HostedRoom;
import org.jivesoftware.smackx.packet.MUCUser;
import org.jivesoftware.smackx.packet.MUCUser.Destroy;

import com.lorent.common.controller.BaseController;
import com.lorent.common.dto.VovoMyInfo;
import com.lorent.common.tree.MemberBean;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.bean.DepartmentTreeNode;
import com.lorent.vovo.dto.ChatRecord;
import com.lorent.vovo.dto.ControllerEvent;
import com.lorent.vovo.dto.FontStyle;
import com.lorent.vovo.dto.GroupInfo;
import com.lorent.vovo.iq.ApplyInGroupChatBean;
import com.lorent.vovo.iq.FetchGroupChatAuthorityBean;
import com.lorent.vovo.iq.GroupChatJidBean;
import com.lorent.vovo.iq.QuitGroupChatBean;
import com.lorent.vovo.iq.SearchGroupChatBean;
import com.lorent.vovo.iq.UpdateGroupChatAuthorityBean;
import com.lorent.vovo.ui.DefaultRightCornerPopMessageShowStrategy;
import com.lorent.vovo.ui.FriendChatPanel;
import com.lorent.vovo.ui.GroupChatInviteDialog;
import com.lorent.vovo.ui.GroupChatPanel;
import com.lorent.vovo.ui.GroupChatSetFrame;
import com.lorent.vovo.ui.GroupListItem;
import com.lorent.vovo.ui.GroupListPanel;
import com.lorent.vovo.ui.GroupMemberListItem;
import com.lorent.vovo.ui.GroupMemberListPanel;
import com.lorent.vovo.ui.GroupSearchDialog;
import com.lorent.vovo.ui.GroupSearchPanelItem;
import com.lorent.vovo.ui.MainFrame;
import com.lorent.vovo.ui.MessageFrame;
import com.lorent.vovo.ui.MessageTabPanel;
import com.lorent.vovo.ui.MsgRecordPanel;
import com.lorent.vovo.ui.MyTrayIcon;
import com.lorent.vovo.ui.RecieveGroupChatApplyInDialog;
import com.lorent.vovo.ui.RecieveGroupChatInviteDialog;
import com.lorent.vovo.ui.RightCornerMessageDialog;
import com.lorent.vovo.ui.RightCornerPopMessageManager;
import com.lorent.vovo.ui.TestMainFrame;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.DataUtil;
import com.lorent.vovo.util.FlashWindowUtil;
import com.lorent.vovo.util.ImageUtil;
import com.lorent.vovo.util.MyOpenfireUtil;
import com.lorent.vovo.util.TreeUtil;
import com.lorent.vovo.util.VovoStringUtil;

public class GroupChatController extends BaseController{

	private static Logger log = Logger.getLogger(GroupChatController.class);
	
	public void showErrorDialogByGroupChatTopicNull(){
		this.showErrorDialog(VovoStringUtil.getErrorString("error.normal.tip"), VovoStringUtil.getErrorString("groupchat.topic.null"));
	}
	
	public void showErrorDialogByGroupChatTopicLen(){
		this.showErrorDialog(VovoStringUtil.getErrorString("error.normal.tip"), VovoStringUtil.getFormatString(VovoStringUtil.getErrorString("groupchat.topic.length"), Constants.GROUPCHAT_TOPIC_LEN));
	}
	
	public void showErrorDialogByGroupChatDescLen(){
		this.showErrorDialog(VovoStringUtil.getErrorString("error.normal.tip"), VovoStringUtil.getFormatString(VovoStringUtil.getErrorString("groupchat.description.length"), Constants.GROUPCHAT_DESCRIPTION_LEN));
	}
	
	public GroupListItem createGroupChat(String topic,String desc, List<String> members,boolean isCreateTempGroupChat)throws Exception{
		if(topic==null || topic.length()<1){
			showErrorDialogByGroupChatTopicNull();
			return null;
		}else if(topic.length()>Constants.GROUPCHAT_TOPIC_LEN){
			showErrorDialogByGroupChatTopicLen();
			return null;
		}
		if(desc.length()>Constants.GROUPCHAT_DESCRIPTION_LEN){
			showErrorDialogByGroupChatDescLen();
			return null;
		}
		if(!isCreateTempGroupChat){
			Collection<HostedRoom> hostedRooms = MyOpenfireUtil.getGroupHostedRooms();
			for (HostedRoom hostedRoom : hostedRooms) {
				if (hostedRoom.getName().equals(topic)) {
					this.showErrorDialog(VovoStringUtil.getErrorString("error.normal.tip"), VovoStringUtil.getErrorString("groupchat.topic.same"));
					return null;
				}
			}
		}
		
		String roomjid = MyOpenfireUtil.createGroupChat(topic,desc,members);
		GroupListItem item = new GroupListItem();
		item.setRoomJid(roomjid);
		item.setGroupName(topic);
		item.setGroupDesc(desc);
		GroupListPanel groupListPanel = context.getViewManager().getView(Constants.ViewKey.GroupListPanel.toString());
		groupListPanel.addItem(item);
		groupListPanel.repaint();
		return item;
	}
	
	public void quitGroupChat(){
		GroupListPanel groupListPanel = context.getViewManager().getView(Constants.ViewKey.GroupListPanel.toString());
		GroupListItem item = (GroupListItem) groupListPanel.getGroupList().getSelectedValue();
		if(item != null){
			int result = this.showConfirmDialog(null, "你确定要退出" + item.getGroupName() + "群");
			if(result==JOptionPane.OK_OPTION){
				MyOpenfireUtil.quitGroupChat(item.getRoomJid());
			}
		}
	}
	
	public void quitGroupChatCallback(final QuitGroupChatBean bean){
		if(bean.getResult().equals(Constants.SUCCESS)){
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run(){
					GroupListPanel groupListPanel = context.getViewManager().getView(Constants.ViewKey.GroupListPanel.toString());
					DefaultListModel model = (DefaultListModel) groupListPanel.getGroupList().getModel();
					String roomJid = bean.getRoomJid();
					int index=-1;
					GroupListItem item=null;
					for(int i = 0; i < model.getSize(); i++) {
						item = (GroupListItem)(model.getElementAt(i));
						if(roomJid.equals(item.getRoomJid())){
							index=i;
							break;
						}
					}
					if(bean.isNeedToConfirm()&&item!=null){
						String groupName = item.getGroupName();
//						int result = JOptionPane.showConfirmDialog(null, "你确定要解散群组"+groupName+"吗？","退出群组"+groupName,JOptionPane.OK_CANCEL_OPTION);
//						if(result==JOptionPane.OK_OPTION){
							MyOpenfireUtil.quitGroupChat(item.getRoomJid(),true);
//						}
					}else if(index!=-1){
						
						try {
							MyOpenfireUtil.leftGroupChat(roomJid);
						} catch (Exception e) {
							e.printStackTrace();
						}
//						model.remove(index);
//						groupListPanel.repaint();
						Vovo.exeC("groupChat", "myselfLeftGroupChat", roomJid);
					}
				}
			});
		}else{
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					JOptionPane.showMessageDialog(null, bean.getMessage(),"退出失败",JOptionPane.ERROR_MESSAGE);
				}
			});
			
		}
	}
	
	public void dismissGroupChatCallback(final MUCUser user,final String roomJid){
		SwingUtilities.invokeLater(new Runnable() {
			
//			@Override
//			public void run() {
//				GroupListPanel groupListPanel = context.getViewManager().getView(Constants.ViewKey.GroupListPanel.toString());
//				DefaultListModel model = (DefaultListModel) groupListPanel.getGroupList().getModel();
//				Destroy destroy = user.getDestroy();
//				if(destroy==null)
//					return;
//				String roomJid = destroy.getJid();
//				int index=-1;
//				GroupListItem item=null;
//				for(int i = 0; i < model.getSize(); i++) {
//					item = (GroupListItem)(model.getElementAt(i));
//					if(roomJid.equals(item.getRoomJid())){
//						index=i;
//						break;
//					}
//				}
//				if(index!=-1){
//					MyOpenfireUtil.leftGroupChat(roomJid);
//					model.remove(index);
//					groupListPanel.repaint();
//					JOptionPane.showMessageDialog(null, "群"+item.getGroupName()+"由于"+destroy.getReason());
//					Vovo.exeC("groupChat", "myselfLeftGroupChat", roomJid);
//				}
//			}
			
			@Override
			public void run() {
				Destroy destroy = user.getDestroy();
				if(destroy==null)
					return;
				try {
					log.info("destroy.getJid()===============" + destroy.getJid());
					if(!DataUtil.getUserName().equals(destroy.getJid())){
						MyOpenfireUtil.leftGroupChatByDestroy(roomJid);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				GroupListItem item = (GroupListItem) Vovo.exeC("groupChat", "myselfLeftGroupChat", roomJid);
				try {
					if(!DataUtil.getUserName().equals(destroy.getJid())){
						JOptionPane.showMessageDialog(null, "群"+item.getGroupName()+"由于"+destroy.getReason());
					}
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
		});
	}
	
	public void getGroupChatJid(){
		MyOpenfireUtil.getGroupChatJid();
	}
	
	//
	public void initGroupChat(final GroupChatJidBean bean) throws Exception{
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				try{
					if(bean.getResult().equals(Constants.SUCCESS)){
						log.info("初始化群组 开始。。。。。。。。。。。。。。。。。。。。。。");
						Map<String,String[]> map = MyOpenfireUtil.joinGroupChat(bean.getJids());
						Set<Map.Entry<String,String[]>> set = map.entrySet();
						GroupListPanel groupListPanel = context.getViewManager().getView(Constants.ViewKey.GroupListPanel.toString());
						for(Map.Entry<String,String[]> entry:set){
							String roomjid = entry.getKey();
							String[] contents = entry.getValue();
							GroupListItem item = new GroupListItem();
							item.setRoomJid(roomjid);
							item.setGroupName(contents[0]!=null?contents[0]:"");
							item.setGroupDesc(contents[1]!=null?contents[1]:"");
							groupListPanel.addItem(item);
							groupListPanel.repaint();
						}
						log.info("初始化群组 结束。。。。。。。。。。。。。。。。。。。。。。");
					}
					MyOpenfireUtil.addGroupChatListener();
				}catch(Exception ex){
					ex.printStackTrace();
					log.error(ex.getMessage(),ex);				
				}
			}
		});
	}
	
	//显示群组的成员列表
	public void showGroupMemberListPanel(String roomJid) throws Exception{
//		GroupMemberListPanel panel = DataUtil.getGroupMemberListPanel(roomJid);
//		if(panel == null){
//			panel = new GroupMemberListPanel();
//			GroupMemberListItem item = new GroupMemberListItem();
//			item.setMemberBean(node.getInfo().getMember());
//			panel.addItem(item);
//		}
		GroupMemberListPanel panel = getGroupMemberListPanel(roomJid);
		
		TestMainFrame frame = Vovo.getViewManager().createView(TestMainFrame.class, Constants.ViewKey.TestMainFrame.toString());// new TestMainFrame();
		frame.setRoomJid(roomJid);
		frame.addPanel(panel);
		Vovo.getViewManager().setWindowCenterLocation(frame);
		try{
			Vovo.getMyContext().getPermissionUtil().checkPermission("SetGroupChatInfoPermissionCheck",roomJid);
			frame.setGroupSetButtonVisible(true);
		}catch(Exception ex){
			
		}
		frame.setVisible(true);
		
	}
	
	private GroupMemberListPanel getGroupMemberListPanel(String roomJid) throws Exception{
		GroupMemberListPanel panel = DataUtil.getGroupMemberListPanel(roomJid);
		if(panel!=null){
			panel.clearMemberList();
		}
		List<String> list = MyOpenfireUtil.getGroupChatMembers(roomJid);
		for(String userJid:list){
			addMemberToGroupChat(userJid,roomJid);
		}
		panel = DataUtil.getGroupMemberListPanel(roomJid);
		return panel;
	}
	
	public void showGroupChat(GroupInfo info)throws Exception{
		MessageFrame msgFrame = getMessageFrame();
		getGroupChatPanel(info);
		String key = Constants.GROUP_CHAT_SESSION_PREFIX + info.getRoomJID();
		ControllerEvent event = new ControllerEvent();
		event.setExClass("chat");
		event.setExMethod("showNotReadMessage");
		event.setParas(new Object[]{key});
		MyTrayIcon trayIcon = context.getViewManager().getView(Constants.ViewKey.TRAYICON.toString());
		trayIcon.removeEvent(event);
		Vovo.exeC("chat", "showNotReadMessage", key);
//		if(!msgFrame.isVisible()){
			msgFrame.setExtendedState(JFrame.NORMAL);
			msgFrame.setVisible(true);
//		}
	}
	
	public GroupChatPanel getGroupChatPanel(GroupInfo info)throws Exception{
		String sessionID = Constants.GROUP_CHAT_SESSION_PREFIX + info.getRoomJID();
		MessageTabPanel tabPanel = context.getViewManager().getView(Constants.ViewKey.MESSAGETABPANEL.toString());
		GroupChatPanel panel = (GroupChatPanel)tabPanel.getTab(sessionID);
		if(panel == null){
			panel = new GroupChatPanel();
			panel.setInfo(info);
			try{
				Vovo.getMyContext().getPermissionUtil().checkPermission("SetGroupChatInfoPermissionCheck",info.getRoomJID());
			}catch(Exception e){
				panel.hiddenGroupSetButton();
			}
			ImageIcon icon = ImageUtil.getImageIcon(ImageUtil.GROUP_SMALL_ICON);
			tabPanel.addTab(panel, info.getTopic(), icon, sessionID);
			panel.initShareFilePanel(sessionID);
			panel.setGroupMemberPanel(getGroupMemberListPanel(info.getRoomJID()));
		}else{
//			tabPanel.setSelectTab(sessionID);
			tabPanel.setNoticeTab(sessionID);
		}
		return panel;
	}
	
	private MessageFrame getMessageFrame(){
		MessageFrame frame = (MessageFrame)Vovo.exeC("chat", "getMessageFrame");
		return frame;
	}
	
	public void showRightCornerMessageDialog(String content){
		RightCornerMessageDialog dialog = new RightCornerMessageDialog(null,false);
		dialog.setContent(content);
		RightCornerPopMessageManager.showRightCornerPopMessage(dialog, new DefaultRightCornerPopMessageShowStrategy());
	}
	
	public void recieveRefuseJoinGroupChatMessage(String topic,String roomjid){
		showRightCornerMessageDialog(topic + "群拒绝了你的申请");
	}
	
	public void showJoinGroupChat(final String roomjid,final String topic,final String desc,final boolean autoJoin)throws Exception{
		if(autoJoin){
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					GroupListPanel groupListPanel = context.getViewManager().getView(Constants.ViewKey.GroupListPanel.toString());
					if(groupListPanel.findItemByRoomJid(roomjid)!=null){
						return;
					}
					GroupListItem item = new GroupListItem();
					item.setRoomJid(roomjid);
					item.setGroupName(topic);
					item.setGroupDesc(desc);
					groupListPanel.addItem(item);
				}
			});
		}
	}
	
	public void forbidKickMyself(){
		this.showErrorDialog("错误提示", "不能将自己从群踢出");
	}
	
	public void kickOneFromGroupChat(MemberBean bean,String roomJid) throws Exception{
		Vovo.getMyContext().getPermissionUtil().checkPermission("KickOneFromGroupChatPermissionCheck",roomJid);
		int result = this.showConfirmDialog(null, "你确定要将" + bean.getRealName() + "踢出群吗？");
//		System.out.println(result);
//		System.out.println(JOptionPane.OK_OPTION);
		if(result==JOptionPane.OK_OPTION){
			MyOpenfireUtil.kickOneFromGroupChat(bean,roomJid);
		}
	}
	
	public GroupListItem myselfLeftGroupChat(String roomJid){
		GroupListPanel groupListPanel = context.getViewManager().getView(Constants.ViewKey.GroupListPanel.toString());
		
		MyOpenfireUtil.removeMultiUserChatByRoomJid(roomJid);
		GroupListItem item = groupListPanel.removeItem(roomJid);
		removeAuthoritiesOfGroupChat(roomJid);
		GroupMemberListPanel panel = DataUtil.removeGroupMemberListPanel(roomJid);
		String sessionID = Constants.GROUP_CHAT_SESSION_PREFIX + roomJid;
		MessageTabPanel tabPanel = context.getViewManager().getView(Constants.ViewKey.MESSAGETABPANEL.toString());
		if(tabPanel != null){
			tabPanel.removeTab(sessionID);
		}
		GroupChatSetFrame setFrame = DataUtil.getGroupChatSetFrame(roomJid);
		if(setFrame!=null){
			setFrame.dispose();
		}
		return item;
	}
	
	public void leafGroupChatByKick(final String roomJid) throws Exception{
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				GroupListItem item = myselfLeftGroupChat(roomJid);
				String content = "你被请出" + item.getGroupName() + "群";
				System.out.println(content);
				showRightCornerMessageDialog(content);
			}
		});
	}
	
	
	public void removeAuthoritiesOfGroupChat(String roomJid){
		Map<String,List<String>> authorityMap = DataUtil.getValue(Constants.DataKey.groupChatAuthority);
		if(authorityMap!=null){
			authorityMap.remove(roomJid);
		}
	}
	
	
	public void ownerKickOneFromGroupChat(String userJid,String actor,String roomJid) throws Exception{
//		GroupMemberListPanel panel = DataUtil.getGroupMemberListPanel(roomJid);
//		panel.removeItem(userJid);
//		GroupChatSetFrame setFrame = DataUtil.getGroupChatSetFrame(roomJid);
//		if(setFrame!=null){
//			setFrame.removeItem(userJid);
//		}
		
		oneLeftFromGroupChat(userJid,roomJid);
	}
	
	public void oneLeftFromGroupChat(final String userJid,final String roomJid){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				try {
					MyOpenfireUtil.revokeOwnership(userJid, roomJid);
				} catch (Exception e) {
				}
				GroupMemberListPanel panel = DataUtil.getGroupMemberListPanel(roomJid);
				if(panel!=null){
					panel.removeItem(userJid);
				}
				GroupChatSetFrame setFrame = DataUtil.getGroupChatSetFrame(roomJid);
				if(setFrame!=null){
					setFrame.removeItem(userJid);
				}
			}
		});
		
	}
	
	
	public void applyInGroupChat(String roomJid){
		if(DataUtil.getGroupChat(roomJid) != null){
			this.showErrorDialog("群组信息", "你已经加入了该群组");
		}else{
			MyOpenfireUtil.applyInGroupChat(roomJid);
			RightCornerMessageDialog dialog = new RightCornerMessageDialog(null,false);
			dialog.setContent("已发送申请，等候对方验证");
			RightCornerPopMessageManager.showRightCornerPopMessage(dialog, new DefaultRightCornerPopMessageShowStrategy());
		}
		
	}
	
	public void recieveGroupChatApplyIn(final ApplyInGroupChatBean bean) throws Exception{
		if(bean.getResult().equals(Constants.SUCCESS)){
			if(MyOpenfireUtil.isjoinGroupChat(bean.getApplicantJid(), bean.getRoomJid())){
				return;
			}
			//显示某某申请加入群组信息界面
			String subject = MyOpenfireUtil.getRoomSubject(bean.getRoomJid());
			String realName = MyOpenfireUtil.getRealNameByJid(bean.getApplicantJid());
			final String content = realName + "向您申请加入群组" + subject;
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					RecieveGroupChatApplyInDialog dialog = new RecieveGroupChatApplyInDialog(null,false);
					dialog.setContent(content);
					dialog.setRoomJid(bean.getRoomJid());
					dialog.setApplicantJid(bean.getApplicantJid());
					RightCornerPopMessageManager.showRightCornerPopMessage(dialog, new DefaultRightCornerPopMessageShowStrategy());
				}
			});
		}else{
			//显示申请不成功界面
		}
	}
	
	public void refuseOneJoinGroupChat(String userJid,String roomJid) throws Exception{
		MyOpenfireUtil.refuseOneJoinGroupChat(userJid, roomJid);
	}
	
	public void agreeOneJoinGroupChat(String userJid,String roomJid) throws Exception{
		
		MyOpenfireUtil.agreeOneJoinGroupChat(userJid, roomJid);
	}
	
	public void addMemberToGroupChatCallBack(final String userJid,final String roomJid){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				GroupMemberListItem item = addMemberToGroupChat(userJid,roomJid);
				GroupMemberListPanel panel = DataUtil.getGroupMemberListPanel(roomJid);
				GroupChatSetFrame setFrame = DataUtil.getGroupChatSetFrame(panel.getRoomJid());
				if(setFrame != null && item != null){
					setFrame.addItem(item);
				}
			}
		});
	}
	
	public GroupMemberListItem addMemberToGroupChat(String userJid,String roomJid){
		GroupMemberListPanel panel = DataUtil.getGroupMemberListPanel(roomJid);
		if(panel == null){
			panel = new GroupMemberListPanel();
			panel.setRoomJid(roomJid);
			DataUtil.addGroupMemberListPanel(roomJid, panel);
		}
		DepartmentTreeNode node = TreeUtil.findMemberNodeByJid(userJid);
		if(node == null){
			if(userJid!=null){
				int idx = userJid.indexOf("@");
				if(idx>0){
					node = TreeUtil.findMemberNodeByJid(userJid.substring(0, idx));
				}
			}
		}
		if(node == null){
			return null;
		}
		GroupMemberListItem item = new GroupMemberListItem();
		item.setMemberBean(node.getInfo().getMember());
		panel.addItem(item);
//		panel.updateUI();
		return item;
//		TestMainFrame frame = Vovo.getViewManager().getView(Constants.ViewKey.TestMainFrame.toString());
//		if(frame!=null){
//			
//			frame.validate();
//			frame.repaint();
//		}
	}
	
	public void showGroupChatSetFrame(GroupMemberListPanel panel) throws Exception{
		GroupChatSetFrame setFrame = DataUtil.getGroupChatSetFrame(panel.getRoomJid());
		if(setFrame == null ){
			setFrame = new GroupChatSetFrame();//Vovo.getViewManager().createView(GroupChatSetFrame.class, Constants.ViewKey.GroupChatSetFrame.toString());
			DataUtil.addGroupChatSetFrame(panel.getRoomJid(), setFrame);
		}
		setFrame.initMemberList(panel);
		setFrame.setTopic(MyOpenfireUtil.getRoomSubject(panel.getRoomJid()));
		setFrame.setDesc(MyOpenfireUtil.getRoomDesc(panel.getRoomJid()));
		setFrame.setTitle(VovoStringUtil.getUIString("GroupChatSetFrame.title") +  MyOpenfireUtil.getRoomSubject(panel.getRoomJid()));
		Vovo.getViewManager().setWindowCenterLocation(setFrame);
		setFrame.setVisible(true);
	}
	
	public void refleshGroupChatMemberList(String userJid,String roomJid){
		//更新群组成员列表界面
	}
	
	public void fetchAllGroupChatAuthority(){
		GroupListPanel groupListPanel = context.getViewManager().getView(Constants.ViewKey.GroupListPanel.toString());
		javax.swing.JList groupList = groupListPanel.getGroupList();
		DefaultListModel model = (DefaultListModel) groupList.getModel();
		List<String> roomJids = new ArrayList<String>();
		Enumeration<GroupListItem> items = (Enumeration<GroupListItem>) model.elements();
		while(items.hasMoreElements()){
			GroupListItem item = items.nextElement();
			roomJids.add(item.getRoomJid());
		}
		FetchGroupChatAuthorityBean bean = MyOpenfireUtil.fetchGroupChatAuthority(roomJids);
		fetchGroupChatAuthorityCallBack(bean);
	}
	
	public void fetchOneGroupChatAuthority(String roomJid){
		if(roomJid!=null){
			Map<String, List<String>> map = DataUtil.getValue(Constants.DataKey.groupChatAuthority);
			if(map == null || !map.containsKey(roomJid)){
				List<String> list = new ArrayList<String>();
				list.add(roomJid);
				FetchGroupChatAuthorityBean bean = MyOpenfireUtil.fetchGroupChatAuthority(list);
				fetchGroupChatAuthorityCallBack(bean);
			}
		}
	}
	
	public void changeGroupChatTopicAndDesc(String roomJid,String topic,String desc) throws Exception{
		if(topic==null || topic.length()<1){
			showErrorDialogByGroupChatTopicNull();
			return;
		}else if(topic.length()>Constants.GROUPCHAT_TOPIC_LEN){
			showErrorDialogByGroupChatTopicLen();
			return;
		}
		if(desc.length()>Constants.GROUPCHAT_DESCRIPTION_LEN){
			showErrorDialogByGroupChatDescLen();
			return;
		}
		MyOpenfireUtil.changeGroupChatTopicAndDesc(roomJid, topic, desc);
	}
	
	public void changeGroupChatTopicAndDescCallBack(final String roomJid,final String topic,final String desc){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				GroupListPanel groupListPanel = context.getViewManager().getView(Constants.ViewKey.GroupListPanel.toString());
				groupListPanel.updateItem(roomJid, topic, desc);
				GroupChatSetFrame setFrame = DataUtil.getGroupChatSetFrame(roomJid);
				if(setFrame!=null){
					setFrame.setTitle(VovoStringUtil.getUIString("GroupChatSetFrame.title") + topic);
				}
				MessageTabPanel tabPanel = context.getViewManager().getView(Constants.ViewKey.MESSAGETABPANEL.toString());
				if(tabPanel != null){
					tabPanel.updateGroupChatTopicAndDesc(roomJid, topic, desc);
				}
			}
		});
	}
	
	public void fetchGroupChatAuthorityCallBack(FetchGroupChatAuthorityBean bean){
		Map<String,List<String>> authorityMap = DataUtil.getValue(Constants.DataKey.groupChatAuthority);
		if(authorityMap!=null){
			authorityMap.putAll(bean.getAuthorityMap());
		}else{
			DataUtil.setValue(Constants.DataKey.groupChatAuthority, bean.getAuthorityMap());
		}
		
	}
	
	
	public void updateGroupChatAuthority(){
		//
		String roomJid = null;
		String userJid = null;
		List<String> marks = null;
		MyOpenfireUtil.upateGroupChatAuthority(roomJid, userJid, marks);
	}
	
	public void updateGroupChatAuthorityCallBack(UpdateGroupChatAuthorityBean bean){
		Map<String,List<String>> authorityMap = DataUtil.getValue(Constants.DataKey.groupChatAuthority);
		authorityMap.remove(bean.getRoomJid());
		authorityMap.put(bean.getRoomJid(), bean.getMarks());
	}
	
	public void searchGroupChat(String searchStr) throws Exception{
		GroupSearchDialog dialog = Vovo.getViewManager().getView(Constants.ViewKey.GroupSearchDialog.toString());
		if(dialog == null){
			try {
				dialog = Vovo.getViewManager().createView(GroupSearchDialog.class,new Class[] { java.awt.Frame.class, boolean.class },
						new Object[] {
					null, false }, Constants.ViewKey.GroupSearchDialog.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		MyOpenfireUtil.searchGroupChat(searchStr);
		if(!dialog.isVisible()){
			dialog.setPreferredSize(new Dimension(640, 400));
			dialog.setSize(640, 400);
			dialog.repaint();
			dialog.clearTextField();
			Vovo.getViewManager().setWindowCenterLocation(dialog);
			dialog.setVisible(true);
		}else{
			Vovo.getViewManager().setWindowCenterLocation(dialog);
			dialog.setVisible(true);
		}
		
	}
	
	
	public void searchGroupChatCallBack(final SearchGroupChatBean bean) throws Exception{
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				GroupSearchDialog dialog = context.getViewManager().getView(Constants.ViewKey.GroupSearchDialog.toString());
//				if(dialog == null){
//					dialog = context.getViewManager().createView(GroupSearchDialog.class,new Class[] { java.awt.Frame.class, boolean.class },
//							new Object[] {
//						Vovo.getViewManager().getView(
//								Constants.ViewKey.MAIN_FRAME_NAME
//										.toString()), true }, Constants.ViewKey.GroupSearchDialog.toString());
//				}
				
				dialog.clearGroupChat();
				dialog.repaint();
				if(bean.getResult().equals(Constants.SUCCESS)){
					List<String[]> infos = bean.getGroupChatInfos();
					List<GroupSearchPanelItem> list = new ArrayList<GroupSearchPanelItem>();
					GroupSearchPanelItem searchPanel = new GroupSearchPanelItem();
					for(String[] str:infos){
						GroupListItem item = new GroupListItem();
						item.setRoomJid(str[0]);
						item.setGroupName(str[1]);
						item.setGroupDesc(str[2]);
						if(!searchPanel.addItem(item)){
							list.add(searchPanel);
							searchPanel = new GroupSearchPanelItem();
							searchPanel.addItem(item);
						}
					}
					if(!list.contains(searchPanel)){
						list.add(searchPanel);
					}
//					System.out.println(list.size());
					for(GroupSearchPanelItem pitem:list){
						dialog.addGroupChatItem(pitem);
					}
				}
				dialog.validate();
				dialog.repaint();
				
			}
		});
		
	}
	
	public void sendGroupChatMsg(String context, FontStyle fontStyle, String roomJID, Map<String,String> imgs, long time)throws Exception{
		MyOpenfireUtil.sendGroupChatMsg(roomJID, context, fontStyle, imgs, time);
		VovoMyInfo info = DataUtil.getMyInfo();
		ChatRecord c = new ChatRecord(info.getId(), info.getRealName(), context, new Date(time), fontStyle, Constants.GROUP_CHAT_SESSION_PREFIX + roomJID);
		Vovo.exeS("dataStore", "saveChatRecord", c);
	}
	
	public void getGroupChatMessage(final String roomJID, final String lccno, final String msg, final FontStyle fontStyle, final Date sendDate, final Map<String,String> imgs)throws Exception{
		if(DataUtil.getGroupChat(roomJID)==null){
			return;
		}
		MessageFrame msgFrame = getMessageFrame();
		final MemberBean memberBean = TreeUtil.getMemberBeanByLccno(lccno);
		GroupInfo info = MyOpenfireUtil.getGroupInfo(roomJID);
		final GroupChatPanel panel = getGroupChatPanel(info);
		if (!SwingUtilities.isEventDispatchThread()){
			SwingUtilities.invokeAndWait(new Runnable() {
				
				@Override
				public void run() {
					try{
						handleFriendMsg(panel, memberBean, msg, fontStyle, sendDate, roomJID, imgs, lccno);
					}catch(Exception e){
						log.error("insertGroupMsg", e);
						showErrorDialog("error", e.getMessage());
					}
				}
			});
		}else{
			handleFriendMsg(panel, memberBean, msg, fontStyle, sendDate, roomJID, imgs, lccno);
		}
		if(!msgFrame.isVisible()){
			msgFrame.setVisible(true);
		}
	}
	
	private void handleFriendMsg(GroupChatPanel panel, MemberBean memberBean, String msg, FontStyle fontStyle, Date sendDate, String roomJID, Map<String,String> imgs, String lccno)throws Exception{
		if(memberBean!=null){
			panel.insertFriendMsg(memberBean.getRealName(), msg, fontStyle, sendDate, imgs);
			FlashWindowUtil.start(getMessageFrame());
			ChatRecord c = new ChatRecord(memberBean.getId(), memberBean.getRealName(), msg, sendDate, fontStyle, Constants.GROUP_CHAT_SESSION_PREFIX + roomJID);
			Vovo.exeS("dataStore", "saveChatRecord", c);
		}else{
			panel.insertFriendMsg(lccno, msg, fontStyle, sendDate, imgs);
			FlashWindowUtil.start(getMessageFrame());
			ChatRecord c = new ChatRecord(0, lccno, msg, sendDate, fontStyle, Constants.GROUP_CHAT_SESSION_PREFIX + roomJID);
			Vovo.exeS("dataStore", "saveChatRecord", c);
		}
	}
	
	public void showInviteDialog(String roomJid) throws Exception{
		GroupChatInviteDialog dialog = new GroupChatInviteDialog((MainFrame)context.getViewManager().getView(Constants.ViewKey.MAINFRAME.toString()),true);
		dialog.setRoomJid(roomJid);
		context.getViewManager().setWindowCenterLocation(dialog);
		dialog.setVisible(true);
	}
	
	public boolean inviteOneToGroupChat(String roomJid,String lccAccount,GroupChatInviteDialog dialog) throws Exception{
		if(lccAccount==null || lccAccount.trim().length()<1){
//			this.showErrorDialog("", VovoStringUtil
//					.getUIString("GroupChatInviteDialog.inviteLabel.text"));
			dialog.setTipContent(VovoStringUtil.getUIString("GroupChatInviteDialog.inviteLabel.text"));
			return false;
		}
		if(!lccAccount.equals(DataUtil.getUserName())){
			if(MyOpenfireUtil.isGroupChatMember(roomJid, lccAccount)){
				dialog.setTipContent("被邀请者已在群组中");
				return false;
			}
			if(TreeUtil.getMemberBeanByLccno(lccAccount)==null){
				dialog.setTipContent("该号码不存在");
				return false;
			}
			MyOpenfireUtil.inviteOneToGroupChat(roomJid,lccAccount);
			return true;
		}else{
//			this.showErrorDialog("", "不能邀请自己");
			dialog.setTipContent("不能邀请自己");
			return false;
		}
	}
	
	public void invitationDeclined(String invitee, String reason){
		DepartmentTreeNode node = TreeUtil.findMemberNodeByJid(invitee.split("@")[0]);
//		if(node != null && node.getInfo().getMember()!=null){
//			this.showMessageDialog(null, node.getInfo().getMember().getRealName() + "(" + node.getInfo().getMember().getLccAccount() + ")" + "拒绝了你的邀请");
//		}
		this.showRightCornerMessageDialog(node.getInfo().getMember().getRealName() + "(" + node.getInfo().getMember().getLccAccount() + ")" + "拒绝了你的邀请");
	}
	
	public void recieveInviteMsg(final String room,final String inviter, final String reason, final String password,final String roomSubject,final String inviterName){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				RecieveGroupChatInviteDialog dialog = new RecieveGroupChatInviteDialog(null,false);
				dialog.setRoomJid(room);
				dialog.setInviter(inviter);
				dialog.setReason(reason);
				dialog.setPassword(password);
				dialog.setRoomSubject(roomSubject);
				dialog.setInviterName(inviterName);
				String content = inviterName + "邀请你加入群组" + roomSubject + "是否同意?";
				dialog.setContent(content);
				RightCornerPopMessageManager.showRightCornerPopMessage(dialog, new DefaultRightCornerPopMessageShowStrategy());
			}
		});
//		return this.showConfirmDialog(null, inviterName + "邀请你加入群组" + roomSubject + "是否同意?");
	}
	
	public void acceptGroupInvitation(String inviter,String room) throws Exception{
		MyOpenfireUtil.sendAcceptGroupInvitations(inviter,room);
	}
	
	public void refuseGroupInvitation(String inviter,String room) throws Exception{
		MyOpenfireUtil.refuseGroupInvitation(inviter,room);
	}
	
	
	
    public void getLatestRecord(String roomJID, MsgRecordPanel panel)throws Exception{
    	String sessionID = Constants.GROUP_CHAT_SESSION_PREFIX + roomJID;
    	int count = (Integer) Vovo.exeS("dataStore", "getRecordNum", sessionID);
    	int totalPage = count / Constants.SHOW_MESSAGE_PER_PAGE + 1;
    	int offset = (totalPage - 1) * Constants.SHOW_MESSAGE_PER_PAGE;
    	List<ChatRecord> records = (List<ChatRecord>) Vovo.exeS("dataStore", "getChatRecord", sessionID, Constants.SHOW_MESSAGE_PER_PAGE, offset);
    	panel.initInfo(totalPage, totalPage, records, sessionID);
    }
    
    public void changePage(int page, String sessionID, MsgRecordPanel panel)throws Exception{
    	int offset = (page - 1) * Constants.SHOW_MESSAGE_PER_PAGE;
    	List<ChatRecord> records = (List<ChatRecord>) Vovo.exeS("dataStore", "getChatRecord", sessionID, Constants.SHOW_MESSAGE_PER_PAGE, offset);
    	panel.changeData(records);
    }
}
