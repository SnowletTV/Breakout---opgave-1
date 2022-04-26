package breakout;

import java.awt.Color;

/**
 * Represents the state of a normal paddle in the breakout game.
 *
 * @immutable
 * @invar | getCenter() != null
 */
public class NormalPaddleState extends PaddleState {

	/**
	 * @invar | center != null
	 */
	private final Point center;
	private static final Color color = new Color(153,50,50);

	/**
	 * Construct a normal paddle located around a given center in the field.
	 * @pre | center != null
	 * @post | getCenter().equals(center)
	 */
	public NormalPaddleState(Point center) {
		this.center = center;
	}
	
	/**
	 * Return the colour of this paddle.
	 * @post | result != null
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Return the center point of this paddle.
	 * @post | result != null
	 */
	public Point getCenter() {
		return center;
	}
	
	/**
	* Returns this paddle's size.
	* @post | result != null
	*/
	public Vector getSize() {
	   return new Vector(WIDTH, HEIGHT);
	}
	
	/**
	 * Returns this paddle's leftover hits
	 * @post | result == 0
	 */
	public int getHits() {
		return 0;
	}

	/**
	 * Return the rectangle occupied by this paddle in the field.
	 * @post | result != null
	 * @post | result.getTopLeft().equals(getCenter().plus(new Vector(-WIDTH/2,-HEIGHT/2)))
	 * @post | result.getBottomRight().equals(getCenter().plus(new Vector(WIDTH/2,HEIGHT/2)))
	 */
	public Rect getLocation() {
		Vector halfDiag = new Vector(-WIDTH/2,-HEIGHT/2);
		return new Rect(center.plus(halfDiag), center.plus(halfDiag.scaled(-1)));
	}
	
	/**
	* Returns a paddle of the other subclass if no more hits left. Otherwise resets hits.
	* @creates | result
	* @post | this.getCenter().equals(result.getCenter())
	* @post | this.getHits() <= 3
	* @post | this.getHits() >= 0
	* @post | this.getHits() == 0
	* @post | result.getHits() == 3 || result.getHits() == 0
	* @post | result.getHits() == 3
	* @post | result instanceof ReplicatorPaddleState
	* @post | result != null
	*/
	public PaddleState changePaddle() {		
		return new ReplicatorPaddleState(this.getCenter(), 3);
	}
	
	/**
	* Returns the paddle with a different center and switches class if needed.
	* @pre | center != null
	* @creates | result
	* @post | result != null
	* @post | this.getHits() <= 3
	* @post | this.getHits() >= 0
	* @post | result.getHits() <= 3
	* @post | result.getHits() >= 0
	* @post | result.getClass().equals(this.getClass())
	* @post | center.equals(result.getCenter())
	*/
	public PaddleState samePaddle(Point center) {		
		return new NormalPaddleState(center);
	}
	
	/**
	* Returns the array of balls with the extra balls for replicators added if applicable.
	* @pre | balls != null
	* @pre | ball != null
	* @creates | result
	* @post | result != null
	* @post | result.length - balls.length <= 3
	* @post | result.length - balls.length >= 0
	* @post | result.length == balls.length
	*/
	public Ball[] ballChange(Ball[] balls, Ball ball) {
		return balls;
	}

}
