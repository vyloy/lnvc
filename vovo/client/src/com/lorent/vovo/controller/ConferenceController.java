package com.lorent.vovo.controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
import org.apache.xmlrpc.XmlRpcException;

import com.lorent.common.controller.BaseController;
import com.lorent.common.dto.LCMConferenceDto;
import com.lorent.common.dto.LCMConferenceRoleBean;
import com.lorent.common.dto.LCMConferenceTypeBean;
import com.lorent.common.dto.LCMRoleDto;
import com.lorent.common.tree.MemberBean;
import com.lorent.common.util.LCMUtil;
import com.lorent.common.util.OpenfireUtil;
import com.lorent.common.util.PasswordUtil;
import com.lorent.lvmc.Launcher;
import com.lorent.lvmc.Launcher.EventListener;
import com.lorent.util.LCCUtil;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.dto.LoginInfo;
import com.lorent.vovo.ui.ConferenceBubbleDialog;
import com.lorent.vovo.ui.ConferenceCardInfoPanel;
import com.lorent.vovo.ui.ConferenceDescShowDialog;
import com.lorent.vovo.ui.ConferenceEditDialog;
import com.lorent.vovo.ui.ConferenceListItem;
import com.lorent.vovo.ui.ConferencePanel;
import com.lorent.vovo.ui.JoinConfConfirmDialog;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.DataUtil;
import com.lorent.vovo.util.TreeUtil;
import com.lorent.vovo.util.VovoStringUtil;

public class ConferenceController extends BaseController {
	
	
	private static Logger log = Logger.getLogger(ConferenceController.class);
	
	public void showConferenceCardInfoPanel(ConferencePanel panel,ConferenceListItem item, java.awt.event.MouseEvent evt) throws Exception{
		
//		ConferenceCardInfoPanel conferenceCardInfoPanel = new ConferenceCardInfoPanel();
		
		ConferenceCardInfoPanel  conferenceCardInfoPanel = context.getViewManager().getView(Constants.ViewKey.CONFERENCE_CARDINFOPANEL.toString());
		if (conferenceCardInfoPanel == null) {
			conferenceCardInfoPanel = context.getViewManager().createView(ConferenceCardInfoPanel.class, Constants.ViewKey.CONFERENCE_CARDINFOPANEL.toString());
		}
		
		
		JPopupMenu cardInfoPopupMenu = panel.getCardInfoPopupMenu();
//		cardInfoPopupMenu.getRootPane().setOpaque(false);
//		cardInfoPopupMenu.setOpaque(false);
		cardInfoPopupMenu.removeAll();
		cardInfoPopupMenu.add(conferenceCardInfoPanel);
		
		
		String stemp;
		LCMConferenceDto lcmConferenceDto = item.getLcmConferenceDto();
		conferenceCardInfoPanel.getConferenceNameLabel().setText(Vovo.getViewManager().getFormatString("ConferenceController.CardInfo.ConfName", lcmConferenceDto.getConferenceName()));
		conferenceCardInfoPanel.getConfNoLabel().setText(Vovo.getViewManager().getFormatString("ConferenceController.CardInfo.ConfNo", lcmConferenceDto.getConfNo()));
		conferenceCardInfoPanel.getConferenceRoleLabel().setText(Vovo.getViewManager().getFormatString("ConferenceController.CardInfo.DefaultRole", lcmConferenceDto.getDefaultRoleName()));
		conferenceCardInfoPanel.getCreatorLabel().setText(Vovo.getViewManager().getFormatString("ConferenceController.CardInfo.Creator", lcmConferenceDto.getCreatorName()));
		stemp = lcmConferenceDto.getNeedApply().equals(0) ? Vovo.getViewManager().getUIString("ConferenceController.NeedApply.No") : Vovo.getViewManager().getUIString("ConferenceController.NeedApply.Yes");
		conferenceCardInfoPanel.getNeedApplyLabel().setText(Vovo.getViewManager().getFormatString("ConferenceController.CardInfo.NeedApply", stemp));
		stemp = lcmConferenceDto.getTopic() == null ? "" : lcmConferenceDto.getTopic();
		conferenceCardInfoPanel.getTopicLabel().setText(Vovo.getViewManager().getFormatString("ConferenceController.CardInfo.Topic", stemp));
		stemp = lcmConferenceDto.getDescription() == null ? "" : lcmConferenceDto.getDescription();
		conferenceCardInfoPanel.setConferenceListItem(item);
		if(stemp.length()>10){
			conferenceCardInfoPanel.getShowSpecLabel().setVisible(true);
			conferenceCardInfoPanel.getDescriptionLabel().setText(Vovo.getViewManager().getFormatString("ConferenceController.CardInfo.Description", stemp.substring(0, 9) + "..."));
		}else{
			conferenceCardInfoPanel.getShowSpecLabel().setVisible(false);
			conferenceCardInfoPanel.getDescriptionLabel().setText(Vovo.getViewManager().getFormatString("ConferenceController.CardInfo.Description", stemp));
		}
		//计算位置
		Point locationOnScreen = panel.getConferenceList().getLocationOnScreen();
		int leftx = 0;
		int topy = 0;
		if ((locationOnScreen.x - panel.getWidth()) > cardInfoPopupMenu.getWidth()) {
			leftx = 0-cardInfoPopupMenu.getWidth();
		}
		else{
			leftx = panel.getWidth();
		}
		cardInfoPopupMenu.show(panel.getConferenceList(), leftx, evt.getPoint().y);
		
	}
	
