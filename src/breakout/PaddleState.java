package breakout;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Represents the state of a paddle in the breakout game.
 *
 * @immutable
 * @invar | getCenter() != null
 * @invar | getHits() <= 3 && getHits() >= 0
 * @invar | getSize() != null
 */
public abstract class PaddleState {
	
	public static final int HEIGHT = 500;
	public static final int WIDTH = 3000;
	
	/**
	 * Return the colour of this paddle.
	 * @post | result != null
	 */
	public abstract Color getColor();
	
	/**
	 * Return the center point of this paddle.
	 * @post | result != null
	 */
	public abstract Point getCenter();
	
	/**
	* Returns this paddle's size.
	* @post | result != null
	*/
	public abstract Vector getSize();
	
	/**
	 * Returns this paddle's leftover hits
	 * @post | result >= 0 && result <= 3
	 */
	public abstract int getHits();

	/**
	 * Return the rectangle occupied by this paddle in the field.
	 * @post | result != null
	 * @post | result.getTopLeft().equals(getCenter().plus(new Vector(-WIDTH/2,-HEIGHT/2)))
	 * @post | result.getBottomRight().equals(getCenter().plus(new Vector(WIDTH/2,HEIGHT/2)))
	 */
	public abstract Rect getLocation();
	
	/**
	* Returns a paddle of the other subclass unless a repliccator, then just resets hits.
	* @creates | result
	* @post | this.getCenter().equals(result.getCenter()) 
	* @post | this.getHits() <= 3
	* @post | this.getHits() >= 0
	* @post | result.getHits() == 3
	* @post | result != null
	*/
	public abstract PaddleState changePaddle();
	
	/**
	* Returns the paddle with a different center and switches class if needed.
	* @pre | center != null
	* @creates | result
	* @post | result != null
	* @post | this.getHits() <= 3
	* @post | this.getHits() >= 0
	* @post | result.getHits() <= 3
	* @post | result.getHits() >= 0
	* @post | center.equals(result.getCenter())
	*/
	public abstract PaddleState samePaddle(Point center);
	
	/**
	* Returns the array of balls with the extra balls for replicators added if applicable.
	* @pre | balls != null
	* @pre | ball != null
	* @creates | result
	* @post | result != null
	* @post | result.length - balls.length <= 3
	* @post | result.length - balls.length >= 0
	*/
	public abstract Ball[] ballChange(Ball[] balls, Ball ball);
}
