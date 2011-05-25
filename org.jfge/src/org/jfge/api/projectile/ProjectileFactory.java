package org.jfge.api.projectile;

import java.util.List;

import org.jfge.spi.graphics.Image;
import org.jfge.spi.physics.SpritePhysics;

/**
 * A factory for creating Particle objects.
 */
public interface ProjectileFactory {
	
	/**
	 * Creates a new Particle object.
	 *
	 * @param name the name
	 * @param ticks the ticks
	 * @param loop the loop
	 * @param nextState the next state
	 * @param isFinalState TODO
	 * @param move the move
	 * @param damage TODO
	 * @return the particle state
	 */
	public ProjectileState createProjectileState(String name, List<Image> images, int ticks, boolean loop, String nextState, boolean isFinalState, SpritePhysics move, int damage);
	
	/**
	 * Creates a new Particle object.
	 *
	 * @param name the name
	 * @param states the states
	 * @return the particle
	 */
	public Projectile createProjectile(String name, List<ProjectileState> states, String startState);
}
