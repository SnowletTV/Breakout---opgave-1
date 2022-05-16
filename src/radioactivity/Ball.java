package radioactivity;

import java.awt.Color;

import utils.Circle;
import utils.Point;
import utils.Rect;
import utils.Vector;

/**
 * Represents the state of a ball in the breakout game.
 * 
 * @mutable
 * @invar | getLocation() != null
 * @invar | getVelocity() != null
 * @invar | getLifetime() >= 0
 * @invar | getLifetime() <= 10000
 */
public abstract class Ball {
	
	/**
	 * @invar | location != null
	 */
	private Circle location;
	
	/**
	 * @invar | velocity != null
	 */
	private Vector velocity;
	
	/**
	 * Construct a new normal ball at a given location, with a given velocity.
	 * @pre | location != null
	 * @pre | velocity != null
	 * @post | getLocation().equals(location)
	 * @post | getVelocity().equals(velocity) 
	 */
	public Ball(Circle location, Vector velocity) {
		this.location = location;
		this.velocity = velocity;
	}
	
	/**
	 * Return this ball's colour.
	 * @post | result != null
	 */
	public abstract Color getColor();
	
	/**
	 * Return this ball's location.
	 * @post | result != null
	 */
	public Circle getLocation() {
		return location;
	}
	
	/**
	 * Sets this ball's location.
	 * @pre | location != null
	 * @mutates | this
	 */
	public void setLocation(Circle location) {
		this.location = location;
	}

	/**
	 * Return this ball's velocity.
	 * @post | result != null
	 */
	public Vector getVelocity() {
		return velocity;
	}
	
	/**
	 * Sets this ball's velocity.
	 * @pre | velocity != null
	 * @mutates | this
	 */
	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
	}
	
	/**
	 * Returns the ball itself if lifetime isn't up, otherwise returns a normal ball.
	 * @post | result != null
	 * @post | this.getLocation().equals(result.getLocation())
	 * @post | this.getVelocity().equals(result.getVelocity())
	 * @post | result.getLifetime() <= 10000 || result.getLifetime() > 0
	 */
	public abstract Ball checkLife();
	
	/**
	 * Check whether this ball collides with a given `rect` and if so, return the 
	 * new velocity this ball will have after bouncing on the given rect all the while accounting for the conditions of SuperchargedBall.
	 * 
	 * @pre | rect != null
	 * @mutates this
	 * @post | (rect.collideWith(getLocation()) == null && result == null) ||
	 * 		 | (rect.collideWith(getLocation()) != null && result != null && result.equals(getVelocity())) ||
	 *       | (getVelocity().product(rect.collideWith(getLocation())) <= 0 && result == null) || 
	 *       | (result.equals(getVelocity().mirrorOver(rect.collideWith(getLocation()))))
	 */
	public abstract Vector hitBlock(Rect rect, boolean destroyed);

	/**
	 * Return this ball's center.
	 * @post | result != null
	 * @post | getLocation().getCenter().equals(result)
	 */
	public Point getCenter() {
		return getLocation().getCenter();
	}
	
	/**
	 * Sets this ball's center.
	 * @pre | center != null
	 * @mutates | this
	 */
	public void setCenter(Point center) {
		Circle newLocation = new Circle(location.getCenter(), location.getDiameter());
		this.location = newLocation;
	}
	
	/**
	 * Return this ball's lifetime.
	 * @post | result > 0
	 */
	public abstract int getLifetime();
	
	/**
	 * Sets this ball's lifetime.
	 * @pre | lifetime > 0
	 * @pre | lifetime <= 10000
	 * @mutates | this
	 */
	public abstract void setLifetime(int lifetime);
	
	/**
	 * Return this ball's Diameter.
	 * @post | result > 0
	 * @post | getLocation().getDiameter() == result
	 */
	public abstract int getDiameter();
	
	/**
	* Changes the ball's subclass unless the Ball still has lifetime left.
	* @creates | result
	* @inspects | this
	* @post | result != null
	* @post | this.getLocation().equals(result.getLocation())
	* @post | this.getVelocity().equals(result.getVelocity())
	* @post | result.getLifetime() <= 10000 || result.getLifetime() > 0
	*/
	public abstract Ball changeBall();
}