package org.jfge.android.graphics;


/**
 * The Class AndroidColor.
 */
public class AndroidColor implements org.jfge.spi.graphics.Color {
	
	/** The color. */
	private int color;
	
	/**
	 * Instantiates a new android color.
	 *
	 * @param color the color
	 */
	public AndroidColor(int color) {
		this.color = color;
	}
	
	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public int getColor() {
		return color;
	}

}
