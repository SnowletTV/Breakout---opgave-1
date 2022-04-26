package breakout;

import java.awt.Color;

/**
 * Represents the state of a ball in the breakout game.
 * 
 * @mutable
 * @invar | getLocation() != null
 * @invar | getVelocity() != null
 * @invar | getLifetime() >= 0
 * @invar | getLifetime() <= 10000
 */
public class NormalBall extends Ball {
	
	/**
	 * @invar | location != null
	 */
	private Circle location;
	
	/**
	 * @invar | velocity != null
	 */
	private Vector velocity;
	private static final Color color = new Color(255,255,255);
	
	/**
	 * Construct a new normal ball at a given location, with a given velocity.
	 * @pre | location != null
	 * @pre | velocity != null
	 * @post | getLocation().equals(location)
	 * @post | getVelocity().equals(velocity) 
	 */
	public NormalBall(Circle location, Vector velocity) {
		this.location = location;
		this.velocity = velocity;
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
	 * @post | result != null
	 * @post | this.getLocation().equals(result.getLocation())
	 * @post | this.getVelocity().equals(result.getVelocity())
	 * @post | result.getLifetime() <= 10000 || result.getLifetime() > 0 
	 * @post | result.getLifetime() == this.getLifetime()
	 * @post | result.getLifetime() == 1
	 * @post | this.getClass().equals(result.getClass())
	 */
	public Ball checkLife() {
		return this;
	}
	
	/**
	 * Check whether this ball collides with a given `rect` and if so, return the 
	 * new velocity this ball will have after bouncing on the given rect all the while accounting for the conditions of SuperchargedBall.
	 * 
	 * @pre | rect != null
	 * @post | (rect.collideWith(getLocation()) == null && result == null) ||
	 * 		 | (rect.collideWith(getLocation()) != null && result != null && result.equals(getVelocity())) ||
	 *       | (getVelocity().product(rect.collideWith(getLocation())) <= 0 && result == null) || 
	 *       | (result.equals(getVelocity().mirrorOver(rect.collideWith(getLocation()))))
	 * @post | (rect.collideWith(getLocation()) == null && result == null) ||
	 *       | (getVelocity().product(rect.collideWith(getLocation())) <= 0 && result == null) || 
	 *       | (result.equals(getVelocity().mirrorOver(rect.collideWith(getLocation()))))
	 */
	public Vector hitBlock(Rect rect, boolean destroyed) {
		Vector coldir = rect.collideWith(location);
		if(coldir != null && velocity.product(coldir) > 0) {
			return velocity.mirrorOver(coldir);
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
	 * @post | result > 0
	 */
	public int getLifetime() {
		return 1;
	}
	
	/**
	 * Sets this ball's lifetime.
	 * @pre | lifetime > 0
	 * @pre | lifetime <= 10000
	 * @mutates | this
	 */
	public void setLifetime(int lifetime) {
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
	* @post | this.getClass().equals(result.getClass()) == false
	*/
	public Ball changeBall() {		
		return new SuperchargedBall(this.getLocation(), this.getVelocity(), 10000);
	}
}
