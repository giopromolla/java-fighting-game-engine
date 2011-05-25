package org.jfge.api.fsm;

// TODO: Auto-generated Javadoc
/**
 * The Interface StateMachine.
 *
 * @param <T> the generic type
 */
public interface StateMachine<T extends State> {
	
	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public boolean setState(String state);
	
	/**
	 * Handle.
	 *
	 * @param event the event
	 */
	public boolean handle(String event);

	
	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public T getState();
	
	/**
	 * Next state.
	 */
	public boolean nextState();
	
	/**
	 * Initialize.
	 */
	public boolean startState();
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName();
	
	/**
	 * Checks for final state reached.
	 *
	 * @return true, if successful
	 */
	public boolean hasFinalStateReached();
}
