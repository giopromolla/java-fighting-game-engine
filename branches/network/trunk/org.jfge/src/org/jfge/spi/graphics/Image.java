package org.jfge.spi.graphics;

/**
 * The Interface Image.
 */
public interface Image {
	
	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getHeight();
	
	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth();
	
	/**
	 * Flip.
	 *
	 * @return the image
	 */
	public Image flip();

	/**
	 * Rotate.
	 *
	 * @param degree the degree
	 * @return the image
	 */
	public Image rotate(int degree);
}
