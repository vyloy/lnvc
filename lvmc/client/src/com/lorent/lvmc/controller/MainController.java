/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.controller;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.apache.log4j.Logger;
import org.jhotdraw.samples.svg.SVGPanels;
import org.jivesoftware.smack.packet.Presence;

import com.lorent.common.util.LCMUtil;
import com.lorent.common.util.ParaUtil;
import com.lorent.common.util.PlatformUtil;
import com.lorent.lvmc.Launcher;
import com.lorent.lvmc.bean.ChatComboxMemberModel;
import com.lorent.lvmc.dto.LoginInfo;
import com.lorent.lvmc.dto.MemberDto;
import com.lorent.lvmc.event.AuthorityCheckListener;
import com.lorent.lvmc.service.FetchMemberInfoService;
import com.lorent.lvmc.ui.AboutDialog;
import com.lorent.lvmc.ui.ChatMainPanel;
import com.lorent.lvmc.ui.DockingLayoutMeetingPanel;
import com.lorent.lvmc.ui.InviteDialog;
import com.lorent.lvmc.ui.LoginFrame;
import com.lorent.lvmc.ui.MainFrame;
import com.lorent.lvmc.ui.MainPanel;
import com.lorent.lvmc.ui.MainWindow;
import com.lorent.lvmc.ui.MemberListPanel;
import com.lorent.lvmc.ui.MyTrayIcon;
import com.lorent.lvmc.ui.ShareFileListPanel;
import com.lorent.lvmc.ui.VideoViewsPanel;
import com.lorent.lvmc.ui.WhiteBoardPanel;
import com.lorent.lvmc.ui.voteMainPanel;
import com.lorent.lvmc.util.ConfigUtil;
import com.lorent.lvmc.util.Constants;
import com.lorent.lvmc.util.DataUtil;
import com.lorent.lvmc.util.LvmcOpenfireUtil;
import com.lorent.lvmc.util.LvmcUtil;
import com.lorent.lvmc.util.PermissionUtil;
import com.lorent.lvmc.util.ProcessUtil;
import com.lorent.lvmc.util.StringUtil;
import com.lorent.lvmc.util.UserInfoUtil;
import com.lorent.lvmc.util.DataUtil.Key;
import com.lorent.util.LCCUtil;

/**
 *
 * @author jack
 */
public class MainController extends BaseController{
    private Logger log = Logger.getLogger(MainController.class);
    private static boolean disconnectFlag = true;
    
    public void doLogoutFromOutSide() throws Exception{
		Launcher.isStartedFromOutSide = false;
		ControllerFacade.execute("shareDesktopController", "stopScreenShare");
		
    	MainFrame mainFrame = DataUtil.getValue(DataUtil.Key.TopWindow);
    	mainFrame.dispose();
//    	MyTrayIcon trayicon = ViewManager.getComponent(MyTrayIcon.class);
//    	trayicon.removeTrayIcon();
        services.getLoginService().doLogoutFromOutSide();
        ControllerFacade.execute("phoneController", "stopLoopThread");
    	ViewManager.clearComponet();
    	ViewManager.clearView();
    	DataUtil.setValue(DataUtil.Key.TopWindow, null);
//    	services.getScreenShareService().unInitScreenShareService();
//    	DataUtil.setValue(DataUtil.Key.LoginInfo, null);
//    	DataUtil.clear();
    }
    
