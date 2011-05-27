package org.jfge.games.sf2.projectile;

import org.jfge.api.projectile.Projectile;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;

public class StreetFighter2ProjectileModule extends AbstractModule {

	@Override
	protected void configure() {
		MapBinder<String, Projectile> projectileBinder = MapBinder.newMapBinder(binder(), String.class, Projectile.class);
	
		projectileBinder.addBinding("hadouken").toProvider(Hadouken.class);
	}

}
