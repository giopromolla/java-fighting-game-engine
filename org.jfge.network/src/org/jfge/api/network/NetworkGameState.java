package org.jfge.api.network;

import org.jfge.api.game.GameState;

public interface NetworkGameState extends GameState {
	/**
	 * The update count represents the count of updates
	 * passed since the start of the state.
	 * @return the current update count number 
	 */
	public int getUpdateCount();
	
	/**
	 * The connection that will be used for communication
	 * @param con The connection to use
	 */
	public void setConnection(Connection con);
}
