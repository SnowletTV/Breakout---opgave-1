package breakout;

/**
 * Represents a paddle. 
 *
 * @immutable
 */
public final class PaddleState {
    // TODO: implement
	/**
	* @invar the paddle must not be across the right wall.
	* | center.getX() + size.getX()/2 <= 50000
	* @invar the paddle must not be across the left wall.
	* | center.getX() - size.getX()/2 >= 0
	*/
	private final Point center;
	private final Vector size;

	/**
	*  Initializes this object with the given center and size.
	*
	* @pre Argument {@code center} is not {@code null}.
	* | center != null
	* @pre Argument {@code size} is not {@code null}.
	* | size != null
	* @post This object's center equal the given center.
	* | getCenter() == center
	* @post This object's size equal the given size.
	* | getSize() == size
	* @invar X coordinate of the center of the paddle must have been decreased by 10
	* | getPaddle().getCenter().getX() == old(getPaddle().getCenter().getX())-10
    * @invar
    * | getPaddle().getCenter().getX() + getPaddle().getSize().getX()/2 <= getBottomRight().getX()
	*/
    public PaddleState(Point center, Vector size) {
        this.center = center;
        this.size = size;
    }
    
    /**
	* Returns this object's center.
	*/
    public Point getCenter() {
		return center;
	}
    
    
    /**
	* Returns this object's size.
	*/
    public Vector getSize() {
		return size;
	}
}
