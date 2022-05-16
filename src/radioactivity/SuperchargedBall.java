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
	
	private static final Color color = new Color(100,200,180);
	
	/**
	 * Construct a new supercharged ball at a given location, with a given velocity.
	 * @pre | location != null
	 * @pre | velocity != null
	 * @post | getLocation().equals(location)
	 * @post | getVelocity().equals(velocity) 
	 */
	public SuperchargedBall(Circle location, Vector velocity, int lifetime) {
		super(location, velocity);
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
	 * @override
	 * @pre | rect != null
	 * @mutates this
	 * TODO documentation
	 */
	public void hitBlock(Rect rect, boolean destroyed) {
		Vector coldir = rect.collideWith(getLocation());
		if(coldir != null && getVelocity().product(coldir) > 0 && destroyed == false) {
			this.setVelocity(getVelocity().mirrorOver(coldir));
		}
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