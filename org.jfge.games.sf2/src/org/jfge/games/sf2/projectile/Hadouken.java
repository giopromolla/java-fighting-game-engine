package org.jfge.games.sf2.projectile;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.jfge.api.projectile.Projectile;
import org.jfge.api.projectile.ProjectileParser;
import org.xml.sax.SAXException;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public final class Hadouken implements Provider<Projectile> {

	private Projectile projectile;
	
	private ProjectileParser projectileParser;
	
	@Inject
	public Hadouken(ProjectileParser projectileParser) throws ParserConfigurationException, SAXException, IOException {
		this.projectileParser = projectileParser;
	}
	
	@Override
	public Projectile get() {
		if(this.projectile == null) {
			try {
				this.projectile = projectileParser.parseFromXmlFile("/org/jfge/games/sf2/projectile/shoryuken/hadouken.xml");
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
