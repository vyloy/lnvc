package com.lorent.vovo.controller;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.painter.ImagePainter;
import org.jdesktop.swingx.painter.ImagePainter.ScaleType;

import sun.util.logging.resources.logging;

import com.lorent.common.controller.BaseController;
import com.lorent.common.dto.LCMVideoClip;
import com.lorent.vovo.VovoVod;
import com.lorent.vovo.ui.VodFrame;
import com.lorent.vovo.ui.VodListItem;
import com.lorent.vovo.ui.VodListPanel;
import com.lorent.vovo.util.Constants;

public class VodController extends BaseController {
	
	private Logger log = Logger.getLogger(VodController.class);
	private int vodListColSize = 12;
	private int pageTabsSize = 12;
	
	public void showVodFrame() throws Exception{
		
		VodFrame frame = VovoVod.getMyContext().getViewManager().getView(Constants.ViewKey.VODFRAME.toString());
		if (frame == null) {
			frame = VovoVod.getMyContext().getViewManager().createView(VodFrame.class, Constants.ViewKey.VODFRAME.toString());
		}
//		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
//		frame.setSize(screenSize);
//		frame.setLocation(0, 0);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		String[] videoCategory = Constants.VIDEO_CATEGORY;
		for (String category : videoCategory) {
			VodListPanel vodListPanel = new VodListPanel();
			vodListPanel.setCategory(category);
			frame.getVodListCardPanel().add(vodListPanel,category);
			reflashVodList(vodListPanel, 0);
		}
//		frame.getVodListCardPanel().add(comp)
//		reflashVodList(frame, 0);
	}

	
	public void reflashVodList(final VodListPanel vodListPanel,int index) throws Exception{
		JPanel vodListPane = vodListPanel.getListPanel();
		java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
		int ntemp = 6;
		int videoListLength = VovoVod.getLcmUtil().getVideoListLength(vodListPanel.getCategory());
		int maxPageCount = videoListLength / vodListColSize;
		vodListPanel.setMaxPageIndex(maxPageCount);
		vodListPanel.setPageIndex(index);
		final LCMVideoClip[] videoClipList = VovoVod.getLcmUtil().getVideoClipList(index, vodListColSize,vodListPanel.getCategory());
		vodListPane.removeAll();
		if (videoClipList != null) {
			
			for (int i = 0; i < vodListColSize; i++) {
				gridBagConstraints.gridx = i % ntemp;
	            gridBagConstraints.gridy = i / ntemp;
	            gridBagConstraints.gridwidth = 1;
	            gridBagConstraints.gridheight = 1;
	            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
	            gridBagConstraints.weightx = 1;
	            gridBagConstraints.weighty = 1;
	            final int nindex = i;
				if (i < videoClipList.length) {
		            final VodListItem vodListItem = new VodListItem();
		            
		            new Thread(){

						@Override
						public void run() {
							ImagePainter imagePainter = null;
				            try {
				            	Image image = Toolkit.getDefaultToolkit().getImage(videoClipList[nindex].getThumbnailUrl());
								imagePainter = new ImagePainter(new URL(videoClipList[nindex].getThumbnailUrl()));
							} catch (Exception e) {
								try {
									imagePainter = new ImagePainter(ImageIO.read(getClass().getResource("/com/lorent/vovo/resource/images/video_no_pic.png")));
								} catch (IOException e1) {
									e1.printStackTrace();
									log.error("reflashVodList", e1);
								}
								log.error("reflashVodList", e);
							}
				            
				            imagePainter.setScaleType(ScaleType.Distort);
				            vodListItem.getVideoPictureXPanel().setBackgroundPainter(imagePainter);
						}
		            	
		            }.start();
		            
		            String title = videoClipList[i].getTitle();
		            if (title.length() > 17) {
						title = title.substring(0, 17)+"...";
					}
		            String description = videoClipList[i].getDescription();
		            if (description.length() > 24) {
		            	description = description.substring(0, 24) + "...";
					}
		            vodListItem.getVideoTitleLabel().setText(title);
		            if (videoClipList[i].getDuration() == null) {
		            	vodListItem.getDurationLabel().setText(" ");
					}
		            else{
		            	vodListItem.getDurationLabel().setText(videoClipList[i].getDuration());
		            }
		            
		            ImagePainter imagePainter2 = new ImagePainter(ImageIO.read(getClass().getResource("/com/lorent/vovo/resource/images/tran_1.png")));
		            imagePainter2.setScaleType(ScaleType.Distort);
		            vodListItem.getDurationXPanel().setBackgroundPainter(imagePainter2);
		            vodListItem.getVideoTitleLabel().setToolTipText(videoClipList[i].getTitle());
		            vodListItem.getVideoDescriptionLabel().setText(description);
		            vodListItem.getVideoDescriptionLabel().setToolTipText(videoClipList[i].getDescription());
		            vodListPane.add(vodListItem, gridBagConstraints);
		            vodListItem.setLcmVideoClip(videoClipList[i]);
				}
				else{
//					VodListItem vodListItem = new VodListItem();
//		            ImagePainter imagePainter = new ImagePainter(new URL(videoClipList[i].getThumbnailUrl()));
//		            imagePainter.setScaleType(ScaleType.Distort);
//		            vodListItem.getVideoPictureXPanel().setBackgroundPainter(imagePainter);
//		            vodListPane.add(vodListItem, gridBagConstraints);
					JPanel jPanel = new JPanel();
					jPanel.setOpaque(false);
					vodListPane.add(jPanel,gridBagConstraints);
				}
			}
		}
		JPanel navigateNumberPanel = vodListPanel.getNavigateNumberPanel();
		navigateNumberPanel.removeAll();
		int maxPageIndex = vodListPanel.getMaxPageIndex();
		int beginindex =  index - pageTabsSize/2;
		int endindex =  index + pageTabsSize/2;
		if (beginindex < 0) {
			beginindex = 0;
		}
		if (endindex > maxPageIndex) {
			endindex = maxPageIndex;
		}
		if (endindex - beginindex < pageTabsSize && (beginindex != 0 || endindex != maxPageIndex)) {
			if (beginindex != 0) {
				beginindex = maxPageIndex - pageTabsSize;
			}
			if (endindex != maxPageIndex) {
				endindex = pageTabsSize;
			}
		}
		for (int i = beginindex; i <= endindex; i++) {
			final JButton jButton = new JButton((i+1)+"");
			jButton.setFont(new java.awt.Font("微软雅黑", 0, 12));
			jButton.setName(i+"");
			jButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						reflashVodList(vodListPanel, Integer.parseInt(jButton.getName()));
					} catch (NumberFormatException e1) {
						log.error("reflashVodList", e1);
						e1.printStackTrace();
					} catch (Exception e2) {
						log.error("reflashVodList", e2);
						e2.printStackTrace();
					}
				}
			});
			navigateNumberPanel.add(jButton);
			if (i == index) {
				jButton.setEnabled(false);
				jButton.setFont(new java.awt.Font("微软雅黑", 1, 18));
			}
		}
		log.info("nextVodListPage: "+index);
//		frame.getVodListPanel().repaint();
		vodListPanel.getListPanel().revalidate();
	}
	
}
