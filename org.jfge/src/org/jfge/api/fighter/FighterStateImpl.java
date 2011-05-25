package org.jfge.api.fighter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jfge.api.effect.CollisionEffect;
import org.jfge.api.projectile.Projectile;
import org.jfge.spi.graphics.Image;
import org.jfge.spi.physics.SpritePhysics;

/**
 * The Class FighterStateImpl.
 */
final class FighterStateImpl implements FighterState {

	/** The images. */
	private List<Image> images;
	
	/** The fighter. */
	private Fighter fighter;
	
	/** The damage. */
	private int damage;
	
	/** The name. */
	private String name;
	
	/** The loop. */
	private boolean loop;
	
	/** The next state. */
	private String nextState;
	
	/** The cur index. */
	private int curIndex;
	
	/** The ticks. */
	private int ticks;
	
	/** The cur ticks. */
	private int curTicks = 0;
	
	/** The transitions. */
	private Map<List<String>, String> transitions;
	
	/** The sprite physics. */
	private SpritePhysics<Fighter> physics;
	
	/** The collision effect. */
	private CollisionEffect collisionEffect;
	
	/** The projectile. */
	private Projectile projectile;
	
	/** The is final state. */
	private boolean isFinalState;
	
	/**
	 * Instantiates a new fighter state impl.
	 *
	 * @param state the state
	 * @param images the images
	 * @param ticks the ticks
	 * @param loop the loop
	 * @param nextState the next state
	 * @param isFinalState the is final state
	 * @param transitions the transitions
	 * @param physics the physics
	 * @param collisionEffect the collision effect
	 * @param projectile the projectile
	 */
	public FighterStateImpl(String name, 
			int damage,
			List<Image> images,
			int ticks,
			boolean loop, 
			String nextState, 
			boolean isFinalState,
			Map<List<String>, String> transitions, 
			SpritePhysics<Fighter> physics,
			CollisionEffect collisionEffect,
			Projectile projectile){
		
		this.name = name;
		this.damage = damage;
		this.images = images;
		this.ticks = ticks;
		this.loop = loop;
		this.nextState = nextState;
		this.isFinalState = isFinalState;
		this.transitions = transitions;
		this.physics = physics;
		this.collisionEffect = collisionEffect;
		this.projectile = projectile;
	}
	
	/* (non-Javadoc)
	 * @see org.jfge.engine.Updatable#update()
	 */
	@Override
	public void update() {
		if(fighter == null)
			return;
		
		// update sprite physics
		if(physics == null) {
			fighter.setDx(0);
			fighter.setDy(0);
		} else {
			physics.update();
		}
		
		/*
		 * Setting the current sprite image, check if we have to flip the image. 
		 * This is the case when the fighter is looking in Sprite.LEFT direction
		 */
		if(fighter.getDirection() == Fighter.RIGHT) {
			fighter.setImage(this.images.get(this.curIndex));
		} else if (fighter.getDirection() == Fighter.LEFT) {
			fighter.setImage(this.images.get(this.curIndex).flip());
		}
		
		//updating current image index and ticks
		if(curTicks == 0) {
			curIndex = (curIndex+1) % images.size();
		}
		curTicks = (curTicks+1) % ticks;
				
		if(curIndex == 0 && curTicks == 0 && !loop) {
			//We've reached the end, now we're cleaning up and setting the next state 
			curTicks = 0;
			this.nextState(); // setting nextState
		}
	}

	/* (non-Javadoc)
	 * @see org.jfge.fighter.FighterState#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see org.jfge.sprite.SpriteState#setSprite(org.jfge.sprite.Sprite)
	 */
	@Override
	public void setParent(Fighter sprite) {
		this.fighter = sprite;
		
		if(this.physics != null) {
			this.physics.setParent(sprite);	
		}
		
		if(collisionEffect != null) {
			collisionEffect.setParent(sprite);
		}
	}

	/* (non-Javadoc)
	 * @see org.jfge.fsm.StateMachine#handle(java.lang.String)
	 */
	@Override
	public boolean handle(String event) {
		if(transitions == null)
			return false;
		
		List<String> events = new ArrayList<String>();
		events.add(event);
		String state = transitions.get(events);
		
		if(nextState != null)
			this.fighter.setState(state);
	
		return nextState != null;
	}

	/* (non-Javadoc)
	 * @see org.jfge.fsm.StateMachine#setState(java.lang.String)
	 */
	@Override
	public boolean setState(String state) { 
		// look if we can find a transition to
		
		return false; 
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fsm.State#getNextState()
	 */
	@Override
	public String getNextState() {
		return this.nextState;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fsm.StateMachine#getState()
	 */
	@Override
	public FighterState getState() {
		return this;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fsm.StateMachine#nextState()
	 */
	@Override
	public boolean nextState() {
		return this.fighter.setState(nextState);
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fighter.FighterState#handle(java.util.List)
	 */
	@Override
	public boolean handle(List<String> move) {
		String nextState = transitions.get(move);
		
		if(nextState != null) {
			this.fighter.setState(nextState);
		}
			
		return nextState != null;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fighter.FighterState#getCollisionEffect()
	 */
	@Override
	public CollisionEffect getCollisionEffect() {
		return this.collisionEffect;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fighter.FighterState#getProjectile()
	 */
	@Override
	public Projectile getProjectile() {
		if(this.projectile != null) {
			Projectile projectile = this.projectile.create();
			projectile.setX(fighter.getX());
			projectile.setY((int) (fighter.getY()-fighter.getHeight()*.6));
			projectile.setDirection(fighter.getDirection());
			projectile.startState();
			return projectile;
		}
		
		return projectile;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fsm.StateMachine#startState()
	 */
	@Override
	public boolean startState() {
		this.curIndex = 0;
		this.curTicks = 0;
		
		return true;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fsm.StateMachine#hasFinalStateReached()
	 */
	@Override
	public boolean hasFinalStateReached() {
		return this.isFinalState;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fighter.FighterState#getDamage()
	 */
	@Override
	public int getDamage() {
		return damage;
	}	
}
