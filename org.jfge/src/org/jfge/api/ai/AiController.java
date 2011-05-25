package org.jfge.api.ai;

import org.jfge.api.fighter.Fighter;

/**
 * The Interface AiController.
 */
public interface AiController {
	
	/** The Constant NEAR. */
	public final static String NEAR = "near";
	
	/** The Constant MID. */
	public final static String MID = "mid";
	
	/** The Constant FAR. */
	public final static String FAR = "far";
	
	/**
	 * Handle.
	 *
	 * @param obsrvFighter the obsrv sprite
	 * @param oppFighter the opp sprite
	 * @return true, if successful
	 */
	public boolean handle(Fighter obsrvFighter, Fighter oppFighter);
}
