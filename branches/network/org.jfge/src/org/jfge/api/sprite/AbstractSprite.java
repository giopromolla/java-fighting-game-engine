package org.jfge.api.sprite;

import org.jfge.spi.graphics.Image;

/**
 * The Class Sprite.
 */
public abstract class AbstractSprite implements Sprite {
	
	/** The y. */
	private int x;
	
	/** The y. */
	private int y;
	
	/** The dx. */
	private int dx;
	
	/** The dy. */
	private int dy;
	
	/** The cur image. */
	protected volatile Image curImage;
	
	/** The direction. */
    protected int direction = Sprite.RIGHT;
    
	/* (non-Javadoc)
	 * @see org.jfge.sprite.Sprite#getHeight()
	 */
	@Override
	public final int getHeight() {
		if(this.curImage == null)
			return 0;
		else 
			return this.curImage.getHeight();
	}
	
	/* (non-Javadoc)
	 * @see org.jfge.sprite.Sprite#getWidth()
	 */
	@Override
    public final int getWidth() {
		if(this.curImage == null)
			return 0;
		else
			return this.curImage.getWidth();
	}

	/* (non-Javadoc)
	 * @see org.jfge.sprite.Sprite#getX()
	 */
	@Override
    public final int getX() {
		return this.x;
	}

	/* (non-Javadoc)
	 * @see org.jfge.sprite.Sprite#setX(int)
	 */
	@Override
    public final synchronized void setX(int x) {
		this.x = x;
	}

	/* (non-Javadoc)
	 * @see org.jfge.sprite.Sprite#getY()
	 */
	@Override
    public final int getY() {
		return this.y;
	}

	/* (non-Javadoc)
	 * @see org.jfge.sprite.Sprite#setY(int)
	 */
	@Override
    public final synchronized void setY(int y) {
		this.y = y;
	}

	/* (non-Javadoc)
	 * @see org.jfge.sprite.Sprite#getDx()
	 */
	@Override
    public final int getDx() {
		return this.dx;
	}

	/* (non-Javadoc)
	 * @see org.jfge.sprite.Sprite#setDx(int)
	 */
	@Override
    public final synchronized void setDx(int dx) {
		this.dx = dx;
	}

	/* (non-Javadoc)
	 * @see org.jfge.sprite.Sprite#getDy()
	 */
	@Override
    public final int getDy() {
		return this.dy;
	}

	/* (non-Javadoc)
	 * @see org.jfge.sprite.Sprite#setDy(int)
	 */
	@Override
    public final synchronized void setDy(int dy) {
		this.dy = dy;
	}

	/* (non-Javadoc)
	 * @see org.jfge.sprite.Sprite#getCurImage()
	 */
	@Override
    public final Image getImage() {
		return this.curImage;
	}

	/* (non-Javadoc)
	 * @see org.jfge.sprite.Sprite#setCurImage(java.awt.Image)
	 */
	@Override
    public final synchronized void setImage(Image curImage) {
		if(curImage == null)
			return;
		
		this.curImage = curImage;
	}
	
	/* (non-Javadoc)
	 * @see org.jfge.sprite.Sprite#setDirection(int)
	 */
	@Override
    public final synchronized void setDirection(int dir) {
		this.direction = dir;
	}
	
	/* (non-Javadoc)
	 * @see org.jfge.sprite.Sprite#getDirection()
	 */
	@Override
    public final int getDirection() {
		return this.direction;
	}
}
