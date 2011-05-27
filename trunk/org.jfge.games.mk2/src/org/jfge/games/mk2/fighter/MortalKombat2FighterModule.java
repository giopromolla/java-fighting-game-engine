package org.jfge.games.mk2.fighter;

import org.jfge.api.fighter.Fighter;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;

/**
 * The Class MortalKombat2FighterModule.
 */
public final class MortalKombat2FighterModule extends AbstractModule {

	/* (non-Javadoc)
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		MapBinder<String, Fighter> fighterBinder = MapBinder.newMapBinder(binder(), String.class, Fighter.class);
		fighterBinder.addBinding("kano").toProvider(Kano.class);
		fighterBinder.addBinding("liuKang").toProvider(LiuKang.class);
		fighterBinder.addBinding("cyrax").toProvider(Cyrax.class);
		fighterBinder.addBinding("johnnyCage").toProvider(JohnnyCage.class);
		}

}
