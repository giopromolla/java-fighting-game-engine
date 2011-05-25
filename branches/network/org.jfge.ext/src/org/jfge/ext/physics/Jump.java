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
	private Fighter fighter;
	
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
		this.fighter = sprite;
	}

	/* (non-Javadoc)
	 * @see org.jfge.engine.Updatable#update()
	 */
	@Override
	public void update() {
		if(fighter == null)
			return;
		
		if(fighter.getDy() > 0) {
			fighter.setDy(VERTICAL);
		} else {
			fighter.setDy(-VERTICAL);
		}
		
		if(fighter.getY() - fighter.getHeight() < 0) {
			fighter.setDy(VERTICAL);
		}
		
		if(fighter.getY() > FLOOR) {
			fighter.setDx(0);
			fighter.setDy(0);
			fighter.setY(FLOOR);
			fighter.nextState();
		}
	}

}
