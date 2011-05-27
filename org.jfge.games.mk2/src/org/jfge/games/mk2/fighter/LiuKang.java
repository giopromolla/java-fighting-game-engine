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
 * The Class LiuKang.
 */
@Singleton
public class LiuKang implements Provider<Fighter> {

	/** The liu kang. */
	private Fighter liuKang;
	
	/** The fighter factory. */
	private FighterParser fighterFactory;
	
	/**
	 * Instantiates a new liu kang.
	 *
	 * @param fighterFactory the fighter factory
	 */
	@Inject
	public LiuKang(FighterParser fighterFactory) {
		this.fighterFactory = fighterFactory;
	}
	
	/* (non-Javadoc)
	 * @see com.google.inject.Provider#get()
	 */
	@Override
	public Fighter get() {
		if(liuKang == null) {
			try {
				liuKang = fighterFactory.parseFromXmlFile("/org/jfge/games/mk2/fighter/liukang/liukang.xml");
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
		
		return liuKang;
	}

}
