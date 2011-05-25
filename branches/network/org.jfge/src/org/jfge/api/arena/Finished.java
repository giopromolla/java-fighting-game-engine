package org.jfge.api.arena;

import org.jfge.api.effect.ArenaEffect;
import org.jfge.api.fsm.State;
import org.jfge.spi.graphics.Graphics;
import org.jfge.spi.render.ArenaRenderer;

public final class Finished implements ArenaState {

	private final int ticks;
	
	private int curTicks;
	
	private Arena parent;
	
	private String nextState;
	
	private String name;
	
	private boolean isFinalState;
	
	private ArenaEffect arenaEffect;
	
	private ArenaRenderer arenaRenderer;
	
	public Finished(ArenaRenderer arenaRenderer,
			String name,
			int ticks, 
			String nextState,
			ArenaEffect arenaEffect,
			boolean isFinalState) {
		this.arenaRenderer = arenaRenderer;
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
		if(this.parent == null)
			return false;
		
		if(this.parent.hasWinner()) {
			if(this.parent.getWinner() == parent.getFighterLeft()) {
				parent.setFighterLeftWins(parent.getFighterLeftWins()+1);
			} else {
				parent.setFighterRightWins(parent.getFighterRightWins()+1);
			}
		}
		
		this.parent.setRound(parent.getRound()+1);
		
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
				this.parent.getFighterRightController().setStateMachine(null);
			}
			
			if(this.parent.getFighterLeftController() != null) {
				this.parent.getFighterLeftController().setStateMachine(null);	
			}
		}
		
		curTicks = 0;
		return true;
	}

	@Override
	public void update() {
		if(parent == null)
			return;
		
		// check if one of our fighters has passed out, if it's so
		// then we better finish this round by setting the next state.
//		if(this.parent.hasWinner() &&
//				this.parent.getWinner() == this.parent.getFighterRight() &&
//				!this.parent.getFighterRight().inVictoryState() && 
//				this.parent.getFighterRight().getDy() == 0 && // this can only be done when fighter's not moving
//				this.parent.getFighterRight().getDx() == 0){
//			
//			this.parent.getFighterRight().victoryState();
//		}
//		
//		if(this.parent.hasWinner() &&
//				this.parent.getWinner() == this.parent.getFighterLeft() &&
//				!this.parent.getFighterLeft().inVictoryState() &&
//				this.parent.getFighterLeft().getDy() == 0 && // this can only be done when fighter's not moving
//				this.parent.getFighterLeft().getDx() == 0){
//			
//			this.parent.getFighterLeft().victoryState();
//		}
		
		if(curTicks++ == ticks) {
			//We've reached the end, now we're cleaning up and setting the next state 
			curTicks = 0;
			this.nextState(); // setting nextState
		}
	}

	@Override
	public void render(Graphics graphics) { 
		if(graphics == null)
			return;
		
		if(this.parent == null)
			return;
		
		if(this.parent.hasWinner())
			arenaRenderer.drawWinner(graphics, this.parent.getWinner().getName() + " Wins");
		else
			arenaRenderer.drawWinner(graphics, "Time's Up");
	}

	@Override
	public ArenaEffect getArenaEffect() {
		return this.arenaEffect;
	}

	@Override
	public boolean hasArenaEffect() {
		return this.arenaEffect != null;
	}

}
