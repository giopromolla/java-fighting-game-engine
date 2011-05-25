package org.jfge.j2se.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import org.jfge.api.fsm.StateMachine;
import com.google.inject.Singleton;

/**
 * The Class KeyboardController.
 */
@Singleton
public final class J2SeKeyboardController implements org.jfge.spi.controller.Controller, KeyListener{
	/** The fighter. */
	public StateMachine stateMachine;
	
	/** The map. */
	public HashMap<Integer, String> key_pressed;
	public HashMap<Integer, String> key_released;
	
	
	/**
	 * Instantiates a new keyboard controller.
	 */
	public J2SeKeyboardController() {
		key_pressed = new HashMap<Integer, String>();
		key_released = new HashMap<Integer, String>();
		
		key_pressed.put(KeyEvent.VK_A, "leftPressed");
		key_pressed.put(KeyEvent.VK_D, "rightPressed");
		key_pressed.put(KeyEvent.VK_S, "downPressed");
		key_pressed.put(KeyEvent.VK_W, "upPressed");
		key_pressed.put(KeyEvent.VK_K, "xPressed");
		key_pressed.put(KeyEvent.VK_M, "aPressed");
		key_pressed.put(KeyEvent.VK_J, "yPressed");
		key_pressed.put(KeyEvent.VK_N, "bPressed");
		key_pressed.put(KeyEvent.VK_COMMA, "lrPressed");
		key_pressed.put(KeyEvent.VK_L, "lbPressed");
		
		
		key_released.put(KeyEvent.VK_A, "leftReleased");
		key_released.put(KeyEvent.VK_D, "rightReleased");
		key_released.put(KeyEvent.VK_S, "downReleased");
		key_released.put(KeyEvent.VK_W, "upReleased");
		key_released.put(KeyEvent.VK_K, "xReleased");
		key_released.put(KeyEvent.VK_M, "aReleased");
		key_released.put(KeyEvent.VK_J, "yReleased");
		key_released.put(KeyEvent.VK_N, "bReleased");
		key_released.put(KeyEvent.VK_COMMA, "lrReleased");
		key_released.put(KeyEvent.VK_L, "lbReleased");
		
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(stateMachine != null) {
			if(stateMachine.handle(key_pressed.get(arg0.getKeyCode()))) {
				arg0.consume();
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		if(stateMachine != null) {
			if(stateMachine.handle(key_released.get(arg0.getKeyCode()))){
				arg0.consume();
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	/* (non-Javadoc)
	 * @see org.jfge.controller.Controller#setFighter(org.jfge.fighter.Fighter)
	 */
	@Override
	public void setStateMachine(StateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}
	
}
