package org.jfge.api.collision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.jfge.api.fsm.StateMachine;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public final class CollisionHandlerImpl implements CollisionHandler {

	private Map<List<String>,String> collisionMap;
	
	@Inject
	public CollisionHandlerImpl(@Assisted Map<List<String>,String> collisionMap) {
		this.collisionMap = collisionMap;
	}
	
	@Override
	public synchronized boolean handleCollision(StateMachine fsm, StateMachine colFsm) {
		/*
		 * create new tuple
		 */
		String fsmState = fsm.getState().getName();
		String colFsmState = colFsm.getState().getName();
		
		List<String> tuple = new ArrayList<String>();
		tuple.add(fsmState);
		tuple.add(colFsmState);
		
		/*
		 * find a reaction to our collision sprite
		 */
		String reaction = collisionMap.get(tuple);
		
		if(reaction != null) {
			colFsm.setState(reaction);
		}
		
		/*
		 * check if there's a drawback to react on
		 */
		Collections.reverse(tuple);
		String drawBack = collisionMap.get(tuple);
		
		if(drawBack != null) {
			fsm.setState(drawBack);
		}
		
		/*
		 * returning false if there's no reaction 
		 */
		if(reaction == null && drawBack == null) {
			return false;
		}
		
		/*
		 * returning true 
		 */
		return true;
	}
}
