package org.jfge.android.graphics;

import javax.microedition.khronos.opengles.GL;

import org.jfge.spi.graphics.Color;
import org.jfge.spi.graphics.Font;
import org.jfge.spi.graphics.Graphics;
import org.jfge.spi.graphics.Image;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.PorterDuff.Mode;
import android.graphics.Region.Op;

/**
 * The Class AndroidGraphics.
 */
public class AndroidGraphics implements Graphics {

	/** The canvas. */
	private Canvas canvas;
	
	/** The paint. */
	private Paint paint;
	
	/**
	 * Instantiates a new android graphics.
	 *
	 * @param canvas the canvas
	 */
	public AndroidGraphics(Canvas canvas) {
		this.canvas = canvas;
		paint = new Paint();
	}
	
	/**
	 * Gets the canvas.
	 *
	 * @return the canvas
	 */
	public Canvas getCanvas() {
		return canvas;
	}

	
	/* (non-Javadoc)
	 * @see org.jfge.spi.graphics.Graphics#drawFillRectangle(int, int, int, int)
	 */
	@Override
	public void drawFillRectangle(int x, int y, int width, int height) {
		canvas.drawRect(new RectF(x, y+height, x+width, y), paint);
	}

	/* (non-Javadoc)
	 * @see org.jfge.spi.graphics.Graphics#drawImage(int, int, org.jfge.spi.graphics.Image)
	 */
	@Override
	public void drawImage(int x, int y, Image image) {
		if(image == null)
			return;
		
		if(!(image instanceof AndroidImage))
			return;
		
		canvas.drawBitmap(((AndroidImage) image).getBitmap(), x, y, paint);
	}

	/* (non-Javadoc)
	 * @see org.jfge.spi.graphics.Graphics#drawRectangle(int, int, int, int)
	 */
	@Override
	public void drawRectangle(int x, int y, int width, int height) {
		canvas.drawRect(new Rect(x, y+height, x+width, y), paint);
	}

	/* (non-Javadoc)
	 * @see org.jfge.spi.graphics.Graphics#drawString(int, int, java.lang.String)
	 */
	@Override
	public void drawString(int x, int y, String text) {
		canvas.drawText(text, x, y, this.paint);
	}

	/* (non-Javadoc)
	 * @see org.jfge.spi.graphics.Graphics#setGraphicsColor(org.jfge.spi.graphics.Color)
	 */
	@Override
	public void setGraphicsColor(Color color) {
		if(color == null)
			return;
		
		if(!(color instanceof AndroidColor))
			return;
		
		this.paint.setColor(((AndroidColor)color).getColor());
		
	}

	/* (non-Javadoc)
	 * @see org.jfge.spi.graphics.Graphics#setGraphicsFont(org.jfge.spi.graphics.Font)
	 */
	@Override
	public void setGraphicsFont(Font font) {
		if(font == null)
			return;
		
		if(!(font instanceof AndroidFont))
			return;
		
		this.paint.setTypeface(((AndroidFont) font).getTypeface());
		this.paint.setTextSize(((AndroidFont) font).getPointSize());
	}

	@Override
	public int getHeight() {
		return canvas.getHeight();
	}

	@Override
	public int getWidth() {
		return canvas.getWidth();
	}
}
