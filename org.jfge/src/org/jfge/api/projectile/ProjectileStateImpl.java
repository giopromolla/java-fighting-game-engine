package org.jfge.api.projectile;

import java.util.List;
import java.util.logging.Logger;

import org.jfge.api.fsm.State;
import org.jfge.api.sprite.Sprite;
import org.jfge.spi.graphics.Image;
import org.jfge.spi.physics.SpritePhysics;

import com.google.inject.Inject;

/**
 * The Class ProjectileStateImpl.
 */
final class ProjectileStateImpl implements ProjectileState {
	
	/** The name. */
	private String name;
	
	/** The damage. */
	private int damage;
	
	/** The ticks. */
	private int ticks;
	
	/** The loop. */
	private boolean loop;
	
	/** The next state. */
	private String nextState;
	
	/** The move. */
	private SpritePhysics<Projectile> physics;
	
	/** The parent. */
	private Projectile parent;
	
	/** The cur index. */
	private int curIndex;
	
	/** The cur ticks. */
	private int curTicks;
	
	/** The images. */
	private List<Image> images;
	
	/** The logger. */
	private final Logger logger;
	
	/** The is final state. */
	private boolean isFinalState;
	
	/**
	 * Instantiates a new projectile state impl.
	 *
	 * @param logger the logger
	 * @param name the name
	 * @param images the images
	 * @param ticks the ticks
	 * @param loop the loop
	 * @param nextState the next state
	 * @param move the move
	 * @param damage TODO
	 */
	@Inject
	public ProjectileStateImpl(Logger logger, 
			String name, 
			List<Image> images,
			int ticks, 
			boolean loop, 
			String nextState, 
			boolean isFinalState,
			SpritePhysics<Projectile> move, 
			int damage) {
		
		this.logger = logger;
		this.name = name;
		this.images = images;
		this.ticks = ticks;
		this.loop = loop;
		this.nextState = nextState;
		this.isFinalState = isFinalState;
		this.physics = move;
		this.damage = damage;
	}
	
	/* (non-Javadoc)
	 * @see org.jfge.sprite.SpriteState#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see org.jfge.sprite.SpriteState#getNextState()
	 */
	@Override
	public String getNextState() {
		return this.nextState;
	}

	/* (non-Javadoc)
	 * @see org.jfge.sprite.SpriteState#setParent(org.jfge.sprite.Sprite)
	 */
	@Override
	public void setParent(Projectile sprite) {
		this.parent = sprite;
		
		if(this.physics != null) {
			this.physics.setParent(this.parent);	
		}
	}

	/* (non-Javadoc)
	 * @see org.jfge.engine.Updatable#update()
	 */
	@Override
	public void update() {
		if(this.parent == null)
			return;
		
		// update sprite physics
		if(physics == null) {
			parent.setDx(0);
			parent.setDy(0);
		} else {
			physics.update();
		}
		
		/*
		 * Setting the current sprite image, check if we have to flip the image. 
		 * This is the case when the fighter is looking in Sprite.LEFT direction
		 */
		if(parent.getDirection() == Sprite.RIGHT) {
			parent.setImage(this.images.get(this.curIndex));
		} else if (parent.getDirection() == Sprite.LEFT) {
			parent.setImage(this.images.get(this.curIndex).flip());
		}
		
		//updating current image index and ticks
		if(curTicks == 0) {
			curIndex = (curIndex+1) % images.size();
		}
		curTicks = (curTicks+1) % ticks;
				
		if(curIndex == 0 && !loop) {
			//We've reached the end, now we're cleaning up and setting the next state 
			curTicks = 0;
			nextState();
		}
	}

	/* (non-Javadoc)
	 * @see org.jfge.fsm.StateMachine#handle(java.lang.String)
	 */
	@Override
	public boolean handle(String event) {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.jfge.fsm.StateMachine#setState(java.lang.String)
	 */
	@Override
	public boolean setState(String state) { 
		return false;
	}

	@Override
	public State getState() {
		return this;
	}

	@Override
	public boolean nextState() {
		return parent.setState(nextState); // setting nextState
	}

	@Override
	public boolean startState() {
		this.curIndex = 0;
		this.curTicks = 0;
	
		if(this.parent != null && this.physics != null) {
			this.physics.init();
		}
		
		return true;
	}

	@Override
	public boolean hasFinalStateReached() {
		return this.isFinalState;
	}

	@Override
	public int getDamage() {
		return this.damage;
	}
}
