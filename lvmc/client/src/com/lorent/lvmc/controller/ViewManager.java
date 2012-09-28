/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorent.lvmc.controller;


import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.Window;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.infonode.docking.RootWindow;
import net.infonode.docking.theme.DockingWindowsTheme;
import net.infonode.docking.theme.ShapedGradientDockingTheme;

import org.apache.log4j.Logger;

import com.jtattoo.plaf.AbstractLookAndFeel;
import com.lorent.lvmc.ui.DockingLayoutMeetingPanel;
import com.lorent.lvmc.ui.MainFrame;
import com.lorent.lvmc.util.ConfigUtil;
import com.lorent.lvmc.util.Constants;
import com.lorent.lvmc.util.DataUtil;
import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;

/**
 *
 * @author jack
 */
public class ViewManager {

    private static Logger log = Logger.getLogger(ViewManager.class);
    private static Map<Class, Object> views = new HashMap<Class, Object>();

   
    public static <T>T getComponent(Class<T> clazz, Class partypes[], Object arglist[]) throws Exception{
        Object view = getView(clazz);
        if (view == null) {
            try {
                Constructor ct = clazz.getConstructor(partypes);
                view = ct.newInstance(arglist);
                views.put(clazz, view);
            } catch (Exception ex) {
                //log.error("", ex);
                throw ex;
            }
            return (T)view;
        } else {
            return (T)view;
        }
    }

    public static <T> T getComponent(Class<T> clazz) throws Exception{
        Object view = getView(clazz);
        if (view == null) {
            try {
                log.debug("创建对象：" + clazz.getName());
                view = clazz.newInstance();
                views.put(clazz, view);
            } catch (Exception ex) {
                //log.error("", ex);
                throw ex;
            }
            return (T)view;
        } else {
            return (T)view;
        }

    }

    public static void disposeComponent(Class clazz) throws Exception{
        Object o = getView(clazz);
        if (o == null) {
            return;
        }
        log.debug("销毁对象: "+ clazz.getName());
        views.remove(clazz);
        if(o instanceof Window){
            Window w = (Window)o;
            w.dispose();
        }
        if(o instanceof Panel){
            Panel p = (Panel)o;
            p.setVisible(false);
        }
        o = null;
    }

    public static void clearComponet() throws Exception{
        ArrayList<Class> list = new ArrayList<Class>();
        Iterator it = views.keySet().iterator();
        while(it.hasNext()) {
            Class key = (Class)it.next();
//            if(!key.equals(LccTrayIcon.class)){
//                list.add(key);
//            }
            list.add(key);
        }
        for(int i=0;i<list.size();i++){
            disposeComponent(list.get(i));
        }
        
    }
    
    public static void clearView(){
        views.clear();
    }

    private static Object getView(Class clazz) throws Exception{
        return views.get(clazz);
    }

    public static void main(String args[]) {
//        LoginNewFrame f = (LoginNewFrame)getComponent(com.lorent.desktoplcc.ui.LoginNewFrame.class);
//        System.out.println(f);
//        f.setVisible(true);
        JFrame f1 = new JFrame("aa");
        JFrame f2 = new JFrame("bbb");
        System.out.println(f1.hashCode() + "==============" + f2.hashCode());
        Integer i = new Integer(f1.hashCode());
        Integer f = new Integer(f2.hashCode());
        Map<Integer, JFrame> m = new HashMap<Integer, JFrame>();
        m.put(i, f1);
        m.put(f, f2);
        System.out.println(m.get(i).getTitle());
        System.out.println(m.get(f).getTitle());
        Map<String, String> ms = new HashMap<String, String>();
        String s = new String("qq");
        String d = new String("qq");
        ms.put(s, "aa");
        ms.put(d, "bbf");
        //System.out.println(ms.get(s));
    }

