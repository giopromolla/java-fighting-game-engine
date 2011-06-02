package org.jfge.api.network.udp.parsers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.jfge.api.misc.ByteOperations;
import org.jfge.api.network.udp.messages.ActionMessage;
import org.jfge.api.network.udp.messages.NetworkMessage;

public class ActionMessageParserImpl extends
		AbstractNetworkMessageTypeParserImpl {
	private ActionMessage am = new ActionMessage();
	private int lastCnt = 0;

	@Override
	public int getType() {
		return ActionMessage.TYPE;
	}

	@Override
	protected NetworkMessage createMessage(int seqNmbr, int type, int ackSeq,
			ByteArrayInputStream dataStream) { 
		if (lastCnt > seqNmbr) {
			System.out.println("Discarded pckg: "+seqNmbr);
			return null;
		}
		lastCnt = seqNmbr;
		am.seqNmbr = seqNmbr;
		am.type = type;
		am.ackSeqNmbr = ackSeq;
		am.actions = new HashMap<Integer, List<String>>();
		try {
			int actionCnt = dataStream.read();
			byte[] tmp = new byte[4]; 
			for (int i = 0; i < actionCnt; i++) {				
				dataStream.read(tmp); 
				int key = ByteOperations.byteArrayToInt(tmp);
				int cnt = dataStream.read();
				List<String> events = am.actions.get(key);
				if (events == null) {
					events = new LinkedList<String>();
					if (key >= lastCnt) {
						am.actions.put(key, events);
					}
				}
				for (int j = 0; j < cnt; j++) {
					int strlen = dataStream.read();
					if (strlen < 0) {
						return null;
					}
					byte[] str = new byte[strlen];

					dataStream.read(str);
					if (key >= lastCnt) {
						String event = new String(str, "UTF-8");
						events.add(event);
					} 
				}
			}
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}

		return am;
	}  
}
