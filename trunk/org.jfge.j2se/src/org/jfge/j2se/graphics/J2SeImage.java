package org.jfge.j2se.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.ImageCapabilities;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;
import java.awt.image.TileObserver;
import java.awt.image.WritableRaster;
import java.util.Vector;

import org.jfge.spi.graphics.Image;

/**
 * The Class ImageImpl.
 */
public final class J2SeImage extends BufferedImage implements Image {

	/** The image. */
	private BufferedImage image;

	/** The flipped. */
	private Image flipped;
	
	/**
	 * Instantiates a new image impl.
	 *
	 * @param image the image
	 */
	public J2SeImage(BufferedImage image) {
		super(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		this.image = image;
	}
	
	/**
	 * Adds the tile observer.
	 *
	 * @param arg0 the arg0
	 */
	@Override
	public void addTileObserver(TileObserver arg0) {
		this.image.addTileObserver(arg0);
	}

	/**
	 * Coerce data.
	 *
	 * @param arg0 the arg0
	 */
	@Override
	public void coerceData(boolean arg0) {
		this.image.coerceData(arg0);
	}

	/**
	 * Copy data.
	 *
	 * @param arg0 the arg0
	 * @return the writable raster
	 */
	@Override
	public WritableRaster copyData(WritableRaster arg0) {
		return this.image.copyData(arg0);
	}

	/**
	 * Creates the graphics.
	 *
	 * @return the graphics2 d
	 */
	@Override
	public Graphics2D createGraphics() {
		return this.image.createGraphics();
	}

	/**
	 * Gets the alpha raster.
	 *
	 * @return the alpha raster
	 */
	@Override
	public WritableRaster getAlphaRaster() {
		return this.image.getAlphaRaster();
	}

	/**
	 * Gets the color model.
	 *
	 * @return the color model
	 */
	@Override
	public ColorModel getColorModel() {
		return this.image.getColorModel();
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	@Override
	public Raster getData() {
		return this.image.getData();
	}

	/**
	 * Gets the data.
	 *
	 * @param arg0 the arg0
	 * @return the data
	 */
	@Override
	public Raster getData(Rectangle arg0) {
		return this.image.getData(arg0);
	}

	/**
	 * Gets the graphics.
	 *
	 * @return the graphics
	 */
	@Override
	public Graphics getGraphics() {
		return this.image.getGraphics();
	}

	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	@Override
	public int getHeight() {
		return this.image.getHeight();
	}

	/**
	 * Gets the height.
	 *
	 * @param arg0 the arg0
	 * @return the height
	 */
	@Override
	public int getHeight(ImageObserver arg0) {
		return this.image.getHeight();
	}

	/**
	 * Gets the min tile x.
	 *
	 * @return the min tile x
	 */
	@Override
	public int getMinTileX() {
		return this.image.getMinTileX();
	}

	/**
	 * Gets the min tile y.
	 *
	 * @return the min tile y
	 */
	@Override
	public int getMinTileY() {
		return this.image.getMinTileY();
	}

	/**
	 * Gets the min x.
	 *
	 * @return the min x
	 */
	@Override
	public int getMinX() {
		return this.image.getMinX();
	}

	/**
	 * Gets the min y.
	 *
	 * @return the min y
	 */
	@Override
	public int getMinY() {
		return this.image.getMinY();
	}

	/**
	 * Gets the num x tiles.
	 *
	 * @return the num x tiles
	 */
	@Override
	public int getNumXTiles() {
		return this.image.getNumXTiles();
	}

	/**
	 * Gets the num y tiles.
	 *
	 * @return the num y tiles
	 */
	@Override
	public int getNumYTiles() {
		return this.image.getNumYTiles();
	}

	/**
	 * Gets the property.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 * @return the property
	 */
	@Override
	public Object getProperty(String arg0, ImageObserver arg1) {
		return this.image.getProperty(arg0, arg1);
	}

	/**
	 * Gets the property.
	 *
	 * @param arg0 the arg0
	 * @return the property
	 */
	@Override
	public Object getProperty(String arg0) {
		return this.image.getProperty(arg0);
	}

	/**
	 * Gets the property names.
	 *
	 * @return the property names
	 */
	@Override
	public String[] getPropertyNames() {
		return this.image.getPropertyNames();
	}

	/**
	 * Gets the raster.
	 *
	 * @return the raster
	 */
	@Override
	public WritableRaster getRaster() {
		return this.image.getRaster();
	}

	/**
	 * Gets the rGB.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 * @param arg2 the arg2
	 * @param arg3 the arg3
	 * @param arg4 the arg4
	 * @param arg5 the arg5
	 * @param arg6 the arg6
	 * @return the rGB
	 */
	@Override
	public int[] getRGB(int arg0, int arg1, int arg2, int arg3, int[] arg4,
			int arg5, int arg6) {
		return this.image.getRGB(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	/**
	 * Gets the rGB.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 * @return the rGB
	 */
	@Override
	public int getRGB(int arg0, int arg1) {
		return this.image.getRGB(arg0, arg1);
	}

	/**
	 * Gets the sample model.
	 *
	 * @return the sample model
	 */
	@Override
	public SampleModel getSampleModel() {
		return this.image.getSampleModel();
	}

	/**
	 * Gets the source.
	 *
	 * @return the source
	 */
	@Override
	public ImageProducer getSource() {
		return this.image.getSource();
	}

	/**
	 * Gets the sources.
	 *
	 * @return the sources
	 */
	@Override
	public Vector<RenderedImage> getSources() {
		return this.image.getSources();
	}

	/**
	 * Gets the subimage.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 * @param arg2 the arg2
	 * @param arg3 the arg3
	 * @return the subimage
	 */
	@Override
	public BufferedImage getSubimage(int arg0, int arg1, int arg2, int arg3) {
		return this.image.getSubimage(arg0, arg1, arg2, arg3);
	}

	/**
	 * Gets the tile.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 * @return the tile
	 */
	@Override
	public Raster getTile(int arg0, int arg1) {
		return this.image.getTile(arg0, arg1);
	}

	/**
	 * Gets the tile grid x offset.
	 *
	 * @return the tile grid x offset
	 */
	@Override
	public int getTileGridXOffset() {
		return this.image.getTileGridXOffset();
	}

	/**
	 * Gets the tile grid y offset.
	 *
	 * @return the tile grid y offset
	 */
	@Override
	public int getTileGridYOffset() {
		return this.image.getTileGridYOffset();
	}

	/**
	 * Gets the tile height.
	 *
	 * @return the tile height
	 */
	@Override
	public int getTileHeight() {
		return this.image.getTileHeight();
	}

	/**
	 * Gets the tile width.
	 *
	 * @return the tile width
	 */
	@Override
	public int getTileWidth() {
		return this.image.getTileWidth();
	}

	/**
	 * Gets the transparency.
	 *
	 * @return the transparency
	 */
	@Override
	public int getTransparency() {
		return this.image.getTransparency();
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	@Override
	public int getType() {
		return this.image.getType();
	}

	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	@Override
	public int getWidth() {
		return this.image.getWidth();
	}

	/**
	 * Gets the width.
	 *
	 * @param arg0 the arg0
	 * @return the width
	 */
	@Override
	public int getWidth(ImageObserver arg0) {
		return this.image.getWidth(arg0);
	}

	/**
	 * Gets the writable tile.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 * @return the writable tile
	 */
	@Override
	public WritableRaster getWritableTile(int arg0, int arg1) {
		return this.image.getWritableTile(arg0, arg1);
	}

	/**
	 * Gets the writable tile indices.
	 *
	 * @return the writable tile indices
	 */
	@Override
	public Point[] getWritableTileIndices() {
		return this.image.getWritableTileIndices();
	}

	/**
	 * Checks for tile writers.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean hasTileWriters() {
		return this.image.hasTileWriters();
	}

	/**
	 * Checks if is alpha premultiplied.
	 *
	 * @return true, if is alpha premultiplied
	 */
	@Override
	public boolean isAlphaPremultiplied() {
		return this.image.isAlphaPremultiplied();
	}

	/**
	 * Checks if is tile writable.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 * @return true, if is tile writable
	 */
	@Override
	public boolean isTileWritable(int arg0, int arg1) {
		return this.image.isTileWritable(arg0, arg1);
	}

	/**
	 * Release writable tile.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	@Override
	public void releaseWritableTile(int arg0, int arg1) {
		this.image.releaseWritableTile(arg0, arg1);
	}

	/**
	 * Removes the tile observer.
	 *
	 * @param arg0 the arg0
	 */
	@Override
	public void removeTileObserver(TileObserver arg0) {
		this.image.removeTileObserver(arg0);
	}

	/**
	 * Sets the data.
	 *
	 * @param arg0 the new data
	 */
	@Override
	public void setData(Raster arg0) {
		this.image.setData(arg0);
	}

	/**
	 * Sets the rgb.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 * @param arg2 the arg2
	 * @param arg3 the arg3
	 * @param arg4 the arg4
	 * @param arg5 the arg5
	 * @param arg6 the arg6
	 */
	@Override
	public void setRGB(int arg0, int arg1, int arg2, int arg3, int[] arg4,
			int arg5, int arg6) {
		this.image.setRGB(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	/**
	 * Sets the rgb.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 * @param arg2 the arg2
	 */
	@Override
	public synchronized void setRGB(int arg0, int arg1, int arg2) {
		this.image.setRGB(arg0, arg1, arg2);
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return this.image.toString();
	}

	/**
	 * Flush.
	 */
	@Override
	public void flush() {
		this.image.flush();
	}

	/**
	 * Gets the acceleration priority.
	 *
	 * @return the acceleration priority
	 */
	@Override
	public float getAccelerationPriority() {
		return this.image.getAccelerationPriority();
	}

	/**
	 * Gets the capabilities.
	 *
	 * @param gc the gc
	 * @return the capabilities
	 */
	@Override
	public ImageCapabilities getCapabilities(GraphicsConfiguration gc) {
		return this.image.getCapabilities(gc);
	}

	/**
	 * Gets the scaled instance.
	 *
	 * @param width the width
	 * @param height the height
	 * @param hints the hints
	 * @return the scaled instance
	 */
	@Override
	public java.awt.Image getScaledInstance(int width, int height, int hints) {
		return this.image.getScaledInstance(width, height, hints);
	}

	/**
	 * Sets the acceleration priority.
	 *
	 * @param priority the new acceleration priority
	 */
	@Override
	public void setAccelerationPriority(float priority) {
		this.image.setAccelerationPriority(priority);
	}

	@Override
	public Image flip() {
		if(this.flipped == null) {
			AffineTransform tx = new AffineTransform(); tx.scale(-1, 1); tx.translate(-image.getWidth(null), 0); 
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR); 
			this.flipped =new J2SeImage(op.filter(image, null));
		}
		
		return this.flipped;
	}

	@Override
	public Image rotate(int degree) {
		AffineTransform tx = new AffineTransform();
		tx.rotate(degree, getWidth()/2, getHeight()/2);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		
		return new J2SeImage(op.filter(this.image, null));
	}
}
