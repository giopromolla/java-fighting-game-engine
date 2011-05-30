package org.jfge.api.network.udp.connection;

import org.jfge.api.network.Connection;
import org.jfge.api.network.ConnectionFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ConnectionFactoryImpl implements ConnectionFactory {
	@Inject
	public ConnectionFactoryImpl(Connection con) {
		this.connection = con;
	}
	
	private Connection connection;
	
	public Connection provideConnection() {
		if (connection == null) {
			connection = new ConnectionImpl();
		}
		return connection;
	}
}
