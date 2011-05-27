package org.jfge.games.sf2.fighter;

import org.jfge.api.fighter.Fighter;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;

public class StreetFighter2FighterModule extends AbstractModule {

	@Override
	protected void configure() {
		MapBinder<String, Fighter> fighterBinder = MapBinder.newMapBinder(binder(), String.class, Fighter.class);
		fighterBinder.addBinding("ryu").toProvider(Ryu.class);
		fighterBinder.addBinding("blanka").toProvider(Blanka.class);
	}

}
