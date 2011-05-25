package org.jfge.spi.graphics;

// TODO: Auto-generated Javadoc
/**
 * The Interface Graphics.
 */
public interface Graphics {
	
	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth();
	
	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getHeight();
	
	/**
	 * Sets the color.
	 *
	 * @param color the new color
	 */
	public void setGraphicsColor(Color color);
	
	/**
	 * Sets the font.
	 *
	 * @param font the new font
	 */
	public void setGraphicsFont(Font font);
	
	/**
	 * Draw rectangle.
	 *
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 */
	public void drawRectangle(int x, int y, int width, int height);
	
	/**
	 * Draw fill rectangle.
	 *
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 */
	public void drawFillRectangle(int x, int y, int width, int height);
	
	/**
	 * Draw string.
	 *
	 * @param x the x
	 * @param y the y
	 * @param text the text
	 */
	public void drawString(int x, int y, String text);
	
	/**
	 * Draw image.
	 *
	 * @param x the x
	 * @param y the y
	 * @param image the image
	 */
	public void drawImage(int x, int y, Image image);
}
