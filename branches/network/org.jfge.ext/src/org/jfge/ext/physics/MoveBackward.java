package org.jfge.ext.physics;

import org.jfge.api.fighter.Fighter;
import org.jfge.api.sprite.Sprite;
import org.jfge.spi.physics.SpritePhysics;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public final class MoveBackward implements SpritePhysics<Fighter> {

	/** The sprite. */
	private Fighter fighter;
	
	private final int WALK;
	
	@Inject
	public MoveBackward(@Named("physics.fighter.walk") int walk) {
		this.WALK = walk;
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
			fighter.setDx(-WALK);
		} else if (fighter.getDirection() == Sprite.LEFT) {
			fighter.setDx(WALK);
		}
	}
}
