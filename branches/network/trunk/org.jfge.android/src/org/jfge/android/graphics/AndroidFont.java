package org.jfge.android.graphics;

import org.jfge.spi.graphics.Font;

import android.graphics.Typeface;

/**
 * The Class AndroidFont.
 */
public class AndroidFont implements Font {
	
	/** The type face. */
	private Typeface typeFace;
	
	/** The point size. */
	private int pointSize;
	
	/**
	 * Instantiates a new android font.
	 *
	 * @param family the family
	 * @param style the style
	 * @param pointSize the point size
	 */
	public AndroidFont(String family, int style, int pointSize) {
		this.pointSize = pointSize;
		this.typeFace = Typeface.create(family, style);
	}

	/**
	 * Gets the typeface.
	 *
	 * @return the typeface
	 */
	public Typeface getTypeface() {
		return typeFace;
	}
	
	/**
	 * Gets the point size.
	 *
	 * @return the point size
	 */
	public int getPointSize() {
		return pointSize;
	}
	
}
