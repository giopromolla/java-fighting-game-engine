package org.jfge.games.sfvsmk2.game;

import org.jfge.api.ai.AiController;
import org.jfge.api.fighter.Fighter;
import org.jfge.api.game.Game;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;

public final class SfVsMk2GameModule extends AbstractModule {

	@Override
	protected void configure() {
		MapBinder<String, Fighter> fighterBinder = MapBinder.newMapBinder(binder(), String.class, Fighter.class);
		MapBinder<String, AiController> aiControllerBinder = MapBinder.newMapBinder(binder(), String.class, AiController.class);
		
		
		MapBinder<String, Game> gameBinder = MapBinder.newMapBinder(binder(), String.class, Game.class);
		gameBinder.addBinding("sfVsMk2").toProvider(SfVsMk2Game.class);
	}
}
