/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DockingLayoutPanel.java
 *
 * Created on 2011-12-13, 10:13:03
 */
package com.lorent.lvmc.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.basic.BasicLookAndFeel;
import javax.swing.plaf.metal.MetalLookAndFeel;

import net.infonode.docking.DockingWindow;
import net.infonode.docking.DockingWindowAdapter;
import net.infonode.docking.FloatingWindow;
import net.infonode.docking.OperationAbortedException;
import net.infonode.docking.RootWindow;
import net.infonode.docking.SplitWindow;
import net.infonode.docking.TabWindow;
import net.infonode.docking.View;
import net.infonode.docking.WindowBar;
import net.infonode.docking.properties.RootWindowProperties;
import net.infonode.docking.theme.BlueHighlightDockingTheme;
import net.infonode.docking.theme.ClassicDockingTheme;
import net.infonode.docking.theme.DefaultDockingTheme;
import net.infonode.docking.theme.DockingWindowsTheme;
import net.infonode.docking.theme.GradientDockingTheme;
import net.infonode.docking.theme.LookAndFeelDockingTheme;
import net.infonode.docking.theme.ShapedGradientDockingTheme;
import net.infonode.docking.theme.SlimFlatDockingTheme;
import net.infonode.docking.theme.SoftBlueIceDockingTheme;
import net.infonode.docking.util.DockingUtil;
import net.infonode.docking.util.PropertiesUtil;
import net.infonode.docking.util.ViewMap;
import net.infonode.gui.laf.InfoNodeLookAndFeel;
import net.infonode.util.Direction;

import org.apache.log4j.Logger;

import com.lorent.lvmc.controller.ControllerFacade;
import com.lorent.lvmc.controller.ViewManager;
import com.lorent.lvmc.util.ConfigUtil;
import com.lorent.lvmc.util.DataUtil;
import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

/**
 *
 * @author Administrator
 */
public class DockingLayoutMeetingPanel extends javax.swing.JPanel {

    private ArrayList<DockingWindowAdapter> adapterList = new ArrayList<DockingWindowAdapter>();
    
    public void setDockingWindowOpaque(java.awt.Container container,boolean flag){
    	System.out.println(container.getClass().getName());
    	if(container!=null){
    		if(container instanceof javax.swing.JComponent){
    			((javax.swing.JComponent)container).setOpaque(flag);
    			container.repaint();
    		}
    		int count = container.getComponentCount();
    		System.out.println(count);
        	for(int i=0;i<count;i++){

        		if(container.getComponent(i) instanceof JPanel){
    				setDockingWindowOpaque((JPanel)container.getComponent(i),flag);
    			}else if(container.getComponent(i) instanceof JSplitPane){
    				setDockingWindowOpaque((JSplitPane)container.getComponent(i),flag);
    			}else if(container.getComponent(i) instanceof JToolBar){
    				((JToolBar)container.getComponent(i)).setOpaque(flag);
    			}else if(container.getComponent(i) instanceof JTabbedPane){
    				((JTabbedPane)container.getComponent(i)).setOpaque(flag);
    			}else if(container.getComponent(i) instanceof JList){
    				JList jList1 = (JList)container.getComponent(i);
    				((JLabel)jList1.getCellRenderer()).setOpaque(flag);
    				jList1.setOpaque(flag);
    			}else if(container.getComponent(i) instanceof JScrollPane){
    				((JScrollPane)container.getComponent(i)).getViewport().setOpaque(false);
    			}else if(container.getComponent(i) instanceof javax.swing.JComponent){
        			((javax.swing.JComponent)container.getComponent(i)).setOpaque(flag);
        			
        		}
        		((javax.swing.JComponent)container.getComponent(i)).repaint();
        	}
    	}
    	
    }
    
    /** Creates new form DockingLayoutPanel */
    public DockingLayoutMeetingPanel() {
        initComponents();
        rootWindow = DockingUtil.createRootWindow(viewMap, true);

     // Set gradient theme. The theme properties object is the super object of our properties object, which
        // means our property value settings will override the theme values
        properties.addSuperObject(currentTheme.getRootWindowProperties());

        // Our properties object is the super object of the root window properties object, so all property values of the
        // theme and in our property object will be used by the root window
        rootWindow.getRootWindowProperties().addSuperObject(properties);
        rootWindow.getWindowBar(Direction.DOWN).setEnabled(true);
        rootWindow.getWindowBar(Direction.LEFT).setEnabled(true);
        rootWindow.getWindowBar(Direction.RIGHT).setEnabled(true);
        this.add(rootWindow, BorderLayout.CENTER);
//        setDockingWindowOpaque(rootWindow,false);
//        reloadLayoutMenus();
//        titleBarMenuItem.setSelected(true);
        this.setTitleBarStyle(true);
        rootWindow.setVisible(true);
        initDockingWindowAdapter();
        hideCloseButton();
//        changeLookAndFeel(new WindowsClassicLookAndFeel());
        
//        properties.getDockingWindowProperties().setUndockEnabled(false);
//        properties.getFloatingWindowProperties().setUseFrame(true);
    }
    
    
    
    public void addDockingWindowAdapter(DockingWindowAdapter adapter){
        adapterList.add(adapter);
    }
    
    private static Logger log = Logger.getLogger(DockingLayoutMeetingPanel.class);

    public class MyView extends View {

        public MyView(String string, Icon icon, Component cmpnt,String imageFileName) {
            super(string, icon, cmpnt);
            this.imageFileName = imageFileName;
        }
        private String viewId = "";
        private String imageFileName;
        
        
        public String getImageFileName(){
        	return imageFileName;
        }

