package org.jfge.api.render;

import org.jfge.spi.graphics.Graphics;
import org.jfge.spi.graphics.Image;

/**
 * The Interface SpriteRenderer.
 */
public interface SpriteRenderer {
	
	/** The LEFT. */
	public final int LEFT = 1;
	
	/** The RIGHT. */
	public final int RIGHT = 2;
	
	/** The CENTER. */
	public final int CENTER = 3;
	
	/**
	 * Render sprite.
	 *
	 * @param graphics the graphics
	 * @param image the image
	 * @param x the x
	 * @param y the y
	 * @param dir the dir
	 */
	public void drawSprite(Graphics graphics, Image image, int x, int y, int dir);
}