	public void getConferenceList(ConferencePanel panel) throws Exception{
		
//		ConferencePanel panel = Vovo.getMyContext().getViewManager().getView(Constants.ViewKey.CONFERENCEPANEL.toString());
		DefaultListModel model = (DefaultListModel) panel.getConferenceList().getModel();
		model.removeAllElements();
		
		Map<String, LCMConferenceDto> confList = Vovo.getLcmUtil().getConfList();
		Collection<LCMConferenceDto> values = confList.values();
		for (LCMConferenceDto lcmConferenceDto : values) {
			
			ConferenceListItem conferenceListItem = new ConferenceListItem();
			conferenceListItem.getInfoTopLabel().setText(lcmConferenceDto.getConferenceName()+"  ("+lcmConferenceDto.getConfNo()+")");
			conferenceListItem.getInfoCenterLabel().setText(Vovo.getViewManager().getFormatString("ConferenceController.CardInfo.Topic", (lcmConferenceDto.getTopic()==null? "":lcmConferenceDto.getTopic())));
			conferenceListItem.setLcmConferenceDto(lcmConferenceDto);
			
			if (lcmConferenceDto.getPassword() != null && !lcmConferenceDto.getPassword().equals("")) {
				conferenceListItem.getIconLabel().setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/conference1.png")));
			}
			
			model.addElement(conferenceListItem);
			
		}
	}
	
