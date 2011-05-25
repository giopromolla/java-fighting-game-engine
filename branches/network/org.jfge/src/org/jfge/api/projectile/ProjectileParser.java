package org.jfge.api.projectile;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * The Interface ProjectileParser.
 */
public interface ProjectileParser {
	
	/**
	 * Parses the from xml file.
	 *
	 * @param file the file
	 * @return the projectile
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws SAXException the sAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Projectile parseFromXmlFile(String file) throws ParserConfigurationException, SAXException, IOException;
}
