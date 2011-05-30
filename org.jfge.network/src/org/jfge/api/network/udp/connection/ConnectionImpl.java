package org.jfge.api.network.udp.connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.jfge.api.network.Connection;

import com.google.inject.Singleton;

@Singleton
public class ConnectionImpl implements Connection{
	public static final int STANDARD_PORT = 666;
	private DatagramSocket socket;
	private InetAddress otherIp;
	private int myPort, otherPort;
	
	public ConnectionImpl() {
	}
	
	@Override
	public boolean isConnected() { 
		return socket != null && socket.isBound();
	}

	@Override
	public void connect(String connectionDetails) throws IllegalArgumentException {
		if(isConnected()) {
			disconnect();
		}
		
		String[] tmp = connectionDetails.split(":");
		if ( tmp.length != 3  && tmp.length != 1) {
			throw new IllegalArgumentException("UDP-Connection requires: myport:otherIP:otherPort or otherIP (port defaults to 666)");
		}		
		
		try {
			this.otherIp =InetAddress.getByName(tmp[1]);
		} catch (UnknownHostException e1) {
			throw new IllegalArgumentException(e1);
		}
		
		if (tmp.length > 1) {
			try {
				this.otherPort = Integer.parseInt(tmp[2]);
				this.myPort = Integer.parseInt(tmp[0]);
			} catch ( NumberFormatException nfe ) {
				throw new IllegalArgumentException(nfe);
			}
		} else {
			this.myPort = this.otherPort = STANDARD_PORT;
		}
		
		try {
			socket = new DatagramSocket(myPort);
		} catch (SocketException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public void disconnect() {
		if(isConnected()) {
			this.socket.close();
			this.socket = null;
		}
	}

	@Override
	public boolean sendMessage(byte[] message) {
		DatagramPacket dp = new DatagramPacket(message, message.length, this.otherIp, this.otherPort);		
		try {
			socket.send(dp);
			return true;
		} catch (IOException e) {
			return false;
		}
		
	}

	@Override
	public boolean receiveMessage(byte[] targetBuffer) {		
		DatagramPacket dp = new DatagramPacket(targetBuffer, targetBuffer.length);
		try {
			socket.receive(dp);
			return true;
		} catch (IOException e) { 
			return false;
		}
	}

}
