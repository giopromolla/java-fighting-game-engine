package org.jfge.ext.physics;

import org.jfge.api.fighter.Fighter;
import org.jfge.spi.physics.SpritePhysics;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * The Class Jump.
 */
public final class Jump implements SpritePhysics<Fighter> {

	/** The FLOOR. */
	private final int FLOOR = 240;
	
	/** The Constant JUMP_DY. */
	private final int VERTICAL;
	
	/** The sprite. */
	private Fighter parent;
	
	private int direction;
	
	/**
	 * Instantiates a new jump.
	 *
	 * @param vertical the vertical
	 */
	@Inject
	public Jump(@Named("physics.fighter.jump.vertical") int vertical) {
		this.VERTICAL = vertical;
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
		
		if(parent.getDy() > 0) {
			parent.setDy(VERTICAL);
		} else {
			parent.setDy(-VERTICAL);
		}
		
		if(parent.getY() - parent.getHeight() < 0) {
			parent.setDy(VERTICAL);
		}
		
		if(parent.getY() > FLOOR) {
			parent.setDx(0);
			parent.setDy(0);
			parent.setY(FLOOR);
			parent.nextState();
		}
	}

	@Override
	public void init() {
		if(this.parent == null)
			return;
		
		this.direction = parent.getDirection();
	}

}
