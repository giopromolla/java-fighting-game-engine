package org.jfge.api.network;

public interface MessageSender {
	public void sendActionMessage(int cnt);
	public void sendAcknowledgeMessage(int cnt);
}
