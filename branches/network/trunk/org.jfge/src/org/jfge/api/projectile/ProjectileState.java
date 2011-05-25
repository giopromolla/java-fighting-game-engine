package org.jfge.api.projectile;

import org.jfge.api.engine.Updatable;
import org.jfge.api.fsm.State;

public interface ProjectileState extends State<Projectile>, Updatable {
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName();
	
	/**
	 * Gets the damage.
	 *
	 * @return the damage
	 */
	public int getDamage();
}
