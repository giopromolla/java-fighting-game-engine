package org.jfge.api.ai;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * The Interface AiControllerParser.
 */
public interface AiControllerParser {
	
	/**
	 * Parses the ai controller.
	 *
	 * @param file the file
	 * @return the ai controller
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws SAXException the sAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public AiController parseFromXmlFile(String file) throws ParserConfigurationException, SAXException, IOException;
}
