package org.jfge.api.network.udp.messages;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public class ActionMessage extends NetworkMessage {
	public static final int TYPE = 0x02;
	public Map<Integer, List<String>> actions;	
	
	public ActionMessage() {
		super(TYPE);
	}
	
	@Override
	protected void serializePayload(ByteArrayOutputStream byteBuffer) {
		int actionCnt = actions.keySet().size();
		byteBuffer.write(actionCnt); //write byte
		for (Integer current : actions.keySet()) {
			List<String> acts = actions.get(current);			
			byteBuffer.write(current);
			byteBuffer.write(acts.size());			
			for (String ac : acts) {
				
				try {
					byte[] strByte = ac.getBytes("UTF-8");
					byteBuffer.write(strByte.length);
					byteBuffer.write(strByte);					
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}								
			}
		}
	}
}
