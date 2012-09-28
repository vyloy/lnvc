package org.jhotdraw.samples.svg.figures;

import java.io.Serializable;

import org.jhotdraw.draw.Figure;

import com.lorent.whiteboard.model.Identifiable;

public abstract class IdentifiableTransform implements Serializable,Cloneable,Identifiable{
	private static final long serialVersionUID = 1L;
	protected long id;

	public IdentifiableTransform(long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdentifiableChangeBounds other = (IdentifiableChangeBounds) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

	@Override
	public long getIdentifying() {
		return id;
	}

	@Override
	public void setIdentifying(long id) {
		this.id=id;
	}

	@Override
	public IdentifiableTransform clone(){
		try {
			return (IdentifiableTransform) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
	}

	public abstract void change(Figure figure);
	public abstract void reverse(Figure figure);

}