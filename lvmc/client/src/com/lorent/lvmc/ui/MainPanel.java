/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainPanel.java
 *
 * Created on 2011-12-6, 10:51:03
 */
package com.lorent.lvmc.ui;

import java.awt.AWTEvent;
import java.awt.BorderLayout;

import org.apache.log4j.Logger;

import com.lorent.lvmc.controller.ViewManager;

/**
 *
 * @author jack
 */
public class MainPanel extends javax.swing.JPanel implements MainWindow{

    private DockingLayoutMeetingPanel dockingLayoutMeetingPanel;
    private Logger log = Logger.getLogger(MainPanel.class);
    @Override
    public DockingLayoutMeetingPanel getDockingLayoutMeetingPanel() {
        return dockingLayoutMeetingPanel;
    }
    
    /** Creates new form MainPanel */
    public MainPanel() {
        initComponents();
        try {
            dockingLayoutMeetingPanel = ViewManager.getComponent(DockingLayoutMeetingPanel.class);//new DockingLayoutMeetingPanel();
        } catch (Exception ex) {
        	log.error("MainPanel", ex);
        }
//        mainMenuBar.add(dockingLayoutMeetingPanel.getWindowStyleMenu());
//        mainMenuBar.add(dockingLayoutMeetingPanel.getLayoutMenu());
//        mainMenuBar.add(dockingLayoutMeetingPanel.getViewsMenu());
//        mainMenuBar.add(dockingLayoutMeetingPanel.getScreenShareMenu());
        dockingLayoutMeetingPanel.setMenuBar(mainMenuBar);
        add(mainMenuBar,BorderLayout.NORTH);
        add(dockingLayoutMeetingPanel.getRootWindow(),BorderLayout.CENTER);
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainMenuBar = new javax.swing.JMenuBar();

        setPreferredSize(new java.awt.Dimension(800, 600));
        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar mainMenuBar;
    // End of variables declaration//GEN-END:variables
}
