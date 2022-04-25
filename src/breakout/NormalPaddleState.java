package breakout;

import java.awt.Color;

public class NormalPaddleState extends PaddleState {

	/**
	 * @invar | center != null
	 */
	private final Point center;
	private final Color color = new Color(153,50,50);

	/**
	 * Construct a paddle located around a given center in the field.
	 * @pre | center != null
	 * @post | getCenter().equals(center)
	 */
	public NormalPaddleState(Point center) {
		this.center = center;
	}
	
	public Color getColor() {
		return color;
	}
	
	/**
	 * Return the center point of this paddle.
	 */
	public Point getCenter() {
		return center;
	}
	
	/**
	* Returns this object's size.
	* @post | result != null
	*/
	public Vector getSize() {
	   return new Vector(WIDTH, HEIGHT);
	}
	
	/**
	 * 
	 */
	public int getHits() {
		return 0;
	}

	/**
	 * Return the rectangle occupied by this paddle in the field.
	 * 
	 * @post | result != null
	 * @post | result.getTopLeft().equals(getCenter().plus(new Vector(-WIDTH/2,-HEIGHT/2)))
	 * @post | result.getBottomRight().equals(getCenter().plus(new Vector(WIDTH/2,HEIGHT/2)))
	 */
	public Rect getLocation() {
		Vector halfDiag = new Vector(-WIDTH/2,-HEIGHT/2);
		return new Rect(center.plus(halfDiag), center.plus(halfDiag.scaled(-1)));
	}
	
	public PaddleState changePaddle() {		
		return new ReplicatorPaddleState(this.getCenter(), 3);
	}
	
	/**
	* Returns this object's size.
	* @post | result != null
	* @post | result instanceof NormalPaddleState
	*/
	public PaddleState samePaddle(Point center) {		
		return new NormalPaddleState(center);
	}
	
	public Ball[] ballChange(Ball[] balls, Ball ball) {
		return balls;
	}

}
