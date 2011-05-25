package org.jfge.api.sprite;

import org.jfge.api.engine.Renderable;
import org.jfge.api.engine.Updatable;
import org.jfge.api.fsm.State;
import org.jfge.api.fsm.StateMachine;
import org.jfge.spi.graphics.Image;

// TODO: Auto-generated Javadoc
/**
 * The Interface Sprite.
 */
public interface Sprite extends Updatable, Renderable{
	
	/** The Constant LEFT. */
	public static final int LEFT = 1; 
	
	/** The Constant RIGHT. */
	public static final int RIGHT = 2;
	
	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getHeight();
	
	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth();

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX();

	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(int x);

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY();

	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(int y);

	
	/**
	 * Gets the dx.
	 *
	 * @return the dx
	 */
	public int getDx();

	/**
	 * Sets the dx.
	 *
	 * @param dx the new dx
	 */
	public void setDx(int dx);

	
	/**
	 * Gets the dy.
	 *
	 * @return the dy
	 */
	public int getDy();

	/**
	 * Sets the dy.
	 *
	 * @param dy the new dy
	 */
	public void setDy(int dy);

	
	/**
	 * Gets the cur image.
	 *
	 * @return the cur image
	 */
	public org.jfge.spi.graphics.Image getImage();

	/**
	 * Sets the cur image.
	 *
	 * @param curImage the new cur image
	 */
	public void setImage(Image curImage);
	
	
	/**
	 * Sets the direction.
	 *
	 * @param dir the new direction
	 */
	public void setDirection(int dir);
	
	/**
	 * Gets the direction.
	 *
	 * @return the direction
	 */
	public int getDirection();
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName();
}
