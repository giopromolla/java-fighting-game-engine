package org.jfge.j2se.controller;

import org.jfge.spi.controller.Controller;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;


/**
 * The Class ControllerModule.
 */
public final class ControllerModule extends AbstractModule{

	/* (non-Javadoc)
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
//		bind(Controller.class).to(XboxController.class);
//		bind(Controller.class).annotatedWith(Names.named("keyboard.j2se")).to(KeyboardController.class);
//		bind(Controller.class).annotatedWith(Names.named("gamepad")).to(XboxController.class);
		
		Multibinder<Controller> controllerBinder = Multibinder.newSetBinder(binder(), Controller.class);
		controllerBinder.addBinding().to(J2SeKeyboardController.class);
	}

}
