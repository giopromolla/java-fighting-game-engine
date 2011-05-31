package org.jfge.api.network.udp.parsers;

import java.util.HashMap;
import java.util.Map;

import org.jfge.api.misc.ByteOperations;
import org.jfge.api.network.MessageParser;
import org.jfge.api.network.udp.messages.NetworkMessage;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class MessageParserImpl implements MessageParser {
	private Map<Integer, TypeParser> parsers = new HashMap<Integer, TypeParser>();
	
	@Inject
	public MessageParserImpl(Map<Integer, TypeParser> parsers) {
		this.parsers = parsers;
	}
	
	@Override
	public NetworkMessage parseMessage(byte[] data) {
		int type = ByteOperations.byteArrayToInt(data);
		TypeParser parser = parsers.get(type);
		if (parser == null) {
			throw new RuntimeException("No parser for type "+type);
		}
		return parser.parseMessage(data);
	}
}
