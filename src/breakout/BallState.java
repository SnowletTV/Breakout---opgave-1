package breakout;

public class BallState {
	// TODO: implement
	public Point center;
	public int diameter;
	public Vector velocity;
	
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
	*
	* @post This object's size equal the returned size.
	* | result == center
	*/
	public Point getCenter() {
		return center;
	}
	
	/**
	* Returns this object's velocity.
	*
	* @post This object's velocity equal the returned velocity.
	* | result == velocity
	*/
	public Vector getVelocity() {
		return velocity;
	}
	
	/**
	* Returns this object's diameter.
	*
	* @post This object's diameter equal the returned diameter.
	* | result == diameter
	*/
	public int getDiameter() {
		return diameter;
	}
}
