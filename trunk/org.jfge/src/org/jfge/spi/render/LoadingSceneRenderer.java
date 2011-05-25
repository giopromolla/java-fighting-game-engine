package org.jfge.spi.render;

import org.jfge.api.render.SpriteRenderer;
import org.jfge.spi.graphics.Graphics;
import org.jfge.spi.graphics.Image;

/**
 * The Interface LoadingScreenRenderer.
 */
public interface LoadingSceneRenderer extends SpriteRenderer {
	/**
	 * Draw background.
	 *
	 * @param backGround the back ground
	 */
	public void drawBackground(Graphics graphics, Image backGround, int x, int y);
}
