package org.jfge.api.collision;

import org.jfge.api.fsm.StateMachine;

/**
 * The Interface CollisionHandler.
 */
public interface CollisionHandler {
	
	/**
	 * Handle collision.
	 *
	 * @param fsm the sprite
	 * @param colFsm the col sprite
	 * @return true, if successful
	 */
	public boolean handleCollision(StateMachine fsm, StateMachine colFsm);
}
