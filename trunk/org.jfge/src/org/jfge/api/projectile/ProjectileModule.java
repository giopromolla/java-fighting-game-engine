package org.jfge.api.projectile;

import org.jfge.api.effect.CollisionEffect;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;

public final class ProjectileModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ProjectileParser.class).to(ProjectileParserImpl.class);
		bind(ProjectileFactory.class).to(ProjectileFactoryImpl.class);
		
		MapBinder<String, Projectile> projectileBinder = MapBinder.newMapBinder(binder(), String.class, Projectile.class);
		
	}

}
