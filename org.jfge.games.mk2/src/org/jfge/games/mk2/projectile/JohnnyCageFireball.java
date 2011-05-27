package org.jfge.games.mk2.projectile;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.jfge.api.projectile.Projectile;
import org.jfge.api.projectile.ProjectileParser;
import org.xml.sax.SAXException;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class JohnnyCageFireball implements Provider<Projectile> {
	
	/** The projectile. */
	private Projectile projectile;
	
	/** The projectile parser. */
	private ProjectileParser projectileParser;
	
	/**
	 * Instantiates a new liu kang fireball.
	 *
	 * @param projectileParser the projectile parser
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws SAXException the sAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Inject
	public JohnnyCageFireball(ProjectileParser projectileParser) throws ParserConfigurationException, SAXException, IOException {
		this.projectileParser = projectileParser;
	}
	
	/* (non-Javadoc)
	 * @see com.google.inject.Provider#get()
	 */
	@Override
	public Projectile get() {
		if(projectile == null) {
			try {
				this.projectile = projectileParser.parseFromXmlFile("/org/jfge/games/mk2/projectile/johnnycagefireball/johnnycagefireball.xml");
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		
		return projectile.create();
	}

}