    public Image getImageInstance(String path) throws Exception{
        return Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(path));
    }

    //设置窗口显示屏幕中心位置
    public static void setWindowCenterLocation(Window w) throws Exception{
        if (w != null) {
            double width = (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - w.getPreferredSize().width) / 2;
            double height = (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - w.getPreferredSize().height) / 2;
            w.setLocation((int) width, (int) height);
        }
    }

    public static void changeLookAndFeel(final LookAndFeel lookAndFeel) throws Exception{
        final Iterator<Entry<Class, Object>> iterator = views.entrySet().iterator();
        try {
            UIManager.setLookAndFeel(lookAndFeel);
            if(DataUtil.getTopWindow()!=null && DataUtil.getTopWindow() instanceof JFrame){
            	((JFrame)DataUtil.getTopWindow()).getRootPane().updateUI();
            	SwingUtilities.updateComponentTreeUI(DataUtil.getTopWindow());
            	DataUtil.getTopWindow().validate();
            	DataUtil.getTopWindow().repaint();
            	log.error("改变 look and feel=============" + lookAndFeel.getClass());
            }
        } catch (UnsupportedLookAndFeelException ex) {
            log.error("changeLookAndFeel", ex);
        }
        while (iterator.hasNext()) {
            Entry<Class, Object> next = iterator.next();
            Object value = next.getValue();
            if (value instanceof java.awt.Component) {
                java.awt.Component win = (java.awt.Component) value;
                SwingUtilities.updateComponentTreeUI(win);
                win.repaint();
                /*if(value instanceof DockingLayoutMeetingPanel && UIManager.getLookAndFeel() instanceof NimRODLookAndFeel){
					((DockingLayoutMeetingPanel) value).getRootWindow()
							.setBorder(
									javax.swing.BorderFactory.createLineBorder(
											((NimRODLookAndFeel) UIManager
													.getLookAndFeel())
													.getControl(), 5));
                }
                if(value instanceof com.lorent.lvmc.ui.VideoViewsPanel){
//                	com.lorent.lvmc.ui.VideoViewsPanel videoPanel = (com.lorent.lvmc.ui.VideoViewsPanel)value;
//                	ArrayList<VideoViewsPanelItem> items = videoPanel.getVideoItemPanels();
//                	for(VideoViewsPanelItem item:items){
//                		SwingUtilities.updateComponentTreeUI(item.getVideoPanel());
//                		item.getVideoPanel().validate();
//                		item.getVideoPanel().repaint();
//                	}
//                	videoPanel.revalidate();
//                	videoPanel.repaint();
                	final String selectedText = (String) ControllerFacade.execute("videoViewsController","getChangedLayoutName");
					ControllerFacade.execute("videoViewsController","changeLayoutEx", selectedText,false);
                }*/
            }
            
        }
        
//        ConfigUtil.setProperty("DefaultLookAndFeelClassName", UIManager.getLookAndFeel().getClass().getName());
    }
    
    public static LookAndFeel getLookAndFeel(String lookAndFeelClassName) throws Exception{
        LookAndFeel newInstance = null;
        try {
            Class<?> forName = Class.forName(lookAndFeelClassName);
            newInstance = (LookAndFeel) forName.newInstance();
        }  catch (Exception ex) {
        	log.error("getLookAndFeel", ex);
        	newInstance = new WindowsClassicLookAndFeel();
        }
//        return  newInstance==null?new WindowsClassicLookAndFeel():newInstance;
//        com.nilo.plaf.nimrod.NimRODLookAndFeel lf =new com.nilo.plaf.nimrod.NimRODLookAndFeel();
//        com.nilo.plaf.nimrod.NimRODTheme nt = new com.nilo.plaf.nimrod.NimRODTheme();
//        nt.setFont(java.awt.Font.decode("SansSerif-PLAIN-12"));
//        lf.setCurrentTheme(nt);
//        return new com.nilo.plaf.nimrod.NimRODLookAndFeel();
        return newInstance;
    }
    
    
    public static void changeJTattooLookAndFeel(String lf){
    	if (lf.equals(Constants.PLAF_FAST)) {
            com.jtattoo.plaf.fast.FastLookAndFeel.setTheme("Default");
        } else if (lf.equals(Constants.PLAF_GRAPHITE)) {
            com.jtattoo.plaf.graphite.GraphiteLookAndFeel.setTheme("Default");
        } else if (lf.equals(Constants.PLAF_SMART)) {
            com.jtattoo.plaf.smart.SmartLookAndFeel.setTheme("Default");
        } else if (lf.equals(Constants.PLAF_ACRYL)) {
            com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Default");
        } else if (lf.equals(Constants.PLAF_AERO)) {
            com.jtattoo.plaf.aero.AeroLookAndFeel.setTheme("Default");
        } else if (lf.equals(Constants.PLAF_BERNSTEIN)) {
            com.jtattoo.plaf.bernstein.BernsteinLookAndFeel.setTheme("Default");
        } else if (lf.equals(Constants.PLAF_ALUMINIUM)) {
            com.jtattoo.plaf.aluminium.AluminiumLookAndFeel.setTheme("Default");
        } else if (lf.equals(Constants.PLAF_MCWIN)) {
            com.jtattoo.plaf.mcwin.McWinLookAndFeel.setTheme("Default");
        } else if (lf.equals(Constants.PLAF_MINT)) {
            com.jtattoo.plaf.mint.MintLookAndFeel.setTheme("Default");
        } else if (lf.equals(Constants.PLAF_HIFI)) {
            com.jtattoo.plaf.hifi.HiFiLookAndFeel.setTheme("Default");
        } else if (lf.equals(Constants.PLAF_NOIRE)) {
            com.jtattoo.plaf.noire.NoireLookAndFeel.setTheme("Default");
        } else if (lf.equals(Constants.PLAF_LUNA)) {
            com.jtattoo.plaf.luna.LunaLookAndFeel.setTheme("Default");
        }
    	try {
			UIManager.setLookAndFeel(lf);
		} catch (Exception e) {
			log.error("changeJTattooLookAndFeel", e);
			e.printStackTrace();
		}
    }
    
    public static void changeLookAndFeel(String lf){
        try {
//        	changeLookAndFeel(getLookAndFeel(lookAndFeel));
        	changeJTattooLookAndFeel(lf);
    		if(DataUtil.getTopWindow()!=null && DataUtil.getTopWindow() instanceof MainFrame){
    			MainFrame mainFrame = getComponent(MainFrame.class);
        		mainFrame.getRootPane().updateUI();
        		SwingUtilities.updateComponentTreeUI(mainFrame);
    			final Iterator<Entry<Class, Object>> iterator = views.entrySet().iterator();
    			while (iterator.hasNext()) {
    	            Entry<Class, Object> next = iterator.next();
    	            Object value = next.getValue();
    	            if (value instanceof java.awt.Component) {
//    	            	log.warn("value class==============================="+ value.getClass());
    	                java.awt.Component win = (java.awt.Component) value;
    	                SwingUtilities.updateComponentTreeUI(win);
    	                win.repaint();
    	            }
    			}
        		ConfigUtil.setProperty("DefaultLookAndFeelClassName", lf);
    		}
        } catch (Exception ex) {
            log.error("changeLookAndFeel", ex);
        }
    }
    
    public static void changeLookAndFeel(){
        try {
        	chageTheme(ConfigUtil.getProperty("themeFileName"));
        } catch (Exception ex) {
            log.error("changeLookAndFeel", ex);
        }
    }
    
    
    
