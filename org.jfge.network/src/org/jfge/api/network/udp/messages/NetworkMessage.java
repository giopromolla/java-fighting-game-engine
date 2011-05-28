package org.jfge.api.network.udp.messages;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.jfge.api.misc.ByteOperations;

public abstract class NetworkMessage {
	public int seqNmbr;
	public int ackSeqNmbr;
	public int type; 
	
	public NetworkMessage(int type) {
		this.type = type;
	}
	
	public byte[] serialize() {		
		ByteArrayOutputStream bos = new ByteArrayOutputStream(512);
		try {
			bos.write(ByteOperations.intToByteArray(type));
			bos.write(ByteOperations.intToByteArray(seqNmbr));
			bos.write(ByteOperations.intToByteArray(ackSeqNmbr));			
		} catch (IOException e) {		
			e.printStackTrace();
		}
		serializePayload(bos);
		return bos.toByteArray();
	}	
	
	protected void serializePayload(ByteArrayOutputStream byteBuffer) { };
}
