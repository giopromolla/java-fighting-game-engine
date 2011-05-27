package org.jfge.games.sf2.game;

import java.util.Map;

import org.jfge.api.ai.AiModule;
import org.jfge.api.arena.ArenaModule;
import org.jfge.api.collision.CollisionModule;
import org.jfge.api.effect.EffectModule;
import org.jfge.api.engine.EngineModule;
import org.jfge.api.fighter.FighterModule;
import org.jfge.api.game.Game;
import org.jfge.api.game.GameModule;
import org.jfge.api.projectile.ProjectileModule;
import org.jfge.api.render.RenderModule;
import org.jfge.ext.physics.PhysicsModule;
import org.jfge.ext.scene.SceneModule;
import org.jfge.games.sf2.arena.StreetFighter2ArenaModule;
import org.jfge.games.sf2.collision.StreetFighter2CollisionModule;
import org.jfge.games.sf2.fighter.StreetFighter2FighterModule;
import org.jfge.games.sf2.projectile.StreetFighter2ProjectileModule;
import org.jfge.games.sf2.renderer.StreetFighter2RenderModule;
import org.jfge.j2se.graphics.J2SeGraphicsModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;

public class StreetFighter2 {
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(
				new EngineModule(), 
				new J2SeGraphicsModule(),
				new CollisionModule(),
				new EffectModule(),
				new PhysicsModule(),
				new FighterModule(),
				new SceneModule(),
				new AiModule(),
				new ProjectileModule(),
				new ArenaModule(),
				new RenderModule(),
				new GameModule(),
				
				// street fighter modules
				new StreetFighter2FighterModule(),
				new StreetFighter2ArenaModule(),
				new StreetFighter2RenderModule(),
				new StreetFighter2ProjectileModule(),
				new StreetFighter2CollisionModule(),
				new StreetFighter2GameModule()
		);

		Map<String, Game> games = injector.getInstance(Key.get(new TypeLiteral<Map<String, Game>>() {}));
		Game game = games.get("streetFighter2");
		game.start();
	}
}
