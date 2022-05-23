package breakout.radioactivity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

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
 * @invar | getECharge() != 0
 * @invar | getLifetime() >= 0
 * @invar | getLifetime() <= 10000
 * @invar | getLinkedAlphas().stream().allMatch(b -> b != null)
 */
public abstract class Ball extends AlphaBall {
	
	/**
     * @peerObjects
     * @invar | linkedAlphas != null
     * @invar | linkedAlphas.stream().allMatch(b -> b != null)
     */
    private Set<Alpha> linkedAlphas = new LinkedHashSet<Alpha>();
	
	/**
	 * Construct a new normal ball at a given location, with a given velocity.
	 * @pre | location != null
	 * @pre | velocity != null
	 * @mutates | this
	 * @post | getLocation().equals(location)
	 * @post | getVelocity().equals(velocity)
	 */
	public Ball(Circle location, Vector velocity) {
		super(location, velocity);
		this.EChargeCheck();
		this.EChargeCheckAll();
	}
	
	/**
	 * Checks if the contents are equal for two arrays of Balls
	 * @pre | ball != null
	 * @inspects this
	 * @post | result == false || (ball.getLocation().equals(this.getLocation()) && ball.getECharge() == this.getECharge() && ball.getColor().equals(this.getColor()) && ball.getVelocity().equals(this.getVelocity()) && ball.getClass().equals(this.getClass()))
	 */
	public boolean equalContent(Ball ball) {
		if(!ball.getLocation().equals(this.getLocation())) return false;
		if(ball.getECharge() != this.getECharge()) return false;
		if(!ball.getColor().equals(this.getColor())) return false;
		if(!ball.getVelocity().equals(this.getVelocity())) return false;
		if(!ball.getClass().equals(this.getClass())) return false;
		return true;
	}
	
	/**
	 * Return this ball's colour.
	 * @post | result != null
	 */
	public abstract Color getColor();
	
	/**
	 * Return this ball's colour.
	 * @post | getECharge() != 0
	 * Redundant (Arrays.stream(getLinkedAlphas().toArray()).anyMatch(b -> ((Alpha) b).getLinkedBalls().toArray().length == this.getECharge()) || getECharge() == -1)
	 */
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
	 * Sets this alpha/ball's dynamically calculated electric charge.
	 * @pre | getLinkedAlphas() != null
	 * @pre | getECharge() != 0
	 * @mutates | this
	 * @post | getLinkedAlphas() != null
	 * @post | getECharge() != 0
	 */
	public void EChargeCheckAll() {
		for(Alpha a: this.getLinkedAlphas()) {
			for(Ball b: a.getLinkedBalls()) {
				b.EChargeCheck();
			}
		}
	}
	
	/**
	 * Links this ball to an alpha
	 * @pre ball != null
	 * @mutates | this
	 * @mutates | alpha
	 * @post | getLinkedAlphas().contains(alpha)
	 */
	public void linkTo(Alpha alpha) {
		this.linkedAlphas.add(alpha);
		this.EChargeCheckAll();
	}
	
	/**
	 * Unlinks this ball from an alpha
	 * @pre ball != null
	 * @mutates | this
	 * @mutates | alpha
	 * @post | !getLinkedAlphas().contains(alpha)
	 */
	public void unlinkFrom(Alpha alpha) {
		this.linkedAlphas.remove(alpha);
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
	 *  Return this ball's linked alphas
	 * @post | result != null
	 */
	public Set<Alpha> getLinkedAlphas() {
		return linkedAlphas;
	}

	/**
	 * Sets this ball's linked alphas
	 * @pre | linkedAlphas != null
	 * @mutates this
	 */
	public void setLinkedAlphas(Set<Alpha> linkedAlphas) {
		this.linkedAlphas = linkedAlphas;
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