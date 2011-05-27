package org.jfge.games.sf2.renderer;

import java.io.IOException;

import org.jfge.spi.graphics.Graphics;
import org.jfge.spi.graphics.GraphicsFactory;
import org.jfge.spi.graphics.Image;
import org.jfge.spi.render.ArenaRenderer;

import com.google.inject.Inject;

public class StreetFighter2ArenaRenderer implements ArenaRenderer {

	private Image lifebar;
	
	private Image roundSymbol;
	
	private Image koSymbol;
	
	
	private org.jfge.spi.graphics.Color clockColor;
	
	private org.jfge.spi.graphics.Color lifeBarColor;
	
	private org.jfge.spi.graphics.Color textColor;
	
	private org.jfge.spi.graphics.Font lifeBarTextFont;
	
	private org.jfge.spi.graphics.Font textFont;
	
	private org.jfge.spi.graphics.Font clockFont;
	
	
	@Inject
	public StreetFighter2ArenaRenderer(GraphicsFactory graphicsFactory) throws IOException {
		this.lifebar = graphicsFactory.createImage("/org/jfge/games/sf2/arena/images/lifebar.png");
		this.roundSymbol = graphicsFactory.createImage("/org/jfge/games/sf2/arena/images/roundsymbol.png");
		this.koSymbol = graphicsFactory.createImage("/org/jfge/games/sf2/arena/images/ko.png");
		
		clockColor = graphicsFactory.createColor(org.jfge.spi.graphics.Color.RED);
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
		g.drawString(225, 60, String.valueOf(time));
	}

	@Override
	public void drawLifebar(Graphics g, String name, int health, Image portrait, int wins, int align) {
		if(align == ArenaRenderer.ALIGN_RIGHT) {
			g.drawImage(420-lifebar.getWidth(), 20, lifebar);
			g.setGraphicsColor(lifeBarColor);
			g.drawFillRectangle(420-lifebar.getWidth()+2, 21, (int) ((lifebar.getWidth()-3)*(100-health)/100.0), lifebar.getHeight()-2);
			g.setGraphicsColor(this.textColor);
			g.setGraphicsFont(this.lifeBarTextFont);
			g.drawString(440, 15, name);
			
			for(int i=0; i < wins; i++) {
				g.drawImage(450 - i*(roundSymbol.getWidth()+5), 21, roundSymbol);
			}
		} else {
			g.drawImage(60, 20, lifebar.flip());
			g.setGraphicsColor(lifeBarColor);
			g.drawFillRectangle(61, 21, (int) ((lifebar.getWidth()-3)*(100-health)/100.0), lifebar.getHeight()-2);
			g.setGraphicsColor(this.textColor);
			g.setGraphicsFont(this.lifeBarTextFont);
			g.drawString(45, 15, name);
			
			for(int i=0; i < wins; i++) {
				g.drawImage(15 + i*(roundSymbol.getWidth()+5), 21, roundSymbol);
			}
		}
		
		g.drawImage(217, 15, koSymbol);
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
