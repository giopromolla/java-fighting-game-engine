package org.jfge.api.fighter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.jfge.api.effect.CollisionEffect;
import org.jfge.api.projectile.Projectile;
import org.jfge.api.render.SpriteRenderer;
import org.jfge.api.sprite.AbstractSprite;
import org.jfge.spi.graphics.Graphics;
import org.jfge.spi.graphics.Image;

// TODO: Auto-generated Javadoc
/**
 * The Class FighterImpl.
 */
final class FighterImpl extends AbstractSprite implements Fighter {
	
	/** The RIGH t_ bounds. */
	private final int BOUNDS_WIDTH;
	
	/** The DOW n_ bounds. */
	private final int BOUNDS_HEIGHT;
	
	/** The HEALTH. */
	private final int HEALTH = 100;
	
	/** The name. */
    private String name;
    
    /** The portrait. */
    private Image portrait;
    
    /** The health. */
    private int health = HEALTH;
    
    /** The states. */
    private Map<String, FighterState> states;
    
    /** The renderer. */
    private SpriteRenderer renderer;
    
    /** The input queue. */
    private InputQueue inputQueue;
    
    /** The cur state. */
    private FighterState curState;
    
    /** The start state. */
    private String startState;
    
    /** The dead state. */
    private String endState;
    
    /** The victory state. */
    private String victoryState;
    
    /** The effects. */
    private List<CollisionEffect> effects;
    
    /** The projectiles. */
    private List<Projectile> projectiles;
    
    /**
     * Instantiates a new fighter impl.
     *
     * @param name the name
     * @param renderer the renderer
     * @param inputQueue the input queue
     * @param states the states
     * @param startState the start state
     * @param endState the end state
     * @param boundsWidth the bounds width
     * @param boundsHeight the bounds height
     * @param victoryState TODO
     */
    public FighterImpl(String name, 
    		Image portrait,
    		SpriteRenderer renderer,
    		InputQueue inputQueue,
    		List<FighterState> states,
    		String startState,
    		String endState,
    		int boundsWidth,
    		int boundsHeight, 
    		String victoryState) {
    	this.name = name;
    	this.portrait = portrait;
    	this.renderer = renderer;
    	this.inputQueue = inputQueue;
    	this.setDirection(Fighter.RIGHT);
    	this.startState = startState;
    	this.endState = endState;
    	this.victoryState = victoryState;
    	this.BOUNDS_WIDTH = boundsWidth;
    	this.BOUNDS_HEIGHT = 2*boundsHeight;
    	
    	
    	this.effects = new Vector<CollisionEffect>();
    	this.projectiles = new Vector<Projectile>();
    	
    	/*
		 * adding states and setting parent. We're using a 
		 * hashmap for faster state access.
		 */
    	this.states = new HashMap<String, FighterState>();
		for(FighterState state: states) {
			state.setParent(this);
			this.states.put(state.getName(), state);
		}
	}

	/* (non-Javadoc)
	 * @see org.jfge.render.Renderable#render(java.awt.Graphics)
	 */
	@Override
	public void render(Graphics g) {
		if(g == null)
			return;
		
		if(this.curImage != null) {
			renderer.drawSprite(g, getImage(), getX(), getY(), getDirection());
		}
		
		for(CollisionEffect effect: this.effects) {
			effect.render(g);
		}
		
		for(Projectile projectile: this.projectiles) {
			projectile.render(g);
		}
	}

	/* (non-Javadoc)
	 * @see org.jfge.engine.Updatable#update()
	 */
	@Override
	public void update() {
		if(curState != null) {
			this.curState.update();
		}
		
		
		// updating projectiles
		this.updateProjectiles();
		
		// update collision effects
		for(int i=0; i< this.effects.size(); i++) {
			this.effects.get(i).update();
		}
		
		checkForDeadState();
		
		// updating input queue
		this.inputQueue.update();
		
		// updating fighters position
		setX(getX()+getDx());
		setY(getY()+getDy());
	}

	
	private void checkForDeadState() {
		if(this.health <= 0 && 
			this.getDx() == 0 && // this can only be done when fighter's not moving
			this.getDy() == 0) {
			this.endState();
		}
	}
	
