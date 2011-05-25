
package org.jfge.api.engine;

import java.util.logging.Logger;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

/**
 * The Class TimerImpl.
 */
@Singleton
public final class TimerImpl implements org.jfge.api.engine.Timer {

	/** The logger. */
	private final Logger logger;
	
	/** The no delays per yield. */
	private final int noDelaysPerYield;
	
	/** The max frame skip. */
	private final int maxFrameSkips;
	
	/** The Constant FPS. */
	private final int fps;
	
	/** The before time. */
	private long beforeTime;
	
	/** The after time. */
	private long afterTime;
	
	/** The over sleep time. */
	private long overSleepTime;
	
	/** The no delays. */
	private int noDelays;
	
	/** The excess. */
	private long excess;

	/** The time diff. */
	private long timeDiff;
	
	/** The sleep time. */
	private long sleepTime;
	
	/** The period. */
	private long period ;
	
	/** The engine. */
	private Engine engine;
	
	/**
	 * Instantiates a new timer impl.
	 *
	 * @param fps the fps
	 * @param logger the logger
	 */
	@Inject
	public TimerImpl(Engine engine,
			Logger logger,
			@Named("engine.fps") int fps, 
			@Named("engine.nodelays") int noDelaysPerYield , 
			@Named("engine.frameskip") int maxFrameSkips) {
		this.fps = fps;
		this.noDelaysPerYield = noDelaysPerYield;
		this.maxFrameSkips = maxFrameSkips;
		this.logger = logger;
		this.engine = engine;
		
		this.beforeTime = System.nanoTime();
		this.afterTime = this.beforeTime;
		this.period = 1000000000L/fps;
		
		logger.info("timer initialized with " + fps  +" fps");
	}
	
    /* (non-Javadoc)
     * @see org.jfge.engine.Timer#measure()
     */
    public void measure() {        
       beforeTime = System.nanoTime();
    } 

    /* (non-Javadoc)
     * @see org.jfge.engine.Timer#sleep()
     */
    public void sleep() {        
    	afterTime = System.nanoTime();
    	timeDiff = (afterTime - beforeTime);
    	sleepTime = (period - timeDiff) - overSleepTime;  

    	if (sleepTime > 0) {   // some time left in this cycle
    		try{
    			Thread.sleep(sleepTime/1000000L);  // nano -> ms
    		}
    		catch(InterruptedException ex){}
    		overSleepTime = (System.nanoTime() - afterTime) - sleepTime;
    	}
    	else {    // sleepTime <= 0; the frame took longer than the period
    		excess -= sleepTime;  // store excess time value
    		overSleepTime = 0L;

    		if (++noDelays >= noDelaysPerYield) {
    			Thread.yield();   // give another thread a chance to run
    			noDelays = 0;
    		}
    	 }
    	 
    	 int skips = 0;
    	 while((excess > period) && (skips < maxFrameSkips)) {
    		 excess -= period;
    		 this.engine.update();    // update state but don't render
    		 skips++;
    	 }
    }
 }