	public void showConferenceModifyDialog(ConferencePanel panel,ConferenceListItem selectedItem) throws Exception{
		if (selectedItem != null) {
			if(DataUtil.getMyInfo().getId() != selectedItem.getLcmConferenceDto().getCreator()){//not conf creator
				this.showErrorDialog(getUIString("common.error"), getUIString("conference.notConfCreator"));
				return;
			}
//			JDialog jDialog = context.getViewManager().getView(Constants.VIEW_CONFERENCE_MODIFYIDALOG);
			ConferenceEditDialog jDialog = new ConferenceEditDialog(null, true);
			jDialog.setOpt(Constants.VIEW_CONF_EDIT_MODIFY);
//			jDialog.getMainTabbedPane().getComponentAt(1).setVisible(false);
//			jDialog.getNoticeXPanel().setVisible(false);
			jDialog.getMainTabbedPane().remove(1);
			jDialog.setConfId(selectedItem.getLcmConferenceDto().getId());
			jDialog.setConfNo(selectedItem.getLcmConferenceDto().getConfNo());
			context.getViewManager().setWindowCenterLocation(jDialog);
			
			
			LoginInfo loginInfo = Vovo.getMyContext().getDataManager().getValue(Constants.DataKey.LOGGININFO.toString());
			Map<String, Object> confDefaultOptions = Vovo.getLcmUtil().getConfDefaultOptions();
			//会议类型
			DefaultComboBoxModel confTypeModel = (DefaultComboBoxModel) jDialog.getConfTypeComboBox().getModel();
			confTypeModel.removeAllElements();
			Map<Integer, LCMConferenceTypeBean> typeMap = (Map<Integer, LCMConferenceTypeBean>) confDefaultOptions.get("conference_type");
			Collection<LCMConferenceTypeBean> values = typeMap.values();
			
			for (LCMConferenceTypeBean lcmConferenceTypeBean : values) {
				confTypeModel.addElement(lcmConferenceTypeBean.getTypeName());
			}
			//会议默认角色
			DefaultComboBoxModel confRoleModel = (DefaultComboBoxModel) jDialog.getConfRoleComboBox().getModel();
			confRoleModel.removeAllElements();
			Map<Integer, LCMConferenceRoleBean> roleMap = (Map<Integer, LCMConferenceRoleBean>) confDefaultOptions.get("conference_role");
			Collection<LCMConferenceRoleBean> values2 = roleMap.values();
			for (LCMConferenceRoleBean lcmConferenceRoleBean : values2) {
				if(!lcmConferenceRoleBean.getRoleName().equals("主持人")){//TODO
					confRoleModel.addElement(lcmConferenceRoleBean.getRoleName());
				}
			}
			jDialog.getConferenceNameTextField().setText(selectedItem.getLcmConferenceDto().getConferenceName());
			jDialog.getConferencePasswordField().setText(PasswordUtil.getDesString(selectedItem.getLcmConferenceDto().getPassword()));
//			jDialog.getConferencePasswordField().setEnabled(false);
			jDialog.getConfenenceTopicTextField().setText(selectedItem.getLcmConferenceDto().getTopic());
			jDialog.getConferenceDescriptionTextArea().setText(selectedItem.getLcmConferenceDto().getDescription());
			jDialog.getConfTypeComboBox().setSelectedItem(selectedItem.getLcmConferenceDto().getConferenceTypeName());
			jDialog.getConfRoleComboBox().setSelectedItem(selectedItem.getLcmConferenceDto().getDefaultRoleName());
			Integer needApply = selectedItem.getLcmConferenceDto().getNeedApply();
			String stemp = (needApply == 1) ? Vovo.getViewManager().getUIString("ConferenceController.NeedApply.Yes") : Vovo.getViewManager().getUIString("ConferenceController.NeedApply.No");
			jDialog.getNeedApplyComboBox().setSelectedItem(stemp);
			jDialog.setTitle(Vovo.getViewManager().getUIString("ConferenceController.modifyConferenceDialog.title"));
			jDialog.setConferencePanel(panel);
			//各人角色
			List<MemberBean> memberBeans = TreeUtil.getMemberBeans();
			ArrayList<String> arrayList = new ArrayList<String>();
			for (MemberBean memberBean : memberBeans) {
				arrayList.add(memberBean.getLccAccount());
			}
			String[] lccAccounts = new String[memberBeans.size()];
			arrayList.toArray(lccAccounts);
			Map<String, LCMRoleDto> confUserRole = Vovo.getLcmUtil().getConfUserRole(selectedItem.getLcmConferenceDto().getConfNo(), lccAccounts);
			
			
			DefaultTableModel model = (DefaultTableModel) jDialog.getConfUserListTable().getModel();
			boolean role1 = false;
			boolean role2 = false;
			boolean role3 = false;
			for (MemberBean memberBean : memberBeans) {
				role1 = false;
				role2 = false;
				role3 = false;
				LCMRoleDto lcmRoleDto = confUserRole.get(memberBean.getLccAccount());
				List<String> names = lcmRoleDto.getNames();
				if (names.contains(Constants.ROLE_ZHUCHIREN)) {
					role1 = true;
				}
				if (names.contains(Constants.ROLE_ZHUJIANGREN)) {
					role2 = true;
				}
				if (names.contains(Constants.ROLE_PUTONGHUIYIZHE)) {
					role3 = true;
				}
				model.addRow(new Object[]{memberBean.getRealName()+" ("+memberBean.getLccAccount()+")",role1,role2,role3,memberBean.getId()+"",memberBean.getUsername()});
			}
			jDialog.setVisible(true);
		}
	}
	
