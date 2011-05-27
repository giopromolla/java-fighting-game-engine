package org.jfge.games.mk2.game;

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
import org.jfge.games.mk2.arena.MortalKombat2ArenaModule;
import org.jfge.games.mk2.collision.MortalKombat2CollisionModule;
import org.jfge.games.mk2.effect.MortalKombat2EffectModule;
import org.jfge.games.mk2.fighter.MortalKombat2FighterModule;
import org.jfge.games.mk2.projectile.MortalKombat2ProjectileModule;
import org.jfge.games.mk2.render.MortalKombat2RenderModule;
import org.jfge.j2se.graphics.J2SeGraphicsModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;

public class MortalKombat2 {

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
				
				// mortal kombat modules
				new MortalKombat2ArenaModule(),
				new MortalKombat2FighterModule(),
				new MortalKombat2CollisionModule(),
				new MortalKombat2EffectModule(),
				new MortalKombat2ProjectileModule(),
				new MortalKombat2RenderModule(),
				new MortalKombat2GameModule()
		);

		Map<String, Game> games = injector.getInstance(Key.get(new TypeLiteral<Map<String, Game>>() {}));
		Game game = games.get("mortalKombat2");
		game.start();
	}
}
