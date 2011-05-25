package org.jfge.api.fighter;

import java.util.List;
import java.util.Map;

import org.jfge.api.effect.CollisionEffect;
import org.jfge.api.projectile.Projectile;
import org.jfge.spi.graphics.Image;
import org.jfge.spi.physics.SpritePhysics;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Fighter objects.
 */
public interface FighterFactory {
	
	
	/**
	 * Creates a new Fighter object.
	 *
	 * @param name the name
	 * @param states the states
	 * @param victoryState TODO
	 * @return the fighter
	 */
	public Fighter createFighter(String name, Image portrait, List<FighterState> states, String startState, String endState, String victoryState);

	/**
	 * Creates a new Fighter object.
	 *
	 * @param state the state
	 * @param images the images
	 * @param ticks the ticks
	 * @param loop the loop
	 * @param nextState the next state
	 * @param isFinalState TODO
	 * @param transitions the transitions
	 * @param move the move
	 * @param collisionEffect the collision effect
	 * @return the fighter state
	 */
	FighterState createFighterState(String name, int damage, List<Image> images,
			int ticks, boolean loop, String nextState,
			boolean isFinalState, Map<List<String>, String> transitions,
			SpritePhysics move, CollisionEffect collisionEffect, Projectile Projectile);
}
