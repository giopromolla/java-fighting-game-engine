package org.jfge.api.ai;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * The Class AiControllerParserImpl.
 */
@Singleton
public final class AiControllerParserImpl implements AiControllerParser {

	/** The ai controller factory. */
	private AiControllerFactory aiControllerFactory;
	
	/** The logger. */
	private final Logger logger;
	
	/**
	 * Instantiates a new ai controller parser impl.
	 *
	 * @param logger the logger
	 * @param aiControllerFactory the ai controller factory
	 */
	@Inject
	public AiControllerParserImpl(Logger logger,
			AiControllerFactory aiControllerFactory) {
		this.aiControllerFactory = aiControllerFactory;
		this.logger = logger;
	}
	
	/* (non-Javadoc)
	 * @see org.jfge.ai.AiControllerParser#parseAiController(java.lang.String)
	 */
	@Override
	public AiController parseFromXmlFile(String file) throws ParserConfigurationException, SAXException, IOException {
		return this.parseXmlAiController(file);
	}
	
	/**
	 * Parses the xml ai controller.
	 *
	 * @param file the file
	 * @return the ai controller
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws SAXException the sAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private AiController parseXmlAiController(String file) throws ParserConfigurationException, SAXException, IOException {
		/*
		 * setting up xml environment
		 */
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setIgnoringElementContentWhitespace(true);
		
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse(getClass().getResourceAsStream(file));
		
		NodeList nodes =  doc.getFirstChild().getChildNodes();
		HashMap<List<String>, String> transitions = new HashMap<List<String>, String>();
		
		
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			String nodeType = node.getNodeName();
			
			if(nodeType.equals("transition")) {
				List<String> tuple = new ArrayList<String>();
				
				tuple.add(node.getAttributes().getNamedItem("dist").getNodeValue());
				tuple.add(node.getAttributes().getNamedItem("obsrvState").getNodeValue());
				tuple.add(node.getAttributes().getNamedItem("oppState").getNodeValue());
				
				String reaction = node.getAttributes().getNamedItem("reaction").getNodeValue();
				transitions.put(tuple, reaction);
			}
		}
		
		return this.aiControllerFactory.createAiController(transitions);
	}

}
