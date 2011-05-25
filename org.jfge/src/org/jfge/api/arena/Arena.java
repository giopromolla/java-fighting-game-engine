package org.jfge.api.arena;

import org.jfge.api.effect.ArenaEffect;
import org.jfge.api.fighter.Fighter;
import org.jfge.api.fsm.StateMachine;
import org.jfge.spi.controller.Controller;
import org.jfge.spi.scene.Scene;

/**
 * The Interface Arena.
 */
public interface Arena extends Scene, StateMachine<ArenaState>{
	
	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public int getTime();
	
	/**
	 * Sets the time.
	 *
	 * @return the int
	 */
	public void setTime(int time);
	
	/**
	 * Sets the fighter.
	 */
	public Fighter getFighterLeft();
	
	public void setFighterLeft(Fighter left);
	
	public Fighter getFighterRight();
	
	public void setFighterRight(Fighter right);
	
	public int getFighterLeftWins();
	
	public int getFighterRightWins();
	
	public void setFighterLeftWins(int wins);
	
	public void setFighterRightWins(int wins);
	
	public void setFigherLeftPos(int x, int y);
	
	public void setFighterRightPos(int x, int y);
	
	public void addArenaEffect(ArenaEffect arenaEffect);
	
	public void removeArenaEffet(ArenaEffect arenaEffect);
	
	public void setFighterLeftController(Controller left);
	
	public void setFighterRightController(Controller right);
	
	public Controller getFighterLeftController();
	
	public Controller getFighterRightController();
	
	public int getRound();
	
	public void setRound(int round);
	
	public boolean hasWinner();
	
	public Fighter getWinner();
}
