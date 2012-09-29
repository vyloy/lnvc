/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VideosPanel.java
 *
 * Created on 2011-12-14, 14:42:15
 */
package com.lorent.lvmc.ui;

import com.lorent.common.dnd.MyDropTargetListener;
import com.lorent.lvmc.controller.ControllerFacade;
import com.lorent.lvmc.util.DataUtil;
import com.lorent.lvmc.util.StringUtil;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.event.ItemEvent;
import java.util.*;
import java.util.logging.Level;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import net.infonode.docking.DockingWindow;
import net.infonode.docking.DockingWindowAdapter;
import net.infonode.docking.OperationAbortedException;
import net.infonode.docking.View;
import net.infonode.docking.util.ViewMap;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class VideoViewsPanel extends javax.swing.JPanel {

	private static Logger log = Logger.getLogger(VideoViewsPanel.class);
	private String panelTitle = StringUtil.getUIString("VideoViewsPanel.title");
	private String individualPanelTitle = StringUtil
			.getUIString("IndividualVideo.title");
	private String[] layoutNames = null;
	public final static String defaultLayoutName = "1xN";

	//    private List<VideoViewsPanelItem> videoViewPanelItemList = new ArrayList<VideoViewsPanelItem>();
	//    
	//    public List<VideoViewsPanelItem> getVideoViewPanelItemList(){
	//        return videoViewPanelItemList;
	//    }
	//    
	//    public void addVideoViewPanelItemToList(VideoViewsPanelItem item){
	//        videoViewPanelItemList.add(item);
	//    }
	//    
	//    public void clearVideoViewPanelItemList(){
	//        videoViewPanelItemList.clear();
	//    }

	/** Creates new form VideosPanel */
	public VideoViewsPanel() {
		initComponents();
		//        final String defaultLayoutName = "1xN";
		layoutNames = new String[] { defaultLayoutName, "1+5", "1+7", "1+9",
				"1+N", "1X1", "2X2", "3X3", "4X4", "5X5", "6X6" };//,"1XN", "2XN", "3XN"
		//		layoutChoice.removeAllItems();
		//		for (String name : layoutNames) {
		//			layoutChoice.addItem(name);
		//		}
		//        layoutChoice.setVisible(false);
		layoutComboBox.setVisible(false);
		reconnectButton.setVisible(false);
		Thread thread = new Thread() {

			@Override
			public void run() {
				try {
					Thread.sleep(4000);
				} catch (InterruptedException ex) {
					log.error("VideoViewsPanel()", ex);
				}
				layoutComboBox.setSelectedItem(defaultLayoutName);
				String selectedText = layoutComboBox.getSelectedItem()
						.toString();
				//                ControllerFacade.execute("videoViewsController", "changeLayout", defaultLayoutName);

//				ControllerFacade.execute("videoViewsController", "initVideo");
			}

		};
		thread.start();
		final JPanel instance = videosPanel;
		new MyDropTargetListener(instance) {

			@Override
			public void dragEnter(DropTargetDragEvent dtde) {
				super.dragEnter(dtde);
				//                setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 204), 3, true));
				log.debug("dragEnter");
			}

			@Override
			public void dragExit(DropTargetEvent dte) {
				super.dragExit(dte);
				//                setBorder(javax.swing.BorderFactory.createEtchedBorder());
				log.debug("dragExit");
			}

			@Override
			public void onDrop(DropTargetDropEvent dtde, Component source,
					Map<String, Object> properties) {
				super.onDrop(dtde, source, properties);
				//                setBorder(javax.swing.BorderFactory.createEtchedBorder());
				ControllerFacade
						.execute("videoViewsController", "videosPanelOnDrop",
								dtde, source, properties, instance);
			}

		};
		topPanel.setVisible(false);
	}

	public ArrayList<VideoViewsPanelItem> getVideoViewsPanels(String username) {
		ArrayList<VideoViewsPanelItem> resultList = new ArrayList<VideoViewsPanelItem>();
		int componentCount = videosPanel.getComponentCount();
		for (int i = 0; i < componentCount; i++) {
			Component component = videosPanel.getComponent(i);
			if (component instanceof VideoViewsPanelItem) {
				VideoViewsPanelItem panelItem = (VideoViewsPanelItem) component;
				if (panelItem.getLccUserName().equals(username)) {
					resultList.add(panelItem);
				}
			}else if(component instanceof JPanel && component.getName() != null && component.getName().equals("VideoGrid")){
				JPanel panel = (JPanel)component;
				ArrayList<VideoViewsPanelItem> videoItemPanels = getVideoItemPanels(panel);
				for (VideoViewsPanelItem videoViewsPanelItem : videoItemPanels) {
					if (videoViewsPanelItem.getLccUserName().equals(username)) {
						resultList.add(videoViewsPanelItem);
					}
				}
			}
		}
		return resultList;
	}

	public ArrayList<VideoViewsPanelItem> getSortedVideoItemPanels() {
		ArrayList<VideoViewsPanelItem> resultList = new ArrayList<VideoViewsPanelItem>();
		int componentCount = videosPanel.getComponentCount();
		for (int i = 0; i < componentCount; i++) {
			Component component = videosPanel.getComponent(i);
			if (component instanceof VideoViewsPanelItem) {
				VideoViewsPanelItem panelItem = (VideoViewsPanelItem) component;
				if (panelItem.getLccUserName() != null
						&& !panelItem.getLccUserName().equals("")) {
					resultList.add(0, panelItem);
				} else {
					resultList.add(panelItem);
				}
			}else if(component instanceof JPanel && component.getName() != null && component.getName().equals("VideoGrid")){
				JPanel panel = (JPanel)component;
				ArrayList<VideoViewsPanelItem> videoItemPanels = getVideoItemPanels(panel);
				for (VideoViewsPanelItem videoViewsPanelItem : videoItemPanels) {
					if (videoViewsPanelItem.getLccUserName() != null && !videoViewsPanelItem.getLccUserName().equals("")) {
						resultList.add(0,videoViewsPanelItem);
					}else{
						resultList.add(videoViewsPanelItem);
					}
				}
			}
		}
		return resultList;
	}

	public ArrayList<VideoViewsPanelItem> getSortedAndExistVideoItemPanels() {
		ArrayList<VideoViewsPanelItem> resultList = new ArrayList<VideoViewsPanelItem>();
		int componentCount = videosPanel.getComponentCount();
		for (int i = 0; i < componentCount; i++) {
			Component component = videosPanel.getComponent(i);
			if (component instanceof VideoViewsPanelItem) {
				VideoViewsPanelItem panelItem = (VideoViewsPanelItem) component;
				if (panelItem.getLccUserName() != null
						&& !panelItem.getLccUserName().equals("")) {
					//                    resultList.add(0,panelItem);
					resultList.add(panelItem);
				}
			}else if(component instanceof JPanel && component.getName() != null && component.getName().equals("VideoGrid")){
				JPanel panel = (JPanel)component;
				ArrayList<VideoViewsPanelItem> videoItemPanels = getVideoItemPanels(panel);
				for (VideoViewsPanelItem videoViewsPanelItem : videoItemPanels) {
					if (videoViewsPanelItem.getLccUserName() != null && !videoViewsPanelItem.getLccUserName().equals("")) {
						resultList.add(videoViewsPanelItem);
					}
				}
			}
		}
		return resultList;
	}

	public int getVideoViewsPanelItemIndex(VideoViewsPanelItem item) {
		int componentCount = videosPanel.getComponentCount();
		int j = 0;
		for (int i = 0; i < componentCount; i++,j++) {
			Component component = videosPanel.getComponent(i);
			if (component instanceof VideoViewsPanelItem
					&& component.equals(item)) {
				return j;
			}
			else if(component instanceof JPanel && component.getName() != null && component.getName().equals("VideoGrid")){
				JPanel panel = (JPanel)component;
				ArrayList<VideoViewsPanelItem> videoItemPanels = getVideoItemPanels(panel);
				
				for (int k = 0; k < videoItemPanels.size(); k++,j++) {
					VideoViewsPanelItem videoViewsPanelItem = videoItemPanels.get(k);
					if (videoViewsPanelItem.equals(item)) {
						return j;
					}
				}
			}
		}
		return -1;
	}

	public int getVideoViewsPanelItemIndex(String lccUserName) {
		int componentCount = videosPanel.getComponentCount();
		int j = 0;
		for (int i = 0; i < componentCount; i++,j++) {
			Component component = videosPanel.getComponent(i);
			if (component instanceof VideoViewsPanelItem) {
				VideoViewsPanelItem panelItem = (VideoViewsPanelItem) component;
				if (panelItem.getLccUserName().equals(lccUserName)) {
					return i;
//					return panelItem.getVideoIndex();
				}
			}
			else if(component instanceof JPanel && component.getName() != null && component.getName().equals("VideoGrid")){
				JPanel panel = (JPanel)component;
				ArrayList<VideoViewsPanelItem> videoItemPanels = getVideoItemPanels(panel);
				for (int k = 0; k < videoItemPanels.size(); k++,j++) {
					VideoViewsPanelItem videoViewsPanelItem = videoItemPanels.get(k);
					if (videoViewsPanelItem.getLccUserName().equals(lccUserName)) {
						return j;
					}
				}
			}
		}
		return -1;
	}

	public ArrayList<VideoViewsPanelItem> getVideoItemPanels() {
		ArrayList<VideoViewsPanelItem> resultList = new ArrayList<VideoViewsPanelItem>();
		int componentCount = videosPanel.getComponentCount();
		for (int i = 0; i < componentCount; i++) {
			Component component = videosPanel.getComponent(i);
			if (component instanceof VideoViewsPanelItem) {
				VideoViewsPanelItem panelItem = (VideoViewsPanelItem) component;
				resultList.add(panelItem);
			}
			else if(component instanceof JPanel && component.getName() != null && component.getName().equals("VideoGrid")){
				JPanel panel = (JPanel)component;
				ArrayList<VideoViewsPanelItem> videoItemPanels = getVideoItemPanels(panel);
				for (VideoViewsPanelItem videoViewsPanelItem : videoItemPanels) {
					resultList.add(videoViewsPanelItem);
				}
			}
		}
		return resultList;
	}

	private ArrayList<VideoViewsPanelItem> getVideoItemPanels(JPanel panel) {
		ArrayList<VideoViewsPanelItem> resultList = new ArrayList<VideoViewsPanelItem>();
		int componentCount = panel.getComponentCount();
		for (int i = 0; i < componentCount; i++) {
			Component component = panel.getComponent(i);
			if (component instanceof VideoViewsPanelItem) {
				VideoViewsPanelItem panelItem = (VideoViewsPanelItem) component;
				resultList.add(panelItem);
			}
		}
		return resultList;
	}
	
	public int getVideoItemCount() {
		return getVideoItemPanels().size();
	}

	public void addVideoItemPanel(VideoViewsPanelItem panel) {

		videosPanel.add(panel);
		//        panelsMap.put(panel.getLccUserName(), panel);
		revalidate();
		repaint();

	}

	public void removeVideoItemPanel(VideoViewsPanelItem panel) {
		//        panelsMap.put(panel.getLccUserName(), null);
		//        panelsMap.remove(panel.getLccUserName());
		videosPanel.remove(panel);

		//        panelsMap.remove(panel.getLccUserName());
		revalidate();
		repaint();
	}

	public DockingWindowAdapter getDockingWindowAdapter() {
		return new DockingWindowAdapter() {

			@Override
			public void windowMaximized(DockingWindow dw) {
				log.debug("windowMaximized , title:" + dw.getTitle()
						+ " panelTitle:" + panelTitle);
				if (dw.getTitle().indexOf(panelTitle) != -1) {
					String selectedText = (String) ControllerFacade.execute(
							"videoViewsController", "getChangedLayoutName");//layoutComboBox.getSelectedItem().toString();
					log.debug("windowMaximized selectedText:" + selectedText);
					ControllerFacade.execute("videoViewsController",
							"changeLayoutEx", selectedText, false);
				}
			}

			@Override
			public void windowShown(DockingWindow dw) {
				log.debug("windowShown , title:" + dw.getTitle()
						+ " panelTitle:" + panelTitle);
				//dw.getTitle().equals(panelTitle)
				if (dw.getTitle().indexOf(panelTitle) != -1
						&& !dw.getTitle().equals(individualPanelTitle)) {
					//                    final String selectedText = layoutComboBox.getSelectedItem().toString();
					final String selectedText = (String) ControllerFacade
							.execute("videoViewsController",
									"getChangedLayoutName");
					log.info("windowShown selectedText:" + selectedText);
					log.debug("DockingWindow:" + dw + ",title:" + dw.getTitle()
							+ ",name:" + dw.getName());
					//                    ControllerFacade.execute("videoViewsController", "changeLayout", selectedText);
					ControllerFacade.execute("videoViewsController",
							"changeLayoutEx", selectedText, false);
					//                    Thread thread = new Thread() {
					//
					//                        @Override
					//                        public void run() {
					//                            try {
					//                                Thread.sleep(1500);
					//                            } catch (InterruptedException ex) {
					//                                log.error(ex);
					//                            }
					//
					//                        
					//                        }
					//                    };
					//                    thread.start();
				}
			}

			@Override
			public void windowRestored(DockingWindow dw) {
				log.debug("windowRestored , title:" + dw.getTitle()
						+ " panelTitle:" + panelTitle);
				if (dw.getTitle().indexOf(panelTitle) != -1) {
					String selectedText = (String) ControllerFacade.execute(
							"videoViewsController", "getChangedLayoutName");//layoutComboBox.getSelectedItem().toString();
					log.debug("windowRestored selectedText:" + selectedText);
					ControllerFacade.execute("videoViewsController",
							"changeLayoutEx", selectedText, false);
				}
			}

			@Override
			public void windowAdded(DockingWindow dw, DockingWindow dw1) {
				log.debug("windowAdded");
			}

			@Override
			public void windowClosed(DockingWindow dw) {
				log.debug("windowClosed");
			}

			@Override
			public void windowClosing(DockingWindow dw)
					throws OperationAbortedException {
				log.debug("windowClosing");
			}

			@Override
			public void windowDocked(DockingWindow dw) {
				log.debug("windowDocked");
			}

			@Override
			public void windowDocking(DockingWindow dw)
					throws OperationAbortedException {
				log.debug("windowDocking");
			}

			@Override
			public void windowHidden(DockingWindow dw) {
				log.debug("windowHidden");
			}

			@Override
			public void windowMaximizing(DockingWindow dw)
					throws OperationAbortedException {
				log.debug("windowMaximizing");
			}

			@Override
			public void windowMinimized(DockingWindow dw) {
				log.debug("windowMinimized");
			}

			@Override
			public void windowMinimizing(DockingWindow dw)
					throws OperationAbortedException {
				log.debug("windowMinimizing");
			}

			@Override
			public void windowRemoved(DockingWindow dw, DockingWindow dw1) {
				log.debug("windowRemoved");
			}

			@Override
			public void windowRestoring(DockingWindow dw)
					throws OperationAbortedException {
				log.debug("windowRestoring");
			}

			@Override
			public void windowUndocked(DockingWindow dw) {
				log.debug("windowUndocked");
			}

			@Override
			public void windowUndocking(DockingWindow dw)
					throws OperationAbortedException {
				log.debug("windowUndocking");
			}

		};
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		changeLayoutButtonGroup = new javax.swing.ButtonGroup();
		topPanel = new javax.swing.JPanel();
		textPanel = new javax.swing.JPanel();
		buttonPanel = new javax.swing.JPanel();
		layoutComboBox = new javax.swing.JComboBox();
		layoutNameLabel = new javax.swing.JLabel();
		layoutChoice = new javax.swing.JComboBox();
		videosPanel = new javax.swing.JPanel();
		jPanel1 = new javax.swing.JPanel();
		iconLabel = new javax.swing.JLabel();
		stateLabel = new javax.swing.JLabel();
		reconnectButton = new javax.swing.JButton();

		addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentShown(java.awt.event.ComponentEvent evt) {
				formComponentShown(evt);
			}
		});
		addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				formKeyPressed(evt);
			}
		});
		setLayout(new java.awt.BorderLayout());

		topPanel.setLayout(new java.awt.GridBagLayout());

		textPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT,
				10, 5));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		topPanel.add(textPanel, gridBagConstraints);

		buttonPanel
				.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

		layoutComboBox.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "1+5", "1+7", "1+9", "1+N", "1XN", "2XN", "3XN",
						"1X1", "2X2", "3X3", "4X4", "5X5", "6X6", "flow" }));
		layoutComboBox.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				layoutComboBoxItemStateChanged(evt);
			}
		});
		buttonPanel.add(layoutComboBox);
		buttonPanel.add(layoutNameLabel);

		layoutChoice.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "1xN", "1+5", "1+7", "1+9", "1+N", "1X1", "2X2",
						"3X3", "4X4", "5X5", "6X6" }));
		layoutChoice.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				layoutChoiceItemStateChanged(evt);
			}
		});
		buttonPanel.add(layoutChoice);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		topPanel.add(buttonPanel, gridBagConstraints);

		add(topPanel, java.awt.BorderLayout.NORTH);

		videosPanel.setAutoscrolls(true);
		videosPanel.setLayout(new java.awt.GridLayout(0, 1));
		add(videosPanel, java.awt.BorderLayout.CENTER);

		jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

		iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/com/lorent/lvmc/resource/images/network-offline.png"))); // NOI18N
		jPanel1.add(iconLabel);

		stateLabel.setText("jLabel1");
		jPanel1.add(stateLabel);

		reconnectButton.setText("\u91cd\u65b0\u8fde\u63a5");
		reconnectButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
		reconnectButton.setPreferredSize(new java.awt.Dimension(60, 23));
		reconnectButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				reconnectButtonActionPerformed(evt);
			}
		});
		jPanel1.add(reconnectButton);

		add(jPanel1, java.awt.BorderLayout.SOUTH);
	}// </editor-fold>
	//GEN-END:initComponents

	private void layoutChoiceItemStateChanged(java.awt.event.ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED) {
			log.info("视频窗口选择了布局：" + evt.getItem().toString());
			String selectString = evt.getItem().toString();
			ControllerFacade.execute("videoViewsController", "changeLayoutExt",
					selectString);
			DataUtil.setValue(DataUtil.Key.videoLayout, selectString);
		}
	}

	private void reconnectButtonActionPerformed(java.awt.event.ActionEvent evt) {
		ControllerFacade.execute("phoneController", "tryConnnectToCS", true);
	}

	private void layoutComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_layoutComboBoxItemStateChanged
		//videosPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));
		if (evt.getStateChange() == ItemEvent.SELECTED) {
			log.info("视频窗口选择了布局：" + evt.getItem().toString());
			String selectString = evt.getItem().toString();
			ControllerFacade.execute("videoViewsController", "changeLayout",
					selectString);
		}

	}//GEN-LAST:event_layoutComboBoxItemStateChanged

	private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
		log.info("keypressed:" + evt.getKeyChar());
	}//GEN-LAST:event_formKeyPressed

	private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
		log.info("formComponentShown");
	}//GEN-LAST:event_formComponentShown

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JPanel buttonPanel;
	private javax.swing.ButtonGroup changeLayoutButtonGroup;
	private javax.swing.JLabel iconLabel;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JComboBox layoutChoice;
	private javax.swing.JComboBox layoutComboBox;
	private javax.swing.JLabel layoutNameLabel;
	private javax.swing.JButton reconnectButton;
	private javax.swing.JLabel stateLabel;
	private javax.swing.JPanel textPanel;
	private javax.swing.JPanel topPanel;
	private javax.swing.JPanel videosPanel;

	// End of variables declaration//GEN-END:variables

	public javax.swing.JLabel getStateLabel() {
		return stateLabel;
	}

	public JPanel getVideosPanel() {
		return videosPanel;
	}

	public JLabel getIconLabel() {
		return iconLabel;
	}

	public javax.swing.JComboBox getLayoutChoice() {
		return layoutChoice;
	}

	public javax.swing.JButton getReconnectButton() {
		return reconnectButton;
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				JFrame jFrame = new JFrame();
				jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				jFrame.setSize(400, 400);
				VideoViewsPanel videoViewsPanel = new VideoViewsPanel();
				for (int i = 0; i < 40; i++) {
					VideoViewsPanelItem videoViewsPanelItem = new VideoViewsPanelItem();

					videoViewsPanel.addVideoItemPanel(videoViewsPanelItem);
					videoViewsPanelItem
							.getVideoPanel()
							.setImg(
									Toolkit
											.getDefaultToolkit()
											.createImage(
													getClass()
															.getResource(
																	"/com/lorent/lvmc/resource/images/screen.png")));
				}
				jFrame.add(videoViewsPanel);
				jFrame.setVisible(true);
			}
		});
	}
}
