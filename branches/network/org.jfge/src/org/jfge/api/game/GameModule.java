package org.jfge.api.game;

import com.google.inject.AbstractModule;

public class GameModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(GameFactory.class).to(GameFactoryImpl.class);
	}

}
