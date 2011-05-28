package org.jfge.j2se.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import org.jfge.api.fsm.StateMachine;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

// TODO: Auto-generated Javadoc
/**
 * The Class KeyboardController.
 */
@Singleton
public final class J2SeKeyboardController2 implements org.jfge.spi.controller.Controller, KeyListener{
	/** The fighter. */
	private StateMachine stateMachine;
	
	/** The map. */
	private HashMap<Integer, String> key_pressed;
	
	/** The key_released. */
	private HashMap<Integer, String> key_released;
	
	/** The left. */
	private final int left;
	
	/** The right. */
	private final int right;
	
	/** The up. */
	private final int up;
	
	/** The down. */
	private final int down;
	
	/** The x. */
	private final int x;
	
	/** The y. */
	private final int y;
	
	/** The a. */
	private final int a;
	
	/** The b. */
	private final int b;
	
	/** The lr. */
	private final int lr;
	
	/** The lb. */
	private final int lb;
	
	/** The start. */
	private final int start;
	
	/** The select. */
	private final int select;
	
	/**
	 * Instantiates a new keyboard controller.
	 *
	 * @param left the left
	 * @param right the right
	 * @param up the up
	 * @param down the down
	 * @param x the x
	 * @param y the y
	 * @param a the a
	 * @param b the b
	 * @param lr the lr
	 * @param lb the lb
	 * @param start the start
	 * @param select the select
	 */
	@Inject
	public J2SeKeyboardController2(
			@Named("keyboard.controller2.left") int left,
			@Named("keyboard.controller2.right") int right,
			@Named("keyboard.controller2.up") int up,
			@Named("keyboard.controller2.down") int down,
			@Named("keyboard.controller2.x") int x,
			@Named("keyboard.controller2.y") int y,
			@Named("keyboard.controller2.a") int a,
			@Named("keyboard.controller2.b") int b,
			@Named("keyboard.controller2.lr") int lr,
			@Named("keyboard.controller2.lb") int lb,
			@Named("keyboard.controller2.start") int start, 
			@Named("keyboard.controller2.select") int select
	) {
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
		this.x = x;
		this.y = y;
		this.a = a;
		this.b = b;
		this.lr = lr;
		this.lb = lb;
		this.start = start;
		this.select = select;
		
		key_pressed = new HashMap<Integer, String>();
		key_released = new HashMap<Integer, String>();
		
		key_pressed.put(left, "leftPressed");
		key_pressed.put(right, "rightPressed");
		key_pressed.put(down, "downPressed");
		key_pressed.put(up, "upPressed");
		key_pressed.put(x, "xPressed");
		key_pressed.put(a, "aPressed");
		key_pressed.put(y, "yPressed");
		key_pressed.put(b, "bPressed");
		key_pressed.put(lr, "lrPressed");
		key_pressed.put(lb, "lbPressed");
		
		
		key_released.put(left, "leftReleased");
		key_released.put(right, "rightReleased");
		key_released.put(down, "downReleased");
		key_released.put(up, "upReleased");
		key_released.put(x, "xReleased");
		key_released.put(a, "aReleased");
		key_released.put(y, "yReleased");
		key_released.put(b, "bReleased");
		key_released.put(lr, "lrReleased");
		key_released.put(lb, "lbReleased");
		
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	/**
	 * Key pressed.
	 *
	 * @param arg0 the arg0
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
	/**
	 * Key released.
	 *
	 * @param arg0 the arg0
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
	/**
	 * Key typed.
	 *
	 * @param arg0 the arg0
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
