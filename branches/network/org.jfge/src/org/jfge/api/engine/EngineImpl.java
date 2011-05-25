
package org.jfge.api.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.jfge.spi.graphics.Graphics;
import org.jfge.spi.graphics.GraphicsProvider;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * The Class EngineImpl.
 */
@Singleton
public final class EngineImpl implements org.jfge.api.engine.Engine {

	/** The logger. */
	private final Logger logger;
	
	/** The paused. */
	private boolean paused;
	
	/** The running. */
	private boolean running;
	
	/** The thread. */
	private Thread thread;
	
	/** The timer. */
    private org.jfge.api.engine.Timer timer;

    /** The graphics provider. */
    private GraphicsProvider graphicsProvider;
    
    /** The renderable. */
    private List<Renderable> renderables;
    
    /** The updateables. */
    private List<Updatable> updatables;
    
    /** The graphics. */
    private Graphics graphics;
    
   /**
     * Instantiates a new engine impl.
     *
     * @param graphicsProvider the graphics provider
     * @param timer the timer
     * @param logger the logger
     */
    @Inject
    public EngineImpl(GraphicsProvider graphicsProvider,Timer timer, Logger logger) {
    	this.graphicsProvider = graphicsProvider;
    	this.graphics = graphicsProvider.getGraphics();
    	this.timer = timer;
    	this.logger = logger;
    	this.thread = new Thread(this);
    	
    	this.renderables = new ArrayList<Renderable>();
    	this.updatables = new ArrayList<Updatable>();
    	this.paused = false;
    }
    
    /* (non-Javadoc)
     * @see org.jfge.engine.Engine#update()
     */
    /**
     * Update.
     */
    @Override
    public void update() {        
    	for(Updatable r: updatables) {
    		r.update();
    	}
    } 

    /* (non-Javadoc)
     * @see org.jfge.engine.Engine#render()
     */
    /**
     * Render.
     */
    private void render() {        
    	/*
    	 * render game elements
    	 */
    	for(Renderable r: renderables) {
    		r.render(graphics);
    	}
    } 

   /**
     * Adds the renderable.
     *
     * @param renderable the renderable
     */
    @Override
    public void addRenderable(Renderable renderable) {
    	renderables.add(renderable);
    }
    
    /**
     * Adds the updatables.
     *
     * @param updatable the updatable
     */
    @Override
    public void addUpdatable(Updatable updatable) {
    	this.updatables.add(updatable);
    }
    
    /* (non-Javadoc)
     * @see org.jfge.engine.Engine#removeRenderable(org.jfge.engine.Renderable)
     */
    @Override
	public void removeRenderable(Renderable renderable) {
		this.renderables.remove(renderable);
	}

	/* (non-Javadoc)
	 * @see org.jfge.engine.Engine#removeUpdatable(org.jfge.engine.Updatable)
	 */
	@Override
	public void removeUpdatable(Updatable updatable) {
		this.updatables.remove(updatable);
	}
    
    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {        
    	
    	while(running) {	//core main loop
    		
    		timer.measure();
    		
    		//engine update
    		if(!paused)
    			update();
    		
    		//engine render
    		render();
    		
    		//draw buffered graphics
    		graphicsProvider.draw();
    		
    		//sleep intelligent 
    		timer.sleep(); 
    	}
    }
    
   /* (non-Javadoc)
	 * @see org.jfge.engine.Engine#pause()
	 */
	@Override
	public void pause() {
		paused = true;
	}

	/* (non-Javadoc)
	 * @see org.jfge.engine.Engine#resume()
	 */
	@Override
	public void resume() {
		paused = false;
	}

	/* (non-Javadoc)
	 * @see org.jfge.engine.Engine#start()
	 */
	@Override
	public void start() {
		if(thread.isAlive())
			return;
		
		//starting main loop
    	logger.info("core main loop started");
    
		this.running = true;
		thread.start();
	}
	
	/* (non-Javadoc)
	 * @see org.jfge.engine.Engine#stop()
	 */
	@Override
	public void stop() {
		this.running = false;
		while(thread.isAlive());
	}
}
