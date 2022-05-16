package radioactivity;

import java.awt.Color;
import java.util.LinkedHashSet;
import java.util.Set;

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
 * TODO documentation for eCharge
 */
public abstract class Ball extends AlphaBall {
	
	/**
     * @peerObjects
     */
    private Set<Alpha> linkedAlphas = new LinkedHashSet<Alpha>();;
	
	/**
	 * Construct a new normal ball at a given location, with a given velocity.
	 * @pre | location != null
	 * @pre | velocity != null
	 * @post | getLocation().equals(location)
	 * @post | getVelocity().equals(velocity) 
	 */
	public Ball(Circle location, Vector velocity) {
		super(location, velocity);
	}
	
	/**
	 * Return this ball's colour.
	 * @post | result != null
	 */
	public abstract Color getColor();
	
	/**
	 * Returns the ball itself if lifetime isn't up, otherwise returns a normal ball.
	 * @post | result != null
	 * @post | this.getLocation().equals(result.getLocation())
	 * @post | this.getVelocity().equals(result.getVelocity())
	 * @post | result.getLifetime() <= 10000 || result.getLifetime() > 0
	 */
	public abstract Ball checkLife();

	/**
	 * Return this ball's center.
	 * @post | result != null
	 * @post | getLocation().getCenter().equals(result)
	 */
	public Point getCenter() {
		return getLocation().getCenter();
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
	* Changes the ball's subclass unless the Ball still has lifetime left.
	* @creates | result
	* @inspects | this
	* @post | result != null
	* @post | this.getLocation().equals(result.getLocation())
	* @post | this.getVelocity().equals(result.getVelocity())
	* @post | result.getLifetime() <= 10000 || result.getLifetime() > 0
	*/
	public abstract Ball changeBall();

	/**
	 * @return the linkedAlphas
	 */
	public Set<Alpha> getLinkedAlphas() {
		return linkedAlphas;
	}

	/**
	 * @param linkedAlphas the linkedAlphas to set
	 */
	public void setLinkedAlphas(Set<Alpha> linkedAlphas) {
		this.linkedAlphas = linkedAlphas;
	}
}