//    public static void changeLookAndFeelOnLoginFrame(LookAndFeel lookAndFeel){
//    	try {
//    		UIManager.setLookAndFeel(lookAndFeel);
//    		SwingUtilities.updateComponentTreeUI(ViewManager.getComponent(LoginFrame.class));
//        } catch (Exception ex) {
//            log.info(ex);
//        }
//    }
//    
//    public static void changeLookAndFeelOnLoginFrame(String lookAndFeel){
//    	try {
//    		UIManager.setLookAndFeel(getLookAndFeel(lookAndFeel));
//    		SwingUtilities.updateComponentTreeUI(ViewManager.getComponent(LoginFrame.class));
//        } catch (Exception ex) {
//            log.info(ex);
//        }
//    }
    
    public static void changeTheme(String themeName){
    	try {
    		
    		DockingWindowsTheme theme = getDockingWindowsTheme(themeName);
    		changeTheme(theme);
    	} catch (Exception ex) {
            log.error("changeTheme", ex);
        }
    }
    
    public static void changeTheme(DockingWindowsTheme theme){
    	try {
    		DockingLayoutMeetingPanel panel = ViewManager.getComponent(DockingLayoutMeetingPanel.class);
    		panel.setTheme(theme);
    		ConfigUtil.setProperty("DefaultThemeClassName", theme.getClass().getName());
    	} catch (Exception ex) {
            log.error("changeTheme", ex);
        }
    }
    
    public static void chageTheme(String themeFileName) throws Exception{
    	if(themeFileName==null || "".equals(themeFileName.trim())){
    		themeFileName = "2.theme";
    	}
    	NimRODTheme nt = new NimRODTheme(ViewManager.class.getResource("/com/lorent/lvmc/resource/theme/" + themeFileName));
    	NimRODLookAndFeel nf = new NimRODLookAndFeel();
    	nf.setCurrentTheme(nt);
    	changeLookAndFeel(nf);
    	ConfigUtil.setProperty("themeFileName", themeFileName);
    }
    
    public static DockingWindowsTheme getDockingWindowsTheme(String className){
    	DockingWindowsTheme newInstance = null;
         try {
             Class<?> forName = Class.forName(className);
             newInstance = (DockingWindowsTheme) forName.newInstance();
         } catch (Exception ex) {
        	 log.error("getDockingWindowsTheme", ex);
        	 newInstance = new ShapedGradientDockingTheme();
         }
         return  newInstance==null?new ShapedGradientDockingTheme():newInstance;
    }
    
    public static void setComponentByAuthority(java.awt.Component c,boolean f){
    	c.setEnabled(f);
    }
    
    
