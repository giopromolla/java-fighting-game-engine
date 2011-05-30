package org.jfge.api.network;


public interface Connection {
	public boolean isConnected();
	public void connect(String connectionDetails) throws IllegalArgumentException;
	public void disconnect();
	public boolean sendMessage(byte[] message);
	public boolean receiveMessage(byte[] buffer);
}
