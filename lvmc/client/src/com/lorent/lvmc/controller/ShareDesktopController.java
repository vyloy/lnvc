/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.controller;

import com.lorent.common.component.FadeWindow;
import com.lorent.common.component.ImageAwtPanel;
import com.lorent.common.util.ParaUtil;
import com.lorent.common.util.PlatformUtil;
import com.lorent.lvmc.dto.LoginInfo;
import com.lorent.lvmc.dto.MemberDto;
import com.lorent.lvmc.ui.ConfirmDialog;
import com.lorent.lvmc.ui.DockingLayoutMeetingPanel;
import com.lorent.lvmc.ui.MainFrame;
import com.lorent.lvmc.ui.MaxVncPanelWindow;
import com.lorent.lvmc.ui.MemberListItem;
import com.lorent.lvmc.ui.MemberListPanel;
import com.lorent.lvmc.ui.VideoViewsPanel;
import com.lorent.lvmc.ui.VideoViewsPanelItem;
import com.lorent.lvmc.ui.VncViewPanel;
import com.lorent.lvmc.util.ConfigUtil;
import com.lorent.lvmc.util.Constants;
import com.lorent.lvmc.util.DataUtil;
import com.lorent.lvmc.util.ShareFileServerUtil;
import com.lorent.lvmc.util.StringUtil;
import com.lorent.vnc.VncViewer;
import com.lorent.vnc.VncViewerListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

import net.infonode.docking.View;
import net.infonode.properties.propertymap.ref.ThisPropertyMapRef;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author Administrator
 */
public class ShareDesktopController extends BaseController {

	private int connectTimes = 0;
	
    private static Logger log = Logger.getLogger(ShareDesktopController.class);

    private Map<String,VncViewer> viewerMap = new HashMap<String, VncViewer>();
    
    private boolean screenshareflag = false;
    
    private ArrayBlockingQueue<WaitViewParamBean> waitlist = new ArrayBlockingQueue<WaitViewParamBean>(100);
    
    private Map<String, Object> confirmDialogMap = new HashMap<String, Object>();
    
    private Thread looThread = new Thread(){

		@Override
		public void run() {
			WaitViewParamBean poll;
			while (true) {
				try {
					poll = waitlist.poll(1000, TimeUnit.MILLISECONDS);
					if (poll != null) {
						
						String[] args = poll.getArgs();
						String remoteUserName = poll.getRemoteUserName(); 
						String repeaterid = poll.getRepeaterid();
						
						services.getScreenShareService().addRepeaterID((String)DataUtil.getValue(DataUtil.Key.RepeaterHost), Integer.parseInt(repeaterid));
//						Thread.sleep(2000);
						ShareFileServerUtil.getInstance().startScreenShareToOneClient(DataUtil.getLoginInfo().getConfno(), remoteUserName, repeaterid,args);
						
					}
				}catch (Exception e) {
					log.error("looThread.run", e);
				}
	
			}
		}
    };
    
    class WaitViewParamBean{
    	private String[] args;
    	private String remoteUserName;
    	private String repeaterid;
		public String[] getArgs() {
			return args;
		}
		public void setArgs(String[] args) {
			this.args = args;
		}
		public String getRemoteUserName() {
			return remoteUserName;
		}
		public void setRemoteUserName(String remoteUserName) {
			this.remoteUserName = remoteUserName;
		}
		public String getRepeaterid() {
			return repeaterid;
		}
		public void setRepeaterid(String repeaterid) {
			this.repeaterid = repeaterid;
		}
    	
    }
    
