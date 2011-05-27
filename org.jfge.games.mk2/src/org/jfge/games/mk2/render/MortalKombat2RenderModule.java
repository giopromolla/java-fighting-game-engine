package org.jfge.games.mk2.render;

import org.jfge.spi.render.ArenaRenderer;
import org.jfge.spi.render.LoadingSceneRenderer;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class MortalKombat2RenderModule extends AbstractModule {

	@Override
	protected void configure() {
		/*
		 * binding render implementations
		 */
		bind(ArenaRenderer.class).annotatedWith(Names.named("arenaRenderer.mortalKombat2")).to(MortalKombat2ArenaRenderer.class);
	}

}
