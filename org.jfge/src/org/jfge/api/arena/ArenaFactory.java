package org.jfge.api.arena;

import java.io.IOException;

import org.jfge.spi.render.ArenaRenderer;

/**
 * A factory for creating Arena objects.
 */
public interface ArenaFactory {
	
	/**
	 * Creates a new Arena object.
	 *
	 * @param file the file
	 * @return the arena
	 * @throws IOException 
	 */
	public Arena createArena(String file, ArenaRenderer arenaRenderer) throws IOException;
}
