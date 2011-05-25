package org.jfge.api.collision;

import org.jfge.api.sprite.Sprite;
import org.jfge.spi.collision.CollisionDetectionStrategy;
import org.jfge.spi.graphics.GraphicsFactory;

import com.google.inject.Inject;

public final class RectangleCollisionStrategy implements CollisionDetectionStrategy {

	private GraphicsFactory graphicsFactory;
	
	@Inject
	public RectangleCollisionStrategy(GraphicsFactory graphicsFactory) {
		this.graphicsFactory = graphicsFactory;
	}
	
	@Override
	public boolean intersect(Sprite sprite, Sprite colSprite) {
		org.jfge.spi.graphics.Rectangle spriteRect = graphicsFactory.createRectangle(sprite.getDirection() == Sprite.LEFT ? sprite.getX() - sprite.getWidth(): sprite.getX(), sprite.getY()-sprite.getHeight(), 
					sprite.getWidth(), sprite.getHeight());
		
		org.jfge.spi.graphics.Rectangle colSpriteRect = graphicsFactory.createRectangle(colSprite.getDirection() == Sprite.LEFT ? colSprite.getX() - colSprite.getWidth(): colSprite.getX(), colSprite.getY()-colSprite.getHeight(), 
					colSprite.getWidth(), colSprite.getHeight());
		
		return spriteRect.rectIntersects(colSpriteRect);
	}

}
