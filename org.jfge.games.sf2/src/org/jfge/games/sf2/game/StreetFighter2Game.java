package org.jfge.games.sf2.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jfge.api.arena.Arena;
import org.jfge.api.fighter.Fighter;
import org.jfge.api.game.FightingState;
import org.jfge.api.game.Game;
import org.jfge.api.game.GameFactory;
import org.jfge.api.game.GameState;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public final class StreetFighter2Game implements Provider<Game> {

	private Game game;
	
	private GameFactory gameFactory;
	
	private Map<String, Provider<Fighter>> fighterProviders;
	
	private Map<String, Provider<Arena>> arenaProviders;
	
	@Inject
	private StreetFighter2Game(GameFactory gameFactory, 
			Map<String, Provider<Fighter>> fighterProviders,
			Map<String, Provider<Arena>> arenaProviders) {
		
		this.gameFactory = gameFactory;
		this.fighterProviders = fighterProviders;
		this.arenaProviders = arenaProviders;
	}
	
	@Override
	public Game get() {
		if(game == null) {
			List<GameState> gameStates = new ArrayList<GameState>();
			
			FightingState fightingState= gameFactory.createFightingState("fighting", "fighting");
			
			fightingState.setArena(arenaProviders.get("blanka").get());
			fightingState.setFighterLeft(fighterProviders.get("ryu").get());
			fightingState.setFighterRight(fighterProviders.get("blanka").get());
			
			gameStates.add(fightingState);
			this.game = gameFactory.createGame("Street Fighter 2", gameStates, "fighting");
		}
		
		return game;
	}

}
