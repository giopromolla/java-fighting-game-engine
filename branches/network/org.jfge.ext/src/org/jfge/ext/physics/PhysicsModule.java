package org.jfge.ext.physics;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.jfge.spi.physics.SpritePhysics;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.name.Names;

public final class PhysicsModule extends AbstractModule {

	@Override
	protected void configure() {
		loadProperties(binder());
		
		MapBinder<String, SpritePhysics> spritePhysicsBinder = MapBinder.newMapBinder(binder(), String.class, SpritePhysics.class);
		spritePhysicsBinder.addBinding("moveForward").to(MoveForward.class);
		spritePhysicsBinder.addBinding("moveBackward").to(MoveBackward.class);
		spritePhysicsBinder.addBinding("jump").to(Jump.class);
		spritePhysicsBinder.addBinding("jumpForward").to(JumpForward.class);
		spritePhysicsBinder.addBinding("jumpBackward").to(JumpBackward.class);
		spritePhysicsBinder.addBinding("flying").to(Flying.class);
	}
	
	/**
	 * Load properties.
	 *
	 * @param binder the binder
	 */
	private void loadProperties(Binder binder) {
	    InputStream stream = PhysicsModule.class.getResourceAsStream("/org/jfge/config/physics/physics.properties");
	    Properties engineProperties = new Properties();
	    try {
	      engineProperties.load(stream);
	      Names.bindProperties(binder, engineProperties);
	    } catch (IOException e) {
	    	binder.addError(e);
	    }
	  }
}
