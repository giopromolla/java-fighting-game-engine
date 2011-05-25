package org.jfge.api.effect;

import java.util.List;

import org.jfge.spi.graphics.Image;

import com.google.inject.assistedinject.Assisted;

/**
 * A factory for creating ArenaEffect objects.
 */
public interface ArenaEffectFactory{
	
	/**
	 * Creates a new ArenaEffect object.
	 *
	 * @param images the images
	 * @return the arena effect
	 */
	public ArenaEffect createArenaEffect(List<Image> images, @Assisted("arenaFactory.x") int x, @Assisted("arenaFactory.y") int y);
}