	public void showConferenceCreateDialog(ConferencePanel panel) throws Exception{
		
		ConferenceEditDialog jDialog = new ConferenceEditDialog(null, true);
		jDialog.setOpt(Constants.VIEW_CONF_EDIT_CREATE);
		
		Map<String, Object> confDefaultOptions = Vovo.getLcmUtil().getConfDefaultOptions();
		//会议类型
		DefaultComboBoxModel confTypeModel = (DefaultComboBoxModel) jDialog.getConfTypeComboBox().getModel();
		confTypeModel.removeAllElements();
		Map<Integer, LCMConferenceTypeBean> typeMap = (Map<Integer, LCMConferenceTypeBean>) confDefaultOptions.get("conference_type");
		Collection<LCMConferenceTypeBean> values = typeMap.values();
		
		for (LCMConferenceTypeBean lcmConferenceTypeBean : values) {
			confTypeModel.addElement(lcmConferenceTypeBean.getTypeName());
		}
		//会议默认角色
		DefaultComboBoxModel confRoleModel = (DefaultComboBoxModel) jDialog.getConfRoleComboBox().getModel();
		confRoleModel.removeAllElements();
		Map<Integer, LCMConferenceRoleBean> roleMap = (Map<Integer, LCMConferenceRoleBean>) confDefaultOptions.get("conference_role");
		Collection<LCMConferenceRoleBean> values2 = roleMap.values();
		for (LCMConferenceRoleBean lcmConferenceRoleBean : values2) {
			if(!lcmConferenceRoleBean.getRoleName().equals("主持人")){//TODO
				confRoleModel.addElement(lcmConferenceRoleBean.getRoleName());				
			}
		}
		jDialog.getConfRoleComboBox().setSelectedItem(Vovo.getViewManager().getUIString("ConferenceController.DefaultConfRole"));
		jDialog.getNeedApplyComboBox().setSelectedIndex(1);//否
		//角色详细权限
		DefaultTableModel model = (DefaultTableModel) jDialog.getConfUserListTable().getModel();
		List<MemberBean> memberBeans = TreeUtil.getMemberBeans();
		LoginInfo logginInfo = Vovo.getMyContext().getDataManager().getValue(Constants.DataKey.LOGGININFO.toString());
		for (MemberBean memberBean : memberBeans) {
			if (memberBean.getLccAccount().equals(logginInfo.getUsername())) {
				model.addRow(new Object[]{memberBean.getRealName()+" ("+memberBean.getLccAccount()+")",Boolean.TRUE,Boolean.TRUE,Boolean.FALSE,memberBean.getId()+"",memberBean.getUsername()});
			}
			else{
				model.addRow(new Object[]{memberBean.getRealName()+" ("+memberBean.getLccAccount()+")",Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,memberBean.getId()+"",memberBean.getUsername()});
			}
		}
		
		jDialog.setTitle(Vovo.getViewManager().getUIString("ConferenceController.createConferenceDialog.title"));
		context.getViewManager().setWindowCenterLocation(jDialog);
		jDialog.setConferencePanel(panel);
		jDialog.setVisible(true);
	}
	
	public boolean checkStrLength(String str,int len){
		if(str.length()>len){
			return true;
		}else{
			return false;
		}
	} 
	
