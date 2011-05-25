package org.jfge.api.game;

import java.util.List;

public interface GameFactory {

	public Game createGame(String name, List<GameState> states, String startState);

	public FightingState createFightingState(String name, String nextState);
}
