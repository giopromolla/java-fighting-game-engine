package org.jfge.android.graphics;

import org.jfge.android.controller.AndroidKeyboardController;
import org.jfge.spi.controller.Controller;
import org.jfge.spi.graphics.GraphicsFactory;
import org.jfge.spi.graphics.GraphicsProvider;

import android.app.Activity;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;


public class AndroidGraphicsModule extends AbstractModule {

	private Activity activity;
	
	public AndroidGraphicsModule(Activity activity) {
		this.activity = activity;
	}
	
	@Override
	protected void configure() {
		bind(Activity.class).toInstance(activity);
		/*
		 * binding graphics, image and collision implementations
		 */
		bind(GraphicsFactory.class).to(AndroidGraphicsFactory.class);
		bind(GraphicsProvider.class).to(AndroidGraphicsProvider.class);
	
		/*
		 * making keyboard controller available via multibind
		 */
		bind(Controller.class).annotatedWith(Names.named("keyboard.android")).to(AndroidKeyboardController.class);
		bind(Controller.class).to(AndroidKeyboardController.class);
		
		Multibinder<Controller> controllerBinder = Multibinder.newSetBinder(binder(), Controller.class);
		controllerBinder.addBinding().to(AndroidKeyboardController.class);
	}
}
