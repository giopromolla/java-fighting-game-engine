package org.jfge.games.sfvsmk2.arena;

import java.io.IOException;

import org.jfge.api.arena.Arena;
import org.jfge.api.arena.ArenaFactory;
import org.jfge.spi.render.ArenaRenderer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

@Singleton
public final class SfVsMk2Arena implements Provider<Arena> {

	private ArenaRenderer arenaRenderer;
	
	private ArenaFactory arenaFactory;
	
	private Arena arena;
	
	@Inject
	public SfVsMk2Arena(@Named("arenaRenderer.mortalKombat2") ArenaRenderer arenaRenderer, ArenaFactory arenaFactory) {
		this.arenaRenderer = arenaRenderer;
		this.arenaFactory = arenaFactory;
	}
	
	@Override
	public Arena get() {
		if(this.arena == null) {
			try {
				this.arena = arenaFactory.createArena("/org/jfge/games/sfvsmk2/arena/images/sfvsmk.png", arenaRenderer);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return this.arena;
	}
}
