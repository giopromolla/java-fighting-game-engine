package org.jfge.api.network.udp;

import org.jfge.api.network.Connection;
import org.jfge.api.network.EventBuffer;
import org.jfge.api.network.MessageSender;
import org.jfge.api.network.udp.messages.AcknowledgeMessage;
import org.jfge.api.network.udp.messages.ActionMessage;

import com.google.inject.Inject;
import com.google.inject.Singleton;
@Singleton
public class MessageSenderImpl implements MessageSender {

	private EventBuffer buffer;
	private Connection connection;

	@Inject
	public MessageSenderImpl(Connection con, EventBuffer buffer) {
		this.buffer = buffer;
		this.connection = con;
	}
	
	@Override
	public void sendActionMessage(int cnt) {		
		buffer.setEventCnt(cnt);
		ActionMessage msg = buffer.generateEvent(cnt);
		if (msg.actions.keySet().size() > 0) {
			byte[] result = msg.serialize();
			connection.sendMessage(result);
		}
	}

	@Override
	public void sendAcknowledgeMessage(int cnt) {
		AcknowledgeMessage am = new AcknowledgeMessage();
		am.ackSeqNmbr = cnt;
		connection.sendMessage(am.serialize());
	}
}
