package org.jfge.api.misc;

public final class ByteOperations {
	public static final byte[] intToByteArray(int value) {
		byte[] result = new byte[] { (byte) (value >> 24),
				(byte) (value >> 16), (byte) (value >> 8), (byte) value };
		return result;
	}

	public static final int byteArrayToInt(byte[] bytes) {
		int result = ((bytes[0] & 0xff) << 24) | ((bytes[1] & 0xff) << 16)
				| ((bytes[2] & 0xff) << 8) | (bytes[3] & 0xff);

		return result;
	}

}
