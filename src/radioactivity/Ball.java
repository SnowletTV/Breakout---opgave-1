package radioactivity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
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
    private Set<Alpha> linkedAlphas = new LinkedHashSet<Alpha>();
	
	/**
	 * Construct a new normal ball at a given location, with a given velocity.
	 * @pre | location != null
	 * @pre | velocity != null
	 * @post | getLocation().equals(location)
	 * @post | getVelocity().equals(velocity) 
	 */
	public Ball(Circle location, Vector velocity) {
		super(location, velocity);
		this.EChargeCheck();
		this.EChargeCheckAll();
	}
	
	public boolean equalContent(Ball ball) {
		if(ball.getLocation() != this.getLocation()) return false;
		if(ball.getECharge() != this.getECharge()) return false;
		if(ball.getColor() != this.getColor()) return false;
		if(ball.getVelocity() != this.getVelocity()) return false;
		if(ball.getClass() != this.getClass()) return false;
		if(ball.getLinkedAlphas() != this.getLinkedAlphas()) return false;
		return true;
	}
	
	/**
	 * Return this ball's colour.
	 * @post | result != null
	 */
	public abstract Color getColor();
	
	private void EChargeCheck() {
		int largestSize = 1;
		for(Alpha a: this.getLinkedAlphas()) {
			if(a.getLinkedBalls().size() > largestSize) {
				largestSize = a.getLinkedBalls().size();
			}	
		}
		if(largestSize % 2 != 0) {
			largestSize *= -1;
		}
		this.setECharge(largestSize);
	}
	
	/**
	 * Sets this alpha/ball's dynamically electric charge.
	 * TODO document formally
	 * @mutates | this
	 */
	public void EChargeCheckAll() {
		for(Alpha a: this.getLinkedAlphas()) {
			for(Ball b: a.getLinkedBalls()) {
				b.EChargeCheck();
			}
		}
	}
	
	public void linkTo(Alpha alpha) {
		this.linkedAlphas.add(alpha);
		this.EChargeCheckAll();
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
		LinkedHashSet<Alpha> a = new LinkedHashSet<Alpha>();
		a.addAll(linkedAlphas);
		return a;
	}

	/**
	 * @param linkedAlphas the linkedAlphas to set
	 */
	public void setLinkedAlphas(Set<Alpha> linkedAlphas) {
		LinkedHashSet<Alpha> a = new LinkedHashSet<Alpha>();
		a.addAll(linkedAlphas);
		this.linkedAlphas = a;
	}
	
	/**
	* Returns the array of balls with the extra ball added
	* @pre | balls != null
	* @pre | this != null
	* @inspects | this
	* @creates | result
	* @post | result != null
	* @post | result.length - balls.length == 1
	*/
	public Ball[] ballChange(Ball[] balls) {
		ArrayList<Ball> nballs = new ArrayList<Ball>();
		for( Ball b : balls ) {
			nballs.add(b);
		}
		nballs.add(this);
		return nballs.toArray(new Ball[] {});
	}
}