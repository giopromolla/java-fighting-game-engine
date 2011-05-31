package org.jfge.api.network.udp;

import org.jfge.api.game.GameFactory;
import org.jfge.api.network.AsyncReceiver;
import org.jfge.api.network.Connection;
import org.jfge.api.network.ConnectionFactory;
import org.jfge.api.network.EventBuffer;
import org.jfge.api.network.MessageParser;
import org.jfge.api.network.MessageSender;
import org.jfge.api.network.udp.connection.ConnectionFactoryImpl;
import org.jfge.api.network.udp.connection.ConnectionImpl;
import org.jfge.api.network.udp.messages.AcknowledgeMessage;
import org.jfge.api.network.udp.messages.ActionMessage;
import org.jfge.api.network.udp.messages.SequenceResetMessage;
import org.jfge.api.network.udp.parsers.AcknowledgeMessageParserImpl;
import org.jfge.api.network.udp.parsers.ActionMessageParserImpl;
import org.jfge.api.network.udp.parsers.MessageParserImpl;
import org.jfge.api.network.udp.parsers.SequenceResetNetworkMessageParserImpl;
import org.jfge.api.network.udp.parsers.TypeParser;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.name.Names;

public class UDPNetworkModule extends AbstractModule{
	@Override
	protected void configure() {
		bind(Connection.class).to(ConnectionImpl.class);
		bind(AsyncReceiver.class).to(AsyncReceiverImpl.class);
		bind(MessageParser.class).to(MessageParserImpl.class);		
		bind(ConnectionFactory.class).to(ConnectionFactoryImpl.class);
		bind(MessageSender.class).to(MessageSenderImpl.class);
		bind(EventBuffer.class).to(EventBufferImpl.class);
		bind(GameFactory.class).annotatedWith(Names.	named("udpGameImpl")).to(GameFactoryImpl.class);
				
		MapBinder<Integer, TypeParser> typeParsers = MapBinder.newMapBinder(binder(), Integer.class, TypeParser.class);
		typeParsers.addBinding(ActionMessage.TYPE).to(ActionMessageParserImpl.class);
		typeParsers.addBinding(AcknowledgeMessage.TYPE).to(AcknowledgeMessageParserImpl.class);
		typeParsers.addBinding(SequenceResetMessage.TYPE).to(SequenceResetNetworkMessageParserImpl.class);
	}
}
