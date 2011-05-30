package org.jfge.api.network;

import org.jfge.api.network.udp.messages.NetworkMessage;
import org.jfge.api.network.udp.parsers.TypeParser;

public interface MessageParser {
	public void registerTypeParser(TypeParser parser);
	public NetworkMessage parseMessage(byte[] data);
}
