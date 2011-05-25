package org.jfge.api.projectile;

import java.util.List;
import java.util.logging.Logger;

import org.jfge.api.render.SpriteRenderer;
import org.jfge.spi.graphics.Image;
import org.jfge.spi.physics.SpritePhysics;

import com.google.inject.Inject;

public class ProjectileFactoryImpl implements ProjectileFactory {

	private SpriteRenderer spriteRenderer;
	
	private final Logger logger;
	
	@Inject
	public ProjectileFactoryImpl(Logger logger, SpriteRenderer spriteRenderer) {
		this.spriteRenderer = spriteRenderer;
		this.logger = logger;
	}
	
	@Override
	public Projectile createProjectile(String name, List<ProjectileState> states, String startState) {
		return new ProjectileImpl(logger, name, spriteRenderer, states, startState);
	}

	@Override
	public ProjectileState createProjectileState(String name, List<Image> images, int ticks,
			boolean loop, String nextState, boolean isFinalState, SpritePhysics move, int damage) {
		return new ProjectileStateImpl(logger, name, images, ticks, loop, nextState, isFinalState, move, damage);
	}

}
