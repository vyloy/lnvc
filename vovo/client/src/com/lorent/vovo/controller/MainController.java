package com.lorent.vovo.controller;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;

import com.jniwrapper.win32.registry.RegistryKey;
import com.jniwrapper.win32.registry.RegistryKeyType;
import com.jniwrapper.win32.registry.RegistryKeyValues;
import com.jniwrapper.win32.registry.RegistryValueTransformer;
import com.lorent.common.controller.BaseController;
import com.lorent.common.dto.VovoMyInfo;
import com.lorent.common.tree.BroadcastEvent;
import com.lorent.common.tree.MemberBean;
import com.lorent.common.util.FileUtil;
import com.lorent.common.util.LCMUtil;
import com.lorent.common.util.NetworkUtil;
import com.lorent.common.util.OpenfireUtil;
import com.lorent.common.util.StringUtil;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.dto.LoginInfo;
import com.lorent.vovo.ui.AboutDialog;
import com.lorent.vovo.ui.ChangeHeadImgDialog;
import com.lorent.vovo.ui.ConferencePanel;
import com.lorent.vovo.ui.FriendChatPanel;
import com.lorent.vovo.ui.GroupListPanel;
import com.lorent.vovo.ui.LoginFrame;
import com.lorent.vovo.ui.MainFrame;
import com.lorent.vovo.ui.MessageTabPanel;
import com.lorent.vovo.ui.MyTrayIcon;
import com.lorent.vovo.ui.OrganListPanel;
import com.lorent.vovo.ui.PhoneFrame;
import com.lorent.vovo.ui.ShareFileListPanel;
import com.lorent.vovo.ui.UserSettingDialog;
import com.lorent.vovo.ui.VideoClipPanel;
import com.lorent.vovo.util.CallHistory;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.DataUtil;
import com.lorent.vovo.util.MyLoginSessionUtil;
import com.lorent.vovo.util.MyOpenfireUtil;
import com.lorent.vovo.util.PrivateDataUtil;
import com.lorent.vovo.util.TreeUtil;
import com.lorent.vovo.util.UserInfoUtil;
import com.lorent.vovo.util.VovoStringUtil;

public class MainController extends BaseController {
	
	private static Logger log = Logger.getLogger(MainController.class);
	
	public void showLogin()throws Exception{
		if(doCheckSoundCard()<1){
			JOptionPane.showMessageDialog(null, context.getViewManager().getUIString("maincontroller.showLogin.soundcard.error"), context.getViewManager().getUIString("info.tip"), JOptionPane.INFORMATION_MESSAGE);
        }
		log.info("显示trayicon");
		MyTrayIcon trayIcon = context.getViewManager().createView(MyTrayIcon.class, Constants.ViewKey.TRAYICON.toString());
		trayIcon.showLoginMenu();
		
		boolean restart = context.getConfigManager().getBoolProperty("restart", false);
		if(restart){
			context.getConfigManager().setProperty("restart", false + "");
			int type = context.getConfigManager().getIntProperty("restartType", Constants.RESTART_TYPE_LOGOUT);
			handleRestart(type);
		}
		
//		String username = ConfigUtil.getProperty("username", "");
		String user_name = UserInfoUtil.get("username_", "");
		LoginInfo info = UserInfoUtil.getInfo(user_name);
		if (info != null && info.isAutoLogin() && !restart) {
			try{
				doLogin(info.getUsername(), info.getPassWord(), info.getServerIP(), info.getStatus());
			}catch(InvocationTargetException e){
				this.showErrorDialog(null, e.getTargetException().getMessage());
				info.setAutoLogin(false);
				UserInfoUtil.setInfo(info);
				restartApplication(Constants.RESTART_TYPE_LOGOUT);
			}
		}
		else{
			log.info("显示登录界面");
			LoginFrame loginFrame = context.getViewManager().createView(LoginFrame.class, Constants.ViewKey.LOGIN.toString());
			String username = UserInfoUtil.get("username_", "");
			List<String> allUserNames = UserInfoUtil.getAllUserNames();
			allUserNames.remove(username);
			allUserNames.add(0, username);
			String[] temp = new String[allUserNames.size()];
			loginFrame.setUserNames(allUserNames.toArray(temp));
			loginFrameChangeUsername(username);
			loginFrame.setVisible(true);
		}
	}
	
	private void handleRestart(int type){
		if(Constants.RESTART_TYPE_RELOGIN == type){
			this.showMessageDialog(getUIString("info.tip"), getUIString("relogin.info"));
		}
	}
	
