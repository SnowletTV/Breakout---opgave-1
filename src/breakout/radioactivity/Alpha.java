package breakout.radioactivity;

import java.awt.Color;
import logicalcollections.LogicalSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import breakout.utils.Circle;
import breakout.utils.Vector;

/**
 * Represents the state of an alpha particle in the breakout game.
 * @mutable
 * @invar | getLocation() != null
 * @invar | getVelocity() != null
 * @invar | getECharge() == 1
 */
public class Alpha extends AlphaBall {	
    private static final Color color = new Color(170,170,170);
	
	 /**
     * @peerObjects
     * @invar | linkedBalls != null
     * TODO invar
     */
    private Set<Ball> linkedBalls = new LinkedHashSet<Ball>();
    
    /**
	 * Construct a new normal Alpha at a given location, with a given velocity.
	 * @pre | location != null
	 * @pre | velocity != null
	 * @post | getLocation().equals(location)
	 * @post | getVelocity().equals(velocity) 
	 */
	public Alpha(Circle location, Vector velocity) {
		super(location, velocity);
	}
	
	/**
	 * Checks if the contents are equal for two arrays of Alphas
	 * TODO check if LinkedBalls equal
	 * @pre | alpha != null
	 * @inspects this
	 * @post | result == false || (alpha.getLocation().equals(this.getLocation()) && alpha.getECharge() == this.getECharge() && alpha.getColor().equals(this.getColor()) && alpha.getVelocity().equals(this.getVelocity()) && alpha.getClass().equals(this.getClass()))
	 */
	public boolean equalContent(Alpha alpha) {
		if(!alpha.getLocation().equals(this.getLocation())) return false;
		if(alpha.getECharge() != this.getECharge()) return false;
		if(!alpha.getColor().equals(this.getColor())) return false;
		if(!alpha.getVelocity().equals(this.getVelocity())) return false;
		if(!alpha.getClass().equals(this.getClass())) return false;
		return true;
	}
	
	/**
	 * Sets this alpha/ball's dynamically electric charge.
	 * @mutates | this
	 */
	public void EChargeCheckAll() {
	}
	
	
	/**
	 * Links this alpha to a ball
	 * @pre ball != null
	 * @mutates | this
	 * @mutates | ball
	 * @post | getLinkedBalls().contains(ball)
	 */
	public void linkTo(Ball ball) {
		this.linkedBalls.add(ball);
		ball.EChargeCheckAll();
	}
	
	/**
	 * Unlinks this alpha from a ball
	 * @pre ball != null
	 * @mutates | this
	 * @mutates | ball
	 * @post | !getLinkedBalls().contains(ball)
	 */
	public void unlinkFrom(Ball ball) {
		this.linkedBalls.remove(ball);
		ball.EChargeCheckAll();
	}
	
	/**
	 * Return this alpha's colour.
	 * @post | result != null
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Return this alpha's linked Balls.
	 * @post | result != null
	 */
	public Set<Ball> getLinkedBalls() {
		LinkedHashSet<Ball> a = new LinkedHashSet<Ball>();
		a.addAll(linkedBalls);
		return a;
	}

	/**
	 * @pre | linkedBalls != null
	 * @mutates | this
	 * @post | Arrays.stream(linkedBalls.toArray()).allMatch(b -> getLinkedBalls().contains(b))
	 */
	public void setLinkedBalls(Set<Ball> linkedBalls) {
		LinkedHashSet<Ball> a = new LinkedHashSet<Ball>();
		a.addAll(linkedBalls);
		this.linkedBalls = a;
	}
	
	/**
	* Returns the array of alphas with the extra alpha added
	* @pre | alphas != null
	* @pre | this != null
	* @inspects | this
	* @creates | result
	* @post | result != null
	* @post | result.length - alphas.length == 1
	*/
	public Alpha[] alphaChange(Alpha[] alphas) {
		ArrayList<Alpha> nalphas = new ArrayList<Alpha>();
		for( Alpha a : alphas ) {
			nalphas.add(a);
		}
		nalphas.add(this);
		return nalphas.toArray(new Alpha[] {});
	}
}
