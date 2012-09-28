package org.jhotdraw.samples.svg.figures;

import javax.swing.SwingUtilities;

import org.jhotdraw.draw.DefaultDrawingView;

public class Zoom extends AbstractNormalResultUpdater {

	private static final long serialVersionUID = 1L;
	private double scaleFactor;

	public Zoom(double scaleFactor) {
		this.scaleFactor = scaleFactor;
	}

	@Override
	public void change(final DefaultDrawingView view) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				view.setScaleFactor(scaleFactor);
			}
		});
	}

}
