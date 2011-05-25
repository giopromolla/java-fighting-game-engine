package org.jfge.api.fighter;

import java.util.List;
import java.util.Map;

import org.jfge.api.effect.CollisionEffect;
import org.jfge.api.projectile.Projectile;
import org.jfge.api.render.SpriteRenderer;
import org.jfge.spi.graphics.Image;
import org.jfge.spi.physics.SpritePhysics;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

/**
 * The Class FighterFactoryImpl.
 */
@Singleton
public final class FighterFactoryImpl implements FighterFactory{

	/** The input queue provider. */
	private Provider<InputQueue> inputQueueProvider;
	
	/** The sprite renderer provider. */
	private Provider<SpriteRenderer> spriteRendererProvider;
	
	/** The ENGIN e_ width. */
	private final int ENGINE_WIDTH;
	
	/** The ENGIN e_ height. */
	private final int ENGINE_HEIGHT;
	
	/**
	 * Instantiates a new fighter factory impl.
	 *
	 * @param inputQueueProvider the input queue provider
	 * @param spriteRendererProvider the sprite renderer provider
	 * @param engineWidth the engine width
	 * @param engineHeight the engine height
	 */
	@Inject
	public FighterFactoryImpl(Provider<InputQueue> inputQueueProvider, Provider<SpriteRenderer> spriteRendererProvider, @Named("engine.width") int engineWidth, @Named("engine.height") int engineHeight) {
		this.inputQueueProvider = inputQueueProvider;
		this.spriteRendererProvider = spriteRendererProvider;
		this.ENGINE_WIDTH = engineWidth;
		this.ENGINE_HEIGHT = engineHeight;
	}
	
	/* (non-Javadoc)
	 * @see org.jfge.fighter.FighterFactory#createFighter(java.lang.String, java.util.List)
	 */
	@Override
	public Fighter createFighter(String name, Image portrait, List<FighterState> states, String startState, String endState, String victoryState) {
		return new FighterImpl(name, portrait, spriteRendererProvider.get(), inputQueueProvider.get(), states, startState, endState, ENGINE_WIDTH, ENGINE_HEIGHT, victoryState);
	}
	
	/**
	 * Creates the fighter state.
	 *
	 * @param name the state
	 * @param images the images
	 * @param ticks the ticks
	 * @param loop the loop
	 * @param nextState the next state
	 * @param transitions the transitions
	 * @param move the move
	 * @param collisionEffect the collision effect
	 * @param projectile the projectile
	 * @return the fighter state
	 */
	@Override
	public FighterState createFighterState(String name, int damage, List<Image> images, int ticks, 
			boolean loop, String nextState, boolean isFinalState, 
			Map<List<String>, String> transitions, SpritePhysics move, CollisionEffect collisionEffect, Projectile projectile) {
		FighterState fighterState = new FighterStateImpl(name, damage, images, ticks, loop, nextState, isFinalState, transitions, move, collisionEffect, projectile);
		
		return fighterState;
	}
}
