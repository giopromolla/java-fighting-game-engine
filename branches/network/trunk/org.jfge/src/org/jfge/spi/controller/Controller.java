package org.jfge.spi.controller;

import org.jfge.api.fsm.StateMachine;

/**
 * The Interface Controller.
 */
public interface Controller {
	
	/**
	 * Sets the fighter.
	 *
	 * @param stateMachine the new fighter
	 */
	public void setStateMachine(StateMachine stateMachine);
}
