package org.jfge.games.mk2.collision;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.jfge.api.collision.CollisionHandler;
import org.jfge.api.collision.CollisionHandlerParser;
import org.xml.sax.SAXException;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

/**
 * The Class SfVsMk2ProjectileCollisions.
 */
@Singleton
public class MortalKomat2ProjectileCollisions implements Provider<CollisionHandler> {
	
	/** The collision handler. */
	private CollisionHandler collisionHandler;
	
	/** The collision handler parser. */
	private CollisionHandlerParser collisionHandlerParser;
	
	/**
	 * Instantiates a new sf vs mk2 projectile collisions.
	 *
	 * @param collisionHandlerParser the collision handler parser
	 */
	@Inject
	public MortalKomat2ProjectileCollisions(CollisionHandlerParser collisionHandlerParser) {
		this.collisionHandlerParser = collisionHandlerParser;
	}
	
	/* (non-Javadoc)
	 * @see com.google.inject.Provider#get()
	 */
	@Override
	public CollisionHandler get() {
		if(collisionHandler == null) {
			try {
				this.collisionHandler = collisionHandlerParser.parseFromXmlFile("/org/jfge/games/mk2/collision/MortalKombat2ProjectileCollisions.xml");
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return this.collisionHandler;
	}
}
