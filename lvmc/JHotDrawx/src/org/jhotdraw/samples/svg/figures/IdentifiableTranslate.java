package org.jhotdraw.samples.svg.figures;

import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;

import org.jhotdraw.draw.Figure;

public class IdentifiableTranslate extends IdentifiableTransform{
	private static final long serialVersionUID = 1L;

	private AffineTransform tx;

	public IdentifiableTranslate(long id, AffineTransform tx) {
		super(id);
		this.tx = tx;
	}

	@Override
	public void change(Figure figure) {
		figure.willChange();
		figure.basicTransform(tx);
		figure.changed();
	}

	@Override
	public void reverse(Figure figure) {
		try {
			tx.invert();
		} catch (NoninvertibleTransformException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public IdentifiableTransform clone(){
		IdentifiableTranslate clone = (IdentifiableTranslate)super.clone();
		clone.tx=(AffineTransform) clone.tx.clone();
		return clone;
	}
	
}
