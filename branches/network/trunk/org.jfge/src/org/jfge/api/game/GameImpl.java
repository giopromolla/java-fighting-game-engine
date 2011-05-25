package org.jfge.api.game;

import java.util.HashMap;
import java.util.List;

import org.jfge.api.engine.Engine;
import org.jfge.spi.graphics.Graphics;

final class GameImpl implements Game {

	private Engine engine;
	
	private String name;
	
	private GameState curState;
	
	private HashMap<String, GameState> states;
	
	private String startState;
	
	public GameImpl(Engine engine, 
			String name, 
			List<GameState> states, 
			String startState) {
		
		this.engine = engine;
		this.name = name;
		
		this.states = new HashMap<String, GameState>();
		for(GameState state: states) {
			state.setParent(this);
			this.states.put(state.getName(), state);
		}
		
		this.startState = startState;
	}
	
	/* (non-Javadoc)
	 * @see org.jfge.game.Game#end()
	 */
	@Override
	public void end() {
		if(this.engine == null)
			return;
		
		this.engine.stop();
	}

	/* (non-Javadoc)
	 * @see org.jfge.game.Game#start()
	 */
	@Override
	public void start() {
		if(this.engine == null)
			return;
		
		this.startState();
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public GameState getState() {
		return curState;
	}

	@Override
	public boolean handle(String event) {
		if(curState == null)
			return false;
		
		return curState.handle(event);
	}

	@Override
	public boolean hasFinalStateReached() {
		if(curState == null)
			return false;
		
		return curState.hasFinalStateReached();
	}

	@Override
	public boolean nextState() {
		if(curState == null)
			return false;
		
		return curState.nextState();
	}

	@Override
	public boolean setState(String state) {
		if (state == null)
			return false;

		/*
		 * looking for needed state in our hashmap
		 */
		GameState gameState = states.get(state);

		if (gameState != null) {
			// set found state to initial state
			gameState.startState();
			
			// setting found state to current state
			this.curState = gameState;
			
		}

		return gameState != null;
	}

	@Override
	public boolean startState() {
		return this.setState(this.startState);
	}

	@Override
	public void update() {
		if(curState == null)
			return;
		
		this.curState.update();
	}

	@Override
	public void render(Graphics graphics) {
		if(graphics == null)
			return;
		
		if(curState == null)
			return;
		
		this.curState.render(graphics);
	}

	@Override
	public Engine getEngine() {
		return this.engine;
	}
}
