package org.jfge.j2se.graphics;

import java.awt.Font;

public final class J2SeFont extends Font implements org.jfge.spi.graphics.Font{

	public J2SeFont(String family, int style, int pointsize) {
		super(family, style, pointsize);
	}
}