	public void doCreateOrModifyConference(ConferenceEditDialog dialog) throws Exception{
//		ConferenceCreatePanel dialog = context.getViewManager().getView(Constants.VIEW_CONFERENCE_CREATEPANEL);
		String confName = dialog.getConferenceNameTextField().getText().trim();
		String confPsw = dialog.getConferencePasswordField().getText().trim();
		String confTopic = dialog.getConfenenceTopicTextField().getText().trim();
		String confDescription = dialog.getConferenceDescriptionTextArea().getText().trim();
		int confNameLen = Constants.CONFERENCE_NAME_LENGTH;
		int confPswLen = Constants.CONFERENCE_PASSWD_LENGTH;
		int confTopicLen = Constants.CONFERENCE_SUBJECT_LENGTH;
		int confDescriptionLen = Constants.CONFERENCE_DESCRIPTION_LENGTH;
		if(checkStrLength(confName,confNameLen)){
			this.showErrorDialog(null, VovoStringUtil.getFormatString(VovoStringUtil.getErrorString("content.length.error"), VovoStringUtil.getUIString("conference.name"),confNameLen));
			return;
		}
		if(checkStrLength(confPsw,confPswLen)){
			this.showErrorDialog(null, VovoStringUtil.getFormatString(VovoStringUtil.getErrorString("content.length.error"), VovoStringUtil.getUIString("conference.passwd"),confPswLen));
			return;
		}
		if(checkStrLength(confTopic,confTopicLen)){
			this.showErrorDialog(null, VovoStringUtil.getFormatString(VovoStringUtil.getErrorString("content.length.error"), VovoStringUtil.getUIString("conference.subject"),confTopicLen));
			return;
		}
		if(checkStrLength(confDescription,confDescriptionLen)){
			this.showErrorDialog(null, VovoStringUtil.getFormatString(VovoStringUtil.getErrorString("content.length.error"), VovoStringUtil.getUIString("conference.description"),confDescriptionLen));
			return;
		}
		Pattern pattern = Pattern.compile(Constants.ConfSpecChatStrRegEx);
		Matcher matcher = pattern.matcher(confName);
		if (matcher.find()) {
			this.showErrorDialog(null, VovoStringUtil.getErrorString("conference.confname.regexerror"));
			return;
		}
		Integer defaultRoleId = -1;
		Integer confTypeId = -1;
		Integer needApply = -1;
		LoginInfo loginInfo = Vovo.getMyContext().getDataManager().getValue(Constants.DataKey.LOGGININFO.toString());
		Map<String, Object> confDefaultOptions = Vovo.getLcmUtil().getConfDefaultOptions();
		String confRoleName = (String) dialog.getConfRoleComboBox().getSelectedItem();
		Map<Integer, LCMConferenceRoleBean> mapRole = (Map<Integer, LCMConferenceRoleBean>) confDefaultOptions.get("conference_role");
		Collection<LCMConferenceRoleBean> roleValues = mapRole.values();
		for (LCMConferenceRoleBean lcmConferenceRoleBean : roleValues) {
			if (lcmConferenceRoleBean.getRoleName().equals(confRoleName)) {
				defaultRoleId = lcmConferenceRoleBean.getId();
				break;		
			}
		}
		Map<Integer, LCMConferenceTypeBean> mapType = (Map<Integer, LCMConferenceTypeBean>) confDefaultOptions.get("conference_type");
		String confTypeName = (String) dialog.getConfTypeComboBox().getSelectedItem();
		Collection<LCMConferenceTypeBean> typeValues = mapType.values();
		for (LCMConferenceTypeBean lcmConferenceTypeBean : typeValues) {
			if (lcmConferenceTypeBean.getTypeName().equals(confTypeName)) {
				confTypeId = lcmConferenceTypeBean.getId();
				break;
			}
		}
		String sNeedApply = (String) dialog.getNeedApplyComboBox().getSelectedItem();
		if (sNeedApply.equals(Vovo.getViewManager().getUIString("ConferenceController.NeedApply.Yes"))) {
			needApply = 1;
		}
		else{
			needApply = 0;
		}
		//各用户角色
		DefaultTableModel model = (DefaultTableModel) dialog.getConfUserListTable().getModel();
		int rowCount = model.getRowCount();
		ArrayList<String> urIds = new ArrayList<String>();
		Map<Integer, LCMConferenceRoleBean> roleMap = (Map<Integer, LCMConferenceRoleBean>) confDefaultOptions.get("conference_role");
		Collection<LCMConferenceRoleBean> values2 = roleMap.values();
		String role0_id = "";
		String role1_id = "";
		String role2_id = "";
		for (LCMConferenceRoleBean lcmConferenceRoleBean : values2) {
			if (lcmConferenceRoleBean.getRoleName().equals(Constants.ROLE_ZHUCHIREN)) {
				role0_id = lcmConferenceRoleBean.getId().toString();
			}
			else if (lcmConferenceRoleBean.getRoleName().equals(Constants.ROLE_ZHUJIANGREN)) {
				role1_id = lcmConferenceRoleBean.getId().toString();
			}
			else if (lcmConferenceRoleBean.getRoleName().equals(Constants.ROLE_PUTONGHUIYIZHE)){
				role2_id = lcmConferenceRoleBean.getId().toString();
			}
		}
		for (int i = 0; i < rowCount; i++) {
			Boolean isRole0 = (Boolean) model.getValueAt(i, 1);
			Boolean isRole1 = (Boolean) model.getValueAt(i, 2);
			Boolean isRole2 = (Boolean) model.getValueAt(i, 3);
			String userid = (String) model.getValueAt(i, 4);
			String username = (String) model.getValueAt(i, 5);
			
			if (isRole0) {
				String sTemp = role0_id+"_"+userid+"_"+username;
				urIds.add(sTemp);
			}
			if (isRole1) {
				String sTemp = role1_id+"_"+userid+"_"+username;
				urIds.add(sTemp);
			}
			if (isRole2) {
				String sTemp = role2_id+"_"+userid+"_"+username;
				urIds.add(sTemp);
			}
		}
		String[] urIdsArray  = new String[urIds.size()];
		urIds.toArray(urIdsArray);
		for (String sTemp : urIdsArray) {
			System.out.println(sTemp);
		}
		
		log.info(loginInfo.getUsername()+","+confName+","+confPsw+","+defaultRoleId+","+confTypeId+","+needApply+","+confTopic+","+confDescription);
		String confNo = null;
		if (dialog.getOpt() == Constants.VIEW_CONF_EDIT_CREATE) {
			
			try{
				//判断是否可以创建会议
				Vovo.getLcmUtil().canCreateConf(DataUtil.getUserName());
				confNo = Vovo.getLcmUtil().createConference(
						loginInfo.getUsername(), confName, confPsw, defaultRoleId, confTypeId, 
						needApply, confTopic, confDescription,urIdsArray);
				if (confNo != null && !confNo.equals("")) {
					dialog.dispose();
				}
			}catch(Exception e){
				String msg = e.getMessage().split(":")[1];
				throw new Exception(msg);
			}
		}
		else if(dialog.getOpt() == Constants.VIEW_CONF_EDIT_MODIFY){
			Integer confId = dialog.getConfId();
			if (confId != -1) {
				try{
					boolean modifyConference = Vovo.getLcmUtil().modifyConference(
							confId, loginInfo.getUsername(), dialog.getConfNo(), confName, confPsw, 
							defaultRoleId, confTypeId, needApply, confTopic, confDescription,urIdsArray);
					if (modifyConference) {
						dialog.dispose();
					}
				}catch(Exception e){
					String msg = e.getMessage().split(":")[1];
					throw new Exception(msg);
				}
			}
		}
		
		//会议通知
		List<String> membersUserName = dialog.getSelectedUserList().getMembersLccAccounts();
		String[] members = new String[membersUserName.size()];
		String[] array = membersUserName.toArray(members);
		Vovo.getLcmUtil().sendConferenceNotice(loginInfo.getUsername(),confNo, members, 
				dialog.getVovoNoticeCheckBox().isSelected(), 
				dialog.getSmsNoticeCheckBox().isSelected(), 
				dialog.getEmailNoticeCheckBox().isSelected());
		getConferenceList(dialog.getConferencePanel());
		dialog.dispose();
		
	}
	
