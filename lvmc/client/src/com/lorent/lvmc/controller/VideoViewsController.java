/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.controller;

import com.lorent.common.component.ImageAwtPanel;
import com.lorent.common.util.ParaUtil;
import com.lorent.lvmc.dto.LoginInfo;
import com.lorent.lvmc.dto.MemberDto;
import com.lorent.lvmc.event.PhoneEvent;
import com.lorent.lvmc.ui.*;
import com.lorent.lvmc.util.*;
import com.lorent.util.LCCUtil;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.dnd.DropTargetDropEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.infonode.docking.DockingWindow;
import net.infonode.docking.DockingWindowAdapter;
import net.infonode.docking.OperationAbortedException;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class VideoViewsController extends BaseController {

    private static Logger log = Logger.getLogger(VideoViewsController.class);

    private String defLayoutName = "1xN";
    private String individualPanelTitle = "";
    private static boolean initVideoed = false;
    private String changedLayoutName = "";
    private ArrayList<VideoViewsPanelItem> logicExistAndSortedLccList = new ArrayList<VideoViewsPanelItem>();

    
    public VideoViewsController(){
    	for (int i = 0; i < 36; i++) {
            VideoViewsPanelItem videoItemPanel = new VideoViewsPanelItem();
            videoItemPanel.setEnabled(false);
            videoItemPanel.setLccUserName("");
            videoItemPanel.setNickName("");
            logicExistAndSortedLccList.add(new VideoViewsPanelItem());
        }
    }
    
    public void initVideo() throws Exception {
        
        log.info("initVideo");
        individualPanelTitle = StringUtil.getUIString("IndividualVideo.title");
        
//        if (initVideoed == false) {

//		}	

//            ControllerFacade.execute("phoneController", "tryConnnectToCS", true);
        
//        mypanel.DrawImage();
        initVideoed = true;
    }

    public Integer enableVideo(String confno, String lccNo, Component win) throws Exception {
        if (lccNo != null && confno != null && !lccNo.equals("") && !confno.equals("")) {
            try {
                return  LCCUtil.getInstance().doAddMuxVideoStream(confno, lccNo, win);
            } catch (Exception e) {
                log.error("enableVideo 错误：",e);
                return  -1;
            }
        }
        return -1;
    }

    public Integer changeVideo(String confno,String lccNo,Component win) throws Exception{
        if (lccNo != null && confno != null && !lccNo.equals("") && !confno.equals("")) {
            try {
                return  LCCUtil.getInstance().doChangeMuxVideoStream(confno, lccNo, win);
            } catch (Exception e) {
                log.error("changeVideo 错误：",e);
                return  -1;
            }
        }
        return -1;
    }
    
    public Integer disableVideo(String confno, String lccNo) throws Exception {
        if (lccNo != null && confno != null && !lccNo.equals("") && !confno.equals("")) {
            try {
                return  LCCUtil.getInstance().doDelMuxVideoStream(confno, lccNo);
            } catch (Exception e) {
                log.error("disableVideo 错误:",e);
                return -1;
            }
        }
        return -1;
    }
    
    public void reflashVideoViewsPanel() throws Exception{
        VideoViewsPanel panel = ViewManager.getComponent(VideoViewsPanel.class);
        panel.revalidate();
        panel.repaint();
    }
    
    public void removeFromVideoPanel(String lccUserName) throws Exception {
        if (lccUserName != null && !lccUserName.equals("")) {
            ParaUtil newInstance = ParaUtil.newInstance();
            newInstance.setString("member", lccUserName).setInt("status", Constants.MEMBER_STATUS_LEAVE);
            roomMemberChange(newInstance);
            if (checkIsFloatLayout(changedLayoutName)) {
            	changeLayoutEx(changedLayoutName,checkIsFloatLayout(defLayoutName));
			}
//            if (changedLayoutName.equals(defLayoutName)) {
//                changeLayoutEx(changedLayoutName,checkIsFloatLayout(defLayoutName));
//            }
        }
    }
    
    public void roomMemberChange(ParaUtil paras)throws Exception{
        
        String member = paras.getValue("member");
        log.debug("member:"+member);
        Integer status = paras.getValue("status");
        LoginInfo loginInfo=DataUtil.getValue(DataUtil.Key.LoginInfo);
//        MemberListPanel panel = ViewManager.getComponent(MemberListPanel.class);
//        ChatMainPanel chatMainPanel = ViewManager.getComponent(ChatMainPanel.class);
        VideoViewsPanel viewsPanel = ViewManager.getComponent(VideoViewsPanel.class);
//        VideoViewsPanelItem videoViewsPanelItem = viewsPanel.getVideoItemPanel(member);
        ArrayList<VideoViewsPanelItem> videoViewsPanels = viewsPanel.getVideoViewsPanels(member);

        if (status.equals(Constants.MEMBER_STATUS_LEAVE)) {
            for (VideoViewsPanelItem videoViewsPanelItem : videoViewsPanels) {
                if (videoViewsPanelItem.isViewingVideo()) {
                    videoViewsPanelItem.disableVideo();
                }
                videoViewsPanelItem.setViewingVideo(false);
                videoViewsPanelItem.setLccUserName("");
                videoViewsPanelItem.setConfno("");
                videoViewsPanelItem.setNickName("");
                videoViewsPanelItem.getVideoPanel().setImg(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/com/lorent/lvmc/resource/images/nothing.png")));
                videoViewsPanelItem.getButtonPanel().setVisible(false);
                Thread.sleep(200);
                videoViewsPanelItem.repaint();
            }
            
            int findFirstItemInogicSortAndExistList = findFirstItemInogicSortAndExistList(member);
            if (findFirstItemInogicSortAndExistList != -1) {
            	VideoViewsPanelItem videoViewsPanelItem = logicExistAndSortedLccList.get(findFirstItemInogicSortAndExistList);
                if (videoViewsPanelItem.isViewingVideo()) {
                	videoViewsPanelItem .disableVideo();
    			}
                videoViewsPanelItem .setViewingVideo(false);
                videoViewsPanelItem .setLccUserName("");
                videoViewsPanelItem .setConfno("");
                videoViewsPanelItem .setNickName("");
                videoViewsPanelItem .getVideoPanel().setImg(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/com/lorent/lvmc/resource/images/nothing.png")));
                videoViewsPanelItem .getButtonPanel().setVisible(false);
			}
            
            viewsPanel.revalidate();
            viewsPanel.repaint();
            if (checkIsFloatLayout(changedLayoutName)) {
            	changeLayoutEx(changedLayoutName,true);
			}
            log.info("roomMemberChange: "+changedLayoutName+","+defLayoutName);
        }
    }

    public String getChangedLayoutName() throws Exception{
        return changedLayoutName;
    }
    
    /*public String getSavedLayoutName(String key) throws Exception{
        String layouts = ConfigUtil.getProperty("video.layout","");
        String startFlag = ":";
        String endFlag = ";";
        if(layouts==null || "".equals(layouts)){
            return VideoViewsPanel.defaultLayoutName;
        }
        int idx = layouts.indexOf(key);
        if(idx>-1){
            int endIdx = layouts.indexOf(endFlag,idx+key.length());
            if(endIdx<0){
                endIdx = layouts.length();
            }
            return layouts.substring(idx+key.length()+startFlag.length(), endIdx);
        }else{
            return VideoViewsPanel.defaultLayoutName;
        }
    }*/
    
    
    public String getSavedLayoutName(String key) throws Exception{
    	Map<String,String> layouts = ConfigUtil.getLayOutMap();
    	if(layouts.containsKey(key)){
    		return layouts.get(key);
    	}else{
    		return VideoViewsPanel.defaultLayoutName;
    	}
    }
    
    private boolean checkIsFloatLayout(String layoutName){
    	boolean flag = false;
		if (layoutName.equals("flow")) {
			flag = true;
		} else if (layoutName.equals("1xN")) {
			flag = true;
		} else if (layoutName.equals("1XN")) {
			flag = true;
		} else if (layoutName.equals("2XN")) {
			flag = true;
		} else if (layoutName.equals("3XN")) {
			flag = true;
		} else if (layoutName.equals("1+N")) {
			flag = true;
		}
		return flag;
    }
    
    public void changeLayout(String layoutName) throws Exception{
    	changeLayoutEx(layoutName, false);
    }
    
    public void changeLayoutEx(String layoutName,boolean resort) throws Exception {
        log.info("改变布局 layoutName:"+layoutName+" , "+resort);
        if (layoutName == null || layoutName.equals("")) {
            return;
        }
        
        
        VideoViewsPanel viewsPanel = ViewManager.getComponent(VideoViewsPanel.class);
        JPanel videosPanel = viewsPanel.getVideosPanel();
        
//        SortLccList();
        printLogicIndex();
        ArrayList<VideoViewsPanelItem> logicSortAndExistVideoItems = getLogicSortAndExistVideoItems();
        
        
        ArrayList<VideoViewsPanelItem> videoItemPanels = viewsPanel.getVideoItemPanels();
        int oldsize = videoItemPanels.size();
        ArrayList<VideoViewsPanelItem> sortedAndExistVideoItemPanels = viewsPanel.getSortedAndExistVideoItemPanels();
        int panelItemsCount = viewsPanel.getVideoItemCount();
        int viewCount = 1;
//        boolean gridBagLayoutFlag1 = false;
        int layoutflag = 0;
        
        if (changedLayoutName.equals(layoutName)) {
            logicSortAndExistVideoItems = videoItemPanels;
        }
        
        changedLayoutName = layoutName;
        if (layoutName.equals("2X2")) {
            videoItemPanels = logicSortAndExistVideoItems;
            viewCount = 4;
            videosPanel.setLayout(new java.awt.GridLayout(2, 2));
        } else if (layoutName.equals("3X3")) {
            videoItemPanels = logicSortAndExistVideoItems;
            viewCount = 9;
            videosPanel.setLayout(new java.awt.GridLayout(3, 3));
        } else if (layoutName.equals("1X1")) {
            videoItemPanels = logicSortAndExistVideoItems;
            viewCount = 1;
            videosPanel.setLayout(new java.awt.GridLayout(1, 1));
        } else if (layoutName.equals("flow")) {
            videoItemPanels = sortedAndExistVideoItemPanels;
            viewCount = videoItemPanels.size();
            videosPanel.setLayout(new java.awt.FlowLayout());
        } else if (layoutName.equals("4X4")) {
            videoItemPanels = logicSortAndExistVideoItems;
            viewCount = 16;
            videosPanel.setLayout(new java.awt.GridLayout(4, 4));
        } else if (layoutName.equals("5X5")) {
            videoItemPanels = logicSortAndExistVideoItems;
            viewCount = 25;
            videosPanel.setLayout(new java.awt.GridLayout(5, 5));
        } else if (layoutName.equals("6X6")) {
            videoItemPanels = logicSortAndExistVideoItems;
            viewCount = 36;
            videosPanel.setLayout(new java.awt.GridLayout(6, 6));
        } else if (layoutName.equals("1+5")) {
            videoItemPanels = logicSortAndExistVideoItems;
            viewCount = 6;
            videosPanel.setLayout(new GridBagLayout());
//            gridBagLayoutFlag1 = true;
            layoutflag = 1;
        } else if (layoutName.equals("1+7")) {
            videoItemPanels = logicSortAndExistVideoItems;
            viewCount = 8;
            videosPanel.setLayout(new GridBagLayout());
//            gridBagLayoutFlag1 = true;
            layoutflag = 1;
        } else if (layoutName.equals("1+9")) {
            videoItemPanels = logicSortAndExistVideoItems;
            viewCount = 10;
            videosPanel.setLayout(new GridBagLayout());
//            gridBagLayoutFlag1 = true;
            layoutflag = 1;
        }else if(layoutName.equals("1+12")){
        	videoItemPanels = logicSortAndExistVideoItems;
            viewCount = 13;
            videosPanel.setLayout(new GridBagLayout());
        	layoutflag = 2;
        }else if(layoutName.equals("2+8")){
        	videoItemPanels = logicSortAndExistVideoItems;
            viewCount = 10;
            videosPanel.setLayout(new GridLayout(2, 2));
        	layoutflag = 3;
        }else if(layoutName.equals("3+4")){
        	videoItemPanels = logicSortAndExistVideoItems;
            viewCount = 7;
            videosPanel.setLayout(new GridLayout(2,2));
        	layoutflag = 4;
        }else if (layoutName.equals("1+N")) {
        	if (resort) {
        		videoItemPanels = sortedAndExistVideoItemPanels;
			}
        	else{
        		videoItemPanels = logicSortAndExistVideoItems;
        	}
            panelItemsCount = sortedAndExistVideoItemPanels.size();
            viewCount = (panelItemsCount < 4) ? 4 : panelItemsCount;
            if (viewCount % 2 != 0) {
                viewCount = viewCount + 1;
            }
            videosPanel.setLayout(new GridBagLayout());
//            gridBagLayoutFlag1 = true;
            layoutflag = 1;
        }else if (layoutName.equals("1xN")) {
        	if (resort) {
        		videoItemPanels = sortedAndExistVideoItemPanels;
			}
        	else{
        		videoItemPanels = logicSortAndExistVideoItems;
        	}
            viewCount = (videoItemPanels.size() <= 4)? 4:videoItemPanels.size();
            videosPanel.setLayout(new java.awt.GridLayout(0, 1));
        }
        
        videosPanel.removeAll();
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
//        viewsPanel.clearVideoViewPanelItemList();
        
        if (checkIsFloatLayout(layoutName) && resort) {
			if (videoItemPanels.size() < viewCount) {
				int count = viewCount - videoItemPanels.size();
				for (int i = 0; i < count; i++) {
					videoItemPanels.add(null);
				}
			}
		}
        
        for (int i = 0; i < viewCount; i++) {
            VideoViewsPanelItem videoItemPanel = null;
            if (i <= videoItemPanels.size() - 1) {
                videoItemPanel = videoItemPanels.get(i);
            }
            if (videoItemPanel == null) {
                videoItemPanel = new VideoViewsPanelItem();
                videoItemPanel.setEnabled(false);
                videoItemPanel.setLccUserName("");
                videoItemPanel.setNickName("");
            }
            videoItemPanel.setVideoIndex(i);
            java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
            int base = 0;
            switch (layoutflag) {
			case 1://1+N
                base = (viewCount / 2)-1;
                if (i == 0) {
                    gridBagConstraints.gridx = 0;
                    gridBagConstraints.gridy = 0;
                    gridBagConstraints.gridwidth = base;
                    gridBagConstraints.gridheight = base;
                    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
                    gridBagConstraints.weightx = base;
                    gridBagConstraints.weighty = base;
                }
                else{
                    gridBagConstraints.gridx = (i <= base) ? base : (i - base - 1);
                    gridBagConstraints.gridy = (i > base) ? base : (i - 1);
                    gridBagConstraints.gridwidth = 1;
                    gridBagConstraints.gridheight = 1;
                    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
                    gridBagConstraints.weightx = 1.0;
                    gridBagConstraints.weighty = 1.0;
                }
                videosPanel.add(videoItemPanel,gridBagConstraints);
				break;

			case 2://12-1
//				base = (viewCount / 2)-1;
				gridBagConstraints.gridwidth = 1;
                gridBagConstraints.gridheight = 1;
				if (i < 5) {
					gridBagConstraints.gridx = i % 4;
					gridBagConstraints.gridy = i /4;
				} else if (i == 5) {
					gridBagConstraints.gridx = 1;
					gridBagConstraints.gridy = 1;
					gridBagConstraints.gridwidth = 2;
                    gridBagConstraints.gridheight = 2;
				} else if (i == 6 || i == 7) {
					gridBagConstraints.gridx = (i + 1) % 4;
					gridBagConstraints.gridy = (i + 1) / 4;
				} else {
					gridBagConstraints.gridx = (i + 3) % 4;
					gridBagConstraints.gridy = (i + 3) / 4;
				}
                gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.weighty = 1.0;
                videosPanel.add(videoItemPanel,gridBagConstraints);
				break;
				
			case 3://2+8
				if (i == 2) {
					jPanel1.setLayout(new GridLayout(2, 2));
					jPanel1.setName("VideoGrid");
					videosPanel.add(jPanel1);
				}
				if (i == 4) {
					jPanel2.setLayout(new GridLayout(2, 2));
					jPanel2.setName("VideoGrid");
					videosPanel.add(jPanel2);
				}
				if (i < 2) {
					videosPanel.add(videoItemPanel);
				}else{
					if ((2 <= i && i <= 3) || (6 <= i && i <= 7)) {
						jPanel1.add(videoItemPanel);
					}
					else{
						jPanel2.add(videoItemPanel);
					}
				}
				
				break;
				
			case 4://3+4
				if (i == 3) {
					jPanel1.setLayout(new GridLayout(2, 2));
					jPanel1.setName("VideoGrid");
					videosPanel.add(jPanel1);
				}
				if (i < 3) {
    				videosPanel.add(videoItemPanel);
				}else{
					jPanel1.add(videoItemPanel);
				}
				break;
				
			default:
				videosPanel.add(videoItemPanel);
				break;
			}
            
            if (videoItemPanel.isViewingVideo()) {
//                videoItemPanel.enableVideo();
                videoItemPanel.changeVideo();
            }
        }
        viewsPanel.revalidate();
        viewsPanel.repaint();
        SortLccList();
    }
    
    public void changeLayoutExt(String layoutName) throws Exception {
        log.info("改变布局ext layoutName:"+layoutName);
        if (layoutName == null || layoutName.equals("") || layoutName.equals(changedLayoutName)) {
            return;
        }
//        ArrayList<String> namelist = (ArrayList<String>) ControllerFacade.execute("layoutController", "getLayoutNames");
        
        if(layoutName.equals(defLayoutName)){
            ControllerFacade.execute("layoutController", "changeLayoutOnly",DataUtil.getValue(DataUtil.Key.DockingLayoutMeetingPanel), layoutName);
        }
        else{
            if(changedLayoutName.equals(defLayoutName)){
                ControllerFacade.execute("layoutController", "changeLayoutOnly",DataUtil.getValue(DataUtil.Key.DockingLayoutMeetingPanel), "Not1xN");
            }
        }
        changeLayout(layoutName);
//        VideoViewsPanelItem individualItem = ViewManager.getComponent(VideoViewsPanelItem.class);
//        individualItem.changeVideo();
//        individualItem.repaint();
    }
    
    public void maxVideoItemPanel(VideoViewsPanelItem panel) throws Exception{
        log.debug("maxVideoItemPanel "+panel);
        DockingLayoutMeetingPanel dockingPanel = DataUtil.getValue(DataUtil.Key.DockingLayoutMeetingPanel);
        //        dockingPanel.addFloatingPanel(panel, "test", "maxVideo", null);
        //        individualVideoItemPanel = new VideoViewsPanelItem();
        VideoViewsPanel videoViewsPanel = ViewManager.getComponent(VideoViewsPanel.class);
        if (panel.isViewingVideo()) {
            dockingPanel.restoreView("individualvideopanel");
            VideoViewsPanelItem videopanel = ViewManager.getComponent(VideoViewsPanelItem.class);

            videopanel.setMaximizeable(false);
            videopanel.setViewingVideo(true);
            videopanel.setConfno(panel.getConfno());
            videopanel.setLccUserName(panel.getLccUserName());
            videopanel.setNickName(panel.getNickName());
            videopanel.getVideoPanel().setImg(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/com/lorent/lvmc/resource/images/screen.png")));
            Thread.sleep(200);
            videopanel.getVideoPanel().DrawImage();
            Thread.sleep(100);
            videopanel.enableVideo();
            videopanel.revalidate();
            videopanel.repaint();
        }
        
        //        dockingPanel.addPanel(videoViewsPanelItem, "独立视频窗口", "maxVideoPanel", null);
    }
    
    public void minVideoItemPanel(VideoViewsPanelItem panel) throws Exception{
        log.debug("minVideoItemPanel "+panel);
    }
    
    public void showMemberVideo(MemberListItem memberListItem) throws Exception{
        boolean flag = false;
        MemberDto data = memberListItem.getData();
        VideoViewsPanel videoViewsPanel = ViewManager.getComponent(VideoViewsPanel.class);
        if(isOpenVideo(data.getName())){
            return;
        }
        Integer lccstate = (java.lang.Integer) ControllerFacade.execute("phoneController", "getLccState");
        if (lccstate != PhoneEvent.PHONE_STATE_CALLING) {
			JOptionPane.showMessageDialog(null, StringUtil.getErrorString("MemberListPanel.lccNotConnected"));
        	return;
		}
        List<VideoViewsPanelItem> items = videoViewsPanel.getVideoItemPanels();//videoViewsPanel.getVideoViewPanelItemList();
        for (Iterator<VideoViewsPanelItem> it = items.iterator(); it.hasNext();) {
            VideoViewsPanelItem item = it.next();
            if(item.getLccUserName()==null || item.getLccUserName().equals("")){
                int findFirstItemInogicSortAndExistList = findFirstItemInogicSortAndExistList(data.getName());
                if (findFirstItemInogicSortAndExistList != -1) {
                    removeFromVideoPanel(data.getName());
                    logicExistAndSortedLccList.set(findFirstItemInogicSortAndExistList, new VideoViewsPanelItem());
                }
                loadVideoIntoVideoViewsPanelItem(data,item);
                reflash(videoViewsPanel);
                int videoViewsPanelItemIndex = videoViewsPanel.getVideoViewsPanelItemIndex(data.getName());
                if (videoViewsPanelItemIndex != -1) {
                    logicExistAndSortedLccList.set(videoViewsPanelItemIndex, item);
                }
                printLogicIndex();
                flag = true;
                return;
            }
        }
        if(flag == false){
            if (changedLayoutName.equals(defLayoutName)) {
//                ArrayList<VideoViewsPanelItem> sortedAndExistVideoItemPanels = videoViewsPanel.getSortedAndExistVideoItemPanels();
                VideoViewsPanelItem addVideoIntoVideoViewsPanel = addVideoIntoVideoViewsPanel(DataUtil.getLoginInfo().getConfno(), data.getName(), data.getNickname());
                int videoViewsPanelItemIndex = videoViewsPanel.getVideoViewsPanelItemIndex(addVideoIntoVideoViewsPanel);
                if (videoViewsPanelItemIndex != -1) {
                    logicExistAndSortedLccList.set(videoViewsPanelItemIndex, addVideoIntoVideoViewsPanel);
                }
                printLogicIndex();
            }
            else{
                JOptionPane.showMessageDialog(ViewManager.getComponent(MainFrame.class), StringUtil.getErrorString("availabel.videoPanelItem.notenough"));
            }
            return;
        }
    }
    
    public void reflash(VideoViewsPanel videoViewsPanel){
        videoViewsPanel.revalidate();
        videoViewsPanel.repaint();
    }
    
    public void loadVideoIntoVideoViewsPanelItem(MemberDto data,VideoViewsPanelItem targetPanelItem) throws Exception{
        targetPanelItem.setConfno(DataUtil.getLoginInfo().getConfno());
        java.awt.Dimension originSize = targetPanelItem.getPreferredSize();
        if (targetPanelItem.isViewingVideo()) {
            targetPanelItem.disableVideo();
        }
        targetPanelItem.setLccUserName(data.getName());
        targetPanelItem.setNickName(data.getNickname());
        targetPanelItem.getVideoPanel().setImg(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/com/lorent/lvmc/resource/images/screen.png")));
        Thread.sleep(100);
        targetPanelItem.getVideoPanel().DrawImage();
        Thread.sleep(100);
        targetPanelItem.enableVideo();
        targetPanelItem.setViewingVideo(true);
        targetPanelItem.getButtonPanel().setVisible(true);
        targetPanelItem.setPreferredSize(originSize);
    }
    
    private VideoViewsPanelItem addVideoIntoVideoViewsPanel(String confno, String lccUserName, String nickName) throws Exception{
        VideoViewsPanel videoViewsPanel = ViewManager.getComponent(VideoViewsPanel.class);
        VideoViewsPanelItem addPanelItem = new VideoViewsPanelItem();
//                    addPanelItem.setEnabled(false);
        addPanelItem.setConfno(confno);
        addPanelItem.setLccUserName(lccUserName);
        addPanelItem.setNickName(nickName);
//                    addPanelItem.getVideoPanel().setImg(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/com/lorent/lvmc/resource/images/screen.png")));
        addPanelItem.getVideoPanel().setImg(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/com/lorent/lvmc/resource/images/screen.png")));
        videoViewsPanel.getVideosPanel().add(addPanelItem);
        Thread.sleep(100);
        addPanelItem.getVideoPanel().DrawImage();
//                    videoViewsPanel.getVideosPanel().repaint();
        Thread.sleep(100);
        addPanelItem.enableVideo();
        addPanelItem.setViewingVideo(true);
        addPanelItem.getButtonPanel().setVisible(true);
        videoViewsPanel.revalidate();
        videoViewsPanel.repaint();
        return addPanelItem;
    }
    
    public boolean isOpenVideo(String name) throws Exception{
        boolean flag = false;
        VideoViewsPanel videoViewsPanel = ViewManager.getComponent(VideoViewsPanel.class);
        ArrayList<VideoViewsPanelItem> itempanelList = videoViewsPanel.getVideoViewsPanels(name);
        if (itempanelList.size() > 0) {
            flag = true;
            return flag;
        }
//        VideoViewsPanelItem individual = ViewManager.getComponent(VideoViewsPanelItem.class);
//        if(individual.getLccUserName().equals(name)){
//            flag = true;
//            return flag;
//        }
        return flag;
    }
    
    public void videoViewItemOnDrop(DropTargetDropEvent dtde, Component source, 
            Map<String, Object> properties,VideoViewsPanelItem targetPanelItem) throws Exception {
        log.debug("videoViewItemOnDrop source:"+source+", dropto:"+targetPanelItem);
        
        Integer lccstate = (java.lang.Integer) ControllerFacade.execute("phoneController", "getLccState");
        if (lccstate != PhoneEvent.PHONE_STATE_CALLING) {
			JOptionPane.showMessageDialog(null, StringUtil.getErrorString("MemberListPanel.lccNotConnected"));
        	return;
		}
        
        if (source instanceof JList) {
            MemberListPanel memberListPanel = ViewManager.getComponent(MemberListPanel.class);
            JList memberList = memberListPanel.getMemberList();
            if (source.equals(memberList) && targetPanelItem != null) {
                VideoViewsPanel videoViewsPanel = ViewManager.getComponent(VideoViewsPanel.class);
                MemberListItem selectedValue = (MemberListItem) memberList.getSelectedValue();
                MemberDto data = selectedValue.getData();
//                ArrayList<VideoViewsPanelItem> itempanelList = videoViewsPanel.getVideoViewsPanels(data.getName());
//                if (itempanelList.size() > 0) {
//                    return;
//                }
//                VideoViewsPanelItem individual = ViewManager.getComponent(VideoViewsPanelItem.class);
//                if(individual.getLccUserName().equals(data.getName())){
//                    return;
//                }
                if(isOpenVideo(data.getName())){
                    return;
                }
                ArrayList<VideoViewsPanelItem> videoItemPanels = videoViewsPanel.getVideoItemPanels();
                ArrayList<VideoViewsPanelItem> sortedAndExistVideoItemPanels = videoViewsPanel.getSortedAndExistVideoItemPanels();
                log.info("videoItemPanels.size: "+videoItemPanels.size()+" sortedAndExistVideoItemPanels.size:"+sortedAndExistVideoItemPanels.size());
                if (changedLayoutName.equals(defLayoutName) && videoItemPanels.size() <= sortedAndExistVideoItemPanels.size()) { //1xN 并新增
//                if (changedLayoutName.equals("1xN")) {
                    VideoViewsPanelItem addVideoIntoVideoViewsPanel = addVideoIntoVideoViewsPanel(DataUtil.getLoginInfo().getConfno(), data.getName(), data.getNickname());
                    reflash(videoViewsPanel);
                    int videoViewsPanelItemIndex = videoViewsPanel.getVideoViewsPanelItemIndex(addVideoIntoVideoViewsPanel);
                    if (videoViewsPanelItemIndex != -1) {
                        logicExistAndSortedLccList.set(videoViewsPanelItemIndex, addVideoIntoVideoViewsPanel);
                    }
                    printLogicIndex();
                } else {
                    int findFirstItemInogicSortAndExistList = findFirstItemInogicSortAndExistList(data.getName());
                    if (findFirstItemInogicSortAndExistList != -1) {
                        removeFromVideoPanel(data.getName());
                        logicExistAndSortedLccList.set(findFirstItemInogicSortAndExistList, new VideoViewsPanelItem());
                    }
                    loadVideoIntoVideoViewsPanelItem(data, targetPanelItem);
                    reflash(videoViewsPanel);
                    int videoViewsPanelItemIndex = videoViewsPanel.getVideoViewsPanelItemIndex(data.getName());
                    if (videoViewsPanelItemIndex != -1) {
                        logicExistAndSortedLccList.set(videoViewsPanelItemIndex, targetPanelItem);
                    }
                    printLogicIndex();
                }
                
            }
        }
        else if (source instanceof VideoViewsPanelItem) {
            VideoViewsPanelItem sourceItem = (VideoViewsPanelItem) source;
            if (targetPanelItem != null) {
            	java.awt.Dimension originSize = targetPanelItem.getPreferredSize();
                VideoViewsPanel videoViewsPanel = ViewManager.getComponent(VideoViewsPanel.class);
                String meetingID1 = sourceItem.getConfno();
                String sTemp1 = sourceItem.getLccUserName();
                String sNickName1 = sourceItem.getNickName();
                boolean viewing1 = sourceItem.isViewingVideo();
                String sTemp2 = targetPanelItem.getLccUserName();
                String sNickName2 = targetPanelItem.getNickName();
                String meetingID2 = targetPanelItem.getConfno();
                boolean viewing2 = targetPanelItem.isViewingVideo();
                int videoViewsPanelItemIndex1 = -1;
                int videoViewsPanelItemIndex2 = -1;
                log.debug("stemp1:" + sTemp1 + " , stemp2:" + sTemp2);

                VideoViewsPanelItem individual = ViewManager.getComponent(VideoViewsPanelItem.class);
//                videoViewsPanelItemIndex1 = videoViewsPanel.getVideoViewsPanelItemIndex(sourceItem);
//                videoViewsPanelItemIndex2 = videoViewsPanel.getVideoViewsPanelItemIndex(targetPanelItem);
                videoViewsPanelItemIndex1 = sourceItem.getVideoIndex();
                videoViewsPanelItemIndex2 = targetPanelItem.getVideoIndex();
                logicExistAndSortedLccList.set(videoViewsPanelItemIndex1, sourceItem);
                logicExistAndSortedLccList.set(videoViewsPanelItemIndex2, targetPanelItem);
                log.debug("sTemp2-> sourceIndex:"+videoViewsPanelItemIndex1+"  , sTemp1-> targetIndex:"+videoViewsPanelItemIndex2);
                
                if (viewing1) {
//                    sourceItem.disableVideo();
                    sourceItem.setViewingVideo(false);
                }
                if (viewing2) {
//                    targetPanelItem.disableVideo();
                    targetPanelItem.setViewingVideo(false);
                }
                targetPanelItem.setLccUserName(sTemp1);
                targetPanelItem.setNickName(sNickName1);
                targetPanelItem.setConfno(meetingID1);

                sourceItem.setLccUserName(sTemp2);
                sourceItem.setNickName(sNickName2);
                sourceItem.setConfno(meetingID2);
                
                if (sTemp1 == null || sTemp1.equals("")) {
                    targetPanelItem.getVideoPanel().setImg(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/com/lorent/lvmc/resource/images/nothing.png")));
                    targetPanelItem.getButtonPanel().setVisible(false);
                } else {
                    targetPanelItem.getVideoPanel().setImg(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/com/lorent/lvmc/resource/images/screen.png")));
//                    targetPanelItem.getVideoPanel().DrawImage();
                    targetPanelItem.getButtonPanel().setVisible(true);
                }
                if (viewing1) {
                	targetPanelItem.setViewingVideo(true);
                	targetPanelItem.changeVideo();
                	Thread.sleep(100);
                }
                if (viewing2) {
                	sourceItem.setViewingVideo(true);
                	sourceItem.changeVideo();
                	Thread.sleep(100);
                }
                if (sTemp2 == null || sTemp2.equals("")) {
                    sourceItem.getVideoPanel().setImg(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/com/lorent/lvmc/resource/images/nothing.png")));
                    sourceItem.getButtonPanel().setVisible(false);
                } else {
                    sourceItem.getVideoPanel().setImg(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/com/lorent/lvmc/resource/images/screen.png")));
//                    sourceItem.getVideoPanel().DrawImage();
                    sourceItem.getButtonPanel().setVisible(true);
                }

                if (changedLayoutName.equals(defLayoutName) && targetPanelItem.equals(individual)){
                    changeLayout(changedLayoutName);
                }
                targetPanelItem.setPreferredSize(originSize);
//                reflash(videoViewsPanel);
                printLogicIndex();
            }
        }
        else if(source instanceof VideoViewsPanel){
            
        }
    }
    
    
    private ArrayList<VideoViewsPanelItem> getLogicSortAndExistVideoItems(){
        ArrayList<VideoViewsPanelItem> resulList = new ArrayList<VideoViewsPanelItem>();
        for (int i = 0; i < logicExistAndSortedLccList.size(); i++) {
            VideoViewsPanelItem item = logicExistAndSortedLccList.get(i);
            if (item.getLccUserName() != null && !item.getLccUserName().equals("")) {
                resulList.add(item);
            }
        }
        return resulList;
    }
    
    private int findFirstItemInogicSortAndExistList(String lccUserName){
        for (int i = 0; i < logicExistAndSortedLccList.size(); i++) {
            VideoViewsPanelItem get = logicExistAndSortedLccList.get(i);
            if (get.getLccUserName() != null && get.getLccUserName().equals(lccUserName)) {
                return  i;
            }
        }
        return -1;
    }
    
    private void SortLccList(){
        String stemp = "";
        ArrayList<VideoViewsPanelItem> resulList = new ArrayList<VideoViewsPanelItem>();
        for (int i = 0; i < logicExistAndSortedLccList.size(); i++) {
            VideoViewsPanelItem item = logicExistAndSortedLccList.get(i);
            if (item.getLccUserName() != null && !item.getLccUserName().equals("")) {
                resulList.add(item);
            }
        }
        for (int i = 0; i < logicExistAndSortedLccList.size(); i++) {
            VideoViewsPanelItem item = logicExistAndSortedLccList.get(i);
            if (item.getLccUserName() == null || item.getLccUserName().equals("")) {
                resulList.add(item);
            }
        }
        logicExistAndSortedLccList = resulList;
    }
    
    private void printLogicIndex(){
        String name = "printLogicIndex: ";
        for (int i = 0; i < logicExistAndSortedLccList.size(); i++) {
            name += i+" ["+logicExistAndSortedLccList.get(i).getLccUserName() +"],";
        }
        log.debug(name);
    }
    
    public void disconnectAllVideo() throws Exception{
    	log.info("disconnectAllVideo");
    	ArrayList<VideoViewsPanelItem> logicSortAndExistVideoItems = getLogicSortAndExistVideoItems();
    	for (VideoViewsPanelItem videoViewsPanelItem : logicSortAndExistVideoItems) {
    		videoViewsPanelItem.disableVideo();
//    		videoViewsPanelItem.setViewingVideo(false);
//    		videoViewsPanelItem.getVideoPanel().DrawImage();
		}
    	VideoViewsPanel videoViewsPanel = ViewManager.getComponent(VideoViewsPanel.class);
    	videoViewsPanel.revalidate();
    	videoViewsPanel.repaint();
    }
    
    public void reconnectVideos(String[] username) throws Exception{
    	for (String lccUserName : username) {
			int findFirstItemInogicSortAndExistList = findFirstItemInogicSortAndExistList(lccUserName);
			if (findFirstItemInogicSortAndExistList != -1) {
				VideoViewsPanelItem videoViewsPanelItem = logicExistAndSortedLccList.get(findFirstItemInogicSortAndExistList);
				if (videoViewsPanelItem.isViewingVideo()) {
					videoViewsPanelItem.disableVideo();
					Thread.sleep(2000);
	    			videoViewsPanelItem.enableVideo();
//					videoViewsPanelItem.changeVideo();
				}
			}
		}
    	VideoViewsPanel videoViewsPanel = ViewManager.getComponent(VideoViewsPanel.class);
    	videoViewsPanel.revalidate();
    	videoViewsPanel.repaint();
    }
    
    public void reconnectAllVideo() throws Exception{
    	ArrayList<VideoViewsPanelItem> logicSortAndExistVideoItems = getLogicSortAndExistVideoItems();
    	for (VideoViewsPanelItem videoViewsPanelItem : logicSortAndExistVideoItems) {
    		if (videoViewsPanelItem.isViewingVideo()) {
    			videoViewsPanelItem.enableVideo();
			}
		}
    	VideoViewsPanel videoViewsPanel = ViewManager.getComponent(VideoViewsPanel.class);
    	videoViewsPanel.revalidate();
    	videoViewsPanel.repaint();
    }
    
    public DockingWindowAdapter getIndividualVideoWindowAdapter(){
        return  new DockingWindowAdapter(){

            @Override
            public void windowMaximized(DockingWindow dw) {
                log.debug("windowMaximized , title:"+dw.getTitle()+" panelTitle:"+individualPanelTitle);
                
                if (dw.getTitle().indexOf(individualPanelTitle) != -1) {
//                    String selectedText = (String) ControllerFacade.execute("videoViewsController", "getChangedLayoutName");//layoutComboBox.getSelectedItem().toString();
//                    log.debug("windowMaximized selectedText:" + selectedText);
//                    ControllerFacade.execute("videoViewsController", "changeLayout", selectedText);
                    log.info("windowMaximized title == "+individualPanelTitle);
                    VideoViewsPanelItem component;
                    try {
                        component = ViewManager.getComponent(VideoViewsPanelItem.class);
                        if (component.isViewingVideo()) {
                            component.changeVideo();
                            log.info("windowMaximized component.changeVideo()"+component.getLccUserName());
                        }
                    } catch (Exception ex) {
                        log.error("windowMaximized ",ex);
                    }
                }
            }

            @Override
            public void windowShown(DockingWindow dw) {
                log.debug("windowShown , title:"+dw.getTitle()+" panelTitle:"+individualPanelTitle);
                if (dw.getTitle().indexOf(individualPanelTitle) != -1) {
//                    final String selectedText = layoutComboBox.getSelectedItem().toString();
//                    final String selectedText = (String) ControllerFacade.execute("videoViewsController", "getChangedLayoutName");
//                    log.info("windowShown selectedText:" + selectedText);
//                    log.debug("DockingWindow:" + dw + ",title:" + dw.getTitle() + ",name:" + dw.getName());
//                    ControllerFacade.execute("videoViewsController", "changeLayout", selectedText);
                    log.info("windowShown title == "+individualPanelTitle);
                    Thread thread = new Thread() {

                        @Override
                        public void run() {
                            try {
                                Thread.sleep(900);
                                VideoViewsPanelItem component = ViewManager.getComponent(VideoViewsPanelItem.class);
                                if(component.isViewingVideo()){
                                    component.changeVideo();
                                }
                            } catch (Exception ex) {
                                log.error("windowShown",ex);
                            }
                        }
                    };
                    thread.start();
                }
            }

            @Override
            public void windowRestored(DockingWindow dw) {
                log.debug("windowRestored , title:"+dw.getTitle()+" panelTitle:"+individualPanelTitle);
                if (dw.getTitle().indexOf(individualPanelTitle) != -1) {
//                    String selectedText = (String) ControllerFacade.execute("videoViewsController", "getChangedLayoutName");//layoutComboBox.getSelectedItem().toString();
//                    log.debug("windowRestored selectedText:" + selectedText);
//                    ControllerFacade.execute("videoViewsController", "changeLayout", selectedText);
                    log.info("windowRestored title == "+individualPanelTitle);
                    VideoViewsPanelItem component;
                    try {
                        component = ViewManager.getComponent(VideoViewsPanelItem.class);
                        if (component.isViewingVideo()) {
                            component.changeVideo();
                            log.info("windowRestored component.changeVideo()"+component.getLccUserName());
                        }
                    } catch (Exception ex) {
                        log.error("windowRestored",ex);
                    }
                }
            }

            @Override
            public void windowClosed(DockingWindow dw) {
                log.debug("windowClosed , title:"+dw.getTitle()+" , panelTitle:"+individualPanelTitle);
                if (dw.getTitle().indexOf(individualPanelTitle) != -1) {
                    VideoViewsPanelItem component;
                    try {
                        component = ViewManager.getComponent(VideoViewsPanelItem.class);
                        removeFromVideoPanel(component.getLccUserName());
                    } catch (Exception ex) {
                        log.error("windowClosed",ex);
                    }
                }
            }

            @Override
            public void windowClosing(DockingWindow dw) throws OperationAbortedException {
                log.debug("windowClosing , title:"+dw.getTitle()+" , panelTitle:"+individualPanelTitle);
            }

            
        };
    }
    public static void main(String args[]) {
    	JFrame jFrame = new JFrame();
		jFrame.setSize(1024, 768);
//		jFrame.add(new TestLayoutPanel());
		jFrame.setVisible(true);

		int x, y;
		
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridLayout(2, 2));
		JPanel jPanel2 = new JPanel();
		jPanel2.setLayout(new GridLayout(2, 2));
		jFrame.add(jPanel);
		
		for (int i = 0; i < 7; i++) {
			java.awt.GridBagConstraints tempGridBagConstraints = new java.awt.GridBagConstraints();
			tempGridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
			VideoViewsPanelItem videoItemPanel = new VideoViewsPanelItem();
            videoItemPanel.setEnabled(false);
            videoItemPanel.setLccUserName("");
            videoItemPanel.setNickName("");
			if (i == 3) {
				jPanel.add(jPanel2);
			}
			
			if (i < 3) {
				x = i % 2 + i % 2;
				y = i / 2 + i / 2;
				tempGridBagConstraints.gridwidth = 2;
				tempGridBagConstraints.gridheight = 2;
				tempGridBagConstraints.weightx = 2.0;
				tempGridBagConstraints.weighty = 2.0;
				tempGridBagConstraints.gridx = x;
				tempGridBagConstraints.gridy = y;
				jPanel.add(videoItemPanel);
			} else {
				x = 3 - (i % 2);
				y = (i - 3) / 2 + 2;
				tempGridBagConstraints.gridwidth = 1;
				tempGridBagConstraints.gridheight = 1;
				tempGridBagConstraints.weightx = 1.0;
				tempGridBagConstraints.weighty = 1.0;
				tempGridBagConstraints.gridx = x;
				tempGridBagConstraints.gridy = y;
				jPanel2.add(videoItemPanel);
			}
			
			
			
			
			
			
			
			
//			ImageAwtPanel imageAwtPanel = new ImageAwtPanel();
//			JPanel jPanel2 = new JPanel();
//			jPanel2.add(imageAwtPanel);
//			jPanel2.setBorder(BorderFactory.createEtchedBorder());
			
			
//			jPanel2.add(videoItemPanel, BorderLayout.CENTER);
			//			System.out.println("" + i + "     " + x + "," + y);
		}
		jFrame.setVisible(true);
    }
}
