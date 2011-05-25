package org.jfge.api.projectile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jfge.spi.graphics.Image;
import org.jfge.spi.graphics.GraphicsFactory;
import org.jfge.spi.physics.SpritePhysics;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
final class ProjectileParserImpl implements ProjectileParser {

	private ProjectileFactory projectileFactory;
	
	private GraphicsFactory imageFactory;
	
	private Map<String, Provider<SpritePhysics>> spritePhysics;
	
	private final Logger logger;
	
	@Inject
	private ProjectileParserImpl(Logger logger,
			ProjectileFactory particleFactory, 
			GraphicsFactory imageFactory, 
			Map<String, Provider<SpritePhysics>> spritePhysics) {
		
		this.logger = logger;
		this.projectileFactory = particleFactory;
		this.imageFactory = imageFactory;
		this.spritePhysics = spritePhysics;
	}
	
	@Override
	public Projectile parseFromXmlFile(String file) throws ParserConfigurationException, SAXException, IOException {
		return parseParticle(file);
	}
	
	private List<Image> parseParticleStateImages(Node n) throws IOException {
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
	
	private Projectile parseParticle(String file) throws ParserConfigurationException, SAXException, IOException {
		/*
		 * setting up xml environment
		 */
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setIgnoringElementContentWhitespace(true);
		
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse(getClass().getResourceAsStream(file));
		
		String projectileName = doc.getFirstChild().getAttributes().getNamedItem("name").getNodeValue();
		
		String startState = doc.getFirstChild().getAttributes().getNamedItem("startState")
		.getNodeValue();
		
		
		
		NodeList nodes =  doc.getFirstChild().getChildNodes();
		List<ProjectileState> states = new ArrayList<ProjectileState>();
		
		
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			String nodeType = node.getNodeName();
			
			if(nodeType.equals("state")) {
				// reading state name
				String name = node.getAttributes().getNamedItem("name").getNodeValue();

				// reading ticks
				Node ticksNode = node.getAttributes().getNamedItem("ticks");
				int ticks = 1;
				if(ticksNode != null) {
					if((Integer.parseInt(ticksNode.getNodeValue())) > 0) {
						ticks = Integer.parseInt(ticksNode.getNodeValue());
					}
				}
				
				// reading state loop
				boolean loop = Boolean.parseBoolean(node.getAttributes().getNamedItem(
						"loop").getNodeValue());

				// reading next state
				String nextState = node.getAttributes().getNamedItem("nextState")
						.getNodeValue();

				// reading final state
				Node finalStateNode = node.getAttributes().getNamedItem("finalState");
				boolean finalState = false;
				if (finalStateNode != null) {
					finalState = Boolean.parseBoolean(finalStateNode.getNodeValue());
				}
				
				// reading final state
				Node damageNode = node.getAttributes().getNamedItem("damage");
				int damage = 0;
				if (damageNode != null) {
					damage = Integer.parseInt(damageNode.getNodeValue());
				}

				// reading sprite physics
				SpritePhysics physics = null;
				Node physicsNode = node.getAttributes().getNamedItem("move");
				if (physicsNode != null) {
					Provider<SpritePhysics> provider = spritePhysics.get(physicsNode
							.getNodeValue());

					if (provider != null)
						physics = provider.get();
				}
			
				// reading state images
				List<Image> images = this.parseParticleStateImages(node);
				states.add(projectileFactory.createProjectileState(name, images, ticks, loop, nextState, finalState, physics, damage));
			}
		}
		
		return projectileFactory.createProjectile(projectileName, states, startState);
	}

}