	private void doLogin(String username,String password,String serverIP,int status) throws Exception{
		context.getDataManager().setValue(Constants.DataKey.LOGGININFO.toString(), UserInfoUtil.getInfo(username));
		context.getExecuteManager().executeService("login", "doLogin",username,password,serverIP, status);
		context.getExecuteManager().executeController("sharefile", "setFtpConfig", "admin","1234",serverIP,2121);
		
		try {
			String addcounthttpurl = "http://"+serverIP+":6090/videoserver/video.jsp";
			MyLoginSessionUtil.downLoadPages(addcounthttpurl, true);
		} catch (Exception e) {
			log.error("MainController.doLogin", e);
		}
		
		MyTrayIcon trayIcon = context.getViewManager().getView(Constants.ViewKey.TRAYICON.toString());
		trayIcon.showMainMenu();
		showMainFrame(status, username);
		
		MemberBean bean = new MemberBean();
		bean.setLccAccount(username);
		String[] allLocalHostIP = NetworkUtil.getAllLocalHostIP();
		for (int i = 0; i < allLocalHostIP.length; i++) {
			log.info(i+" : "+allLocalHostIP[i]);
		}
		int networkinterfaceIndex = Vovo.getConfigManager().getIntProperty("networkinterfaceIndex", 0);
		bean.setIp(allLocalHostIP[networkinterfaceIndex]);
		Vovo.getLcmUtil().broadcastMyIpAddress(bean,username,"");
	}
	
	private void showMainFrame(int status, String lccno)throws Exception{
		PhoneFrame phone = context.getViewManager().createView(PhoneFrame.class, Constants.ViewKey.PhoneFrame.toString());
		
		MainFrame mainframe = context.getViewManager().createView(MainFrame.class, Constants.ViewKey.MAINFRAME.toString());
		OrganListPanel organListPanel = context.getViewManager().createView(OrganListPanel.class, Constants.ViewKey.ORGAN_LIST.toString());		
		mainframe.getMainTab().add(getUIString("mainTab.organlist"), organListPanel);
		GroupListPanel groupListPanel = context.getViewManager().createView(GroupListPanel.class, Constants.ViewKey.GroupListPanel.toString());
		mainframe.getMainTab().add(getUIString("mainTab.groupListPanel"), groupListPanel);
		mainframe.getStatusCombobox().setSelectStatus(status);
		ConferencePanel conferencePanel = Vovo.getMyContext().getViewManager().createView(ConferencePanel.class, Constants.ViewKey.CONFERENCEPANEL.toString());
		mainframe.getMainTab().add(getUIString("mainTab.conferencePanel"), conferencePanel);
		ShareFileListPanel shareFileListPanel = context.getViewManager().createView(ShareFileListPanel.class, Constants.ViewKey.SHARE_FILE_LIST.toString());		
		shareFileListPanel.setSessionID("Files");
		shareFileListPanel.setParentFtpPath("CommonFilePath");
		mainframe.getMainTab().add(getUIString("mainTab.shareFileList"), shareFileListPanel);
		VideoClipPanel videoClipPanel = Vovo.getMyContext().getViewManager().createView(VideoClipPanel.class, Constants.ViewKey.VIDEOCLIPPANEL.toString());
		mainframe.getMainTab().add(getUIString("mainTab.videoclippanel"), videoClipPanel);
		//set my info
		VovoMyInfo info = Vovo.getLcmUtil().getVovoMyInfo(lccno);
		mainframe.setMyInfo(info);
		mainframe.setVisible(true);
		DataUtil.setValue(Constants.DataKey.MYINFO, info);
		Vovo.exeC("groupChat", "getGroupChatJid");
		Vovo.exeC("conference", "getConferenceList", conferencePanel);
		Vovo.exeC("sharefile", "getDefaultFileList", shareFileListPanel);
		Vovo.exeC("videoclip", "reflashMonitorPanel");
		Vovo.exeC("videoclip", "reflashAllVideoClipPanel");
		PrivateDataUtil.setLoginTime(new Date());
	}
	
	
	public void doLogin(LoginFrame frame) throws Exception{
		Object selectedItem = frame.getUsernameCb().getSelectedItem();
		String username = (String)selectedItem;
		int serverPort = context.getConfigManager().getIntProperty("serverPort", 5060);
		String password = frame.getPasswdIt().getText();
		String serverIP = frame.getServerIPIt().getText();
		boolean autoLogin = frame.getAutoLoginCb().isSelected();
		boolean savePasswd = frame.getSavePasswdCb().isSelected();
		int status = frame.getStatusCombobox().getSelectStatus();
		
		log.info("doLogin: "+selectedItem+","+serverPort+","+password+","+serverIP+","+status);
		UserInfoUtil.setInfo(username, password, autoLogin, savePasswd, serverIP, status);
		doLogin(username,password,serverIP, status);
		UserInfoUtil.setProperty("username_", username);
		frame.dispose();
		
	}
	
