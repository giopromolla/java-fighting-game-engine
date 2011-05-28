package org.jfge.api.misc;

public final class ByteOperations {
	public static final byte[] intToByteArray(int value) {
	    return new byte[] {
	            (byte)(value >> 24),
	            (byte)(value >> 16),
	            (byte)(value >> 8),
	            (byte)value};
	}
	
	public static final int byteArrayToInt(byte[] bytes) {
		int result = bytes[0]<<24;
		result |= bytes[1] <<16;
		result |= bytes[2] <<8;
		result |= bytes[3];
		return result;
	}

}
