
package org.jfge.j2se.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfge.spi.controller.Controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

/**
 * The Class GraphicsProviderImpl.
 */
@Singleton
public final class J2SeGraphicsProvider implements org.jfge.spi.graphics.GraphicsProvider {

	/** The logger. */
	private final Logger logger;
	
	/** The Constant HEIGHT. */
	private final int height;

    /** The Constant WIDTH. */
    private final int width;

    /** The j panel. */
    private javax.swing.JPanel jPanel;

    /** The j frame. */
    private javax.swing.JFrame jFrame;

    /** The graphics environment. */
    private java.awt.GraphicsEnvironment graphicsEnvironment;

    /** The graphics device. */
    private java.awt.GraphicsDevice graphicsDevice;

    /** The graphics configuration. */
    private java.awt.GraphicsConfiguration graphicsConfiguration;
    
    /** The db image. */
    private BufferedImage dbImage;

    /** The db enabled. */
    private boolean dbEnabled;
    
    /**
     * Instantiates a new graphics provider impl.
     *
     * @param logger the logger
     * @param controller the controller
     * @param height the height
     * @param width the width
     */
    @Inject
    public  J2SeGraphicsProvider(Logger logger,
    		@Named("keyboard.j2se") Controller controller, 
    		@Named("engine.height") int height,
    		@Named("engine.width") int width,  
    		@Named("engine.db") boolean dbEnabled) {        
        this.height = height;
        this.width = width;
    	this.dbEnabled = dbEnabled;
    	this.logger = logger;
    	
    	// create JPanel
        jPanel = new JPanel();
    	jPanel.setIgnoreRepaint(true);
    	jPanel.setPreferredSize(new Dimension(width, height));
    	jPanel.setSize(new Dimension(width, height));
    	
    	//create JFrame
    	jFrame = new JFrame();
    	jFrame.setIgnoreRepaint(true);
    	jFrame.setResizable(this.dbEnabled);
    	jFrame.setTitle("Java Fighting Game Engine - JavaSE 6");
    	jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	jFrame.add(jPanel);
    	jFrame.pack();
    	
    	// show frame
    	jFrame.setVisible(true);
    	jFrame.addKeyListener((KeyListener) controller);
    	logger.info("graphics provider resolution:" + width +" x " + height);
    	logger.info("double buffering " + (dbEnabled ? "enabled" : "disabled"));
    	
    	// create graphics environment
    	graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
		graphicsConfiguration  = graphicsDevice.getDefaultConfiguration();
		
		// create double buffer image
		dbImage =  graphicsConfiguration.createCompatibleImage(width, height);
	} 
    
    /* (non-Javadoc)
     * @see org.jfge.engine.Engine#draw()
     */
    @Override
    public void draw() {        
    	
    	if(!dbEnabled)
    		return;
    	
    	Graphics graphics = null;
    	
    	try {
			graphics = jPanel.getGraphics();
			
			//draw buffered image
			graphics.drawImage(dbImage, 0, 0, jPanel.getWidth(), jPanel.getHeight(), null);
		} finally {
			//release graphics
	        if(graphics != null ) 
	        	graphics.dispose();
	       	}
    } 

    /* (non-Javadoc)
     * @see org.jfge.engine.GraphicsProvider#getGraphics()
     */
    public org.jfge.spi.graphics.Graphics getGraphics() {        
    	if(dbEnabled)
    		return new J2SeGraphics(dbImage.getGraphics());
    	else
    		return new J2SeGraphics(jPanel.getGraphics());
    } 
 }
