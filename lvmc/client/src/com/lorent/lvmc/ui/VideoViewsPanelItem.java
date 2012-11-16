/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VideosPanelItem.java
 *
 * Created on 2011-12-14, 14:44:06
 */
package com.lorent.lvmc.ui;

import com.lorent.common.component.ImageAwtPanel;
import com.lorent.common.dnd.MyDragGestureListener;
import com.lorent.common.dnd.MyDropTargetListener;
import com.lorent.lvmc.controller.ControllerFacade;
import com.lorent.lvmc.controller.ViewManager;
import com.lorent.lvmc.util.DataUtil;
import com.lorent.lvmc.util.LvmcUtil;

import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.logging.Level;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class VideoViewsPanelItem extends javax.swing.JPanel {

	private static Logger log = Logger.getLogger(VideoViewsPanelItem.class);
	private String lccUserName;
	private String nickName;
	private int videoIndex;

	public int getVideoIndex() {
		return videoIndex;
	}

	public void setVideoIndex(int videoIndex) {
		this.videoIndex = videoIndex;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		if(!LvmcUtil.isUCSAPP()){
			this.nickName = nickName;
			nickNameLabel.setText(nickName);
		}else{
			this.nickName = nickName;
			nickNameLabel.setText(nickName);
		}
	}

	private String confno;
	private boolean viewingVideo;

	public void setViewingVideo(boolean viewingVideo) {
		this.viewingVideo = viewingVideo;
		if (viewingVideo) {
			enableVideoButton.setSelected(true);
			videoEnableButton.setVisible(false);
			videoDisableButton.setVisible(true);
		} else {
			disableVideoButton.setSelected(true);
			videoEnableButton.setVisible(true);
			videoDisableButton.setVisible(false);
		}
	}

	public boolean isViewingVideo() {
		return viewingVideo;
	}

	public String getConfno() {
		return confno;
	}

	public void setConfno(String confno) {
		this.confno = confno;
	}

	public String getLccUserName() {
		return lccUserName;
	}

	public void setLccUserName(String lccUserName) {
		this.lccUserName = lccUserName;
		if (lccUserName == null || lccUserName.equals("")) {
			lccNoLabel.setText("");
		} else {
			if(LvmcUtil.isUCSAPP()){
				lccNoLabel.setText("(" + lccUserName + ")");
			}else{
				lccNoLabel.setText("(" + lccUserName + ")");
			}
		}
	}

	public void setMaximizeable(boolean flag) {
		maxButton.setVisible(flag);
	}

	/** Creates new form VideosPanelItem */
	public VideoViewsPanelItem() {
		initComponents();
		setMaximizeable(false);
		buttonPanel.setVisible(false);
		enableVideoButton.setVisible(false);
		disableVideoButton.setVisible(false);
		videoEnableButton.setVisible(false);
		videoDisableButton.setVisible(true);
		lccNoLabel.setText("");
		nickNameLabel.setText("");
		videoPanel.setImg(Toolkit.getDefaultToolkit().createImage(
				getClass().getResource(
						"/com/lorent/lvmc/resource/images/nothing.png")));
		final VideoViewsPanelItem instance = this;
		new MyDropTargetListener(this) {

			@Override
			public void dragEnter(DropTargetDragEvent dtde) {
				super.dragEnter(dtde);
				setBorder(new javax.swing.border.LineBorder(UIManager
						.getColor("List.selectionBackground"), 3, true));
				log.debug("dragEnter");
			}

			@Override
			public void dragExit(DropTargetEvent dte) {
				super.dragExit(dte);
				setBorder(javax.swing.BorderFactory.createEtchedBorder());
				log.debug("dragExit");
			}

			@Override
			public void onDrop(DropTargetDropEvent dtde, Component source,
					Map<String, Object> properties) {
				super.onDrop(dtde, source, properties);
				setBorder(javax.swing.BorderFactory.createEtchedBorder());
				ControllerFacade.execute("videoViewsController",
						"videoViewItemOnDrop", dtde, source, properties,
						instance);
			}

		};

		MyDragGestureListener myDragGestureListener = new MyDragGestureListener();
		myDragGestureListener.setPropery("object", this);
		new DragSource().createDefaultDragGestureRecognizer(this,
				DnDConstants.ACTION_MOVE, myDragGestureListener);
	}

	public void enableVideo() {
		Integer result = (Integer) ControllerFacade.execute(
				"videoViewsController", "enableVideo", confno, lccUserName,
				videoPanel);
		log.info("result:" + result);
	}

	public void changeVideo() {
		Integer result = (Integer) ControllerFacade.execute(
				"videoViewsController", "changeVideo", confno, lccUserName,
				videoPanel);
		log.info("result:" + result);
	}

	public void disableVideo() {
		Integer result = (Integer) ControllerFacade.execute(
				"videoViewsController", "disableVideo", confno, lccUserName);
		log.info("result:" + result);
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		videoButtonGroup = new javax.swing.ButtonGroup();
		videoPanel = new com.lorent.common.component.ImageAwtPanel();
		topPanel = new javax.swing.JPanel();
		buttonPanel = new javax.swing.JPanel();
		jToolBar1 = new javax.swing.JToolBar();
		enableVideoButton = new javax.swing.JToggleButton();
		disableVideoButton = new javax.swing.JToggleButton();
		videoEnableButton = new javax.swing.JButton();
		videoDisableButton = new javax.swing.JButton();
		maxButton = new javax.swing.JButton();
		removeVideoButton = new javax.swing.JButton();
		textPanel = new javax.swing.JPanel();
		nickNameLabel = new javax.swing.JLabel();
		lccNoLabel = new javax.swing.JLabel();

		setBorder(javax.swing.BorderFactory.createEtchedBorder());
		setMinimumSize(new java.awt.Dimension(200, 200));
		setLayout(new java.awt.BorderLayout());

		videoPanel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				videoPanelMouseClicked(evt);
			}
		});

		javax.swing.GroupLayout videoPanelLayout = new javax.swing.GroupLayout(
				videoPanel);
		videoPanel.setLayout(videoPanelLayout);
		videoPanelLayout.setHorizontalGroup(videoPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 206, Short.MAX_VALUE));
		videoPanelLayout.setVerticalGroup(videoPanelLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 165,
				Short.MAX_VALUE));

		add(videoPanel, java.awt.BorderLayout.CENTER);

		topPanel.setLayout(new java.awt.GridBagLayout());

		buttonPanel.setPreferredSize(new java.awt.Dimension(249, 31));
		buttonPanel.setLayout(new java.awt.FlowLayout(
				java.awt.FlowLayout.RIGHT, 2, 0));

		jToolBar1.setFloatable(false);
		jToolBar1.setRollover(true);

		videoButtonGroup.add(enableVideoButton);
		enableVideoButton.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource(
						"/com/lorent/lvmc/resource/images/camera-close.png"))); // NOI18N
		enableVideoButton.setBorder(null);
		enableVideoButton.setBorderPainted(false);
		enableVideoButton.setContentAreaFilled(false);
		enableVideoButton.setFocusable(false);
		enableVideoButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		enableVideoButton
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		enableVideoButton.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				enableVideoButtonItemStateChanged(evt);
			}
		});
		enableVideoButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						enableVideoButtonActionPerformed(evt);
					}
				});
		jToolBar1.add(enableVideoButton);

		videoButtonGroup.add(disableVideoButton);
		disableVideoButton.setIcon(new javax.swing.ImageIcon(
				getClass().getResource(
						"/com/lorent/lvmc/resource/images/camera-open.png"))); // NOI18N
		disableVideoButton.setSelected(true);
		disableVideoButton.setBorder(new javax.swing.border.LineBorder(
				new java.awt.Color(0, 204, 204), 2, true));
		disableVideoButton.setBorderPainted(false);
		disableVideoButton.setContentAreaFilled(false);
		disableVideoButton.setFocusable(false);
		disableVideoButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		disableVideoButton
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		disableVideoButton.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				disableVideoButtonItemStateChanged(evt);
			}
		});
		disableVideoButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						disableVideoButtonActionPerformed(evt);
					}
				});
		jToolBar1.add(disableVideoButton);

		videoEnableButton.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource(
						"/com/lorent/lvmc/resource/images/camera-close.png"))); // NOI18N
		videoEnableButton.setFocusable(false);
		videoEnableButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		videoEnableButton
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		videoEnableButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						videoEnableButtonActionPerformed(evt);
					}
				});
		jToolBar1.add(videoEnableButton);

		videoDisableButton.setIcon(new javax.swing.ImageIcon(
				getClass().getResource(
						"/com/lorent/lvmc/resource/images/camera-open.png"))); // NOI18N
		videoDisableButton.setFocusable(false);
		videoDisableButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		videoDisableButton
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		videoDisableButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						videoDisableButtonActionPerformed(evt);
					}
				});
		jToolBar1.add(videoDisableButton);

		maxButton.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/lvmc/resource/images/object-order-raise.png"))); // NOI18N
		maxButton.setToolTipText("\u653e\u5927");
		maxButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		maxButton.setBorderPainted(false);
		maxButton.setContentAreaFilled(false);
		maxButton.setFocusPainted(false);
		maxButton.setFocusable(false);
		maxButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		maxButton.setPreferredSize(new java.awt.Dimension(23, 25));
		maxButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		maxButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				maxButtonActionPerformed(evt);
			}
		});
		jToolBar1.add(maxButton);

		removeVideoButton.setIcon(new javax.swing.ImageIcon(
				getClass().getResource(
						"/com/lorent/lvmc/resource/images/dialog-no-2.png"))); // NOI18N
		removeVideoButton.setToolTipText("\u79fb\u9664");
		removeVideoButton.setFocusable(false);
		removeVideoButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		removeVideoButton
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		removeVideoButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						removeVideoButtonActionPerformed(evt);
					}
				});
		jToolBar1.add(removeVideoButton);

		buttonPanel.add(jToolBar1);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		topPanel.add(buttonPanel, gridBagConstraints);

		textPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

		nickNameLabel.setText("jLabel1");
		textPanel.add(nickNameLabel);

		lccNoLabel.setText("jLabel1");
		textPanel.add(lccNoLabel);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		topPanel.add(textPanel, gridBagConstraints);

		add(topPanel, java.awt.BorderLayout.NORTH);
	}// </editor-fold>
	//GEN-END:initComponents

	private void enableVideoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enableVideoButtonActionPerformed
		log.info("enableVideoButtonActionPerformed");
		Integer result = (Integer) ControllerFacade.execute(
				"videoViewsController", "enableVideo", confno, lccUserName,
				videoPanel);
		log.info("result:" + result);
		if (result == 0) {
			viewingVideo = true;
		}
	}//GEN-LAST:event_enableVideoButtonActionPerformed

	private void disableVideoButtonActionPerformed(
			java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disableVideoButtonActionPerformed
		log.info("disableVideoButtonActionPerformed");
		Integer result = (Integer) ControllerFacade.execute(
				"videoViewsController", "disableVideo", confno, lccUserName);
		log.info("result:" + result);
		viewingVideo = false;
		try {
			Thread.sleep(200);
		} catch (InterruptedException ex) {
			log.error("disableVideoButtonActionPerformed", ex);
		}
		videoPanel.DrawImage();
		ControllerFacade.execute("videoViewsController",
				"reflashVideoViewsPanel");
	}//GEN-LAST:event_disableVideoButtonActionPerformed

	private void enableVideoButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_enableVideoButtonItemStateChanged
		log.info("enableVideoButtonItemStateChanged:" + evt);
		if (evt.getStateChange() == ItemEvent.SELECTED) {
			enableVideoButton.setBorder(new javax.swing.border.LineBorder(
					new java.awt.Color(0, 204, 204), 2, true));
			enableVideoButton.setBorderPainted(true);
			disableVideoButton.setBorder(null);
			disableVideoButton.setBorderPainted(false);
		}
	}//GEN-LAST:event_enableVideoButtonItemStateChanged

	private void disableVideoButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_disableVideoButtonItemStateChanged
		log.info("disableVideoButtonItemStateChanged" + evt);
		if (evt.getStateChange() == ItemEvent.SELECTED) {
			enableVideoButton.setBorder(null);
			enableVideoButton.setBorderPainted(false);
			disableVideoButton.setBorder(new javax.swing.border.LineBorder(
					new java.awt.Color(0, 204, 204), 2, true));
			disableVideoButton.setBorderPainted(true);
		}
	}//GEN-LAST:event_disableVideoButtonItemStateChanged

	private void maxButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxButtonActionPerformed
		//        ControllerFacade.execute("videoViewsController", "maxVideoItemPanel", this);
		openMaxedVideoDialog();
	}//GEN-LAST:event_maxButtonActionPerformed

	private void videoDisableButtonActionPerformed(
			java.awt.event.ActionEvent evt) {//GEN-FIRST:event_videoDisableButtonActionPerformed
		log.info("videoDisableButtonActionPerformed");
		Integer result = (Integer) ControllerFacade.execute(
				"videoViewsController", "disableVideo", confno, lccUserName);
		log.info("result:" + result);
		viewingVideo = false;
		setViewingVideo(viewingVideo);
		try {
			Thread.sleep(200);
		} catch (InterruptedException ex) {
			log.error("videoDisableButtonActionPerformed", ex);
		}
		videoPanel.DrawImage();
		ControllerFacade.execute("videoViewsController",
				"reflashVideoViewsPanel");
	}//GEN-LAST:event_videoDisableButtonActionPerformed

	private void videoEnableButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_videoEnableButtonActionPerformed
		log.info("videoEnableButtonActionPerformed");
		Integer result = (Integer) ControllerFacade.execute(
				"videoViewsController", "enableVideo", confno, lccUserName,
				videoPanel);
		log.info("result:" + result);
		if (result == 0) {
			viewingVideo = true;
			setViewingVideo(viewingVideo);
		}
	}//GEN-LAST:event_videoEnableButtonActionPerformed

	private void removeVideoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeVideoButtonActionPerformed
		ControllerFacade.execute("videoViewsController",
				"removeFromVideoPanel", lccUserName);
	}//GEN-LAST:event_removeVideoButtonActionPerformed

	private boolean isMaxed = false;
	private Dimension primitiveSize = null;

	private void videoPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_videoPanelMouseClicked
		if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1
				&& isViewingVideo()) {
			openMaxedVideoDialog();
		}
	}//GEN-LAST:event_videoPanelMouseClicked

	private void openMaxedVideoDialog() {

		try {
			DataUtil.setValue(DataUtil.Key.primitiveVideoPanel, this);
			MaxedVideoDialog dialog = ViewManager.getComponent(
					MaxedVideoDialog.class, new Class[] { java.awt.Frame.class,
							boolean.class }, new Object[] {
							(Frame) DataUtil.getTopWindow(), false });
			if (lccUserName == null || lccUserName.equals("")) {
				dialog.setTitle(nickName);
			} else {
				dialog.setTitle(nickName + "(" + lccUserName + ")");
			}
			dialog.setVisible(true);
			Thread.sleep(150);
			Integer result = (Integer) ControllerFacade.execute(
					"videoViewsController", "changeVideo", confno, lccUserName,
					dialog.getVideoPanel());
		} catch (Exception ex) {
			java.util.logging.Logger.getLogger(
					VideoViewsPanelItem.class.getName()).log(Level.SEVERE,
					null, ex);
			log.error("openMaxedVideoDialog", ex);
		}

	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JPanel buttonPanel;
	private javax.swing.JToggleButton disableVideoButton;
	private javax.swing.JToggleButton enableVideoButton;
	private javax.swing.JToolBar jToolBar1;
	private javax.swing.JLabel lccNoLabel;
	private javax.swing.JButton maxButton;
	private javax.swing.JLabel nickNameLabel;
	private javax.swing.JButton removeVideoButton;
	private javax.swing.JPanel textPanel;
	private javax.swing.JPanel topPanel;
	private javax.swing.ButtonGroup videoButtonGroup;
	private javax.swing.JButton videoDisableButton;
	private javax.swing.JButton videoEnableButton;
	private com.lorent.common.component.ImageAwtPanel videoPanel;

	// End of variables declaration//GEN-END:variables

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public ImageAwtPanel getVideoPanel() {
		return videoPanel;
	}

}
