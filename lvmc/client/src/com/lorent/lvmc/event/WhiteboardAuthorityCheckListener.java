package com.lorent.lvmc.event;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import org.jhotdraw.samples.svg.SVGPanels;

import com.jtattoo.plaf.WhiteboardPermission;

public class WhiteboardAuthorityCheckListener extends AuthorityCheckAdapter {

	@Override
	public void enable() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SVGPanels.getInstance().setWritable(true);
				JButton b = WhiteboardPermission.getAddButton();
				b.setVisible(true);
			}
		});
	}

	@Override
	public void disable() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SVGPanels.getInstance().setWritable(false);
				JButton b = WhiteboardPermission.getAddButton();
				b.setVisible(false);
			}
		});
	}

}
