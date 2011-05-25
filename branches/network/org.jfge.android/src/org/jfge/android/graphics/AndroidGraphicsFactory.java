package org.jfge.android.graphics;

import java.io.IOException;

import org.jfge.spi.graphics.Color;
import org.jfge.spi.graphics.Font;
import org.jfge.spi.graphics.GraphicsFactory;
import org.jfge.spi.graphics.Image;
import org.jfge.spi.graphics.Rectangle;

import android.graphics.BitmapFactory;

import com.google.inject.Singleton;

@Singleton
public class AndroidGraphicsFactory implements GraphicsFactory {

	@Override
	public Image createImage(String file) throws IOException {
		return new AndroidImage(BitmapFactory.decodeStream(getClass().getResourceAsStream(file)));
	}

	@Override
	public Color createColor(int color) {
		return new AndroidColor(color);
	}

	@Override
	public Color createColor(String color) {
		return createColor(android.graphics.Color.parseColor(color));
	}

	@Override
	public Font createFont(String family, int style, int pointsize) {
		return new AndroidFont(family, style, pointsize);
	}

	@Override
	public Rectangle createRectangle(int x, int y, int width, int height) {
		return new AndroidRectangle(x, y, width, height);
	}

}
