package org.jfge.ext.physics;

import org.jfge.api.projectile.Projectile;
import org.jfge.api.sprite.Sprite;
import org.jfge.spi.physics.SpritePhysics;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class Flying implements SpritePhysics<Projectile> {

	/** The sprite. */
	private Projectile projectile;
	
	private final int FLYING;
	
	@Inject
	public Flying(@Named("physics.projectile.flying") int flying) {
		this.FLYING = flying;
	}
	
	/* (non-Javadoc)
	 * @see org.jfge.physics.SpritePhysics#setSprite(org.jfge.sprite.Sprite)
	 */
	@Override
	public void setParent(Projectile sprite) {
		this.projectile = sprite;
	}

	/* (non-Javadoc)
	 * @see org.jfge.engine.Updatable#update()
	 */
	@Override
	public void update() {
		if(projectile == null)
			return;
	
		if(projectile.getDirection() == Sprite.RIGHT) {
			projectile.setDx(FLYING);
		} else if (projectile.getDirection() == Sprite.LEFT) {
			projectile.setDx(-FLYING);
		}
	}
}
