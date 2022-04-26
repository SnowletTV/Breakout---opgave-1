package breakout;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Represents the state of a replicator paddle in the breakout game.
 *
 * @immutable
 * @invar | getCenter() != null
 * @invar | getHits() <= 3 && getHits() >= 0
 */
public class ReplicatorPaddleState extends PaddleState {
	
	/**
	 * @invar | hits <= 3 && hits >= 0
	 */
	private int hits = 3;
	/**
	 * @invar | center != null
	 */
	private final Point center;
	private static final Color color = new Color(170,70,20);
	
	/**
	 * Construct a replicator paddle located around a given center in the field.
	 * @pre | center != null
	 * @pre | hits > 0 && hits <= 3
	 * @post | getCenter().equals(center)
	 * @post | getHits() == hits
	 */
	public ReplicatorPaddleState(Point center, int hits) {
		this.center = center;
		this.hits = hits;
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
	 * @post | result >= 0 && result <= 3
	 */
	public int getHits() {
		return hits;
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
	* @inspects | this
	* @creates | result
	* @post | this.getHits() <= 3
	* @post | this.getHits() >= 0
	* @post | result.getHits() == 3 || result.getHits() == 0
	* @post | this.getCenter().equals(result.getCenter())
	* @post | this.getHits() <= 0 || result.getHits() == 3
	* @post | result != null
	*/
	public PaddleState changePaddle() {
		if(getHits() <= 0) {
			return new NormalPaddleState(this.getCenter());
		}
		return new ReplicatorPaddleState(center, 3);
	}
	
	/**
	* Returns the paddle with a different center and switches class if needed.
	* @pre | center != null
	* @inspects | this
	* @creates | result
	* @post | result != null
	* @post | center.equals(result.getCenter())
	* @post | this.getHits() == result.getHits()
	* @post | result.getHits() == this.getHits()
	* @post | result.getHits() >= 0
	* @post | result.getHits() <= 3
	* @post | this.getHits() <= 0 || result instanceof ReplicatorPaddleState
	* @post | this.getHits() <= 3
	* @post | this.getHits() >= 0
	*/
	public PaddleState samePaddle(Point center) {
		if(getHits() <= 0) {
			return new NormalPaddleState(center);
		}
		return new ReplicatorPaddleState(center, getHits());	
	}
	
	/**
	* Returns the array of balls with the extra balls for replicators added if applicable.
	* @pre | balls != null
	* @pre | ball != null
	* @inspects | this
	* @creates | result
	* @post | result != null
	* @post | result.length - balls.length <= 3
	* @post | result.length - balls.length > 0
	* @post | result.length - balls.length == getHits()+1
	*/
	public Ball[] ballChange(Ball[] balls, Ball ball) {
		ArrayList<Ball> nballs = new ArrayList<Ball>();
		for( Ball b : balls ) {
			nballs.add(b);
		}
		if(getHits() >= 1) {
			nballs.add(new NormalBall(ball.getLocation(), ball.getVelocity().plus(new Vector(2,-2))));
			
		}
		if(getHits() >= 2) {
			nballs.add(new NormalBall(ball.getLocation(), ball.getVelocity().plus(new Vector(-2,2))));
		}
		if(getHits() >= 3) {
			nballs.add(new NormalBall(ball.getLocation(), ball.getVelocity().plus(new Vector(2,2))));
		}
		hits = getHits()-1;
		return nballs.toArray(new Ball[] {});
	}
}
