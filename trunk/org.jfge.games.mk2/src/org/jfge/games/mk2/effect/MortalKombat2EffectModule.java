package org.jfge.games.mk2.effect;

import org.jfge.api.effect.ArenaEffect;
import org.jfge.api.effect.CollisionEffect;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;

public class MortalKombat2EffectModule extends AbstractModule {

	@Override
	protected void configure() {
		MapBinder<String, ArenaEffect> arenaEffectBinder = MapBinder.newMapBinder(binder(), String.class, ArenaEffect.class);
		
		arenaEffectBinder.addBinding("FightAnimation").toProvider(FightAnimation.class);
		
		MapBinder<String, CollisionEffect> collisionEffectBinder = MapBinder.newMapBinder(binder(), String.class, CollisionEffect.class);
		
		collisionEffectBinder.addBinding("HighHitBlood").toProvider(HighHitBlood.class);
		collisionEffectBinder.addBinding("LowHitBlood").toProvider(LowHitBlood.class);
		collisionEffectBinder.addBinding("FallDownBlood").toProvider(FallDownBlood.class);
	}
}
