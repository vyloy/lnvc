/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ShareFileListPanel.java
 *
 * Created on 2011-12-22, 13:49:11
 */
package com.lorent.lvmc.ui;

import com.lorent.common.dnd.MyDropTargetListener;
import com.lorent.lvmc.controller.ControllerFacade;
import com.lorent.lvmc.controller.ViewManager;
import com.lorent.lvmc.dto.FileDto;
import com.lorent.lvmc.util.PermissionUtil;
import com.lorent.lvmc.util.StringUtil;
import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JTabbedPane;
import javax.swing.ListModel;
import javax.swing.UIManager;
import org.apache.log4j.Logger;

/**
 * 
 * @author Administrator
 */
public class ShareFileListPanel extends javax.swing.JPanel {

	private static Logger log = Logger.getLogger(ShareFileListPanel.class);

	private Map<String, ShareFileListItem> downLoadItemPanelMap = new HashMap<String, ShareFileListItem>();
	private Map<String, ShareFileListItem> upLoadItemPanelMap = new HashMap<String, ShareFileListItem>();

	public void addDownLoadShareFileListItem(String uniqueFileID,
			ShareFileListItem panel) {
		downLoadItemPanelMap.put(uniqueFileID, panel);
		//		DefaultListModel model = (DefaultListModel) transferFileList.getModel();
		//		model.addElement(panel);
		transferFileListPanel.add(panel);
	}

