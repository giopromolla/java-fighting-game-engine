package org.jfge.api.network.udp.parsers;

import org.jfge.api.network.udp.messages.NetworkMessage;

public interface MessageParser {
	public void registerTypeParser(TypeParser parser);
	public NetworkMessage parseMessage(byte[] data);
}
