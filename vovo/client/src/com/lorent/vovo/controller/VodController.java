package com.lorent.vovo.controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		reflashVodList(frame, 0);
	}
	
	public void reflashVodList(final VodFrame frame,int index) throws Exception{
		JPanel vodListPane = frame.getVodListPanel();
		java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
		int ntemp = 6;
		int videoListLength = VovoVod.getLcmUtil().getVideoListLength();
		int maxPageCount = videoListLength / vodListColSize;
		frame.setMaxPageIndex(maxPageCount);
		frame.setPageIndex(index);
		LCMVideoClip[] videoClipList = VovoVod.getLcmUtil().getVideoClipList(index, vodListColSize);
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
				if (i < videoClipList.length) {
		            VodListItem vodListItem = new VodListItem();
		            ImagePainter imagePainter = null;
		            try {
						imagePainter = new ImagePainter(new URL(videoClipList[i].getThumbnailUrl()));
					} catch (Exception e) {
						imagePainter = new ImagePainter(ImageIO.read(getClass().getResource("/com/lorent/vovo/resource/images/video_no_pic.png")));//video_no_pic.png
						log.error("", e);
					}
		            
		            imagePainter.setScaleType(ScaleType.Distort);
		            vodListItem.getVideoPictureXPanel().setBackgroundPainter(imagePainter);
		            String title = videoClipList[i].getTitle();
		            if (title.length() > 30) {
						title = title.substring(0, 30)+"...";
					}
		            String description = videoClipList[i].getDescription();
		            if (description.length() > 37) {
		            	description = description.substring(0, 37) + "...";
					}
		            vodListItem.getVideoTitleLabel().setText(title);
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
		JPanel navigateNumberPanel = frame.getNavigateNumberPanel();
		navigateNumberPanel.removeAll();
		int maxPageIndex = frame.getMaxPageIndex();
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
						reflashVodList(frame, Integer.parseInt(jButton.getName()));
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
		frame.getVodListPanel().revalidate();
	}
	
}
