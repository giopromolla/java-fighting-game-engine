package org.jfge.api.network;

import java.util.EventListener;

public interface StateMachineEventListener extends EventListener{
	public void handle(String event);
	public void setState(String state);
	public void nextState();
	public void startState();	
}
