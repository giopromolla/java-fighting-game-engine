package org.jfge.api.fighter;

import java.util.List;

import org.jfge.api.effect.CollisionEffect;
import org.jfge.api.engine.Updatable;
import org.jfge.api.fsm.State;
import org.jfge.api.projectile.Projectile;

// TODO: Auto-generated Javadoc
/**
 * The Interface FighterState.
 */
public interface FighterState extends State<Fighter>, Updatable {
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName();
	
	/**
	 * Handle.
	 *
	 * @param move the move
	 * @return true, if successful
	 */
	public boolean handle(List<String> move);
	
	/**
	 * Gets the projectile.
	 *
	 * @return the projectile
	 */
	public Projectile getProjectile();
	
	/**
	 * Gets the collision effect.
	 *
	 * @return the collision effect
	 */
	public CollisionEffect getCollisionEffect();
	
	/**
	 * Gets the damage.
	 *
	 * @return the damage
	 */
	public int getDamage();
}
