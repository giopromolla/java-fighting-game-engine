package org.jfge.j2se.controller;

import java.util.logging.Logger;

import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;

import org.jfge.api.fsm.StateMachine;
import org.jfge.spi.controller.Controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * The Class XboxController.
 */
@Singleton
public final class J2SeGamePadController implements Controller, Runnable {
	
	/** The logger. */
	private final Logger logger;
	
	/** The controller. */
	private net.java.games.input.Controller controller;

	/** The thread. */
	private Thread thread;
	
	/** The ups. */
	private int ups;
	
	/** The fighter. */
	private StateMachine stateMachine;
	
	/**
	 * Instantiates a new xbox controller.
	 *
	 * @param logger the logger
	 */
	@Inject
	public J2SeGamePadController(Logger logger) {
		this.logger = logger;
		
		/*
		 * initalize xbox 360 controller
		 */
		net.java.games.input.Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
		
		for(net.java.games.input.Controller controller: controllers) {
			if(controller.getType() == net.java.games.input.Controller.Type.GAMEPAD && 
					controller.getName().contains("X-Box")){
				this.controller = controller;
				logger.info("found " + controller.getName());
			}
		}
		
		/*
		 * start polling controller
		 */
		logger.info("start polling " + controller.getName());
		thread = new Thread(this);
		thread.start();
	}

	/* (non-Javadoc)
	 * @see org.jfge.controller.Controller#setFighter(org.jfge.fighter.Fighter)
	 */
	@Override
	public void setStateMachine(StateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		EventQueue queue;
		Event event = new Event();
		
		
		while(true) {
			controller.poll();
			
			queue = controller.getEventQueue();
			
			while(queue.getNextEvent(event)) {
				stateMachine.handle(event.getComponent().getName());
			}
			
			/*
			 * sleeping till next poll
			 */
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
