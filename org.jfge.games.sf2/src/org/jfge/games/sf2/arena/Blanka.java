package org.jfge.games.sf2.arena;

import java.io.IOException;

import org.jfge.api.arena.Arena;
import org.jfge.api.arena.ArenaFactory;
import org.jfge.spi.render.ArenaRenderer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

public class Blanka implements Provider<Arena> {

private ArenaFactory arenaFactory;
	
	private ArenaRenderer arenaRenderer;

	private Arena arena;
	
	@Inject
	public Blanka(@Named("arenaRenderer.streetFighter2") ArenaRenderer arenaRenderer, ArenaFactory arenaFactory) {
		this.arenaRenderer = arenaRenderer;
		this.arenaFactory = arenaFactory;
	}
	
	@Override
	public Arena get() {
		if(this.arena == null) {
			try {
				this.arena = arenaFactory.createArena("/org/jfge/games/sf2/arena/images/blanka.png", arenaRenderer);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return this.arena;
	}

}
