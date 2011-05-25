package org.jfge.ext.scene;

import java.io.IOException;
import java.util.logging.Logger;

import org.jfge.api.sprite.Sprite;
import org.jfge.spi.graphics.Graphics;
import org.jfge.spi.graphics.Image;
import org.jfge.spi.graphics.GraphicsFactory;
import org.jfge.spi.render.LoadingSceneRenderer;
import org.jfge.spi.scene.Scene;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class LoadingSceneImpl implements Scene{

	private LoadingSceneRenderer loadingSceneRenderer;
	
	private GraphicsFactory imageFactory;
	
	private Image[] bigGears;
	
	private Image[] smallGears;
	
	
	private int index;
	
	private Logger logger;
	
	
	@Inject
	public LoadingSceneImpl(LoadingSceneRenderer loadingSceneRenderer, 
			GraphicsFactory imageFactory,
			Logger logger) throws IOException {
		this.loadingSceneRenderer = loadingSceneRenderer;
		this.imageFactory = imageFactory;
		this.logger = logger;
		
		// loading big gears images
		this.bigGears = new Image[4];
		this.bigGears[0] = imageFactory.createImage("/org/jfge/ext/scene/images/bigGear_1.png");
		this.bigGears[1] = imageFactory.createImage("/org/jfge/ext/scene/images/bigGear_2.png");
		this.bigGears[2] = imageFactory.createImage("/org/jfge/ext/scene/images/bigGear_3.png");
		this.bigGears[3] = imageFactory.createImage("/org/jfge/ext/scene/images/bigGear_4.png");
		
		this.smallGears = new Image[4];
		this.smallGears[0] = imageFactory.createImage("/org/jfge/ext/scene/images/smallGear_1.png");
		this.smallGears[1] = imageFactory.createImage("/org/jfge/ext/scene/images/smallGear_2.png");
		this.smallGears[2] = imageFactory.createImage("/org/jfge/ext/scene/images/smallGear_3.png");
		this.smallGears[3] = imageFactory.createImage("/org/jfge/ext/scene/images/smallGear_4.png");
		
	}
	
	@Override
	public void update() {
		index = (index+1) % 4;
	}

	@Override
	public void render(Graphics graphics) {
		loadingSceneRenderer.drawBackground(graphics, null, 0, 0);
		loadingSceneRenderer.drawSprite(graphics, smallGears[3-index], 385, 215, Sprite.RIGHT);
		loadingSceneRenderer.drawSprite(graphics, bigGears[index], 410, 240, Sprite.RIGHT);
	}

}
