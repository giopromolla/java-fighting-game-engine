package org.jfge.ext.physics;

import org.jfge.api.fighter.Fighter;
import org.jfge.api.sprite.Sprite;
import org.jfge.spi.physics.SpritePhysics;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public final class MoveBackward implements SpritePhysics<Fighter> {

	/** The sprite. */
	private Fighter parent;
	
	private final int WALK;
	
	private int direction;
	
	@Inject
	public MoveBackward(@Named("physics.fighter.walk") int walk) {
		this.WALK = walk;
	}
	/* (non-Javadoc)
	 * @see org.jfge.physics.SpritePhysics#setSprite(org.jfge.sprite.Sprite)
	 */
	@Override
	public void setParent(Fighter sprite) {
		this.parent = sprite;
	}

	/* (non-Javadoc)
	 * @see org.jfge.engine.Updatable#update()
	 */
	@Override
	public void update() {
		if(parent == null)
			return;
	
		if(direction == Sprite.RIGHT) {
			parent.setDx(-WALK);
		} else if (direction == Sprite.LEFT) {
			parent.setDx(WALK);
		}
	}
	@Override
	public void init() {
		if(this.parent == null)
			return;
		
		this.direction = parent.getDirection();
	}
}
