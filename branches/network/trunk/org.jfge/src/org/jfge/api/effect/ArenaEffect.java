package org.jfge.api.effect;

import org.jfge.api.arena.Arena;
import org.jfge.api.sprite.Sprite;

public interface ArenaEffect extends Sprite{
	
	/**
	 * Sets the parent.
	 *
	 * @param fighter the new parent
	 */
	public void setParent(Arena arena);
}
