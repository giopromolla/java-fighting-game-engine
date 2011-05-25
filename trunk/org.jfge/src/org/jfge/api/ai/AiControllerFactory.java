package org.jfge.api.ai;

import java.util.List;
import java.util.Map;

/**
 * A factory for creating AiController objects.
 */
public interface AiControllerFactory {
	
	/**
	 * Creates a new AiController object.
	 *
	 * @param transitions the transitions
	 * @return the ai controller
	 */
	public AiController createAiController(Map<List<String>,String> transitions);
}
