package breakout;

import java.awt.Color;

/**
 * Represents the state of a ball in the breakout game.
 * 
 * @mutable
 * @invar | getLocation() != null
 * @invar | getVelocity() != null
 */
public abstract class Ball {
	
	public abstract Color getColor();
	
	/**
	 * Return this ball's location.
	 */
	public abstract Circle getLocation();
	
	/**
	 * Sets this ball's location.
	 */
	public abstract void setLocation(Circle location);

	/**
	 * Return this ball's velocity.
	 */
	public abstract Vector getVelocity();
	
	/**
	 * Sets this ball's velocity.
	 */
	public abstract void setVelocity(Vector velocity);
	
	public abstract Ball checkLife();

	/**
	 * Check whether this ball collides with a given `rect` and if so, return the 
	 * new velocity this ball will have after bouncing on the given rect.
	 * 
	 * @pre | rect != null
	 * @post | (rect.collideWith(getLocation()) == null && result == null) ||
	 *       | (getVelocity().product(rect.collideWith(getLocation())) <= 0 && result == null) || 
	 *       | (result.equals(getVelocity().mirrorOver(rect.collideWith(getLocation()))))
	 */
	public abstract Vector bounceOn(Rect rect);
	
	public abstract Vector hitBlock(Rect rect, boolean destroyed);

	/**
	 * Return this ball's center.
	 * 
	 * @post | getLocation().getCenter().equals(result)
	 */
	public abstract Point getCenter();
	
	/**
	 * Sets this ball's center.
	 */
	public abstract void setCenter(Point center);
	
	public abstract void setLifetime(int lifetime);
	
	/**
	 * Return this ball's Diameter.
	 * 
	 * @post | getLocation().getDiameter() == result
	 */
	public abstract int getDiameter();
	
	public abstract Ball changeBall();
}