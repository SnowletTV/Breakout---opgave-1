package breakout;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Represents the state of a block in the breakout game.
 * 
 * @invar | getLocation() != null
 * @invar | getHealth() <= 3 && getHealth() >= 0
 * @immutable
 */
public abstract class BlockState {

	/**
	 * Return the rectangle occupied by this block in the field.
	 * @post | result != null
	 */
	public abstract Rect getLocation();
	
	/**
	 * Return the colour of this Block.
	 * @post | result != null
	 * @inspects | this
	 */
	public abstract Color getColor();
	
	/**
	 * Removes THIS block from the Blockstate[] and/or makes it lose 1 health.
	 * @pre | blocks != null
	 * @creates | result
	 * @post | result != null
	 * @post | (result.length == blocks.length && this.getHealth() != 1) || result.length == blocks.length - 1 
	 */
	public abstract BlockState[] removeBlock(BlockState[] blocks);

	/**
	 * Return the health of this Block.
	 * @post | result > 0
	 */
	public abstract int getHealth();
	
	/**
	 * Changes the ball's subclass if needed and resets its lifetime to 10000 if needed.
	 * @pre | ball != null
	 * @post | result != null
	 * @post | ball.getLocation().equals(result.getLocation())
	 * @post | ball.getVelocity().equals(result.getVelocity())
	 */
	public abstract Ball ballChange(Ball ball);
	
	/**
	 * Returns a new paddle with a different subclass if needed and resets its lifetime to 10000 if needed.
	 * @pre | paddle != null
	 * @pre | paddle.getHits() <= 3
	 * @pre | paddle.getHits() >= 0
	 * @post | result != null
	 * @post | paddle.getCenter().equals(result.getCenter())
	 * @post | result.getHits() <= 3
	 * @post | result.getHits() >= 0
	 * @post | result.getHits() == 3 || result.getHits() == 0
	 */
	public abstract PaddleState paddleChange(PaddleState paddle);
	
}