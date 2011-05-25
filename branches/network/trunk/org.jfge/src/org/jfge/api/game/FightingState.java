package org.jfge.api.game;

import org.jfge.api.arena.Arena;
import org.jfge.api.fighter.Fighter;

public interface FightingState extends GameState {
	
	public void setFighterLeft(Fighter left);
	
	public void setFighterRight(Fighter right);
	
	public void setArena(Arena arena);
}
