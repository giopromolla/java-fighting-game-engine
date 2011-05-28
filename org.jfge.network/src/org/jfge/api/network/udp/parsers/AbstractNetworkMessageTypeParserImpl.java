package org.jfge.api.network.udp.parsers;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.jfge.api.misc.ByteOperations;
import org.jfge.api.network.udp.messages.NetworkMessage;

public abstract class AbstractNetworkMessageTypeParserImpl implements TypeParser {

	@Override
	public NetworkMessage parseMessage(byte[] data) {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		byte[] tmp = new byte[4];
		try {
			bis.read(tmp);
			int type = ByteOperations.byteArrayToInt(tmp);
			bis.read(tmp);
			int seqNmbr = ByteOperations.byteArrayToInt(tmp);
			bis.read(tmp);
			int ackSeq = ByteOperations.byteArrayToInt(tmp);
			return createMessage(seqNmbr, type, ackSeq, bis);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} 
	}
	
	protected abstract NetworkMessage createMessage(int seqNmbr, int type, int ackSeq, ByteArrayInputStream dataStream);
}
