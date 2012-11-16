package com.lorent.vovo.controller;

import java.awt.Dimension;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jdesktop.swingx.painter.ImagePainter;
import org.jdesktop.swingx.painter.ImagePainter.ScaleType;

import com.lorent.common.controller.BaseController;
import com.lorent.common.dto.LCMVideoClip;
import com.lorent.vovo.VovoVod;
import com.lorent.vovo.ui.VodFrame;
import com.lorent.vovo.ui.VodListItem;
import com.lorent.vovo.util.Constants;

public class VodController extends BaseController {
	
	private int pageSize = 12;
	
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
	
	public void reflashVodList(VodFrame frame,int index) throws Exception{
		JPanel vodListPane = frame.getVodListPanel();
		java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
		int ntemp = 6;
		int videoListLength = VovoVod.getLcmUtil().getVideoListLength();
		int maxPageCount = videoListLength / pageSize;
		frame.setMaxPageIndex(maxPageCount);
		LCMVideoClip[] videoClipList = VovoVod.getLcmUtil().getVideoClipList(index, pageSize);
		vodListPane.removeAll();
		if (videoClipList != null) {
			
			for (int i = 0; i < pageSize; i++) {
				gridBagConstraints.gridx = i % ntemp;
	            gridBagConstraints.gridy = i / ntemp;
	            gridBagConstraints.gridwidth = 1;
	            gridBagConstraints.gridheight = 1;
	            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
	            gridBagConstraints.weightx = 1;
	            gridBagConstraints.weighty = 1;
				if (i < videoClipList.length) {
		            VodListItem vodListItem = new VodListItem();
		            ImagePainter imagePainter = new ImagePainter(new URL(videoClipList[i].getThumbnailUrl()));
		            imagePainter.setScaleType(ScaleType.Distort);
		            vodListItem.getVideoPictureXPanel().setBackgroundPainter(imagePainter);
		            vodListItem.getVideoTitleLabel().setText(videoClipList[i].getTitle());
		            vodListItem.getVideoTitleLabel().setToolTipText(videoClipList[i].getTitle());
		            vodListItem.getVideoDescriptionLabel().setText(videoClipList[i].getDescription());
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
					vodListPane.add(new JPanel(),gridBagConstraints);
				}
			}
			
			
		}
	}
	
	public void lastVodListPage(final VodFrame frame,final int index) throws Exception{
		
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					reflashVodList(frame, index);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void nextVodListPage(final VodFrame frame,final int index) throws Exception{
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					reflashVodList(frame, index);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
