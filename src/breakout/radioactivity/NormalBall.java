package breakout.radioactivity;

import java.awt.Color;

import breakout.utils.Circle;
import breakout.utils.Point;
import breakout.utils.Rect;
import breakout.utils.Vector;

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

	private static final Color color = new Color(255,255,255);
	
	/**
	 * Construct a new normal ball at a given location, with a given velocity.
	 * @pre | location != null
	 * @pre | velocity != null
	 * @post | getLocation().equals(location)
	 * @post | getVelocity().equals(velocity) 
	 */
	public NormalBall(Circle location, Vector velocity) {
		super(location, velocity);
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
