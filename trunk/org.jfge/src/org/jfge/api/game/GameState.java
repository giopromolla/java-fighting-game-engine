package org.jfge.api.game;

import org.jfge.api.engine.Renderable;
import org.jfge.api.engine.Updatable;
import org.jfge.api.fsm.State;

public interface GameState extends State<Game>, Updatable, Renderable {

}
