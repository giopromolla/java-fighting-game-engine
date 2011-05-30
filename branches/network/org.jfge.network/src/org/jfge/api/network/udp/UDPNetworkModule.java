package org.jfge.api.network.udp;

import org.jfge.api.game.GameFactory;
import org.jfge.api.network.AsyncReceiver;
import org.jfge.api.network.Connection;
import org.jfge.api.network.ConnectionFactory;
import org.jfge.api.network.MessageParser;
import org.jfge.api.network.udp.connection.ConnectionFactoryImpl;
import org.jfge.api.network.udp.connection.ConnectionImpl;
import org.jfge.api.network.udp.parsers.MessageParserImpl;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class UDPNetworkModule extends AbstractModule{
	@Override
	protected void configure() {
		bind(AsyncReceiver.class).to(AsyncReceiverImpl.class);
		bind(MessageParser.class).to(MessageParserImpl.class);
		bind(Connection.class).to(ConnectionImpl.class);
		bind(ConnectionFactory.class).to(ConnectionFactoryImpl.class);
		bind(GameFactory.class).annotatedWith(Names.named("udpGameImpl")).to(GameFactoryImpl.class);
	}
}
