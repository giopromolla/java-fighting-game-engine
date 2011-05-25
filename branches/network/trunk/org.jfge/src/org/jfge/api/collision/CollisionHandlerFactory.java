package org.jfge.api.collision;

import java.util.List;
import java.util.Map;

/**
 * A factory for creating CollisionHandler objects.
 */
public interface CollisionHandlerFactory {
	
	/**
	 * Creates a new CollisionHandler object.
	 *
	 * @param collisionMap the collision map
	 * @return the collision handler
	 */
	public CollisionHandler createCollisionHandler(Map<List<String>,String> collisionMap);
}
