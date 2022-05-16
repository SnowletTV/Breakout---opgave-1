package radioactivity;

import java.util.Set;

import utils.Circle;
import utils.Vector;

/**
 * Represents an alpha particle in the breakout game.
 * 
 * @mutable
 */
public class Alpha extends AlphaBall {	
	
	/**
     * @peerObjects
     */
    Set<Ball> linkedBalls;
    
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
}
