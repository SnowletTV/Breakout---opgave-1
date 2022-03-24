package breakout;

public class PaddleState {
    // TODO: implement
	public Point center;
	public Vector size;

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
	*/
    public PaddleState(Point center, Vector size) {
        this.center = center;
        this.size = size;
    }
    
    /**
	* Returns this object's center.
	*
	* @post This object's center equal the returned center.
	* | result == center
	*/
    public Point getCenter() {
		return center;
	}
    
    /**
	* Returns this object's size.
	*
	* @post This object's size equal the returned size.
	* | result == size
	*/
    public Vector getSize() {
		return size;
	}
}
