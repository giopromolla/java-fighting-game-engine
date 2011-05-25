package org.jfge.api.render;

import com.google.inject.AbstractModule;

public class RenderModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(SpriteRenderer.class).to(SpriteRendererImpl.class);
	}

}
