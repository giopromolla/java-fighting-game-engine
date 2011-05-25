package org.jfge.j2se.graphics;

import org.jfge.api.collision.RectangleCollisionStrategy;
import org.jfge.j2se.controller.J2SeKeyboardController;
import org.jfge.spi.collision.CollisionDetectionStrategy;
import org.jfge.spi.controller.Controller;
import org.jfge.spi.graphics.GraphicsFactory;
import org.jfge.spi.graphics.GraphicsProvider;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;

/**
 * The Class GraphicsModule.
 */
public final class J2SeGraphicsModule extends AbstractModule {

	/* (non-Javadoc)
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		/*
		 * binding graphics provider, image factory and collision strategy implementations 
		 */
		bind(GraphicsProvider.class).to(J2SeGraphicsProvider.class);
		bind(GraphicsFactory.class).to(J2SeGraphicsFactory.class);
		
		/*
		 * making keyboard controller available via multibind
		 */
		bind(Controller.class).annotatedWith(Names.named("keyboard.j2se")).to(J2SeKeyboardController.class);
		
		Multibinder<Controller> controllerBinder = Multibinder.newSetBinder(binder(), Controller.class);
		controllerBinder.addBinding().to(J2SeKeyboardController.class);
	}
}
