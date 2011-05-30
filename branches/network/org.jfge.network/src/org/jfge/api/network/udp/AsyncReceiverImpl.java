package org.jfge.api.network.udp;

import java.util.Stack;

import org.jfge.api.network.AsyncReceiver;
import org.jfge.api.network.Connection;

import com.google.inject.Singleton;

@Singleton
public class AsyncReceiverImpl extends Thread implements AsyncReceiver {
	private Connection con;
	private Object waiter = new Object();
	private Stack<byte[]> byteStack = new Stack<byte[]>();

	public AsyncReceiverImpl() {
		this.start();
	}

	public void run() {
		waitForConnection();		
		while(con != null && con.isConnected()) {
			byte[] buffer = new byte[1024];
			if (con.receiveMessage(buffer)) {
				byteStack.push(buffer);
			}
			waitForConnection();
		}
	}
	
	private void waitForConnection() {
		while (this.con == null) {
			try {
				synchronized (waiter) {
					waiter.wait();
				}
			} catch (InterruptedException e) { }
		}
	}

	@Override
	public byte[] popMessage() {
		return byteStack.isEmpty() ? null : byteStack.pop();
	}

	@Override
	public void setConnection(Connection con) {
		this.con = con;
		waiter.notify();
	}

}
