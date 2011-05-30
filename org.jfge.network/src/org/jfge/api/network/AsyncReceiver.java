package org.jfge.api.network;


public interface AsyncReceiver {
	public byte[] popMessage();
	public void setConnection(Connection con);
}