	public void doDeleteConference(ConferenceListItem selectedItem,ConferencePanel panel) throws Exception{
		if (selectedItem == null) {
//			JOptionPane.showMessageDialog(null, Vovo.getMyContext().getViewManager().getUIString("ConferenceController.delConfCanNotNull"));
			return;
		}
		if(DataUtil.getMyInfo().getId() != selectedItem.getLcmConferenceDto().getCreator()){//not conf creator
			this.showErrorDialog(getUIString("common.error"), getUIString("conference.notConfCreator"));
			return;
		}
		int showConfirmDialog = JOptionPane.showConfirmDialog(null, 
				Vovo.getViewManager().getFormatString("ConferenceController.delConfConfirmText", selectedItem.getLcmConferenceDto().getConferenceName(),selectedItem.getLcmConferenceDto().getConfNo()),
				Vovo.getViewManager().getUIString("ConferenceController.delConfConfirmTitle"),JOptionPane.YES_NO_OPTION);
		if (showConfirmDialog == JOptionPane.YES_OPTION) {
			if (selectedItem != null) {
				LCMConferenceDto lcmConferenceDto = selectedItem.getLcmConferenceDto();
				LoginInfo loginInfo = Vovo.getMyContext().getDataManager().getValue(Constants.DataKey.LOGGININFO.toString());
				Vovo.getLcmUtil().deleteConference(loginInfo.getUsername(), lcmConferenceDto.getConfNo());
			}
			getConferenceList(panel);
		}
	}
	
	public void showConferenceBubbleDialog(String confNo,String msgText) throws Exception{
		log.info("showConferenceBubbleDialog "+confNo+","+msgText);
		ConferenceBubbleDialog bubbleDialog = new ConferenceBubbleDialog(null, false);
		
		bubbleDialog.getMsgTextPane().setText(msgText);
		JTextPane msgTextPane = bubbleDialog.getMsgTextPane();
		bubbleDialog.setTitle(Vovo.getViewManager().getFormatString("ConferenceController.bubbleDialogTitle", confNo));
		bubbleDialog.setVisible(true);
		Vovo.getMyContext().getViewManager().setWindowRightButtomLocation(bubbleDialog);
	}
	
