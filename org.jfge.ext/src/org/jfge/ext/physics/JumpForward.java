package org.jfge.ext.physics;

import org.jfge.api.fighter.Fighter;
import org.jfge.api.sprite.Sprite;
import org.jfge.spi.physics.SpritePhysics;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * The Class JumpForward.
 */
public final class JumpForward implements SpritePhysics<Fighter> {

	/** The FLOOR. */
	private final int FLOOR = 240;
	
	/** The sprite. */
	private Fighter fighter;

	/** The HORIZONTAL. */
	private final int HORIZONTAL;
	
	/** The VERTICAL. */
	private final int VERTICAL;
	
	/**
	 * Instantiates a new jump forward.
	 *
	 * @param horizontal the horizontal
	 * @param vertical the vertical
	 */
	@Inject
	public JumpForward(@Named("physics.fighter.jump.horizontal") int horizontal,
			@Named("physics.fighter.jump.vertical") int vertical) {
		this.HORIZONTAL = horizontal;
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
		
		if(fighter.getDirection() == Sprite.RIGHT) {
			fighter.setDx(HORIZONTAL);
		} else {
			fighter.setDx(-HORIZONTAL);
		}
		
		if(fighter.getDy() > 0) {
			fighter.setDy(VERTICAL);
		} else {
			fighter.setDy(-VERTICAL);
		}
		
		if(fighter.getY() - fighter.getHeight() < 0) {
			fighter.setDy(VERTICAL);
		}
		
		if(fighter.getY() + fighter.getDy()> FLOOR) {
			fighter.setDx(0);
			fighter.setDy(0);
			fighter.setY(FLOOR);
			fighter.nextState();
		}
	}
}