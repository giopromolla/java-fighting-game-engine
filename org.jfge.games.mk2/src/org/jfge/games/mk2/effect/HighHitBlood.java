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
 * The Class HighHitBlood.
 */
public final class HighHitBlood implements Provider<CollisionEffect> {

	/** The images. */
	private List<Image> images;
	
	/** The collision effect factory. */
	private CollisionEffectFactory collisionEffectFactory;
	
	/** The graphics factory. */
	private GraphicsFactory graphicsFactory;
	
	/**
	 * Instantiates a new high hit blood.
	 *
	 * @param graphicsFactory the graphics factory
	 * @param collisionEffectFactory the collision effect factory
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Inject
	public HighHitBlood(GraphicsFactory graphicsFactory, CollisionEffectFactory collisionEffectFactory) throws IOException {
		this.graphicsFactory = graphicsFactory;
		this.collisionEffectFactory = collisionEffectFactory;
		
	}
	
	/* (non-Javadoc)
	 * @see com.google.inject.Provider#get()
	 */
	@Override
	public CollisionEffect get() {
		if(this.images == null) {
			this.images = new ArrayList<Image>();
			try {
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/high_hit_blood_1.gif"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/high_hit_blood_2.gif"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/high_hit_blood_3.gif"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/high_hit_blood_4.gif"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/high_hit_blood_5.gif"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/high_hit_blood_6.gif"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/high_hit_blood_7.gif"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return this.collisionEffectFactory.createCollisionEffect(images, 0.1, 0.8);
	}

}
