package org.jfge.api.arena;

import org.jfge.api.effect.ArenaEffect;
import org.jfge.api.fighter.Fighter;
import org.jfge.api.fsm.State;
import org.jfge.api.sprite.Sprite;
import org.jfge.spi.graphics.Graphics;
import org.jfge.spi.render.ArenaRenderer;

public final class Intro implements ArenaState {

	private final int ticks;
	
	private int curTicks;
	
	private Arena parent;
	
	private String nextState;
	
	private String name;
	
	private boolean isFinalState;
	
	private ArenaEffect arenaEffect;
	
	private int startTime;
	
	private ArenaRenderer arenaRenderer;
	
	public Intro(ArenaRenderer arenaRenderer,
			String name,
			int startTime,
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
		this.startTime = startTime;
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
		return true;
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
		
		// we're in the beginning, so we have to initialize the fighters position and state
		if(curTicks == 0) {
			Fighter left = this.parent.getFighterLeft();
			left.startState();
			left.setDirection(Sprite.RIGHT);
			left.setX(100);
			left.setY(240);
			
			Fighter right = this.parent.getFighterRight();
			right.startState();
			right.setDirection(Sprite.LEFT);
			right.setX(380);
			right.setY(240);
		}
		
		if(curTicks++ == ticks) {
			//We've reached the end, now we're cleaning up and setting the next state 
			curTicks = 0;
			this.nextState(); // setting nextState
		}
		
		this.parent.setTime(this.startTime);
	}

	@Override
	public void render(Graphics graphics) { 
		if(graphics == null)
			return;
		
		if(this.parent == null)
			return;
		
		arenaRenderer.drawRound(graphics, this.parent.getRound());
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
