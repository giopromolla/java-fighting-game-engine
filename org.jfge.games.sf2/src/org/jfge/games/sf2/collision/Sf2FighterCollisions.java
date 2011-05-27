package org.jfge.games.sf2.collision;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.jfge.api.collision.CollisionHandler;
import org.jfge.api.collision.CollisionHandlerParser;
import org.xml.sax.SAXException;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

/**
 * The Class sf2FighterCollisions.
 */
@Singleton
public final class Sf2FighterCollisions implements Provider<CollisionHandler> {
	
	/** The collision handler. */
	private CollisionHandler collisionHandler;
	
	/** The collision handler parser. */
	private CollisionHandlerParser collisionHandlerParser;
	
	/**
	 * Instantiates a new sf2 fighter collisions.
	 *
	 * @param collisionHandlerParser the collision handler parser
	 */
	@Inject
	private Sf2FighterCollisions(CollisionHandlerParser collisionHandlerParser) {
		this.collisionHandlerParser = collisionHandlerParser;
	}
	
	/* (non-Javadoc)
	 * @see com.google.inject.Provider#get()
	 */
	public CollisionHandler get() {
		if(collisionHandler == null) {
			try {
				this.collisionHandler = collisionHandlerParser.parseFromXmlFile("/org/jfge/games/sf2/collision/sf2FighterCollisions.xml");
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
