package org.jfge.api.network;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.jfge.api.fsm.State;
import org.jfge.api.fsm.StateMachine;

public class WrappingStateMachine implements StateMachine {
	private StateMachine machine;
	private ArrayList<StateMachineEventListener> listeners = new ArrayList<StateMachineEventListener>();
	
	public WrappingStateMachine(StateMachine machine) {
		this.machine = machine;
	}
	@Override
	public boolean setState(String state) {
		return machine.setState(state);
	}

	@Override
	public boolean handle(String event) {
		if (event != null) {
			System.out.println(event);
			this.fireHandleInvoked(event);
		}
		if (machine != null) {
			return machine.handle(event);
		}
		return false;
	}

	@Override
	public State getState() {		
		return machine.getState();
	}

	@Override
	public boolean nextState() {
		return machine.nextState();
	}

	@Override
	public boolean startState() {
		return machine.startState();
	}

	@Override
	public String getName() {
		return machine.getName();
	}

	@Override
	public boolean hasFinalStateReached() {
		return machine.hasFinalStateReached();
	}
	
	public void addStateMachineEventListener(StateMachineEventListener listener) {
		this.listeners.add(listener);
	}
	
	public void removeStateMachineEventListener(StateMachineEventListener listener) {
		this.listeners.remove(listener);
	}
	
	private void fireHandleInvoked(String event) {
		for (StateMachineEventListener smel : this.listeners) {
			 smel.handle(event);
		}
	}
}
