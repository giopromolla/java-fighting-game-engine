package org.jfge.spi.physics;

import org.jfge.api.engine.Updatable;

public interface SpritePhysics<T> extends Updatable{
	public void setParent(T parent);
	
	public void init();
}
