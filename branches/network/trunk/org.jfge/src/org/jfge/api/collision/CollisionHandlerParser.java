package org.jfge.api.collision;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

/**
 * A factory for creating CollsionHandler objects.
 */
public interface CollisionHandlerParser {
	
	/**
	 * Creates a new CollsionHandler object.
	 *
	 * @param file the file
	 * @return the collision handler
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	CollisionHandler parseFromXmlFile(String file) throws ParserConfigurationException, SAXException, IOException;
}
