package org.jfge.api.arena;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.jfge.api.effect.ArenaEffect;
import org.jfge.api.fighter.Fighter;
import org.jfge.spi.controller.Controller;
import org.jfge.spi.graphics.Graphics;
import org.jfge.spi.graphics.Image;
import org.jfge.spi.render.ArenaRenderer;

/**
 * The Class ArenaImpl.
 */
public final class ArenaImpl implements Arena {

	/** The backg ground. */
	private Image backgGround;
	
	/** The arena renderer. */
	private ArenaRenderer arenaRenderer;

	/** The cur state. */
	private ArenaState curState;

	/** The states. */
	private Map<String, ArenaState> states;

	/** The start state. */
	private String startState;

	/** The name. */
	private String name;

	/** The cur time. */
	private int curTime;
	
	/** The left fighter. */
	private Fighter leftFighter;
	
	/** The right fighter. */
	private Fighter rightFighter;
	
	/** The arena effects. */
	private List<ArenaEffect> arenaEffects;
	
	/** The cur round. */
	private int curRound;
	
	/** The left fighter wins. */
	private int leftFighterWins;
	
	/** The right fighter wins. */
	private int rightFighterWins;
	
	/** The left fighter controller. */
	private Controller leftFighterController;
	
	/** The right fighter controller. */
	private Controller rightFighterController;
	
