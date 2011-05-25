package org.jfge.api.game;

import org.jfge.api.engine.Engine;
import org.jfge.api.engine.Renderable;
import org.jfge.api.engine.Updatable;
import org.jfge.api.fsm.StateMachine;

public interface Game extends StateMachine<GameState>, Updatable, Renderable{
	
	/**
	 * Start.
	 */
	public void start();
	
	/**
	 * End.
	 */
	public void end();
	
	public Engine getEngine();
}
