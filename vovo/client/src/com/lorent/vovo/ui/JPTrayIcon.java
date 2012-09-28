package com.lorent.vovo.ui;

import java.awt.Frame;
import java.awt.Image;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import com.lorent.vovo.dto.ControllerEvent;

public class JPTrayIcon extends TrayIcon {

	private transient JPopupMenu menu;
	private static final JDialog DIALOG = new JDialog((Frame) null);
	private transient final PopupMenuListener popupListener;
	private transient final MouseListener mouseListener;


	static {
		DIALOG.setUndecorated(true);
		DIALOG.setAlwaysOnTop(true);
	}

	/**
	 * 
	 * @param image
	 */
	public JPTrayIcon(Image image) {
		super(image);
		popupListener = new TrayPopupListener();
		mouseListener = new TrayMouseAdapter();
	}
	
	public void changeIcon(Image image){
		this.setImage(image);
	}

	/**
	 * 
	 * @return
	 */
	public final JPopupMenu getJPopupMenu() {
		return menu;
	}

	/**
	 * 
	 * @param menu
	 */
	public final void setJPopupMenu(final JPopupMenu menu) {
		if (this.menu != null) {
			this.menu.removePopupMenuListener(popupListener);
			removeMouseListener(mouseListener);
		}
		if (menu != null) {
			this.menu = menu;
			this.menu.addPopupMenuListener(popupListener);
			addMouseListener(mouseListener);
		}
	}

	/**
	 * @return the menu
	 */
	private final class TrayMouseAdapter extends MouseAdapter {

		/**
		 * 
		 * @param evt
		 */
		private void showJPopupMenu(final MouseEvent evt) {
			if (evt.isPopupTrigger() && menu != null) {
				DIALOG.setLocation(evt.getX(), evt.getY()
						- menu.getPreferredSize().height);
				DIALOG.setVisible(true);
				menu.show(DIALOG.getContentPane(), 0, 0);
				// popup works only for focused windows
				DIALOG.toFront();
			}
		}

		@Override
		public void mousePressed(final MouseEvent evt) {
			showJPopupMenu(evt);
		}

		@Override
		public void mouseReleased(final MouseEvent evt) {
			showJPopupMenu(evt);
		}

		@Override
		public void mouseClicked(final MouseEvent evt) {
			showJPopupMenu(evt);
			myMouseClicked(evt);
		}
		
	}
	
	protected void myMouseClicked(final MouseEvent evt) {
		
	}
	
	

	private static final class TrayPopupListener implements PopupMenuListener {

		@Override
		public void popupMenuWillBecomeVisible(final PopupMenuEvent evt) {
			// not used
		}

		@Override
		public void popupMenuWillBecomeInvisible(final PopupMenuEvent evt) {
			DIALOG.setVisible(false);
		}

		@Override
		public void popupMenuCanceled(final PopupMenuEvent evt) {
			DIALOG.setVisible(false);
		}
	}
}
