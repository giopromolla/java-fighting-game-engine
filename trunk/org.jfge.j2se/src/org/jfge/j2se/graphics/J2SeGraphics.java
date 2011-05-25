package org.jfge.j2se.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

// TODO: Auto-generated Javadoc
/**
 * The Class GraphicsImpl.
 */
public final class J2SeGraphics extends Graphics implements
		org.jfge.spi.graphics.Graphics {

	/** The graphics. */
	private java.awt.Graphics graphics;
	
	/**
	 * Instantiates a new graphics impl.
	 *
	 * @param graphics the graphics
	 */
	public J2SeGraphics(java.awt.Graphics graphics) {
		this.graphics = graphics;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Graphics#clearRect(int, int, int, int)
	 */
	@Override
	public void clearRect(int x, int y, int width, int height) {
		this.graphics.clearRect(x, y, width, height);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#clipRect(int, int, int, int)
	 */
	@Override
	public void clipRect(int x, int y, int width, int height) {
		this.graphics.clipRect(x, y, width, height);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#copyArea(int, int, int, int, int, int)
	 */
	@Override
	public void copyArea(int x, int y, int width, int height, int dx, int dy) {
		this.graphics.copyArea(x, y, width, height, dx, dy);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#create()
	 */
	@Override
	public Graphics create() {
		return this.graphics.create();
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#dispose()
	 */
	@Override
	public void dispose() {
		this.graphics.dispose();
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawArc(int, int, int, int, int, int)
	 */
	@Override
	public void drawArc(int x, int y, int width, int height, int startAngle,
			int arcAngle) {
		this.graphics.drawArc(x, y, width, height, startAngle, arcAngle);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawImage(java.awt.Image, int, int, java.awt.image.ImageObserver)
	 */
	@Override
	public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
		return this.graphics.drawImage(img, x, y, observer);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawImage(java.awt.Image, int, int, java.awt.Color, java.awt.image.ImageObserver)
	 */
	@Override
	public boolean drawImage(Image img, int x, int y, Color bgcolor,
			ImageObserver observer) {
		return this.graphics.drawImage(img, x, y, bgcolor, observer);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawImage(java.awt.Image, int, int, int, int, java.awt.image.ImageObserver)
	 */
	@Override
	public boolean drawImage(Image img, int x, int y, int width, int height,
			ImageObserver observer) {
		return this.graphics.drawImage(img, x, y, width, height, observer);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawImage(java.awt.Image, int, int, int, int, java.awt.Color, java.awt.image.ImageObserver)
	 */
	@Override
	public boolean drawImage(Image img, int x, int y, int width, int height,
			Color bgcolor, ImageObserver observer) {
		return this.graphics.drawImage(img, x, y, width, height, bgcolor, observer);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawImage(java.awt.Image, int, int, int, int, int, int, int, int, java.awt.image.ImageObserver)
	 */
	@Override
	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2,
			int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
		return this.graphics.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawImage(java.awt.Image, int, int, int, int, int, int, int, int, java.awt.Color, java.awt.image.ImageObserver)
	 */
	@Override
	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2,
			int sx1, int sy1, int sx2, int sy2, Color bgcolor,
			ImageObserver observer) {
		return this.graphics.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawLine(int, int, int, int)
	 */
	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		this.graphics.drawLine(x1, y1, x2, y2);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawOval(int, int, int, int)
	 */
	@Override
	public void drawOval(int x, int y, int width, int height) {
		this.graphics.drawOval(x, y, width, height);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawPolygon(int[], int[], int)
	 */
	@Override
	public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		this.graphics.drawPolygon(xPoints, yPoints, nPoints);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawPolyline(int[], int[], int)
	 */
	@Override
	public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
		this.graphics.drawPolyline(xPoints, yPoints, nPoints);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawRoundRect(int, int, int, int, int, int)
	 */
	@Override
	public void drawRoundRect(int x, int y, int width, int height,
			int arcWidth, int arcHeight) {
		this.graphics.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawString(java.lang.String, int, int)
	 */
	@Override
	public void drawString(String str, int x, int y) {
		this.graphics.drawString(str, x, y);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawString(java.text.AttributedCharacterIterator, int, int)
	 */
	@Override
	public void drawString(AttributedCharacterIterator iterator, int x, int y) {
		this.graphics.drawString(iterator, x, y);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#fillArc(int, int, int, int, int, int)
	 */
	@Override
	public void fillArc(int x, int y, int width, int height, int startAngle,
			int arcAngle) {
		this.graphics.fillArc(x, y, width, height, startAngle, arcAngle);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#fillOval(int, int, int, int)
	 */
	@Override
	public void fillOval(int x, int y, int width, int height) {
		this.graphics.fillOval(x, y, width, height);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#fillPolygon(int[], int[], int)
	 */
	@Override
	public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
		this.graphics.fillPolygon(xPoints, yPoints, nPoints);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#fillRect(int, int, int, int)
	 */
	@Override
	public void fillRect(int x, int y, int width, int height) {
		this.graphics.fillRect(x, y, width, height);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#fillRoundRect(int, int, int, int, int, int)
	 */
	@Override
	public void fillRoundRect(int x, int y, int width, int height,
			int arcWidth, int arcHeight) {
		this.graphics.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#getClip()
	 */
	@Override
	public Shape getClip() {
		return this.graphics.getClip();
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#getClipBounds()
	 */
	@Override
	public Rectangle getClipBounds() {
		return this.graphics.getClipBounds();
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#getColor()
	 */
	@Override
	public Color getColor() {
		return this.graphics.getColor();
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#getFont()
	 */
	@Override
	public Font getFont() {
		return this.graphics.getFont();
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#getFontMetrics(java.awt.Font)
	 */
	@Override
	public FontMetrics getFontMetrics(Font f) {
		return this.graphics.getFontMetrics(f);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#setClip(java.awt.Shape)
	 */
	@Override
	public void setClip(Shape clip) {
		this.graphics.setClip(clip);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#setClip(int, int, int, int)
	 */
	@Override
	public void setClip(int x, int y, int width, int height) {
		this.graphics.setClip(x, y, width, height);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#setColor(java.awt.Color)
	 */
	@Override
	public void setColor(Color c) {
		this.graphics.setColor(c);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#setFont(java.awt.Font)
	 */
	@Override
	public void setFont(Font font) {
		this.graphics.setFont(font);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#setPaintMode()
	 */
	@Override
	public void setPaintMode() {
		this.graphics.setPaintMode();
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#setXORMode(java.awt.Color)
	 */
	@Override
	public void setXORMode(Color c1) {
		this.graphics.setXORMode(c1);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#translate(int, int)
	 */
	@Override
	public void translate(int x, int y) {
		this.graphics.translate(x, y);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#create(int, int, int, int)
	 */
	@Override
	public Graphics create(int x, int y, int width, int height) {
		return this.graphics.create(x, y, width, height);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#draw3DRect(int, int, int, int, boolean)
	 */
	@Override
	public void draw3DRect(int x, int y, int width, int height, boolean raised) {
		this.graphics.draw3DRect(x, y, width, height, raised);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawBytes(byte[], int, int, int, int)
	 */
	@Override
	public void drawBytes(byte[] data, int offset, int length, int x, int y) {
		this.graphics.drawBytes(data, offset, length, x, y);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawChars(char[], int, int, int, int)
	 */
	@Override
	public void drawChars(char[] data, int offset, int length, int x, int y) {
		this.graphics.drawChars(data, offset, length, x, y);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawPolygon(java.awt.Polygon)
	 */
	@Override
	public void drawPolygon(Polygon p) {
		this.graphics.drawPolygon(p);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#drawRect(int, int, int, int)
	 */
	@Override
	public void drawRect(int x, int y, int width, int height) {
		this.graphics.drawRect(x, y, width, height);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#fill3DRect(int, int, int, int, boolean)
	 */
	@Override
	public void fill3DRect(int x, int y, int width, int height, boolean raised) {
		this.graphics.fill3DRect(x, y, width, height, raised);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#fillPolygon(java.awt.Polygon)
	 */
	@Override
	public void fillPolygon(Polygon p) {
		this.graphics.fillPolygon(p);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#finalize()
	 */
	@Override
	public void finalize() {
		this.graphics.finalize();
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#getClipBounds(java.awt.Rectangle)
	 */
	@Override
	public Rectangle getClipBounds(Rectangle r) {
		return this.graphics.getClipBounds(r);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#getClipRect()
	 */
	@Override
	public Rectangle getClipRect() {
		return this.graphics.getClipRect();
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#getFontMetrics()
	 */
	@Override
	public FontMetrics getFontMetrics() {
		return this.graphics.getFontMetrics();
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#hitClip(int, int, int, int)
	 */
	@Override
	public boolean hitClip(int x, int y, int width, int height) {
		return this.graphics.hitClip(x, y, width, height);
	}

	/* (non-Javadoc)
	 * @see java.awt.Graphics#toString()
	 */
	@Override
	public String toString() {
		return this.graphics.toString();
	}

	/* (non-Javadoc)
	 * @see org.jfge.spi.graphics.Graphics#getHeight()
	 */
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.jfge.spi.graphics.Graphics#getWidth()
	 */
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.jfge.spi.graphics.Graphics#drawFillRectangle(int, int, int, int)
	 */
	@Override
	public void drawFillRectangle(int x, int y, int width, int height) {
		this.fillRect(x, y, width, height);
	}

	/* (non-Javadoc)
	 * @see org.jfge.spi.graphics.Graphics#drawRectangle(int, int, int, int)
	 */
	@Override
	public void drawRectangle(int x, int y, int width, int height) {
		this.drawRect(x, y, width, height);
	}

	/* (non-Javadoc)
	 * @see org.jfge.spi.graphics.Graphics#drawString(int, int, java.lang.String)
	 */
	@Override
	public void drawString(int x, int y, String text) {
		this.drawString(text, x, y);
	}

	/* (non-Javadoc)
	 * @see org.jfge.spi.graphics.Graphics#setGraphicsColor(org.jfge.spi.graphics.Color)
	 */
	@Override
	public void setGraphicsColor(org.jfge.spi.graphics.Color color) {
		if(color == null)
			return;
		
		if(!(color instanceof J2SeColor))
			return;
		
		this.setColor((J2SeColor) color);
	}

	/* (non-Javadoc)
	 * @see org.jfge.spi.graphics.Graphics#setGraphicsFont(org.jfge.spi.graphics.Font)
	 */
	@Override
	public void setGraphicsFont(org.jfge.spi.graphics.Font font) {
		this.setFont((J2SeFont) font);
	}

	@Override
	public void drawImage(int x, int y, org.jfge.spi.graphics.Image image) {
		if(image == null)
			return;
		
		if(!(image instanceof J2SeImage))
			return;
		
		this.drawImage((J2SeImage) image, x, y, null);
	}
}
