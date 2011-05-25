package org.jfge.api.arena;

import com.google.inject.AbstractModule;

public final class ArenaModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ArenaFactory.class).to(ArenaFactoryImpl.class);
	}

}