	/**
	 * Instantiates a new arena impl.
	 *
	 * @param name the name
	 * @param states the states
	 * @param startState the start state
	 * @param backGround the back ground
	 * @param arenaRenderer the arena renderer
	 */
	public ArenaImpl(String name,
			List<ArenaState> states,
			String startState,
			Image backGround, 
			ArenaRenderer arenaRenderer) {
		this.name = name;
		this.startState = startState;
		this.backgGround = backGround;
		this.arenaRenderer = arenaRenderer;
		this.arenaEffects = new Vector<ArenaEffect>();
		this.curRound = 1;
		this.leftFighterWins = 0;
		this.rightFighterWins = 0;
		
		this.states = new HashMap<String, ArenaState>();
		for(ArenaState state: states) {
			state.setParent(this);
			this.states.put(state.getName(), state);
		}
		
//		this.startState();
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.engine.Updatable#update()
	 */
	@Override
	public void update() {
		updateFighterDirection();
		this.curState.update();
		for(int i=0; i< this.arenaEffects.size(); i++) {
			arenaEffects.get(i).update();
		}
	}
	
	/**
	 * Update fighter direction.
	 */
	private void updateFighterDirection()  {
		if(leftFighter == null) {
			return;
		}
		
		if(rightFighter == null) {
			return;
		}
		
		if(Math.abs(leftFighter.getX() - rightFighter.getX()) < (leftFighter.getImage().getWidth()+rightFighter.getImage().getWidth())/2) {
			
			// check direction for left fighter
			if(leftFighter.getDirection() == Fighter.LEFT) {
				leftFighter.setDirection(Fighter.RIGHT); // now correct the shift
				leftFighter.setX(leftFighter.getX()-(leftFighter.getImage().getWidth() + rightFighter.getImage().getWidth())/2);
			} else if (leftFighter.getDirection() == Fighter.RIGHT) {
				leftFighter.setDirection(Fighter.LEFT); // now correct the shift
				leftFighter.setX(leftFighter.getX()+(leftFighter.getImage().getWidth() + rightFighter.getImage().getWidth())/2);
			}	
			
			// check direction for right fighter
			if(rightFighter.getDirection() == Fighter.LEFT) {
				rightFighter.setDirection(Fighter.RIGHT); // now correct the shift
				rightFighter.setX(rightFighter.getX()-(leftFighter.getImage().getWidth() + rightFighter.getImage().getWidth())/2);
			} else if (rightFighter.getDirection() == Fighter.RIGHT) {
				rightFighter.setDirection(Fighter.LEFT); // now correct the shift
				rightFighter.setX(rightFighter.getX()+(leftFighter.getImage().getWidth() + rightFighter.getImage().getWidth())/2);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see org.jfge.api.engine.Renderable#render(org.jfge.spi.graphics.Graphics)
	 */
	@Override
	public void render(Graphics graphics) {
		this.arenaRenderer.drawBackground(graphics, this.backgGround, 0, 0);
		this.arenaRenderer.drawClock(graphics, this.curTime);
		this.curState.render(graphics);
		
		if(leftFighter != null) {
			this.arenaRenderer.drawLifebar(graphics, leftFighter.getName(), leftFighter.getHealth(), leftFighter.getPortrait(), this.leftFighterWins, ArenaRenderer.ALIGN_LEFT);
		}
		
		if(rightFighter != null) {
			this.arenaRenderer.drawLifebar(graphics, rightFighter.getName(), rightFighter.getHealth(), rightFighter.getPortrait().flip(), this.rightFighterWins, ArenaRenderer.ALIGN_RIGHT);
		}
		
		// render arena effects
		for(ArenaEffect arenaEffect: this.arenaEffects){
			arenaEffect.render(graphics);
		}
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fsm.StateMachine#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fsm.StateMachine#getState()
	 */
	@Override
	public ArenaState getState() {
		return this.curState;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fsm.StateMachine#handle(java.lang.String)
	 */
	@Override
	public boolean handle(String event) {
		return this.curState.handle(event);
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fsm.StateMachine#hasFinalStateReached()
	 */
	@Override
	public boolean hasFinalStateReached() {
		return this.curState.hasFinalStateReached();
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fsm.StateMachine#nextState()
	 */
	@Override
	public boolean nextState() {
		return this.curState.nextState();
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fsm.StateMachine#setState(java.lang.String)
	 */
	@Override
	public boolean setState(String state) {
		if (state == null)
			return false;

		/*
		 * looking for needed state in our hashmap
		 */
		ArenaState arenaState = states.get(state);

		if (arenaState != null) {
			// set found state to initial state
			arenaState.startState();
			
			// add arena Effects
			if(arenaState.hasArenaEffect()) {
				ArenaEffect arenaEffect = arenaState.getArenaEffect();
				arenaEffect.setParent(this);
				this.addArenaEffect(arenaEffect);
			}
			// setting found state to current state
			this.curState = arenaState;
			
		}

		return arenaState != null;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.fsm.StateMachine#startState()
	 */
	@Override
	public boolean startState() {
		this.curRound = 1;
		this.leftFighterWins = 0;
		this.rightFighterWins = 0;
		this.leftFighter.startState();
		this.rightFighter.startState();
		return this.setState(this.startState);
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.arena.Arena#getTime()
	 */
	@Override
	public int getTime() {
		return this.curTime;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.arena.Arena#setTime(int)
	 */
	@Override
	public void setTime(int time) {
		if(time < 0)
			time = 0;
		
		this.curTime = time;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.arena.Arena#addArenaEffect(org.jfge.api.effect.ArenaEffect)
	 */
	@Override
	public void addArenaEffect(ArenaEffect arenaEffect) {
		this.arenaEffects.add(arenaEffect);
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.arena.Arena#removeArenaEffet(org.jfge.api.effect.ArenaEffect)
	 */
	@Override
	public void removeArenaEffet(ArenaEffect arenaEffect) {
		this.arenaEffects.remove(arenaEffect);
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.arena.Arena#getRound()
	 */
	@Override
	public int getRound() {
		return curRound;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.arena.Arena#setRound(int)
	 */
	@Override
	public void setRound(int round) {
		if(round < 0)
			return;
		
		else this.curRound = round;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.arena.Arena#getWinner()
	 */
	@Override
	public Fighter getWinner() {
		if(!this.hasWinner())
			return null;
		
		if(this.rightFighter.hasFinalStateReached())
			return this.leftFighter;
		
		if(this.leftFighter.hasFinalStateReached())
			return this.rightFighter;
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.arena.Arena#hasWinner()
	 */
	@Override
	public boolean hasWinner() {
		if(this.leftFighter == null)
			return false;
		
		if(this.rightFighter == null)
			return false;
		
		if(this.rightFighter.hasFinalStateReached())
			return true;
		
		if(this.leftFighter.hasFinalStateReached())
			return true;
			
		return false;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.arena.Arena#getFighterLeftWins()
	 */
	@Override
	public int getFighterLeftWins() {
		return this.leftFighterWins;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.arena.Arena#getFighterRightWins()
	 */
	@Override
	public int getFighterRightWins() {
		return this.rightFighterWins;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.arena.Arena#setFighterLeftWins(int)
	 */
	@Override
	public void setFighterLeftWins(int wins) {
		this.leftFighterWins = wins;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.arena.Arena#setFighterRightWins(int)
	 */
	@Override
	public void setFighterRightWins(int wins) {
		this.rightFighterWins = wins;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.arena.Arena#getFighterLeft()
	 */
	@Override
	public Fighter getFighterLeft() {
		return this.leftFighter;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.arena.Arena#getFighterRight()
	 */
	@Override
	public Fighter getFighterRight() {
		return this.rightFighter;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.arena.Arena#setFighterLeft(org.jfge.api.fighter.Fighter)
	 */
	@Override
	public void setFighterLeft(Fighter left) {
		if(left == null)
			return;
		
		this.leftFighter = left;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.arena.Arena#setFighterRight(org.jfge.api.fighter.Fighter)
	 */
	@Override
	public void setFighterRight(Fighter right) {
		if(right == null)
			return;
		
		this.rightFighter = right;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.arena.Arena#setFigherLeftPos(int, int)
	 */
	@Override
	public void setFigherLeftPos(int x, int y) {
		if(this.leftFighter == null)
			return;
		
		leftFighter.setX(x);
		leftFighter.setY(y);
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.arena.Arena#setFighterRightPos(int, int)
	 */
	@Override
	public void setFighterRightPos(int x, int y) {
		if(this.rightFighter == null)
			return;
		
		rightFighter.setX(x);
		rightFighter.setY(y);
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.arena.Arena#setFighterLeftController(org.jfge.spi.controller.Controller)
	 */
	@Override
	public void setFighterLeftController(Controller left) {
		if(left == null)
			return;
		
		this.leftFighterController = left;
	}

	/* (non-Javadoc)
	 * @see org.jfge.api.arena.Arena#setFighterRightController(org.jfge.spi.controller.Controller)
	 */
	@Override
	public void setFighterRightController(Controller right) {
		if(right == null)
			return;
		
		this.rightFighterController = right;
	}

	@Override
	public Controller getFighterLeftController() {
		return this.leftFighterController;
	}

	@Override
	public Controller getFighterRightController() {
		return this.rightFighterController;
	}
}
