package org.jfge.api.network;

import org.jfge.api.network.udp.messages.ActionMessage;

public interface EventBuffer {
	public void addEvent(String event);
	public void setEventCnt(int cnt);
	public void updateCnt(int newCnt);
	public ActionMessage generateEvent();
}