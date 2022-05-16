package radioactivity;

import java.awt.Color;
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
		return linkedBalls;
	}

	/**
	 * @param linkedBalls the linkedBalls to set
	 */
	public void setLinkedBalls(Set<Ball> linkedBalls) {
		this.linkedBalls = linkedBalls;
	}
}
