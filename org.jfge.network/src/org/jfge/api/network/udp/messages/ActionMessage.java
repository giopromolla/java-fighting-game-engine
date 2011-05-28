package org.jfge.api.network.udp.messages;

import java.io.ByteArrayOutputStream;

public class ActionMessage extends NetworkMessage {
	public static final int TYPE = 0x02;
	
	public ActionMessage() {
		super(TYPE);
	}
	
	@Override
	protected void serializePayload(ByteArrayOutputStream byteBuffer) {
		
	}
}
