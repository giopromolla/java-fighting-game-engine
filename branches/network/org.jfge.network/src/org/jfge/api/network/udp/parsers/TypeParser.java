package org.jfge.api.network.udp.parsers;

import org.jfge.api.network.udp.messages.NetworkMessage;

public interface TypeParser {
	public int getType();
	public NetworkMessage parseMessage(byte[] data);
}