        public String getViewId() {
            return viewId;
        }

        public void setViewId(String viewId) {
            this.viewId = viewId;
        }
        
        public void setFocused(boolean focused){
        	super.setFocused(focused);
        }
    }
    private RootWindow rootWindow;
    private RootWindowProperties properties = new RootWindowProperties();
    private DockingWindowsTheme currentTheme = new ShapedGradientDockingTheme();
    private RootWindowProperties titleBarStyleProperties = PropertiesUtil.createTitleBarStyleRootWindowProperties();

    public RootWindow getRootWindow() {
        return rootWindow;
    }

//    public ViewMap getViewMap() {
//        return viewMap;
//    }
    private ViewMap viewMap = new ViewMap();

    public ViewMap getViewMap() {
        return viewMap;
    }

    public void reloadLayoutMenus() {
        final DockingLayoutMeetingPanel instance = this;
        Component[] menuComponents = layoutMenu.getMenuComponents();
        String selectName = "";
        /*
        for (Component component : menuComponents) {
        if (component instanceof JRadioButtonMenuItem ) {
        JRadioButtonMenuItem item = (JRadioButtonMenuItem) component;
        if (item.isSelected()) {
        selectName = item.getText();
        break;
        }
        }
        }
         */
        selectName = ConfigUtil.getProperty("LayoutName");
        ArrayList<String> namelist = (ArrayList<String>) ControllerFacade.execute("layoutController", "getLayoutNames");
        ButtonGroup buttonGroup = new ButtonGroup();
        layoutMenu.removeAll();
        layoutMenu.add(layoutMenuItemSaveLayout);
        layoutMenu.add(new JSeparator());
        for (final String name : namelist) {
            JRadioButtonMenuItem jRadioButtonMenuItem = new JRadioButtonMenuItem(name);
            buttonGroup.add(jRadioButtonMenuItem);
            if (selectName != null && selectName.equals(name) && !name.equals("")) {
                jRadioButtonMenuItem.setSelected(true);
            }
            jRadioButtonMenuItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    log.debug("选择了布局:" + name);
                    ControllerFacade.execute("layoutController", "changeLayout", instance, name);
                }
            });
            layoutMenu.add(jRadioButtonMenuItem);
        }


    }

    public synchronized int findIndexOfViewMap(String viewID){
        for (int i = 0; i < viewMap.getViewCount(); i++) {
            MyView view = (MyView) viewMap.getView(i);
            if (view != null && view.getViewId().equals(viewID)) {
                return i;
            }
        }
        return -1;
    }
    
    public synchronized View findInViewMap(String viewID) {
        for (int i = 0; i < viewMap.getViewCount(); i++) {
            MyView view = (MyView) viewMap.getView(i);
            if (view != null && view.getViewId().equals(viewID)) {
                return view;
            }
        }
        return null;
    }

    public void hideCloseButton() {
        properties.getDockingWindowProperties().setCloseEnabled(false);
    }

    public void enableCloseButton() {
        properties.getDockingWindowProperties().setCloseEnabled(true);
    }

    public void setDockEnabled(boolean flag){
        properties.getDockingWindowProperties().setDockEnabled(flag);
    }
    
    public void setUndockEnabled(boolean flag){
        properties.getDockingWindowProperties().setUndockEnabled(flag);
    }
    
    public void addFloatingPanel(JPanel panel, String title, String panelID, Icon icon){
        MyView view = new MyView(title, icon, panel,null);
        view.setViewId(panelID);
        view.setName(panelID);
        FloatingWindow createFloatingWindow = rootWindow.createFloatingWindow(new Point(50, 50), new Dimension(panel.getWidth(), panel.getHeight()), view);
        createFloatingWindow.getTopLevelAncestor().setVisible(true);
    }
    
    public synchronized void addPanel(JPanel panel, String title, String panelID, Icon icon,String img) {
//        View view = new View(title, null, panel);
        if (findInViewMap(panelID) != null) {
            return;
        }
        MyView view = new MyView(title, icon, panel,img);
        view.setViewId(panelID);
        view.setName(panelID);
        viewMap.addView(viewMap.getViewCount(), view);
//        view.setEnabled(true);
        DockingUtil.addWindow(view, rootWindow);
        log.info("addPanel: " + panelID);
    }
    
    public synchronized void addPanelToTab(JPanel panel, String title, String panelID, Icon icon,String img,TabWindow tabWindow){
    	if (findInViewMap(panelID) != null) {
            return;
        }
        MyView view = new MyView(title, icon, panel,img);
        view.setViewId(panelID);
        view.setName(panelID);
        viewMap.addView(viewMap.getViewCount(), view);
        
        tabWindow.addTab(view);
        view.restoreFocus();
        log.info("addPanelToTab: " + panelID);
    }
    
    public synchronized void removePanel(String panelID){
        int findIndexOfViewMap = findIndexOfViewMap(panelID);
        if (findIndexOfViewMap != -1) {
            View view = viewMap.getView(findIndexOfViewMap);
            if (view != null) {
            	view.close();
            	rootWindow.removeView(view);
                viewMap.removeView(findIndexOfViewMap);
			}
        }
    }

    public synchronized void updatePanel(JPanel panel,String panelID){
    	int findIndexOfViewMap = findIndexOfViewMap(panelID);
        if (findIndexOfViewMap != -1) {
            View view = viewMap.getView(findIndexOfViewMap);
            view.setComponent(panel);
        }
    }
    
    public void setPanelAtWindowBar(String panelID, int direction) {
        View findInViewMap = findInViewMap(panelID);
        if (findInViewMap != null) {
            WindowBar windowBar = null;
            if (direction == com.lorent.lvmc.util.Constants.DOCKING_LAYOUT_DIRECTION_UP) {
                windowBar = rootWindow.getWindowBar(Direction.UP);
            } else if (direction == com.lorent.lvmc.util.Constants.DOCKING_LAYOUT_DIRECTION_LEFT) {
                windowBar = rootWindow.getWindowBar(Direction.LEFT);
            } else if (direction == com.lorent.lvmc.util.Constants.DOCKING_LAYOUT_DIRECTION_RIGHT) {
                windowBar = rootWindow.getWindowBar(Direction.RIGHT);
            } else {
                windowBar = rootWindow.getWindowBar(Direction.DOWN);
            }
            windowBar.addTab(findInViewMap);
        }
    }

    public static  void setWindowAtWindowBar(RootWindow rootWindow,DockingWindow window ,int direction){
        
        WindowBar windowBar = null;
            if (direction == com.lorent.lvmc.util.Constants.DOCKING_LAYOUT_DIRECTION_UP) {
                windowBar = rootWindow.getWindowBar(Direction.UP);
            } else if (direction == com.lorent.lvmc.util.Constants.DOCKING_LAYOUT_DIRECTION_LEFT) {
                windowBar = rootWindow.getWindowBar(Direction.LEFT);
            } else if (direction == com.lorent.lvmc.util.Constants.DOCKING_LAYOUT_DIRECTION_RIGHT) {
                windowBar = rootWindow.getWindowBar(Direction.RIGHT);
            } else {
                windowBar = rootWindow.getWindowBar(Direction.DOWN);
            }
            windowBar.addTab(window);
    }
    
    /**
     * Sets the docking windows theme.
     *
     * @param theme the docking windows theme
     */
    public void setTheme(DockingWindowsTheme theme) {
        properties.replaceSuperObject(currentTheme.getRootWindowProperties(),
                theme.getRootWindowProperties());
        currentTheme = theme;
    }

    public void setTitleBarStyle(boolean flag) {
        if (flag) {
            properties.addSuperObject(titleBarStyleProperties);
        } else {
            properties.removeSuperObject(titleBarStyleProperties);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layoutMenuItemSaveLayout = new javax.swing.JMenuItem();
        themeGroup = new javax.swing.ButtonGroup();
        layoutGroup = new javax.swing.ButtonGroup();
        lookAndFeelGroup = new javax.swing.ButtonGroup();
        windowSystemMenu = new javax.swing.JMenu();
        lookAndFeelMenu = new javax.swing.JMenu();
        lookAndFeelMenuItemWindowsClassic = new javax.swing.JRadioButtonMenuItem();
        lookAndFeelMenuItemWindows = new javax.swing.JRadioButtonMenuItem();
        lookAndFeelMenuItemCDEMotif = new javax.swing.JRadioButtonMenuItem();
        lookAndFeelMenuItemMetal = new javax.swing.JRadioButtonMenuItem();
        themeMenu = new javax.swing.JMenu();
        themeMenuItemDefaultDockingTheme = new javax.swing.JRadioButtonMenuItem();
        themeMenuItemLookAndFeelDockingTheme = new javax.swing.JRadioButtonMenuItem();
        themeMenuItemBlueHighlightDockingTheme = new javax.swing.JRadioButtonMenuItem();
        themeMenuItemSlimFlatDockingTheme = new javax.swing.JRadioButtonMenuItem();
        themeMenuItemGradientDockingTheme = new javax.swing.JRadioButtonMenuItem();
        themeMenuItemShapedGradientDockingTheme = new javax.swing.JRadioButtonMenuItem();
        themeMenuItemSoftBlueIceDockingTheme = new javax.swing.JRadioButtonMenuItem();
        themeMenuItemClassicDockingTheme = new javax.swing.JRadioButtonMenuItem();
        titleBarMenuItem = new javax.swing.JCheckBoxMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        layoutMenu = new javax.swing.JMenu();
        viewsMenu = new javax.swing.JMenu();
        screenShareMenu = new javax.swing.JMenu();
        startScreenShareMI = new javax.swing.JMenuItem();
        stopScreenShareMI = new javax.swing.JMenuItem();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/lorent/lvmc/resource/i18n/view"); // NOI18N
        layoutMenuItemSaveLayout.setText(bundle.getString("com.lorent.lvmc.ui.DefaultLayoutMeetingPanel.layoutMenuItemSaveLayout")); // NOI18N
        layoutMenuItemSaveLayout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                layoutMenuItemSaveLayoutActionPerformed(evt);
            }
        });

        windowSystemMenu.setText(bundle.getString("com.lorent.lvmc.ui.DefaultLayoutMeetingPanel.windowSystemMenu")); // NOI18N

        lookAndFeelMenu.setText(bundle.getString("com.lorent.lvmc.ui.DefaultLayoutMeetingPanel.windowStyleMenu.lookAndFeelMenu")); // NOI18N

        lookAndFeelGroup.add(lookAndFeelMenuItemWindowsClassic);
        lookAndFeelMenuItemWindowsClassic.setSelected(true);
        lookAndFeelMenuItemWindowsClassic.setText(bundle.getString("DockingLayoutMeetingPanel.lookAndFeelMenuItemWindowsClassic.text")); // NOI18N
        lookAndFeelMenuItemWindowsClassic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lookAndFeelMenuItemWindowsClassicActionPerformed(evt);
            }
        });
        lookAndFeelMenu.add(lookAndFeelMenuItemWindowsClassic);

        lookAndFeelGroup.add(lookAndFeelMenuItemWindows);
        lookAndFeelMenuItemWindows.setText(bundle.getString("DockingLayoutMeetingPanel.lookAndFeelMenuItemWindows.text")); // NOI18N
        lookAndFeelMenuItemWindows.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lookAndFeelMenuItemWindowsActionPerformed(evt);
            }
        });
        lookAndFeelMenu.add(lookAndFeelMenuItemWindows);

        lookAndFeelGroup.add(lookAndFeelMenuItemCDEMotif);
        lookAndFeelMenuItemCDEMotif.setText(bundle.getString("DockingLayoutMeetingPanel.lookAndFeelMenuItemCDEMotif.text")); // NOI18N
        lookAndFeelMenuItemCDEMotif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lookAndFeelMenuItemCDEMotifActionPerformed(evt);
            }
        });
        lookAndFeelMenu.add(lookAndFeelMenuItemCDEMotif);

        lookAndFeelGroup.add(lookAndFeelMenuItemMetal);
        lookAndFeelMenuItemMetal.setText(bundle.getString("DockingLayoutMeetingPanel.lookAndFeelMenuItemMetal.text")); // NOI18N
        lookAndFeelMenuItemMetal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lookAndFeelMenuItemMetalActionPerformed(evt);
            }
        });
        lookAndFeelMenu.add(lookAndFeelMenuItemMetal);

        windowSystemMenu.add(lookAndFeelMenu);

        themeMenu.setText(bundle.getString("com.lorent.lvmc.ui.DefaultLayoutMeetingPanel.windowStyleMenu.themeMenu")); // NOI18N

        themeGroup.add(themeMenuItemDefaultDockingTheme);
        themeMenuItemDefaultDockingTheme.setText(bundle.getString("com.lorent.lvmc.ui.DefaultLayoutMeetingPanel.windowStyleMenu.themeMenu.themeMenuItemDefaultDockingTheme")); // NOI18N
        themeMenuItemDefaultDockingTheme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themeMenuItemDefaultDockingThemeActionPerformed(evt);
            }
        });
        themeMenu.add(themeMenuItemDefaultDockingTheme);

        themeGroup.add(themeMenuItemLookAndFeelDockingTheme);
        themeMenuItemLookAndFeelDockingTheme.setText(bundle.getString("com.lorent.lvmc.ui.DefaultLayoutMeetingPanel.windowStyleMenu.themeMenu.themeMenuItemLookAndFeelDockingTheme")); // NOI18N
        themeMenuItemLookAndFeelDockingTheme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themeMenuItemLookAndFeelDockingThemeActionPerformed(evt);
            }
        });
        themeMenu.add(themeMenuItemLookAndFeelDockingTheme);

        themeGroup.add(themeMenuItemBlueHighlightDockingTheme);
        themeMenuItemBlueHighlightDockingTheme.setText(bundle.getString("com.lorent.lvmc.ui.DefaultLayoutMeetingPanel.windowStyleMenu.themeMenu.themeMenuItemBlueHighlightDockingTheme")); // NOI18N
        themeMenuItemBlueHighlightDockingTheme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themeMenuItemBlueHighlightDockingThemeActionPerformed(evt);
            }
        });
        themeMenu.add(themeMenuItemBlueHighlightDockingTheme);

        themeGroup.add(themeMenuItemSlimFlatDockingTheme);
        themeMenuItemSlimFlatDockingTheme.setText(bundle.getString("com.lorent.lvmc.ui.DefaultLayoutMeetingPanel.windowStyleMenu.themeMenu.themeMenuItemSlimFlatDockingTheme")); // NOI18N
        themeMenuItemSlimFlatDockingTheme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themeMenuItemSlimFlatDockingThemeActionPerformed(evt);
            }
        });
        themeMenu.add(themeMenuItemSlimFlatDockingTheme);

        themeGroup.add(themeMenuItemGradientDockingTheme);
        themeMenuItemGradientDockingTheme.setText(bundle.getString("com.lorent.lvmc.ui.DefaultLayoutMeetingPanel.windowStyleMenu.themeMenu.themeMenuItemGradientDockingTheme")); // NOI18N
        themeMenuItemGradientDockingTheme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themeMenuItemGradientDockingThemeActionPerformed(evt);
            }
        });
        themeMenu.add(themeMenuItemGradientDockingTheme);

        themeGroup.add(themeMenuItemShapedGradientDockingTheme);
        themeMenuItemShapedGradientDockingTheme.setSelected(true);
        themeMenuItemShapedGradientDockingTheme.setText(bundle.getString("com.lorent.lvmc.ui.DefaultLayoutMeetingPanel.windowStyleMenu.themeMenu.themeMenuItemShapedGradientDockingTheme")); // NOI18N
        themeMenuItemShapedGradientDockingTheme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themeMenuItemShapedGradientDockingThemeActionPerformed(evt);
            }
        });
        themeMenu.add(themeMenuItemShapedGradientDockingTheme);

        themeGroup.add(themeMenuItemSoftBlueIceDockingTheme);
        themeMenuItemSoftBlueIceDockingTheme.setText(bundle.getString("com.lorent.lvmc.ui.DefaultLayoutMeetingPanel.windowStyleMenu.themeMenu.themeMenuItemSoftBlueIceDockingTheme")); // NOI18N
        themeMenuItemSoftBlueIceDockingTheme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themeMenuItemSoftBlueIceDockingThemeActionPerformed(evt);
            }
        });
        themeMenu.add(themeMenuItemSoftBlueIceDockingTheme);

        themeGroup.add(themeMenuItemClassicDockingTheme);
        themeMenuItemClassicDockingTheme.setText(bundle.getString("com.lorent.lvmc.ui.DefaultLayoutMeetingPanel.windowStyleMenu.themeMenu.themeMenuItemClassicDockingTheme")); // NOI18N
        themeMenuItemClassicDockingTheme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themeMenuItemClassicDockingThemeActionPerformed(evt);
            }
        });
        themeMenu.add(themeMenuItemClassicDockingTheme);

        windowSystemMenu.add(themeMenu);

        titleBarMenuItem.setText(bundle.getString("com.lorent.lvmc.ui.DefaultLayoutMeetingPanel.windowStyleMenu.titleBarMenuItem")); // NOI18N
        titleBarMenuItem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                titleBarMenuItemItemStateChanged(evt);
            }
        });
        windowSystemMenu.add(titleBarMenuItem);

        exitMenuItem.setText(bundle.getString("DockingLayoutMeetingPanel.exitMenuItem.text")); // NOI18N
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        windowSystemMenu.add(exitMenuItem);

        layoutMenu.setText(bundle.getString("com.lorent.lvmc.ui.DefaultLayoutMeetingPanel.windowStyleMenu.layoutMenu")); // NOI18N

        viewsMenu.setText(bundle.getString("com.lorent.lvmc.ui.DefaultLayoutMeetingPanel.viewsMenu")); // NOI18N
        viewsMenu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                viewsMenuItemStateChanged(evt);
            }
        });

        screenShareMenu.setText(bundle.getString("DockingLayoutMeetingPanel.screenShareMenu.text")); // NOI18N

        startScreenShareMI.setText(bundle.getString("DockingLayoutMeetingPanel.startScreenShareMI.text")); // NOI18N
        startScreenShareMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startScreenShareMIActionPerformed(evt);
            }
        });
        screenShareMenu.add(startScreenShareMI);

        stopScreenShareMI.setText(bundle.getString("DockingLayoutMeetingPanel.stopScreenShareMI.text")); // NOI18N
        stopScreenShareMI.setEnabled(false);
        stopScreenShareMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopScreenShareMIActionPerformed(evt);
            }
        });
        screenShareMenu.add(stopScreenShareMI);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void themeMenuItemDefaultDockingThemeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themeMenuItemDefaultDockingThemeActionPerformed
        setTheme(new DefaultDockingTheme());
    }//GEN-LAST:event_themeMenuItemDefaultDockingThemeActionPerformed

    private void themeMenuItemLookAndFeelDockingThemeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themeMenuItemLookAndFeelDockingThemeActionPerformed
        setTheme(new LookAndFeelDockingTheme());
    }//GEN-LAST:event_themeMenuItemLookAndFeelDockingThemeActionPerformed

    private void themeMenuItemBlueHighlightDockingThemeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themeMenuItemBlueHighlightDockingThemeActionPerformed
        setTheme(new BlueHighlightDockingTheme());
    }//GEN-LAST:event_themeMenuItemBlueHighlightDockingThemeActionPerformed

    private void themeMenuItemSlimFlatDockingThemeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themeMenuItemSlimFlatDockingThemeActionPerformed
        setTheme(new SlimFlatDockingTheme());
    }//GEN-LAST:event_themeMenuItemSlimFlatDockingThemeActionPerformed

    private void themeMenuItemGradientDockingThemeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themeMenuItemGradientDockingThemeActionPerformed
        setTheme(new GradientDockingTheme());
    }//GEN-LAST:event_themeMenuItemGradientDockingThemeActionPerformed

    private void themeMenuItemShapedGradientDockingThemeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themeMenuItemShapedGradientDockingThemeActionPerformed
        setTheme(new ShapedGradientDockingTheme());
    }//GEN-LAST:event_themeMenuItemShapedGradientDockingThemeActionPerformed

    private void themeMenuItemSoftBlueIceDockingThemeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themeMenuItemSoftBlueIceDockingThemeActionPerformed
        setTheme(new SoftBlueIceDockingTheme());
    }//GEN-LAST:event_themeMenuItemSoftBlueIceDockingThemeActionPerformed

    private void themeMenuItemClassicDockingThemeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themeMenuItemClassicDockingThemeActionPerformed
        setTheme(new ClassicDockingTheme());
    }//GEN-LAST:event_themeMenuItemClassicDockingThemeActionPerformed

    private void titleBarMenuItemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_titleBarMenuItemItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            setTitleBarStyle(true);
        } else {
            setTitleBarStyle(false);
        }

    }//GEN-LAST:event_titleBarMenuItemItemStateChanged

    private void layoutMenuItemSaveLayoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_layoutMenuItemSaveLayoutActionPerformed
        ControllerFacade.execute("layoutController", "showSaveLayoutDialog", rootWindow);
        reloadLayoutMenus();
    }//GEN-LAST:event_layoutMenuItemSaveLayoutActionPerformed

    public void restoreView(String panelID){
        View findInViewMap = findInViewMap(panelID);
        if (findInViewMap != null) {
            DockingUtil.addWindow(findInViewMap, rootWindow);
            findInViewMap.restoreFocus();
            log.info("restoreViewing ...");
        }
        log.info("restoreView "+panelID);
    }
    
    private synchronized void  viewsMenuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_viewsMenuItemStateChanged
        viewsMenu.removeAll();
        for (int i = 0; i < viewMap.getViewCount(); i++) {
            final View view = viewMap.getView(i);
            JMenuItem jMenuItem = new JMenuItem(view.getTitle());
            jMenuItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    DockingUtil.addWindow(view, rootWindow);
                    view.restoreFocus();
                }
            });
            viewsMenu.add(jMenuItem);
        }
    }//GEN-LAST:event_viewsMenuItemStateChanged

    private void startScreenShareMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startScreenShareMIActionPerformed
        ControllerFacade.execute("shareDesktopController", "startScreenShare");