	public void doLogout() throws Exception{
		int ret = this.showConfirmDialog(getUIString("info.tip"), getUIString("logout.confirmInfo"));
		if(ret != JOptionPane.OK_OPTION){
			return;
		}
		LoginInfo info = context.getDataManager().getValue(Constants.DataKey.LOGGININFO.toString());
//		LoginInfo info = UserInfoUtil.getInfo(username);
		if (info.isAutoLogin()) {
			info.setAutoLogin(false);
			UserInfoUtil.setInfo(info.getUsername(), info.getPassWord(), info.isAutoLogin(), info.isSavePasswd(), info.getServerIP(), info.getStatus());
		}
		restartApplication(Constants.RESTART_TYPE_LOGOUT);
	}
	
	public void recieveMessage(String msg){
		System.out.println("recieveMessage:" + msg);
	}
	
	public int doCheckSoundCard() throws Exception{
		
        return (Integer) context.getExecuteManager().executeService("login", "doCheckSoundCard");
    }
	
	public void loginFrameChangeUsername(String username) throws Exception{
    	LoginInfo info = UserInfoUtil.getInfo(username);
    	if(info != null){
    		
    		LoginFrame frame = context.getViewManager().getView(Constants.ViewKey.LOGIN.toString());
    		if(info.isSavePasswd() || info.isAutoLogin()){
    			frame.changeInfo(info.getPassWord(), info.isAutoLogin(), info.isSavePasswd(),info.getServerIP(), info.getStatus());
    		}else{
    			frame.changeInfo("", info.isAutoLogin(), info.isSavePasswd(),info.getServerIP(), info.getStatus());
    		}
    	}
    }
	
	public void exitApplication() throws Exception{
		try{
			PrivateDataUtil.setLogoutTime(new Date());
			CallHistory.getInstance().close();
		}catch(Exception e){
			;//do noting
		}

		System.exit(0);
	}
	
	public void restartApplication(int type) throws Exception{
		log.info("重启程序");
		context.getConfigManager().setProperty("restart", true + "");
		context.getConfigManager().setProperty("restartType", type + "");
        String path = System.getProperty("user.dir");
//        Runtime.getRuntime().exec("cmd /c " + StringUtil.convertFilePath2DOSCommandStr(path) + "/startvovo.bat");
        String cmd = "cmd /c " + StringUtil.convertFilePath2DOSCommandStr(path + "/VoVo.exe");
        log.info("restartApplication : " + cmd);
        Runtime.getRuntime().exec(cmd);
        Thread.sleep(500);
        exitApplication();
	}
	
	public void showAboutDialog()throws Exception{
		AboutDialog view = context.getViewManager().getView(Constants.ViewKey.ABOUTDIALOG.toString());
		if (view == null) {
			view = context.getViewManager().createView(AboutDialog.class, Constants.ViewKey.ABOUTDIALOG.toString());
		}
		view.setLocationRelativeTo(null);
		view.setVisible(true);
	}
	
	public void changePresence(int status){
		Vovo.exeC("organ", "userStatusChange", DataUtil.getUserName(),status);
		MyOpenfireUtil.changeMyPresence(status);
	}
	
	public void showUserSettingDialog() throws Exception{
		UserSettingDialog userSettingDialog = new UserSettingDialog(null, false);
		Vovo.getViewManager().setWindowCenterLocation(userSettingDialog);
		LoginInfo loginInfo = Vovo.getMyContext().getDataManager().getValue(Constants.DataKey.LOGGININFO.toString());
		VovoMyInfo vovoMyInfo = Vovo.getLcmUtil().getVovoMyInfo(loginInfo.getUsername());
		userSettingDialog.setInfo(vovoMyInfo);
		userSettingDialog.setTitle(Vovo.getMyContext().getViewManager().getUIString("UserSettingDialog.title"));
		userSettingDialog.setVisible(true);
	}
	
