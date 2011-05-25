package org.jfge.api.effect;

import java.util.List;

import org.jfge.spi.graphics.Image;

import com.google.inject.assistedinject.Assisted;

public interface CollisionEffectFactory {
	
	public CollisionEffect createCollisionEffect(List<Image> images, @Assisted("relX") double relX, @Assisted("relY") double relY);
}
