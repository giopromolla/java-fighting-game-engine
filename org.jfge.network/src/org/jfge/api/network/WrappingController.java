package org.jfge.api.network;

import java.util.ArrayList;

import org.jfge.api.fsm.StateMachine;
import org.jfge.spi.controller.Controller;

public class WrappingController implements Controller {
	private WrappingStateMachine sm;
	private ArrayList<StateMachineEventListener> listeners = new ArrayList<StateMachineEventListener>();
	
	private Controller wrappedController;
	
	public WrappingController(Controller toWrap) {
		this.wrappedController = toWrap;
	}
	@Override
	public void setStateMachine(StateMachine stateMachine) {
		this.sm = new WrappingStateMachine(stateMachine);
		updateWrappingStateMachine();
		wrappedController.setStateMachine(sm);	
	}
	
	public WrappingStateMachine getMachine() {
		return this.sm;
	}
	
	public void addStateMachineEventListener(StateMachineEventListener listener) {
		this.listeners.add(listener);
	}
	
	public void removeStateMachineEventListener(StateMachineEventListener listener) {
		this.listeners.remove(listener);
	}
	
	private void updateWrappingStateMachine() {
		if (sm != null) {
			for (StateMachineEventListener lis : listeners) {
				sm.addStateMachineEventListener(lis);
			}
		}
	}

}
