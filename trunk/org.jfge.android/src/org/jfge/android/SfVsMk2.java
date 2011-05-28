package org.jfge.android;

import java.util.Map;

import org.jfge.android.graphics.AndroidGraphicsModule;
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
import org.jfge.games.mk2.effect.MortalKombat2EffectModule;
import org.jfge.games.mk2.fighter.MortalKombat2FighterModule;
import org.jfge.games.mk2.game.MortalKombat2GameModule;
import org.jfge.games.mk2.projectile.MortalKombat2ProjectileModule;
import org.jfge.games.mk2.render.MortalKombat2RenderModule;
import org.jfge.games.sf2.arena.StreetFighter2ArenaModule;
import org.jfge.games.sf2.collision.StreetFighter2CollisionModule;
import org.jfge.games.sf2.fighter.StreetFighter2FighterModule;
import org.jfge.games.sf2.game.StreetFighter2GameModule;
import org.jfge.games.sf2.projectile.StreetFighter2ProjectileModule;
import org.jfge.games.sf2.renderer.StreetFighter2RenderModule;
import org.jfge.games.sfvsmk2.arena.SfVsMk2ArenaModule;
import org.jfge.games.sfvsmk2.collision.SfVsMk2CollisionModule;
import org.jfge.games.sfvsmk2.game.SfVsMk2GameModule;
import org.jfge.spi.controller.Controller;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;


/**
 * The Class SfVsMk2.
 */
public class SfVsMk2 extends Activity{
	
	/** The injector. */
	private Injector injector;
	
	/** The controller. */
	private Controller controller;
	
	/** The game. */
	private Game game;
	
	/**
	 * Instantiates a new sf vs mk2.
	 */
	public SfVsMk2() {
		injector = Guice.createInjector(
				new EngineModule(),
				new AndroidGraphicsModule(this),
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
				
				// mortal kombat modules
//				new MortalKombat2ArenaModule(),
//				new MortalKombat2FighterModule(),
//				new MortalKombat2EffectModule(),
//				new MortalKombat2ProjectileModule(),
//				new MortalKombat2RenderModule(),
//				new MortalKombat2GameModule(),
				
				// street fighter vs. mortal kombat 2 modules
//				new SfVsMk2ArenaModule(),
//				new SfVsMk2GameModule(),
//				new SfVsMk2CollisionModule()
			);
	}
	
//	 @Override
//	 public boolean onCreateOptionsMenu(Menu menu) {
//		 super.onCreateOptionsMenu(menu);
//	     menu.add(0, MENU_EXIT, 0, R.string.menu_exit);
//	     return true;
//	 }
	
//	 public boolean onOptionsItemSelected(MenuItem item) {
//		 System.exit(0);  
//		 return true;
//	 }
	 
	 @Override
	 public boolean onKeyDown(int keyCode, KeyEvent event) {
		 return ((android.view.KeyEvent.Callback) controller).onKeyDown(keyCode, event);
	 }

	 @Override
	 public boolean onKeyUp(int keyCode, KeyEvent event) {
		 return ((android.view.KeyEvent.Callback) controller).onKeyUp(keyCode, event);
	 }

	 /* (non-Javadoc)
 * @see android.app.Activity#onCreate(android.os.Bundle)
 */
@Override
	 protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		Map<String, Game> games = injector.getInstance(Key.get(new TypeLiteral<Map<String, Game>>() {}));
		this.controller = injector.getInstance(Key.get(Controller.class, Names.named("keyboard.android")));
		this.game = games.get("streetFighter2");
		game.start();
	 }
	 
	 /* (non-Javadoc)
 	 * @see android.app.Activity#onConfigurationChanged(android.content.res.Configuration)
 	 */
 	@Override
	 public void onConfigurationChanged(Configuration newConfig) {
	     super.onConfigurationChanged(newConfig);
	     setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	 }

}
