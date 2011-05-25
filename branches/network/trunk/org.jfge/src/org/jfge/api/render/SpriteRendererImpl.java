package org.jfge.api.render;

import org.jfge.api.sprite.Sprite;
import org.jfge.spi.graphics.Graphics;
import org.jfge.spi.graphics.Image;

import com.google.inject.Singleton;

/**
 * The Class SpriteRendererImpl.
 */
@Singleton
public class SpriteRendererImpl implements SpriteRenderer {
	/* (non-Javadoc)
	 * @see org.jfge.render.SpriteRenderer#drawSprite(java.awt.Graphics, java.awt.Image, int, int)
	 */
	@Override
	public void drawSprite(Graphics g, Image image, int x, int y, int direction) {
		
		if(direction == Sprite.RIGHT) {
			 g.drawImage(x, y - image.getHeight(), image);
		} else if(direction == Sprite.LEFT){
			g.drawImage(x - image.getWidth(), y - image.getHeight(), image);
		} else if(direction == CENTER) {
			g.drawImage(x - image.getWidth()/2, y - image.getHeight(), image);
		}
	}

}
