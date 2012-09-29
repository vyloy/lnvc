/*
 * MaxedVideoDialog.java
 *
 * Created on __DATE__, __TIME__
 */

package com.lorent.lvmc.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.lorent.common.component.ImageAwtPanel;
import com.lorent.lvmc.controller.ControllerFacade;
import com.lorent.lvmc.util.DataUtil;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author  __USER__
 */
public class MaxedVideoDialog extends java.awt.Dialog {

	/** Creates new form MaxedVideoDialog */
	public MaxedVideoDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		this.setSize(screenSize);
		videoPanel.setImg(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/com/lorent/lvmc/resource/images/black.png")));
	}

	private void changeVideo() {
		VideoViewsPanelItem panelItem = DataUtil.getPrimitiveVideoPanel();
		Integer result = (Integer) ControllerFacade.execute(
				"videoViewsController", "changeVideo", panelItem.getConfno(),
				panelItem.getLccUserName(), panelItem.getVideoPanel());
		this.dispose();
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		videoPanel = new com.lorent.common.component.ImageAwtPanel();

		setAlwaysOnTop(true);
		setUndecorated(true);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				closeDialog(evt);
			}
		});
		addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				formKeyReleased(evt);
			}
		});

		videoPanel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				videoPanelMouseClicked(evt);
			}
		});
		videoPanel.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				videoPanelKeyPressed(evt);
			}
		});
		add(videoPanel, java.awt.BorderLayout.CENTER);

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	private void formKeyReleased(java.awt.event.KeyEvent evt) {
		if(evt.getKeyCode() == KeyEvent.VK_ESCAPE ){
            changeVideo();
        }
	}

	private void videoPanelKeyPressed(java.awt.event.KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
			changeVideo();
		}
	}

	private void videoPanelMouseClicked(java.awt.event.MouseEvent evt) {
		if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2) {
			changeVideo();
		}
	}

	/** Closes the dialog */
	private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
		setVisible(false);
		dispose();
	}//GEN-LAST:event_closeDialog

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				MaxedVideoDialog dialog = new MaxedVideoDialog(
						new java.awt.Frame(), true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private com.lorent.common.component.ImageAwtPanel videoPanel;

	// End of variables declaration//GEN-END:variables

	public com.lorent.common.component.ImageAwtPanel getVideoPanel() {
		return videoPanel;
	}

}