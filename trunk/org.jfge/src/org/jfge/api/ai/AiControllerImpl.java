package org.jfge.api.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.jfge.api.fighter.Fighter;
import org.jfge.api.projectile.Projectile;
import org.jfge.api.sprite.Sprite;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * The Class AiControllerImpl.
 */
public final class AiControllerImpl implements AiController {
	
	/** The transitions. */
	private Map<List<String>,String> transitions;
	
	/** The logger. */
	private final Logger logger;
	
	/**
	 * Instantiates a new ai controller impl.
	 *
	 * @param logger the logger
	 * @param transitions the transitions
	 */
	@Inject
	public AiControllerImpl(Logger logger,
			@Assisted Map<List<String>, String> transitions) {
		this.logger = logger;
		this.transitions = transitions;
	}

	/* (non-Javadoc)
	 * @see org.jfge.ai.AiController#handle(org.jfge.sprite.Sprite, org.jfge.sprite.Sprite)
	 */
	@Override
	public boolean handle(Fighter obsrvFighter, Fighter oppFighter) {
		
		if(this.handleProjectiles(obsrvFighter, oppFighter)) {
			// found some projectiles we should care about, skipping the fighter reaction
			return true;
		}
		
		//create new tuple
		List<String> tuple = new ArrayList<String>();
		
		tuple.add(this.calculateSpriteDistance(obsrvFighter, oppFighter));
		tuple.add(obsrvFighter.getState().getName());
		tuple.add(oppFighter.getState().getName());
		
		//find a reaction to our fighter
		String reaction = transitions.get(tuple);
		
		if(reaction != null) {
			obsrvFighter.setState(reaction);
		}
		
		//returning reaction 
		return reaction != null ? true: false;
	}

	private boolean handleProjectiles(Fighter obsrvFighter, Fighter oppFighter) {
		
		//create new tuple
		List<String> tuple = new ArrayList<String>();
		List<Projectile> projectiles = oppFighter.getProjectiles();
		
		for(Projectile projectile: projectiles) {
			tuple.add(this.calculateSpriteDistance(obsrvFighter, projectile));
			tuple.add(obsrvFighter.getState().getName());
			tuple.add(projectile.getState().getName());
			
			//find a reaction to our projectile
			String reaction = transitions.get(tuple);
			
			if(reaction != null) {
				obsrvFighter.setState(reaction);
				return true;
			}
		}
		
		
		
		return false;
	}
	
	/**
	 * Calculate sprite distance.
	 *
	 * @param obsrv the obsrv
	 * @param opp the opp
	 * @return the string
	 */
	private String calculateSpriteDistance(Sprite obsrv, Sprite opp) {
		String distance = AiController.FAR;
		
		if(Math.abs(obsrv.getX() - opp.getX()) < 2*obsrv.getWidth()) {
			distance = AiController.NEAR;
		} else if(Math.abs(obsrv.getX() - opp.getX()) < 3*opp.getWidth()) {
			distance = AiController.MID;
		}
		
//		logger.info(distance);
		
		return distance;
	}
}