	/**
	 * Update projectiles.
	 */
	private void updateProjectiles() {
		for(int i=0; i < this.projectiles.size(); i++) {
			// check if the projectiles are within the bounds of 0 < x < display width
			if(projectiles.get(i).getX() <= 0 || this.projectiles.get(i).getX() >= BOUNDS_WIDTH) {
				this.projectiles.remove(i);
				continue;
			}
			
			// check if the projectiles are within the bounds of 0 < y < display width
			if(projectiles.get(i).getY() <= 0 || this.projectiles.get(i).getY() >= BOUNDS_HEIGHT) {
				this.projectiles.remove(i);
				continue;
			}
			
			// check if the projectiles are terminated
			if(projectiles.get(i).hasFinalStateReached()) {
				this.projectiles.remove(i);
				continue;
			}
			
			// update the projectile if its in the visible display area
			this.projectiles.get(i).update();
		}
	}
	
	/* (non-Javadoc)
	 * @see org.jfge.fighter.Fighter#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see org.jfge.sprite.Sprite#setState(java.lang.String)
	 */
	public boolean setState(String state) {
		if(state == null)
			return false;
		
		/*
		 * looking for needed state in our hashmap
		 */
		FighterState fighterState = states.get(state);
		
		if(fighterState != null) {
			// set found state to initial state
			fighterState.startState();
			
			CollisionEffect effect = fighterState.getCollisionEffect();
			if(effect != null) {
				// found a collison effect in this state, add it for further processing
				effect.setParent(this);
				this.addCollisionEffect(effect);
			}
			
			Projectile projectile = fighterState.getProjectile();
			if(projectile != null) {
				// found a projectile in this state, add it for further processing
				this.addProjectile(projectile);
				projectile.setDirection(this.direction);
			}
			
			// setting found state to current state
			this.curState = fighterState;
		}
		
		return fighterState != null;
	}
	/* (non-Javadoc)
	 * @see org.jfge.fighter.Fighter#handle(java.lang.String)
	 */
	@Override
	public boolean handle(String event) {
		if(event == null)
			return false;
		
		if(curState == null)
			return false;

		// detect a possible special move
		List<String> move = this.inputQueue.handleInput(event);
		if(move.size() > 1 && curState.handle(move)) {
			return true;
		}
		
		// check if input event has direction dependency
		List<String> directionList = new ArrayList<String>();
		directionList.add(this.getDirection() + "");
		directionList.add(event);
		if(curState.handle(directionList)) {
			return true;
		}
		
		// dispatch event to current state
		return this.curState.handle(event);
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
	public FighterState getState() {
		return this.curState;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fsm.StateMachine#startState()
	 */
	@Override
	public boolean startState() {
		this.health = HEALTH;
		return this.setState(this.startState);
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fighter.Fighter#addCollisionEffect(org.jfge.api.effect.CollisionEffect)
	 */
	@Override
	public void addCollisionEffect(CollisionEffect effect) {
		this.effects.add(effect);
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fighter.Fighter#addProjectile(org.jfge.api.projectile.Projectile)
	 */
	@Override
	public void addProjectile(Projectile projectile) {
		this.projectiles.add(projectile);
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fighter.Fighter#removeCollisionEffect(org.jfge.api.effect.CollisionEffect)
	 */
	@Override
	public void removeCollisionEffect(CollisionEffect effect) {
		this.effects.remove(effect);
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fighter.Fighter#removeProjectile(org.jfge.api.projectile.Projectile)
	 */
	@Override
	public void removeProjectile(Projectile projectile) {
		this.projectiles.remove(projectile);
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fighter.Fighter#getProjectiles()
	 */
	@Override
	public List<Projectile> getProjectiles() {
		return this.projectiles;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fsm.StateMachine#hasFinalStateReached()
	 */
	@Override
	public boolean hasFinalStateReached() {
		return this.curState.hasFinalStateReached();
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fighter.Fighter#getHealth()
	 */
	@Override
	public int getHealth() {
		return this.health;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fighter.Fighter#setHealth(int)
	 */
	@Override
	public void setHealth(int health) {
		if(health > HEALTH) {
			this.health = HEALTH;
			return;
		}
		
		if(health <= 0) {
			this.health = 0;
			return;
		}
		
		this.health = health;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fighter.Fighter#endState()
	 */
	@Override
	public void endState() {
		this.setState(this.endState);
	}

	@Override
	public boolean inVictoryState() {
		if(this.curState == null )
			return false;
		
		return this.curState.getName() == this.victoryState;
	}

	@Override
	public void victoryState() {
		this.setState(this.victoryState);
	}

	@Override
	public Image getPortrait() {
		return this.portrait;
	}
}
