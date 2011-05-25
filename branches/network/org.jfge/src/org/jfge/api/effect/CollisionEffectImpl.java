package org.jfge.api.effect;

import java.io.IOException;
import java.util.List;

import org.jfge.api.fighter.Fighter;
import org.jfge.api.render.SpriteRenderer;
import org.jfge.api.sprite.AbstractSprite;
import org.jfge.api.sprite.Sprite;
import org.jfge.spi.graphics.Graphics;
import org.jfge.spi.graphics.Image;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public final class CollisionEffectImpl extends AbstractSprite implements CollisionEffect {

	private List<Image> images;
	
	private SpriteRenderer spriteRenderer;
	
	private int index = 0;
	
	private Fighter fighter;
	
	private double relX;
	
	private double relY;
	
	@Inject
	public CollisionEffectImpl(	SpriteRenderer spriteRenderer,  
			@Assisted List<Image> images,
			@Assisted("relX") double relX,
			@Assisted("relY") double relY) throws IOException {
		this.spriteRenderer = spriteRenderer;
		this.images = images;
		this.relX = relX;
		this.relY = relY;
	}
	
	@Override
	public void update() {
		if(fighter == null)
			return;
		
		/*
		 * updating current position and direction
		 * everytime blood is added for processing and displaying
		 */
		if(index == 0) {
			if(fighter.getDirection() == Sprite.RIGHT) {
				this.setX((int) (fighter.getX() + fighter.getWidth() * relX));
			} else {
				this.setX((int) (fighter.getX() - fighter.getWidth() * relX));
			}
			
			this.setY((int) (fighter.getY()-fighter.getHeight() * relY));
			this.setDirection(fighter.getDirection());
		}
		
		/*
		 * check if we've reached the end of our image list.
		 * If this is the case, then we should do some cleaning up
		 * and prevent this blood effect from processing and displaying
		 */
		if(index++ >= images.size()) {
			index = 0;	// cleaning up
			fighter.removeCollisionEffect(this); // removing this blood effect from processing	
		}
	}

	@Override
	public void render(Graphics graphics) {
		if(index >= images.size())
			return;
		
		if(this.direction == Sprite.LEFT) {
			spriteRenderer.drawSprite(graphics, images.get(index), this.getX(), this.getY(), SpriteRenderer.CENTER);
		} else if(this.direction == Sprite.RIGHT) {
			spriteRenderer.drawSprite(graphics, images.get(index).flip(), this.getX(), this.getY(), SpriteRenderer.CENTER);
		}
	}

	@Override
	public String getName() {
		return "Blood";
	}

	@Override
	public synchronized void setParent(Fighter fighter) {
		this.fighter = fighter;
	}
}
