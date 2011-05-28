package org.jfge.ext.physics;

import org.jfge.api.projectile.Projectile;
import org.jfge.api.sprite.Sprite;
import org.jfge.spi.physics.SpritePhysics;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class Flying implements SpritePhysics<Projectile> {

	/** The sprite. */
	private Projectile parent;
	
	private final int FLYING;
	
	private int direction;
	
	@Inject
	public Flying(@Named("physics.projectile.flying") int flying) {
		this.FLYING = flying;
	}
	
	/* (non-Javadoc)
	 * @see org.jfge.physics.SpritePhysics#setSprite(org.jfge.sprite.Sprite)
	 */
	@Override
	public void setParent(Projectile sprite) {
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
			parent.setDx(FLYING);
		} else if (direction == Sprite.LEFT) {
			parent.setDx(-FLYING);
		}
	}

	@Override
	public void init() {
		if(this.parent == null)
			return;
		
		this.direction = parent.getDirection();
	}
}
