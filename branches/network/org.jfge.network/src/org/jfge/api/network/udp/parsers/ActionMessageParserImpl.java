package org.jfge.api.network.udp.parsers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.jfge.api.network.udp.messages.ActionMessage;
import org.jfge.api.network.udp.messages.NetworkMessage;

public class ActionMessageParserImpl extends
		AbstractNetworkMessageTypeParserImpl {
	private ActionMessage am = new ActionMessage();
	private int lastCnt;
	
	@Override
	public int getType() {
		return ActionMessage.TYPE;
	}

	@Override
	protected NetworkMessage createMessage(int seqNmbr, int type, int ackSeq,
			ByteArrayInputStream dataStream) {
		// TODO Auto-generated method stub
		if (lastCnt > seqNmbr) {
			return null;
		}
		lastCnt = seqNmbr;
		am.seqNmbr = seqNmbr;
		am.type = type;
		am.ackSeqNmbr = ackSeq;
		am.actions = new HashMap<Integer, List<String>>();
		lastCnt = seqNmbr;
		int actionCnt = dataStream.read();
		for(int i=0; i < actionCnt; i++) {
			int key = dataStream.read();
			int cnt = dataStream.read();
			List<String> events = am.actions.get(key);
			if (events == null) {
				events = new LinkedList<String>();
				if (key >= lastCnt) { 
					am.actions.put(key, events);
				}
			}
			for(int j=0; j < cnt; j++) {
				int strlen = dataStream.read();
				byte[] str = new byte[strlen];
				try {
					dataStream.read(str);
					if (key >= lastCnt) {
						String event = new String(str, "UTF-8");
						events.add(event);
					}
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
		
		return am;
	}
	
	/*
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
	 */

}
