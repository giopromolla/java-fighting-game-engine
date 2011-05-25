package org.jfge.api.fighter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jfge.api.effect.CollisionEffect;
import org.jfge.api.projectile.Projectile;
import org.jfge.api.render.SpriteRenderer;
import org.jfge.api.sprite.Sprite;
import org.jfge.spi.graphics.Image;
import org.jfge.spi.graphics.GraphicsFactory;
import org.jfge.spi.physics.SpritePhysics;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

/**
 * The Class FighterParserImpl.
 */
@Singleton
public final class FighterParserImpl implements FighterParser {

	/** The logger. */
	public final Logger logger;

	/** The image factory. */
	private GraphicsFactory imageFactory;

	/** The sprite physics. */
	private Map<String, Provider<SpritePhysics>> spritePhysics;

	/** The collision effects. */
	private Map<String, Provider<CollisionEffect>> collisionEffects;

	/** The projectiles. */
	private Map<String, Projectile> projectiles;
	
	/** The fighter factory. */
	private FighterFactory fighterFactory;
	
	/**
	 * Instantiates a new fighter parser impl.
	 *
	 * @param logger the logger
	 * @param imageFactory the image factory
	 * @param spritePhysics the sprite physics
	 * @param collisionEffects the collision effects
	 * @param inputBufferQueueProvider the input buffer queue provider
	 * @param spriteRendererProvider the sprite renderer provider
	 * @param fighterFactory the fighter factory
	 * @param projectiles the projectiles
	 */
	@Inject
	public FighterParserImpl(Logger logger, GraphicsFactory imageFactory,
			Map<String, Provider<SpritePhysics>> spritePhysics,
			Map<String, Provider<CollisionEffect>> collisionEffects,
			Provider<InputQueue> inputBufferQueueProvider,
			Provider<SpriteRenderer> spriteRendererProvider,
			FighterFactory fighterFactory,
			Map<String, Projectile> projectiles) {
		this.logger = logger;
		this.imageFactory = imageFactory;
		this.spritePhysics = spritePhysics;
		this.collisionEffects = collisionEffects;
		this.fighterFactory = fighterFactory;
		this.projectiles = projectiles;
	}

	/**
	 * Parses the fighter state images.
	 *
	 * @param n the n
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private List<Image> parseFighterStateImages(Node n) throws IOException {
		List<Image> images = new ArrayList<Image>();
		
		NodeList nodes = n.getChildNodes();
		
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			String nodeType = node.getNodeName();
			
			if(nodeType.equals("image")) {
				// reading image src
				String src = node.getAttributes().getNamedItem("src").getNodeValue();
				images.add(imageFactory.createImage(src));
			}
		}
		
		return images;
	}
	
	/* (non-Javadoc)
	 * @see org.jfge.api.fighter.FighterParser#parseFromXmlFile(java.lang.String)
	 */
	@Override
	public Fighter parseFromXmlFile(String file) throws DOMException, IOException,
			ParserConfigurationException, SAXException {

		/*
		 * parsing fighter into dom and return new fighter instance
		 */
		Document doc = parseToDom(file);
		Node root = doc.getFirstChild();

		return parseFighter(root);
	}

	/**
	 * Parses the to dom.
	 *
	 * @param fileName the file name
	 * @return the document
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws SAXException the sAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private Document parseToDom(String fileName)
			throws ParserConfigurationException, SAXException, IOException {
		/*
		 * setting up xml environment
		 */
		DocumentBuilderFactory domFactory = DocumentBuilderFactory
				.newInstance();
		domFactory.setIgnoringElementContentWhitespace(true);

		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse(getClass().getResourceAsStream(fileName));

