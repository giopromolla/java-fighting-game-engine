package org.jfge.api.network.udp.parsers;

import java.io.ByteArrayInputStream;

import org.jfge.api.network.udp.messages.NetworkMessage;
import org.jfge.api.network.udp.messages.SequenceResetMessage;

public class SequenceResetNetworkMessageParserImpl extends AbstractNetworkMessageTypeParserImpl {

	@Override
	public int getType() {
		return SequenceResetMessage.TYPE;
	}

	@Override
	protected NetworkMessage createMessage(int seqNmbr, int type, int ackSeq,
			ByteArrayInputStream dataStream) {
		SequenceResetMessage srm = new SequenceResetMessage();
		srm.ackSeqNmbr = ackSeq;
		srm.type = type;
		srm.seqNmbr = seqNmbr;
		return srm;
	}

}
