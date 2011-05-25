package org.jfge.api.arena;

import org.jfge.api.effect.ArenaEffect;
import org.jfge.api.fsm.State;
import org.jfge.spi.graphics.Graphics;

final class Running implements ArenaState {

	private final int ticks;
	
	private int curTicks;
	
	private Arena parent;
	
	private String nextState;
	
	private String name;
	
	private boolean isFinalState;
	
	private ArenaEffect arenaEffect;
	
	public Running(String name,
			int ticks, 
			String nextState,
			ArenaEffect arenaEffect,
			boolean isFinalState) {
		this.name = name;
		this.ticks = ticks;
		this.nextState = nextState;
		this.isFinalState = isFinalState;
		this.arenaEffect = arenaEffect;
	}
	
	@Override
	public String getNextState() {
		return this.nextState;
	}

	@Override
	public void setParent(Arena parent) {
		if(parent == null)
			return;
		
		this.parent = parent;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public State getState() {
		return this;
	}

	@Override
	public boolean handle(String event) {
		return false;
	}

	@Override
	public boolean hasFinalStateReached() {
		return this.isFinalState;
	}

	@Override
	public boolean nextState() {
		return this.parent.setState(this.nextState);
	}

	@Override
	public boolean setState(String state) {
		return false;
	}

	@Override
	public boolean startState() {
		if(this.parent != null) {
			if(this.parent.getFighterRightController() != null) {
				this.parent.getFighterRightController().setStateMachine(this.parent.getFighterRight());
			}
			
			if(this.parent.getFighterLeftController() != null) {
				this.parent.getFighterLeftController().setStateMachine(this.parent.getFighterLeft());	
			}
		}
		
		curTicks = 0;
		return true;
	}

	@Override
	public void update() {
		if(this.parent == null)
			return;
		
		if(parent.getTime() <= 0) {
			//We've reached the end, now we're cleaning up and setting the next state 
			curTicks = 0;
			this.nextState(); // setting nextState
		}
		
		this.checkForFighterVictory();
		this.checkForFighterEndState();
		
		if(curTicks == 0) {
			this.parent.setTime(parent.getTime()-1);
		}
		curTicks = (curTicks+1) % ticks;
	}

	private void checkForFighterVictory() {
		if(this.parent == null)
			return;
		
		if(this.parent.getFighterLeft() == null)
			return;
		
		if(this.parent.getFighterRight() == null)
			return;
		
		// check if one of our fighters has passed out, if it's so
		// then we better finish this round by setting the next state.
		if(this.parent.getFighterLeft().getHealth() <= 0 && 
				!this.parent.getFighterRight().inVictoryState() && 
				this.parent.getFighterRight().getDy() == 0 && // this can only be done when fighter's not moving
				this.parent.getFighterRight().getDx() == 0){
			this.parent.getFighterRight().victoryState();
		}
		
		if(this.parent.getFighterRight().getHealth() <= 0 && 
				!this.parent.getFighterLeft().inVictoryState() &&
				this.parent.getFighterLeft().getDy() == 0 && // this can only be done when fighter's not moving
				this.parent.getFighterLeft().getDx() == 0){
			
			this.parent.getFighterLeft().victoryState();
		}
	}
	
	private void checkForFighterEndState() {
		if(this.parent == null)
			return;
		
		if(this.parent.getFighterLeft() == null)
			return;
		
		if(this.parent.getFighterRight() == null)
			return;
		
		// check if one of our fighters has passed out, if it's so
		// then we better finish this round by setting the next state.
		if(this.parent.getFighterLeft().hasFinalStateReached()){
			this.nextState();
		}
		
		if(this.parent.getFighterRight().hasFinalStateReached()){
			this.nextState();
		}
	}

	
	@Override
	public void render(Graphics graphics) { }

	@Override
	public ArenaEffect getArenaEffect() {
		return this.arenaEffect;
	}

	@Override
	public boolean hasArenaEffect() {
		return this.arenaEffect != null;
	}
}
