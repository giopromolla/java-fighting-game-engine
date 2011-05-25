package org.jfge.j2se.graphics;

import java.awt.Rectangle;

/**
 * The Class J2SeRectangle.
 */
public class J2SeRectangle extends Rectangle implements
		org.jfge.spi.graphics.Rectangle {

	/**
	 * Instantiates a new j2 se rectangle.
	 *
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 */
	public J2SeRectangle(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	private J2SeRectangle(Rectangle r) {
		super(r);
	}
	
	/* (non-Javadoc)
	 * @see org.jfge.spi.graphics.Rectangle#rectIntersection(org.jfge.spi.graphics.Rectangle)
	 */
	@Override
	public org.jfge.spi.graphics.Rectangle rectIntersection(
			org.jfge.spi.graphics.Rectangle r) {
		if(r == null)
			return null;
		
		if(!(r instanceof J2SeRectangle))
			return null;
		
		return new J2SeRectangle(this.intersection((Rectangle) r));
	}

	/* (non-Javadoc)
	 * @see org.jfge.spi.graphics.Rectangle#rectIntersects(org.jfge.spi.graphics.Rectangle)
	 */
	@Override
	public boolean rectIntersects(org.jfge.spi.graphics.Rectangle r) {
		if(r == null)
			return false;
		
		if(!(r instanceof J2SeRectangle))
			return false;
		
		return this.intersects((Rectangle) r);
	}

	/* (non-Javadoc)
	 * @see org.jfge.spi.graphics.Rectangle#getRectHeight()
	 */
	@Override
	public int getRectHeight() {
		return (int) this.getHeight();
	}

	/* (non-Javadoc)
	 * @see org.jfge.spi.graphics.Rectangle#getRectWidth()
	 */
	@Override
	public int getRectWidth() {
		return (int) this.getWidth();
	}

	/* (non-Javadoc)
	 * @see org.jfge.spi.graphics.Rectangle#getRectX()
	 */
	@Override
	public int getRectX() {
		return (int) this.getX();
	}

	/* (non-Javadoc)
	 * @see org.jfge.spi.graphics.Rectangle#getRectY()
	 */
	@Override
	public int getRectY() {
		return (int) this.getY();
	}

}
