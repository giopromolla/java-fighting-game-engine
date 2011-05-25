package org.jfge.spi.collision;

import org.jfge.api.sprite.Sprite;

/**
 * The Interface CollisionStrategy.
 */
public interface CollisionDetectionStrategy {
	
	/**
	 * Intersect.
	 *
	 * @param sprite the sprite
	 * @param colSprite the col sprite
	 * @return true, if successful
	 */
	public boolean intersect(Sprite sprite, Sprite colSprite);
}
