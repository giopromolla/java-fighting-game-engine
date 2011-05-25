package org.jfge.api.fighter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.jfge.api.effect.CollisionEffect;
import org.jfge.api.projectile.Projectile;
import org.jfge.spi.physics.SpritePhysics;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.name.Names;

/**
 * The Class FighterModule.
 */
public final class FighterModule extends AbstractModule {

	/* (non-Javadoc)
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		loadProperties(binder());
		bind(FighterFactory.class).to(FighterFactoryImpl.class);
		bind(FighterParser.class).to(FighterParserImpl.class);
		bind(InputQueue.class).to(BufferedInputQueue.class);

		MapBinder<String, SpritePhysics> physicsBinder = MapBinder.newMapBinder(binder(), String.class, SpritePhysics.class);
		MapBinder<String, CollisionEffect> collisionEffectBinder = MapBinder.newMapBinder(binder(), String.class, CollisionEffect.class);
		MapBinder<String, Projectile> projectileBinder = MapBinder.newMapBinder(binder(), String.class, Projectile.class);
		
	}
	
	/**
	 * Load properties.
	 *
	 * @param binder the binder
	 */
	private void loadProperties(Binder binder) {
	    InputStream stream = FighterModule.class.getResourceAsStream("/org/jfge/config/fighter/fighter.properties");
	    Properties fighterProperties = new Properties();
	    try {
	      fighterProperties.load(stream);
	      Names.bindProperties(binder, fighterProperties);
	    } catch (IOException e) {
	    	binder.addError(e);
	    }
	  }
}

