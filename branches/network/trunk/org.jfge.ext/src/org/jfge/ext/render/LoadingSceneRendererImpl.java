package org.jfge.ext.render;

import java.util.logging.Logger;

import org.jfge.api.render.SpriteRenderer;
import org.jfge.spi.graphics.Graphics;
import org.jfge.spi.graphics.GraphicsFactory;
import org.jfge.spi.graphics.Image;
import org.jfge.spi.render.LoadingSceneRenderer;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

@Singleton
public class LoadingSceneRendererImpl implements LoadingSceneRenderer {

	private SpriteRenderer spriteRenderer;
	
	private int height;
	
	private int width;
	
	private Logger logger;
	
	private org.jfge.spi.graphics.Color backgroundColor;
	
	@Inject
	public LoadingSceneRendererImpl(SpriteRenderer spriteRenderer,
			GraphicsFactory graphicsFactory,
			@Named("engine.height") int height,
    		@Named("engine.width") int width,
    		Logger logger) {
		
		this.spriteRenderer = spriteRenderer;
		this.logger = logger;
		
		backgroundColor = graphicsFactory.createColor(org.jfge.spi.graphics.Color.BLACK);
		
		this.height = height;
		this.width = width;
	}

	@Override
	public void drawBackground(Graphics g, Image backGround, int x, int y) {
		g.setGraphicsColor(backgroundColor);
		g.drawFillRectangle(0, 0, this.width, this.height);
	}

	@Override
	public void drawSprite(Graphics graphics, Image image, int x, int y, int dir) {
		spriteRenderer.drawSprite(graphics, image, x, y, dir);
	}
}
