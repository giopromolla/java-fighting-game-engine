package org.jfge.api.game;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.jfge.api.ai.AiControllerParser;
import org.jfge.api.arena.Arena;
import org.jfge.api.collision.CollisionDetector;
import org.jfge.api.fighter.Fighter;
import org.jfge.api.fsm.State;
import org.jfge.spi.controller.Controller;
import org.jfge.spi.graphics.Graphics;
import org.jfge.spi.scene.Scene;

import com.google.inject.Provider;

public class Fighting implements FightingState {

	private String name; 
	
	private String nextState;
	
	private Game parent;
	
	private Map<String, Controller> availableControllers;
	
	private CollisionDetector collisionDetector;
	
	private Map<String,Provider<Scene>> scenes;
	
	private AiControllerParser aiControllerParser;
	
	private Arena arena;
	
	private Fighter fighterLeft;
	
	private Fighter fighterRight;
	
	public Fighting(String name, 
			String nextState,
			Map<String, Controller> availableControllers,
			CollisionDetector collisionDetector,
			Map<String,Provider<Scene>> scenes,
			AiControllerParser aiControllerParser) {
		
		this.name = name;
		this.nextState = nextState;
		this.availableControllers = availableControllers;
		this.collisionDetector = collisionDetector;
		this.scenes = scenes;
		this.aiControllerParser = aiControllerParser;
	}
	
	@Override
	public String getNextState() {
		return this.nextState;
	}

	@Override
	public void setParent(Game parent) {
		this.parent = parent;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public State getState() {
		return this;
	}

	@Override
	public boolean handle(String event) {
		return false;
	}

	@Override
	public boolean hasFinalStateReached() {
		if(arena == null)
			return false;
		
		
		return arena.hasFinalStateReached();
	}

	@Override
	public boolean nextState() {
		return this.setState(this.nextState);
	}

	@Override
	public boolean setState(String state) {
		if(parent == null)
			return false;
		
		return this.parent.setState(state);
	}

	@Override
	public boolean startState() {
		Scene loadingScene = scenes.get("loadingScreen").get();
		
		parent.getEngine().addRenderable(loadingScene);
		parent.getEngine().addUpdatable(loadingScene);
		parent.getEngine().start();
		
		collisionDetector.addFighter(fighterLeft);
		collisionDetector.addFighter(fighterRight);
		
		Controller controller1 = availableControllers.get("keyboard.controller1");
		Controller controller2 = availableControllers.get("keyboard.controller2");
		
		arena.setFighterLeft(fighterLeft);
		arena.setFighterRight(fighterRight);
		arena.setFighterLeftController(controller1);
		arena.setFighterRightController(controller2);
		
		arena.startState();
		
		
		parent.getEngine().removeRenderable(loadingScene);
		parent.getEngine().removeUpdatable(loadingScene);
		
		parent.getEngine().addRenderable(this);
		parent.getEngine().addUpdatable(this);
		
		return true;
	}

	@Override
	public void update() {
		fighterLeft.update();
		fighterRight.update();
		collisionDetector.update();
		arena.update();
	}

	@Override
	public void render(Graphics graphics) {
		/*
    	 * draw arena background should be done in renderer
    	 */
		arena.render(graphics);
		
    	/*
    	 * render fighters;
    	 */
    	fighterLeft.render(graphics);
    	fighterRight.render(graphics);
	}

	@Override
	public void setArena(Arena arena) {
		if(arena == null)
			return;
		
		this.arena = arena;
	}

	@Override
	public void setFighterLeft(Fighter left) {
		if(left == null)
			return;
		
		this.fighterLeft = left;
	}

	@Override
	public void setFighterRight(Fighter right) {
		if(right == null)
			return;
		
		this.fighterRight = right;
	}
}
