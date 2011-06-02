package org.jfge.api.network.udp;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jfge.api.network.EventBuffer;
import org.jfge.api.network.udp.messages.ActionMessage;

import com.google.inject.Singleton;

@Singleton
public class EventBufferImpl implements EventBuffer{
	private ActionMessage msg = new ActionMessage(); 
	
	private Map<Integer, List<String>> cntToEvents = Collections.synchronizedMap(new HashMap<Integer, List<String>>());
	
	@Override
	public void addEvent(String event) { 		
		List<String> events = cntToEvents.get(0);
		if (events == null) {
			events = new LinkedList<String>();
			cntToEvents.put(0, events);
		}		
		events.add(event);		
	}

	@Override
	public void setEventCnt(int cnt) {
		List<String> events = cntToEvents.get(0);
		List<String> newEvents = cntToEvents.get(cnt);
		if (events != null) {
			if (newEvents == null) {
				newEvents = events;
				cntToEvents.remove(0);
				cntToEvents.put(cnt, newEvents);
			}  else {
				newEvents.addAll(events);
				events.clear();
			}
		}
	}

	@Override
	public void updateCnt(int newCnt) {
		Map<Integer, List<String>> newEvents = new HashMap<Integer, List<String>>();
		for(int cnt : cntToEvents.keySet()) {
			if (cnt >= newCnt) {
				newEvents.put(cnt, cntToEvents.get(cnt));
			}
		}
		cntToEvents = newEvents;
	}

	@Override
	public ActionMessage generateEvent(int cnt) {
		msg.actions = new HashMap<Integer, List<String>>();
		for (Integer i : cntToEvents.keySet()) {
			List<String> actions = cntToEvents.get(i);
			if (actions != null && i <= cnt) {
				msg.actions.put(i, actions);
			}
		}
		msg.seqNmbr = cnt;
		return msg;
	}

	private String previous = new String();
	
	@Override
	public void handle(String event) {
		if(!previous.equals(event)) {
			previous = event;
			this.addEvent(event);
		}			
	}

	@Override
	public void setState(String state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nextState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startState() {
		// TODO Auto-generated method stub
		
	}

}
