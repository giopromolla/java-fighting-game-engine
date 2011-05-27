package org.jfge.games.sfvsmk2.game;

import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.jfge.api.ai.AiController;
import org.jfge.api.ai.AiControllerParser;
import org.jfge.api.arena.Arena;
import org.jfge.api.arena.ArenaFactory;
import org.jfge.api.collision.CollisionDetector;
import org.jfge.api.engine.Engine;
import org.jfge.api.fighter.Fighter;
import org.jfge.api.game.Game;
import org.jfge.api.game.GameState;
import org.jfge.spi.controller.Controller;
import org.jfge.spi.graphics.Graphics;
import org.jfge.spi.graphics.GraphicsFactory;
import org.jfge.spi.render.ArenaRenderer;
import org.jfge.spi.scene.Scene;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

/**
 * The Class GameImpl.
 */
@Singleton
public final class SfVsMk2Impl implements Game {

	/** The logger. */
	private final Logger logger;
	
	/** The controller. */
	private Controller controller;
	
	/** The engine. */
	private Engine engine;
	
	/** The fighter1. */
	private Fighter fighter1;
	
	/** The fighter2. */
	private Fighter fighter2;
	
	/** The collision detector. */
	private CollisionDetector collisionDetector;
	
	/** The ai controller parser. */
	private AiControllerParser aiControllerParser;
	
	/** The ai controller. */
	private AiController aiController;
	
	/** The arena. */
	private Arena arena;
	
	/**
	 * Instantiates a new game impl.
	 *
	 * @param logger the logger
	 * @param imageLoader the image loader
	 * @param engine the engine
	 * @param fighters the fighters
	 * @param availableControllers the available controllers
	 * @param collisionDetector the collision detector
	 * @param arenaRenderer the arena renderer
	 * @param loadingScene the loading scene
	 * @param aiControllerParser the ai controller parser
	 * @throws Exception the exception
	 */
	@Inject
	public SfVsMk2Impl(Logger logger, 
			Engine engine, 
			Map<String, Provider<Fighter>> fighters,
			Set<Controller> availableControllers,
			CollisionDetector collisionDetector,
			ArenaRenderer arenaRenderer,
			Map<String,Provider<Scene>> scenes,
			Map<String,Provider<Arena>> arenas,
			AiControllerParser aiControllerParser, ArenaFactory arenaFactory) throws Exception {
			
			this.logger = logger;
			this.engine = engine;
			this.controller = availableControllers.iterator().next();
			this.collisionDetector = collisionDetector;
			this.aiControllerParser = aiControllerParser;
			this.aiController = aiControllerParser.parseFromXmlFile("/org/jfge/games/sfvsmk2/ai/sfvsmk2Ai.xml");
			
			Scene loadingScene = scenes.get("loadingScreen").get();
			
			engine.addRenderable(loadingScene);
			engine.addUpdatable(loadingScene);
			engine.start();
			
			fighter1 = fighters.get("liuKang").get();
			fighter2 = fighters.get("ryu").get();
			
			collisionDetector.addFighter(fighter1);
			collisionDetector.addFighter(fighter2);
			
			this.arena = arenas.get("sfvsmk2").get();
			
			arena.setFighterLeft(fighter1);
			arena.setFighterRight(fighter2);
			arena.setFighterLeftController(controller);
			
			engine.removeRenderable(loadingScene);
			engine.removeUpdatable(loadingScene);
			
			engine.addRenderable(this);
			engine.addUpdatable(this);
	}
	
	/* (non-Javadoc)
	 * @see org.jfge.game.Game#end()
	 */
	@Override
	public void end() {
		this.engine.stop();
	}

	/* (non-Javadoc)
	 * @see org.jfge.game.Game#start()
	 */
	@Override
	public void start() {
		this.engine.start();
	}

	/* (non-Javadoc)
	 * @see org.jfge.engine.Updatable#update()
	 */
	@Override
	public void update() {
		
//		aiController.handle(fighter2, fighter1);
		
		fighter1.update();
		fighter2.update();
		collisionDetector.update();
		arena.update();
	}

	/* (non-Javadoc)
	 * @see org.jfge.engine.Renderable#render(java.awt.Graphics)
	 */
	@Override
	public void render(Graphics graphics) {
		/*
    	 * draw arena background should be done in renderer
    	 */
		arena.render(graphics);
		
    	/*
    	 * render fighters;
    	 */
    	fighter1.render(graphics);
    	fighter2.render(graphics);
    }

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameState getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean handle(String event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasFinalStateReached() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean nextState() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setState(String state) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean startState() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Engine getEngine() {
		// TODO Auto-generated method stub
		return null;
	}
}
