package com.jtattoo.plaf.vovoglass;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicListUI;

public class VoVoBasicListUI extends BasicListUI {

	private static int locationToIndex = -1;
	private static BufferedImage selectedImage = null;
	private static BufferedImage selectedBackImage = null;
	
	static{
		try {
			selectedImage = ImageIO.read(VoVoTabbedPaneUI.class.getResource(
			"/com/jtattoo/resource/image/whitealpha.png"));
			selectedBackImage = ImageIO.read(VoVoTabbedPaneUI.class.getResource(
			"/com/jtattoo/resource/image/whitealpha2.png"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	protected void paintCell(Graphics g, int row, Rectangle rowBounds,
			ListCellRenderer cellRenderer, ListModel dataModel,
			ListSelectionModel selModel, int leadIndex) {
//		System.out.println("row: "+row+" leadIndex:"+leadIndex);

		if (row == leadIndex) {
			g.drawImage(selectedBackImage, rowBounds.x, rowBounds.y,rowBounds.width, rowBounds.height, null);
		}
		else{
			if (row == locationToIndex && row != -1 && locationToIndex != -1) {
				
				g.drawImage(selectedImage, rowBounds.x, rowBounds.y, rowBounds.width, rowBounds.height, null);
			}
		}
//		System.out.println(locationToIndex+","+row);
		
		
		super.paintCell(g, row, rowBounds, cellRenderer, dataModel, selModel,leadIndex);
	}

	@Override
	public int locationToIndex(JList list, Point location) {
		locationToIndex = super.locationToIndex(list, location);
//		System.out.println(location+","+locationToIndex);
		return locationToIndex;
	}

	
}