		return doc;
	}

	/**
	 * Parses the fighter.
	 *
	 * @param root the root
	 * @return the fighter
	 * @throws DOMException the dOM exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private Fighter parseFighter(Node root) throws DOMException, IOException {
		List<FighterState> fighterStates = new ArrayList<FighterState>();

		// getting the fighters name
		String fighterName = root.getAttributes().getNamedItem("name").getNodeValue();

		String startState = root.getAttributes().getNamedItem("startState").getNodeValue();
		
		String endState = root.getAttributes().getNamedItem("endState").getNodeValue();
		
		String victoryState = root.getAttributes().getNamedItem("victoryState").getNodeValue();
		
		Image portrait = imageFactory.createImage(root.getAttributes().getNamedItem("portrait").getNodeValue());
		
		NodeList nodes = root.getChildNodes();

		/*
		 * iterating over all fighter document nodes and parsing the found
		 * elements
		 */
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			String nodeType = node.getNodeName();

			if (nodeType.equals("state")) {
				FighterState fighterState = parseFighterState(node, root);
				fighterStates.add(fighterState);
			}
		}

		// create and return fighter instance
		return fighterFactory.createFighter(fighterName, portrait, fighterStates, startState, endState, victoryState);
	}

	/**
	 * Parses the fighter state.
	 *
	 * @param state the state
	 * @param root the root
	 * @return the fighter state
	 * @throws DOMException the dOM exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private FighterState parseFighterState(Node state, Node root)
			throws DOMException, IOException {

		// reading state name
		String name = state.getAttributes().getNamedItem("name").getNodeValue();

		// reading damage
		Node damageNode = state.getAttributes().getNamedItem("damage");
		int damage = 0;
		if (damageNode != null) {
			damage = Integer.parseInt(damageNode.getNodeValue());
		}
		
		// loading state images
		List<Image> images = parseFighterStateImages(state);
		
		// reading ticks
		Node ticksNode = state.getAttributes().getNamedItem("ticks");
		int ticks = 1;
		if(ticksNode != null) {
			if((Integer.parseInt(ticksNode.getNodeValue())) > 0) {
				ticks = Integer.parseInt(ticksNode.getNodeValue());
			}
		}
		
		// reading state loop
		boolean loop = Boolean.parseBoolean(state.getAttributes().getNamedItem("loop").getNodeValue());

		// reading next state
		String nextState = state.getAttributes().getNamedItem("nextState").getNodeValue();

		// reading final state
		Node finalStateNode = state.getAttributes().getNamedItem("finalState");
		boolean finalState = false;
		if (finalStateNode != null) {
			finalState = Boolean.parseBoolean(finalStateNode.getNodeValue());
		}
		
		// reading sprite physics
		SpritePhysics physics = null;
		Node physicsNode = state.getAttributes().getNamedItem("move");
		if (physicsNode != null) {
			Provider<SpritePhysics> provider = spritePhysics.get(physicsNode
					.getNodeValue());

			if (provider != null)
				physics = provider.get();
		}

		// reading collision effect
		CollisionEffect effect = null;
		Node collisionEffectNode = state.getAttributes().getNamedItem("effect");
		if (collisionEffectNode != null) {
			Provider<CollisionEffect> provider = collisionEffects
					.get(collisionEffectNode.getNodeValue());

			if (provider != null)
				effect = provider.get();
		}

		// reading projectile
		Projectile projectile = null;
		Node projectileNode = state.getAttributes().getNamedItem("projectile");
		if(projectileNode != null) {
			projectile = projectiles.get(projectileNode.getNodeValue());
		}
		
		// reading state transitions
		HashMap<List<String>, String> stateTransitions = parseFighterStateTransitions(
				name, root);

		// create and return fighter state
		return fighterFactory.createFighterState(name, damage, images, ticks, loop, nextState, finalState, stateTransitions, physics, effect, projectile);
	}

	/**
	 * Parses the fighter state transitions.
	 *
	 * @param state the state
	 * @param root the root
	 * @return the hash map
	 */
	private HashMap<List<String>, String> parseFighterStateTransitions(
			String state, Node root) {
		HashMap<List<String>, String> transitions = new HashMap<List<String>, String>();
		NodeList nodes = root.getChildNodes();

		// look if we can find transitions according to the specified state.
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			String nodeType = node.getNodeName();

			if (nodeType.equals("transition")
					&& node.getAttributes().getNamedItem("state")
							.getNodeValue().equals(state)) {

				List<String> list = new ArrayList<String>();
				
				/*
				 * found transition to the specified state. Now we're parsing
				 * the attributes and add the transitions to our transitions
				 * maps.
				 */
				String[] events = node.getAttributes().getNamedItem("event").getNodeValue().split(",");
				String nextState = node.getAttributes().getNamedItem("nextState").getNodeValue();
				Integer direction;
				
				Node directionNode = node.getAttributes().getNamedItem("direction");
				if(directionNode != null) {
					String dir = directionNode.getNodeValue();
					if(dir.equals("left")) {
						direction = Sprite.LEFT;
					} else {
						direction = Sprite.RIGHT;
					}
					list.add(direction.toString());
				}
				
				for(String event: events) {
					list.add(event.trim());	
				}
				
				transitions.put(list, nextState);
			}
		}

		return transitions;
	}
}
