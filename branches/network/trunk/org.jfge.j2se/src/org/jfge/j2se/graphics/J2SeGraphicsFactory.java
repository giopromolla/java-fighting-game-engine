package org.jfge.j2se.graphics;

import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.jfge.spi.graphics.Color;
import org.jfge.spi.graphics.Font;
import org.jfge.spi.graphics.Image;
import org.jfge.spi.graphics.GraphicsFactory;
import org.jfge.spi.graphics.Rectangle;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * The Class ImageLoader.
 */
@Singleton
public final class J2SeGraphicsFactory implements GraphicsFactory {
	
	/** The logger. */
	private final Logger logger;
	
	/** The graphics environment. */
    private java.awt.GraphicsEnvironment graphicsEnvironment;

    /** The graphics device. */
    private java.awt.GraphicsDevice graphicsDevice;

    /** The graphics configuration. */
    private java.awt.GraphicsConfiguration graphicsConfiguration;
    
    /**
     * Instantiates a new image loader.
     *
     * @param logger the logger
     */
    @Inject
	public J2SeGraphicsFactory(Logger logger) {
		this.logger = logger;
    	
    	/*
    	 * create graphics environment
    	 */
    	this.graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		this.graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
		this.graphicsConfiguration  = graphicsDevice.getDefaultConfiguration();
	}
	
    /**
     * Load.
     *
     * @param file the file
     * @return the image
     * @throws IOException 
     */
    @Override
    public Image createImage(String file) throws IOException {
    	BufferedImage img;
    	
    	try {
    		img = ImageIO.read(getClass().getResourceAsStream(file));
    	} catch(IOException e) {
    		throw e;
    	} catch(Exception e)  {
    		logger.info("can't load: " + file);
    		return null;
    	}

		/*
		 * create optimized copy of the image
		 */
		int transparency = img.getColorModel().getTransparency();
		BufferedImage copy =  this.graphicsConfiguration.createCompatibleImage(img.getWidth(), img.getHeight(), transparency );

	    Graphics2D g2d = copy.createGraphics(); // create a graphics context
	    g2d.drawImage(img,0,0,null); // copy image
	    g2d.dispose();
		
	    return new J2SeImage(copy);
    }

	@Override
	public Color createColor(int color) {
		return new J2SeColor(color);
	}

	@Override
	public Color createColor(String color) {
		return new J2SeColor(Integer.parseInt(color));
	}

	@Override
	public Font createFont(String family, int style, int pointsize) {
		return new J2SeFont(family, style, pointsize);
	}

	@Override
	public Rectangle createRectangle(int x, int y, int width, int height) {
		return new J2SeRectangle(x, y, width, height);
	}
}
