/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.controller;

import com.lorent.lvmc.ui.DockingLayoutMeetingPanel;
import com.lorent.lvmc.ui.SaveLayoutDialog;
import com.lorent.lvmc.ui.VideoViewsPanel;
import com.lorent.lvmc.util.ConfigUtil;
import com.lorent.lvmc.util.DataUtil;
import com.lorent.lvmc.util.FileUtil;
import com.lorent.lvmc.util.StringUtil;
import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import net.infonode.docking.DockingWindow;
import net.infonode.docking.RootWindow;
import net.infonode.docking.View;
import net.infonode.docking.util.DockingUtil;
import net.infonode.docking.util.ViewMap;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class LayoutController extends BaseController {

    private static Logger log = Logger.getLogger(LayoutController.class);

    public void showSaveLayoutDialog(RootWindow rootWindow) throws Exception {
        log.info("显示输入布局名称界面");
        Class[] clazz = new Class[]{Frame.class, boolean.class};
        SaveLayoutDialog dialog = ViewManager.getComponent(SaveLayoutDialog.class, clazz, new Object[]{null, true});
        dialog.getTextInput().setText("");
//        dialog.setAlwaysOnTop(true);
//        dialog.setModal(true);
        dialog.setRootWindow(rootWindow);
        dialog.setVisible(true);
    }

    
    public void saveLayoutByButton(RootWindow rootWindow)throws Exception{
    	saveLayout(rootWindow, "enjoinlayout");
    }
    
    public void saveLayout(RootWindow rootWindow, String filename) throws Exception {
        log.info("保存当前布局:" + filename);
        String startFlag = ":";
        String endFlag = ";";
        if (filename == null || filename.equals("")) {
            throw new Exception(StringUtil.getErrorString("LayoutController.layoutNameIsNull"));
        }
        filename = filename.replaceAll(startFlag, "").replaceAll(endFlag, "");
        String filepath = com.lorent.lvmc.util.Constants.LayoutDataPath + "/" + filename + ".obj";
        File file = new File(filepath);
//        if (file.exists()) {
//            if (JOptionPane.showConfirmDialog(null, StringUtil.getUIString("SaveLayoutDialog.askCover")) != JOptionPane.YES_OPTION) {
//                return;
//            }
//        }
        FileOutputStream fileout = new FileOutputStream(filepath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileout);
        rootWindow.write(objectOutputStream);
        objectOutputStream.close();
        log.info("LayoutController.saveLayout  "+filepath);
        ConfigUtil.setProperty("LayoutName", filename);
        String selectedLayout = DataUtil.getVideoLayout();
        String layoutStr = ConfigUtil.getProperty("video.layout","");
        if(layoutStr==null||"".equals(layoutStr)){
            ConfigUtil.setProperty("video.layout", filename + startFlag + selectedLayout);
        }else{
            int idx = layoutStr.indexOf(filename);
            if(idx<0){
                layoutStr = layoutStr + endFlag + filename + startFlag + selectedLayout;
            }else{
                int startIdx = layoutStr.indexOf(startFlag,idx);
                if(startIdx>-1){
                    int endIdx = layoutStr.indexOf(endFlag,startIdx);
                    if(endIdx>-1){
                        layoutStr = layoutStr.substring(0,startIdx+startFlag.length()) + selectedLayout + layoutStr.substring(endIdx);
                    }else{
                        layoutStr = layoutStr.substring(0,startIdx+startFlag.length()) + selectedLayout;
                    }
                }
            }
            ConfigUtil.setProperty("video.layout", layoutStr);
        }
        this.showMessageDialog(null, StringUtil.getUIString("saveLayout.success"));
    }

    public void changeLayoutOnly(DockingLayoutMeetingPanel dockingLayoutMeetingPanel, String filename) throws Exception {
        log.info("改变当前布局至:" + filename);
        RootWindow rootWindow = dockingLayoutMeetingPanel.getRootWindow();
        if (filename != null && !filename.equals("")) {
            String filepath = com.lorent.lvmc.util.Constants.LayoutDataPath + "/" + filename + ".obj";
            File file = new File(filepath);
            if (!file.exists()) {
            	File layoutPath = new File(com.lorent.lvmc.util.Constants.LayoutDataPath);
            	layoutPath.mkdirs();
//            	FileUtil.fileCopy("/layouts/default.obj", filepath);
//            	com.lorent.common.util.FileUtil.localFileCopy(com.lorent.lvmc.util.Constants.AppPath+"/layouts/"+filename+".obj", filepath);
            	FileUtil.fileCopy("/com/lorent/lvmc/config/"+filename+".obj", filepath);
//                throw new Exception(filename + StringUtil.getErrorString("LayoutController.layoutNotExist"));
            }
            FileInputStream fileInputStream = new FileInputStream(filepath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            try {
                rootWindow.read(objectInputStream);
            } catch (Exception e) {
                log.error("changeLayoutOnly", e);
            }
            objectInputStream.close();
        }
        log.info("改变当前布局至:" + filename+" 结束");
    }
    
    public void changeLayout(DockingLayoutMeetingPanel dockingLayoutMeetingPanel, String filename) throws Exception {
        changeLayoutOnly(dockingLayoutMeetingPanel, filename);
        String selectedText = (String) ControllerFacade.execute("videoViewsController", "getSavedLayoutName", filename);//getSavedLayoutName(String) ControllerFacade.execute("videoViewsController", "getChangedLayoutName");
//        System.out.println("selectedText====================" + selectedText);
        VideoViewsPanel videoViewsPanel = ViewManager.getComponent(VideoViewsPanel.class);
        videoViewsPanel.getLayoutChoice().setSelectedItem(selectedText);
        ControllerFacade.execute("videoViewsController", "changeLayout", selectedText);
        ConfigUtil.setProperty("LayoutName", filename);
    }

    public ArrayList getLayoutNames() throws Exception {
        log.info("从目录获得所有已设置的布局");
        ArrayList namelist = new ArrayList();
        String filepath = com.lorent.lvmc.util.Constants.LayoutDataPath;
        File file = new File(filepath);
        if (!file.exists()) {
            file.mkdirs();
            FileUtil.fileCopy("/com/lorent/lvmc/config/default.obj", filepath + "/default.obj");//("/com/lorent/lvmc/config/default.obj"));
        } else if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            for (File thefile : listFiles) {
                if (thefile.isFile()) {
                    String name = thefile.getName();
                    String substring = name.substring(0, name.indexOf("."));
                    namelist.add(substring);
//                    log.debug(substring+""+thefile);
                }
            }
        }
        return namelist;
    }
}
