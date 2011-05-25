package org.jfge.api.fsm;

/**
 * The Interface State.
 *
 * @param <T> the generic type
 */
public interface State<T> extends StateMachine<State>{

	/**
	 * Gets the next state.
	 *
	 * @return the next state
	 */
	public String getNextState();
	
	/**
	 * Sets the parent.
	 *
	 * @param parent the new parent
	 */
	public void setParent(T parent);

}
