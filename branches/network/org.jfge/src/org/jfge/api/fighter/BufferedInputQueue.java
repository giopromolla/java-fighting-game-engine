package org.jfge.api.fighter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * The Class InputBufferQueueImpl.
 */
public final class BufferedInputQueue implements InputQueue {

	/** The TICKS. */
	private final int CLEAR;
	
	private final int MAX_BUFFER_SIZE;
	
	/** The cur ticks. */
	private int curTicks;
	
	/** The queue. */
	private List<String> queue;
	
	/** The logger. */
	private final Logger logger;
	
	/**
	 * Instantiates a new input buffer queue impl.
	 */
	@Inject
	private BufferedInputQueue(Logger logger, @Named("fighter.inputqueue.clear") int clear, @Named("fighter.inputqueue.maxsize") int maxsize) {
		this.logger = logger;
		this.CLEAR = clear;
		this.MAX_BUFFER_SIZE = maxsize;
		queue = new ArrayList<String>(MAX_BUFFER_SIZE);
	}
	
	/* (non-Javadoc)
	 * @see org.jfge.fighter.InputBufferQueue#handleInput(java.lang.String)
	 */
	@Override
	public synchronized List<String> handleInput(String input) {
		if(queue.size() > MAX_BUFFER_SIZE) {
			queue.clear();
		}
		
		queue.add(input);
		curTicks = 0;
		
		logger.info(queue.toString());
		
		List<String> queueCopy = new ArrayList<String>(queue);
		return queueCopy;
	}

	/* (non-Javadoc)
	 * @see org.jfge.engine.Updatable#update()
	 */
	@Override
	public void update() {
		if(curTicks++ >= CLEAR) {
			queue.clear();
			curTicks = 0;
		}
	}

}
