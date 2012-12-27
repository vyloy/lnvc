package com.lorent.lvmc.ui;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.UIManager;


public class PopupMenuLabel extends JLabel {

	public PopupMenuLabel(boolean isAddDefaultMouseListener){
		super();
		if(isAddDefaultMouseListener){
			this.addMouseListener(new PopupMenuLabelMouseAdapter());
		}else{
			this.setBorder(javax.swing.BorderFactory.createEtchedBorder());
			this.addMouseListener(new PopupMenuLabelMouseAdapter1());
		}
	}
	
	public PopupMenuLabel(ImageIcon icon,int d){
		this(icon,d,true);
	}
	
	
	public void setIconCenter(){
		setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        setVerticalAlignment(javax.swing.SwingConstants.CENTER);
	}
	
	public PopupMenuLabel(ImageIcon icon,int d,boolean isAddDefaultMouseListener){
		super(icon,d);
		if(icon!=null){
			setPreferredSize(new Dimension(icon.getIconWidth()+ 5,icon.getIconHeight()+5));
		}
		if(isAddDefaultMouseListener){
			this.addMouseListener(new PopupMenuLabelMouseAdapter());
		}else{
			this.setBorder(javax.swing.BorderFactory.createEtchedBorder());
			this.addMouseListener(new PopupMenuLabelMouseAdapter1());
		}
	} 
	
	public void setOpaqueAndReflesh(boolean flag){
		setOpaque(flag);
		revalidate();
		repaint();
	}
	
	private class PopupMenuLabelMouseAdapter extends MouseAdapter{
		
		
		@Override
		public void mouseEntered(MouseEvent e) {
			PopupMenuLabel.this.setBackground(UIManager.getColor("List.selectionBackground"));
			PopupMenuLabel.this.setOpaqueAndReflesh(true);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			PopupMenuLabel.this.setOpaqueAndReflesh(false);
		}
	}
	
	private class PopupMenuLabelMouseAdapter1 extends MouseAdapter{
		@Override
		public void mouseEntered(MouseEvent e) {
			PopupMenuLabel.this.setBorder(new javax.swing.border.LineBorder(UIManager.getColor("List.selectionBackground"), 3, true));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			PopupMenuLabel.this.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		}	
	}
}
