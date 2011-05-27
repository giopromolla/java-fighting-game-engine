package org.jfge.games.mk2.render;

import java.io.IOException;

import org.jfge.spi.graphics.Graphics;
import org.jfge.spi.graphics.GraphicsFactory;
import org.jfge.spi.graphics.Image;
import org.jfge.spi.render.ArenaRenderer;

import com.google.inject.Inject;

public class MortalKombat2ArenaRenderer implements ArenaRenderer {

	private Image lifebar;
	
	private Image roundSymbol;
	
	private org.jfge.spi.graphics.Color clockColor;
	
	private org.jfge.spi.graphics.Color lifeBarColor;
	
	private org.jfge.spi.graphics.Color textColor;
	
	private org.jfge.spi.graphics.Font lifeBarTextFont;
	
	private org.jfge.spi.graphics.Font textFont;
	
	private org.jfge.spi.graphics.Font clockFont;
	
	
	@Inject
	public MortalKombat2ArenaRenderer(GraphicsFactory graphicsFactory) throws IOException {
		this.lifebar = graphicsFactory.createImage("/org/jfge/games/mk2/arena/images/lifebar.png");
		this.roundSymbol = graphicsFactory.createImage("/org/jfge/games/mk2/arena/images/roundsymbol.png");
	
		clockColor = graphicsFactory.createColor(org.jfge.spi.graphics.Color.ORANGE);
		lifeBarColor = graphicsFactory.createColor(org.jfge.spi.graphics.Color.RED);
		textColor = graphicsFactory.createColor(org.jfge.spi.graphics.Color.YELLOW);
		
		lifeBarTextFont = graphicsFactory.createFont(org.jfge.spi.graphics.Font.DEFAULT, org.jfge.spi.graphics.Font.BOLD, 12);
		textFont = graphicsFactory.createFont(org.jfge.spi.graphics.Font.DEFAULT, org.jfge.spi.graphics.Font.BOLD, 18);
		clockFont = graphicsFactory.createFont(org.jfge.spi.graphics.Font.DEFAULT, org.jfge.spi.graphics.Font.BOLD, 20);
	}
	
	public void drawBackground(Graphics g, Image bgr, int x, int y) {
		g.drawImage(x, y, bgr);
	}

	@Override
	public void drawClock(Graphics g, int time) {
		g.setGraphicsColor(clockColor);
		g.setGraphicsFont(clockFont);
		g.drawString(227, 20+lifebar.getHeight(), String.valueOf(time));
	}

	@Override
	public void drawLifebar(Graphics g, String name, int health, Image portrait, int wins, int align) {
		if(align == ArenaRenderer.ALIGN_RIGHT) {
			g.drawImage(440, 0, portrait);
			g.drawImage(440-lifebar.getWidth(), 20, lifebar);
			g.setGraphicsColor(lifeBarColor);
			g.drawFillRectangle(440-lifebar.getWidth()+2, 23, (int) ((lifebar.getWidth()-6)*(100-health)/100.0), lifebar.getHeight()-6);
			g.setGraphicsColor(this.textColor);
			g.setGraphicsFont(this.lifeBarTextFont);
			g.drawString(440-(name.length()*10), 15, name);
			
			for(int i=0; i < wins; i++) {
				g.drawImage(350 - i*roundSymbol.getWidth(), 25 + lifebar.getHeight(), roundSymbol);
			}
		} else {
			g.drawImage(0, 0, portrait);
			g.drawImage(40, 20, lifebar.flip());
			g.setGraphicsColor(lifeBarColor);
			g.drawFillRectangle(43, 23, (int) ((lifebar.getWidth()-6)*(100-health)/100.0), lifebar.getHeight()-6);
			g.setGraphicsColor(this.textColor);
			g.setGraphicsFont(this.lifeBarTextFont);
			g.drawString(45, 15, name);
			
			for(int i=0; i < wins; i++) {
				g.drawImage(40 + i*roundSymbol.getWidth(), 25 + lifebar.getHeight(), roundSymbol);
				
			}
		}
	}

	@Override
	public void drawRound(Graphics g, int round) {
		g.setGraphicsColor(textColor);
		g.setGraphicsFont(textFont);
		g.drawString(200, 100, "Round " + round);
	}

	@Override
	public void drawWinner(Graphics g, String winner) {
		g.setGraphicsColor(textColor);
		g.setGraphicsFont(textFont);
		g.drawString(200, 100, winner);
	}
}