	public void showJoinConfConfirmDialog(ConferenceListItem item) throws Exception{
		//判断是否有通话正在进行
		JoinConfConfirmDialog joinConfConfirmDialog = new JoinConfConfirmDialog(null, true);
		joinConfConfirmDialog.setSelectedItem(item);
		Vovo.getMyContext().getViewManager().setWindowCenterLocation(joinConfConfirmDialog);
		joinConfConfirmDialog.getTextLabel().setText(Vovo.getViewManager().getFormatString("ConferenceController.joinConfConfirmText", item.getLcmConferenceDto().getConferenceName(),item.getLcmConferenceDto().getConfNo()));
		joinConfConfirmDialog.setTitle(Vovo.getViewManager().getUIString("ConferenceController.joinConfConfirmTitle"));
		String password = item.getLcmConferenceDto().getPassword();
		if (password == null || password.equals("")) {
			joinConfConfirmDialog.getConfPasswordField().setText("");
			joinConfConfirmDialog.getConfPasswordField().setEnabled(false);
		}
		joinConfConfirmDialog.setVisible(true);
	}
	
	public void joinConference(ConferenceListItem item,String psw) throws Exception{
		if (item != null) {
			String confNo = item.getLcmConferenceDto().getConfNo();
			Map<String, LCMConferenceDto> confList = Vovo.getLcmUtil().getConfList();
			LCMConferenceDto lcmConferenceDto = confList.get(confNo);
			if (lcmConferenceDto != null) {
				if (lcmConferenceDto.getPassword().equals(PasswordUtil.getEncString(psw))) {
					LoginInfo logginInfo = Vovo.getMyContext().getDataManager().getValue(Constants.DataKey.LOGGININFO.toString());
					Object[] argss = new Object[]{
							item.getLcmConferenceDto().getConferenceName(),
							item.getLcmConferenceDto().getConfNo(),
							logginInfo.getUsername(),
							logginInfo.getPassWord(),
		    				logginInfo.getServerIP()
		    		};
					if (Launcher.isStartedFromOutSide) {
						JOptionPane.showMessageDialog(null, Vovo.getViewManager().getUIString("ConferenceController.OnlyOneConference"));
						return;
					}
					if (!LCCUtil.canCall()){
						JOptionPane.showMessageDialog(null, Vovo.getViewManager().getUIString("phone.alreadyHadOneCall"));
						return;
					}
					confCanEnter(item.getLcmConferenceDto().getConfNo());
					LCCUtil.getInstance().removeAllListener();
					Launcher.setEventListener(new LvmcEventListener());
					Launcher.startLvmcFromOutSide(argss, com.lorent.lvmc.util.Constants.AppName.VOVO, false);
				}
				else{
					JOptionPane.showMessageDialog(null, Vovo.getViewManager().getUIString("ConferenceController.ConferencePswWrong"));
				}
			}
			else{
				JOptionPane.showMessageDialog(null, Vovo.getViewManager().getUIString("ConferenceController.CannotFindConference"));
			}
		}
	}
	
	private void confCanEnter(String confno)throws Exception{
		int maxnum = Vovo.getLcmUtil().getConfUserNum();
		int occupants = OpenfireUtil.getInstance().getMUCOccupants(confno);
		if(occupants < maxnum){
			;
		}else{
			throw new Exception(context.getViewManager().getUIString("conference.isMaxConfUser"));
		}
	}
	
	private class LvmcEventListener implements EventListener{

		@Override
		public void stopLvmc() {
			afterLvmcClose();
		}
		
	}
	
	private void afterLvmcClose(){
		try {
			context.getExecuteManager().executeService("login", "initLCCUtil");
		} catch (Exception e) {
			log.error("afterLvmcClose", e);
		}
	}
	
	public void showConferenceDescDialog(ConferenceListItem item) throws Exception{
		ConferenceDescShowDialog dialog = new ConferenceDescShowDialog(null,true);
		dialog.setContent(item.getLcmConferenceDto().getDescription());
		dialog.setTitle(item.getLcmConferenceDto().getConferenceName() + "描述");
		context.getViewManager().setWindowCenterLocation(dialog);
		dialog.setVisible(true);
	}
}
