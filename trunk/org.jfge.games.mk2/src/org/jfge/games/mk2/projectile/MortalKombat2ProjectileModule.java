package org.jfge.games.mk2.projectile;

import org.jfge.api.projectile.Projectile;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;

/**
 * The Class MortalKombat2ProjectileModule.
 */
public final class MortalKombat2ProjectileModule extends AbstractModule {

	/* (non-Javadoc)
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		MapBinder<String, Projectile> projectileBinder = MapBinder.newMapBinder(binder(), String.class, Projectile.class);
		projectileBinder.addBinding("liukangFireball").toProvider(LiuKangFireball.class);
		projectileBinder.addBinding("kanoFireball").toProvider(KanoFireball.class);
		projectileBinder.addBinding("johnnyCageFireball").toProvider(JohnnyCageFireball.class);	
	}
}
