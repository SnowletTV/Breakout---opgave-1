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
     * dus we doen een stream over alle linkedballs, allmatch(een stream over alle linkedalphas, anymatch c equalContent aan het originele
     * @invar | linkedBalls != null
     * @invar | Arrays.stream(linkedBalls.toArray()).allMatch(b -> (Arrays.stream(((Ball) b).getLinkedAlphas().toArray()).anyMatch(c ->((Alpha) c).equalContent((this)))))
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
	 * @pre | alpha != null
	 * @inspects this
	 * @post | result == false || (alpha.getLocation().equals(this.getLocation()) && alpha.getECharge() == this.getECharge() && alpha.getColor().equals(this.getColor()) && alpha.getVelocity().equals(this.getVelocity()) && alpha.getClass().equals(this.getClass())) && Arrays.stream(alpha.getLinkedBalls().toArray()).allMatch(b -> ((Ball) b).getLinkedAlphas().contains(this))
	 */
	public boolean equalContent(Alpha alpha) {
		if(!alpha.getLocation().equals(this.getLocation())) return false;
		if(alpha.getECharge() != this.getECharge()) return false;
		if(!alpha.getColor().equals(this.getColor())) return false;
		if(!alpha.getVelocity().equals(this.getVelocity())) return false;
		if(!alpha.getClass().equals(this.getClass())) return false;
		if(!Arrays.stream(alpha.getLinkedBalls().toArray()).allMatch(b -> ((Ball) b).getLinkedAlphas().contains(this))) return false;
		return true;
	}
	
	/**
	 * Sets this alpha/ball's dynamically electric charge.
	 * @pre | getLinkedBalls() != null
	 * @pre | getECharge() != 0
	 * @mutates | this
	 * @post | getLinkedBalls() != null
	 * @post | getECharge() != 0
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
		return linkedBalls;
	}

	/**
	 * @pre | linkedBalls != null
	 * @mutates | this
	 */
	public void setLinkedBalls(Set<Ball> linkedBalls) {
		this.linkedBalls = linkedBalls;
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
