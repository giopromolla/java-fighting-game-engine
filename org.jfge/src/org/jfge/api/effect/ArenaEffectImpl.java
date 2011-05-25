package org.jfge.api.effect;

import java.util.List;

import org.jfge.api.arena.Arena;
import org.jfge.api.render.SpriteRenderer;
import org.jfge.api.sprite.AbstractSprite;
import org.jfge.spi.graphics.Graphics;
import org.jfge.spi.graphics.Image;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class ArenaEffectImpl extends AbstractSprite implements ArenaEffect {

	private Arena arena;
	
	private List<Image> images;
	
	private SpriteRenderer spriteRenderer;
	
	private int index = 0;
	
	@Inject
	public ArenaEffectImpl(SpriteRenderer spriteRenderer, 
			@Assisted List<Image> images,
			@Assisted("arenaFactory.x") int x,
			@Assisted("arenaFactory.y") int y) {
		this.spriteRenderer = spriteRenderer;
		this.images = images;
		this.setX(x);
		this.setY(y);
	}
	
	@Override
	public void update() {
		if(index++ >= images.size()) {
			index = 0;	// cleaning up
			
			if(arena != null) {
				arena.removeArenaEffet(this);
			}
		}
	}

	@Override
	public void render(Graphics graphics) {
		if(index >= images.size())
			return;
		
		spriteRenderer.drawSprite(graphics, images.get(index), this.getX(), this.getY(), SpriteRenderer.CENTER);	
	}

	@Override
	public String getName() {
		return "Fight";
	}

	@Override
	public void setParent(Arena arena) {
		if(arena == null)
			return;
		
		this.arena = arena;
	}

}
