package org.jfge.api.arena;

import org.jfge.api.effect.ArenaEffect;
import org.jfge.api.engine.Renderable;
import org.jfge.api.engine.Updatable;
import org.jfge.api.fsm.State;

/**
 * The Interface ArenaState.
 */
public interface ArenaState extends State<Arena>, Updatable, Renderable {
	
	/**
	 * Checks for arena effect.
	 *
	 * @return true, if successful
	 */
	public boolean hasArenaEffect();
	
	/**
	 * Gets the arena effect.
	 *
	 * @return the arena effect
	 */
	public ArenaEffect getArenaEffect();
}
