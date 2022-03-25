package breakout;

/**
 * This class represents a paddle in a 2-dimensional integer grid. 
 *
 * @immutable
 */
public final class PaddleState {
    // TODO: implement
	private final Point center;
	private final Vector size;

	/**
	*  Initializes this object with the given center and size.
	*
	* @pre Argument {@code center} is not {@code null}.
	* | center != null
	* @pre Argument {@code size} is not {@code null}.
	* | size != null
	* @pre paddle is not left of the field.
	* | center.getX()-size.getX()/2 >= 0
	* @pre paddle  is not right of the field.
	* | center.getX()+size.getX()/2  <= 50000
	* @pre paddle  is not ontop of the field.
	* | center.getY()-size.getY()/2 >= 0
	* @pre paddle is not below of the field.
	* | center.getY()+size.getY()/2  <= 30000
	* @post This object's center equal the given center.
	* | getCenter() == center
	* @post This object's size equal the given size.
	* | getSize() == size
	* @post paddle is not left of the field.
	* | getCenter().getX()-getSize().getX()/2 >= 0
	* @post paddle  is not right of the field.
	* | getCenter().getX()+getSize().getX()/2 <= 50000
	* @post paddle  is not ontop of the field.
	* | getCenter().getY()-getSize().getY()/2 >= 0
	* @post paddle is not below of the field.
	* | getCenter().getY()+getSize().getY()/2 <= 30000
	*/
    public PaddleState(Point center, Vector size) {
        this.center = center;
        this.size = size;
    }
    
    /**
	* Returns this object's center.
	* @post | result != null
	*/
    public Point getCenter() {
		return center;
	}
    
    
    /**
	* Returns this object's size.
	* @post | result != null
	*/
    public Vector getSize() {
		return size;
	}
}
