package org.jfge.games.mk2.effect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jfge.api.effect.CollisionEffect;
import org.jfge.api.effect.CollisionEffectFactory;
import org.jfge.spi.graphics.GraphicsFactory;
import org.jfge.spi.graphics.Image;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * The Class FallDownBlood.
 */
public final class FallDownBlood implements Provider<CollisionEffect> {
	
	/** The images. */
	private List<Image> images;
	
	/** The collision effect factory. */
	private CollisionEffectFactory collisionEffectFactory;
	
	/** The graphics factory. */
	private GraphicsFactory graphicsFactory;
	
	/**
	 * Instantiates a new fall down blood.
	 *
	 * @param graphicsFactory the graphics factory
	 * @param collisionEffectFactory the collision effect factory
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Inject
	public FallDownBlood(GraphicsFactory graphicsFactory, CollisionEffectFactory collisionEffectFactory) throws IOException {
		this.collisionEffectFactory = collisionEffectFactory;
		this.graphicsFactory = graphicsFactory;
	}
	
	/**
	 * Gets the.
	 *
	 * @return the collision effect
	 */
	@Override
	public CollisionEffect get() {
		if(images == null) {
			this.images = new ArrayList<Image>();
			try {
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/uppercut_hit_blood_1.gif"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/uppercut_hit_blood_2.gif"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/uppercut_hit_blood_3.gif"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/uppercut_hit_blood_4.gif"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/uppercut_hit_blood_5.gif"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/uppercut_hit_blood_6.gif"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/uppercut_hit_blood_7.gif"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/uppercut_hit_blood_8.gif"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/uppercut_hit_blood_9.gif"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return this.collisionEffectFactory.createCollisionEffect(images, 0.5, 0.6);
	}
}
