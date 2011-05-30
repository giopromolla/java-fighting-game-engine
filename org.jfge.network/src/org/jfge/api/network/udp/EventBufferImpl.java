package org.jfge.api.network.udp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jfge.api.network.EventBuffer;
import org.jfge.api.network.udp.messages.ActionMessage;

public class EventBufferImpl implements EventBuffer{
	private ActionMessage msg = new ActionMessage(); 
	
	private Map<Integer, List<String>> cntToEvents = new HashMap<Integer, List<String>>();
	
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
		for(int cnt : cntToEvents.keySet()) {
			//TODO: Go ahead here
		}
	}

	@Override
	public ActionMessage generateEvent() {
		// TODO Auto-generated method stub
		return null;
	}

}
