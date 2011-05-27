package org.jfge.games.sf2.fighter;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.jfge.api.fighter.Fighter;
import org.jfge.api.fighter.FighterParser;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * The Class Blanka.
 */
public class Blanka implements Provider<Fighter> {
	
	/** The blanka. */
	private Fighter blanka;
	
	/** The fighter factory. */
	private FighterParser fighterFactory;
	
	/**
	 * Instantiates a new blanka.
	 *
	 * @param fighterFactory the fighter factory
	 */
	@Inject
	public Blanka(FighterParser fighterFactory) {
		this.fighterFactory = fighterFactory;
	}
	
	/* (non-Javadoc)
	 * @see com.google.inject.Provider#get()
	 */
	@Override
	public Fighter get() {
		if(blanka == null) {
			try {
				blanka = fighterFactory.parseFromXmlFile("/org/jfge/games/sf2/fighter/blanka/blanka.xml");
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
		
		return blanka;
	}
}
