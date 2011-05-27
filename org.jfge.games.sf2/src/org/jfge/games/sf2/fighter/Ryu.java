package org.jfge.games.sf2.fighter;

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
 * The Class Ryu.
 */
@Singleton
public class Ryu implements Provider<Fighter> {

	/** The ryu. */
	private Fighter ryu;
	
	/** The fighter factory. */
	private FighterParser fighterFactory;
	
	/**
	 * Instantiates a new ryu.
	 *
	 * @param fighterFactory the fighter factory
	 */
	@Inject
	public Ryu(FighterParser fighterFactory) {
		this.fighterFactory = fighterFactory;
	}
	
	/* (non-Javadoc)
	 * @see com.google.inject.Provider#get()
	 */
	@Override
	public Fighter get() {
		if(ryu == null) {
			try {
				ryu = fighterFactory.parseFromXmlFile("/org/jfge/games/sf2/fighter/ryu/ryu.xml");
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
		
		return ryu;
	}

}
