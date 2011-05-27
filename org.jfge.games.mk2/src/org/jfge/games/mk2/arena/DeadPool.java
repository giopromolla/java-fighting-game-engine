package org.jfge.games.mk2.arena;

import java.io.IOException;

import org.jfge.api.arena.Arena;
import org.jfge.api.arena.ArenaFactory;
import org.jfge.spi.render.ArenaRenderer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

public class DeadPool implements Provider<Arena> {

private ArenaRenderer arenaRenderer;
	
	private ArenaFactory arenaFactory;
	
	private Arena arena;
	
	@Inject
	public DeadPool(@Named("arenaRenderer.mortalKombat2")ArenaRenderer arenaRenderer, ArenaFactory arenaFactory) {
		this.arenaRenderer = arenaRenderer;
		this.arenaFactory = arenaFactory;
	}
	
	@Override
	public Arena get() {
		if(this.arena == null) {
			try {
				this.arena = arenaFactory.createArena("/org/jfge/games/mk2/arena/images/deadpool.png", arenaRenderer);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return this.arena;
	}

}
