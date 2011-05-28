package org.jfge.api.network.udp.messages;


public class AcknowledgeMessage extends NetworkMessage {
	public static final int TYPE = 0x00;
	
	public AcknowledgeMessage() {
		super(TYPE);
	}

	
 
}
