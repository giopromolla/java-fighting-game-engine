package org.jfge.android.controller;

import java.util.HashMap;
import java.util.logging.Logger;

import org.jfge.api.fsm.StateMachine;
import org.jfge.spi.controller.Controller;

import android.view.KeyEvent;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * The Class AndroidKeyboardController.
 */
@Singleton
public class AndroidKeyboardController implements Controller, android.view.KeyEvent.Callback {

	/** The fighter. */
	public StateMachine stateMachine;
	
	/** The map. */
	public HashMap<Integer, String> key_pressed;
	
	/** The key_released. */
	public HashMap<Integer, String> key_released;
	
	/** The logger. */
	private final Logger logger;
	
	/**
	 * Instantiates a new android keyboard controller.
	 *
	 * @param logger the logger
	 */
	@Inject
	public AndroidKeyboardController(Logger logger) {
		this.logger = logger;
		
		key_pressed = new HashMap<Integer, String>();
		key_released = new HashMap<Integer, String>();
		
		key_pressed.put(KeyEvent.KEYCODE_A, "leftPressed");
		key_pressed.put(KeyEvent.KEYCODE_D, "rightPressed");
		key_pressed.put(KeyEvent.KEYCODE_S, "downPressed");
		key_pressed.put(KeyEvent.KEYCODE_W, "upPressed");
		key_pressed.put(KeyEvent.KEYCODE_J, "xPressed");
		key_pressed.put(KeyEvent.KEYCODE_N, "aPressed");
		key_pressed.put(KeyEvent.KEYCODE_K, "yPressed");
		key_pressed.put(KeyEvent.KEYCODE_M, "bPressed");
		key_pressed.put(KeyEvent.KEYCODE_SPACE, "lrPressed");
	
		key_released.put(KeyEvent.KEYCODE_A, "leftReleased");
		key_released.put(KeyEvent.KEYCODE_D, "rightReleased");
		key_released.put(KeyEvent.KEYCODE_S, "downReleased");
		key_released.put(KeyEvent.KEYCODE_W, "upReleased");
		key_released.put(KeyEvent.KEYCODE_J, "xReleased");
		key_released.put(KeyEvent.KEYCODE_N, "aReleased");
		key_released.put(KeyEvent.KEYCODE_K, "yReleased");
		key_released.put(KeyEvent.KEYCODE_M, "bReleased");
		key_released.put(KeyEvent.KEYCODE_SPACE, "lrReleased");
	}
	
	/* (non-Javadoc)
	 * @see org.jfge.controller.Controller#setStateMachine(org.jfge.fsm.StateMachine)
	 */
	@Override
	public void setStateMachine(StateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}

	/* (non-Javadoc)
	 * @see android.view.KeyEvent.Callback#onKeyDown(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(this.stateMachine == null)
			return false;
		
		this.stateMachine.handle(key_pressed.get(event.getKeyCode()));
		return true;
	}

	/* (non-Javadoc)
	 * @see android.view.KeyEvent.Callback#onKeyLongPress(int, android.view.KeyEvent)
	 */
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		return false;
	}

	/* (non-Javadoc)
	 * @see android.view.KeyEvent.Callback#onKeyMultiple(int, int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyMultiple(int keyCode, int count, KeyEvent event) {
		return false;
	}

	/* (non-Javadoc)
	 * @see android.view.KeyEvent.Callback#onKeyUp(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if(this.stateMachine == null)
			return false;
		
		return this.stateMachine.handle(key_released.get(event.getKeyCode()));
	}
}