    private void createDesktopViewFromParameters(String[] args,final String remoteUserName,final String repeaterid) throws Exception{
    	
    	VncViewer vncViewer = new VncViewer();
        vncViewer.setVncViewerListener(new VncViewerListener() {
           
            @Override
            public void createDesktopView(Container desktop, Panel butomPanel) {
            	log.info("创建共享桌面界面");
                try {
                    DockingLayoutMeetingPanel dockingLayoutMeetingPanel = ViewManager.getComponent(DockingLayoutMeetingPanel.class);
                    VncViewPanel viewPanel = new VncViewPanel();
//                    int findIndexOfViewMap = mainFrame.getDockingLayoutMeetingPanel().findIndexOfViewMap("vncview_"+remoteUserName);
                    View findInViewMap = dockingLayoutMeetingPanel.findInViewMap("vncview_"+remoteUserName);
                    //判断是否已经存在界面
                    if(findInViewMap != null){
//                        mainFrame.getDockingLayoutMeetingPanel().remotePanel("vncview_"+remoteUserName);
                        viewPanel = (VncViewPanel) findInViewMap.getComponent();
                        viewPanel.getDesktopScrollPane().setVisible(true);
                        viewPanel.getDesktopScrollPane().removeAll();
                        viewPanel.getDesktopScrollPane().add(desktop);
                        viewPanel.getTitletext().setText("ID:"+repeaterid);
                        dockingLayoutMeetingPanel.restoreView("vncview_"+remoteUserName);
                        
                    }
                    else{
                        viewPanel.getDesktopScrollPane().add(desktop);
                        viewPanel.setUsername(remoteUserName);
                        viewPanel.getTitletext().setText("ID:"+repeaterid);
                        dockingLayoutMeetingPanel.addPanel(viewPanel, "远程桌面："+remoteUserName, "vncview_"+remoteUserName, null,StringUtil.getUIString("VncViewPanel.img"));
                    }
//                    Thread.sleep(200);
                    viewPanel.revalidate();
                    viewPanel.repaint();
                    
                    VncViewer vncViewer = viewerMap.get("vncview_"+remoteUserName);
                    if (vncViewer != null) {
                    	vncViewer.setCustomProperty("CreateDesktopViewed", "true");
					}
                    
                } catch (Exception ex) {
                    log.error("VncViewerListener.createDesktopView", ex);
                }
            }

            @Override
            public void onFatalError(String string) {
                try {
                    log.info("onFatalError:"+string);
                    stopDesktopView(remoteUserName);
                    
                } catch (Exception ex) {
                	log.error("VncViewerListener.onFatalError", ex);
                }
            }
        });
//        Integer nport = port;
        vncViewer.mainArgs = args;//new String[]{"HOST", ip, "PORT", nport.toString(), "PASSWORD", password, "View only", "Yes", "Restricted colors", "Full"};
        vncViewer.inAnApplet = false;
        vncViewer.inSeparateFrame = true;
        vncViewer.init();
        vncViewer.start();
        vncViewer.setCustomProperty("IsDestroy", "false");
        vncViewer.setCustomProperty("CreateDesktopViewed", "false");
        viewerMap.put("vncview_"+remoteUserName, vncViewer);
        log.info("vncViewer.start()  ");
      //判断是否远程连接成功
        new Thread(){

			@Override
			public void run() {
				try {
					Thread.sleep(7000);
				} catch (InterruptedException e) {
					log.error("createDesktopViewFromParameters", e);
				}
				VncViewer vncViewer = viewerMap.get("vncview_"+remoteUserName);
				if (vncViewer != null && !vncViewer.getCustomProperty("IsDestroy").equals("true") && !vncViewer.getCustomProperty("CreateDesktopViewed").equals("false")) {
//					System.out.println("titile:"+repeaterid);
//					waitlist.remove(repeaterid+"");
					log.info("远程桌面: vncview_"+remoteUserName+" 连接成功..."+DataUtil.getLoginInfo().getUsername()+","+repeaterid);
					
					try {
						DockingLayoutMeetingPanel dockingLayoutMeetingPanel = ViewManager.getComponent(DockingLayoutMeetingPanel.class);
			    		View findInViewMap = dockingLayoutMeetingPanel.findInViewMap("vncview_"+remoteUserName);
			    		if (findInViewMap != null) {
			    			VncViewPanel viewPanel = (VncViewPanel) findInViewMap.getComponent();
		                    if(viewPanel != null){
		                    	viewPanel.getStatusText().setText("连接成功 ");
		                    	viewPanel.getAskforControlButton().setEnabled(true);
		                    	viewPanel.getMaxViewButton().setEnabled(true);
//		                    	viewPanel.getCloseButton().setEnabled(true);
//		                    	viewPanel.getCloseButton().setVisible(false);
		                    	viewPanel.getRevertViewButton().setEnabled(true);
		                    	Component component = viewPanel.getDesktopScrollPane().getComponent(0);
		                    	if (component instanceof ImageAwtPanel) {
									ImageAwtPanel panel = (ImageAwtPanel)component;
									panel.setImg(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/com/lorent/lvmc/resource/images/black.png")));
								}
		                    	viewPanel.getDesktopScrollPane().getComponent(0).repaint();
		                    	viewPanel.setConnectTimes(0);
		                    	viewPanel.setConnected(true);
		                    }	
						}
						ShareFileServerUtil.getInstance().startScreenShareToOneClientCallBack(DataUtil.getLoginInfo().getConfno(), remoteUserName, DataUtil.getLoginInfo().getUsername(), "SUCCESS",repeaterid);
					} catch (Exception e) {
						log.error("createDesktopViewFromParameters", e);
						e.printStackTrace();
					}
				}
				else{
			    	//通知远程服务端失败
					log.info("远程桌面: vncview_"+remoteUserName+" 连接失败..."+DataUtil.getLoginInfo().getUsername()+","+repeaterid);
			    	try {
			    		DockingLayoutMeetingPanel dockingLayoutMeetingPanel = ViewManager.getComponent(DockingLayoutMeetingPanel.class);
			    		View findInViewMap = dockingLayoutMeetingPanel.findInViewMap("vncview_"+remoteUserName);
			    		if (findInViewMap != null) {
			    			VncViewPanel viewPanel = (VncViewPanel) findInViewMap.getComponent();
		                    if(viewPanel != null){
		                    	viewPanel.setConnectTimes(viewPanel.getConnectTimes()+1);
		                    	viewPanel.setConnected(false);
		                    	viewPanel.getStatusText().setText("尝试连接    "+viewPanel.getConnectTimes());
		                    }
						}
	                    stopDesktopView(remoteUserName);
						ShareFileServerUtil.getInstance().startScreenShareToOneClientCallBack(DataUtil.getLoginInfo().getConfno(), remoteUserName, DataUtil.getLoginInfo().getUsername(), "ERROR",repeaterid);
					} catch (Exception e) {
						log.error("createDesktopViewFromParameters", e);
						e.printStackTrace();
					}
					
				}
			}
		}.start();
    }
    
    public void createDesktopControlView(String ip,int port,String password,String remoteUserName) throws Exception{
        Integer nport = port;
        String[] parameters = new String[]{
            "HOST", ip, "PORT", nport.toString(), "PASSWORD", password,
            "Restricted colors", "Full"};
        createDesktopViewFromParameters(parameters, remoteUserName,"");
    }
    
    public void createDesktopView(String ip, int port, String password, String remoteUserName, Map<String, Integer> repeaterids) throws Exception {
        Integer nport = port;
        String innat = ConfigUtil.getProperty("IsInNat", "true");
        String[] parameters = new String[]{
            "HOST", ip, "PORT", nport.toString(), "PASSWORD", password,
            "View only", "Yes", "Restricted colors", "Full"};
        if (!innat.equals("true")) {
            if (repeaterids != null && repeaterids.size() != 0) {

                Integer id = repeaterids.get(remoteUserName + "-" + DataUtil.getLoginInfo().getUsername());
                Collection<Integer> values = repeaterids.values();

                Set<String> keySet = repeaterids.keySet();
                for (String string : keySet) {
                    log.info("repeaterids key:" + string + " value:" + repeaterids.get(string));
                }
                String vnccolor = ConfigUtil.getProperty("VncColors", "Full");
                if (id != null) {

                    nport = ConfigUtil.getIntProperty("repeaterport");
                    parameters = new String[]{
                        "HOST", "ID", "PORT", id.toString(), "PASSWORD", password,
                        "View only", "Yes", "Restricted colors", vnccolor,
                        "REPEATERHOST", DataUtil.getValue(DataUtil.Key.RepeaterHost),
                        "REPEATERPORT", nport.toString()};
                    createDesktopViewFromParameters(parameters, remoteUserName, id.toString());
                }
                log.info("createDesktopView " + remoteUserName + "-" + DataUtil.getLoginInfo().getUsername() + " id:" + id);
            }
        } else {
            createDesktopViewFromParameters(parameters, remoteUserName, "");
            log.info("createDesktopView " + remoteUserName + "-" + DataUtil.getLoginInfo().getUsername() + " noid");
        }
    }
    
    //主动关闭,或被远端主动关闭
    public void closeDesktopView(String remoteUserName) throws Exception{
    	log.info("closeDesktopView");
    	VncViewer viewer = viewerMap.get("vncview_"+remoteUserName);
        if (viewer != null) {
        	viewer.setCustomProperty("IsDestroy", "true");
            viewer.setEnabled(false);
            viewer.disconnect();
            viewer.destroy();//dispose!
        }
        DockingLayoutMeetingPanel dockingLayoutMeetingPanel = ViewManager.getComponent(DockingLayoutMeetingPanel.class);
        View findInViewMap = dockingLayoutMeetingPanel.findInViewMap("vncview_"+remoteUserName);
        if (findInViewMap != null) {
//        	dockingLayoutMeetingPanel.removePanel("vncview_"+remoteUserName);
        }
        dockingLayoutMeetingPanel.removePanel("vncview_"+remoteUserName);
        MaxVncPanelWindow component = ViewManager.getComponent(MaxVncPanelWindow.class);
        component.setVncViewPanel(null);
    	component.dispose();
        viewerMap.put("vncview_"+remoteUserName, null);
        ShareFileServerUtil.getInstance().startScreenShareToOneClientCallBack(DataUtil.getLoginInfo().getConfno(), remoteUserName, DataUtil.getLoginInfo().getUsername(), "DESKTOP_STOPVIWE","-1");
    }
    
    //被动关闭
    public void stopDesktopView(String remoteUserName) throws Exception{
        log.info("stopDesktopView");
        VncViewer viewer = viewerMap.get("vncview_"+remoteUserName);
        if (viewer == null) {
            return;
        }
        viewer.setCustomProperty("IsDestroy", "true");
        viewer.setEnabled(false);
        viewer.disconnect();
        viewer.destroy();//dispose!
        
        VncViewPanel viewPanel = null;
        MaxVncPanelWindow maxVncPanelWindow = ViewManager.getComponent(MaxVncPanelWindow.class);
        DockingLayoutMeetingPanel dockingLayoutMeetingPanel = ViewManager.getComponent(DockingLayoutMeetingPanel.class);
        View findInViewMap = dockingLayoutMeetingPanel.findInViewMap("vncview_"+remoteUserName);
        if (maxVncPanelWindow.isVisible()) {
			viewPanel = maxVncPanelWindow.getVncViewPanel();
		}
        else{
        	if (findInViewMap != null) {
            	viewPanel = (VncViewPanel) findInViewMap.getComponent();
        	}
        }
        
        if (viewPanel != null && viewPanel.isConnected()) {
        	final JDialog jDialog = new JDialog(ViewManager.getComponent(MainFrame.class),false);
        	jDialog.addWindowListener(new java.awt.event.WindowAdapter() {
    			public void windowClosing(java.awt.event.WindowEvent e) {
    				jDialog.dispose();
    			}
    		});
        	
        	final JOptionPane jOptionPane = new JOptionPane("远程桌面 "+remoteUserName+" 的连接已经断开", JOptionPane.INFORMATION_MESSAGE);
        	jDialog.setContentPane(jOptionPane);
        	jDialog.setTitle("信息提示");
        	jOptionPane.addPropertyChangeListener(new PropertyChangeListener() {
    			
    			@Override
    			public void propertyChange(PropertyChangeEvent evt) {
    				String propertyName = evt.getPropertyName();
//    				System.out.println(propertyName+" , "+evt.getSource());
    				if (jDialog.isVisible() && evt.getSource() == jOptionPane && propertyName.equals(JOptionPane.VALUE_PROPERTY)) {
//    					jDialog.setVisible(false);
    					jDialog.dispose();
    				}
    			}
    		});
        	jDialog.pack();
        	FadeWindow fadeWindow = new FadeWindow(jDialog);
        	fadeWindow.setVisible(true);
        	fadeWindow.setPosistionInRightBottom();
    		fadeWindow.startFadeIn();
		}
        maxVncPanelWindow.setVncViewPanel(null);
        maxVncPanelWindow.dispose();
        dockingLayoutMeetingPanel.removePanel("vncview_"+remoteUserName);
        viewerMap.put("vncview_"+remoteUserName, null);
        ShareFileServerUtil.getInstance().startScreenShareToOneClientCallBack(DataUtil.getLoginInfo().getConfno(), remoteUserName, DataUtil.getLoginInfo().getUsername(), "DESKTOP_STOPVIWE","-1");
        log.debug("stopDesktopView End");
    }
    
    public void askforControlDesktop(String remoteUserName) throws Exception{
//        log.info("askforControlDesktop");
        services.getScreenShareService().askforControlDesktop(remoteUserName);
    }
    
    public void showConfirmControlDesktopDialog(final String remoteUserName,String remoteNickName) throws Exception{
        
    	Object object = confirmDialogMap.get(remoteUserName);
    	if (object == null) {
    		final JDialog jDialog = new JDialog(ViewManager.getComponent(MainFrame.class),false);
        	jDialog.addWindowListener(new java.awt.event.WindowAdapter() {
    			public void windowClosing(java.awt.event.WindowEvent e) {
    				jDialog.dispose();
    				confirmDialogMap.remove(remoteUserName);
    			}
    		});
        	
        	final JOptionPane jOptionPane = new JOptionPane(remoteNickName+"（"+remoteUserName+"）"+"  申请控制，是否允许？", JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION);
        	jDialog.setContentPane(jOptionPane);
        	jDialog.setTitle("信息提示");
        	jOptionPane.addPropertyChangeListener(new PropertyChangeListener() {
    			
    			@Override
    			public void propertyChange(PropertyChangeEvent evt) {
    				String propertyName = evt.getPropertyName();
    				if (jDialog.isVisible() && evt.getSource() == jOptionPane && propertyName.equals(JOptionPane.VALUE_PROPERTY)) {
    					if (evt.getNewValue().equals(JOptionPane.YES_OPTION)) {
    						try {
    							//找出正在控制的控制端
    							MemberListPanel memberListPanel = ViewManager.getComponent(MemberListPanel.class);
    							DefaultListModel model = (DefaultListModel)memberListPanel.getMemberList().getModel();
    							for (int i = 0; i < model.size(); i++) {
    					            MemberListItem item = (MemberListItem) model.get(i);
    					            if (item.getShareDesktopState() == Constants.MEMBER_STATUS_SHAREDESKTOP_CONTROL) {
    					            	services.getScreenShareService().notAgreeControlDesktop(item.getData().getName());
									}
    					        }
    	    					services.getScreenShareService().agreeControlDesktop(remoteUserName);
    	    				} catch (Exception e1) {
    	    					log.error("showConfirmControlDesktopDialog", e1);
    	    				}
    	    				confirmDialogMap.remove(remoteUserName);
    	    				jDialog.dispose();
						}
						else if (evt.getNewValue().equals(JOptionPane.NO_OPTION)) {
							confirmDialogMap.remove(remoteUserName);
							jDialog.dispose();
						}
    				}
    			}
    		});
        	jDialog.pack();
        	FadeWindow fadeWindow = new FadeWindow(jDialog);
//        	fadeWindow.startRuning();
        	fadeWindow.setPosistionInRightBottom();
        	fadeWindow.setWindowOpacity(1.0f);
        	fadeWindow.setVisible(true);
//        	fadeWindow.startFadeIn();
        	confirmDialogMap.put(remoteUserName, remoteUserName);

		}
    	
    	
    }
    
    //远程桌面同意被控制
    public void remoteAgreeControlDesktop(String remoteUserName) throws Exception{
        log.info("remoteAgreeControlDesktop "+remoteUserName);
        VncViewer viewer = viewerMap.get("vncview_"+remoteUserName);
        if (viewer != null) {
            viewer.setViewOnly(false);
            
            MaxVncPanelWindow component = ViewManager.getComponent(MaxVncPanelWindow.class);
            if (component.isVisible()) {
            	VncViewPanel viewPanel = component.getVncViewPanel();
            	viewPanel.getAskforControlButton().setEnabled(false);
                viewPanel.getStopControlButton().setEnabled(true);
			}
            else{
            	DockingLayoutMeetingPanel dockingLayoutMeetingPanel = ViewManager.getComponent(DockingLayoutMeetingPanel.class);
                View findInViewMap = dockingLayoutMeetingPanel.findInViewMap("vncview_" + remoteUserName);
                if (findInViewMap != null) {
                    VncViewPanel viewPanel = (VncViewPanel) findInViewMap.getComponent();
                    viewPanel.getAskforControlButton().setEnabled(false);
                    viewPanel.getStopControlButton().setEnabled(true);
                }
            }
            ShareFileServerUtil.getInstance().startScreenShareToOneClientCallBack(DataUtil.getLoginInfo().getConfno(), remoteUserName, DataUtil.getLoginInfo().getUsername(), "DESKTOP_CONTROL","-1");
        }
    }
    
    public void stopControlRemoteDesktop(String remoteUserName) throws Exception{
    	log.info("stopControlRemoteDesktop "+remoteUserName);
        VncViewer viewer = viewerMap.get("vncview_"+remoteUserName);
        if (viewer != null) {
            viewer.setViewOnly(true);
            
            MaxVncPanelWindow component = ViewManager.getComponent(MaxVncPanelWindow.class);
            if (component.isVisible()) {
            	VncViewPanel viewPanel = component.getVncViewPanel();
            	viewPanel.getAskforControlButton().setEnabled(true);
                viewPanel.getStopControlButton().setEnabled(false);
			}
            else{
            	DockingLayoutMeetingPanel dockingLayoutMeetingPanel = ViewManager.getComponent(DockingLayoutMeetingPanel.class);
                View findInViewMap = dockingLayoutMeetingPanel.findInViewMap("vncview_" + remoteUserName);
                if (findInViewMap != null) {
                    VncViewPanel viewPanel = (VncViewPanel) findInViewMap.getComponent();
                    viewPanel.getAskforControlButton().setEnabled(true);
                    viewPanel.getStopControlButton().setEnabled(false);
                }
            }
            ShareFileServerUtil.getInstance().startScreenShareToOneClientCallBack(DataUtil.getLoginInfo().getConfno(), remoteUserName, DataUtil.getLoginInfo().getUsername(), "DESKTOP_NOTCONTROL","-1");
        }
    }
    
    public void getRepeaterIDCallBack(Map<String, Integer> repeaterids) throws Exception{
    	log.info("getRepeaterIDCallBack");
    	if (screenshareflag) {

    		if (!looThread.isAlive()) {
    			looThread.start();
			}
    		
    		Iterator<Entry<String, Integer>> iterator = repeaterids.entrySet().iterator();
    		while (iterator.hasNext()) {
				Map.Entry<java.lang.String, java.lang.Integer> entry = (Map.Entry<java.lang.String, java.lang.Integer>) iterator.next();
				String remoteUserName = entry.getKey().substring(entry.getKey().indexOf("-")+1);
				if (!remoteUserName.equals(DataUtil.getLoginInfo().getUsername())) {
					addToWaitList(remoteUserName, entry.getValue()+"",true);					
				}
			}

//			DockingLayoutMeetingPanel panel = DataUtil.getValue(DataUtil.Key.DockingLayoutMeetingPanel);
//			panel.getStartScreenShareMI().setEnabled(false);
//			panel.getStopScreenShareMI().setEnabled(true);
    		MainFrame mainFrame = ViewManager.getComponent(MainFrame.class);
    		mainFrame.getShareDesktopButton().setText(StringUtil.getUIString("DockingLayoutMeetingPanel.stopScreenShareMI.text"));
			mainFrame.getShareDesktopButton().setEnabled(true);
			
//    		screenshareflag = false;
		}
    }
    
	public void roomMemberChange(ParaUtil paras)throws Exception{
	        
        final String member = paras.getValue("member");
        log.debug("member:"+member);
        Integer status = paras.getValue("status");
        if (status.equals(Constants.MEMBER_STATUS_JOIN)) {
        	if (screenshareflag) {
        		new Thread(){

					@Override
					public void run() {
		    			try {
		    				Thread.sleep(10000);
							log.info("共享桌面新添加:"+member);
			    			String[] targetusers = new String[]{member};
							services.getScreenShareService().getRepeaterIDsFromServer(targetusers);
						} catch (Exception e) {
							e.printStackTrace();
							log.error("roomMemberChange", e);
						}
					}
        		}.start();
        		
    		}
        }
        else if(status.equals(Constants.MEMBER_STATUS_LEAVE)){
        	VncViewer viewer = viewerMap.get("vncview_"+member);
        	if (viewer != null) {
        		stopDesktopView(member);
			}
        }
	}
    
	public void screenShareButtonClick() throws Exception{
		log.info("screenShareButtonClick");
		if (screenshareflag == false) {
			
			new Thread(){

				@Override
				public void run() {
					try {
						startScreenShare();
					} catch (Exception e) {
						e.printStackTrace();
						log.error("screenShareButtonClick", e);
					}
				}
			}.start();
		}
		else{
			stopScreenShare();
		}
				
	}
	
	public void startScreenShare() throws Exception {
		log.info("启动共享桌面");
		try {
			List<MemberDto> members = (List<MemberDto>) ControllerFacade.execute("mainController", "getOpenfireMemberList");
			String[] targetusers = new String[members.size()];
			for (int i = 0; i < members.size(); i++) {
				MemberDto memberDto = members.get(i);
				targetusers[i] = memberDto.getName();
			}
			
			MainFrame mainFrame = ViewManager.getComponent(MainFrame.class);
			mainFrame.getShareDesktopButton().setText(StringUtil.getUIString("DockingLayoutMeetingPanel.stopScreenShareMI.text"));
			mainFrame.getShareDesktopButton().setEnabled(false);
			
			
            boolean processExists = com.lorent.common.util.ProcessUtil.getInstance().processExists("winvnc.exe");
            if (!processExists) {
            	Double osVersion = PlatformUtil.getOSVersion();
            	if ( osVersion >= 6.0f) {
            		Process startScreenShareProcess = services.getScreenShareService().startScreenShareProcess();
	            	DataUtil.setValue(DataUtil.Key.ScreenShareProcess, startScreenShareProcess);
            	}
            	else{
            		services.getScreenShareService().installScreenShareService();
            	}
            }

			screenshareflag = true;
			services.getScreenShareService().getRepeaterIDsFromServer(targetusers);
			
			new Thread(){

				@Override
				public void run() {
					try {
						
						Thread.sleep(10000);
						MainFrame mainFrame = ViewManager.getComponent(MainFrame.class);
						if (screenshareflag && !mainFrame.getShareDesktopButton().isEnabled()) {
							log.info("启动共享桌面超时");
							mainFrame.getShareDesktopButton().setText(StringUtil.getUIString("DockingLayoutMeetingPanel.screenShareMenu.text"));
							mainFrame.getShareDesktopButton().setEnabled(true);
							screenshareflag = false;
						}
						
					} catch (Exception e) {
						e.printStackTrace();
						log.error("startScreenShare", e);
					}
				}}.start();
		} catch (Exception ex) {
			log.error("startScreenShare", ex);
		}
	}

	public void stopScreenShare() throws Exception {
		log.info("取消共享桌面");
		try {
			waitlist.clear();
			services.getScreenShareService().stopScreenShare();
			//同一时间只有一个被共享者,可以kill
			services.getScreenShareService().stopScreenShareProcess();
			MainFrame mainFrame = ViewManager.getComponent(MainFrame.class);
			mainFrame.getShareDesktopButton().setText(StringUtil.getUIString("DockingLayoutMeetingPanel.screenShareMenu.text"));
//			mainFrame.getShareDesktopButton().setEnabled(false);
			screenshareflag = false;
		} catch (Exception ex) {
			log.error("stopScreenShare", ex);
		}
	}

	public void startScreenShareProcess() throws Exception {
//		services.getScreenShareService().startScreenShareProcess();
	}
    
	private void addToWaitList(String remoteUserName,String repeaterid,boolean immediately) throws Exception{
		log.info("addToWaitList "+remoteUserName+","+repeaterid);
		WaitViewParamBean bean = new WaitViewParamBean();
		bean.setRemoteUserName(remoteUserName);
		bean.setRepeaterid(repeaterid);
		
		String vnccolor = ConfigUtil.getProperty("VncColors", "Full");
        Integer nport = ConfigUtil.getIntProperty("repeaterport");
		
        String[] parameters = new String[]{
                "HOST", "ID", "PORT", repeaterid, 
                "PASSWORD", ConfigUtil.getProperty("screenSharePasswd"),
                "View only", "Yes", "Restricted colors", vnccolor,
                "REPEATERHOST", DataUtil.getValue(DataUtil.Key.RepeaterHost),
                "REPEATERPORT", nport.toString()};
		
		bean.setArgs(parameters);
		
//		services.getScreenShareService().addRepeaterID((String)DataUtil.getValue(DataUtil.Key.RepeaterHost), Integer.parseInt(repeaterid));
//		Thread.sleep(3000);
		waitlist.offer(bean);
		
		
	}
	
    public void startScreenShareToOneClientFromSharer(String shareUserName,String repeaterid,Object args) throws Exception{
    	log.info("startScreenShareToOneClientFromSharer "+shareUserName+","+repeaterid+","+args);
    	String[] sargs = (String[]) args;
    	if (!shareUserName.equals(DataUtil.getLoginInfo().getUsername())) {
    		createDesktopViewFromParameters(sargs, shareUserName, repeaterid);
		}
    }
    
    public void startScreenShareToOneClientCallBack(String sharerUserName,String userName,String status,String repeaterid) throws Exception{
    	log.info("startScreenShareToOneClientCallBack "+sharerUserName+","+userName+","+status+","+repeaterid);
    	if (status.equals("ERROR")) {
//    		DockingLayoutMeetingPanel panel = DataUtil.getValue(DataUtil.Key.DockingLayoutMeetingPanel);
    		if (screenshareflag) {
    			addToWaitList(userName, repeaterid,false);
			}
//			if (panel.getStopScreenShareMI().isEnabled()) {
////				Thread.sleep(2000);
//				addToWaitList(userName, repeaterid,false);
//			}
			MemberListPanel memberPanel = ViewManager.getComponent(MemberListPanel.class);
    		MemberListItem memberListItemByName = memberPanel.getMemberListItemByName(userName);
    		if (memberListItemByName != null) {
    			memberListItemByName.setShareDesktopState(Constants.MEMBER_STATUS_SHAREDESKTOP_FREE);
        		memberPanel.repaint();
			}
		}
    	else if(status.equals("SUCCESS")){
    		MemberListPanel memberPanel = ViewManager.getComponent(MemberListPanel.class);
    		MemberListItem memberListItemByName = memberPanel.getMemberListItemByName(userName);
    		if (memberListItemByName != null) {
    			memberListItemByName.setShareDesktopState(Constants.MEMBER_STATUS_SHAREDESKTOP_VIEW);
//        		memberListItemByName.repaint();
        		memberPanel.repaint();
			}
    	}
    	else if(status.equals("DESKTOP_CONTROL")){
    		MemberListPanel memberPanel = ViewManager.getComponent(MemberListPanel.class);
    		MemberListItem memberListItemByName = memberPanel.getMemberListItemByName(userName);
    		if (memberListItemByName != null) {
    			memberListItemByName.setShareDesktopState(Constants.MEMBER_STATUS_SHAREDESKTOP_CONTROL);
        		memberPanel.repaint();
			}
    	}
    	else if(status.equals("DESKTOP_NOTCONTROL")){
    		MemberListPanel memberPanel = ViewManager.getComponent(MemberListPanel.class);
    		MemberListItem memberListItemByName = memberPanel.getMemberListItemByName(userName);
    		if (memberListItemByName != null) {
    			memberListItemByName.setShareDesktopState(Constants.MEMBER_STATUS_SHAREDESKTOP_VIEW);
        		memberPanel.repaint();
			}
    	}
    	else if(status.equals("DESKTOP_STOPVIWE")){
    		MemberListPanel memberPanel = ViewManager.getComponent(MemberListPanel.class);
    		MemberListItem memberListItemByName = memberPanel.getMemberListItemByName(userName);
    		if (memberListItemByName != null) {
    			memberListItemByName.setShareDesktopState(Constants.MEMBER_STATUS_SHAREDESKTOP_FREE);
//        		memberListItemByName.repaint();
        		memberPanel.repaint();
			}
    	}
    }
    
    public void maxVncViewPanel(VncViewPanel panel) throws Exception{
    	log.info("maxVncViewPanel");
    	MaxVncPanelWindow component = ViewManager.getComponent(MaxVncPanelWindow.class);
    	component.setAlwaysOnTop(true);
    	

    	Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    	component.setSize(screenSize);
    	component.setLocation(0, 0);
    	component.getCenterPanel().removeAll();
    	Dimension topPanelSize = panel.getTopPanel().getSize();
    	panel.getDesktopScrollPane().setSize(screenSize.width+1, screenSize.height-topPanelSize.height-4);
    	
    	component.getCenterPanel().add(panel,BorderLayout.CENTER);
    	component.setVncViewPanel(panel);
    	component.setVisible(true);
    	panel.getDesktopScrollPane().getComponent(0).repaint();
    	
    	panel.getMaxViewButton().setVisible(false);
    	panel.getRevertViewButton().setVisible(true);
    }
    
    public void revertVncViewPanel(VncViewPanel panel) throws Exception{
    	log.info("revertVncViewPanel");
    	DockingLayoutMeetingPanel dockingLayoutMeetingPanel = ViewManager.getComponent(DockingLayoutMeetingPanel.class);
    	dockingLayoutMeetingPanel.updatePanel(panel, "vncview_"+panel.getUsername());
    	MaxVncPanelWindow component = ViewManager.getComponent(MaxVncPanelWindow.class);
    	component.setVncViewPanel(null);
    	component.dispose();
    	panel.getDesktopScrollPane().getComponent(0).repaint();
    	panel.getMaxViewButton().setVisible(true);
    	panel.getRevertViewButton().setVisible(false);
    }

	public boolean isScreenshareflag() {
		return screenshareflag;
	}
    
    
}
