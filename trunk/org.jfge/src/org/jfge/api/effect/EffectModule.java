package org.jfge.api.effect;


import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryProvider;
import com.google.inject.multibindings.MapBinder;

public final class EffectModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(CollisionEffectFactory.class).toProvider(FactoryProvider.newFactory(CollisionEffectFactory.class, CollisionEffectImpl.class));	
		bind(ArenaEffectFactory.class).toProvider(FactoryProvider.newFactory(ArenaEffectFactory.class, ArenaEffectImpl.class));
		
		MapBinder<String, CollisionEffect> collisionEffectBinder = MapBinder.newMapBinder(binder(), String.class, CollisionEffect.class);
		MapBinder<String, ArenaEffect> arenaEffectBinder = MapBinder.newMapBinder(binder(), String.class, ArenaEffect.class);
		
	}
}
