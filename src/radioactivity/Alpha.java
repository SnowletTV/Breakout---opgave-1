package radioactivity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import utils.Circle;
import utils.Vector;

/**
 * Represents an alpha particle in the breakout game.
 * 
 * @mutable
 */
public class Alpha extends AlphaBall {	
    private static final Color color = new Color(170,170,170);
	
	/**
     * @peerObjects
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
	
	public boolean equalContent(Alpha alpha) {
		if(alpha.getLocation() != this.getLocation()) return false;
		if(alpha.getECharge() != this.getECharge()) return false;
		if(alpha.getColor() != this.getColor()) return false;
		if(alpha.getVelocity() != this.getVelocity()) return false;
		if(alpha.getClass() != this.getClass()) return false;
		if(alpha.getLinkedBalls() != this.getLinkedBalls()) return false;
		return true;
	}
	
	/**
	 * Sets this alpha/ball's dynamically electric charge.
	 * @mutates | this
	 */
	public void EChargeCheckAll() {
		
	}
	
	public void linkTo(Ball ball) {
		this.linkedBalls.add(ball);
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
	 * @return the linkedBalls
	 */
	public Set<Ball> getLinkedBalls() {
		LinkedHashSet<Ball> a = new LinkedHashSet<Ball>();
		a.addAll(linkedBalls);
		return a;
	}

	/**
	 * @param linkedBalls the linkedBalls to set
	 */
	public void setLinkedBalls(Set<Ball> linkedBalls) {
		LinkedHashSet<Ball> a = new LinkedHashSet<Ball>();
		a.addAll(linkedBalls);
		this.linkedBalls = a;
	}
	
	/**
	* TODO informal documentation
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
