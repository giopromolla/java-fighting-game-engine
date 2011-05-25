package org.jfge.api.collision;

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
 * The Class CollisionHandlerParserImpl.
 */
@Singleton
public final class CollisionHandlerParserImpl implements CollisionHandlerParser {
	/** The logger. */
	private final Logger logger;
	
	/** The collision handler factory. */
	private CollisionHandlerFactory collisionHandlerFactory;
	
	/**
	 * Instantiates a new xml collision handler factory.
	 *
	 * @param logger the logger
	 * @param collisionHandlerFactory the collision handler factory
	 */
	@Inject
	public CollisionHandlerParserImpl(Logger logger, CollisionHandlerFactory collisionHandlerFactory) {
		this.logger = logger;
		this.collisionHandlerFactory = collisionHandlerFactory;
	}
	
	/* (non-Javadoc)
	 * @see org.jfge.collision.CollisionHandlerFactory#createCollisionHandler(java.lang.String)
	 */
	@Override
	public CollisionHandler parseFromXmlFile(String file) throws ParserConfigurationException, SAXException, IOException {
		return parseCollisionHandler(file);
	}

	/**
	 * Parses the collision handler.
	 *
	 * @param file the file
	 * @return the collision handler
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws SAXException the sAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private CollisionHandler parseCollisionHandler(String file) throws ParserConfigurationException, SAXException, IOException {
		/*
		 * setting up xml environment
		 */
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setIgnoringElementContentWhitespace(true);
		
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse(getClass().getResourceAsStream(file));
		
		NodeList nodes =  doc.getFirstChild().getChildNodes();
		HashMap<List<String>, String> collisions = new HashMap<List<String>, String>();
		
		// look if we can find some collision nodes
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			String nodeType = node.getNodeName();
			
			if(nodeType.equals("collision")) {
				List<String> tuple = new ArrayList<String>();
				
				tuple.add(node.getAttributes().getNamedItem("curState").getNodeValue());
				tuple.add(node.getAttributes().getNamedItem("colState").getNodeValue());
				
				String reaction = node.getAttributes().getNamedItem("reaction").getNodeValue();
				collisions.put(tuple, reaction);
			}
		}
		
		return this.collisionHandlerFactory.createCollisionHandler(collisions);
	}

}
