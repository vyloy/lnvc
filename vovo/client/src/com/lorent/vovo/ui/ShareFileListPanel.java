/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ShareFileListPanel.java
 *
 * Created on 2011-12-22, 13:49:11
 */
package com.lorent.vovo.ui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicToolBarUI;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.painter.ImagePainter;
import org.jdesktop.swingx.painter.ImagePainter.ScaleType;

import com.jtattoo.plaf.UIUtil;
import com.jtattoo.plaf.vovoglass.VoVoTabbedPaneUI;
import com.lorent.vovo.Vovo;
import com.lorent.vovo.util.Constants;
import com.lorent.vovo.util.Constants.DataKey;

/**
 * 
 * @author Administrator
 */
public class ShareFileListPanel extends javax.swing.JPanel {

	private JXPanel backgroundXPanel;
	private String sessionID;
	private ShareFileListItem selectedFileListItem;
	private String currentFtpPath;
	private String parentFtpPath;

	public String getParentFtpPath() {
		return parentFtpPath;
	}

	public void setParentFtpPath(String parentFtpPath) {
		this.parentFtpPath = parentFtpPath;
	}

	public String getCurrentFtpPath() {
		return currentFtpPath;
	}

	public void setCurrentFtpPath(String currentFtpPath) {
		this.currentFtpPath = currentFtpPath;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public void setBackgroundXPanel(JXPanel backgroundXPanel) {
		this.backgroundXPanel = backgroundXPanel;
	}

	private static Logger log = Logger.getLogger(ShareFileListPanel.class);

	private Map<String, ShareFileListItem> downLoadItemPanelMap = new HashMap<String, ShareFileListItem>();
	private Map<String, ShareFileListItem> upLoadItemPanelMap = new HashMap<String, ShareFileListItem>();

	public void addDownLoadShareFileListItem(String uniqueFileID,
			ShareFileListItem panel) {
		downLoadItemPanelMap.put(uniqueFileID, panel);
		//		DefaultListModel model = (DefaultListModel) transferFileList.getModel();
		//		model.addElement(panel);
		transferFileListPanel.add(panel);
		transferFileListPanel.revalidate();
	}

	public void addUpLoadShareFileListItem(String uniqueFileID,
			ShareFileListItem panel) {
		upLoadItemPanelMap.put(uniqueFileID, panel);
		//		DefaultListModel model = (DefaultListModel) transferFileList.getModel();
		//		model.addElement(panel);
		transferFileListPanel.add(panel);
		transferFileListPanel.revalidate();
	}

	public void addUpLoadShareFileListItem(ShareFileListItem panel) {
		//		DefaultListModel model = (DefaultListModel) transferFileList.getModel();
		//		model.addElement(panel);
		transferFileListPanel.add(panel);
		transferFileListPanel.revalidate();
	}

	public ShareFileListItem getDownLoadShareFileListItem(String uniqueFileID) {
		return downLoadItemPanelMap.get(uniqueFileID);
	}

	public ShareFileListItem getUpLoadShareFileListItem(String uniqueFileID) {
		return upLoadItemPanelMap.get(uniqueFileID);
	}

	javax.swing.JButton loadBoardJButton;

	/** Creates new form ShareFileListPanel */
	public ShareFileListPanel() {
		initComponents();

		fileList.setModel(new DefaultListModel());
		fileList.setCellRenderer(new MyListCellRenderer());
		//		transferFileList.setModel(new DefaultListModel());
		//		transferFileList.setCellRenderer(new MyListCellRenderer());
		//
		//		DefaultListModel model = (DefaultListModel) transferFileList.getModel();
		//		model.removeAllElements();

		buttonToolBar.setUI(new BasicToolBarUI());
		jScrollPane1.getViewport().setOpaque(false);
		transferFileListScrollPane.getViewport().setOpaque(false);
		BufferedImage whiteimg;
		try {
			whiteimg = Vovo.getMyContext().getDataManager().getValue(
					DataKey.WHITE_IMAGE.toString());
			ImagePainter ip = new ImagePainter(whiteimg);
			ip.setScaleToFit(true);
			ip.setScaleType(ScaleType.Distort);
			//			this.bannerPanel.setBackgroundPainter(ip);
			fileListXPanel.setBackgroundPainter(ip);
			ImagePainter ip2 = new ImagePainter(whiteimg);
			ip2.setScaleToFit(true);
			ip2.setScaleType(ScaleType.Distort);
			transferFileListXPanel.setBackgroundPainter(ip2);
		} catch (Exception e) {
			log.error("ShareFileListPanel()", e);
			e.printStackTrace();
		}
		tabbedPane.setUI(new VoVoTabbedPaneUI());

	}

	public void setOperationButtonEnable(boolean flag) {
		if (!flag) {
			downloadButton.setEnabled(flag);
			deleteButton.setEnabled(flag);
		}
		reflashButton.setEnabled(flag);
		uploadFileButton.setEnabled(flag);
		createDirButton.setEnabled(flag);
	}

	private class MyListCellRenderer extends DefaultListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			ShareFileListItem node = (ShareFileListItem) value;
			node.setTextForeground(UIManager.getColor("List.foreground"));
			if (isSelected || cellHasFocus) {
				//								node.setOpaque(false);
				//				node.setBorder(new javax.swing.border.LineBorder(UIManager
				//						.getColor("List.selectionForeground"), 1, true));
				node.setBorder(BorderFactory.createEtchedBorder());
				//				node.setForeground(UIManager
				//						.getColor("List.selectionForeground"));
				//				node.setBackground(UIManager
				//						.getColor("List.selectionBackground"));
				//				node.getLeftPanel().setForeground(
				//						UIManager.getColor("List.selectionForeground"));
				//				node.getLeftPanel().setBackground(
				//						UIManager.getColor("List.selectionBackground"));
			} else {
				//								node.setOpaque(false);
				node.setBorder(null);
				//				node.setBackground(UIManager.getColor("List.background"));
				//				node.setForeground(UIManager.getColor("List.foreground"));
				//				node.getLeftPanel().setForeground(
				//						UIManager.getColor("List.foreground"));
				//				node.getLeftPanel().setBackground(
				//						UIManager.getColor("List.background"));
			}
			return node;
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		fileListPopupMenu = new javax.swing.JPopupMenu();
		downLoadMenuItem = new javax.swing.JMenuItem();
		loadToBoardMenuItem = new javax.swing.JMenuItem();
		deleteFileMenuItem = new javax.swing.JMenuItem();
		jPanel2 = new javax.swing.JPanel();
		buttonToolBar = new javax.swing.JToolBar();
		reflashButton = new javax.swing.JButton();
		uploadFileButton = new javax.swing.JButton();
		downloadButton = new javax.swing.JButton();
		deleteButton = new javax.swing.JButton();
		createDirButton = new javax.swing.JButton();
		jSplitPane1 = new javax.swing.JSplitPane();
		fileListXPanel = new org.jdesktop.swingx.JXPanel();
		filePathTextField = new javax.swing.JTextField();
		jScrollPane1 = new javax.swing.JScrollPane();
		fileList = new javax.swing.JList();
		tabbedPane = new javax.swing.JTabbedPane();
		jPanel1 = new javax.swing.JPanel();
		transferFileListXPanel = new org.jdesktop.swingx.JXPanel();
		transferFileListScrollPane = new javax.swing.JScrollPane();
		transferFileListPanel = new javax.swing.JPanel();

		downLoadMenuItem.setText("\u4e0b\u8f7d");
		downLoadMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				downLoadMenuItemActionPerformed(evt);
			}
		});
		fileListPopupMenu.add(downLoadMenuItem);

		loadToBoardMenuItem
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						loadToBoardMenuItemActionPerformed(evt);
					}
				});
		fileListPopupMenu.add(loadToBoardMenuItem);

		deleteFileMenuItem.setText("\u5220\u9664\u6587\u4ef6");
		deleteFileMenuItem
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						deleteFileMenuItemActionPerformed(evt);
					}
				});
		fileListPopupMenu.add(deleteFileMenuItem);

		setOpaque(false);
		addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent evt) {
				formComponentResized(evt);
			}
		});
		setLayout(new java.awt.BorderLayout());

		jPanel2.setOpaque(false);
		jPanel2.setLayout(new java.awt.BorderLayout());

		buttonToolBar.setFloatable(false);
		buttonToolBar.setOpaque(false);

		reflashButton.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/reflash.png"))); // NOI18N
		reflashButton.setText(Vovo.getMyContext().getViewManager().getUIString(
				"ShareFileListPanel.reflash"));
		reflashButton.setToolTipText(Vovo.getMyContext().getViewManager()
				.getUIString("ShareFileListPanel.reflash"));
		reflashButton.setContentAreaFilled(false);
		reflashButton.setFocusable(false);
		reflashButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		reflashButton.setMaximumSize(new java.awt.Dimension(60, 47));
		reflashButton.setMinimumSize(new java.awt.Dimension(60, 47));
		reflashButton.setPreferredSize(new java.awt.Dimension(60, 47));
		reflashButton
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		reflashButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				reflashButtonActionPerformed(evt);
			}
		});
		buttonToolBar.add(reflashButton);

		uploadFileButton.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource(
						"/com/lorent/vovo/resource/images/files/file-up.png"))); // NOI18N
		uploadFileButton.setText(Vovo.getMyContext().getViewManager()
				.getUIString("ShareFileListPanel.upload"));
		uploadFileButton.setToolTipText(Vovo.getMyContext().getViewManager()
				.getUIString("ShareFileListPanel.upload"));
		uploadFileButton.setContentAreaFilled(false);
		uploadFileButton.setFocusable(false);
		uploadFileButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		uploadFileButton.setMaximumSize(new java.awt.Dimension(60, 47));
		uploadFileButton.setPreferredSize(new java.awt.Dimension(25, 47));
		uploadFileButton
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		uploadFileButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				uploadFileButtonActionPerformed(evt);
			}
		});
		buttonToolBar.add(uploadFileButton);

		downloadButton
				.setIcon(new javax.swing.ImageIcon(
						getClass()
								.getResource(
										"/com/lorent/vovo/resource/images/files/go-down-10.png"))); // NOI18N
		downloadButton.setText(Vovo.getMyContext().getViewManager()
				.getUIString("ShareFileListPanel.download"));
		downloadButton.setToolTipText(Vovo.getMyContext().getViewManager()
				.getUIString("ShareFileListPanel.download"));
		downloadButton.setContentAreaFilled(false);
		downloadButton.setEnabled(false);
		downloadButton.setFocusable(false);
		downloadButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		downloadButton.setMaximumSize(new java.awt.Dimension(60, 47));
		downloadButton.setPreferredSize(new java.awt.Dimension(25, 47));
		downloadButton
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		downloadButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				downloadButtonActionPerformed(evt);
			}
		});
		buttonToolBar.add(downloadButton);

		deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/vovo/resource/images/files/delete1.png"))); // NOI18N
		deleteButton.setText(Vovo.getMyContext().getViewManager().getUIString(
				"ShareFileListPanel.delete"));
		deleteButton.setToolTipText(Vovo.getMyContext().getViewManager()
				.getUIString("ShareFileListPanel.delete"));
		deleteButton.setContentAreaFilled(false);
		deleteButton.setEnabled(false);
		deleteButton.setFocusable(false);
		deleteButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		deleteButton.setMaximumSize(new java.awt.Dimension(60, 47));
		deleteButton.setPreferredSize(new java.awt.Dimension(25, 47));
		deleteButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		deleteButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteButtonActionPerformed(evt);
			}
		});
		buttonToolBar.add(deleteButton);

		createDirButton
				.setIcon(new javax.swing.ImageIcon(getClass().getResource(
						"/com/lorent/vovo/resource/images/files/folder-23.png"))); // NOI18N
		createDirButton.setText(Vovo.getMyContext().getViewManager()
				.getUIString("ShareFileListPanel.createPath"));
		createDirButton.setToolTipText(Vovo.getMyContext().getViewManager()
				.getUIString("ShareFileListPanel.createPath"));
		createDirButton.setContentAreaFilled(false);
		createDirButton.setFocusable(false);
		createDirButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		createDirButton.setMaximumSize(new java.awt.Dimension(60, 47));
		createDirButton.setPreferredSize(new java.awt.Dimension(49, 47));
		createDirButton
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		createDirButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createDirButtonActionPerformed(evt);
				loadBoardButtonActionPerformed(evt);
			}
		});
		buttonToolBar.add(createDirButton);

		jPanel2.add(buttonToolBar, java.awt.BorderLayout.PAGE_START);

		jSplitPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		jSplitPane1.setDividerSize(3);
		jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
		jSplitPane1.setOpaque(false);

		fileListXPanel.setOpaque(false);
		fileListXPanel.setLayout(new java.awt.BorderLayout());

		filePathTextField.setBorder(javax.swing.BorderFactory
				.createEmptyBorder(1, 1, 1, 1));
		filePathTextField.setEnabled(false);
		filePathTextField.setOpaque(false);
		fileListXPanel.add(filePathTextField, java.awt.BorderLayout.NORTH);

		jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1,
				1, 1, 1));
		jScrollPane1.setOpaque(false);

		fileList
				.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		fileList.setOpaque(false);
		fileList.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				fileListMouseClicked(evt);
			}
		});
		fileList
				.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
					public void valueChanged(
							javax.swing.event.ListSelectionEvent evt) {
						fileListValueChanged(evt);
					}
				});
		jScrollPane1.setViewportView(fileList);

		fileListXPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

		jSplitPane1.setTopComponent(fileListXPanel);

		tabbedPane
				.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

		jPanel1.setOpaque(false);
		jPanel1.setLayout(new java.awt.BorderLayout());

		transferFileListXPanel.setOpaque(false);
		transferFileListXPanel.setLayout(new java.awt.BorderLayout());

		transferFileListScrollPane.setBorder(javax.swing.BorderFactory
				.createEmptyBorder(1, 1, 1, 1));
		transferFileListScrollPane.setOpaque(false);

		transferFileListPanel.setOpaque(false);
		transferFileListPanel.setLayout(new javax.swing.BoxLayout(
				transferFileListPanel, javax.swing.BoxLayout.Y_AXIS));
		transferFileListScrollPane.setViewportView(transferFileListPanel);

		transferFileListXPanel.add(transferFileListScrollPane,
				java.awt.BorderLayout.CENTER);

		jPanel1.add(transferFileListXPanel, java.awt.BorderLayout.CENTER);

		tabbedPane.addTab(Vovo.getMyContext().getViewManager().getUIString(
				"ShareFileListPanel.transfer"), jPanel1);

		jSplitPane1.setBottomComponent(tabbedPane);

		jPanel2.add(jSplitPane1, java.awt.BorderLayout.CENTER);

		add(jPanel2, java.awt.BorderLayout.CENTER);
	}// </editor-fold>
	//GEN-END:initComponents

	private void loadBoardButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void fileListValueChanged(javax.swing.event.ListSelectionEvent evt) {
		if (evt.getValueIsAdjusting()) {
			return;
		}
		log.debug("fileListValueChanged");
		Object selectedValue = fileList.getSelectedValue();
		if (selectedValue instanceof ShareFileListItem) {
			selectedFileListItem = (ShareFileListItem) selectedValue;
			//			filePathTextField.setText(selectedFileListItem.getAbsolutePath());
			if (selectedFileListItem.isFolder()) {
				downloadButton.setEnabled(false);
				if (selectedFileListItem.isUpperFolderType()) {
					deleteButton.setEnabled(false);
				} else {
					deleteButton.setEnabled(true);
				}
			} else {
				downloadButton.setEnabled(true);
				deleteButton.setEnabled(true);
			}
		} else {
			selectedFileListItem = null;
			downloadButton.setEnabled(false);
			deleteButton.setEnabled(false);
		}
	}

	private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
		//		deleteFile();

		Vovo.exeC("sharefile", "deleteFileAndPath", this, selectedFileListItem);

	}

	private void formComponentResized(java.awt.event.ComponentEvent evt) {
		jSplitPane1.setDividerLocation(0.8);
	}

	private void fileListMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_fileListMouseClicked
		log.debug("fileListMouseClicked");
		//		int locationToIndex = fileList.locationToIndex(evt.getPoint());
		//		fileList.setSelectedIndex(locationToIndex);
		//		Object selectedValue = fileList.getSelectedValue();
		//		ShareFileListItem item = (ShareFileListItem) selectedValue;
		//		if (selectedValue != null) {
		//			
		//		}

		if (evt.getButton() == MouseEvent.BUTTON3) {

			// fileListPopupMenu.show(evt.getComponent(),evt.getPoint().x,evt.getPoint().y);
		} else if (evt.getButton() == MouseEvent.BUTTON1
				&& evt.getClickCount() == 2) {
			Vovo.exeC("sharefile", "doubleClickFileList", this,
					selectedFileListItem);
		} else if (evt.getButton() == MouseEvent.BUTTON1
				&& evt.getClickCount() == 1) {
			//				Boolean bflag = (Boolean) ControllerFacade.execute(
			//						"shareFileListController", "checkFileSupportConvert",
			//						item.getFileName());
			//				if (bflag) {
			//					loadBoardButton.setEnabled(true);
			//				} else {
			//					loadBoardButton.setEnabled(false);
			//				}
		}

	}// GEN-LAST:event_fileListMouseClicked

	private void uploadFileButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_uploadFileButtonActionPerformed
		Vovo.exeC("sharefile", "upLoadFile", this);
	}// GEN-LAST:event_uploadFileButtonActionPerformed

	private void reflashButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_reflashButtonActionPerformed
		Vovo.getMyContext().getExecuteManager().executeController("sharefile",
				"getDefaultFileList", this);
	}// GEN-LAST:event_reflashButtonActionPerformed

	private void downLoadMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
	}

	private void loadToBoardMenuItemActionPerformed(
			java.awt.event.ActionEvent evt) {// GEN-FIRST:event_loadToBoardMenuItemActionPerformed

	}// GEN-LAST:event_loadToBoardMenuItemActionPerformed

	private void downloadButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_downloadButtonActionPerformed
		Vovo.exeC("sharefile", "downLoadFile", this, selectedFileListItem);
	}// GEN-LAST:event_downloadButtonActionPerformed

	private void createDirButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_loadBoardButtonActionPerformed
		Vovo.exeC("sharefile", "createFilePath", this);
	}// GEN-LAST:event_loadBoardButtonActionPerformed

	private void deleteFileMenuItemActionPerformed(
			java.awt.event.ActionEvent evt) {// GEN-FIRST:event_deleteFileMenuItemActionPerformed
		//		deleteFile();
	}// GEN-LAST:event_deleteFileMenuItemActionPerformed

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JToolBar buttonToolBar;
	private javax.swing.JButton createDirButton;
	private javax.swing.JButton deleteButton;
	private javax.swing.JMenuItem deleteFileMenuItem;
	private javax.swing.JMenuItem downLoadMenuItem;
	private javax.swing.JButton downloadButton;
	private javax.swing.JList fileList;
	private javax.swing.JPopupMenu fileListPopupMenu;
	private org.jdesktop.swingx.JXPanel fileListXPanel;
	private javax.swing.JTextField filePathTextField;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JSplitPane jSplitPane1;
	private javax.swing.JMenuItem loadToBoardMenuItem;
	private javax.swing.JButton reflashButton;
	private javax.swing.JTabbedPane tabbedPane;
	private javax.swing.JPanel transferFileListPanel;
	private javax.swing.JScrollPane transferFileListScrollPane;
	private org.jdesktop.swingx.JXPanel transferFileListXPanel;
	private javax.swing.JButton uploadFileButton;

	// End of variables declaration//GEN-END:variables

	public JPopupMenu getFileListPopupMenu() {
		return fileListPopupMenu;
	}

	public JMenuItem getLoadToBoardMenuItem() {
		return loadToBoardMenuItem;
	}

	public JMenuItem getDeleteFileMenuItem() {
		return deleteFileMenuItem;
	}

	public JList getFileList() {
		return fileList;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public javax.swing.JScrollPane getTransferFileListScrollPane() {
		return transferFileListScrollPane;
	}

	//	public javax.swing.JList getTransferFileList() {
	//		return transferFileList;
	//	}

	public javax.swing.JTextField getFilePathTextField() {
		return filePathTextField;
	}

	public javax.swing.JToolBar getButtonToolBar() {
		return buttonToolBar;
	}

}
