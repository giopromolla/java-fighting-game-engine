package org.jfge.api.network.udp.messages;

public final class SequenceResetMessage extends NetworkMessage {
	public final static int TYPE = 0x01;
	
	public SequenceResetMessage() {
		super(TYPE);
	}

}
