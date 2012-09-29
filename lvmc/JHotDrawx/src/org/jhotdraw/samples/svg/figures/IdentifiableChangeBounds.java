package org.jhotdraw.samples.svg.figures;

import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import org.jhotdraw.draw.Figure;

public class IdentifiableChangeBounds extends IdentifiableTransform{
	private static final long serialVersionUID = 1L;

	private Rectangle2D.Double bounds;
	private transient Rectangle2D.Double oldBounds;

	public IdentifiableChangeBounds(long id, Rectangle2D.Double bounds) {
		super(id);
		this.bounds = bounds;
	}

	@Override
	public void change(Figure f) {
		f.willChange();
		Rectangle2D.Double oldBounds = f.getBounds();
		this.oldBounds=oldBounds;
		double sx = bounds.width / oldBounds.width;
		double sy = bounds.height / oldBounds.height;

		AffineTransform tx = new AffineTransform();
		tx.translate(-oldBounds.x, -oldBounds.y);
		if (!Double.isNaN(sx) && !Double.isNaN(sy) && (sx != 1d || sy != 1d)
				&& !(sx < 0.0001) && !(sy < 0.0001)) {
			f.basicTransform(tx);
			tx.setToIdentity();
			tx.scale(sx, sy);
			f.basicTransform(tx);
			tx.setToIdentity();
		}
		tx.translate(bounds.x, bounds.y);
		f.basicTransform(tx);
		f.changed();
	}

	@Override
	public void reverse(Figure figure) {
		bounds=oldBounds;
	}

	@Override
	public IdentifiableTransform clone(){
		IdentifiableChangeBounds clone = (IdentifiableChangeBounds)super.clone();
		clone.bounds= (Rectangle2D.Double) clone.bounds.clone();
		clone.oldBounds= (Rectangle2D.Double) clone.oldBounds.clone();
		return clone;
	}
	
}
