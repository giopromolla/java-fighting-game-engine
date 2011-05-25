package org.jfge.api.projectile;

import org.jfge.api.fsm.StateMachine;
import org.jfge.api.sprite.Sprite;

/**
 * The Interface Projectile.
 */
public interface Projectile extends Sprite, StateMachine<ProjectileState>{
	
	/**
	 * Creates the.
	 *
	 * @return the projectile
	 */
	public Projectile create();
}
