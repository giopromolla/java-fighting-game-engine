package org.jfge.api.network.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;

import org.jfge.api.ai.AiControllerParser;
import org.jfge.api.arena.Arena;
import org.jfge.api.collision.CollisionDetector;
import org.jfge.api.fighter.Fighter;
import org.jfge.api.fsm.State;
import org.jfge.api.game.FightingState;
import org.jfge.api.game.Game;
import org.jfge.api.network.AsyncReceiver;
import org.jfge.api.network.Connection;
import org.jfge.api.network.ConnectionFactory;
import org.jfge.api.network.EventBuffer;
import org.jfge.api.network.MessageParser;
import org.jfge.api.network.MessageSender;
import org.jfge.api.network.NetworkGameState;
import org.jfge.api.network.StateMachineEventAdapter;
import org.jfge.api.network.StateMachineEventListener;
import org.jfge.api.network.WrappingController;
import org.jfge.api.network.udp.messages.AcknowledgeMessage;
import org.jfge.api.network.udp.messages.ActionMessage;
import org.jfge.api.network.udp.messages.NetworkMessage;
import org.jfge.api.network.udp.messages.SequenceResetMessage;
import org.jfge.spi.controller.Controller;
import org.jfge.spi.graphics.Graphics;
import org.jfge.spi.scene.Scene;

import sun.security.action.GetLongAction;

import com.google.inject.Provider;

public class NetworkGameStateFightingImpl implements NetworkGameState, FightingState {

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

	private int cnt;

	private Connection connection;
	
	private AsyncReceiver receiver;

	private MessageParser parser;

	private MessageSender sender;

	private EventBuffer buffer;

	private boolean left;
	
	public NetworkGameStateFightingImpl(String name, 
			String nextState,
			Map<String, Controller> availableControllers,
			CollisionDetector collisionDetector,
			Map<String,Provider<Scene>> scenes,
			AiControllerParser aiControllerParser,
			AsyncReceiver receiver,
			MessageParser parser,
			Connection connection,
			MessageSender sender,
			EventBuffer buffer
			) {
		this.cnt = 0;
		
		this.name = name;
		this.nextState = nextState;
		this.availableControllers = availableControllers;
		this.collisionDetector = collisionDetector;
		this.scenes = scenes;
		this.aiControllerParser = aiControllerParser;
		
		this.receiver = receiver;
		this.connection = connection;
		this.parser = parser;
		this.sender = sender;
		this.buffer = buffer;
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
		if (connection == null || !connection.isConnected()) {
			return false;
		}
		
		Scene loadingScene = scenes.get("loadingScreen").get();
		
		parent.getEngine().addRenderable(loadingScene);
		parent.getEngine().addUpdatable(loadingScene);
		parent.getEngine().start();
		
		collisionDetector.addFighter(fighterLeft);
		collisionDetector.addFighter(fighterRight);
		
		String controllerName = this.availableControllers.keySet().iterator().next();
		Controller controller = this.availableControllers.get(controllerName);
		WrappingController wrappedController = new WrappingController(controller); 
		wrappedController.addStateMachineEventListener(buffer);
		
		arena.setFighterLeft(fighterLeft);
		arena.setFighterRight(fighterRight);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("(l)eft or (r)ight?");
		
		try {
			this.left = br.readLine().equals("l");
			if (left)
				arena.setFighterLeftController(wrappedController);
			else
				arena.setFighterRightController(wrappedController);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
			
		arena.startState(); 
		
		parent.getEngine().removeRenderable(loadingScene);
		parent.getEngine().removeUpdatable(loadingScene);
		
		parent.getEngine().addRenderable(this);
		parent.getEngine().addUpdatable(this);
		
		return true;
	}

	@Override
	public void update() {
		cnt++;
		handleReceivedPackages();		
		fighterLeft.update();
		fighterRight.update();
		collisionDetector.update();
		arena.update();
		sender.sendActionMessage(cnt);
	}

	private void handleReceivedPackages() {		
		byte[] result = this.receiver.popMessage();
		while (result != null) {
			NetworkMessage message = parser.parseMessage(result);
			
			if(message!=null) {
				switch(message.type) {
					case AcknowledgeMessage.TYPE:					
						break;
					case SequenceResetMessage.TYPE:
						break;
					case ActionMessage.TYPE:
						ActionMessage am = (ActionMessage)message;
						//TODO: Don't inject all messages, only the ones that are currently needed
						sender.sendAcknowledgeMessage(am.seqNmbr);
						Fighter other = left ? arena.getFighterRight() : arena.getFighterLeft();
						Integer prevNmbr = null;
						for(Integer k : am.actions.keySet()) {
							while(prevNmbr != null && prevNmbr < k) {
								other.update();
							}
							for (String act : am.actions.get(k)) {															
								other.handle(act);								
							}
							prevNmbr = k;
						}						
						while(this.cnt < am.seqNmbr) {
							update();
						}
						break;
				}
			}
			result = this.receiver.popMessage();
		}
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

	@Override
	public int getUpdateCount() {
		return cnt;
	}

	@Override
	public void setConnection(Connection con) {
		this.connection = con;
		receiver.setConnection(con);
	}
}
