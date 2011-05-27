package org.jfge.games.mk2.effect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jfge.api.effect.ArenaEffect;
import org.jfge.api.effect.ArenaEffectFactory;
import org.jfge.spi.graphics.GraphicsFactory;
import org.jfge.spi.graphics.Image;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public final class FightAnimation implements Provider<ArenaEffect>{

	private ArenaEffectFactory arenaEffectFactory;
	
	private GraphicsFactory graphicsFactory;
	
	private ArenaEffect fightAnimation;
	
	private List<Image> images;
	
	@Inject
	public FightAnimation(ArenaEffectFactory arenaEffectFactory,
			GraphicsFactory graphicsFactory) {
		super();
		this.arenaEffectFactory = arenaEffectFactory;
		this.graphicsFactory = graphicsFactory;
		this.images = new ArrayList<Image>();
	}



	@Override
	public ArenaEffect get() {
		if(fightAnimation == null) {
			try {
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/fight_1.png"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/fight_2.png"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/fight_3.png"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/fight_4.png"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/fight_5.png"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/fight_6.png"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/fight_7.png"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/fight_8.png"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/fight_9.png"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/fight_10.png"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/fight_11.png"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/fight_12.png"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/fight_13.png"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/fight_14.png"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/fight_15.png"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/fight_16.png"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/fight_17.png"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/fight_18.png"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/fight_19.png"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/fight_20.png"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/fight_21.png"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/fight_22.png"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/fight_23.png"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/fight_24.png"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/fight_25.png"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/fight_26.png"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/fight_27.png"));
				this.images.add(graphicsFactory.createImage("/org/jfge/games/mk2/effect/images/fight_28.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			fightAnimation = arenaEffectFactory.createArenaEffect(images, 240, 120);
		}
		
		return this.fightAnimation;
	}

}
