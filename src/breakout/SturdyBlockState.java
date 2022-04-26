package breakout;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Represents the state of a sturdy block in the breakout game.
 * 
 * @invar | getLocation() != null
 * @invar | getHealth() <= 3 && getHealth() >= 0
 * @immutable
 */
public class SturdyBlockState extends BlockState {
	
	/**
	 * @invar | location != null
	 */
	private final Rect location;
	
	/**
	 * @invar | health >= 0 && health <= 3
	 */
	private int health;
	private Color color;
	
	/**
	 * Construct a sturdy Block with a certain location and health.
	 * @pre | location != null
	 * @pre | health > 0
	 * @pre | health <= 3
	 * @post | getLocation().equals(location)
	 * @post | getHealth() == health
	 */
	public SturdyBlockState(Rect location, int health) {
		this.location = location;
		this.health = health;
	}

	/**
	 * Return the rectangle occupied by this block in the field.
	 * @post | result != null
	 */
	public Rect getLocation() {
		return location;
	}
	
	/**
	 * Return the colour of this Block.
	 * @post | result != null
	 * @inspects | this
	 */
	public Color getColor() {
		return setDynamicColor(this);
	}
	
	private Color setDynamicColor(SturdyBlockState block) {
		if(block.getHealth() == 3) {
			return new Color(0,0,153);
		}
		if(block.getHealth() == 2) {
			return new Color(0,0,204);
		}
		return new Color(0,0,255);
	}
	
	/**
	 * Removes THIS block from the Blockstate[] and/or makes it lose 1 health.
	 * @pre | blocks != null
	 * @creates | result
	 * @post | result != null
	 * @post | (result.length == blocks.length && this.getHealth() != 1) || result.length == blocks.length - 1 
	 */
	public BlockState[] removeBlock(BlockState[] blocks) {
		ArrayList<BlockState> nblocks = new ArrayList<BlockState>();
		for( BlockState b : blocks ) {
			if(b != this) {
				nblocks.add(b);
			}else {
				if( health != 1) {
					b = new SturdyBlockState(b.getLocation(), b.getHealth()-1);
					nblocks.add(b);
				}		
			}
		}
		return nblocks.toArray(new BlockState[] {});
	}
	
	/**
	 * Return the health of this Block.
	 * @post | result > 0
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Changes the ball's subclass if needed and resets its lifetime to 10000 if needed.
	 * @pre | ball != null
	 * @post | result != null
	 * @post | ball.getLocation().equals(result.getLocation())
	 * @post | ball.getVelocity().equals(result.getVelocity())
	 */
	public Ball ballChange(Ball ball) {
		return ball;
	}
	
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
	public PaddleState paddleChange(PaddleState paddle) {
		return paddle;
	}

}
