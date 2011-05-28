package org.jfge.api.network.udp.parsers;

import java.io.ByteArrayInputStream;

import org.jfge.api.network.udp.messages.AcknowledgeMessage;
import org.jfge.api.network.udp.messages.NetworkMessage;

public class AcknowledgeMessageParserImpl extends
		AbstractNetworkMessageTypeParserImpl {

	@Override
	public int getType() {
		return AcknowledgeMessage.TYPE;
	}

	@Override
	protected NetworkMessage createMessage(int seqNmbr, int type, int ackSeq,
			ByteArrayInputStream dataStream) {
		AcknowledgeMessage srm = new AcknowledgeMessage();
		srm.ackSeqNmbr = ackSeq;
		srm.type = type;
		srm.seqNmbr = seqNmbr;
		return srm;
	}

}
