package org.jfge.api.network.udp;

import java.util.List;
import java.util.Map;

import org.jfge.api.ai.AiControllerParser;
import org.jfge.api.arena.Arena;
import org.jfge.api.collision.CollisionDetector;
import org.jfge.api.engine.Engine;
import org.jfge.api.fighter.Fighter;
import org.jfge.api.game.FightingState;
import org.jfge.api.game.Game;
import org.jfge.api.game.GameFactory;
import org.jfge.api.game.GameState;
import org.jfge.api.network.AsyncReceiver;
import org.jfge.api.network.Connection;
import org.jfge.api.network.MessageParser;
import org.jfge.spi.controller.Controller;
import org.jfge.spi.scene.Scene;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public final class GameFactoryImpl implements GameFactory{

	private Provider<Engine> engineProvider;
	
	private Map<String, Provider<Fighter>> fighters;
	
	private Map<String, Controller> availableControllers;
	
	private CollisionDetector collisionDetector;
	
	private Map<String,Provider<Scene>> scenes;
	
	private Map<String,Provider<Arena>> arenas;
	
	private AiControllerParser aiControllerParser;

	private AsyncReceiver receiver;

	private MessageParser parser;

	private Connection connection;
	
	@Inject
	public GameFactoryImpl(Provider<Engine> engineProvider, 
			Map<String, Provider<Fighter>> fighters,
			Map<String, Controller> availableControllers,
			CollisionDetector collisionDetector,
			Map<String,Provider<Scene>> scenes,
			Map<String,Provider<Arena>> arenas,
			AiControllerParser aiControllerParser,
			AsyncReceiver receiver,
			MessageParser parser,
			Connection connection) {
		this.engineProvider = engineProvider;
		
		this.fighters = fighters;
		this.availableControllers = availableControllers;
		this.collisionDetector = collisionDetector;
		this.scenes = scenes;
		this.arenas = arenas;
		this.aiControllerParser = aiControllerParser;
		
		this.receiver = receiver;
		this.connection = connection;
		this.parser = parser;
	}
	
	@Override
	public Game createGame(String name, List<GameState> states,
			String startState) {
		return new GameImpl(engineProvider.get(), startState, states, startState);
	}

	@Override
	public FightingState createFightingState(String name, String nextState) {
		return new NetworkGameStateFightingImpl(nextState, nextState, availableControllers, collisionDetector, scenes, aiControllerParser, receiver, parser, connection);
	}

}