//        this.startScreenShareMI.setEnabled(false);
//        this.stopScreenShareMI.setEnabled(true);
    }//GEN-LAST:event_startScreenShareMIActionPerformed

    private void stopScreenShareMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopScreenShareMIActionPerformed
        ControllerFacade.execute("shareDesktopController", "stopScreenShare");
//        this.startScreenShareMI.setEnabled(true);
//        this.stopScreenShareMI.setEnabled(false);
    }//GEN-LAST:event_stopScreenShareMIActionPerformed

    private void lookAndFeelMenuItemWindowsClassicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lookAndFeelMenuItemWindowsClassicActionPerformed
        changeLookAndFeel(new WindowsClassicLookAndFeel());
    }//GEN-LAST:event_lookAndFeelMenuItemWindowsClassicActionPerformed

    private void lookAndFeelMenuItemWindowsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lookAndFeelMenuItemWindowsActionPerformed
        changeLookAndFeel(new WindowsLookAndFeel());
    }//GEN-LAST:event_lookAndFeelMenuItemWindowsActionPerformed

    private void lookAndFeelMenuItemCDEMotifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lookAndFeelMenuItemCDEMotifActionPerformed
        changeLookAndFeel(new MotifLookAndFeel());
    }//GEN-LAST:event_lookAndFeelMenuItemCDEMotifActionPerformed

    private void lookAndFeelMenuItemMetalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lookAndFeelMenuItemMetalActionPerformed
        changeLookAndFeel(new MetalLookAndFeel());
    }//GEN-LAST:event_lookAndFeelMenuItemMetalActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        ControllerFacade.execute("mainController", "exitApplication");
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void changeLookAndFeel(BasicLookAndFeel lookAndFeel){
        try {
        	ViewManager.changeLookAndFeel(lookAndFeel);
        } catch (Exception ex) {
            log.error("changeLookAndFeel", ex);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.ButtonGroup layoutGroup;
    private javax.swing.JMenu layoutMenu;
    private javax.swing.JMenuItem layoutMenuItemSaveLayout;
    private javax.swing.ButtonGroup lookAndFeelGroup;
    private javax.swing.JMenu lookAndFeelMenu;
    private javax.swing.JRadioButtonMenuItem lookAndFeelMenuItemCDEMotif;
    private javax.swing.JRadioButtonMenuItem lookAndFeelMenuItemMetal;
    private javax.swing.JRadioButtonMenuItem lookAndFeelMenuItemWindows;
    private javax.swing.JRadioButtonMenuItem lookAndFeelMenuItemWindowsClassic;
    private javax.swing.JMenu screenShareMenu;
    private javax.swing.JMenuItem startScreenShareMI;
    private javax.swing.JMenuItem stopScreenShareMI;
    private javax.swing.ButtonGroup themeGroup;
    private javax.swing.JMenu themeMenu;
    private javax.swing.JRadioButtonMenuItem themeMenuItemBlueHighlightDockingTheme;
    private javax.swing.JRadioButtonMenuItem themeMenuItemClassicDockingTheme;
    private javax.swing.JRadioButtonMenuItem themeMenuItemDefaultDockingTheme;
    private javax.swing.JRadioButtonMenuItem themeMenuItemGradientDockingTheme;
    private javax.swing.JRadioButtonMenuItem themeMenuItemLookAndFeelDockingTheme;
    private javax.swing.JRadioButtonMenuItem themeMenuItemShapedGradientDockingTheme;
    private javax.swing.JRadioButtonMenuItem themeMenuItemSlimFlatDockingTheme;
    private javax.swing.JRadioButtonMenuItem themeMenuItemSoftBlueIceDockingTheme;
    private javax.swing.JCheckBoxMenuItem titleBarMenuItem;
    private javax.swing.JMenu viewsMenu;
    private javax.swing.JMenu windowSystemMenu;
    // End of variables declaration//GEN-END:variables

    public JMenu getViewsMenu() {
        return viewsMenu;
    }

    public JMenu getLayoutMenu() {
        return layoutMenu;
    }

    public JMenu getWindowStyleMenu() {
        return windowSystemMenu;
    }

    public JMenu getScreenShareMenu() {
        return screenShareMenu;
    }
    
    public JMenuItem getStartScreenShareMI(){
		return startScreenShareMI;
	}
    public JMenuItem getStopScreenShareMI(){
    	return stopScreenShareMI;
    }
    
    public void setMenuBar(JMenuBar menuBar){
        JMenu windowStyleMenu1 = getWindowStyleMenu();
        menuBar.add(windowStyleMenu1);
        menuBar.add(getLayoutMenu());
        menuBar.add(getViewsMenu());
        menuBar.add(getScreenShareMenu());
        if(DataUtil.getShowExitMenuItem().booleanValue()){
//            menuBar.add(exitMenuItem);
//            windowStyleMenu1.add(exitMenuItem);
        }
    }
    
    private void initDockingWindowAdapter(){
        rootWindow.addListener(new DockingWindowAdapter(){

            @Override
            public void viewFocusChanged(View view, View view1) {
//            	if(view!=null){
//            		System.out.println("view ========================" + view.getTitle());
//            	}
            	for (int i = 0; i < viewMap.getViewCount(); i++){
            		MyView mv = (MyView)viewMap.getView(i);
            		mv.setFocused(false);
            	}
            	if(view1 != null){
//            		System.out.println("view1 ========================" + view1.getTitle());
            		((MyView)view1).setFocused(true);
    				view1.restoreFocus();
            	}
            	
                super.viewFocusChanged(view, view1);
                Iterator<DockingWindowAdapter> iterator = adapterList.iterator();
                while (iterator.hasNext()) {
                    DockingWindowAdapter dockingWindowAdapter = iterator.next();
                    
                    dockingWindowAdapter.viewFocusChanged(view, view1);
                }
            }

            @Override
            public void windowAdded(DockingWindow dw, final DockingWindow dw1) {
//                super.windowAdded(dw, dw1);
                Iterator<DockingWindowAdapter> iterator = adapterList.iterator();
                while (iterator.hasNext()) {
                    DockingWindowAdapter dockingWindowAdapter = iterator.next();
                    dockingWindowAdapter.windowAdded(dw, dw1);
                }
                log.info("windowAdded dw:"+dw+"  dw1:"+dw1);

                Container parent2 = dw1.getParent();
				if (parent2 != null) {
					Container parent3 = parent2.getParent().getParent().getParent();
					if (parent3 instanceof JDialog) {
						JDialog jDialog = (JDialog)parent3;
						jDialog.addWindowListener(new WindowAdapter() {

							@Override
							public void windowClosing(WindowEvent e) {
								try {
									dw1.closeWithAbort();
								} catch (OperationAbortedException e1) {
									e1.printStackTrace();
									log.error("initDockingWindowAdapter.windowAdded", e1);
								}
							}
							
						});
					}
					else if(parent3 instanceof JFrame){
						JFrame jFrame = (JFrame) parent3;
						jFrame.addWindowListener(new WindowAdapter() {

							@Override
							public void windowClosing(WindowEvent e) {
								try {
									dw1.closeWithAbort();
								} catch (OperationAbortedException e1) {
									e1.printStackTrace();
									log.error("initDockingWindowAdapter.windowAdded", e1);
								}
							}
							
						});
					}
				}
                
            }

            @Override
            public void windowClosed(DockingWindow dw) {
//                super.windowClosed(dw);
                Iterator<DockingWindowAdapter> iterator = adapterList.iterator();
                while (iterator.hasNext()) {
                    DockingWindowAdapter dockingWindowAdapter = iterator.next();
                    dockingWindowAdapter.windowClosed(dw);
                }
            }

            @Override
            public void windowClosing(DockingWindow dw) throws OperationAbortedException {
//                super.windowClosing(dw);
                Iterator<DockingWindowAdapter> iterator = adapterList.iterator();
                while (iterator.hasNext()) {
                    DockingWindowAdapter dockingWindowAdapter = iterator.next();
                    dockingWindowAdapter.windowClosing(dw);
                }
            }

            @Override
            public void windowDocked(DockingWindow dw) {
//                super.windowDocked(dw);
                Iterator<DockingWindowAdapter> iterator = adapterList.iterator();
                while (iterator.hasNext()) {
                    DockingWindowAdapter dockingWindowAdapter = iterator.next();
                    dockingWindowAdapter.windowDocked(dw);
                }
            }

            @Override
            public void windowDocking(DockingWindow dw) throws OperationAbortedException {
//                super.windowDocking(dw);
                Iterator<DockingWindowAdapter> iterator = adapterList.iterator();
                while (iterator.hasNext()) {
                    DockingWindowAdapter dockingWindowAdapter = iterator.next();
                    dockingWindowAdapter.windowDocking(dw);
                }
            }

            @Override
            public void windowHidden(DockingWindow dw) {
//                super.windowHidden(dw);
                Iterator<DockingWindowAdapter> iterator = adapterList.iterator();
                while (iterator.hasNext()) {
                    DockingWindowAdapter dockingWindowAdapter = iterator.next();
                    dockingWindowAdapter.windowHidden(dw);
                }
            }

            @Override
            public void windowMaximizing(DockingWindow dw) throws OperationAbortedException {
//                super.windowMaximizing(dw);
                Iterator<DockingWindowAdapter> iterator = adapterList.iterator();
                while (iterator.hasNext()) {
                    DockingWindowAdapter dockingWindowAdapter = iterator.next();
                    dockingWindowAdapter.windowMaximizing(dw);
                }
            }

            @Override
            public void windowMinimized(DockingWindow dw) {
//                super.windowMinimized(dw);
                Iterator<DockingWindowAdapter> iterator = adapterList.iterator();
                while (iterator.hasNext()) {
                    DockingWindowAdapter dockingWindowAdapter = iterator.next();
                    dockingWindowAdapter.windowMinimized(dw);
                }
            }

            @Override
            public void windowMinimizing(DockingWindow dw) throws OperationAbortedException {
//                super.windowMinimizing(dw);
                Iterator<DockingWindowAdapter> iterator = adapterList.iterator();
                while (iterator.hasNext()) {
                    DockingWindowAdapter dockingWindowAdapter = iterator.next();
                    dockingWindowAdapter.windowMinimizing(dw);
                }
            }

            @Override
            public void windowRemoved(DockingWindow dw, DockingWindow dw1) {
//                super.windowRemoved(dw, dw1);
                Iterator<DockingWindowAdapter> iterator = adapterList.iterator();
                while (iterator.hasNext()) {
                    DockingWindowAdapter dockingWindowAdapter = iterator.next();
                    dockingWindowAdapter.windowRemoved(dw, dw1);
                }
            }

            @Override
            public void windowRestored(DockingWindow dw) {
//                super.windowRestored(dw);
                Iterator<DockingWindowAdapter> iterator = adapterList.iterator();
                while (iterator.hasNext()) {
                    DockingWindowAdapter dockingWindowAdapter = iterator.next();
                    dockingWindowAdapter.windowRestored(dw);
                }
            }

            @Override
            public void windowRestoring(DockingWindow dw) throws OperationAbortedException {
//                super.windowRestoring(dw);
                Iterator<DockingWindowAdapter> iterator = adapterList.iterator();
                while (iterator.hasNext()) {
                    DockingWindowAdapter dockingWindowAdapter = iterator.next();
                    dockingWindowAdapter.windowRestoring(dw);
                }
            }

            @Override
            public void windowUndocked(DockingWindow dw) {
//                super.windowUndocked(dw);
                Iterator<DockingWindowAdapter> iterator = adapterList.iterator();
                while (iterator.hasNext()) {
                    DockingWindowAdapter dockingWindowAdapter = iterator.next();
                    dockingWindowAdapter.windowUndocked(dw);
                }
            }

            @Override
            public void windowUndocking(DockingWindow dw) throws OperationAbortedException {
//                super.windowUndocking(dw);
                Iterator<DockingWindowAdapter> iterator = adapterList.iterator();
                while (iterator.hasNext()) {
                    DockingWindowAdapter dockingWindowAdapter = iterator.next();
                    dockingWindowAdapter.windowUndocking(dw);
                }
            }
            
            @Override
            public void windowMaximized(DockingWindow dw) {
//                super.windowMaximized(dw);
                Iterator<DockingWindowAdapter> iterator = adapterList.iterator();
                while (iterator.hasNext()) {
                    DockingWindowAdapter dockingWindowAdapter = iterator.next();
                    dockingWindowAdapter.windowMaximized(dw);
                }
            }

            @Override
            public void windowShown(DockingWindow dw) {
//                super.windowShown(dw);
                Iterator<DockingWindowAdapter> iterator = adapterList.iterator();
                while (iterator.hasNext()) {
                    DockingWindowAdapter dockingWindowAdapter = iterator.next();
                    dockingWindowAdapter.windowShown(dw);
                }
            }
            
        });
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        final DockingWindowsTheme[] themes = {new DefaultDockingTheme(),
            new LookAndFeelDockingTheme(),
            new BlueHighlightDockingTheme(),
            new SlimFlatDockingTheme(),
            new GradientDockingTheme(),
            new ShapedGradientDockingTheme(),
            new SoftBlueIceDockingTheme(),
            new ClassicDockingTheme()};

        try {
            UIManager.setLookAndFeel(new InfoNodeLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DockingLayoutMeetingPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                JFrame jFrame = new JFrame();
                jFrame.setSize(800, 600);

                JPanel[] panels = new JPanel[5];
                panels[0] = new JPanel();
                panels[0].add(new JScrollPane(new JTextArea("panel0")));
                panels[0].setName("pannels 0");
                panels[1] = new JPanel();
                panels[1].add(new JScrollPane(new JTextArea("panel1")));
                panels[2] = new JPanel();
                panels[2].add(new JScrollPane(new JTextArea("panel2")));
                panels[3] = new JPanel();
                panels[3].add(new JScrollPane(new JTextArea("panel3")));
                panels[4] = new JPanel();
                panels[4].add(new JScrollPane(new JTextArea("panel4")));
//                JComponent[] panels = new JComponent[]{),new JScrollPane(new JTextArea("panel2"))};
                //                jFrame.add(new DockingLayoutMeetingPanel(panels));
                jFrame.setVisible(true);
                DockingLayoutMeetingPanel dockingLayoutMeetingPanel = new DockingLayoutMeetingPanel();
                dockingLayoutMeetingPanel.addPanel(panels[0], "panel0", "id1", null, null);
                dockingLayoutMeetingPanel.setPanelAtWindowBar("id1", com.lorent.lvmc.util.Constants.DOCKING_LAYOUT_DIRECTION_DOWN);
                dockingLayoutMeetingPanel.addPanel(panels[1], "panel1", "id2", null, null);
                dockingLayoutMeetingPanel.setPanelAtWindowBar("id2", com.lorent.lvmc.util.Constants.DOCKING_LAYOUT_DIRECTION_DOWN);
                dockingLayoutMeetingPanel.addPanel(panels[2], "panel2", "id3", null, null);
                dockingLayoutMeetingPanel.setPanelAtWindowBar("id3", com.lorent.lvmc.util.Constants.DOCKING_LAYOUT_DIRECTION_LEFT);
                dockingLayoutMeetingPanel.addPanel(panels[3], "panel3", "id4", null, null);
                dockingLayoutMeetingPanel.addPanel(panels[4], "panel4", "id5", null, null);

                dockingLayoutMeetingPanel.setTitleBarStyle(true);
                dockingLayoutMeetingPanel.setTheme(themes[0]);
                jFrame.getContentPane().add(dockingLayoutMeetingPanel.getRootWindow(), BorderLayout.CENTER);
                JMenuBar jMenuBar = new JMenuBar();
                jMenuBar.add(dockingLayoutMeetingPanel.getWindowStyleMenu());
                jMenuBar.add(dockingLayoutMeetingPanel.getLayoutMenu());
                jFrame.setJMenuBar(jMenuBar);


            }
        });
    }
}