    public void doLoginFromOutSide(String confName,String confNo,String username,String password,String serverip)throws Exception{

    	try {
    		services.getLoginService().doLoginFromOutSide(username,password,confNo,serverip);			
		} catch (Exception e) {
			Launcher.isStartedFromOutSide = false;
			throw e;
		}
    	final LoginInfo loginInfo=DataUtil.getValue(DataUtil.Key.LoginInfo);
    	DataUtil.setValue(DataUtil.Key.showExitMenuItem, Boolean.FALSE);
    	log.info("MainFrame create start");
        final MainFrame mainFrame = ViewManager.getComponent(MainFrame.class);
        log.info("MainFrame create end");
        DataUtil.setValue(DataUtil.Key.TopWindow, mainFrame);
        mainFrame.setTitle(StringUtil.getUIString("base.conference.title") + confName + " " + StringUtil.getUIString("base.confno") + confNo + " " 
            + StringUtil.getUIString("base.username") + username);
        mainFrame.getTitleLabel().setText(mainFrame.getTitle());
        mainFrame.setVisible(true);
        log.info("================show mainframe=============");
        SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
		        try {
//		        	Thread.sleep(2000);
		            log.info("initMainWindow start");
					initMainWindow(mainFrame, loginInfo);
			        log.info("initMainWindow end");
			        if (!PlatformUtil.isLocalSession()) {
						mainFrame.getShareDesktopButton().setEnabled(false);
						log.info("is not local windows session,can not use sharedesktop");
					}
			        else{
			        	log.info("is local windows session");
			        }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

    }
    
    public void doLogin(ParaUtil paras)throws Exception{
        boolean flag = services.getLoginService().doLogin(paras);
//        if(flag){
        	String username = paras.getValue("username");
        	String password = paras.getValue("password");
        	boolean autoLogin = paras.getValue("autoLogin");
        	boolean savePasswd = paras.getValue("savePasswd");
        	UserInfoUtil.setInfo(username, password, autoLogin, savePasswd);
            LoginInfo loginInfo=DataUtil.getValue(DataUtil.Key.LoginInfo);
            DataUtil.setValue(DataUtil.Key.showExitMenuItem, Boolean.TRUE);
            log.info("MainFrame create start");
            MainFrame mainFrame = ViewManager.getComponent(MainFrame.class);
            log.info("MainFrame create end");
            DataUtil.setValue(DataUtil.Key.TopWindow, mainFrame);
            mainFrame.setTitle(StringUtil.getUIString("base.conference.title") + loginInfo.getConferenceTitle() + " " + StringUtil.getUIString("base.confno") + paras.getValue("confno") + " " 
                + StringUtil.getUIString("base.username") + paras.getValue("username"));
            mainFrame.getTitleLabel().setText(mainFrame.getTitle());
            
            ViewManager.disposeComponent(LoginFrame.class);
            log.info("initMainWindow start");
            this.initMainWindow(mainFrame, loginInfo);
            log.info("initMainWindow end");
            if (!PlatformUtil.isLocalSession()) {
				mainFrame.getShareDesktopButton().setEnabled(false);
				log.info("is not local windows session,can not use sharedesktop");
			}
            else{
            	log.info("is local windows session");
            }
            MyTrayIcon trayicon = ViewManager.getComponent(MyTrayIcon.class);
            trayicon.showMainMenu();
//            ViewManager.changeTheme(ConfigUtil.getProperty("themeFileName"));
//            ViewManager.changeLookAndFeel(ConfigUtil.getProperty("DefaultLookAndFeelClassName"));
//            ViewManager.changeTheme(ConfigUtil.getProperty("DefaultThemeClassName"));
//            ViewManager.changeSkin(ConfigUtil.getProperty("SkinFileName"));
            log.info("================login end=============");
            mainFrame.setVisible(true);
//            ViewManager.changeLookAndFeel(ConfigUtil.getProperty("DefaultLookAndFeelClassName", "com.jtattoo.plaf.mcwin.McWinLookAndFeel"));
//        }else{
//            throw new Exception(StringUtil.getErrorString("login.fail"));
//        }
    }
    
    
    public JPanel getMainPanel(ParaUtil paras)throws Exception{
        boolean flag = services.getLoginService().doLogin(paras);
        MainPanel mainPanel = null;
//        if(flag){
            LoginInfo loginInfo=DataUtil.getValue(DataUtil.Key.LoginInfo);
            ViewManager.disposeComponent(DockingLayoutMeetingPanel.class);
            DataUtil.setValue(DataUtil.Key.showExitMenuItem, Boolean.FALSE);
            mainPanel = ViewManager.getComponent(MainPanel.class);
//            mainFrame.setTitle(StringUtil.getUIString("base.title") + " " 
//                + StringUtil.getUIString("base.confno") + paras.getValue("confno") + " " 
//                + StringUtil.getUIString("base.username") + paras.getValue("username"));
//            mainFrame.setVisible(true);
            this.initMainWindow(mainPanel, loginInfo);
//        }else{
//            throw new Exception(StringUtil.getErrorString("login.fail"));
//        }
        return mainPanel;
    }
    
    
    public void clearChatMainPanelCombox()throws Exception{
    	List<ChatComboxMemberModel> listperson=new ArrayList<ChatComboxMemberModel>();
        listperson.add(new ChatComboxMemberModel(null,"所有人"));
        ViewManager.getComponent(ChatMainPanel.class).initcombox(listperson.toArray(new ChatComboxMemberModel[listperson.size()]));
//      
        ViewManager.getComponent(ChatMainPanel.class).revalidate();
        ViewManager.getComponent(ChatMainPanel.class).repaint();
    }
    
    public void initChatMainPanelCombox() throws Exception{
    	List<MemberDto> members = services.getConfService().getOpenfireMemberList();
        List<ChatComboxMemberModel> listperson=new ArrayList<ChatComboxMemberModel>();
        listperson.add(new ChatComboxMemberModel(null,"所有人"));
        for (Iterator<MemberDto> it = members.iterator(); it.hasNext();) {
            MemberDto memberDto = it.next();
            if(memberDto.getName().equals(DataUtil.getLoginInfo().getUsername()))
                continue;
            if(LvmcUtil.isUCSAPP()){
            	listperson.add(new ChatComboxMemberModel(memberDto.getName(),memberDto.getName()));
            }else{
            	listperson.add(new ChatComboxMemberModel(memberDto.getName(),memberDto.getNickname()));
            }
        }
        ViewManager.getComponent(ChatMainPanel.class).initcombox(listperson.toArray(new ChatComboxMemberModel[listperson.size()]));
//      
//        ViewManager.getComponent(ChatMainPanel.class).revalidate();
//        ViewManager.getComponent(ChatMainPanel.class).repaint();
    }
    
    public void initMainWindow(MainWindow w,LoginInfo loginInfo) throws Exception{
//    	JPanel p = new JPanel();
//    	p.setOpaque(false);
//    	p.add(new javax.swing.JButton("aaa"),java.awt.BorderLayout.CENTER);
//    	w.getDockingLayoutMeetingPanel().addPanel(p, StringUtil.getUIString("MemberListPanel.title"), "memberListPanel", null);
            createMemberList();
            w.getDockingLayoutMeetingPanel().addPanel(ViewManager.getComponent(MemberListPanel.class), StringUtil.getUIString("MemberListPanel.title"), "memberListPanel", null,StringUtil.getUIString("MemberListPanel.img"));
            w.getDockingLayoutMeetingPanel().addPanel(ViewManager.getComponent(ChatMainPanel.class), StringUtil.getUIString("ChatMainPanel.title"), "chatMainPanel", null,StringUtil.getUIString("ChatMainPanel.img"));
            initChatMainPanelCombox();
//            List<MemberDto> members = getMemberList();
//            List<ChatComboxMemberModel> listperson=new ArrayList<ChatComboxMemberModel>();
//            listperson.add(new ChatComboxMemberModel(null,"所有人"));
//            for (Iterator<MemberDto> it = members.iterator(); it.hasNext();) {
//                MemberDto memberDto = it.next();
//                if(memberDto.getName().equals(loginInfo.getUsername()))
//                    continue;
//                listperson.add(new ChatComboxMemberModel(memberDto.getName(),memberDto.getNickname()));
//            }
//            ViewManager.getComponent(ChatMainPanel.class).initcombox(listperson.toArray(new ChatComboxMemberModel[listperson.size()]));
//            JPanel panel = new JPanel();
//            panel.add(new JScrollPane(new JTextArea("panel1")));
            WhiteBoardPanel whiteBoardPanel = ViewManager.getComponent(WhiteBoardPanel.class);
            w.getDockingLayoutMeetingPanel().addPanel(whiteBoardPanel, StringUtil.getUIString("SVGPanel.title"), "whiteborad", null,StringUtil.getUIString("SVGPanel.img"));
            if(!PermissionUtil.hasPermission(PermissionUtil.SCREEN_SHARE)){
//                w.getDockingLayoutMeetingPanel().getScreenShareMenu().setEnabled(false);
            	((MainFrame)w).getShareDesktopButton().setEnabled(false);
            }

            SVGPanels.newInstance(loginInfo.getServerIP(), loginInfo.getConfno(),whiteBoardPanel.getJTabbedPane1(), PermissionUtil.hasPermission(PermissionUtil.WHITE_BOARD));

//            SVGPanel svgPanel = ViewManager.getComponent(SVGPanel.class, new Class[]{String.class, String.class}, new Object[]{loginInfo.getServerIP(), loginInfo.getConfno()});
//            mainFrame.getDockingLayoutMeetingPanel().addPanel(svgPanel, StringUtil.getUIString("SVGPanel.title"), "whiteborad1", null);
            
            w.getDockingLayoutMeetingPanel().addPanel(ViewManager.getComponent(VideoViewsPanel.class),StringUtil.getUIString("VideoViewsPanel.title"), "videospanel1", null,StringUtil.getUIString("VideoViewsPanel.img"));
            ControllerFacade.execute("videoViewsController", "initVideo");
            w.getDockingLayoutMeetingPanel().addPanel(ViewManager.getComponent(voteMainPanel.class), StringUtil.getUIString("MeetingVote.title"), "meetingvotepanel1", null,StringUtil.getUIString("MeetingVote.img"));
            w.getDockingLayoutMeetingPanel().addDockingWindowAdapter(ViewManager.getComponent(VideoViewsPanel.class).getDockingWindowAdapter());
            w.getDockingLayoutMeetingPanel().addPanel(ViewManager.getComponent(ShareFileListPanel.class), StringUtil.getUIString("ShareFileList.title"), "filelistpanel1", null,StringUtil.getUIString("ShareFileList.img"));
//            w.getDockingLayoutMeetingPanel().addPanel(ViewManager.getComponent(VideoViewsPanelItem.class), StringUtil.getUIString("IndividualVideo.title"), "individualvideopanel", null);
//            w.getDockingLayoutMeetingPanel().addDockingWindowAdapter((DockingWindowAdapter)ControllerFacade.execute("videoViewsController", "getIndividualVideoWindowAdapter"));
//            VideoViewsPanelItem component = ViewManager.getComponent(VideoViewsPanelItem.class);
//            component.setLccUserName("");
//            component.setEnabled(false);
//            component.setNickName("");
//        component.getButtonPanel().setVisible(false);
            DataUtil.setValue(DataUtil.Key.DockingLayoutMeetingPanel, w.getDockingLayoutMeetingPanel());
            ControllerFacade.execute("layoutController", "changeLayout",w.getDockingLayoutMeetingPanel(), ConfigUtil.getProperty("LayoutName"));
//            ShareFileServerUtil.getInstance().connectKeepAlive(DataUtil.getLoginInfo().getServerIP(), 8889, DataUtil.getLoginInfo().getConfno());
            services.getShareFileService().keepConnect(DataUtil.getLoginInfo().getConfno(), DataUtil.getLoginInfo().getUsername());
            new Thread(){

				@Override
				public void run() {
					
					try {
						Double osVersion = PlatformUtil.getOSVersion();
			            log.info("osversion: "+osVersion);
//			            services.getScreenShareService().unInitScreenShareService();
//			            Thread.sleep(4000);
			            
			            boolean processExists = com.lorent.common.util.ProcessUtil.getInstance().processExists("winvnc.exe");
			            if (!processExists) {
			            	/*
			            	if ( osVersion >= 6.0f) {
								//higher than Vista
				            	Process startScreenShareProcess = services.getScreenShareService().startScreenShareProcess();
				            	DataUtil.setValue(DataUtil.Key.ScreenShareProcess, startScreenShareProcess);
							}
				            else{
				            	services.getScreenShareService().installScreenShareService();
				            }
			            	*/
			            	Process startScreenShareProcess = services.getScreenShareService().startScreenShareProcess();
			            	DataUtil.setValue(DataUtil.Key.ScreenShareProcess, startScreenShareProcess);
						}
					} catch (Exception e) {
						log.error("",e);
					}
				}
            	
            }.start();
    }
    
    
    public void logout()throws Exception{
        services.getLoginService().logout();
        ViewManager.clearView();
    }
    
    
    public void showLogin()throws Exception{
    	if(doCheckSoundCard()<1){
            this.showMessageDialog(StringUtil.getErrorString("info.tip"), StringUtil.getErrorString("maincontroller.showLogin.soundcard.error"));
//            return;
        }
    	if((Boolean)DataUtil.getValue(Key.Restart)){
    		String reason = ConfigUtil.getProperty("RestartReason");
    		if(!reason.equals("")){
    			this.showMessageDialog(StringUtil.getErrorString("info.tip"), reason);
    		}
    	}
    	UIManager.setLookAndFeel(ConfigUtil.getProperty("DefaultLookAndFeelClassName", "com.jtattoo.plaf.mcwin.McWinLookAndFeel"));
//    	ViewManager.changeLookAndFeelOnLoginFrame(ViewManager.getLookAndFeelByConf(ConfigUtil.getProperty("desktop.style")));
    	log.info("显示trayicon");
        MyTrayIcon trayicon = ViewManager.getComponent(MyTrayIcon.class);
        trayicon.showLoginMenu();
        
        log.info("显示登录界面");
        LoginFrame loginFrame = ViewManager.getComponent(LoginFrame.class);
        //if have login info
        String username = ConfigUtil.getProperty("username", "");
        String confno = ConfigUtil.getProperty("confno", "");
        String serverIP = ConfigUtil.getProperty("serverIP", "");
        
        List<String> usernames = UserInfoUtil.getAllUserNames();
        usernames.remove(username);
        usernames.add(0, username);
        String[] temp = new String[usernames.size()];
        loginFrame.setLoginInfo(usernames.toArray(temp), confno, serverIP);
        changeUsername(username);
        loginFrame.setVisible(true);
//        ViewManager.changeLookAndFeel();
        
        //auto login
        if((Boolean)DataUtil.getValue(Key.Restart)){
        	return;
        }
    	LoginInfo info = UserInfoUtil.getInfo(username);
    	if(info != null){
    		if(info.isAutoLogin()){
//    			LoginFrame frame = ViewManager.getComponent(LoginFrame.class);
//    			frame.enableLoginBtn(false);
    			ParaUtil paras = ParaUtil.newInstance()
    				.setString("username", username)
    				.setString("password", info.getPassWord())
    				.setString("confno", confno)
    				.setString("serverIP", serverIP)
    				.setBoolean("autoLogin", info.isAutoLogin())
    				.setBoolean("savePasswd", info.isSavePasswd());
    			doLogin(paras);
    		}
    	}
    }
    
    public int doCheckSoundCard(){
        return services.getLoginService().doCheckSoundCard();
    }
    
    public List<MemberDto> getMemberList()throws Exception{
        return services.getConfService().getMemberList();
    }
    
    public void createMemberList()throws Exception{
        MemberListPanel panel = ViewManager.getComponent(MemberListPanel.class);
    	List<MemberDto> members = getMemberList();
    	panel.showAllMembers(members);
    }
    
    public void reconnect()throws Exception{
//    	MemberListPanel panel = ViewManager.getComponent(MemberListPanel.class);
//    	panel.clear();
//    	this.clearChatMainPanelCombox();
//    	createMemberList();
//    	this.initChatMainPanelCombox();
    	ChatMainPanel chatMainPanel = ViewManager.getComponent(ChatMainPanel.class);
    	chatMainPanel.clearReceive();
    	chatMainPanel.addReconnectMsg();
//    	services.getLoginService().reAddMultUserChatListeners();
    	LvmcOpenfireUtil.enterRoom(DataUtil.getLoginInfo().getConfno(),DataUtil.getLoginInfo().getUsername());
    	if(!LvmcUtil.isUCSAPP()){
    		createMemberList();
    	}
    	this.initChatMainPanelCombox();
    	disconnectFlag = true;

    }
    
    
    public void openfireDisconnectByNetwork() throws Exception{
    	if(disconnectFlag){
    		ChatMainPanel chatMainPanel = ViewManager.getComponent(ChatMainPanel.class);
        	chatMainPanel.addOpenfireDisconnectMsg();
        	disconnectFlag = false;
    	}
    	
    }
    
    
    //=========================Listener==================================
    
    public synchronized void roomMemberChange(final ParaUtil paras)throws Exception{
    	SwingUtilities.invokeAndWait(new Runnable() {
			
			@Override
			public void run() {
				try{
			        String member = paras.getValue("member");
			        Integer status = paras.getValue("status");
			        boolean isOpenfireUser = (Boolean)(paras.getValue("isOpenfireUser")!=null?paras.getValue("isOpenfireUser"):false);
			        LoginInfo loginInfo=DataUtil.getValue(DataUtil.Key.LoginInfo);
			        MemberListPanel panel = ViewManager.getComponent(MemberListPanel.class);
			        ChatMainPanel chatMainPanel = ViewManager.getComponent(ChatMainPanel.class);
			        log.info("status: " + status + ";lccno:" + member + " from memberlistpanel " + isOpenfireUser);
			        if(status.equals(Constants.MEMBER_STATUS_JOIN)){
			        	log.info("join lccno:" + member + " from memberlistpanel " + isOpenfireUser);

			        	MemberDto temp = null;
			        	if(!LvmcUtil.isUCSAPP()){
			        		temp = services.getConfService().getMemberDtoByName(member);
			        	}else{
			        		FetchMemberInfoService fetchMemberInfoService = new FetchMemberInfoService();
			        		temp = fetchMemberInfoService.getMemberDtoByName(member);
			        	}
			        	
			        	String[] values = paras.getValue("values");
			        	if(values!=null){
				        	//str[6]=video(1-开启0-关闭),str[7]=audio(1-开启0-关闭)
				        	boolean enableVideo = Integer.parseInt(values[6])==1?true:false;
				        	boolean enableAudio = Integer.parseInt(values[7])==1?true:false;
				        	temp.setEnableAudio(enableAudio);
				        	temp.setEnableVideo(enableVideo);
			        	}
			        	if(LvmcUtil.isUCSAPP() && !isOpenfireUser){
			        		panel.joinOneMember(temp);
			        	}else if(!LvmcUtil.isUCSAPP() && isOpenfireUser){
			        		panel.joinOneMember(temp);
			        	}
			            if(isOpenfireUser && !temp.getName().equals(loginInfo.getUsername()))
			            	chatMainPanel.addComboxItem(new ChatComboxMemberModel(temp.getName(), temp.getNickname()));
			        }else if(status.equals(Constants.MEMBER_STATUS_LEAVE)){
			        	
			            log.info("remove lccno:" + member + " from memberlistpanel " + isOpenfireUser);
			            if(!isOpenfireUser){
			            	panel.removeOneMember(member);
			            }
			            //与会者退出时重新设置与会者comboBox
			            if(!isOpenfireUser || loginInfo==null || member.equals(loginInfo.getUsername())){
			            	return;
			            }
			            
//			            log.info("remove " + member + " from chatmainpanel start");
			            List<MemberDto> members = services.getConfService().getOpenfireMemberList();
			            List<ChatComboxMemberModel> listperson = new ArrayList<ChatComboxMemberModel>();
			            listperson.add(new ChatComboxMemberModel(null, "所有人"));
			            for (Iterator<MemberDto> it = members.iterator(); it.hasNext();) {
			                MemberDto memberDto = it.next();
//			                log.info("add " + member + " to chatmainpanel ");
			                if (memberDto.getName().equals(loginInfo.getUsername())) {
			                    continue;
			                }
			                listperson.add(new ChatComboxMemberModel(memberDto.getName(), memberDto.getNickname()));
			            }
			            ViewManager.getComponent(ChatMainPanel.class).initcombox(listperson.toArray(new ChatComboxMemberModel[listperson.size()]));
//			            log.info("remove " + member + " from chatmainpanel end");
			        }else if(status.equals(Constants.MEMBER_STATUS_UPDATE)){
			        	String[] values = paras.getValue("values");
			        	//str[6]=video(1-开启0-关闭),str[7]=audio(1-开启0-关闭)
			        	boolean enableVideo = Integer.parseInt(values[6])==1?true:false;
			        	boolean enableAudio = Integer.parseInt(values[7])==1?true:false;
			        	MemberDto memberDto = panel.getMemberByName(member);
			        	memberDto.setEnableAudio(enableAudio);
			        	memberDto.setEnableVideo(enableVideo);
			        	panel.updateOneMember(memberDto);
			        }
			        panel.revalidate();
			        panel.repaint();
				}catch(Exception e){
					log.error("roomMemberChange", e);
				}
				
			}
		});

    }
    
    //会议被删除，或被踢出会议
    public void kickByRoom(Presence packet) throws Exception{
    	String[] split = packet.getFrom().split("/");
    	String username = split[1];
    	if (packet.getType() == Presence.Type.unavailable) {
    		if (!Launcher.isStartedFromOutSide && DataUtil.getLoginInfo() != null) {
        		if (username.equals(DataUtil.getLoginInfo().getUsername()) && !packet.isAvailable()) {
            		log.info("kickByRoom "+username+" "+packet+" "+packet.toXML());
            		MainFrame mainFrame = ViewManager.getComponent(MainFrame.class);
            		mainFrame.setExtendedState(mainFrame.MAXIMIZED_BOTH);
            		mainFrame.toFront();
        			JOptionPane.showMessageDialog(mainFrame, StringUtil.getUIString("kickByRoom.text"));
        			exitApplicationWithoutConfirm(false, null);
        		}
    		}
        	else if(Launcher.isStartedFromOutSide && DataUtil.getLoginInfo() != null 
        			&& DataUtil.getLoginInfo().getUsername().equals(username)){
        		Boolean initiative = DataUtil.getValue(DataUtil.Key.Initiative);
        		doLogoutFromOutSide();

        		log.info("initiative: "+initiative);
        		if (initiative == null || initiative  != true) {
        			JOptionPane.showMessageDialog(null, StringUtil.getUIString("kickByRoom.lvmc.text"));
				}
        	}
        	log.info("kickByRoom: "+packet.toXML());
		}
    }
    
    public void exitApplicationWithoutConfirm(boolean isRestartApp, String restartReason) throws Exception{
        log.info("退出应用程序");
        try{
            ControllerFacade.execute("phoneController", "exitApplication", ((LoginInfo)DataUtil.getValue(DataUtil.Key.LoginInfo)).getConfno());
//            LCCUtil.getInstance().doHangup(((LoginInfo)DataUtil.getValue(DataUtil.Key.LoginInfo)).getConfno());
//            LCCUtil.getInstance().doUninit();
        }catch(Exception e){
            log.error("exitApplicationWithoutConfirm", e);
        }
//        String filepath = com.lorent.lvmc.util.Constants.AppPath + "/vnc/winvnc.exe";
//        Runtime.getRuntime().exec("cmd /c " + StringUtil.convertFilePath2DOSCommandStr(filepath) + " -kill");
//        Runtime.getRuntime().exec("cmd /c " + StringUtil.convertFilePath2DOSCommandStr(filepath) + " -stopservice");
//        ProcessUtil.getInstance().killProcessByName("winvnc.exe");
//        services.getScreenShareService().unInitScreenShareService();
        ControllerFacade.execute("shareDesktopController", "stopScreenShare");
        
        if(isRestartApp){
        	ConfigUtil.setProperty("restart", true + "");
        	ConfigUtil.setProperty("RestartReason", restartReason);
            ProcessUtil.getInstance().restartApplication();
        }
        Thread.sleep(500);
        System.exit(0);
    }
    
    public void exitApplication() throws Exception{
        int i = this.showConfirmDialog("提示", "确定要退出吗");
        if(i != 0){
             return;
        }
        if (Launcher.isStartedFromOutSide) {
        	DataUtil.setValue(DataUtil.Key.Initiative, true);
			Launcher.stopLvmcFromOutSide();
		}
        else{
        	exitApplicationWithoutConfirm(false, null);
        }
        
//        OpenfireUtil.close();
    }
    
    public void relogin()throws Exception{
        LoginInfo loginInfo = DataUtil.getValue(DataUtil.Key.LoginInfo);
        log.info(loginInfo.getUsername() + "有相同账号登录");
        this.showErrorDialog(StringUtil.getErrorString("error.title"), StringUtil.getErrorString("MainController.relogin"));//
//        System.exit(0);
//        exitApplicationWithoutConfirm(true, "");
        exitApplication();
    }

    
    public void exitApplicationByNotLinkOpenfire()throws Exception{
        this.showErrorDialog(StringUtil.getErrorString("error.title"), StringUtil.getErrorString("MainController.exitApplicationByNotLinkOpenfire"));//
        System.exit(0);
    }
    
    public void exitByNewThread(){
    	new Thread(){
    		public void run(){
    			try {
					Thread.sleep(5000);
					System.exit(0);
				} catch (InterruptedException e) {
					log.error("exitByNewThread", e);
					e.printStackTrace();
				}
    		}
    	}.start();
    }
    
    public void exitFromTrayIcon()throws Exception{
    	log.info("trayicon exit");
    	if(MainFrame.getInstance() == null){//登陆界面
    		System.exit(0);
    	}else{//主界面
    		exitApplication();
    	}
    }
    
    public void dbClickTrayIcon()throws Exception{
    	log.debug("double click trayicon");
    	if(MainFrame.getInstance() == null){//登陆界面
    		LoginFrame loginFrame = ViewManager.getComponent(LoginFrame.class);
    		loginFrame.setExtendedState(Frame.NORMAL);
    		loginFrame.toFront();
    	}else{//主界面
    		MainFrame mainFrame = ViewManager.getComponent(MainFrame.class);
    		mainFrame.setVisible(true);
    		mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
    		mainFrame.toFront();
    	}
    }
    
    public void logoutFromTrayIcon()throws Exception{
        int i = this.showConfirmDialog(StringUtil.getUIString("base.confirm"), StringUtil.getUIString("logout.confirm"));
        if(i != 0){
             return;
        }
        exitApplicationWithoutConfirm(true, "");
    }
    
    public void showAboutDialog()throws Exception{
    	AboutDialog dialog = ViewManager.getComponent(AboutDialog.class, new Class[]{Frame.class, Boolean.class}, new Object[]{(Frame)null, false});
    	dialog.setLocationRelativeTo(null);
    	dialog.setVisible(true);
    }
    
    public void changeUsername(String username) throws Exception{
    	LoginInfo info = UserInfoUtil.getInfo(username);
    	if(info != null){
    		LoginFrame frame = ViewManager.getComponent(LoginFrame.class);
    		if(info.isSavePasswd() || info.isAutoLogin()){
    			frame.changeInfo(info.getPassWord(), info.isAutoLogin(), info.isSavePasswd());
    		}else{
    			frame.changeInfo("", info.isAutoLogin(), info.isSavePasswd());
    		}
    	}
    }
    
    public List<MemberDto> getOpenfireMemberList()throws Exception{
        return services.getConfService().getOpenfireMemberList();
    }
    
    public void showInviteDialog(){
    	InviteDialog dialog = new InviteDialog((java.awt.Frame)DataUtil.getTopWindow(),true);
    	try {
			ViewManager.setWindowCenterLocation(dialog);
		} catch (Exception e) {
			log.error("showInviteDialog", e);
		}
    	dialog.setVisible(true);
    }
    
    public void inviteUser(String lccno) throws Exception{
    	lccno = lccno.trim();
    	log.info("邀请用户："+lccno);
    	if (lccno != null && !lccno.equals("")) {
    		LoginInfo loginInfo = DataUtil.getLoginInfo();
    		if(lccno.equals(loginInfo.getUsername())){
    			this.showErrorDialog(StringUtil.getErrorString("error.title"), StringUtil.getErrorString("inviteUser.cannotInviteMe"));
    			return;
    		}
    		MemberListPanel memberListPanel = ViewManager.getComponent(MemberListPanel.class);
    		MemberDto member = memberListPanel.getMemberByName(lccno);
    		if(member != null){//已存在会议中
    			this.showErrorDialog(StringUtil.getErrorString("error.title"), StringUtil.getErrorString("inviteUser.cannotInviteUserInConf"));
    			return;
    		}
        	String siplccno  = "sip:"+lccno+"@"+LCCUtil.getInstance().getRegServerIP()+":"+LCCUtil.getInstance().getRegServerPort();
        	Launcher.getLCMUtil().inviteUserFromMcu(loginInfo.getConfno(), siplccno);
		}
    	else{
    		this.showErrorDialog(StringUtil.getErrorString("error.title"), StringUtil.getErrorString("MainController.lccnoToShort"));
    	}
    }
    
    public void removeUser(String lccno) throws Exception{
    	log.info("踢出用户："+lccno);
    	if (lccno != null && !lccno.equals("")) {
    		LoginInfo loginInfo = DataUtil.getLoginInfo();
    		if(lccno.equals(loginInfo.getUsername())){
    			this.showErrorDialog(StringUtil.getErrorString("error.title"), StringUtil.getErrorString("kick.oneself.error"));
    			return;
    		}
        	Launcher.getLCMUtil().removeUserFromMcu(loginInfo.getConfno(), lccno);
    	}
    }
    
    public void enableUserVideo(boolean enable, String user)throws Exception{
    	if(PermissionUtil.hasPermission(PermissionUtil.AUTHORITY_OPERATE)){//判断有没有权限
        	String confno = DataUtil.getLoginInfo().getConfno();
        	log.info("enableUserVideo : confno = " + confno + " & user = " + user + " & enable = " + enable);
        	Launcher.getLCMUtil().enableConfUserVideo(DataUtil.getLoginInfo().getConfno(), user, enable);
    	}else{
    		this.showErrorDialog(StringUtil.getErrorString("error.title"), StringUtil.getErrorString("permission.onlyHostCanDo"));
    		return;
    	}

    }
    
    public void enableUserAudio(boolean enable, String user)throws Exception{
    	if(PermissionUtil.hasPermission(PermissionUtil.AUTHORITY_OPERATE)){//判断有没有权限
	    	String confno = DataUtil.getLoginInfo().getConfno();
	    	log.info("enableUserSound : confno = " + confno + " & user = " + user + " & enable = " + enable);
	    	Launcher.getLCMUtil().enableConfUserAudio(DataUtil.getLoginInfo().getConfno(), user, enable);
    	}else{
    		this.showErrorDialog(StringUtil.getErrorString("error.title"), StringUtil.getErrorString("permission.onlyHostCanDo"));
    		return;
    	}
    }
    
    
}
