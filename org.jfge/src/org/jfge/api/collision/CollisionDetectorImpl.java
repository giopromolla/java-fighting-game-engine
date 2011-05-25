package org.jfge.api.collision;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.jfge.api.fighter.Fighter;
import org.jfge.api.projectile.Projectile;
import org.jfge.api.sprite.Sprite;
import org.jfge.spi.collision.CollisionDetectionStrategy;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

/**
 * The Class CollisionDetectorImpl.
 */
@Singleton
public final class CollisionDetectorImpl implements CollisionDetector {
	
	/** The logger. */
	public final Logger logger;
	
	/** The sprites. */
	private List<Fighter> fighters;

	/** The width. */
	@Inject
	@Named("engine.width")
	private int width;
	
	/** The collision handlers. */
	private Set<CollisionHandler> collisionHandlers;
	
	/** The collision strategies. */
	private Set<CollisionDetectionStrategy> collisionStrategies;
	
	/**
	 * Instantiates a new collision detector impl.
	 *
	 * @param logger the logger
	 * @param collisionStrategies the collision strategies
	 * @param collisionHandlers the collision handlers
	 */
	@Inject
	public CollisionDetectorImpl(Logger logger, Set<CollisionDetectionStrategy> collisionStrategies, Set<CollisionHandler> collisionHandlers) {
		this.logger = logger;
		this.fighters = new ArrayList<Fighter>();
		this.collisionStrategies = collisionStrategies;
		this.collisionHandlers = collisionHandlers;
	}

	/* (non-Javadoc)
	 * @see org.jfge.collision.CollisionDectector#addSprite(org.jfge.sprite.Sprite)
	 */
	@Override
	public void  addFighter(Fighter fighter) {
		this.fighters.add(fighter);
	}

	/* (non-Javadoc)
	 * @see org.jfge.collision.CollisionDectector#removeSprite(org.jfge.sprite.Sprite)
	 */
	@Override
	public void removeFighter(Fighter fighter) {
		this.fighters.remove(fighter);
	}

	/**
	 * Arena bounds detection.
	 */
	private void arenaBoundsDetection() {
		for(Sprite sprite : fighters) {
			/*
			 * arena width must be checked individually 
			 * for each sprite direction
			 */
			if(sprite.getDirection() == Sprite.RIGHT) {
				if(sprite.getX() < 0) {
					sprite.setX(0);
				}
				
				if(sprite.getX() + sprite.getWidth() > width) {
					sprite.setX(width - sprite.getWidth());
				}
			} else if(sprite.getDirection() == Sprite.LEFT) {
				if(sprite.getX() - sprite.getWidth() < 0) {
					sprite.setX(sprite.getWidth());
				}
				
				if(sprite.getX() > width) {
					sprite.setX(width);
				}
			}
		}
	}
	
	/**
	 * Fighter collision detection.
	 */
	private void fighterCollisionDetection() {
		boolean isCollision = true;
		
		for(int i=0; i<fighters.size(); i++) {
			for(int j=i+1; j<fighters.size(); j++) {
				/*
				 * check for collision, using all availabable collision strategies.
				 * if not all collision strategies fit, the collision detection falses.
				 */
				for(CollisionDetectionStrategy collisionStrategy: this.collisionStrategies) {
					if(collisionStrategy.intersect(fighters.get(i), fighters.get(j)) == false) {
						isCollision = false;
						break;
					}
				}
				
				if(isCollision) {
					// see if we can find some collision handling
					for(CollisionHandler handler: this.collisionHandlers) {
						if(handler.handleCollision(fighters.get(i), fighters.get(j))) {
							fighters.get(j).setHealth(fighters.get(j).getHealth() - fighters.get(i).getState().getDamage());
							fighters.get(i).setHealth(fighters.get(i).getHealth() - fighters.get(j).getState().getDamage());
							break; //found collision handling
						}
					}
				}
			}
		}
	}
	
	/**
	 * Projectile collision detection.
	 */
	private void projectileCollisionDetection() {
		for(int i=0; i<fighters.size(); i++) {
			for(int j=i+1; j<fighters.size(); j++) {
				
				// check if projectiles of fighter(i) are colliding with fighter(j)
				for(Projectile projectile: fighters.get(i).getProjectiles()) {
					
					//check for collision, using all availabable collision strategies
					for(CollisionDetectionStrategy collisionStrategy: this.collisionStrategies) {
						if(collisionStrategy.intersect(projectile, fighters.get(j)) != false) {
							for(CollisionHandler handler: this.collisionHandlers) {
								if(handler.handleCollision(projectile, fighters.get(j))) {
									fighters.get(j).setHealth(fighters.get(i).getHealth() - projectile.getState().getDamage());
									break; //found collision handling
								}
							}
						}
					}
				}
				
				// check if projectiles of fighter(i) are colliding with fighter(i)
				for(Projectile projectile: fighters.get(j).getProjectiles()) {
					//check for collision, using all availabable collision strategies
					for(CollisionDetectionStrategy collisionStrategy: this.collisionStrategies) {
						if(collisionStrategy.intersect(projectile, fighters.get(i)) != false) {
							for(CollisionHandler handler: this.collisionHandlers) {
								if(handler.handleCollision(projectile, fighters.get(i))) {
									fighters.get(i).setHealth(fighters.get(i).getHealth() - projectile.getState().getDamage());
									break; //found collision handling
								}
							}
						}
					}
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see org.jfge.engine.Updatable#update()
	 */
	@Override
	public void update() {
		/*
		 * check for collisions and arena bounds
		 */
		this.fighterCollisionDetection();
		this.projectileCollisionDetection();
		this.arenaBoundsDetection();
	}
}
