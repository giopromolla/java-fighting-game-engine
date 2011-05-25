package org.jfge.api.projectile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.jfge.api.render.SpriteRenderer;
import org.jfge.api.sprite.AbstractSprite;
import org.jfge.spi.graphics.Graphics;

import com.google.inject.Inject;

/**
 * The Class ProjectileImpl.
 */
final class ProjectileImpl extends AbstractSprite implements Projectile {

	/** The name. */
	private String name;
	
	/** The sprite renderer. */
	private SpriteRenderer spriteRenderer;
	
	/** The states. */
	private Map<String, ProjectileState> states;
	
	/** The logger. */
	private final Logger logger;
	
	/** The cur state. */
	private ProjectileState curState;
	
	/** The start state. */
	private String startState;
	
	/**
	 * Instantiates a new projectile impl.
	 *
	 * @param logger the logger
	 * @param name the name
	 * @param renderer the renderer
	 * @param states the states
	 * @param startState the start state
	 */
	@Inject
	public ProjectileImpl(Logger logger, String name, SpriteRenderer renderer, List<ProjectileState> states, String startState) {
		this.name = name;
		this.spriteRenderer = renderer;
		this.logger = logger;
		this.startState = startState;
		
		/*
		 * adding states and setting parent. We're using a 
		 * hashmap for faster state access.
		 */
    	this.states = new HashMap<String, ProjectileState>();
		for(ProjectileState state: states) {
			state.setParent(this);
			this.states.put(state.getName(), state);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.jfge.api.sprite.Sprite#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.engine.Updatable#update()
	 */
	@Override
	public void update() {
		if(curState != null) {
			this.curState.update();
		}
		
		setX(getX()+getDx());
		setY(getY()+getDy());
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.engine.Renderable#render(org.jfge.spi.graphics.Graphics)
	 */
	@Override
	public void render(Graphics graphics) {
		if(graphics == null)
			return;
		
		if(this.curImage != null) {
			spriteRenderer.drawSprite(graphics, getImage(), getX(), getY(), getDirection());
		}
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fsm.StateMachine#handle(java.lang.String)
	 */
	@Override
	public synchronized boolean handle(String event) {
		if(event == null)
			return false;
		
		if(this.curState != null) {
			return this.curState.handle(event);
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fsm.StateMachine#setState(java.lang.String)
	 */
	@Override
	public synchronized boolean setState(String state) {
		if(state == null)
			return false;
		
		/*
		 * looking for needed state in our hashmap
		 */
		ProjectileState particleState = states.get(state);
		
		if(particleState != null) {
			// found it
			this.curState = particleState;
			this.curState.startState();
		}
		
		return particleState != null;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.projectile.Projectile#create()
	 */
	@Override
	public Projectile create() {
		return new ProjectileImpl(logger, name, spriteRenderer, new ArrayList<ProjectileState>(states.values()), startState);
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fsm.StateMachine#nextState()
	 */
	@Override
	public boolean nextState() {
		return curState.nextState();
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fsm.StateMachine#getState()
	 */
	@Override
	public ProjectileState getState() {
		return this.curState;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fsm.StateMachine#startState()
	 */
	@Override
	public boolean startState() {
		return this.setState(this.startState);
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fsm.StateMachine#hasFinalStateReached()
	 */
	@Override
	public boolean hasFinalStateReached() {
		return curState.hasFinalStateReached();
	}
}
