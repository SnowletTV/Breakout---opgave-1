package breakout;

/**
 * Represents a ball and its velocity. 
 *
 * @immutable
 */
public class BallState {
	// TODO: implement
	private final Point center;
	private final Vector velocity;
	private final int diameter;
	
	/**
	* Initializes this object with the given center, velocity and diameter.
	*
	* @pre Argument {@code center} is not {@code null}.
	* | center != null
	* @pre Argument {@code velocity} is not {@code null}.
	* | velocity != null
	* @pre Argument {@code diameter} is not {@code 0}.
	* | diameter != 0
	* @post This object's center equal the given center.
	* | getCenter() == center
	* @post This object's velocity equal the given velocity.
	* | getVelocity() == velocity
	* @post This object's diameter equal the given diameter.
	* | getDiameter() == diameter
	*/
	public BallState(Point center, Vector velocity, int diameter) {
		this.center = center;
		this.velocity = velocity;
		this.diameter = diameter;
	}
	
	/**
	* Returns this object's center.
	*/
	public Point getCenter() {
		return center;
	}
	
	/**
	* Returns this object's velocity.
	*/
	public Vector getVelocity() {
		return velocity;
	}
	
	/**
	* Returns this object's diameter.
	*/
	public int getDiameter() {
		return diameter;
	}
}