	public void doEditUserSetting(UserSettingDialog dialog) throws Exception{
		String realName = dialog.getRealNameTextField().getText();
		if (realName.length() > 30) {
			JOptionPane.showMessageDialog(dialog, Vovo.getMyContext().getViewManager().getUIString("UserSettingDialog.tips.realNameToLong"));
			return;
		}
		String post = dialog.getPostTextField().getText();
		if (post.length() > 50) {
			JOptionPane.showMessageDialog(dialog, Vovo.getMyContext().getViewManager().getUIString("UserSettingDialog.tips.postToLong"));
			return;
		}
		String mobile = dialog.getPhoneTextField().getText();
		if (mobile.length() >= 13 || !mobile.matches("\\d*")) {
			JOptionPane.showMessageDialog(dialog, Vovo.getMyContext().getViewManager().getUIString("UserSettingDialog.tips.mobileToLong"));
			return;
		}
		String email = dialog.getEmailTextField().getText();
		if (email.length() > 50 || (!email.equals("") && !email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"))) {
			JOptionPane.showMessageDialog(dialog, Vovo.getMyContext().getViewManager().getUIString("UserSettingDialog.tips.emailToLong"));
			return;
		}
		String sign = dialog.getSignTextArea().getText();
		if (sign.length() > 200) {
			JOptionPane.showMessageDialog(dialog, Vovo.getMyContext().getViewManager().getUIString("UserSettingDialog.tips.signToLong"));
			return;
		}
		LoginInfo loginInfo = Vovo.getMyContext().getDataManager().getValue(Constants.DataKey.LOGGININFO.toString());
		if (dialog.getOldPasswordField().getText() != null && !dialog.getOldPasswordField().getText().equals("")) {
			if (dialog.getNewPasswordField().getText() == null || dialog.getNewPasswordField().getText().equals("")) {
				JOptionPane.showMessageDialog(dialog, Vovo.getMyContext().getViewManager().getUIString("UserSettingDialog.tips.insertPsw"));
				return;
			}
			String newPassWord = dialog.getNewPasswordField().getText();
			String confirmPassWord = dialog.getConfirmPasswordField().getText();
			if (!newPassWord.equals(confirmPassWord)) {
				JOptionPane.showMessageDialog(dialog, Vovo.getMyContext().getViewManager().getUIString("UserSettingDialog.tips.differentPsw"));
				return;
			}
			else{
				Vovo.getLcmUtil().changePassWord(loginInfo.getUsername(), dialog.getOldPasswordField().getText(), newPassWord, confirmPassWord);
				log.info("更改密码");
			}
		}
		VovoMyInfo vovoMyInfo = Vovo.getLcmUtil().getVovoMyInfo(loginInfo.getUsername());
		vovoMyInfo.setRealName(realName);
		String gender = (dialog.getSexComboBox().getSelectedIndex() == 0)? "male" :"female";
		vovoMyInfo.setGender(gender);
		vovoMyInfo.setPost(post);
		vovoMyInfo.setMobile(mobile);
		vovoMyInfo.setEmail(email);
		vovoMyInfo.setSign(sign);
		Vovo.getLcmUtil().setVovoMyInfo(vovoMyInfo);
		
		dialog.dispose();
	}
	
	public void dataIsReady(){
		MyOpenfireUtil.doAfterDataIsReady();
	}
	
	public void receiveBroadcastIP(String operate,MemberBean bean,String from,String to){
		if (operate != null && operate.equals(BroadcastEvent.BROADCAST_MY_IPADDRESS)) {
			LoginInfo loginInfo = Vovo.getMyContext().getDataManager().getValue(Constants.DataKey.LOGGININFO.toString());
			if (to == null || to.equals("") || to.equals(loginInfo.getUsername())) {
				if (!loginInfo.getUsername().equals(bean.getLccAccount())) {
					MemberBean memberBeanByLccno = TreeUtil.getMemberBeanByLccno(bean.getLccAccount());
					if (memberBeanByLccno != null) {
						memberBeanByLccno.setIp(bean.getIp());
						TreeUtil.reflashMemberNode(memberBeanByLccno);
						System.out.println("receiveBroadcastIP "+bean.getLccAccount()+"  "+bean.getIp());
						//更新已打开的msgFrame
						MessageTabPanel tabPanel = context.getViewManager().getView(Constants.ViewKey.MESSAGETABPANEL.toString());
						if (tabPanel != null) {
							FriendChatPanel panel = (FriendChatPanel) Vovo.exeC("chat", "getFriendChatPanel", memberBeanByLccno);
							if (panel != null) {
								panel.setInfo(memberBeanByLccno);
							}
						}
					}
				}
			}
		}
	}
	
	public void confirmExit()throws Exception{
		int i = this.showConfirmDialog(null, "你是否要退出VOVO?");
		if(i == JOptionPane.OK_OPTION){
			exitApplication();
		}
	}
	
	public void relogin()throws Exception{
		restartApplication(Constants.RESTART_TYPE_RELOGIN);
	}
	
	public void getStartUpAtSystem(JCheckBoxMenuItem menuItem) throws Exception{
		log.info("获得是否开机启动 ");
		RegistryKeyValues.registerAssociation(RegistryKeyType.EXPAND_SZ, RegistryValueTransformer.STRING_EXPAND_TRANSFORMER);
		//SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders
		//"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run"
        RegistryKey CLSID = RegistryKey.LOCAL_MACHINE.openSubKey("SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders", false);
        RegistryKeyValues values = CLSID.values();
        String startuppath = (String) values.get("Common Startup");
        CLSID.close();
        log.info("键值："+startuppath);
        if (startuppath == null || startuppath.equals("")) {
        	menuItem.setSelected(false);
        	menuItem.setEnabled(false);
		}
        else{
        	File file = new File(startuppath+"\\vovo.lnk");
        	if (file.exists()) {
        		menuItem.setSelected(true);
			}
        	else{
        		menuItem.setSelected(false);
        	}
        	menuItem.setEnabled(true);
        }
	}
	
	public void setStartUpAtSystem(JCheckBoxMenuItem menuItem) throws Exception{
		log.info("设置开机启动: "+menuItem.isSelected());
		boolean selected = menuItem.isSelected();
		RegistryKeyValues.registerAssociation(RegistryKeyType.EXPAND_SZ, RegistryValueTransformer.STRING_EXPAND_TRANSFORMER);
        RegistryKey CLSID = RegistryKey.LOCAL_MACHINE.openSubKey("SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders", false);
        RegistryKeyValues values = CLSID.values();
        String startuppath = (String) values.get("Common Startup");
        CLSID.close();
        log.info("键值："+startuppath);
        try {
        	if (startuppath != null || !startuppath.equals("")) {
        		if (selected) {
        			//复制
        			//判断lnk是否存在
        			String lnkFilePath = Constants.USER_DIR+"\\vovo.lnk";
        			File file = new File(lnkFilePath);
        			if (file.exists()) {
        				FileUtil.localFileCopy(lnkFilePath, startuppath+"\\vovo.lnk");
					}
        			else{
        				showErrorDialog(VovoStringUtil.getErrorString("error.normal.tip"), StringUtil.getFormatString(VovoStringUtil.getErrorString("error.cantnotfindlnk"), lnkFilePath));
        			}
        		}
                else{
                	//删除
            		File file = new File(startuppath+"\\vovo.lnk");
            		file.delete();
                }	
        	}
        	else{
        		menuItem.setSelected(!selected);
        	}
		} catch (Exception e) {
			menuItem.setSelected(!selected);
			throw e;
		}
	}
	
	public void showChangeHeadImgDialog(UserSettingDialog settingDialog){
		ChangeHeadImgDialog dialog = new ChangeHeadImgDialog((MainFrame)Vovo.getViewManager().getView(Constants.ViewKey.MAINFRAME.toString()),true,settingDialog);
		try {
			Vovo.getViewManager().setWindowCenterLocation(dialog);
		} catch (Exception e) {
			
		}
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}
	
	public void changeHeadImg(String imgName) throws Exception{
		VovoMyInfo vovoMyInfo = Vovo.getLcmUtil().getVovoMyInfo(DataUtil.getUserName());
		vovoMyInfo.setDefaultImg(imgName);
		Vovo.getLcmUtil().setVovoMyInfo(vovoMyInfo);
	}
	
	public void serverDisconnect(){
//		JOptionPane.showMessageDialog(null, "serverDisconnect");
		synchronized (OpenfireUtil.getInstance().isLogined) {
			OpenfireUtil.getInstance().isLogined = false;
		}
	}
	
	
	public void reconnectionSuccessful(){
//		JOptionPane.showMessageDialog(null, "reconnectionSuccessful");
		synchronized (OpenfireUtil.getInstance().isLogined) {
			OpenfireUtil.getInstance().isLogined = true;
		}
	}
}
