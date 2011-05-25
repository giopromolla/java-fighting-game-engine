package org.jfge.api.game;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jfge.api.ai.AiControllerParser;
import org.jfge.api.arena.Arena;
import org.jfge.api.collision.CollisionDetector;
import org.jfge.api.engine.Engine;
import org.jfge.api.fighter.Fighter;
import org.jfge.spi.controller.Controller;
import org.jfge.spi.scene.Scene;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public final class GameFactoryImpl implements GameFactory{

	private Provider<Engine> engineProvider;
	
	private Map<String, Provider<Fighter>> fighters;
	
	private Set<Controller> availableControllers;
	
	private CollisionDetector collisionDetector;
	
	private Map<String,Provider<Scene>> scenes;
	
	private Map<String,Provider<Arena>> arenas;
	
	private AiControllerParser aiControllerParser;
	
	@Inject
	public GameFactoryImpl(Provider<Engine> engineProvider, 
			Map<String, Provider<Fighter>> fighters,
			Set<Controller> availableControllers,
			CollisionDetector collisionDetector,
			Map<String,Provider<Scene>> scenes,
			Map<String,Provider<Arena>> arenas,
			AiControllerParser aiControllerParser) {
		this.engineProvider = engineProvider;
		
		this.fighters = fighters;
		this.availableControllers = availableControllers;
		this.collisionDetector = collisionDetector;
		this.scenes = scenes;
		this.arenas = arenas;
		this.aiControllerParser = aiControllerParser;
	}
	
	@Override
	public Game createGame(String name, List<GameState> states,
			String startState) {
		return new GameImpl(engineProvider.get(), startState, states, startState);
	}

	@Override
	public FightingState createFightingState(String name, String nextState) {
		return new Fighting(nextState, nextState, availableControllers, collisionDetector, scenes, aiControllerParser);
	}

}