	public void addUpLoadShareFileListItem(String uniqueFileID,
			ShareFileListItem panel) {
		upLoadItemPanelMap.put(uniqueFileID, panel);
		//		DefaultListModel model = (DefaultListModel) transferFileList.getModel();
		//		model.addElement(panel);
		transferFileListPanel.add(panel);

		//        JScrollBar verticalScrollBar = getTransferFileListScrollPane().getVerticalScrollBar();
		//        int maximum = verticalScrollBar.getMaximum();
		//        verticalScrollBar.setValue(maximum);
		getTransferFileListScrollPane().getViewport().scrollRectToVisible(
				new Rectangle(0, transferFileListPanel.getHeight()
						- getTransferFileListScrollPane().getHeight()
						+ panel.getHeight(), transferFileListPanel.getWidth(),
						getTransferFileListScrollPane().getHeight()
								- panel.getHeight()));
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
		if (!PermissionUtil.hasPermission(PermissionUtil.SHARE_DOCUMENT)) {
			loadBoardButton.setEnabled(false);
		}
		fileList.setModel(new DefaultListModel());
		fileList.setCellRenderer(new MyListCellRenderer());
		//		transferFileList.setModel(new DefaultListModel());
		//		transferFileList.setCellRenderer(new MyListCellRenderer());
		//
		//		DefaultListModel model = (DefaultListModel) transferFileList.getModel();
		//		model.removeAllElements();

		boolean flag = PermissionUtil.hasPermission(PermissionUtil.FILE_UPLOAD);
		uploadFileButton.setEnabled(flag);
		boolean flag1 = PermissionUtil
				.hasPermission(PermissionUtil.DELETE_DOCUMENT);
		this.deleteButton.setEnabled(flag1);
		new MyDropTargetListener(fileList, DnDConstants.ACTION_COPY_OR_MOVE) {

			@Override
			public void drop(DropTargetDropEvent dtde) {
				dtde.acceptDrop(dtde.getDropAction());
				DataFlavor[] dataFlavors = dtde.getCurrentDataFlavors();
				if (dataFlavors[0].match(DataFlavor.javaFileListFlavor)) {
					try {
						Transferable tr = dtde.getTransferable();
						Object obj = tr
								.getTransferData(DataFlavor.javaFileListFlavor);
						List<File> files = (List<File>) obj;
						for (int i = 0; i < files.size(); i++) {

						}
						ControllerFacade.execute("shareFileListController",
								"uploadShareFileOnDrop", files);
					} catch (Exception e) {
						log.error("ShareFileListPanel.MyDropTargetListener", e);
					}
				}
			}
		};
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
		vlcPlayMenuItem = new javax.swing.JMenuItem();
		jPanel2 = new javax.swing.JPanel();
		jToolBar1 = new javax.swing.JToolBar();
		reflashButton = new javax.swing.JButton();
		uploadFileButton = new javax.swing.JButton();
		downloadButton = new javax.swing.JButton();
		deleteButton = new javax.swing.JButton();
		loadBoardButton = new javax.swing.JButton();
		jSplitPane1 = new javax.swing.JSplitPane();
		jScrollPane1 = new javax.swing.JScrollPane();
		fileList = new javax.swing.JList();
		tabbedPane = new javax.swing.JTabbedPane();
		transferFileListScrollPane = new javax.swing.JScrollPane();
		transferFileListPanel = new javax.swing.JPanel();

		downLoadMenuItem.setText("\u4e0b\u8f7d");
		downLoadMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				downLoadMenuItemActionPerformed(evt);
			}
		});
		fileListPopupMenu.add(downLoadMenuItem);

		loadToBoardMenuItem.setText(StringUtil
				.getUIString("ShareFileListPanel.loadToBoardMenuItem.text"));
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

		vlcPlayMenuItem.setText("\u64ad\u653e");
		vlcPlayMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				vlcPlayMenuItemActionPerformed(evt);
			}
		});
		fileListPopupMenu.add(vlcPlayMenuItem);

		addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent evt) {
				formComponentResized(evt);
			}
		});
		setLayout(new java.awt.BorderLayout());

		jPanel2.setLayout(new java.awt.BorderLayout());

		jToolBar1.setFloatable(false);

		reflashButton.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/lvmc/resource/images/reflash.png"))); // NOI18N
		reflashButton.setText("\u5237\u65b0");
		reflashButton.setToolTipText("\u5237\u65b0");
		reflashButton.setContentAreaFilled(false);
		reflashButton.setFocusable(false);
		reflashButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		reflashButton.setMaximumSize(new java.awt.Dimension(60, 47));
		reflashButton.setMinimumSize(new java.awt.Dimension(55, 47));
		reflashButton.setPreferredSize(new java.awt.Dimension(55, 47));
		reflashButton
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		reflashButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				reflashButtonActionPerformed(evt);
			}
		});
		jToolBar1.add(reflashButton);

		uploadFileButton.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource("/com/lorent/lvmc/resource/images/file-up.png"))); // NOI18N
		uploadFileButton.setText("\u4e0a\u4f20");
		uploadFileButton.setToolTipText("\u4e0a\u4f20");
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
		jToolBar1.add(uploadFileButton);

		downloadButton
				.setIcon(new javax.swing.ImageIcon(
						getClass()
								.getResource(
										"/com/lorent/lvmc/resource/images/files/go-down-10.png"))); // NOI18N
		downloadButton.setText("\u4e0b\u8f7d");
		downloadButton.setToolTipText(StringUtil
				.getUIString("ShareFileListPanel.downLoadButton.text"));
		downloadButton.setContentAreaFilled(false);
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
		jToolBar1.add(downloadButton);

		deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/lvmc/resource/images/files/delete1.png"))); // NOI18N
		deleteButton.setText("\u5220\u9664");
		deleteButton.setToolTipText("\u5220\u9664");
		deleteButton.setContentAreaFilled(false);
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
		jToolBar1.add(deleteButton);

		loadBoardButton
				.setIcon(new javax.swing.ImageIcon(
						getClass()
								.getResource(
										"/com/lorent/lvmc/resource/images/files/tab-duplicate-2.png"))); // NOI18N
		loadBoardButton.setText("\u8f7d\u5165\u767d\u677f");
		loadBoardButton.setToolTipText(StringUtil
				.getUIString("ShareFileListPanel.loadToBoardButton.text"));
		loadBoardButton.setContentAreaFilled(false);
		loadBoardButton.setFocusable(false);
		loadBoardButton
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		loadBoardButton.setMaximumSize(new java.awt.Dimension(60, 47));
		loadBoardButton.setPreferredSize(new java.awt.Dimension(49, 47));
		loadBoardButton
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		loadBoardButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				loadBoardButtonActionPerformed(evt);
			}
		});
		jToolBar1.add(loadBoardButton);

		jPanel2.add(jToolBar1, java.awt.BorderLayout.PAGE_START);

		jSplitPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		jSplitPane1.setDividerSize(3);
		jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

		fileList
				.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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

		jSplitPane1.setTopComponent(jScrollPane1);

		transferFileListPanel.setLayout(new javax.swing.BoxLayout(
				transferFileListPanel, javax.swing.BoxLayout.Y_AXIS));
		transferFileListScrollPane.setViewportView(transferFileListPanel);

		tabbedPane
				.addTab(
						"\u4f20\u8f93",
						new javax.swing.ImageIcon(
								getClass()
										.getResource(
												"/com/lorent/lvmc/resource/images/files/format-list-unordered.png")),
						transferFileListScrollPane); // NOI18N

		jSplitPane1.setBottomComponent(tabbedPane);

		jPanel2.add(jSplitPane1, java.awt.BorderLayout.CENTER);

		add(jPanel2, java.awt.BorderLayout.CENTER);
	}// </editor-fold>
	//GEN-END:initComponents

	private void vlcPlayMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
		Object selectedValue = fileList.getSelectedValue();
		if (selectedValue != null) {
			ShareFileListItem item = (ShareFileListItem) selectedValue;
			ControllerFacade.execute("vlcPlayerController","sendPlayCommand", item.getFileName());
		}
	}

	private void fileListValueChanged(javax.swing.event.ListSelectionEvent evt) {
		if (evt.getValueIsAdjusting()) {
			return;
		}
		log.debug("fileListValueChanged");
		Object selectedValue = fileList.getSelectedValue();
		if (selectedValue instanceof ShareFileListItem) {
			ShareFileListItem item = (ShareFileListItem) selectedValue;
			Boolean bflag = (Boolean) ControllerFacade.execute(
					"shareFileListController", "checkFileSupportConvert", item
							.getFileName());
			if (bflag) {
				loadBoardButton.setEnabled(true);
			} else {
				loadBoardButton.setEnabled(false);
			}
		} else {
			loadBoardButton.setEnabled(false);
		}
	}

	private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
		deleteFile();
	}

	private void formComponentResized(java.awt.event.ComponentEvent evt) {
		jSplitPane1.setDividerLocation(0.8);
	}

	private void fileListMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_fileListMouseClicked
		log.debug("fileListMouseClicked");
		int locationToIndex = fileList.locationToIndex(evt.getPoint());
		fileList.setSelectedIndex(locationToIndex);
		Object selectedValue = fileList.getSelectedValue();
		ShareFileListItem item = (ShareFileListItem) selectedValue;
		if (selectedValue != null) {
			if (evt.getButton() == MouseEvent.BUTTON3) {

				ControllerFacade.execute("shareFileListController",
						"showPopupMemu", item.getFileName(), evt);

				// fileListPopupMenu.show(evt.getComponent(),evt.getPoint().x,evt.getPoint().y);
			} else if (evt.getButton() == MouseEvent.BUTTON1
					&& evt.getClickCount() == 2) {
				ControllerFacade.execute("shareFileListController",
						"loadFileToBoardFileProcess", item.getFileName());
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
		}

	}// GEN-LAST:event_fileListMouseClicked

	private void loadBoardJButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Object selectedValue = fileList.getSelectedValue();
		ShareFileListItem item = (ShareFileListItem) selectedValue;
		if (selectedValue != null) {
			ControllerFacade.execute("shareFileListController",
					"loadFileToBoardFileProcess", item.getFileName());
		}
	}

	private void uploadFileButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_uploadFileButtonActionPerformed
		ControllerFacade.execute("shareFileListController", "uploadShareFile");
	}// GEN-LAST:event_uploadFileButtonActionPerformed

	private void reflashButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_reflashButtonActionPerformed
		ControllerFacade.execute("shareFileListController",
				"reflashButtonClick");
	}// GEN-LAST:event_reflashButtonActionPerformed

	private void downLoadMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
		downLoadFile();
	}

	private void loadToBoardMenuItemActionPerformed(
			java.awt.event.ActionEvent evt) {// GEN-FIRST:event_loadToBoardMenuItemActionPerformed
		loadToBoard();
	}// GEN-LAST:event_loadToBoardMenuItemActionPerformed

	private void downloadButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_downloadButtonActionPerformed
		downLoadFile();
	}// GEN-LAST:event_downloadButtonActionPerformed

	private void loadBoardButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_loadBoardButtonActionPerformed
		loadToBoard();
	}// GEN-LAST:event_loadBoardButtonActionPerformed

	private void deleteFileMenuItemActionPerformed(
			java.awt.event.ActionEvent evt) {// GEN-FIRST:event_deleteFileMenuItemActionPerformed
		deleteFile();
	}// GEN-LAST:event_deleteFileMenuItemActionPerformed

	private void downloadJButtonActionPerformed(java.awt.event.ActionEvent evt) {
		downLoadFile();
	}

	private void loadToBoard() {
		Object selectedValue = fileList.getSelectedValue();
		if (selectedValue != null) {
			ShareFileListItem item = (ShareFileListItem) selectedValue;
			ControllerFacade.execute("shareFileListController",
					"loadShareFileToBoard", item.getFileName());
		}
	}

	private void downLoadFile() {
		Object selectedValue = fileList.getSelectedValue();
		if (selectedValue != null) {
			ShareFileListItem item = (ShareFileListItem) selectedValue;
			ControllerFacade.execute("shareFileListController",
					"downLoadShareFile", item.getFileName());
		}
	}

	private void deleteFile() {
		Object selectedValue = fileList.getSelectedValue();
		if (selectedValue != null) {
			ShareFileListItem item = (ShareFileListItem) selectedValue;
			ControllerFacade.execute("shareFileListController",
					"deleteShareFile", item.getFileName());
		}
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton deleteButton;
	private javax.swing.JMenuItem deleteFileMenuItem;
	private javax.swing.JMenuItem downLoadMenuItem;
	private javax.swing.JButton downloadButton;
	private javax.swing.JList fileList;
	private javax.swing.JPopupMenu fileListPopupMenu;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JSplitPane jSplitPane1;
	private javax.swing.JToolBar jToolBar1;
	private javax.swing.JButton loadBoardButton;
	private javax.swing.JMenuItem loadToBoardMenuItem;
	private javax.swing.JButton reflashButton;
	private javax.swing.JTabbedPane tabbedPane;
	private javax.swing.JPanel transferFileListPanel;
	private javax.swing.JScrollPane transferFileListScrollPane;
	private javax.swing.JButton uploadFileButton;
	private javax.swing.JMenuItem vlcPlayMenuItem;

	// End of variables declaration//GEN-END:variables

	public void setVisibleOfDeleteButton(boolean f) {
		//		this.deleteButton.setVisible(f);
		ViewManager.setComponentByAuthority(this.deleteButton, f);
	}

	public void setVisibleOfUploadFileButton(boolean f) {
		//		this.uploadFileButton.setVisible(f);
		ViewManager.setComponentByAuthority(this.uploadFileButton, f);
	}

	public void setVisibleOfLoadBoard(boolean f) {
		//		this.loadBoardButton.setVisible(f);
		//		this.loadToBoardMenuItem.setVisible(f);
		ViewManager.setComponentByAuthority(this.loadBoardButton, f);
		ViewManager.setComponentByAuthority(this.loadToBoardMenuItem, f);
	}

	public void setVisibleOfPlayButton(boolean f){
		ViewManager.setComponentByAuthority(this.vlcPlayMenuItem, f);
	}
	
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

	public javax.swing.JMenuItem getVlcPlayMenuItem() {
		return vlcPlayMenuItem;
	}

	//	public javax.swing.JList getTransferFileList() {
	//		return transferFileList;
	//	}

}
