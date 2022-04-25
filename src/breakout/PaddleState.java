package breakout;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Represents the state of a paddle in the breakout game.
 *
 * @immutable
 * @invar | getCenter() != null
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
	* Returns this object's size.
	* @post | result != null
	*/
	public abstract Vector getSize();
	
	/**
	 * Returns this object's leftover hits
	 */
	public abstract int getHits();

	/**
	 * Return the rectangle occupied by this paddle in the field.
	 * 
	 * @post | result != null
	 * @post | result.getTopLeft().equals(getCenter().plus(new Vector(-WIDTH/2,-HEIGHT/2)))
	 * @post | result.getBottomRight().equals(getCenter().plus(new Vector(WIDTH/2,HEIGHT/2)))
	 */
	public abstract Rect getLocation();
	
	/**
	* Returns this object's size.
	* @post | result != null
	*/
	public abstract PaddleState changePaddle();
	
	/**
	* Returns this object's size.
	* @pre | center != null
	* @post | result != null
	*/
	public abstract PaddleState samePaddle(Point center);
	
	/**
	* Returns this object's size.
	* @pre | balls != null
	* @pre | ball != null
	* @post | result != null
	* @post | result.length - balls.length <= 3
	* @post | result.length - balls.length >= 0
	*/
	public abstract Ball[] ballChange(Ball[] balls, Ball ball);
}
