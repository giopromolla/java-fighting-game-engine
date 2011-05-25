package org.jfge.spi.graphics;

import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * The Interface ImageLoader.
 */
public interface GraphicsFactory {
	
	/**
	 * Load image.
	 *
	 * @param file the file
	 * @return the image
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Image createImage(String file) throws IOException;
	
	/**
	 * Creates a new Graphics object.
	 *
	 * @param color the color
	 * @return the color
	 */
	public Color createColor(int color);
	
	/**
	 * Creates a new Graphics object.
	 *
	 * @param color the color
	 * @return the color
	 */
	public Color createColor(String color);
	
	/**
	 * Creates a new Graphics object.
	 *
	 * @param family the family
	 * @param style the style
	 * @param pointsize the pointsize
	 * @return the font
	 */
	public Font createFont(String family, int style, int pointsize);
	
	/**
	 * Creates a new Graphics object.
	 *
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 * @return the rectangle
	 */
	public Rectangle createRectangle(int x, int y, int width, int height);
}
