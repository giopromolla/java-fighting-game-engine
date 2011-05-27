package org.jfge.games.sfvsmk2.collision;

import org.jfge.api.collision.CollisionHandler;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;

public class SfVsMk2CollisionModule extends AbstractModule {

	@Override
	protected void configure() {
		MapBinder<String, CollisionHandler> collisionHandlerBinder = MapBinder.newMapBinder(binder(), String.class, CollisionHandler.class);
		collisionHandlerBinder.addBinding("sfvsmk2FighterCollisions").toProvider(SfVsMk2FighterCollisions.class);	
		collisionHandlerBinder.addBinding("sfvsmk2ProjectileCollisions").toProvider(SfVsMk2ProjectileCollisions.class);	
		
	}

}
