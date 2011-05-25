package org.jfge.spi.graphics;

// TODO: Auto-generated Javadoc
/**
 * The Interface Rectangle.
 */
public interface Rectangle {
	
	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getRectWidth();
	
	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getRectHeight();
	
	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getRectX();
	
	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getRectY();
	
	/**
	 * Intersects.
	 *
	 * @param r the r
	 * @return true, if successful
	 */
	public boolean rectIntersects(Rectangle r);
	
	/**
	 * Intersection.
	 *
	 * @param r the r
	 * @return the rectangle
	 */
	public Rectangle rectIntersection(Rectangle r);
}
