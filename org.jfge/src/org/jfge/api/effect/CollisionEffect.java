package org.jfge.api.effect;

import org.jfge.api.fighter.Fighter;
import org.jfge.api.sprite.Sprite;

/**
 * The Interface CollisionEffect.
 */
public interface CollisionEffect extends Sprite {
	
	/**
	 * Sets the parent.
	 *
	 * @param fighter the new parent
	 */
	public void setParent(Fighter fighter);
}
