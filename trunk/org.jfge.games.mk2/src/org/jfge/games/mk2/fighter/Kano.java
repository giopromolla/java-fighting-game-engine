package org.jfge.games.mk2.fighter;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.jfge.api.fighter.Fighter;
import org.jfge.api.fighter.FighterParser;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

/**
 * The Class Kano.
 */
@Singleton
public final class Kano implements Provider<Fighter> {

	/** The kano. */
	private Fighter kano;
	
	/** The fighter factory. */
	private FighterParser fighterFactory;
	
	/**
	 * Instantiates a new liu kang.
	 *
	 * @param fighterFactory the fighter factory
	 */
	@Inject
	public Kano(FighterParser fighterFactory) {
		this.fighterFactory = fighterFactory;
	}
	
	/* (non-Javadoc)
	 * @see com.google.inject.Provider#get()
	 */
	@Override
	public Fighter get() {
		if(kano == null) {
			try {
				kano = fighterFactory.parseFromXmlFile("/org/jfge/games/mk2/fighter/kano/kano.xml");
			} catch (DOMException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
		}
		
		return kano;
	}

}
