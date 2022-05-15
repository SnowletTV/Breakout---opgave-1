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
public class SuperchargedBall extends Ball {
	
	/**
	 * @invar | lifetime <= 10000
	 * @invar | lifetime >= 0
	 */
	private int lifetime;	
	
	/**
	 * @invar | location != null
	 */
	private Circle location;
	
	/**
	 * @invar | velocity != null
	 */
	private Vector velocity;
	private static final Color color = new Color(100,200,180);
	
	/**
	 * Construct a new supercharged ball at a given location, with a given velocity.
	 * @pre | location != null
	 * @pre | velocity != null
	 * @post | getLocation().equals(location)
	 * @post | getVelocity().equals(velocity) 
	 */
	public SuperchargedBall(Circle location, Vector velocity, int lifetime) {
		this.location = location;
		this.velocity = velocity;
		this.lifetime = lifetime;
	}

	/**
	 * Return this ball's colour.
	 * @post | result != null
	 */
	public Color getColor() {
		return color;
	}
	
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
	 * @mutates | this
	 * @creates | result
	 * @post | result != null
	 * @post | this.getLocation().equals(result.getLocation())
	 * @post | this.getVelocity().equals(result.getVelocity())
	 * @post | result.getLifetime() <= 10000 || result.getLifetime() > 0
	 */
	public Ball checkLife() {
		this.setLifetime(lifetime - 1);
		return this.changeBall();
	}
	
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
	 * @post | (rect.collideWith(getLocation()) == null && result == null) ||
	 * 		 | (destroyed == true && rect.collideWith(getLocation()) != null && result.equals(getVelocity())) ||
	 *       | (getVelocity().product(rect.collideWith(getLocation())) <= 0 && result == null) || 
	 *       | (result.equals(getVelocity().mirrorOver(rect.collideWith(getLocation()))) && destroyed == false)
	 */
	public Vector hitBlock(Rect rect, boolean destroyed) {
		Vector coldir = rect.collideWith(getLocation());
		if(coldir != null && getVelocity().product(coldir) > 0 && destroyed == false) {
			return getVelocity().mirrorOver(coldir);
		}
		if(coldir != null && destroyed == true) {
			return getVelocity();
		}
		return null;
	}

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
	 * @post | result >= 0
	 */
	public int getLifetime() {
		return lifetime;
	}
	
	/**
	 * Sets this ball's lifetime.
	 * @pre | lifetime >= 0
	 * @pre | lifetime <= 10000
	 * @mutates | this
	 */
	public void setLifetime(int lifetime) {
		this.lifetime = lifetime;
	}	
	
	/**
	 * Return this ball's Diameter.
	 * @post | result > 0
	 * @post | getLocation().getDiameter() == result
	 */
	public int getDiameter() {
		return getLocation().getDiameter();
	}
	
	/**
	* Changes the ball's subclass unless the Ball still has lifetime left.
	* @creates | result
	* @inspects | this
	* @post | result != null
	* @post | this.getLocation().equals(result.getLocation())
	* @post | this.getVelocity().equals(result.getVelocity())
	* @post | result.getLifetime() <= 10000 || result.getLifetime() > 0
	* @post | (this.getClass().equals(result.getClass()) == false && this.getLifetime() == 0) || (this.getLifetime() != 0 && this.getClass().equals(result.getClass()))
	*/
	public Ball changeBall() {
		if(lifetime <= 0) {
			return new NormalBall(this.getLocation(), this.getVelocity());
		}
		return this;
	}
}