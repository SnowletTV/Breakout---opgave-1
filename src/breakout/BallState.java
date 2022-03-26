package breakout;

/**
 * This class represents a ball and its velocity in a 2-dimensional integer grid. 
 *
 * @immutable
 */
public final class BallState {
	// TODO: implement
	private final Point center;
	Vector velocity;
	private final int diameter;
	
	/**
	* Initializes this object with the given center, velocity and diameter.
	*
	* @pre Argument {@code center} is not {@code null}.
	* | center != null
	* @pre Argument {@code velocity} is not {@code null}.
	* | velocity != null
	* @pre Argument {@code diameter} is not 0.
	* | diameter != 0
	* @pre ball is not left of the field.
	* | center.getX()-diameter/2 >= 0
	* @pre ball  is not right of the field.
	* | center.getX()+diameter/2  <= 50000
	* @pre ball  is not ontop of the field.
	* | center.getY()-diameter/2 >= 0
	* @pre ball is not below of the field.
	* | center.getY()+diameter/2  <= 30000
	* @post This object's center equal the given center.
	* | getCenter() == center
	* @post This object's velocity equal the given velocity.
	* | getVelocity() == velocity
	* @post This object's diameter equal the given diameter.
	* | getDiameter() == diameter
	* @post ball is not left of the field.
	* | getCenter().getX()-getDiameter()/2 >= 0
	* @post ball  is not right of the field.
	* | getCenter().getX()+getDiameter()/2 <= 50000
	* @post ball  is not ontop of the field.
	* | getCenter().getY()-getDiameter()/2 >= 0
	* @post ball is not below of the field.
	* | getCenter().getY()+getDiameter()/2 <= 30000
	*/
	public BallState(Point center, Vector velocity, int diameter) {
		this.center = center;
		this.velocity = velocity;
		this.diameter = diameter;
	}
	
	/**
	* Returns this object's center.
	* @post | result != null
	*/
	public Point getCenter() {
		return center;
	}
	
	/**
	* Returns this object's velocity.
	* @post | result != null
	*/
	public Vector getVelocity() {
		return velocity;
	}
	
	/**
	* Returns this object's diameter.
	* @post | 0 <= result
	*/
	public int getDiameter() {
		return diameter;
	}
}
