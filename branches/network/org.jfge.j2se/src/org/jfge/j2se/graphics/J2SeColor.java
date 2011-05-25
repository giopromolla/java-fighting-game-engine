package org.jfge.j2se.graphics;

import java.awt.Color;

public final class J2SeColor extends Color implements org.jfge.spi.graphics.Color {

	public J2SeColor(int r, int g, int b, int a) {
		super(r, g, b, a);
	}

	public J2SeColor(int color) {
		super(color);
	}
}
