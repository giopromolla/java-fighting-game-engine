package org.jfge.games.sf2.renderer;

import org.jfge.spi.render.ArenaRenderer;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class StreetFighter2RenderModule extends AbstractModule {

	@Override
	protected void configure() {
		/*
		 * binding render implementations
		 */
		bind(ArenaRenderer.class).annotatedWith(Names.named("arenaRenderer.streetFighter2")).to(StreetFighter2ArenaRenderer.class);
	}

}