//    public static void changeSkin(String skinFileName) throws Exception{
//    	com.lorent.commonconfig.ConfigUtil cu = new com.lorent.commonconfig.ConfigUtil(Constants.SKIN_PATH + "/" + skinFileName,false);  
//    	MainFrame mf = getComponent(MainFrame.class);
//    	javax.swing.JButton bt = mf.getWindowStyleButton();
////    	setButton(bt,cu.getProperty("MainFrame.windowStyleButton.icon"),cu.getProperty("MainFrame.windowStyleButton.rolloverIcon"),cu.getProperty("MainFrame.windowStyleButton.pressedIcon"));
//    	javax.swing.JButton bt1 = mf.getThemeButton();
//    	setButton(bt1,cu.getProperty("MainFrame.themeButton.icon"),cu.getProperty("MainFrame.themeButton.rolloverIcon"),null);
//    }
    
    public static void setButton(javax.swing.JButton bt,String icon,String rolloverIcon,String pressedIcon){
    	boolean flag = false;
    	if(icon!=null && icon.trim().length()>3){
    		bt.setIcon(new ImageIcon(ViewManager.class.getResource(icon)));
    		bt.setVerticalTextPosition(javax.swing.AbstractButton.CENTER);
    		bt.setHorizontalTextPosition(javax.swing.AbstractButton.RIGHT);
    		flag = true;
    	}
    	if(rolloverIcon!=null && rolloverIcon.trim().length()>3){
    		bt.setRolloverIcon(new ImageIcon(ViewManager.class.getResource(rolloverIcon)));
    		flag = true;
    	}
    	if(pressedIcon!=null && pressedIcon.trim().length()>3){
    		bt.setPressedIcon(new ImageIcon(ViewManager.class.getResource(pressedIcon)));
    		flag = true;
    	}
    	if(flag){
//    		bt.setText(null);
//    		bt.setPreferredSize(new java.awt.Dimension(bt.getWidth() + new ImageIcon(ViewManager.class.getResource(icon)).getIconWidth(),bt.getHeight()));
    		bt.revalidate();
    		bt.repaint();
    	}
    }
    
    /*public static BasicLookAndFeel getLookAndFeelByString(String str){
    	if(str==null || "".equals(str.trim()) || "WindowsClassicLookAndFeel".equals(str)){
    		return new WindowsClassicLookAndFeel();
    	}else if("MotifLookAndFeel".equals(str)){
    		return new MotifLookAndFeel();
    	}else if("MetalLookAndFeel".equals(str)){
    		return new MetalLookAndFeel();
    	}
    	return new WindowsClassicLookAndFeel();
    }*/
    
    /*public static DockingWindowsTheme getDockingWindowsTheme(String str){
    	if(str==null || "".equals(str.trim()) || "ShapedGradientDockingTheme".equals(str)){
    		return new ShapedGradientDockingTheme();
    	}else if("DefaultDockingTheme".equals(str)){
    		return new DefaultDockingTheme();
    	}else if("LookAndFeelDockingTheme".equals(str)){
    		return new LookAndFeelDockingTheme();
    	}else if("BlueHighlightDockingTheme".equals(str)){
    		return new BlueHighlightDockingTheme();
    	}else if("SlimFlatDockingTheme".equals(str)){
    		return new SlimFlatDockingTheme();
    	}else if("GradientDockingTheme".equals(str)){
    		return new GradientDockingTheme();
    	}else if("SoftBlueIceDockingTheme".equals(str)){
    		return new SoftBlueIceDockingTheme();
    	}else if("ClassicDockingTheme".equals(str)){
    		return new ClassicDockingTheme();
    	}
    	return new ShapedGradientDockingTheme();
    }*/
    
//    public static void setFileJProgressBar(String hashCode,final int percent) throws Exception{
//        MessageFrame mframe = (MessageFrame) getComponent(MessageFrame.class);
//        final FileJProgressBar fpb = mframe.getFileProgressBars().get(hashCode);
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//
//                if (fpb != null) {
////                    int i = fpb.getValue();
////                    try{
////                        i = Integer.parseInt(percent);
////                    }catch(Exception ex){
////                        log.error(ex);
////                    }
//                    fpb.setValue(percent);
//                }
//            }
//        });
//    }
}
