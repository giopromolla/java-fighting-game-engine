package org.jfge.api.fighter;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

/**
 * A factory for creating Fighter objects.
 */
public interface FighterParser {
	
	/**
	 * Creates a new Fighter object.
	 *
	 * @param file the name
	 * @return the fighter
	 * @throws IOException 
	 * @throws DOMException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws Exception 
	 */
	public Fighter parseFromXmlFile(String file) throws DOMException, IOException, ParserConfigurationException, SAXException;
}
